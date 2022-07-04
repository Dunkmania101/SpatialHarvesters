package dunkmania101.spatialharvesters.events;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.data.CustomBlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = SpatialHarvesters.modid, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneratorEvents {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(true, new CustomBlockTagsProvider(generator, event.getExistingFileHelper()));
    }
}
