package dunkmania101.spatialharvesters.objects.tile_entities;

import java.util.ArrayList;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.objects.tile_entities.base.SpatialHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SoilHarvesterTE extends SpatialHarvesterTE {
    public SoilHarvesterTE(BlockPos pos, BlockState state) {
        super(TileEntityInit.SOIL_HARVESTER.get(), pos, state);
    }

    @Override
    public int getPrice(Block block) {
        int tier = getTier(block);
        int price = CommonConfig.SOIL_1_PRICE.get();
        if (tier == 2) {
            price = CommonConfig.SOIL_2_PRICE.get();
        } else if (tier == 3) {
            price = CommonConfig.SOIL_3_PRICE.get();
        } else if (tier == 4) {
            price = CommonConfig.SOIL_4_PRICE.get();
        } else if (tier == 5) {
            price = CommonConfig.SOIL_5_PRICE.get();
        } else if (tier == 6) {
            price = CommonConfig.SOIL_6_PRICE.get();
        } else if (tier == 7) {
            price = CommonConfig.SOIL_7_PRICE.get();
        } else if (tier == 8) {
            price = CommonConfig.SOIL_8_PRICE.get();
        }
        return price;
    }

    @Override
    public int getSpeed(Block block) {
        int tier = getTier(block);
        int speed = CommonConfig.SOIL_1_SPEED.get();
        if (tier == 2) {
            speed = CommonConfig.SOIL_2_SPEED.get();
        } else if (tier == 3) {
            speed = CommonConfig.SOIL_3_SPEED.get();
        } else if (tier == 4) {
            speed = CommonConfig.SOIL_4_SPEED.get();
        } else if (tier == 5) {
            speed = CommonConfig.SOIL_5_SPEED.get();
        } else if (tier == 6) {
            speed = CommonConfig.SOIL_6_SPEED.get();
        } else if (tier == 7) {
            speed = CommonConfig.SOIL_7_SPEED.get();
        } else if (tier == 8) {
            speed = CommonConfig.SOIL_8_SPEED.get();
        }
        return speed;
    }

    @Override
    public ArrayList<Item> getOutputs() {
        return Tools.getLoadedSoils();
    }

    @Override
    public ArrayList<ArrayList<String>> getMinTierItems() {
        return CommonConfig.MIN_TIER_SOILS.get();
    }
}
