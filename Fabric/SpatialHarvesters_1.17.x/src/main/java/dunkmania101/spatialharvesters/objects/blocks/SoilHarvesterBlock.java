package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.blocks.base.ActivePreservedDataCustomHorizontalShapedBlock;
import dunkmania101.spatialharvesters.objects.tile_entities.SoilHarvesterTE;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class SoilHarvesterBlock extends ActivePreservedDataCustomHorizontalShapedBlock implements BlockEntityProvider {
    public SoilHarvesterBlock(Settings settings) {
        super(settings, CustomValues.machineShape);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SoilHarvesterTE(pos, state);
    }
}
