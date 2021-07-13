package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.blocks.base.ActivePreservedDataCustomHorizontalShapedBlock;
import dunkmania101.spatialharvesters.objects.tile_entities.BioHarvesterTE;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class BioHarvesterBlock extends ActivePreservedDataCustomHorizontalShapedBlock implements BlockEntityProvider {
    public BioHarvesterBlock(Settings settings) {
        super(settings, CustomValues.machineShape);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BioHarvesterTE(pos, state);
    }
}
