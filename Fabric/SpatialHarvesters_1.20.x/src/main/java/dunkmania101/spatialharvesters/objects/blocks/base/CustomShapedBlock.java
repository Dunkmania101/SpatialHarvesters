package dunkmania101.spatialharvesters.objects.blocks.base;

import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

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

    public CustomShapedBlock(Settings settings, VoxelShape shape) {
        this(settings, shape, Direction.DOWN);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
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
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(FACING, context.getSide().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }
}
