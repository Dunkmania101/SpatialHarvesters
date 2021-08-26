package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.init.TileEntityInit;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BioHarvesterBlock extends HarvesterBlock {
    public BioHarvesterBlock(Properties properties, int tierIn) {
        super(properties, tierIn);
    }

    @Override
    public BlockEntity createBlockEntity(BlockState state, BlockGetter world) {
        return TileEntityInit.BIO_HARVESTER.get().create();
    }
}
