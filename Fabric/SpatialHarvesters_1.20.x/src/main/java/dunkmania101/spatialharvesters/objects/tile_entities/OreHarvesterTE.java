package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.BlockEntityInit;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.objects.tile_entities.base.SpatialHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class OreHarvesterTE extends SpatialHarvesterTE {
    public OreHarvesterTE(BlockPos pos, BlockState state) {
        super(BlockEntityInit.ORE_HARVESTER, pos, state);
    }

    @Override
    public ArrayList<Item> getOutputs() {
        return Tools.getLoadedOres();
    }

    @Override
    public long getPrice(Block block) {
        long price = CommonConfig.price_ore_1;
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
    public int getSpeed(Block block) {
        int speed = CommonConfig.speed_ore_1;
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

    @Override
    public ArrayList<ArrayList<String>> getMinTierItems() {
        return CommonConfig.min_tier_ores;
    }

    @Override
    public int getTier(Block block) {
        int tier = super.getTier(block);
        if (block == BlockInit.ORE_HARVESTER_2) {
            tier = 2;
        } else if (block == BlockInit.ORE_HARVESTER_3) {
            tier = 3;
        } else if (block == BlockInit.ORE_HARVESTER_4) {
            tier = 4;
        } else if (block == BlockInit.ORE_HARVESTER_5) {
            tier = 5;
        } else if (block == BlockInit.ORE_HARVESTER_6) {
            tier = 6;
        } else if (block == BlockInit.ORE_HARVESTER_7) {
            tier = 7;
        } else if (block == BlockInit.ORE_HARVESTER_8) {
            tier = 8;
        }
        return tier;
    }

    @Override
    protected boolean isEnabled() {
        return CommonConfig.enable_ore_harvesters;
    }
}
