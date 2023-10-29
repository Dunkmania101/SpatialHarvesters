package dunkmania101.spatialharvesters;

import dunkmania101.spatialharvesters.init.ItemGroupInit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dunkmania101.spatialharvesters.data.CommonConfig;
import net.fabricmc.api.ModInitializer;

public class SpatialHarvesters implements ModInitializer {
    public static final String modid = "spatialharvesters";
    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {
        new CommonConfig().load();
        ItemGroupInit.register();
    }
}
