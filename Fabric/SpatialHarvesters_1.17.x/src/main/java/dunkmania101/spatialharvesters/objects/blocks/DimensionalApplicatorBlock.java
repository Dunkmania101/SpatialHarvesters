package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.blocks.base.ActivePreservedDataCustomHorizontalShapedBlock;
import dunkmania101.spatialharvesters.objects.tile_entities.DimensionalApplicatorTE;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class DimensionalApplicatorBlock extends ActivePreservedDataCustomHorizontalShapedBlock implements BlockEntityProvider {
    public DimensionalApplicatorBlock(Settings settings) {
        super(settings, CustomValues.machineShape);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DimensionalApplicatorTE(pos, state);
    }
}
