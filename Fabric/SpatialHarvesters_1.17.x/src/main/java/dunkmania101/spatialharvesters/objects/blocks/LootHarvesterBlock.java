package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.blocks.base.ActivePreservedDataCustomHorizontalShapedBlock;
import dunkmania101.spatialharvesters.objects.tile_entities.LootHarvesterTE;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class LootHarvesterBlock extends ActivePreservedDataCustomHorizontalShapedBlock implements BlockEntityProvider {
    public LootHarvesterBlock(Settings settings) {
        super(settings, CustomValues.machineShape);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new LootHarvesterTE(pos, state);
    }
}
