package dunkmania101.spatialharvesters.objects.blocks.base;

import javax.annotation.Nonnull;

import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CustomShapedBlock extends Block {
    private static final DirectionProperty FACING = BlockStateProperties.FACING;
    private final VoxelShape NORTH_SHAPE;
    private final VoxelShape SOUTH_SHAPE;
    private final VoxelShape EAST_SHAPE;
    private final VoxelShape WEST_SHAPE;
    private final VoxelShape UP_SHAPE;
    private final VoxelShape DOWN_SHAPE;

    public CustomShapedBlock(Properties properties, VoxelShape shape, Direction baseDirection) {
        super(properties);

        BlockState this_state = this.getStateDefinition().any();
        this.registerDefaultState(this_state.setValue(FACING, baseDirection));

        this.NORTH_SHAPE = Tools.getRotatedVoxelShape(shape, baseDirection, Direction.NORTH);
        this.SOUTH_SHAPE = Tools.getRotatedVoxelShape(shape, baseDirection, Direction.SOUTH);
        this.EAST_SHAPE = Tools.getRotatedVoxelShape(shape, baseDirection, Direction.EAST);
        this.WEST_SHAPE = Tools.getRotatedVoxelShape(shape, baseDirection, Direction.WEST);
        this.UP_SHAPE = Tools.getRotatedVoxelShape(shape, baseDirection, Direction.UP);
        this.DOWN_SHAPE = Tools.getRotatedVoxelShape(shape, baseDirection, Direction.DOWN);
    }

    public CustomShapedBlock(Properties properties, VoxelShape shape) {
        this(properties, shape, Direction.DOWN);
    }

    @Override
    @Nonnull
    public VoxelShape getShape(BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        switch (state.getValue(FACING)) {
            default:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
            case UP:
                return UP_SHAPE;
            case DOWN:
                return DOWN_SHAPE;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getClickedFace().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }
}
