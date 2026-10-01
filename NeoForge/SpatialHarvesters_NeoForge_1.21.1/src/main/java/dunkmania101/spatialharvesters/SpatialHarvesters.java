package dunkmania101.spatialharvesters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.init.CreativeTabInit;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(SpatialHarvesters.modid)
public class SpatialHarvesters {
    public static final String modid = "spatialharvesters";
    public static final Logger LOGGER = LogManager.getLogger();

    public SpatialHarvesters(IEventBus modBus, ModContainer container) {
        container.registerConfig(ModConfig.Type.STARTUP, CommonConfig.CONFIG, modid + "-common.toml");

        BlockInit.BLOCKS.register(modBus);
        TileEntityInit.TILE_ENTITIES.register(modBus);
        ItemInit.ITEMS.register(modBus);
        CreativeTabInit.TABS.register(modBus);
    }
}
