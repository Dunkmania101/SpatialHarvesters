package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class OreHarvesterBlockPreservedData extends ActivePreservedDataCustomHorizontalShapedBlock {
    public OreHarvesterBlockPreservedData(Properties properties) {
        super(properties, CustomValues.machineShape);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityInit.ORE_HARVESTER.get().create();
    }
}
