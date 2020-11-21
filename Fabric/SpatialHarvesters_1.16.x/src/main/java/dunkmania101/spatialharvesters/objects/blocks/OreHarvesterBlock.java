package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.OreHarvesterTE;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class OreHarvesterBlock extends ActivePreservedDataCustomHorizontalShapedBlock implements BlockEntityProvider {
    public OreHarvesterBlock(Settings settings) {
        super(settings, CustomValues.machineShape);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new OreHarvesterTE();
    }
}
