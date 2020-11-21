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
    public int getPrice(Block block) {
        int price = CommonConfig.BIO_1_PRICE.get();
        if (block == BlockInit.BIO_HARVESTER_1.get()) {
            price = CommonConfig.BIO_1_PRICE.get();
        } else if (block == BlockInit.BIO_HARVESTER_2.get()) {
            price = CommonConfig.BIO_2_PRICE.get();
        } else if (block == BlockInit.BIO_HARVESTER_3.get()) {
            price = CommonConfig.BIO_3_PRICE.get();
        } else if (block == BlockInit.BIO_HARVESTER_4.get()) {
            price = CommonConfig.BIO_4_PRICE.get();
        } else if (block == BlockInit.BIO_HARVESTER_5.get()) {
            price = CommonConfig.BIO_5_PRICE.get();
        } else if (block == BlockInit.BIO_HARVESTER_6.get()) {
            price = CommonConfig.BIO_6_PRICE.get();
        } else if (block == BlockInit.BIO_HARVESTER_7.get()) {
            price = CommonConfig.BIO_7_PRICE.get();
        } else if (block == BlockInit.BIO_HARVESTER_8.get()) {
            price = CommonConfig.BIO_8_PRICE.get();
        }
        return price;
    }

    @Override
    public int getSpeed(Block block) {
        int speed = CommonConfig.BIO_1_SPEED.get();
        if (block == BlockInit.BIO_HARVESTER_1.get()) {
            speed = CommonConfig.BIO_1_SPEED.get();
        } else if (block == BlockInit.BIO_HARVESTER_2.get()) {
            speed = CommonConfig.BIO_2_SPEED.get();
        } else if (block == BlockInit.BIO_HARVESTER_3.get()) {
            speed = CommonConfig.BIO_3_SPEED.get();
        } else if (block == BlockInit.BIO_HARVESTER_4.get()) {
            speed = CommonConfig.BIO_4_SPEED.get();
        } else if (block == BlockInit.BIO_HARVESTER_5.get()) {
            speed = CommonConfig.BIO_5_SPEED.get();
        } else if (block == BlockInit.BIO_HARVESTER_6.get()) {
            speed = CommonConfig.BIO_6_SPEED.get();
        } else if (block == BlockInit.BIO_HARVESTER_7.get()) {
            speed = CommonConfig.BIO_7_SPEED.get();
        } else if (block == BlockInit.BIO_HARVESTER_8.get()) {
            speed = CommonConfig.BIO_8_SPEED.get();
        }
        return speed;
    }

    @Override
    public Item getShard(Block block) {
        Item CHOSEN_SHARD = ItemInit.SHARD_1.get();
        if (block == BlockInit.BIO_HARVESTER_1.get()) {
            CHOSEN_SHARD = ItemInit.SHARD_1.get();
        } else if (block == BlockInit.BIO_HARVESTER_2.get()) {
            CHOSEN_SHARD = ItemInit.SHARD_2.get();
        } else if (block == BlockInit.BIO_HARVESTER_3.get()) {
            CHOSEN_SHARD = ItemInit.SHARD_3.get();
        } else if (block == BlockInit.BIO_HARVESTER_4.get()) {
            CHOSEN_SHARD = ItemInit.SHARD_4.get();
        } else if (block == BlockInit.BIO_HARVESTER_5.get()) {
            CHOSEN_SHARD = ItemInit.SHARD_5.get();
        } else if (block == BlockInit.BIO_HARVESTER_6.get()) {
            CHOSEN_SHARD = ItemInit.SHARD_6.get();
        } else if (block == BlockInit.BIO_HARVESTER_7.get()) {
            CHOSEN_SHARD = ItemInit.SHARD_7.get();
        } else if (block == BlockInit.BIO_HARVESTER_8.get()) {
            CHOSEN_SHARD = ItemInit.SHARD_7.get();
        }
        return CHOSEN_SHARD;
    }
}
