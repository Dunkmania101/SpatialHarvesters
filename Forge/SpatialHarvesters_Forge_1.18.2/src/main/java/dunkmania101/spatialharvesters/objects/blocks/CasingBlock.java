package dunkmania101.spatialharvesters.objects.blocks;

import java.util.stream.Stream;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CasingBlock extends Block {
    public CasingBlock(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE = Stream.of(
            Block.box(0, 0, 0, 3, 1, 16),
            Block.box(13, 0, 0, 16, 1, 16),
            Block.box(3, 0, 13, 13, 1, 16),
            Block.box(3, 0, 0, 13, 1, 3),
            Block.box(0, 1, 0, 1, 3, 16),
            Block.box(15, 1, 0, 16, 3, 16),
            Block.box(1, 1, 15, 15, 3, 16),
            Block.box(1, 1, 0, 15, 3, 1),
            Block.box(0, 3, 0, 1, 13, 3),
            Block.box(0, 3, 13, 1, 13, 16),
            Block.box(15, 3, 13, 16, 13, 16),
            Block.box(15, 3, 0, 16, 13, 3),
            Block.box(13, 3, 15, 15, 13, 16),
            Block.box(1, 3, 15, 3, 13, 16),
            Block.box(1, 3, 0, 3, 13, 1),
            Block.box(13, 3, 0, 15, 13, 1),
            Block.box(1, 13, 0, 15, 15, 1),
            Block.box(15, 13, 0, 16, 15, 16),
            Block.box(0, 13, 0, 1, 15, 16),
            Block.box(1, 13, 15, 15, 15, 16),
            Block.box(3, 15, 0, 13, 16, 3),
            Block.box(0, 15, 0, 3, 16, 16),
            Block.box(13, 15, 0, 16, 16, 16),
            Block.box(3, 15, 13, 13, 16, 16),
            Block.box(1, 1, 1, 2, 2, 15),
            Block.box(14, 1, 1, 15, 2, 15),
            Block.box(2, 1, 1, 14, 2, 2),
            Block.box(2, 1, 14, 14, 2, 15),
            Block.box(2, 14, 14, 14, 15, 15),
            Block.box(1, 14, 1, 2, 15, 15),
            Block.box(2, 14, 1, 14, 15, 2),
            Block.box(14, 14, 1, 15, 15, 15),
            Block.box(1, 2, 1, 2, 14, 2),
            Block.box(1, 2, 14, 2, 14, 15),
            Block.box(14, 2, 14, 15, 14, 15),
            Block.box(14, 2, 1, 15, 14, 2)
    ).reduce((v1, v2) -> Shapes.or(v1, v2)).get();

    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE;
    }
}
