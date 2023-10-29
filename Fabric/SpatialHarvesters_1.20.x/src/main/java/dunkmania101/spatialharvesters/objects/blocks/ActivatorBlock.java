package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.objects.blocks.base.CustomShapedBlock;
import net.minecraft.block.Block;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.shape.VoxelShapes;

import java.util.stream.Stream;

public class ActivatorBlock extends CustomShapedBlock {
    public ActivatorBlock(Settings settings) {
        super(settings, Stream.of(
                Block.createCuboidShape(5, 1, 5, 11, 2, 11),
                Block.createCuboidShape(5, 15, 5, 11, 16, 11),
                Block.createCuboidShape(7, 2, 7, 9, 15, 9),
                Block.createCuboidShape(4, 1, 4, 5, 12, 6),
                Block.createCuboidShape(5, 1, 4, 6, 12, 5),
                Block.createCuboidShape(4, 1, 10, 5, 12, 12),
                Block.createCuboidShape(10, 1, 11, 11, 12, 12),
                Block.createCuboidShape(5, 1, 11, 6, 12, 12),
                Block.createCuboidShape(5, 2, 10, 6, 15, 11),
                Block.createCuboidShape(10, 2, 10, 11, 15, 11),
                Block.createCuboidShape(10, 2, 5, 11, 15, 6),
                Block.createCuboidShape(5, 2, 5, 6, 15, 6),
                Block.createCuboidShape(11, 1, 10, 12, 12, 12),
                Block.createCuboidShape(11, 1, 4, 12, 12, 6),
                Block.createCuboidShape(10, 1, 4, 11, 12, 5),
                Block.createCuboidShape(3, 0, 3, 13, 1, 13)
        ).reduce(VoxelShapes::union).get());
    }
}
