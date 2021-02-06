package dunkmania101.spatialharvesters;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.ItemInit;
import me.lortseam.completeconfig.data.Config;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpatialHarvesters implements ModInitializer {
    public static final String modid = "spatialharvesters";
    public static final Logger LOGGER = LogManager.getLogger();

    public static final ItemGroup SPATIAL_HARVESTERS_GROUP = FabricItemGroupBuilder
            .build(new Identifier(modid, modid), () -> new ItemStack(ItemInit.ORE_HARVESTER_1));

    @Override
    public void onInitialize() {
        Config.builder(modid).add(new CommonConfig()).build();
    }
}
