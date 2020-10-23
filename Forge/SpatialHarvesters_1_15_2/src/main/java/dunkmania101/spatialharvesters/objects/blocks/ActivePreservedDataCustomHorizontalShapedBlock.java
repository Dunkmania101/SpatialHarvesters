package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;

public class ActivePreservedDataCustomHorizontalShapedBlock extends PreservedDataCustomHorizontalShapedBlock {
    public static final BooleanProperty ACTIVE = CustomProperties.ACTIVE;

    public ActivePreservedDataCustomHorizontalShapedBlock(Properties properties, VoxelShape shape, Direction frontDirection) {
        super(properties, shape, frontDirection);

        BlockState thisState = this.getStateContainer().getBaseState();
        this.setDefaultState(thisState.with(ACTIVE, false));
    }

    public ActivePreservedDataCustomHorizontalShapedBlock(Properties properties, VoxelShape shape) {
        this(properties, shape, Direction.NORTH);
    }

    @Override
    protected void fillStateContainer(@Nonnull StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(ACTIVE);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        if (state.get(ACTIVE)) {
            return CommonConfig.MACHINE_LIGHT_LEVEL.get();
        }
        return 0;
    }
}
