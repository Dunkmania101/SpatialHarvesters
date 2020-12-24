package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.ArrayList;

public class OreHarvesterTE extends SpatialHarvesterTE {
    public OreHarvesterTE() {
        super(TileEntityInit.ORE_HARVESTER.get(), Tools.getLoadedOres());
    }

    @Override
    public int getPrice(Block block) {
        int price = CommonConfig.ORE_1_PRICE.get();
        if (block == BlockInit.ORE_HARVESTER_2.get()) {
            price = CommonConfig.ORE_2_PRICE.get();
        } else if (block == BlockInit.ORE_HARVESTER_3.get()) {
            price = CommonConfig.ORE_3_PRICE.get();
        } else if (block == BlockInit.ORE_HARVESTER_4.get()) {
            price = CommonConfig.ORE_4_PRICE.get();
        } else if (block == BlockInit.ORE_HARVESTER_5.get()) {
            price = CommonConfig.ORE_5_PRICE.get();
        } else if (block == BlockInit.ORE_HARVESTER_6.get()) {
            price = CommonConfig.ORE_6_PRICE.get();
        } else if (block == BlockInit.ORE_HARVESTER_7.get()) {
            price = CommonConfig.ORE_7_PRICE.get();
        } else if (block == BlockInit.ORE_HARVESTER_8.get()) {
            price = CommonConfig.ORE_8_PRICE.get();
        }
        return price;
    }

    @Override
    public int getSpeed(Block block) {
        int speed = CommonConfig.ORE_1_SPEED.get();
        if (block == BlockInit.ORE_HARVESTER_2.get()) {
            speed = CommonConfig.ORE_2_SPEED.get();
        } else if (block == BlockInit.ORE_HARVESTER_3.get()) {
            speed = CommonConfig.ORE_3_SPEED.get();
        } else if (block == BlockInit.ORE_HARVESTER_4.get()) {
            speed = CommonConfig.ORE_4_SPEED.get();
        } else if (block == BlockInit.ORE_HARVESTER_5.get()) {
            speed = CommonConfig.ORE_5_SPEED.get();
        } else if (block == BlockInit.ORE_HARVESTER_6.get()) {
            speed = CommonConfig.ORE_6_SPEED.get();
        } else if (block == BlockInit.ORE_HARVESTER_7.get()) {
            speed = CommonConfig.ORE_7_SPEED.get();
        } else if (block == BlockInit.ORE_HARVESTER_8.get()) {
            speed = CommonConfig.ORE_8_SPEED.get();
        }
        return speed;
    }

    @Override
    public Item getShard(Block block) {
        Item CHOSEN_SHARD = ItemInit.SHARD_1.get();
       if (block == BlockInit.ORE_HARVESTER_2.get()) {
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

    @Override
    public ArrayList<ArrayList<String>> getMinTierItems() {
        return CommonConfig.MIN_TIER_ORES.get();
    }

    @Override
    public int getTier(Block block) {
        int tier = super.getTier(block);
        if (block == BlockInit.ORE_HARVESTER_2.get()) {
            tier = 2;
        } else if (block == BlockInit.ORE_HARVESTER_3.get()) {
            tier = 3;
        } else if (block == BlockInit.ORE_HARVESTER_4.get()) {
            tier = 4;
        } else if (block == BlockInit.ORE_HARVESTER_5.get()) {
            tier = 5;
        } else if (block == BlockInit.ORE_HARVESTER_6.get()) {
            tier = 6;
        } else if (block == BlockInit.ORE_HARVESTER_7.get()) {
            tier = 7;
        } else if (block == BlockInit.ORE_HARVESTER_8.get()) {
            tier = 8;
        }
        return tier;
    }
}
