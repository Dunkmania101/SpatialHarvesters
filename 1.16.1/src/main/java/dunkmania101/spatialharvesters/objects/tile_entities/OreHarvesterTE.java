package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.Config;
import dunkmania101.spatialharvesters.data.CustomEnergyStorage;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;

public class OreHarvesterTE extends TileEntity implements ITickableTileEntity {
    public OreHarvesterTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public OreHarvesterTE() {
        this(TileEntityInit.ORE_HARVESTER.get());
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
                return true;
            }

            @Override
            public boolean canReceive() {
                return true;
            }

            @Override
            public int receiveEnergy(int maxReceive, boolean simulate) {
                return super.receiveEnergy(maxReceive, simulate);
            }

            @Override
            public int extractEnergy(int maxExtract, boolean simulate) {
                return super.extractEnergy(maxExtract, simulate);
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

    Random rand = new Random();
    private int ticks = 0;
    @Override
    public void tick() {
        Item CHOSEN_ORE = ItemInit.CRYSTAL_1.get();
        Block this_block = getBlockState().getBlock();
        int price = Config.ORE_1_PRICE.get();
        int delay = Config.ORE_1_SPEED.get();
        if (this_block == BlockInit.ORE_HARVESTER_1.get()) {
            CHOSEN_ORE = ItemInit.SHARD_1.get();
            price = Config.ORE_1_PRICE.get();
            delay = Config.ORE_1_SPEED.get();
        } else if (this_block == BlockInit.ORE_HARVESTER_2.get()) {
            CHOSEN_ORE = ItemInit.SHARD_2.get();
            price = Config.ORE_2_PRICE.get();
            delay = Config.ORE_2_SPEED.get();
        } else if (this_block == BlockInit.ORE_HARVESTER_3.get()) {
            CHOSEN_ORE = ItemInit.SHARD_3.get();
            price = Config.ORE_3_PRICE.get();
            delay = Config.ORE_3_SPEED.get();
        } else if (this_block == BlockInit.ORE_HARVESTER_4.get()) {
            CHOSEN_ORE = ItemInit.SHARD_4.get();
            price = Config.ORE_4_PRICE.get();
            delay = Config.ORE_4_SPEED.get();
        } else if (this_block == BlockInit.ORE_HARVESTER_5.get()) {
            CHOSEN_ORE = ItemInit.SHARD_5.get();
            price = Config.ORE_5_PRICE.get();
            delay = Config.ORE_5_SPEED.get();
        } else if (this_block == BlockInit.ORE_HARVESTER_6.get()) {
            CHOSEN_ORE = ItemInit.SHARD_6.get();
            price = Config.ORE_6_PRICE.get();
            delay = Config.ORE_6_SPEED.get();
        } else if (this_block == BlockInit.ORE_HARVESTER_7.get()) {
            CHOSEN_ORE = ItemInit.SHARD_7.get();
            price = Config.ORE_1_PRICE.get();
            delay = Config.ORE_7_SPEED.get();
        } else if (this_block == BlockInit.ORE_HARVESTER_8.get()) {
            CHOSEN_ORE = ItemInit.SHARD_7.get();
            price = Config.ORE_8_PRICE.get();
            delay = Config.ORE_8_SPEED.get();
        }
        if (ticks >= delay) {
            ticks = 0;
            if (world != null) {
                if (energyStorage.getEnergyStored() >= price) {
                    if (world.getBlockState(pos.offset(Direction.UP)).getBlock() == BlockInit.SPACE_RIPPER.get()) {
                        TileEntity out_down = world.getTileEntity(pos.offset(Direction.DOWN));
                        if (out_down != null) {
                            LazyOptional<IItemHandler> out_down_inv = out_down.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP);
                            if (out_down_inv.isPresent()) {
                                IItemHandler out_down_handler = out_down_inv.orElse(null);
                                if (rand.nextInt(75) != 1) {
                                    ArrayList<Block> ORES = Tools.getLoadedOres();
                                    CHOSEN_ORE = ORES.get(rand.nextInt(ORES.size())).asItem();
                                }
                                ItemHandlerHelper.insertItemStacked(out_down_handler, CHOSEN_ORE.getDefaultInstance(), false);
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
