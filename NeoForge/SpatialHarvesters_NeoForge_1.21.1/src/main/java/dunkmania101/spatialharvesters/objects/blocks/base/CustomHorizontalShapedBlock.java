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

public class CustomHorizontalShapedBlock extends Block {
    private static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private final VoxelShape NORTH_SHAPE;
    private final VoxelShape SOUTH_SHAPE;
    private final VoxelShape EAST_SHAPE;
    private final VoxelShape WEST_SHAPE;

    public CustomHorizontalShapedBlock(Properties properties, VoxelShape shape, Direction frontDirection) {
        super(properties);

        BlockState thisState = this.getStateDefinition().any();
        this.registerDefaultState(thisState.setValue(FACING, frontDirection));

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
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }
}
