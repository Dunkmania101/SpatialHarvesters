package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class StoneHarvesterBlock extends ActivePreservedDataCustomHorizontalShapedBlock {
    public StoneHarvesterBlock(Properties properties) {
        super(properties, CustomValues.machineShape);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityInit.STONE_HARVESTER.get().create();
    }
}
