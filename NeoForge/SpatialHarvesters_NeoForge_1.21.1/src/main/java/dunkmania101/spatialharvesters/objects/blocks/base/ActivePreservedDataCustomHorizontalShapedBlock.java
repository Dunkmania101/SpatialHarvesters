package dunkmania101.spatialharvesters.objects.blocks.base;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;

public class ActivePreservedDataCustomHorizontalShapedBlock extends PreservedDataCustomHorizontalShapedBlock {
    public static final BooleanProperty ACTIVE = CustomProperties.ACTIVE;

    public ActivePreservedDataCustomHorizontalShapedBlock(Properties properties, VoxelShape shape, Direction frontDirection) {
        super(properties, shape, frontDirection);

        BlockState thisState = this.getStateDefinition().any();
        this.registerDefaultState(thisState.setValue(ACTIVE, false));
    }

    public ActivePreservedDataCustomHorizontalShapedBlock(Properties properties, VoxelShape shape) {
        this(properties, shape, Direction.NORTH);
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ACTIVE);
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
        if (state.getValue(ACTIVE)) {
            return CommonConfig.MACHINE_LIGHT_LEVEL.get();
        }
        return 0;
    }
}
