package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.LootHarvesterTE;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class LootHarvesterBlock extends ActivePreservedDataCustomHorizontalShapedBlock implements BlockEntityProvider {
    public LootHarvesterBlock(Settings settings) {
        super(settings, CustomValues.machineShape);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new LootHarvesterTE();
    }
}
