package dunkmania101.spatialharvesters;

import me.lortseam.completeconfig.CompleteConfig;
import net.fabricmc.api.ModInitializer;

public class SpatialHarvesters implements ModInitializer {
	public static final String modid = "spatialharvesters";

	@Override
	public void onInitialize() {
		CompleteConfig.register(modid);
	}
}
