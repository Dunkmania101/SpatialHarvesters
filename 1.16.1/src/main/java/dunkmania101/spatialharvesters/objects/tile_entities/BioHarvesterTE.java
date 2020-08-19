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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;

public class BioHarvesterTE extends TileEntity implements ITickableTileEntity {
    public BioHarvesterTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public BioHarvesterTE() {
        this(TileEntityInit.BIO_HARVESTER.get());
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

    private static final ArrayList<Item> PLANTS = Tools.getLoadedPlantsAndDyes();
    private int ticks = 0;
    @Override
    public void tick() {
        Item CHOSEN_PLANT = ItemInit.CRYSTAL_1.get();
        int delay = Config.BIO_1_SPEED.get();
        int price = Config.BIO_1_PRICE.get();
        Block this_block = getBlockState().getBlock();
        if (this_block == BlockInit.BIO_HARVESTER_1.get()) {
            CHOSEN_PLANT = ItemInit.SHARD_1.get();
            price = Config.BIO_1_PRICE.get();
            delay = Config.BIO_1_SPEED.get();
        } else if (this_block == BlockInit.BIO_HARVESTER_2.get()) {
            CHOSEN_PLANT = ItemInit.SHARD_2.get();
            price = Config.BIO_2_PRICE.get();
            delay = Config.BIO_2_SPEED.get();
        } else if (this_block == BlockInit.BIO_HARVESTER_3.get()) {
            CHOSEN_PLANT = ItemInit.SHARD_3.get();
            price = Config.BIO_3_PRICE.get();
            delay = Config.BIO_3_SPEED.get();
        } else if (this_block == BlockInit.BIO_HARVESTER_4.get()) {
            CHOSEN_PLANT = ItemInit.SHARD_4.get();
            price = Config.BIO_4_PRICE.get();
            delay = Config.BIO_4_SPEED.get();
        } else if (this_block == BlockInit.BIO_HARVESTER_5.get()) {
            CHOSEN_PLANT = ItemInit.SHARD_5.get();
            price = Config.BIO_5_PRICE.get();
            delay = Config.BIO_5_SPEED.get();
        } else if (this_block == BlockInit.BIO_HARVESTER_6.get()) {
            CHOSEN_PLANT = ItemInit.SHARD_6.get();
            price = Config.BIO_6_PRICE.get();
            delay = Config.BIO_6_SPEED.get();
        } else if (this_block == BlockInit.BIO_HARVESTER_7.get()) {
            CHOSEN_PLANT = ItemInit.SHARD_7.get();
            price = Config.BIO_7_PRICE.get();
            delay = Config.BIO_7_SPEED.get();
        } else if (this_block == BlockInit.BIO_HARVESTER_8.get()) {
            CHOSEN_PLANT = ItemInit.SHARD_7.get();
            price = Config.BIO_8_PRICE.get();
            delay = Config.BIO_8_SPEED.get();
        }
        if (ticks >= delay) {
            if (world != null && !world.isRemote) {
                if (energyStorage.getEnergyStored() >= price) {
                    if (world.getBlockState(pos.offset(Direction.UP)).getBlock() == BlockInit.SPACE_RIPPER.get()) {
                        TileEntity out_down = world.getTileEntity(pos.offset(Direction.DOWN));
                        if (out_down != null && !world.isRemote) {
                            LazyOptional<IItemHandler> out_down_cap = out_down.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP);
                            if (out_down_cap.isPresent()) {
                                IItemHandler out_down_inv = out_down_cap.orElse(null);
                                Random rand = world.rand;
                                if (rand.nextInt(75) != 1) {
                                    if (PLANTS.size() > 0) {
                                        CHOSEN_PLANT = PLANTS.get(rand.nextInt(PLANTS.size()));
                                    }
                                }
                                ItemStack CHOSEN_PLANT_STACK = new ItemStack(CHOSEN_PLANT);
                                ItemHandlerHelper.insertItemStacked(out_down_inv, CHOSEN_PLANT_STACK, false);
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
