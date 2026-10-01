package dunkmania101.spatialharvesters.data;

import java.util.concurrent.CompletableFuture;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.init.BlockInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class CustomBlockTagsProvider extends BlockTagsProvider {
    public CustomBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
            ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SpatialHarvesters.modid, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        BlockInit.BLOCKS.getEntries().forEach((entry) -> {
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(entry.get());
        });
    }
}
