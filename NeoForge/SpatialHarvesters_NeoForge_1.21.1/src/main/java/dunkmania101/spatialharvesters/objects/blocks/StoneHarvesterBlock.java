package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.init.TileEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class StoneHarvesterBlock extends HarvesterBlock {
    public StoneHarvesterBlock(Properties properties, int tierIn) {
        super(properties, tierIn);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return TileEntityInit.STONE_HARVESTER.get().create(pos, state);
    }
}
