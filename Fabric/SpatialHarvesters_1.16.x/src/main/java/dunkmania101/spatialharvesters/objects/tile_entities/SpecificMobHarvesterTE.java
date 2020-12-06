package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import net.minecraft.block.Block;

public class SpecificMobHarvesterTE extends MobHarvesterTE {
    public SpecificMobHarvesterTE() {
        super(TileEntityInit.SPECIFIC_MOB_HARVESTER);
    }

    @Override
    public double getPrice(Block block) {
        return CommonConfig.price_specific_mob;
    }

    @Override
    public double getSpeed(Block block) {
        return CommonConfig.speed_specific_mob;
    }
}
