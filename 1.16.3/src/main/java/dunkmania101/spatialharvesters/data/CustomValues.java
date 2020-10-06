package dunkmania101.spatialharvesters.data;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.stream.Stream;

public class CustomValues {
    public static final VoxelShape machineShape = Stream.of(
            Block.makeCuboidShape(3, 0, 3, 13, 13, 13),
            Block.makeCuboidShape(0, 0, 0, 3, 13, 3),
            Block.makeCuboidShape(2, 0, 3, 3, 13, 4),
            Block.makeCuboidShape(3, 0, 2, 4, 13, 3),
            Block.makeCuboidShape(2, 0, 12, 3, 13, 13),
            Block.makeCuboidShape(3, 0, 13, 4, 13, 14),
            Block.makeCuboidShape(0, 0, 13, 3, 13, 16),
            Block.makeCuboidShape(12, 0, 2, 13, 13, 3),
            Block.makeCuboidShape(13, 0, 3, 14, 13, 4),
            Block.makeCuboidShape(13, 0, 0, 16, 13, 3),
            Block.makeCuboidShape(12, 0, 13, 13, 13, 14),
            Block.makeCuboidShape(13, 0, 12, 14, 13, 13),
            Block.makeCuboidShape(13, 0, 13, 16, 13, 16),
            Block.makeCuboidShape(3, 0, 0, 4, 1, 2),
            Block.makeCuboidShape(3, 0, 14, 4, 1, 16),
            Block.makeCuboidShape(12, 0, 0, 13, 1, 2),
            Block.makeCuboidShape(10, 0, 1, 12, 1, 2),
            Block.makeCuboidShape(4, 0, 1, 6, 1, 2),
            Block.makeCuboidShape(4, 0, 14, 6, 1, 15),
            Block.makeCuboidShape(10, 0, 14, 12, 1, 15),
            Block.makeCuboidShape(14, 0, 10, 15, 1, 12),
            Block.makeCuboidShape(1, 0, 10, 2, 1, 12),
            Block.makeCuboidShape(14, 0, 4, 15, 1, 6),
            Block.makeCuboidShape(1, 0, 4, 2, 1, 6),
            Block.makeCuboidShape(12, 0, 14, 13, 1, 16),
            Block.makeCuboidShape(4, 0, 2, 12, 1, 3),
            Block.makeCuboidShape(4, 0, 13, 12, 1, 14),
            Block.makeCuboidShape(0, 0, 3, 2, 1, 4),
            Block.makeCuboidShape(14, 0, 3, 16, 1, 4),
            Block.makeCuboidShape(2, 0, 4, 3, 1, 12),
            Block.makeCuboidShape(13, 0, 4, 14, 1, 12),
            Block.makeCuboidShape(0, 0, 12, 2, 1, 13),
            Block.makeCuboidShape(14, 0, 12, 16, 1, 13),
            Block.makeCuboidShape(4, 3, 13, 12, 11, 14),
            Block.makeCuboidShape(4, 3, 2, 12, 11, 3),
            Block.makeCuboidShape(13, 3, 4, 14, 11, 12),
            Block.makeCuboidShape(2, 3, 4, 3, 11, 12),
            Block.makeCuboidShape(1, 13, 1, 15, 16, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public static final String stackTileNBTKey = SpatialHarvesters.modid + "_stackTileNBT";
    public static final String potionsNBTKey = SpatialHarvesters.modid + "potionsNBTKey";
    public static final String playerNBTKey = SpatialHarvesters.modid + "_DimensionalApplicatorEntity";
    public static final String removePlayerNBTKey = SpatialHarvesters.modid + "removePlayer";
    public static final String removeEntityNBTKey = SpatialHarvesters.modid + "_removeEntityNBT";
    public static final String entityNBTKey = SpatialHarvesters.modid + "_entityNBT";
    public static final String playerNameNBTKey = SpatialHarvesters.modid + "_playerNameNBT";
    public static final String weaponNBTKey = SpatialHarvesters.modid + "_weaponNBT";
}
