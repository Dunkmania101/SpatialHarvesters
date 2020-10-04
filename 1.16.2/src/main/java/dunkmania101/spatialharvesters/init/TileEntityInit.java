package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.objects.tile_entities.*;
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

    public static final RegistryObject<TileEntityType<StoneHarvesterTE>> STONE_HARVESTER = TILE_ENTITIES.register("stone_harvester",
            () -> TileEntityType.Builder.create(StoneHarvesterTE::new,
                    BlockInit.STONE_HARVESTER_1.get(),
                    BlockInit.STONE_HARVESTER_2.get(),
                    BlockInit.STONE_HARVESTER_3.get(),
                    BlockInit.STONE_HARVESTER_4.get(),
                    BlockInit.STONE_HARVESTER_5.get(),
                    BlockInit.STONE_HARVESTER_6.get(),
                    BlockInit.STONE_HARVESTER_7.get(),
                    BlockInit.STONE_HARVESTER_8.get()
            ).build(null));

    public static final RegistryObject<TileEntityType<SoilHarvesterTE>> SOIL_HARVESTER = TILE_ENTITIES.register("soil_harvester",
            () -> TileEntityType.Builder.create(SoilHarvesterTE::new,
                    BlockInit.SOIL_HARVESTER_1.get(),
                    BlockInit.SOIL_HARVESTER_2.get(),
                    BlockInit.SOIL_HARVESTER_3.get(),
                    BlockInit.SOIL_HARVESTER_4.get(),
                    BlockInit.SOIL_HARVESTER_5.get(),
                    BlockInit.SOIL_HARVESTER_6.get(),
                    BlockInit.SOIL_HARVESTER_7.get(),
                    BlockInit.SOIL_HARVESTER_8.get()
            ).build(null));

    public static final RegistryObject<TileEntityType<MobHarvesterTE>> MOB_HARVESTER = TILE_ENTITIES.register("mob_harvester",
            () -> TileEntityType.Builder.create(MobHarvesterTE::new,
                    BlockInit.MOB_HARVESTER.get()
            ).build(null));

    public static final RegistryObject<TileEntityType<HeatGeneratorTE>> HEAT_GENERATOR = TILE_ENTITIES.register("heat_generator",
            () -> TileEntityType.Builder.create(HeatGeneratorTE::new,
                    BlockInit.HEAT_GENERATOR.get()
            ).build(null));

    public static final RegistryObject<TileEntityType<DimensionalApplicatorTE>> DIMENSIONAL_APPLICATOR = TILE_ENTITIES.register("dimensional_applicator",
            () -> TileEntityType.Builder.create(DimensionalApplicatorTE::new,
                    BlockInit.DIMENSIONAL_APPLICATOR.get()
            ).build(null));
}
