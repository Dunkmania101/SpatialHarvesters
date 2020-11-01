package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

public class CustomShapedBlock extends Block {
    private static final DirectionProperty FACING = Properties.FACING;
    private final VoxelShape NORTH_SHAPE;
    private final VoxelShape SOUTH_SHAPE;
    private final VoxelShape EAST_SHAPE;
    private final VoxelShape WEST_SHAPE;
    private final VoxelShape UP_SHAPE;
    private final VoxelShape DOWN_SHAPE;

    public CustomShapedBlock(Settings settings, VoxelShape shape, Direction baseDirection) {
        super(settings);

        BlockState this_state = this.getStateManager().getDefaultState();
        this.setDefaultState(this_state.with(FACING, baseDirection));

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
    public VoxelShape getShape(BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        switch (state.get(FACING)) {
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
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getFace().getOpposite());
    }

    @Override
    protected void fillStateContainer(@Nonnull StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
    }
}
