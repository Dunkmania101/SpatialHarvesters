package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import net.minecraft.block.Block;

public class SpecificMobHarvesterTE extends MobHarvesterTE {
    public SpecificMobHarvesterTE() {
        super(TileEntityInit.SPECIFIC_MOB_HARVESTER.get());
    }

    @Override
    public int getPrice(Block block) {
        return CommonConfig.SPECIFIC_MOB_PRICE.get();
    }

    @Override
    public int getSpeed(Block block) {
        return CommonConfig.SPECIFIC_MOB_SPEED.get();
    }
}
