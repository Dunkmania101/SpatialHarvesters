package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.objects.tile_entities.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockEntityInit implements ModInitializer {
        public static final BlockEntityType<OreHarvesterTE> ORE_HARVESTER = registerBlockEntityType(
                        BlockEntityType.Builder.create(OreHarvesterTE::new, BlockInit.ORE_HARVESTER_1,
                                        BlockInit.ORE_HARVESTER_2, BlockInit.ORE_HARVESTER_3, BlockInit.ORE_HARVESTER_4,
                                        BlockInit.ORE_HARVESTER_5, BlockInit.ORE_HARVESTER_6, BlockInit.ORE_HARVESTER_7,
                                        BlockInit.ORE_HARVESTER_8).build(null),
                        "ore_harvester");

        public static final BlockEntityType<BioHarvesterTE> BIO_HARVESTER = registerBlockEntityType(
                        BlockEntityType.Builder.create(BioHarvesterTE::new, BlockInit.BIO_HARVESTER_1,
                                        BlockInit.BIO_HARVESTER_2, BlockInit.BIO_HARVESTER_3, BlockInit.BIO_HARVESTER_4,
                                        BlockInit.BIO_HARVESTER_5, BlockInit.BIO_HARVESTER_6, BlockInit.BIO_HARVESTER_7,
                                        BlockInit.BIO_HARVESTER_8).build(null),
                        "bio_harvester");

        public static final BlockEntityType<StoneHarvesterTE> STONE_HARVESTER = registerBlockEntityType(
                        BlockEntityType.Builder.create(StoneHarvesterTE::new, BlockInit.STONE_HARVESTER_1,
                                        BlockInit.STONE_HARVESTER_2, BlockInit.STONE_HARVESTER_3,
                                        BlockInit.STONE_HARVESTER_4, BlockInit.STONE_HARVESTER_5,
                                        BlockInit.STONE_HARVESTER_6, BlockInit.STONE_HARVESTER_7,
                                        BlockInit.STONE_HARVESTER_8).build(null),
                        "stone_harvester");

        public static final BlockEntityType<SoilHarvesterTE> SOIL_HARVESTER = registerBlockEntityType(
                        BlockEntityType.Builder.create(SoilHarvesterTE::new, BlockInit.SOIL_HARVESTER_1,
                                        BlockInit.SOIL_HARVESTER_2, BlockInit.SOIL_HARVESTER_3,
                                        BlockInit.SOIL_HARVESTER_4, BlockInit.SOIL_HARVESTER_5,
                                        BlockInit.SOIL_HARVESTER_6, BlockInit.SOIL_HARVESTER_7,
                                        BlockInit.SOIL_HARVESTER_8).build(null),
                        "soil_harvester");

        public static final BlockEntityType<LootHarvesterTE> LOOT_HARVESTER = registerBlockEntityType(
                BlockEntityType.Builder.create(LootHarvesterTE::new, BlockInit.LOOT_HARVESTER)
                        .build(null),
                "loot_harvester");

        public static final BlockEntityType<SpecificMobHarvesterTE> SPECIFIC_MOB_HARVESTER = registerBlockEntityType(
                        BlockEntityType.Builder.create(SpecificMobHarvesterTE::new, BlockInit.SPECIFIC_MOB_HARVESTER)
                                        .build(null),
                        "specific_mob_harvester");

        public static final BlockEntityType<DarkMobHarvesterTE> DARK_MOB_HARVESTER = registerBlockEntityType(
                        BlockEntityType.Builder.create(DarkMobHarvesterTE::new, BlockInit.DARK_MOB_HARVESTER)
                                        .build(null),
                        "dark_mob_harvester");

        public static final BlockEntityType<HeatGeneratorTE> HEAT_GENERATOR = registerBlockEntityType(
                        BlockEntityType.Builder.create(HeatGeneratorTE::new, BlockInit.HEAT_GENERATOR).build(null),
                        "heat_generator");

        public static final BlockEntityType<DarkMobHarvesterTE> DIMENSIONAL_APPLICATOR = registerBlockEntityType(
                        BlockEntityType.Builder.create(DarkMobHarvesterTE::new, BlockInit.DIMENSIONAL_APPLICATOR)
                                        .build(null),
                        "dimensional_applicator");

        public static <E extends BlockEntityType<?>> E registerBlockEntityType(E blockEntityType, String name) {
                Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(SpatialHarvesters.modid, name),
                                blockEntityType);
                return blockEntityType;
        }

        @Override
        public void onInitialize() {
        }
}
