package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class ActiveCustomCustomShapedBlock extends PreservedDataCustomShapedBlock {
    public ActiveCustomCustomShapedBlock(Properties properties, VoxelShape shape, Direction base_direction) {
        super(properties, shape, base_direction);

        BlockState this_state = this.getStateContainer().getBaseState();
        this.setDefaultState(this_state.with(ACTIVE, false));
    }

    public ActiveCustomCustomShapedBlock(Properties properties, VoxelShape shape) {
        this(properties, shape, Direction.DOWN);
    }

    public static final BooleanProperty ACTIVE = CustomProperties.ACTIVE;

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(ACTIVE);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        if (state.get(ACTIVE)) {
            return 7;
        }
        return 0;
    }
}
