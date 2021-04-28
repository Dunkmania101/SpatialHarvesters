package dunkmania101.spatialharvesters.objects.blocks.base;

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

import javax.annotation.Nonnull;

public class CustomHorizontalShapedBlock extends Block {
    private static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
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
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(@Nonnull StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
    }
}
