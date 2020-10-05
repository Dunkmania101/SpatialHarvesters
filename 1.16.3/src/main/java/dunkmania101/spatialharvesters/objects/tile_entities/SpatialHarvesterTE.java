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
    private ArrayList<ItemStack> OUTPUTS = new ArrayList<>();

    public SpatialHarvesterTE(TileEntityType<?> tileEntityTypeIn, ArrayList<Item> OUTPUTS) {
        super(tileEntityTypeIn, false, true, true);

        setOutputs(OUTPUTS);
    }

    @Override
    public void tick() {
        Block this_block = getBlockState().getBlock();
        setEnergyCapacity(this.getPrice(this_block) * 10);
        super.tick();
    }

    @Override
    public void customTickActions() {
        if (world != null && !world.isRemote) {
            Block this_block = getBlockState().getBlock();
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
                if (space_rippers.size() > 0 && out_inventories.size() > 0 && this.OUTPUTS.size() > 0) {
                    Random rand = world.rand;
                    for (Direction ignored : space_rippers) {
                        if (getEnergyStorage().getEnergyStored() >= price) {
                            ItemStack CHOSEN_OUTPUT = new ItemStack(getShard(this_block));
                            if (rand.nextInt(75) != 1) {
                                CHOSEN_OUTPUT = this.OUTPUTS.get(rand.nextInt(this.OUTPUTS.size()));
                            }
                            ItemStack result_stack = ItemHandlerHelper.insertItemStacked(out_inventories.get(rand.nextInt(out_inventories.size())), CHOSEN_OUTPUT, false);
                            if (result_stack.getCount() < CHOSEN_OUTPUT.getCount()) {
                                getEnergyStorage().consumeEnergy(price);
                            }
                        }
                    }
                }
            }
        }
    }

    public void setOutputs(ArrayList<Item> OUTPUTS) {
        this.OUTPUTS = new ArrayList<>();
        for (Item item : OUTPUTS) {
            this.OUTPUTS.add(new ItemStack(item));
        }
    }

    public void setOutputStacks(ArrayList<ItemStack> OUTPUTS) {
        this.OUTPUTS = OUTPUTS;
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
