package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.objects.tile_entities.BioHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.OreHarvesterTE;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, SpatialHarvesters.modid);

    public static final RegistryObject<TileEntityType<OreHarvesterTE>> ORE_HARVESTER = TILE_ENTITIES.register("ore_harvester",
            () -> TileEntityType.Builder.create(OreHarvesterTE::new,
                    BlockInit.ORE_HARVESTER_1.get(),
                    BlockInit.ORE_HARVESTER_2.get(),
                    BlockInit.ORE_HARVESTER_3.get(),
                    BlockInit.ORE_HARVESTER_4.get(),
                    BlockInit.ORE_HARVESTER_5.get(),
                    BlockInit.ORE_HARVESTER_6.get(),
                    BlockInit.ORE_HARVESTER_7.get(),
                    BlockInit.ORE_HARVESTER_8.get()
            ).build(null));

    public static final RegistryObject<TileEntityType<BioHarvesterTE>> BIO_HARVESTER = TILE_ENTITIES.register("bio_harvester",
            () -> TileEntityType.Builder.create(BioHarvesterTE::new,
                    BlockInit.BIO_HARVESTER_1.get(),
                    BlockInit.BIO_HARVESTER_2.get(),
                    BlockInit.BIO_HARVESTER_3.get(),
                    BlockInit.BIO_HARVESTER_4.get(),
                    BlockInit.BIO_HARVESTER_5.get(),
                    BlockInit.BIO_HARVESTER_6.get(),
                    BlockInit.BIO_HARVESTER_7.get(),
                    BlockInit.BIO_HARVESTER_8.get()
            ).build(null));
}
