package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.objects.blocks.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit implements ModInitializer {
        private static final float blockHardness = CommonConfig.block_hardness;
        private static final float blockResistance = CommonConfig.block_resistance;

        // Harvesters
        public static final OreHarvesterBlock ORE_HARVESTER_1 = registerBlock(
                        new OreHarvesterBlock(getMetalBlockSettings()), "ore_harvester_1");

        public static final OreHarvesterBlock ORE_HARVESTER_2 = registerBlock(
                        new OreHarvesterBlock(getMetalBlockSettings()), "ore_harvester_2");

        public static final OreHarvesterBlock ORE_HARVESTER_3 = registerBlock(
                        new OreHarvesterBlock(getMetalBlockSettings()), "ore_harvester_3");

        public static final OreHarvesterBlock ORE_HARVESTER_4 = registerBlock(
                        new OreHarvesterBlock(getMetalBlockSettings()), "ore_harvester_4");

        public static final OreHarvesterBlock ORE_HARVESTER_5 = registerBlock(
                        new OreHarvesterBlock(getMetalBlockSettings()), "ore_harvester_5");

        public static final OreHarvesterBlock ORE_HARVESTER_6 = registerBlock(
                        new OreHarvesterBlock(getMetalBlockSettings()), "ore_harvester_6");

        public static final OreHarvesterBlock ORE_HARVESTER_7 = registerBlock(
                        new OreHarvesterBlock(getMetalBlockSettings()), "ore_harvester_7");

        public static final OreHarvesterBlock ORE_HARVESTER_8 = registerBlock(
                        new OreHarvesterBlock(getMetalBlockSettings()), "ore_harvester_8");

        // Bio Harvesters
        public static final BioHarvesterBlock BIO_HARVESTER_1 = registerBlock(
                        new BioHarvesterBlock(getMetalBlockSettings()), "bio_harvester_1");

        public static final BioHarvesterBlock BIO_HARVESTER_2 = registerBlock(
                        new BioHarvesterBlock(getMetalBlockSettings()), "bio_harvester_2");

        public static final BioHarvesterBlock BIO_HARVESTER_3 = registerBlock(
                        new BioHarvesterBlock(getMetalBlockSettings()), "bio_harvester_3");

        public static final BioHarvesterBlock BIO_HARVESTER_4 = registerBlock(
                        new BioHarvesterBlock(getMetalBlockSettings()), "bio_harvester_4");

        public static final BioHarvesterBlock BIO_HARVESTER_5 = registerBlock(
                        new BioHarvesterBlock(getMetalBlockSettings()), "bio_harvester_5");

        public static final BioHarvesterBlock BIO_HARVESTER_6 = registerBlock(
                        new BioHarvesterBlock(getMetalBlockSettings()), "bio_harvester_6");

        public static final BioHarvesterBlock BIO_HARVESTER_7 = registerBlock(
                        new BioHarvesterBlock(getMetalBlockSettings()), "bio_harvester_7");

        public static final BioHarvesterBlock BIO_HARVESTER_8 = registerBlock(
                        new BioHarvesterBlock(getMetalBlockSettings()), "bio_harvester_8");

        // Stone Harvesters
        public static final StoneHarvesterBlock STONE_HARVESTER_1 = registerBlock(
                        new StoneHarvesterBlock(getMetalBlockSettings()), "stone_harvester_1");

        public static final StoneHarvesterBlock STONE_HARVESTER_2 = registerBlock(
                        new StoneHarvesterBlock(getMetalBlockSettings()), "stone_harvester_2");

        public static final StoneHarvesterBlock STONE_HARVESTER_3 = registerBlock(
                        new StoneHarvesterBlock(getMetalBlockSettings()), "stone_harvester_3");

        public static final StoneHarvesterBlock STONE_HARVESTER_4 = registerBlock(
                        new StoneHarvesterBlock(getMetalBlockSettings()), "stone_harvester_4");

        public static final StoneHarvesterBlock STONE_HARVESTER_5 = registerBlock(
                        new StoneHarvesterBlock(getMetalBlockSettings()), "stone_harvester_5");

        public static final StoneHarvesterBlock STONE_HARVESTER_6 = registerBlock(
                        new StoneHarvesterBlock(getMetalBlockSettings()), "stone_harvester_6");

        public static final StoneHarvesterBlock STONE_HARVESTER_7 = registerBlock(
                        new StoneHarvesterBlock(getMetalBlockSettings()), "stone_harvester_7");

        public static final StoneHarvesterBlock STONE_HARVESTER_8 = registerBlock(
                        new StoneHarvesterBlock(getMetalBlockSettings()), "stone_harvester_8");

        // Soil Harvesters
        public static final SoilHarvesterBlock SOIL_HARVESTER_1 = registerBlock(
                        new SoilHarvesterBlock(getMetalBlockSettings()), "soil_harvester_1");

        public static final SoilHarvesterBlock SOIL_HARVESTER_2 = registerBlock(
                        new SoilHarvesterBlock(getMetalBlockSettings()), "soil_harvester_2");

        public static final SoilHarvesterBlock SOIL_HARVESTER_3 = registerBlock(
                        new SoilHarvesterBlock(getMetalBlockSettings()), "soil_harvester_3");

        public static final SoilHarvesterBlock SOIL_HARVESTER_4 = registerBlock(
                        new SoilHarvesterBlock(getMetalBlockSettings()), "soil_harvester_4");

        public static final SoilHarvesterBlock SOIL_HARVESTER_5 = registerBlock(
                        new SoilHarvesterBlock(getMetalBlockSettings()), "soil_harvester_5");

        public static final SoilHarvesterBlock SOIL_HARVESTER_6 = registerBlock(
                        new SoilHarvesterBlock(getMetalBlockSettings()), "soil_harvester_6");

        public static final SoilHarvesterBlock SOIL_HARVESTER_7 = registerBlock(
                        new SoilHarvesterBlock(getMetalBlockSettings()), "soil_harvester_7");

        public static final SoilHarvesterBlock SOIL_HARVESTER_8 = registerBlock(
                        new SoilHarvesterBlock(getMetalBlockSettings()), "soil_harvester_8");

        // Mob Harvesters
        public static final SpecificMobHarvesterBlock SPECIFIC_MOB_HARVESTER = registerBlock(
                        new SpecificMobHarvesterBlock(getMetalBlockSettings()), "specific_mob_harvester");

        public static final DarkMobHarvesterBlock DARK_MOB_HARVESTER = registerBlock(
                        new DarkMobHarvesterBlock(getMetalBlockSettings()), "dark_mob_harvester");

        // Activators
        public static final ActivatorBlock REGENERATION_ACTIVATOR = registerBlock(
                new ActivatorBlock(getMetalBlockSettings()), "regeneration_activator");
        public static final ActivatorBlock RESISTANCE_ACTIVATOR = registerBlock(
                new ActivatorBlock(getMetalBlockSettings()), "resistance_activator");
        public static final ActivatorBlock ABSORPTION_ACTIVATOR = registerBlock(
                new ActivatorBlock(getMetalBlockSettings()), "absorption_activator");
        public static final ActivatorBlock HASTE_ACTIVATOR = registerBlock(
                new ActivatorBlock(getMetalBlockSettings()), "haste_activator");
        public static final ActivatorBlock SPEED_ACTIVATOR = registerBlock(
                new ActivatorBlock(getMetalBlockSettings()), "speed_activator");
        public static final ActivatorBlock JUMP_BOOST_ACTIVATOR = registerBlock(
                new ActivatorBlock(getMetalBlockSettings()), "jump_boost_activator");
        public static final ActivatorBlock INVISIBILITY_ACTIVATOR = registerBlock(
                new ActivatorBlock(getMetalBlockSettings()), "invisibility_activator");
        public static final ActivatorBlock NIGHT_VISION_ACTIVATOR = registerBlock(
                new ActivatorBlock(getMetalBlockSettings()), "night_vision_activator");
        public static final ActivatorBlock STRENGTH_ACTIVATOR = registerBlock(
                new ActivatorBlock(getMetalBlockSettings()), "strength_activator");

        // Misc
        public static final SpaceRipperBlock SPACE_RIPPER = registerBlock(
                        new SpaceRipperBlock(getMetalBlockSettings()), "space_ripper");

        public static final CasingBlock CASING = registerBlock(
                        new CasingBlock(getMetalBlockSettings()), "casing");

        public static final ChunkLoaderBlock CHUNK_LOADER = registerBlock(
                new ChunkLoaderBlock(getMetalBlockSettings()), "chunk_loader");

        public static final HeatGeneratorBlock HEAT_GENERATOR = registerBlock(
                        new HeatGeneratorBlock(getMetalBlockSettings()), "heat_generator");

        public static final DimensionalApplicatorBlock DIMENSIONAL_APPLICATOR = registerBlock(
                        new DimensionalApplicatorBlock(getMetalBlockSettings()), "dimensional_applicator");

        // Methods
        public static <B extends Block> B registerBlock(B block, String name) {
                Registry.register(Registry.BLOCK, new Identifier(SpatialHarvesters.modid, name), block);
                return block;
        }

        public static FabricBlockSettings getMetalBlockSettings() {
                return FabricBlockSettings.of(Material.METAL)
                                .sounds(BlockSoundGroup.METAL)
                                .strength(blockHardness, blockResistance)
                                .breakByTool(FabricToolTags.PICKAXES);
        }

        @Override
        public void onInitialize() {
        }
}
