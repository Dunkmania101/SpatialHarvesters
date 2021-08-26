package dunkmania101.spatialharvesters.objects.blocks;

import java.util.stream.Stream;

import dunkmania101.spatialharvesters.objects.blocks.base.CustomShapedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;

public class ActivatorBlock extends CustomShapedBlock {
    public ActivatorBlock(Properties properties) {
        super(properties, Stream.of(
                Block.box(5, 1, 5, 11, 2, 11),
                Block.box(5, 15, 5, 11, 16, 11),
                Block.box(7, 2, 7, 9, 15, 9),
                Block.box(4, 1, 4, 5, 12, 6),
                Block.box(5, 1, 4, 6, 12, 5),
                Block.box(4, 1, 10, 5, 12, 12),
                Block.box(10, 1, 11, 11, 12, 12),
                Block.box(5, 1, 11, 6, 12, 12),
                Block.box(5, 2, 10, 6, 15, 11),
                Block.box(10, 2, 10, 11, 15, 11),
                Block.box(10, 2, 5, 11, 15, 6),
                Block.box(5, 2, 5, 6, 15, 6),
                Block.box(11, 1, 10, 12, 12, 12),
                Block.box(11, 1, 4, 12, 12, 6),
                Block.box(10, 1, 4, 11, 12, 5),
                Block.box(3, 0, 3, 13, 1, 13)
        ).reduce((v1, v2) -> Shapes.or(v1, v2)).get());
    }
}
