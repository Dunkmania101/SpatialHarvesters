package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.objects.tile_entities.base.SpatialHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.ArrayList;

public class OreHarvesterTE extends SpatialHarvesterTE {
    public OreHarvesterTE() {
        super(TileEntityInit.ORE_HARVESTER.get());
    }

    @Override
    public int getPrice(Block block) {
        int tier = getTier(block);
        int price = CommonConfig.ORE_1_PRICE.get();
        if (tier == 2) {
            price = CommonConfig.ORE_2_PRICE.get();
        } else if (tier == 3) {
            price = CommonConfig.ORE_3_PRICE.get();
        } else if (tier == 4) {
            price = CommonConfig.ORE_4_PRICE.get();
        } else if (tier == 5) {
            price = CommonConfig.ORE_5_PRICE.get();
        } else if (tier == 6) {
            price = CommonConfig.ORE_6_PRICE.get();
        } else if (tier == 7) {
            price = CommonConfig.ORE_7_PRICE.get();
        } else if (tier == 8) {
            price = CommonConfig.ORE_8_PRICE.get();
        }
        return price;
    }

    @Override
    public int getSpeed(Block block) {
        int tier = getTier(block);
        int speed = CommonConfig.ORE_1_SPEED.get();
        if (tier == 2) {
            speed = CommonConfig.ORE_2_SPEED.get();
        } else if (tier == 3) {
            speed = CommonConfig.ORE_3_SPEED.get();
        } else if (tier == 4) {
            speed = CommonConfig.ORE_4_SPEED.get();
        } else if (tier == 5) {
            speed = CommonConfig.ORE_5_SPEED.get();
        } else if (tier == 6) {
            speed = CommonConfig.ORE_6_SPEED.get();
        } else if (tier == 7) {
            speed = CommonConfig.ORE_7_SPEED.get();
        } else if (tier == 8) {
            speed = CommonConfig.ORE_8_SPEED.get();
        }
        return speed;
    }

    @Override
    public ArrayList<Item> getOutputs() {
        return Tools.getLoadedOres();
    }

    @Override
    public ArrayList<ArrayList<String>> getMinTierItems() {
        return CommonConfig.MIN_TIER_ORES.get();
    }
}
