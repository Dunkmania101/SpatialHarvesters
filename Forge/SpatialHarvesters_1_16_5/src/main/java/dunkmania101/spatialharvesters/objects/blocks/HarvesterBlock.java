package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.blocks.base.ActivePreservedDataCustomHorizontalShapedBlock;
import net.minecraft.block.BlockState;

public class HarvesterBlock extends ActivePreservedDataCustomHorizontalShapedBlock {
    protected final int tier;

    public HarvesterBlock(Properties properties, int tierIn) {
        super(properties, CustomValues.machineShape);

        tier = tierIn;
    }

    public int getTier() {
        return this.tier;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
