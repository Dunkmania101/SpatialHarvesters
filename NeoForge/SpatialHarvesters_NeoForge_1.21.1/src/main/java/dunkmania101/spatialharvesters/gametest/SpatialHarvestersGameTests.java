package dunkmania101.spatialharvesters.gametest;

import java.util.List;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.objects.tile_entities.OreHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;
import net.neoforged.neoforge.items.IItemHandler;

@GameTestHolder("spatialharvesters")
@PrefixGameTestTemplate(false) // template names are used as-is, not prefixed with the class name
public class SpatialHarvestersGameTests {
    private static final String PLATFORM = "machine_platform";

    @GameTest(template = PLATFORM, timeoutTicks = 200)
    public static void harvesterFillsAdjacentChest(GameTestHelper helper) {
        BlockPos machine = new BlockPos(4, 1, 4);
        BlockPos chest = machine.west();

        helper.setBlock(machine, BlockInit.ORE_HARVESTER_1.get());
        helper.setBlock(machine.east(), BlockInit.SPACE_RIPPER.get());
        helper.setBlock(chest, Blocks.CHEST);

        helper.startSequence()
                .thenIdle(2)
                .thenExecute(() -> {
                    OreHarvesterTE te = helper.getBlockEntity(machine);
                    helper.assertTrue(te != null, "no OreHarvesterTE at " + machine);
                    te.getEnergyStorage().addEnergy(Integer.MAX_VALUE);
                    helper.assertTrue(te.getEnergyStorage().getEnergyStored() > 0,
                            "energy storage did not accept any energy");
                    te.setCountedTicks(Integer.MAX_VALUE - 1);
                })
                .thenIdle(10)
                .thenExecute(() -> {
                    IEnergyStorage energy = helper.getLevel().getCapability(
                            Capabilities.EnergyStorage.BLOCK, helper.absolutePos(machine), null);
                    helper.assertTrue(energy != null,
                            "machine exposed no IEnergyStorage capability");
                    helper.assertTrue(energy.getEnergyStored() > 0,
                            "energy capability reported an empty buffer");

                    IItemHandler out = helper.getLevel().getCapability(
                            Capabilities.ItemHandler.BLOCK, helper.absolutePos(chest), null);
                    helper.assertTrue(out != null, "chest exposed no IItemHandler capability");

                    boolean gotSomething = false;
                    for (int slot = 0; slot < out.getSlots(); slot++) {
                        if (!out.getStackInSlot(slot).isEmpty()) {
                            gotSomething = true;
                            break;
                        }
                    }
                    helper.assertTrue(gotSomething, "harvester produced nothing into the chest");
                })
                .thenSucceed();
    }

    @GameTest(template = PLATFORM, timeoutTicks = 200)
    public static void machineStateSurvivesBreakAndPlace(GameTestHelper helper) {
        BlockPos machine = new BlockPos(2, 1, 2);
        BlockPos replaced = new BlockPos(6, 1, 6);
        int marker = 1234;

        helper.setBlock(machine, BlockInit.ORE_HARVESTER_1.get());

        helper.startSequence()
                .thenIdle(2)
                .thenExecute(() -> {
                    OreHarvesterTE te = helper.getBlockEntity(machine);
                    helper.assertTrue(te != null, "no OreHarvesterTE at " + machine);
                    te.getEnergyStorage().addEnergy(marker);
                    helper.assertValueEqual(te.getEnergyStorage().getEnergyStored(), marker, "stored energy");

                    BlockPos abs = helper.absolutePos(machine);
                    BlockState state = helper.getLevel().getBlockState(abs);
                    BlockEntity be = helper.getLevel().getBlockEntity(abs);

                    state.getBlock().playerWillDestroy(helper.getLevel(), abs, state,
                            helper.makeMockPlayer(net.minecraft.world.level.GameType.SURVIVAL));
                    List<ItemStack> drops = net.minecraft.world.level.block.Block.getDrops(
                            state, helper.getLevel(), abs, be,
                            helper.makeMockPlayer(net.minecraft.world.level.GameType.SURVIVAL),
                            new ItemStack(Items.NETHERITE_PICKAXE));

                    ItemStack machineDrop = drops.stream()
                            .filter(s -> s.getItem() == ItemInit.ORE_HARVESTER_1.get())
                            .findFirst()
                            .orElse(ItemStack.EMPTY);
                    helper.assertTrue(!machineDrop.isEmpty(),
                            "breaking the machine did not drop its own block item");

                    CompoundTag preserved = Tools.getCustomData(machineDrop)
                            .getCompound(CustomValues.stackTileNBTKey);
                    helper.assertTrue(!preserved.isEmpty(),
                            "dropped stack carried no preserved block entity data");
                    helper.assertValueEqual(preserved.getInt(CustomValues.energyStorageKey), marker,
                            "energy preserved on the dropped stack");

                    helper.setBlock(replaced, BlockInit.ORE_HARVESTER_1.get());
                    BlockPos absReplaced = helper.absolutePos(replaced);
                    helper.getLevel().getBlockState(absReplaced).getBlock().setPlacedBy(
                            helper.getLevel(), absReplaced,
                            helper.getLevel().getBlockState(absReplaced), null, machineDrop);

                    OreHarvesterTE restored = helper.getBlockEntity(replaced);
                    helper.assertTrue(restored != null, "no OreHarvesterTE at " + replaced);
                    helper.assertValueEqual(restored.getEnergyStorage().getEnergyStored(), marker,
                            "energy restored after placing the stack");
                })
                .thenSucceed();
    }
}
