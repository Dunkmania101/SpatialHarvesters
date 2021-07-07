package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.BlockEntityInit;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.objects.tile_entities.base.SpatialHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.ArrayList;

public class StoneHarvesterTE extends SpatialHarvesterTE {
    public StoneHarvesterTE() {
        super(BlockEntityInit.STONE_HARVESTER);
    }

    @Override
    public ArrayList<Item> getOutputs() {
        return Tools.getLoadedStones();
    }

    @Override
    public double getPrice(Block block) {
        double price = CommonConfig.price_stone_1;
        if (block == BlockInit.STONE_HARVESTER_2) {
            price = CommonConfig.price_stone_2;
        } else if (block == BlockInit.STONE_HARVESTER_3) {
            price = CommonConfig.price_stone_3;
        } else if (block == BlockInit.STONE_HARVESTER_4) {
            price = CommonConfig.price_stone_4;
        } else if (block == BlockInit.STONE_HARVESTER_5) {
            price = CommonConfig.price_stone_5;
        } else if (block == BlockInit.STONE_HARVESTER_6) {
            price = CommonConfig.price_stone_6;
        } else if (block == BlockInit.STONE_HARVESTER_7) {
            price = CommonConfig.price_stone_7;
        } else if (block == BlockInit.STONE_HARVESTER_8) {
            price = CommonConfig.price_stone_8;
        }
        return price;
    }

    @Override
    public double getSpeed(Block block) {
        double speed = CommonConfig.speed_stone_1;
        if (block == BlockInit.STONE_HARVESTER_2) {
            speed = CommonConfig.speed_stone_2;
        } else if (block == BlockInit.STONE_HARVESTER_3) {
            speed = CommonConfig.speed_stone_3;
        } else if (block == BlockInit.STONE_HARVESTER_4) {
            speed = CommonConfig.speed_stone_4;
        } else if (block == BlockInit.STONE_HARVESTER_5) {
            speed = CommonConfig.speed_stone_5;
        } else if (block == BlockInit.STONE_HARVESTER_6) {
            speed = CommonConfig.speed_stone_6;
        } else if (block == BlockInit.STONE_HARVESTER_7) {
            speed = CommonConfig.speed_stone_7;
        } else if (block == BlockInit.STONE_HARVESTER_8) {
            speed = CommonConfig.speed_stone_8;
        }
        return speed;
    }

    @Override
    public Item getShard(Block block) {
        Item CHOSEN_SHARD = ItemInit.SHARD_1;
        if (block == BlockInit.STONE_HARVESTER_2) {
            CHOSEN_SHARD = ItemInit.SHARD_2;
        } else if (block == BlockInit.STONE_HARVESTER_3) {
            CHOSEN_SHARD = ItemInit.SHARD_3;
        } else if (block == BlockInit.STONE_HARVESTER_4) {
            CHOSEN_SHARD = ItemInit.SHARD_4;
        } else if (block == BlockInit.STONE_HARVESTER_5) {
            CHOSEN_SHARD = ItemInit.SHARD_5;
        } else if (block == BlockInit.STONE_HARVESTER_6) {
            CHOSEN_SHARD = ItemInit.SHARD_6;
        } else if (block == BlockInit.STONE_HARVESTER_7) {
            CHOSEN_SHARD = ItemInit.SHARD_7;
        } else if (block == BlockInit.STONE_HARVESTER_8) {
            CHOSEN_SHARD = ItemInit.SHARD_7;
        }
        return CHOSEN_SHARD;
    }

    @Override
    public ArrayList<ArrayList<String>> getMinTierItems() {
        return CommonConfig.min_tier_stones;
    }

    @Override
    public int getTier(Block block) {
        int tier = super.getTier(block);
        if (block == BlockInit.STONE_HARVESTER_2) {
            tier = 2;
        } else if (block == BlockInit.STONE_HARVESTER_3) {
            tier = 3;
        } else if (block == BlockInit.STONE_HARVESTER_4) {
            tier = 4;
        } else if (block == BlockInit.STONE_HARVESTER_5) {
            tier = 5;
        } else if (block == BlockInit.STONE_HARVESTER_6) {
            tier = 6;
        } else if (block == BlockInit.STONE_HARVESTER_7) {
            tier = 7;
        } else if (block == BlockInit.STONE_HARVESTER_8) {
            tier = 8;
        }
        return tier;
    }
}
