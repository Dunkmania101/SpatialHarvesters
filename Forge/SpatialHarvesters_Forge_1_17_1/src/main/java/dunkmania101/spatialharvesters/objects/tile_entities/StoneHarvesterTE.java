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

public class StoneHarvesterTE extends SpatialHarvesterTE {
    public StoneHarvesterTE(BlockPos pos, BlockState state) {
        super(TileEntityInit.STONE_HARVESTER.get(), pos, state);
    }

    @Override
    public int getPrice(Block block) {
        int tier = getTier(block);
        int price = CommonConfig.STONE_1_PRICE.get();
        if (tier == 2) {
            price = CommonConfig.STONE_2_PRICE.get();
        } else if (tier == 3) {
            price = CommonConfig.STONE_3_PRICE.get();
        } else if (tier == 4) {
            price = CommonConfig.STONE_4_PRICE.get();
        } else if (tier == 5) {
            price = CommonConfig.STONE_5_PRICE.get();
        } else if (tier == 6) {
            price = CommonConfig.STONE_6_PRICE.get();
        } else if (tier == 7) {
            price = CommonConfig.STONE_7_PRICE.get();
        } else if (tier == 8) {
            price = CommonConfig.STONE_8_PRICE.get();
        }
        return price;
    }

    @Override
    public int getSpeed(Block block) {
        int tier = getTier(block);
        int speed = CommonConfig.STONE_1_SPEED.get();
        if (tier == 2) {
            speed = CommonConfig.STONE_2_SPEED.get();
        } else if (tier == 3) {
            speed = CommonConfig.STONE_3_SPEED.get();
        } else if (tier == 4) {
            speed = CommonConfig.STONE_4_SPEED.get();
        } else if (tier == 5) {
            speed = CommonConfig.STONE_5_SPEED.get();
        } else if (tier == 6) {
            speed = CommonConfig.STONE_6_SPEED.get();
        } else if (tier == 7) {
            speed = CommonConfig.STONE_7_SPEED.get();
        } else if (tier == 8) {
            speed = CommonConfig.STONE_8_SPEED.get();
        }
        return speed;
    }

    @Override
    public ArrayList<Item> getOutputs() {
        return Tools.getLoadedStones();
    }

    @Override
    public ArrayList<ArrayList<String>> getMinTierItems() {
        return CommonConfig.MIN_TIER_STONES.get();
    }
}
