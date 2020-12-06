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

    // Misc Machines
    public static final ChunkLoaderBlock CHUNK_LOADER = registerBlock(
            new ChunkLoaderBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
            ),
            "chunk_loader"
    );

    // Harvesters
    public static final OreHarvesterBlock ORE_HARVESTER_1 = registerBlock(
            new OreHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "ore_harvester_1"
    );

    public static final OreHarvesterBlock ORE_HARVESTER_2 = registerBlock(
            new OreHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "ore_harvester_2"
    );

    public static final OreHarvesterBlock ORE_HARVESTER_3 = registerBlock(
            new OreHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "ore_harvester_3"
    );

    public static final OreHarvesterBlock ORE_HARVESTER_4 = registerBlock(
            new OreHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "ore_harvester_4"
    );

    public static final OreHarvesterBlock ORE_HARVESTER_5 = registerBlock(
            new OreHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "ore_harvester_5"
    );

    public static final OreHarvesterBlock ORE_HARVESTER_6 = registerBlock(
            new OreHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "ore_harvester_6"
    );

    public static final OreHarvesterBlock ORE_HARVESTER_7 = registerBlock(
            new OreHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "ore_harvester_7"
    );

    public static final OreHarvesterBlock ORE_HARVESTER_8 = registerBlock(
            new OreHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "ore_harvester_8"
    );

    public static final BioHarvesterBlock BIO_HARVESTER_1 = registerBlock(
            new BioHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "bio_harvester_1"
    );

    public static final BioHarvesterBlock BIO_HARVESTER_2 = registerBlock(
            new BioHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "bio_harvester_2"
    );

    public static final BioHarvesterBlock BIO_HARVESTER_3 = registerBlock(
            new BioHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "bio_harvester_3"
    );

    public static final BioHarvesterBlock BIO_HARVESTER_4 = registerBlock(
            new BioHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "bio_harvester_4"
    );

    public static final BioHarvesterBlock BIO_HARVESTER_5 = registerBlock(
            new BioHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "bio_harvester_5"
    );

    public static final BioHarvesterBlock BIO_HARVESTER_6 = registerBlock(
            new BioHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "bio_harvester_6"
    );

    public static final BioHarvesterBlock BIO_HARVESTER_7 = registerBlock(
            new BioHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "bio_harvester_7"
    );

    public static final BioHarvesterBlock BIO_HARVESTER_8 = registerBlock(
            new BioHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "bio_harvester_8"
    );

    public static final StoneHarvesterBlock STONE_HARVESTER_1 = registerBlock(
            new StoneHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "stone_harvester_1"
    );

    public static final StoneHarvesterBlock STONE_HARVESTER_2 = registerBlock(
            new StoneHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "stone_harvester_2"
    );

    public static final StoneHarvesterBlock STONE_HARVESTER_3 = registerBlock(
            new StoneHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "stone_harvester_3"
    );

    public static final StoneHarvesterBlock STONE_HARVESTER_4 = registerBlock(
            new StoneHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "stone_harvester_4"
    );

    public static final StoneHarvesterBlock STONE_HARVESTER_5 = registerBlock(
            new StoneHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "stone_harvester_5"
    );

    public static final StoneHarvesterBlock STONE_HARVESTER_6 = registerBlock(
            new StoneHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "stone_harvester_6"
    );

    public static final StoneHarvesterBlock STONE_HARVESTER_7 = registerBlock(
            new StoneHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "stone_harvester_7"
    );

    public static final StoneHarvesterBlock STONE_HARVESTER_8 = registerBlock(
            new StoneHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "stone_harvester_8"
    );

    public static final SoilHarvesterBlock SOIL_HARVESTER_1 = registerBlock(
            new SoilHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "soil_harvester_1"
    );

    public static final SoilHarvesterBlock SOIL_HARVESTER_2 = registerBlock(
            new SoilHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "soil_harvester_2"
    );

    public static final SoilHarvesterBlock SOIL_HARVESTER_3 = registerBlock(
            new SoilHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "soil_harvester_3"
    );

    public static final SoilHarvesterBlock SOIL_HARVESTER_4 = registerBlock(
            new SoilHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "soil_harvester_4"
    );

    public static final SoilHarvesterBlock SOIL_HARVESTER_5 = registerBlock(
            new SoilHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "soil_harvester_5"
    );

    public static final SoilHarvesterBlock SOIL_HARVESTER_6 = registerBlock(
            new SoilHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "soil_harvester_6"
    );

    public static final SoilHarvesterBlock SOIL_HARVESTER_7 = registerBlock(
            new SoilHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "soil_harvester_7"
    );

    public static final SoilHarvesterBlock SOIL_HARVESTER_8 = registerBlock(
            new SoilHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "soil_harvester_8"
    );

    public static final SpecificMobHarvesterBlock SPECIFIC_MOB_HARVESTER = registerBlock(
            new SpecificMobHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "specific_mob_harvester"
    );

    public static final DarkMobHarvesterBlock DARK_MOB_HARVESTER = registerBlock(
            new DarkMobHarvesterBlock(FabricBlockSettings.of(Material.METAL)
                    .sounds(BlockSoundGroup.METAL)
                    .hardness(blockHardness)
                    .resistance(blockResistance)
                    .breakByTool(FabricToolTags.PICKAXES)
            ),
            "dark_mob_harvester"
    );

    public static <B extends Block> B registerBlock(B block, String name) {
        Registry.register(Registry.BLOCK, new Identifier(SpatialHarvesters.modid, name), block);
        return block;
    }

    @Override
    public void onInitialize() {
    }
}
