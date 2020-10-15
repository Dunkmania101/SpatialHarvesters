package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.CustomEnergyMachineTE;
import dunkmania101.spatialharvesters.objects.tile_entities.HeatGeneratorTE;
import dunkmania101.spatialharvesters.objects.tile_entities.TickingRedstoneEnergyMachineTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class PreservedDataCustomHorizontalShapedBlock extends CustomHorizontalShapedBlock {
    public PreservedDataCustomHorizontalShapedBlock(Properties properties, VoxelShape shape, Direction frontDirection) {
        super(properties, shape, frontDirection);
    }

    public PreservedDataCustomHorizontalShapedBlock(Properties properties, VoxelShape shape) {
        this(properties, shape, Direction.NORTH);
    }

    private CompoundNBT thisTileNBT = new CompoundNBT();

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile != null) {
            this.thisTileNBT = tile.serializeNBT();
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> drops = super.getDrops(state, builder);
        if (this.thisTileNBT != null) {
            if (!this.thisTileNBT.isEmpty()) {
                return Tools.getPreservedDataBlockDrops(drops, state, this.thisTileNBT);
            }
        }
        return drops;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isRemote) {
            TileEntity tile = worldIn.getTileEntity(pos);
            CompoundNBT stackTileNBT = stack.getChildTag(CustomValues.stackTileNBTKey);
            if (tile != null && stackTileNBT != null) {
                tile.deserializeNBT(Tools.correctTileNBT(tile, stackTileNBT));
            }
        }
    }

    @Override
    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile != null) {
            CompoundNBT data = tile.serializeNBT();
            if (tile instanceof CustomEnergyMachineTE) {
                player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.divider"), false);
                player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.energy_message"), false);
                player.sendStatusMessage(new StringTextComponent(Integer.toString(data.getInt(CustomValues.energyStorageKey))), false);
                player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.divider"), false);
            }
            if (tile instanceof TickingRedstoneEnergyMachineTE) {
                if (!(tile instanceof HeatGeneratorTE)) {
                    player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.divider"), false);
                    player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.counted_ticks_message"), false);
                    player.sendStatusMessage(new StringTextComponent(Integer.toString(data.getInt(CustomValues.countedTicksKey))), false);
                    player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.divider"), false);
                }
            }
        }
        super.onBlockClicked(state, worldIn, pos, player);
    }
}
