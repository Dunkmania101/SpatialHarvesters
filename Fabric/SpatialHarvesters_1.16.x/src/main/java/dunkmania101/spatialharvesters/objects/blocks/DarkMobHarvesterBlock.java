package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.blocks.base.ActivePreservedDataCustomHorizontalShapedBlock;
import dunkmania101.spatialharvesters.objects.tile_entities.DarkMobHarvesterTE;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class DarkMobHarvesterBlock extends ActivePreservedDataCustomHorizontalShapedBlock implements BlockEntityProvider {
    public DarkMobHarvesterBlock(Settings settings) {
        super(settings, CustomValues.machineShape);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockView world) {
        return new DarkMobHarvesterTE();
    }
}
