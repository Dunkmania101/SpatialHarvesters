package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.Config;
import dunkmania101.spatialharvesters.data.CustomEnergyStorage;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
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

public class OreHarvesterTE extends TileEntity implements ITickableTileEntity {
    public OreHarvesterTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public OreHarvesterTE() {
        this(TileEntityInit.ORE_HARVESTER.get());
    }

    private final CustomEnergyStorage energyStorage = createEnergy();

    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    private CustomEnergyStorage createEnergy() {
        int capacity = Config.ORE_1_PRICE.get() * 2;
        return new CustomEnergyStorage(capacity, capacity) {
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

    private static final ArrayList<Item> ORES = Tools.getLoadedOres();
    private int ticks = 0;
    @Override
    public void tick() {
        Block this_block = getBlockState().getBlock();
        int speed = getSpeed(this_block);
        if (ticks >= speed) {
            ticks = 0;
            if (world != null && !world.isRemote) {
                int price = getPrice(this_block);
                int set_capacity = price * 2;
                energyStorage.setMaxEnergy(set_capacity);
                energyStorage.setMaxTransfer(set_capacity);
                if (energyStorage.getEnergyStored() >= price) {
                    if (world.getBlockState(pos.offset(Direction.UP)).getBlock() == BlockInit.SPACE_RIPPER.get()) {
                        TileEntity out_down = world.getTileEntity(pos.offset(Direction.DOWN));
                        if (out_down != null && !world.isRemote) {
                            LazyOptional<IItemHandler> out_down_cap = out_down.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP);
                            if (out_down_cap.isPresent()) {
                                IItemHandler out_down_inv = out_down_cap.orElse(null);
                                Item CHOSEN_ORE = getShard(this_block);
                                Random rand = world.rand;
                                if (rand.nextInt(75) != 1) {
                                    if (ORES.size() > 0) {
                                        CHOSEN_ORE = ORES.get(rand.nextInt(ORES.size()));
                                    }
                                }
                                ItemHandlerHelper.insertItemStacked(out_down_inv, new ItemStack(CHOSEN_ORE), false);
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

    private int getPrice(Block block) {
        int price = Config.ORE_1_PRICE.get();
        if (block == BlockInit.ORE_HARVESTER_1.get()) {
            price = Config.ORE_1_PRICE.get();
        } else if (block == BlockInit.ORE_HARVESTER_2.get()) {
            price = Config.ORE_2_PRICE.get();
        } else if (block == BlockInit.ORE_HARVESTER_3.get()) {
            price = Config.ORE_3_PRICE.get();
        } else if (block == BlockInit.ORE_HARVESTER_4.get()) {
            price = Config.ORE_4_PRICE.get();
        } else if (block == BlockInit.ORE_HARVESTER_5.get()) {
            price = Config.ORE_5_PRICE.get();
        } else if (block == BlockInit.ORE_HARVESTER_6.get()) {
            price = Config.ORE_6_PRICE.get();
        } else if (block == BlockInit.ORE_HARVESTER_7.get()) {
            price = Config.ORE_7_PRICE.get();
        } else if (block == BlockInit.ORE_HARVESTER_8.get()) {
            price = Config.ORE_8_PRICE.get();
        }
        return price;
    }

    private int getSpeed(Block block) {
        int speed = Config.ORE_1_SPEED.get();
        if (block == BlockInit.ORE_HARVESTER_1.get()) {
            speed = Config.ORE_1_SPEED.get();
        } else if (block == BlockInit.ORE_HARVESTER_2.get()) {
            speed = Config.ORE_2_SPEED.get();
        } else if (block == BlockInit.ORE_HARVESTER_3.get()) {
            speed = Config.ORE_3_SPEED.get();
        } else if (block == BlockInit.ORE_HARVESTER_4.get()) {
            speed = Config.ORE_4_SPEED.get();
        } else if (block == BlockInit.ORE_HARVESTER_5.get()) {
            speed = Config.ORE_5_SPEED.get();
        } else if (block == BlockInit.ORE_HARVESTER_6.get()) {
            speed = Config.ORE_6_SPEED.get();
        } else if (block == BlockInit.ORE_HARVESTER_7.get()) {
            speed = Config.ORE_7_SPEED.get();
        } else if (block == BlockInit.ORE_HARVESTER_8.get()) {
            speed = Config.ORE_8_SPEED.get();
        }
        return speed;
    }

    private Item getShard(Block block) {
        Item CHOSEN_SHARD = ItemInit.SHARD_1.get();
        if (block == BlockInit.ORE_HARVESTER_1.get()) {
            CHOSEN_SHARD = ItemInit.SHARD_1.get();
        } else if (block == BlockInit.ORE_HARVESTER_2.get()) {
            CHOSEN_SHARD = ItemInit.SHARD_2.get();
        } else if (block == BlockInit.ORE_HARVESTER_3.get()) {
            CHOSEN_SHARD = ItemInit.SHARD_3.get();
        } else if (block == BlockInit.ORE_HARVESTER_4.get()) {
            CHOSEN_SHARD = ItemInit.SHARD_4.get();
        } else if (block == BlockInit.ORE_HARVESTER_5.get()) {
            CHOSEN_SHARD = ItemInit.SHARD_5.get();
        } else if (block == BlockInit.ORE_HARVESTER_6.get()) {
            CHOSEN_SHARD = ItemInit.SHARD_6.get();
        } else if (block == BlockInit.ORE_HARVESTER_7.get()) {
            CHOSEN_SHARD = ItemInit.SHARD_7.get();
        } else if (block == BlockInit.ORE_HARVESTER_8.get()) {
            CHOSEN_SHARD = ItemInit.SHARD_7.get();
        }
        return CHOSEN_SHARD;
    }
}
