package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CustomProperties;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.objects.blocks.ActiveCustomHorizontalShapedBlock;
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
        super(tileEntityTypeIn, false, true, true);

        setOutputs(OUTPUTS);
    }

    @Override
    public void tick() {
        Block thisBlock = getBlockState().getBlock();
        setEnergyCapacity(this.getPrice(thisBlock) * 10);
        super.tick();
    }

    @Override
    public void customTickActions() {
        if (getWorld() != null && !getWorld().isRemote) {
            Block thisBlock = getBlockState().getBlock();
            if (getCountedTicks() >= getSpeed(thisBlock)) {
                resetCountedTicks();
                boolean active = false;
                int price = getPrice(thisBlock);
                ArrayList<Direction> spaceRippers = new ArrayList<>();
                ArrayList<IItemHandler> outInventories = new ArrayList<>();
                for (Direction side : Direction.values()) {
                    if (getWorld().getBlockState(pos.offset(side)).getBlock() instanceof SpaceRipperBlock) {
                        spaceRippers.add(side);
                    }
                    TileEntity out = getWorld().getTileEntity(pos.offset(side));
                    if (out != null) {
                        LazyOptional<IItemHandler> out_cap = out.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side.getOpposite());
                        out_cap.ifPresent(outInventories::add);
                    }
                }
                if (spaceRippers.size() > 0 && outInventories.size() > 0 && this.OUTPUTS.size() > 0) {
                    for (Direction ignored : spaceRippers) {
                        if (getEnergyStorage().getEnergyStored() >= price) {
                            ItemStack chosenOutput = new ItemStack(getShard(thisBlock));
                            Random rand = getWorld().rand;
                            if (rand.nextInt(75) != 1) {
                                chosenOutput = this.OUTPUTS.get(rand.nextInt(this.OUTPUTS.size()));
                            }
                            ItemStack resultStack = ItemHandlerHelper.insertItemStacked(outInventories.get(rand.nextInt(outInventories.size())), chosenOutput, false);
                            if (resultStack != chosenOutput) {
                                getEnergyStorage().consumeEnergy(price);
                                active = true;
                            }
                        }
                    }
                }
                if (thisBlock instanceof ActiveCustomHorizontalShapedBlock) {
                    getWorld().setBlockState(pos, getBlockState().with(CustomProperties.ACTIVE, active));
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
