package dunkmania101.spatialharvesters.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class SpaceRipperBlock extends Block {
    public SpaceRipperBlock(Properties properties) {
        super(properties);
    }

    private static final VoxelShape shape = Stream.of(
            Block.makeCuboidShape(5, 0, 5, 11, 1, 11),
            Block.makeCuboidShape(5, 10, 5, 11, 11, 11),
            Block.makeCuboidShape(7, 1, 7, 9, 3, 9),
            Block.makeCuboidShape(7, 8, 7, 9, 10, 9),
            Block.makeCuboidShape(4, 0, 4, 5, 11, 5),
            Block.makeCuboidShape(4, 0, 11, 5, 11, 12),
            Block.makeCuboidShape(11, 0, 11, 12, 11, 12),
            Block.makeCuboidShape(11, 0, 4, 12, 11, 5)
            ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape;
    }
}
