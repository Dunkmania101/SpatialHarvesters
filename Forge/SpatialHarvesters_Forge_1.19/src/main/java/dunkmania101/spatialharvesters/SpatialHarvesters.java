package dunkmania101.spatialharvesters;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(SpatialHarvesters.modid)
public class SpatialHarvesters {
    public static final String modid = "spatialharvesters";
    public static final Logger LOGGER = LogManager.getLogger();

    public SpatialHarvesters() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        modBus.addListener(this::setup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.CONFIG);
        CommonConfig.init(FMLPaths.CONFIGDIR.get().resolve(SpatialHarvesters.modid + "-common.toml"));

        BlockInit.BLOCKS.register(modBus);
        TileEntityInit.TILE_ENTITIES.register(modBus);
        ItemInit.ITEMS.register(modBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    public static class SPATIAL_HARVESTERS_GROUP extends CreativeModeTab {
        public static final CreativeModeTab instance = new SPATIAL_HARVESTERS_GROUP(CreativeModeTab.TABS.length, modid);

        private SPATIAL_HARVESTERS_GROUP(int index, String label) {
            super(index, label);
        }

        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemInit.ORE_HARVESTER_1.get());
        }
    }
}
