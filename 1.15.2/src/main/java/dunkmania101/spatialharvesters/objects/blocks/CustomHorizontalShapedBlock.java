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

public class CustomHorizontalShapedBlock extends Block {
    private final VoxelShape NORTH_SHAPE;
    private final VoxelShape SOUTH_SHAPE;
    private final VoxelShape EAST_SHAPE;
    private final VoxelShape WEST_SHAPE;

    public CustomHorizontalShapedBlock(Properties properties, VoxelShape shape, Direction frontDirection) {
        super(properties);

        BlockState thisState = this.getStateContainer().getBaseState();
        this.setDefaultState(thisState.with(FACING, frontDirection));

        this.NORTH_SHAPE = Tools.getRotatedVoxelShape(shape, frontDirection, Direction.NORTH);
        this.SOUTH_SHAPE = Tools.getRotatedVoxelShape(shape, frontDirection, Direction.SOUTH);
        this.EAST_SHAPE = Tools.getRotatedVoxelShape(shape, frontDirection, Direction.EAST);
        this.WEST_SHAPE = Tools.getRotatedVoxelShape(shape, frontDirection, Direction.WEST);
    }

    public CustomHorizontalShapedBlock(Properties properties, VoxelShape shape) {
        this(properties, shape, Direction.NORTH);
    }

    private static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            default:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
    }
}
