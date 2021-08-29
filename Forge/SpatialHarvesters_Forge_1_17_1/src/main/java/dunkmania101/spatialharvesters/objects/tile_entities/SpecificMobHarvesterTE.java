package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SpecificMobHarvesterTE extends MobHarvesterTE {
    public SpecificMobHarvesterTE(BlockPos pos, BlockState state) {
        super(TileEntityInit.SPECIFIC_MOB_HARVESTER.get(), pos, state);
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
