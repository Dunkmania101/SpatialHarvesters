package dunkmania101.spatialharvesters.objects.blocks;

import java.util.stream.Stream;

import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.objects.blocks.base.ActivePreservedDataCustomHorizontalShapedBlock;
import dunkmania101.spatialharvesters.objects.tile_entities.HeatGeneratorTE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;

public class HeatGeneratorBlock extends ActivePreservedDataCustomHorizontalShapedBlock implements EntityBlock {
    public HeatGeneratorBlock(Properties properties) {
        super(properties,
                Stream.of(
                        Block.box(0, 0, 0, 16, 1, 16),
                        Block.box(5, 1, 15, 6, 6, 16),
                        Block.box(10, 1, 15, 11, 6, 16),
                        Block.box(6, 5, 15, 10, 6, 16),
                        Block.box(5, 5, 14, 11, 6, 15),
                        Block.box(5, 4, 14, 11, 5, 15),
                        Block.box(1, 1, 1, 15, 4, 15),
                        Block.box(1, 9, 1, 15, 12, 9),
                        Block.box(3, 4, 2, 13, 9, 7),
                        Block.box(4, 4, 7, 7, 6, 12),
                        Block.box(9, 4, 7, 12, 6, 12),
                        Block.box(7, 1, 15, 9, 2, 16),
                        Block.box(7, 4, 15, 9, 5, 16),
                        Block.box(6, 1, 15, 7, 5, 16),
                        Block.box(9, 1, 15, 10, 5, 16),
                        Block.box(4, 5, 12, 6, 6, 13),
                        Block.box(4, 4, 13, 6, 6, 14),
                        Block.box(10, 5, 12, 12, 6, 13),
                        Block.box(10, 4, 13, 12, 6, 14),
                        Block.box(2, 4, 2, 3, 5, 7),
                        Block.box(13, 4, 2, 14, 5, 7),
                        Block.box(2, 8, 2, 3, 9, 7),
                        Block.box(13, 8, 2, 14, 9, 7),
                        Block.box(2, 6, 2, 3, 7, 7),
                        Block.box(13, 6, 2, 14, 7, 7),
                        Block.box(2, 8, 1, 14, 9, 2),
                        Block.box(2, 8, 7, 14, 9, 8),
                        Block.box(2, 6, 1, 14, 7, 2),
                        Block.box(2, 4, 1, 14, 5, 2),
                        Block.box(7, 2, 15, 9, 4, 16)
                ).reduce((v1, v2) -> Shapes.or(v1, v2)).get());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return TileEntityInit.HEAT_GENERATOR.get().create(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        }
        return (level1, blockPos, blockState, t) -> {
            if (t instanceof HeatGeneratorTE tile) {
                tile.tick();
            }
        };
    }
}
