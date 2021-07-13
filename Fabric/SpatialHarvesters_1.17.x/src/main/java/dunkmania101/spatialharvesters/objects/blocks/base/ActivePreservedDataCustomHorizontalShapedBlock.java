package dunkmania101.spatialharvesters.objects.blocks.base;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class ActivePreservedDataCustomHorizontalShapedBlock extends PreservedDataCustomHorizontalShapedBlock {
    public static final BooleanProperty ACTIVE = CustomProperties.ACTIVE;

    public ActivePreservedDataCustomHorizontalShapedBlock(Settings settings, VoxelShape shape, Direction frontDirection) {
        super(settings, shape, frontDirection);

        BlockState thisState = this.getStateManager().getDefaultStaTE(pos, state);
        this.setDefaultState(thisState.with(ACTIVE, false));
    }

    public ActivePreservedDataCustomHorizontalShapedBlock(Settings settings, VoxelShape shape) {
        this(settings, shape, Direction.NORTH);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(ACTIVE);
    }

    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        if (state.get(ACTIVE)) {
            return CommonConfig.machine_light_level;
        }
        return 0;
    }
}
