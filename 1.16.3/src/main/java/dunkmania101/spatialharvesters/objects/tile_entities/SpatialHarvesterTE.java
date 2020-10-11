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
    protected ArrayList<ItemStack> OUTPUTS = new ArrayList<>();

    public SpatialHarvesterTE(TileEntityType<?> tileEntityTypeIn, ArrayList<Item> OUTPUTS) {
        super(tileEntityTypeIn, true, true, true);

        setOutputs(OUTPUTS);
    }

    @Override
    public void customTickActions() {
        super.customTickActions();
        if (getWorld() != null && !getWorld().isRemote) {
            Block thisBlock = getBlockState().getBlock();
            if (getCountedTicks() >= getSpeed(thisBlock)) {
                resetCountedTicks();
                int price = getPrice(thisBlock);
                ArrayList<Direction> spaceRippers = new ArrayList<>();
                ArrayList<IItemHandler> outInventories = new ArrayList<>();
                for (Direction side : Direction.values()) {
                    if (getWorld().getBlockState(pos.offset(side)).getBlock() instanceof SpaceRipperBlock) {
                        spaceRippers.add(side);
                    }
                    TileEntity out = getWorld().getTileEntity(pos.offset(side));
                    if (out != null) {
                        LazyOptional<IItemHandler> outCap = out.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side.getOpposite());
                        outCap.ifPresent(outInventories::add);
                    }
                }
                setActive(false);
                lastMinuteActions();
                if (spaceRippers.size() > 0 && outInventories.size() > 0 && this.OUTPUTS.size() > 0) {
                    for (Direction ignored : spaceRippers) {
                        if (getEnergyStorage().getEnergyStored() >= price) {
                            Random rand = getWorld().rand;
                            ItemStack chosenOutput = new ItemStack(getShard(thisBlock));
                            if (rand.nextInt(75) != 1) {
                                chosenOutput = this.OUTPUTS.get(rand.nextInt(this.OUTPUTS.size()));
                            }
                            int originalCount = chosenOutput.getCount();
                            IItemHandler inventory = outInventories.get(rand.nextInt(outInventories.size()));
                            ItemStack resultStack = ItemHandlerHelper.insertItemStacked(inventory, chosenOutput, false);
                            if (resultStack.getCount() != originalCount) {
                                getEnergyStorage().consumeEnergy(price);
                                setActive(true);
                            }
                        }
                    }
                }
            }
        }
    }

    protected void lastMinuteActions() {
    }

    public void setOutputs(ArrayList<Item> OUTPUTS) {
        this.OUTPUTS = new ArrayList<>();
        for (Item item : OUTPUTS) {
            ItemStack stack = new ItemStack(item);
            if (!stack.isEmpty()) {
                this.OUTPUTS.add(stack);
            }
        }
    }

    public void setOutputStacks(ArrayList<ItemStack> OUTPUTS) {
        this.OUTPUTS = OUTPUTS;
    }

    private Block thisBlock = null;

    @Override
    public void onLoad() {
        super.onLoad();
        this.thisBlock = getBlockState().getBlock();
        updateEnergyStorage();
    }

    @Override
    protected int getCapacity() {
        if (thisBlock != null) {
            return getPrice(thisBlock) * 10;
        }
        return Integer.MAX_VALUE;
    }

    @Override
    protected int getMaxInput() {
        return getCapacity();
    }

    @Override
    protected int getMaxExtract() {
        return getCapacity();
    }

    public int getPrice(Block block) {
        return Integer.MAX_VALUE;
    }

    public int getSpeed(Block block) {
        return Integer.MAX_VALUE;
    }

    public Item getShard(Block block) {
        return ItemInit.SHARD_1.get();
    }
}
