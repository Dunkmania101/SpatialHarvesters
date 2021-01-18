package dunkmania101.spatialharvesters.objects.tile_entities;

import java.util.ArrayList;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.init.BlockEntityInit;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class OreHarvesterTE extends SpatialHarvesterTE {
    public OreHarvesterTE() {
        super(BlockEntityInit.ORE_HARVESTER);
    }

    @Override
    public ArrayList<Item> getOutputs() {
        return Tools.getLoadedOres();
    }

    @Override
    public double getPrice(Block block) {
        double price = CommonConfig.price_ore_1;
        if (block == BlockInit.ORE_HARVESTER_2) {
            price = CommonConfig.price_ore_2;
        } else if (block == BlockInit.ORE_HARVESTER_3) {
            price = CommonConfig.price_ore_3;
        } else if (block == BlockInit.ORE_HARVESTER_4) {
            price = CommonConfig.price_ore_4;
        } else if (block == BlockInit.ORE_HARVESTER_5) {
            price = CommonConfig.price_ore_5;
        } else if (block == BlockInit.ORE_HARVESTER_6) {
            price = CommonConfig.price_ore_6;
        } else if (block == BlockInit.ORE_HARVESTER_7) {
            price = CommonConfig.price_ore_7;
        } else if (block == BlockInit.ORE_HARVESTER_8) {
            price = CommonConfig.price_ore_8;
        }
        return price;
    }

    @Override
    public double getSpeed(Block block) {
        double speed = CommonConfig.speed_ore_1;
        if (block == BlockInit.ORE_HARVESTER_2) {
            speed = CommonConfig.speed_ore_2;
        } else if (block == BlockInit.ORE_HARVESTER_3) {
            speed = CommonConfig.speed_ore_3;
        } else if (block == BlockInit.ORE_HARVESTER_4) {
            speed = CommonConfig.speed_ore_4;
        } else if (block == BlockInit.ORE_HARVESTER_5) {
            speed = CommonConfig.speed_ore_5;
        } else if (block == BlockInit.ORE_HARVESTER_6) {
            speed = CommonConfig.speed_ore_6;
        } else if (block == BlockInit.ORE_HARVESTER_7) {
            speed = CommonConfig.speed_ore_7;
        } else if (block == BlockInit.ORE_HARVESTER_8) {
            speed = CommonConfig.speed_ore_8;
        }
        return speed;
    }

    @Override
    public Item getShard(Block block) {
        Item CHOSEN_SHARD = ItemInit.SHARD_1;
        if (block == BlockInit.ORE_HARVESTER_2) {
            CHOSEN_SHARD = ItemInit.SHARD_2;
        } else if (block == BlockInit.ORE_HARVESTER_3) {
            CHOSEN_SHARD = ItemInit.SHARD_3;
        } else if (block == BlockInit.ORE_HARVESTER_4) {
            CHOSEN_SHARD = ItemInit.SHARD_4;
        } else if (block == BlockInit.ORE_HARVESTER_5) {
            CHOSEN_SHARD = ItemInit.SHARD_5;
        } else if (block == BlockInit.ORE_HARVESTER_6) {
            CHOSEN_SHARD = ItemInit.SHARD_6;
        } else if (block == BlockInit.ORE_HARVESTER_7) {
            CHOSEN_SHARD = ItemInit.SHARD_7;
        } else if (block == BlockInit.ORE_HARVESTER_8) {
            CHOSEN_SHARD = ItemInit.SHARD_7;
        }
        return CHOSEN_SHARD;
    }
}
