package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class BioHarvesterTE extends SpatialHarvesterTE {
    public BioHarvesterTE() {
        super(TileEntityInit.BIO_HARVESTER, Tools.getLoadedBios());
    }

    @Override
    public double getPrice(Block block) {
        double price = CommonConfig.price_bio_1;
        if (block == BlockInit.BIO_HARVESTER_2) {
            price = CommonConfig.price_bio_2;
        } else if (block == BlockInit.BIO_HARVESTER_3) {
            price = CommonConfig.price_bio_3;
        } else if (block == BlockInit.BIO_HARVESTER_4) {
            price = CommonConfig.price_bio_4;
        } else if (block == BlockInit.BIO_HARVESTER_5) {
            price = CommonConfig.price_bio_5;
        } else if (block == BlockInit.BIO_HARVESTER_6) {
            price = CommonConfig.price_bio_6;
        } else if (block == BlockInit.BIO_HARVESTER_7) {
            price = CommonConfig.price_bio_7;
        } else if (block == BlockInit.BIO_HARVESTER_8) {
            price = CommonConfig.price_bio_8;
        }
        return price;
    }

    @Override
    public double getSpeed(Block block) {
        double speed = CommonConfig.speed_bio_1;
        if (block == BlockInit.BIO_HARVESTER_2) {
            speed = CommonConfig.speed_bio_2;
        } else if (block == BlockInit.BIO_HARVESTER_3) {
            speed = CommonConfig.speed_bio_3;
        } else if (block == BlockInit.BIO_HARVESTER_4) {
            speed = CommonConfig.speed_bio_4;
        } else if (block == BlockInit.BIO_HARVESTER_5) {
            speed = CommonConfig.speed_bio_5;
        } else if (block == BlockInit.BIO_HARVESTER_6) {
            speed = CommonConfig.speed_bio_6;
        } else if (block == BlockInit.BIO_HARVESTER_7) {
            speed = CommonConfig.speed_bio_7;
        } else if (block == BlockInit.BIO_HARVESTER_8) {
            speed = CommonConfig.speed_bio_8;
        }
        return speed;
    }

    @Override
    public Item getShard(Block block) {
        Item CHOSEN_SHARD = ItemInit.SHARD_1;
        if (block == BlockInit.BIO_HARVESTER_1) {
            CHOSEN_SHARD = ItemInit.SHARD_1;
        } else if (block == BlockInit.BIO_HARVESTER_2) {
            CHOSEN_SHARD = ItemInit.SHARD_2;
        } else if (block == BlockInit.BIO_HARVESTER_3) {
            CHOSEN_SHARD = ItemInit.SHARD_3;
        } else if (block == BlockInit.BIO_HARVESTER_4) {
            CHOSEN_SHARD = ItemInit.SHARD_4;
        } else if (block == BlockInit.BIO_HARVESTER_5) {
            CHOSEN_SHARD = ItemInit.SHARD_5;
        } else if (block == BlockInit.BIO_HARVESTER_6) {
            CHOSEN_SHARD = ItemInit.SHARD_6;
        } else if (block == BlockInit.BIO_HARVESTER_7) {
            CHOSEN_SHARD = ItemInit.SHARD_7;
        } else if (block == BlockInit.BIO_HARVESTER_8) {
            CHOSEN_SHARD = ItemInit.SHARD_7;
        }
        return CHOSEN_SHARD;
    }
}
