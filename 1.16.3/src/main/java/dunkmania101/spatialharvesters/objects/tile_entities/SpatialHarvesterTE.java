package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.objects.blocks.SpaceRipperBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.ArrayList;
import java.util.Random;

public class SpatialHarvesterTE extends TickingRedstoneEnergyMachineTE {
    private final ArrayList<Item> OUTPUTS;

    public SpatialHarvesterTE(TileEntityType<?> tileEntityTypeIn, ArrayList<Item> OUTPUTS) {
        super(tileEntityTypeIn, false, true, true);

        this.OUTPUTS = OUTPUTS;
    }

    @Override
    public void customTickActions() {
        if (world != null && !world.isRemote) {
            Block this_block = getBlockState().getBlock();
            int set_capacity = getPrice(this_block) * 10;
            if (getEnergyStorage().getMaxEnergyStored() != set_capacity) {
                getEnergyStorage().setMaxEnergy(set_capacity);
            }
            if (getEnergyStorage().getMaxInput() != set_capacity) {
                getEnergyStorage().setMaxInput(set_capacity);
            }
            if (getEnergyStorage().getMaxExtract() != set_capacity) {
                getEnergyStorage().setMaxExtract(set_capacity);
            }
            if (getCountedTicks() >= getSpeed(this_block)) {
                resetCountedTicks();
                int price = getPrice(this_block);
                ArrayList<Direction> space_rippers = new ArrayList<>();
                ArrayList<IItemHandler> out_inventories = new ArrayList<>();
                for (Direction side : Direction.values()) {
                    if (world.getBlockState(pos.offset(side)).getBlock() instanceof SpaceRipperBlock) {
                        space_rippers.add(side);
                    }
                    TileEntity out = world.getTileEntity(pos.offset(side));
                    if (out != null) {
                        LazyOptional<IItemHandler> out_cap = out.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side.getOpposite());
                        out_cap.ifPresent(out_inventories::add);
                    }
                }
                if (space_rippers.size() > 0 && out_inventories.size() > 0) {
                    Random rand = world.rand;
                    for (Direction ignored : space_rippers) {
                        if (getEnergyStorage().getEnergyStored() >= price) {
                            Item CHOSEN_OUTPUT = getShard(this_block);
                            if (rand.nextInt(75) != 1) {
                                if (OUTPUTS.size() > 0) {
                                    CHOSEN_OUTPUT = OUTPUTS.get(rand.nextInt(OUTPUTS.size()));
                                }
                            }
                            ItemStack output_stack = new ItemStack(CHOSEN_OUTPUT);
                            ItemStack result_stack = ItemHandlerHelper.insertItemStacked(out_inventories.get(rand.nextInt(out_inventories.size())), output_stack, false);
                            if (result_stack.getCount() < output_stack.getCount()) {
                                getEnergyStorage().consumeEnergy(price);
                            }
                        }
                    }
                }
            }
        }
    }

    public int getPrice(Block block) {
        return 500;
    }

    public int getSpeed(Block block) {
        return 500;
    }

    public Item getShard(Block block) {
        return ItemInit.SHARD_1.get();
    }
}
