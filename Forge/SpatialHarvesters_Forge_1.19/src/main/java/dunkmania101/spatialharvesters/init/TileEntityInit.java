package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.objects.tile_entities.BioHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.DarkMobHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.DimensionalApplicatorTE;
import dunkmania101.spatialharvesters.objects.tile_entities.HeatGeneratorTE;
import dunkmania101.spatialharvesters.objects.tile_entities.LootHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.OreHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.SoilHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.SpecificMobHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.StoneHarvesterTE;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TileEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, SpatialHarvesters.modid);

    public static final RegistryObject<BlockEntityType<OreHarvesterTE>> ORE_HARVESTER = TILE_ENTITIES.register("ore_harvester",
            () -> BlockEntityType.Builder.of(OreHarvesterTE::new,
                    BlockInit.ORE_HARVESTER_1.get(),
                    BlockInit.ORE_HARVESTER_2.get(),
                    BlockInit.ORE_HARVESTER_3.get(),
                    BlockInit.ORE_HARVESTER_4.get(),
                    BlockInit.ORE_HARVESTER_5.get(),
                    BlockInit.ORE_HARVESTER_6.get(),
                    BlockInit.ORE_HARVESTER_7.get(),
                    BlockInit.ORE_HARVESTER_8.get()
            ).build(null));

    public static final RegistryObject<BlockEntityType<BioHarvesterTE>> BIO_HARVESTER = TILE_ENTITIES.register("bio_harvester",
            () -> BlockEntityType.Builder.of(BioHarvesterTE::new,
                    BlockInit.BIO_HARVESTER_1.get(),
                    BlockInit.BIO_HARVESTER_2.get(),
                    BlockInit.BIO_HARVESTER_3.get(),
                    BlockInit.BIO_HARVESTER_4.get(),
                    BlockInit.BIO_HARVESTER_5.get(),
                    BlockInit.BIO_HARVESTER_6.get(),
                    BlockInit.BIO_HARVESTER_7.get(),
                    BlockInit.BIO_HARVESTER_8.get()
            ).build(null));

    public static final RegistryObject<BlockEntityType<StoneHarvesterTE>> STONE_HARVESTER = TILE_ENTITIES.register("stone_harvester",
            () -> BlockEntityType.Builder.of(StoneHarvesterTE::new,
                    BlockInit.STONE_HARVESTER_1.get(),
                    BlockInit.STONE_HARVESTER_2.get(),
                    BlockInit.STONE_HARVESTER_3.get(),
                    BlockInit.STONE_HARVESTER_4.get(),
                    BlockInit.STONE_HARVESTER_5.get(),
                    BlockInit.STONE_HARVESTER_6.get(),
                    BlockInit.STONE_HARVESTER_7.get(),
                    BlockInit.STONE_HARVESTER_8.get()
            ).build(null));

    public static final RegistryObject<BlockEntityType<SoilHarvesterTE>> SOIL_HARVESTER = TILE_ENTITIES.register("soil_harvester",
            () -> BlockEntityType.Builder.of(SoilHarvesterTE::new,
                    BlockInit.SOIL_HARVESTER_1.get(),
                    BlockInit.SOIL_HARVESTER_2.get(),
                    BlockInit.SOIL_HARVESTER_3.get(),
                    BlockInit.SOIL_HARVESTER_4.get(),
                    BlockInit.SOIL_HARVESTER_5.get(),
                    BlockInit.SOIL_HARVESTER_6.get(),
                    BlockInit.SOIL_HARVESTER_7.get(),
                    BlockInit.SOIL_HARVESTER_8.get()
            ).build(null));

    public static final RegistryObject<BlockEntityType<LootHarvesterTE>> LOOT_HARVESTER = TILE_ENTITIES.register("loot_harvester",
            () -> BlockEntityType.Builder.of(LootHarvesterTE::new,
                    BlockInit.LOOT_HARVESTER.get()
            ).build(null));

    public static final RegistryObject<BlockEntityType<DarkMobHarvesterTE>> DARK_MOB_HARVESTER = TILE_ENTITIES.register("dark_mob_harvester",
            () -> BlockEntityType.Builder.of(DarkMobHarvesterTE::new,
                    BlockInit.DARK_MOB_HARVESTER.get()
            ).build(null));

    public static final RegistryObject<BlockEntityType<SpecificMobHarvesterTE>> SPECIFIC_MOB_HARVESTER = TILE_ENTITIES.register("specific_mob_harvester",
            () -> BlockEntityType.Builder.of(SpecificMobHarvesterTE::new,
                    BlockInit.SPECIFIC_MOB_HARVESTER.get()
            ).build(null));

    public static final RegistryObject<BlockEntityType<HeatGeneratorTE>> HEAT_GENERATOR = TILE_ENTITIES.register("heat_generator",
            () -> BlockEntityType.Builder.of(HeatGeneratorTE::new,
                    BlockInit.HEAT_GENERATOR.get()
            ).build(null));

    public static final RegistryObject<BlockEntityType<DimensionalApplicatorTE>> DIMENSIONAL_APPLICATOR = TILE_ENTITIES.register("dimensional_applicator",
            () -> BlockEntityType.Builder.of(DimensionalApplicatorTE::new,
                    BlockInit.DIMENSIONAL_APPLICATOR.get()
            ).build(null));
}
