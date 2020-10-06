package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class CustomShapedBlock extends Block {
    private final VoxelShape NORTH_SHAPE;
    private final VoxelShape SOUTH_SHAPE;
    private final VoxelShape EAST_SHAPE;
    private final VoxelShape WEST_SHAPE;
    private final VoxelShape UP_SHAPE;
    private final VoxelShape DOWN_SHAPE;

    public CustomShapedBlock(Properties properties, VoxelShape shape, Direction base_direction) {
        super(properties);

        BlockState this_state = this.getStateContainer().getBaseState();
        this.setDefaultState(this_state.with(FACING, base_direction));

        this.NORTH_SHAPE = Tools.getRotatedVoxelShape(shape, base_direction, Direction.NORTH);
        this.SOUTH_SHAPE = Tools.getRotatedVoxelShape(shape, base_direction, Direction.SOUTH);
        this.EAST_SHAPE = Tools.getRotatedVoxelShape(shape, base_direction, Direction.EAST);
        this.WEST_SHAPE = Tools.getRotatedVoxelShape(shape, base_direction, Direction.WEST);
        this.UP_SHAPE = Tools.getRotatedVoxelShape(shape, base_direction, Direction.UP);
        this.DOWN_SHAPE = Tools.getRotatedVoxelShape(shape, base_direction, Direction.DOWN);
    }

    public CustomShapedBlock(Properties properties, VoxelShape shape) {
        this(properties, shape, Direction.DOWN);
    }

    private static final DirectionProperty FACING = BlockStateProperties.FACING;
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch(state.get(FACING)) {
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
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
    }
}
