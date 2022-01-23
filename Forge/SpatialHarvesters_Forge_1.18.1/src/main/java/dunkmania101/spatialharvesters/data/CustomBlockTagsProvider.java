package dunkmania101.spatialharvesters.data;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.init.BlockInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CustomBlockTagsProvider extends BlockTagsProvider {
    public CustomBlockTagsProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, SpatialHarvesters.modid, existingFileHelper);
    }

    @Override
    protected void addTags() {
        BlockInit.BLOCKS.getEntries().forEach((entry) -> {
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(entry.get());
        });
    }
}
