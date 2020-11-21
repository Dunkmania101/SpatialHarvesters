package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.DimensionalApplicatorTE;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class DimensionalApplicatorBlock extends ActivePreservedDataCustomHorizontalShapedBlock implements BlockEntityProvider {
    public DimensionalApplicatorBlock(Settings settings) {
        super(settings, CustomValues.machineShape);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockView world) {
        return new DimensionalApplicatorTE();
    }
}
