package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.objects.blocks.base.ActivePreservedDataCustomHorizontalShapedBlock;
import dunkmania101.spatialharvesters.objects.tile_entities.HeatGeneratorTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShapes;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class HeatGeneratorBlock extends ActivePreservedDataCustomHorizontalShapedBlock implements BlockEntityProvider {
    public HeatGeneratorBlock(Settings settings) {
        super(settings,
                Stream.of(
                        Block.createCuboidShape(0, 0, 0, 16, 1, 16),
                        Block.createCuboidShape(5, 1, 15, 6, 6, 16),
                        Block.createCuboidShape(10, 1, 15, 11, 6, 16),
                        Block.createCuboidShape(6, 5, 15, 10, 6, 16),
                        Block.createCuboidShape(5, 5, 14, 11, 6, 15),
                        Block.createCuboidShape(5, 4, 14, 11, 5, 15),
                        Block.createCuboidShape(1, 1, 1, 15, 4, 15),
                        Block.createCuboidShape(1, 9, 1, 15, 12, 9),
                        Block.createCuboidShape(3, 4, 2, 13, 9, 7),
                        Block.createCuboidShape(4, 4, 7, 7, 6, 12),
                        Block.createCuboidShape(9, 4, 7, 12, 6, 12),
                        Block.createCuboidShape(7, 1, 15, 9, 2, 16),
                        Block.createCuboidShape(7, 4, 15, 9, 5, 16),
                        Block.createCuboidShape(6, 1, 15, 7, 5, 16),
                        Block.createCuboidShape(9, 1, 15, 10, 5, 16),
                        Block.createCuboidShape(4, 5, 12, 6, 6, 13),
                        Block.createCuboidShape(4, 4, 13, 6, 6, 14),
                        Block.createCuboidShape(10, 5, 12, 12, 6, 13),
                        Block.createCuboidShape(10, 4, 13, 12, 6, 14),
                        Block.createCuboidShape(2, 4, 2, 3, 5, 7),
                        Block.createCuboidShape(13, 4, 2, 14, 5, 7),
                        Block.createCuboidShape(2, 8, 2, 3, 9, 7),
                        Block.createCuboidShape(13, 8, 2, 14, 9, 7),
                        Block.createCuboidShape(2, 6, 2, 3, 7, 7),
                        Block.createCuboidShape(13, 6, 2, 14, 7, 7),
                        Block.createCuboidShape(2, 8, 1, 14, 9, 2),
                        Block.createCuboidShape(2, 8, 7, 14, 9, 8),
                        Block.createCuboidShape(2, 6, 1, 14, 7, 2),
                        Block.createCuboidShape(2, 4, 1, 14, 5, 2),
                        Block.createCuboidShape(7, 2, 15, 9, 4, 16)
                ).reduce(VoxelShapes::union).get());
    }
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new HeatGeneratorTE(pos, state);
    }
}
