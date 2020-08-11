package dunkmania101.spatialharvesters.objects.tile_entities;

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
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.ArrayList;
import java.util.Random;

public class BioHarvesterTE extends TileEntity implements ITickableTileEntity {
    public BioHarvesterTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public BioHarvesterTE() {
        this(TileEntityInit.BIO_HARVESTER.get());
    }

    Random rand = new Random();
    private int delay = 400;
    private int ticks = 0;
    @Override
    public void tick() {
        Item CHOSEN_PLANT = ItemInit.CRYSTAL_1.get();
        Block this_block = getBlockState().getBlock();
        if (this_block == BlockInit.BIO_HARVESTER_1.get()) {
            CHOSEN_PLANT = ItemInit.SHARD_1.get();
            delay = 400;
        } else if (this_block == BlockInit.BIO_HARVESTER_2.get()) {
            CHOSEN_PLANT = ItemInit.SHARD_2.get();
            delay = 350;
        } else if (this_block == BlockInit.BIO_HARVESTER_3.get()) {
            CHOSEN_PLANT = ItemInit.SHARD_3.get();
            delay = 300;
        } else if (this_block == BlockInit.BIO_HARVESTER_4.get()) {
            CHOSEN_PLANT = ItemInit.SHARD_4.get();
            delay = 250;
        } else if (this_block == BlockInit.BIO_HARVESTER_5.get()) {
            CHOSEN_PLANT = ItemInit.SHARD_5.get();
            delay = 200;
        } else if (this_block == BlockInit.BIO_HARVESTER_6.get()) {
            CHOSEN_PLANT = ItemInit.SHARD_6.get();
            delay = 150;
        } else if (this_block == BlockInit.BIO_HARVESTER_7.get()) {
            CHOSEN_PLANT = ItemInit.SHARD_7.get();
            delay = 100;
        } else if (this_block == BlockInit.BIO_HARVESTER_8.get()) {
            CHOSEN_PLANT = ItemInit.SHARD_7.get();
            delay = 5;
        }
        if (ticks >= delay) {
            ticks = 0;
            if (world != null) {
                if (world.getBlockState(pos.offset(Direction.UP)).getBlock() == BlockInit.SPACE_RIPPER.get()) {
                    TileEntity out_down = world.getTileEntity(pos.offset(Direction.DOWN));
                    if (out_down != null) {
                        LazyOptional<IItemHandler> out_down_inv = out_down.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP);
                        if (out_down_inv.isPresent()) {
                            IItemHandler out_down_handler = out_down_inv.orElse(null);
                            if (rand.nextInt(150) != 1) {
                                ArrayList<Item> PLANTS_DYES = Tools.getLoadedPlantsAndDyes();
                                CHOSEN_PLANT = PLANTS_DYES.get(rand.nextInt(PLANTS_DYES.size()));
                            }
                            ItemHandlerHelper.insertItemStacked(out_down_handler, CHOSEN_PLANT.getDefaultInstance(), false);
                        }
                    }
                }
            }
        } else {
            ticks++;
        }
    }
}
