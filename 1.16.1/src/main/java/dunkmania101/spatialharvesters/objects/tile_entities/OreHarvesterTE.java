package dunkmania101.spatialharvesters.objects.tile_entities;

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
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.ArrayList;
import java.util.Random;

public class OreHarvesterTE extends TileEntity implements ITickableTileEntity {
    public OreHarvesterTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public OreHarvesterTE() {
        this(TileEntityInit.ORE_HARVESTER.get());
    }

    Random rand = new Random();
    private int delay = 400;
    private int ticks = 0;
    @Override
    public void tick() {
        Item CHOSEN_ORE = ItemInit.CRYSTAL_1.get();
        Block this_block = getBlockState().getBlock();
        if (this_block == BlockInit.ORE_HARVESTER_1.get()) {
            CHOSEN_ORE = ItemInit.SHARD_1.get();
            delay = 400;
        } else if (this_block == BlockInit.ORE_HARVESTER_2.get()) {
            CHOSEN_ORE = ItemInit.SHARD_2.get();
            delay = 350;
        } else if (this_block == BlockInit.ORE_HARVESTER_3.get()) {
            CHOSEN_ORE = ItemInit.SHARD_3.get();
            delay = 300;
        } else if (this_block == BlockInit.ORE_HARVESTER_4.get()) {
            CHOSEN_ORE = ItemInit.SHARD_4.get();
            delay = 250;
        } else if (this_block == BlockInit.ORE_HARVESTER_5.get()) {
            CHOSEN_ORE = ItemInit.SHARD_5.get();
            delay = 200;
        } else if (this_block == BlockInit.ORE_HARVESTER_6.get()) {
            CHOSEN_ORE = ItemInit.SHARD_6.get();
            delay = 150;
        } else if (this_block == BlockInit.ORE_HARVESTER_7.get()) {
            CHOSEN_ORE = ItemInit.SHARD_7.get();
            delay = 100;
        } else if (this_block == BlockInit.ORE_HARVESTER_8.get()) {
            CHOSEN_ORE = ItemInit.SHARD_7.get();
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
                            if (rand.nextInt(75) != 1) {
                                ArrayList<Block> ORES = Tools.getLoadedOres();
                                CHOSEN_ORE = ORES.get(rand.nextInt(ORES.size())).asItem();
                            }
                            ItemHandlerHelper.insertItemStacked(out_down_handler, CHOSEN_ORE.getDefaultInstance(), false);
                        }
                    }
                }
            }
        } else {
            ticks++;
        }
    }
}
