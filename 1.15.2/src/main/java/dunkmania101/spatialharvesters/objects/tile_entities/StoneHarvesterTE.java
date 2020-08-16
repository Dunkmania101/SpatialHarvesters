package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.Config;
import dunkmania101.spatialharvesters.data.CustomEnergyStorage;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;

public class StoneHarvesterTE extends TileEntity implements ITickableTileEntity {
    public StoneHarvesterTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public StoneHarvesterTE() {
        this(TileEntityInit.STONE_HARVESTER.get());
    }

    private CustomEnergyStorage energyStorage = createEnergy();

    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    private CustomEnergyStorage createEnergy() {
        return new CustomEnergyStorage(10000, 10000) {
            @Override
            protected void onEnergyChanged() {
                markDirty();
            }

            @Override
            public boolean canExtract() {
                return false;
            }

            @Override
            public boolean canReceive() {
                return true;
            }
        };
    }

    @Override
    public void remove() {
        super.remove();
        energy.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }

    private int ticks = 0;
    @Override
    public void tick() {
        Item CHOSEN_STONE = ItemInit.CRYSTAL_1.get();
        int delay = Config.STONE_1_SPEED.get();
        int price = Config.STONE_1_PRICE.get();
        Block this_block = getBlockState().getBlock();
        if (this_block == BlockInit.STONE_HARVESTER_1.get()) {
            CHOSEN_STONE = ItemInit.SHARD_1.get();
            price = Config.STONE_1_PRICE.get();
            delay = Config.STONE_1_SPEED.get();
        } else if (this_block == BlockInit.STONE_HARVESTER_2.get()) {
            CHOSEN_STONE = ItemInit.SHARD_2.get();
            price = Config.STONE_2_PRICE.get();
            delay = Config.STONE_2_SPEED.get();
        } else if (this_block == BlockInit.STONE_HARVESTER_3.get()) {
            CHOSEN_STONE = ItemInit.SHARD_3.get();
            price = Config.STONE_3_PRICE.get();
            delay = Config.STONE_3_SPEED.get();
        } else if (this_block == BlockInit.STONE_HARVESTER_4.get()) {
            CHOSEN_STONE = ItemInit.SHARD_4.get();
            price = Config.STONE_4_PRICE.get();
            delay = Config.STONE_4_SPEED.get();
        } else if (this_block == BlockInit.STONE_HARVESTER_5.get()) {
            CHOSEN_STONE = ItemInit.SHARD_5.get();
            price = Config.STONE_5_PRICE.get();
            delay = Config.STONE_5_SPEED.get();
        } else if (this_block == BlockInit.STONE_HARVESTER_6.get()) {
            CHOSEN_STONE = ItemInit.SHARD_6.get();
            price = Config.STONE_6_PRICE.get();
            delay = Config.STONE_6_SPEED.get();
        } else if (this_block == BlockInit.STONE_HARVESTER_7.get()) {
            CHOSEN_STONE = ItemInit.SHARD_7.get();
            price = Config.STONE_7_PRICE.get();
            delay = Config.STONE_7_SPEED.get();
        } else if (this_block == BlockInit.STONE_HARVESTER_8.get()) {
            CHOSEN_STONE = ItemInit.SHARD_7.get();
            price = Config.STONE_8_PRICE.get();
            delay = Config.STONE_8_SPEED.get();
        }
        if (ticks >= delay) {
            if (world != null && !world.isRemote) {
                if (energyStorage.getEnergyStored() >= price) {
                    if (world.getBlockState(pos.offset(Direction.UP)).getBlock() == BlockInit.SPACE_RIPPER.get()) {
                        TileEntity out_down = world.getTileEntity(pos.offset(Direction.DOWN));
                        if (out_down != null && !world.isRemote) {
                            if (out_down instanceof IInventory) {
                                IInventory out_down_inv = (IInventory) out_down;
                                Random rand = world.rand;
                                if (rand.nextInt(75) != 1) {
                                    ArrayList<Item> STONES = Tools.getLoadedStones();
                                    if (STONES.size() > 0) {
                                        CHOSEN_STONE = STONES.get(rand.nextInt(STONES.size()));
                                    }
                                }
                                ItemStack CHOSEN_STONE_STACK = CHOSEN_STONE.getDefaultInstance();
                                int chosen_slot = -1;
                                int empty_slot = -1;
                                for(int i = 0; i < out_down_inv.getSizeInventory(); ++i) {
                                    ItemStack stack = out_down_inv.getStackInSlot(i);
                                    if (out_down_inv.getStackInSlot(i).getItem() == CHOSEN_STONE && (stack.getCount() < stack.getMaxStackSize())) {
                                        chosen_slot = i;
                                        break;
                                    }
                                    if (out_down_inv.getStackInSlot(i).isEmpty() && empty_slot < 0) {
                                        empty_slot = i;
                                    }
                                }
                                if (chosen_slot < 0) {
                                    chosen_slot = empty_slot;
                                }
                                if (chosen_slot > -1) {
                                    ItemStack stack = out_down_inv.getStackInSlot(chosen_slot);
                                    CHOSEN_STONE_STACK.setCount(stack.getCount() + 1);
                                    out_down_inv.setInventorySlotContents(chosen_slot, CHOSEN_STONE_STACK);
                                }
                                energyStorage.consumeEnergy(price);
                            }
                        }
                    }
                }
            }
        } else {
            ticks++;
        }
    }
}
