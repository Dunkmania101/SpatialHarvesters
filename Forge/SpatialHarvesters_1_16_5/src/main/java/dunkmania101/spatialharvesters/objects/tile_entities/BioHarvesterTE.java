package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.objects.tile_entities.base.SpatialHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.ArrayList;

public class BioHarvesterTE extends SpatialHarvesterTE {
    public BioHarvesterTE() {
        super(TileEntityInit.BIO_HARVESTER.get());
    }

    @Override
    public int getPrice(Block block) {
        int tier = getTier(block);
        int price = CommonConfig.BIO_1_PRICE.get();
        if (tier == 2) {
            price = CommonConfig.BIO_2_PRICE.get();
        } else if (tier == 3) {
            price = CommonConfig.BIO_3_PRICE.get();
        } else if (tier == 4) {
            price = CommonConfig.BIO_4_PRICE.get();
        } else if (tier == 5) {
            price = CommonConfig.BIO_5_PRICE.get();
        } else if (tier == 6) {
            price = CommonConfig.BIO_6_PRICE.get();
        } else if (tier == 7) {
            price = CommonConfig.BIO_7_PRICE.get();
        } else if (tier == 8) {
            price = CommonConfig.BIO_8_PRICE.get();
        }
        return price;
    }

    @Override
    public int getSpeed(Block block) {
        int tier = getTier(block);
        int speed = CommonConfig.BIO_1_SPEED.get();
        if (tier == 2) {
            speed = CommonConfig.BIO_2_SPEED.get();
        } else if (tier == 3) {
            speed = CommonConfig.BIO_3_SPEED.get();
        } else if (tier == 4) {
            speed = CommonConfig.BIO_4_SPEED.get();
        } else if (tier == 5) {
            speed = CommonConfig.BIO_5_SPEED.get();
        } else if (tier == 6) {
            speed = CommonConfig.BIO_6_SPEED.get();
        } else if (tier == 7) {
            speed = CommonConfig.BIO_7_SPEED.get();
        } else if (tier == 8) {
            speed = CommonConfig.BIO_8_SPEED.get();
        }
        return speed;
    }

    @Override
    public ArrayList<Item> getOutputs() {
        return Tools.getLoadedBios();
    }

    @Override
    public ArrayList<ArrayList<String>> getMinTierItems() {
        return CommonConfig.MIN_TIER_BIOS.get();
    }
}
