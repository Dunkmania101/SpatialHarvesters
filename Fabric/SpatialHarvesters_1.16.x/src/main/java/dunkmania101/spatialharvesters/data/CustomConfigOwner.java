package dunkmania101.spatialharvesters.data;

import me.lortseam.completeconfig.ConfigBuilder;
import me.lortseam.completeconfig.ConfigHandler;
import me.lortseam.completeconfig.api.ConfigOwner;

public class CustomConfigOwner implements ConfigOwner {
    @Override
    public void onInitializeConfig(ConfigBuilder builder) {
        ConfigHandler handler = builder.add(new CommonConfig()).finish();
    }
}
