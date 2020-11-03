package dunkmania101.spatialharvesters.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;
import java.util.stream.Stream;

public class CasingBlock extends Block {
    public CasingBlock(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 3, 1, 16),
            Block.makeCuboidShape(13, 0, 0, 16, 1, 16),
            Block.makeCuboidShape(3, 0, 13, 13, 1, 16),
            Block.makeCuboidShape(3, 0, 0, 13, 1, 3),
            Block.makeCuboidShape(0, 1, 0, 1, 3, 16),
            Block.makeCuboidShape(15, 1, 0, 16, 3, 16),
            Block.makeCuboidShape(1, 1, 15, 15, 3, 16),
            Block.makeCuboidShape(1, 1, 0, 15, 3, 1),
            Block.makeCuboidShape(0, 3, 0, 1, 13, 3),
            Block.makeCuboidShape(0, 3, 13, 1, 13, 16),
            Block.makeCuboidShape(15, 3, 13, 16, 13, 16),
            Block.makeCuboidShape(15, 3, 0, 16, 13, 3),
            Block.makeCuboidShape(13, 3, 15, 15, 13, 16),
            Block.makeCuboidShape(1, 3, 15, 3, 13, 16),
            Block.makeCuboidShape(1, 3, 0, 3, 13, 1),
            Block.makeCuboidShape(13, 3, 0, 15, 13, 1),
            Block.makeCuboidShape(1, 13, 0, 15, 15, 1),
            Block.makeCuboidShape(15, 13, 0, 16, 15, 16),
            Block.makeCuboidShape(0, 13, 0, 1, 15, 16),
            Block.makeCuboidShape(1, 13, 15, 15, 15, 16),
            Block.makeCuboidShape(3, 15, 0, 13, 16, 3),
            Block.makeCuboidShape(0, 15, 0, 3, 16, 16),
            Block.makeCuboidShape(13, 15, 0, 16, 16, 16),
            Block.makeCuboidShape(3, 15, 13, 13, 16, 16),
            Block.makeCuboidShape(1, 1, 1, 2, 2, 15),
            Block.makeCuboidShape(14, 1, 1, 15, 2, 15),
            Block.makeCuboidShape(2, 1, 1, 14, 2, 2),
            Block.makeCuboidShape(2, 1, 14, 14, 2, 15),
            Block.makeCuboidShape(2, 14, 14, 14, 15, 15),
            Block.makeCuboidShape(1, 14, 1, 2, 15, 15),
            Block.makeCuboidShape(2, 14, 1, 14, 15, 2),
            Block.makeCuboidShape(14, 14, 1, 15, 15, 15),
            Block.makeCuboidShape(1, 2, 1, 2, 14, 2),
            Block.makeCuboidShape(1, 2, 14, 2, 14, 15),
            Block.makeCuboidShape(14, 2, 14, 15, 14, 15),
            Block.makeCuboidShape(14, 2, 1, 15, 14, 2)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        return SHAPE;
    }
}
