package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.init.TileEntityInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class HeatGeneratorBlock extends ActivePreservedDataShapedBlock {
    public HeatGeneratorBlock(Properties properties) {
        super(properties,
                Stream.of(
                        Block.makeCuboidShape(0, 0, 0, 16, 1, 16),
                        Block.makeCuboidShape(5, 1, 15, 6, 6, 16),
                        Block.makeCuboidShape(10, 1, 15, 11, 6, 16),
                        Block.makeCuboidShape(6, 5, 15, 10, 6, 16),
                        Block.makeCuboidShape(5, 5, 14, 11, 6, 15),
                        Block.makeCuboidShape(5, 4, 14, 11, 5, 15),
                        Block.makeCuboidShape(1, 1, 1, 15, 4, 15),
                        Block.makeCuboidShape(1, 9, 1, 15, 12, 9),
                        Block.makeCuboidShape(3, 4, 2, 13, 9, 7),
                        Block.makeCuboidShape(4, 4, 7, 7, 6, 12),
                        Block.makeCuboidShape(9, 4, 7, 12, 6, 12),
                        Block.makeCuboidShape(7, 1, 15, 9, 2, 16),
                        Block.makeCuboidShape(7, 4, 15, 9, 5, 16),
                        Block.makeCuboidShape(6, 1, 15, 7, 5, 16),
                        Block.makeCuboidShape(9, 1, 15, 10, 5, 16),
                        Block.makeCuboidShape(4, 5, 12, 6, 6, 13),
                        Block.makeCuboidShape(4, 4, 13, 6, 6, 14),
                        Block.makeCuboidShape(10, 5, 12, 12, 6, 13),
                        Block.makeCuboidShape(10, 4, 13, 12, 6, 14),
                        Block.makeCuboidShape(2, 4, 2, 3, 5, 7),
                        Block.makeCuboidShape(13, 4, 2, 14, 5, 7),
                        Block.makeCuboidShape(2, 8, 2, 3, 9, 7),
                        Block.makeCuboidShape(13, 8, 2, 14, 9, 7),
                        Block.makeCuboidShape(2, 6, 2, 3, 7, 7),
                        Block.makeCuboidShape(13, 6, 2, 14, 7, 7),
                        Block.makeCuboidShape(2, 8, 1, 14, 9, 2),
                        Block.makeCuboidShape(2, 8, 7, 14, 9, 8),
                        Block.makeCuboidShape(2, 6, 1, 14, 7, 2),
                        Block.makeCuboidShape(2, 4, 1, 14, 5, 2),
                        Block.makeCuboidShape(7, 2, 15, 9, 4, 16)
                ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get(),
                true);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityInit.HEAT_GENERATOR.get().create();
    }
}
