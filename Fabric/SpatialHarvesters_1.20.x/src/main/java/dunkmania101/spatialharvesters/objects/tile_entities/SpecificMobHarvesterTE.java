package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.BlockEntityInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class SpecificMobHarvesterTE extends MobHarvesterTE {
    public SpecificMobHarvesterTE(BlockPos pos, BlockState state) {
        super(BlockEntityInit.SPECIFIC_MOB_HARVESTER, pos, state);
    }

    @Override
    public long getPrice(Block block) {
        return CommonConfig.price_specific_mob;
    }

    @Override
    public int getSpeed(Block block) {
        return CommonConfig.speed_specific_mob;
    }

    @Override
    protected boolean isEnabled() {
        return CommonConfig.enable_specific_mob_harvester;
    }
}
