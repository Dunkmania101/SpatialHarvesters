package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.objects.blocks.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit {
    // Misc Machines
    public static final ChunkLoaderBlock CHUNK_LOADER = registerBlock(
            new ChunkLoaderBlock(FabricBlockSettings.of(Material.METAL).hardness(4.0f)),
            "chunk_loader"
    );

    // Harvesters
    public static final OreHarvesterBlock ORE_HARVESTER = registerBlock(
            new OreHarvesterBlock(FabricBlockSettings.of(Material.METAL).hardness(4.0f)),
            "ore_harvester"
    );

    public static final BioHarvesterBlock BIO_HARVESTER = registerBlock(
            new BioHarvesterBlock(FabricBlockSettings.of(Material.METAL).hardness(4.0f)),
            "bio_harvester"
    );

    public static final StoneHarvesterBlock STONE_HARVESTER = registerBlock(
            new StoneHarvesterBlock(FabricBlockSettings.of(Material.METAL).hardness(4.0f)),
            "stone_harvester"
    );

    public static final SoilHarvesterBlock SOIL_HARVESTER = registerBlock(
            new SoilHarvesterBlock(FabricBlockSettings.of(Material.METAL).hardness(4.0f)),
            "soil_harvester"
    );

    public static final SpecificMobHarvesterBlock SPECIFIC_MOB_HARVESTER = registerBlock(
            new SpecificMobHarvesterBlock(FabricBlockSettings.of(Material.METAL).hardness(4.0f)),
            "specific_mob_harvester"
    );

    public static final DarkMobHarvesterBlock DARK_MOB_HARVESTER = registerBlock(
            new DarkMobHarvesterBlock(FabricBlockSettings.of(Material.METAL).hardness(4.0f)),
            "dark_mob_harvester"
    );

    public static <B extends Block> B registerBlock(B block, String name) {
        Registry.register(Registry.BLOCK, new Identifier(SpatialHarvesters.modid, name), block);
        return block;
    }
}
