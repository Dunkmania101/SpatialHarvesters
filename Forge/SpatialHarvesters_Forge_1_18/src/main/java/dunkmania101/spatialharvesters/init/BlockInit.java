package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.objects.blocks.ActivatorBlock;
import dunkmania101.spatialharvesters.objects.blocks.BioHarvesterBlock;
import dunkmania101.spatialharvesters.objects.blocks.CasingBlock;
import dunkmania101.spatialharvesters.objects.blocks.ChunkLoaderBlock;
import dunkmania101.spatialharvesters.objects.blocks.DarkMobHarvesterBlock;
import dunkmania101.spatialharvesters.objects.blocks.DimensionalApplicatorBlock;
import dunkmania101.spatialharvesters.objects.blocks.HeatGeneratorBlock;
import dunkmania101.spatialharvesters.objects.blocks.LootHarvesterBlock;
import dunkmania101.spatialharvesters.objects.blocks.OreHarvesterBlock;
import dunkmania101.spatialharvesters.objects.blocks.SoilHarvesterBlock;
import dunkmania101.spatialharvesters.objects.blocks.SpaceRipperBlock;
import dunkmania101.spatialharvesters.objects.blocks.SpecificMobHarvesterBlock;
import dunkmania101.spatialharvesters.objects.blocks.StoneHarvesterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            SpatialHarvesters.modid);

    private static final float block_hardness = CommonConfig.BLOCK_HARDNESS.get().floatValue();
    private static final float block_resistance = CommonConfig.BLOCK_RESISTANCE.get().floatValue();

    public static final RegistryObject<ChunkLoaderBlock> CHUNK_LOADER = BLOCKS.register("chunk_loader",
            () -> new ChunkLoaderBlock(getMetalBlockProperties()));

    public static final RegistryObject<CasingBlock> CASING = BLOCKS.register("casing",
            () -> new CasingBlock(getMetalBlockProperties()));

    public static final RegistryObject<SpaceRipperBlock> SPACE_RIPPER = BLOCKS.register("space_ripper",
            () -> new SpaceRipperBlock(getMetalBlockProperties()));

    public static final RegistryObject<OreHarvesterBlock> ORE_HARVESTER_1 = BLOCKS.register("ore_harvester_1",
            () -> new OreHarvesterBlock(getMetalBlockProperties(), 1));

    public static final RegistryObject<OreHarvesterBlock> ORE_HARVESTER_2 = BLOCKS.register("ore_harvester_2",
            () -> new OreHarvesterBlock(getMetalBlockProperties(), 2));

    public static final RegistryObject<OreHarvesterBlock> ORE_HARVESTER_3 = BLOCKS.register("ore_harvester_3",
            () -> new OreHarvesterBlock(getMetalBlockProperties(), 3));

    public static final RegistryObject<OreHarvesterBlock> ORE_HARVESTER_4 = BLOCKS.register("ore_harvester_4",
            () -> new OreHarvesterBlock(getMetalBlockProperties(), 4));

    public static final RegistryObject<OreHarvesterBlock> ORE_HARVESTER_5 = BLOCKS.register("ore_harvester_5",
            () -> new OreHarvesterBlock(getMetalBlockProperties(), 5));

    public static final RegistryObject<OreHarvesterBlock> ORE_HARVESTER_6 = BLOCKS.register("ore_harvester_6",
            () -> new OreHarvesterBlock(getMetalBlockProperties(), 6));

    public static final RegistryObject<OreHarvesterBlock> ORE_HARVESTER_7 = BLOCKS.register("ore_harvester_7",
            () -> new OreHarvesterBlock(getMetalBlockProperties(), 7));

    public static final RegistryObject<OreHarvesterBlock> ORE_HARVESTER_8 = BLOCKS.register("ore_harvester_8",
            () -> new OreHarvesterBlock(getMetalBlockProperties(), 8));

    public static final RegistryObject<BioHarvesterBlock> BIO_HARVESTER_1 = BLOCKS.register("bio_harvester_1",
            () -> new BioHarvesterBlock(getMetalBlockProperties(), 1));

    public static final RegistryObject<BioHarvesterBlock> BIO_HARVESTER_2 = BLOCKS.register("bio_harvester_2",
            () -> new BioHarvesterBlock(getMetalBlockProperties(), 2));

    public static final RegistryObject<BioHarvesterBlock> BIO_HARVESTER_3 = BLOCKS.register("bio_harvester_3",
            () -> new BioHarvesterBlock(getMetalBlockProperties(), 3));

    public static final RegistryObject<BioHarvesterBlock> BIO_HARVESTER_4 = BLOCKS.register("bio_harvester_4",
            () -> new BioHarvesterBlock(getMetalBlockProperties(), 4));

    public static final RegistryObject<BioHarvesterBlock> BIO_HARVESTER_5 = BLOCKS.register("bio_harvester_5",
            () -> new BioHarvesterBlock(getMetalBlockProperties(), 5));

    public static final RegistryObject<BioHarvesterBlock> BIO_HARVESTER_6 = BLOCKS.register("bio_harvester_6",
            () -> new BioHarvesterBlock(getMetalBlockProperties(), 6));

    public static final RegistryObject<BioHarvesterBlock> BIO_HARVESTER_7 = BLOCKS.register("bio_harvester_7",
            () -> new BioHarvesterBlock(getMetalBlockProperties(), 7));

    public static final RegistryObject<BioHarvesterBlock> BIO_HARVESTER_8 = BLOCKS.register("bio_harvester_8",
            () -> new BioHarvesterBlock(getMetalBlockProperties(), 8));

    public static final RegistryObject<StoneHarvesterBlock> STONE_HARVESTER_1 = BLOCKS.register("stone_harvester_1",
            () -> new StoneHarvesterBlock(getMetalBlockProperties(), 1));

    public static final RegistryObject<StoneHarvesterBlock> STONE_HARVESTER_2 = BLOCKS.register("stone_harvester_2",
            () -> new StoneHarvesterBlock(getMetalBlockProperties(), 2));

    public static final RegistryObject<StoneHarvesterBlock> STONE_HARVESTER_3 = BLOCKS.register("stone_harvester_3",
            () -> new StoneHarvesterBlock(getMetalBlockProperties(), 3));

    public static final RegistryObject<StoneHarvesterBlock> STONE_HARVESTER_4 = BLOCKS.register("stone_harvester_4",
            () -> new StoneHarvesterBlock(getMetalBlockProperties(), 4));

    public static final RegistryObject<StoneHarvesterBlock> STONE_HARVESTER_5 = BLOCKS.register("stone_harvester_5",
            () -> new StoneHarvesterBlock(getMetalBlockProperties(), 5));

    public static final RegistryObject<StoneHarvesterBlock> STONE_HARVESTER_6 = BLOCKS.register("stone_harvester_6",
            () -> new StoneHarvesterBlock(getMetalBlockProperties(), 6));

    public static final RegistryObject<StoneHarvesterBlock> STONE_HARVESTER_7 = BLOCKS.register("stone_harvester_7",
            () -> new StoneHarvesterBlock(getMetalBlockProperties(), 7));

    public static final RegistryObject<StoneHarvesterBlock> STONE_HARVESTER_8 = BLOCKS.register("stone_harvester_8",
            () -> new StoneHarvesterBlock(getMetalBlockProperties(), 8));

    public static final RegistryObject<SoilHarvesterBlock> SOIL_HARVESTER_1 = BLOCKS.register("soil_harvester_1",
            () -> new SoilHarvesterBlock(getMetalBlockProperties(), 1));

    public static final RegistryObject<SoilHarvesterBlock> SOIL_HARVESTER_2 = BLOCKS.register("soil_harvester_2",
            () -> new SoilHarvesterBlock(getMetalBlockProperties(), 2));

    public static final RegistryObject<SoilHarvesterBlock> SOIL_HARVESTER_3 = BLOCKS.register("soil_harvester_3",
            () -> new SoilHarvesterBlock(getMetalBlockProperties(), 3));

    public static final RegistryObject<SoilHarvesterBlock> SOIL_HARVESTER_4 = BLOCKS.register("soil_harvester_4",
            () -> new SoilHarvesterBlock(getMetalBlockProperties(), 4));

    public static final RegistryObject<SoilHarvesterBlock> SOIL_HARVESTER_5 = BLOCKS.register("soil_harvester_5",
            () -> new SoilHarvesterBlock(getMetalBlockProperties(), 5));

    public static final RegistryObject<SoilHarvesterBlock> SOIL_HARVESTER_6 = BLOCKS.register("soil_harvester_6",
            () -> new SoilHarvesterBlock(getMetalBlockProperties(), 6));

    public static final RegistryObject<SoilHarvesterBlock> SOIL_HARVESTER_7 = BLOCKS.register("soil_harvester_7",
            () -> new SoilHarvesterBlock(getMetalBlockProperties(), 7));

    public static final RegistryObject<SoilHarvesterBlock> SOIL_HARVESTER_8 = BLOCKS.register("soil_harvester_8",
            () -> new SoilHarvesterBlock(getMetalBlockProperties(), 8));

    public static final RegistryObject<LootHarvesterBlock> LOOT_HARVESTER = BLOCKS.register("loot_harvester",
            () -> new LootHarvesterBlock(getMetalBlockProperties(), 8));

    public static final RegistryObject<DarkMobHarvesterBlock> DARK_MOB_HARVESTER = BLOCKS.register("dark_mob_harvester",
            () -> new DarkMobHarvesterBlock(getMetalBlockProperties(), 8));

    public static final RegistryObject<SpecificMobHarvesterBlock> SPECIFIC_MOB_HARVESTER = BLOCKS
            .register("specific_mob_harvester", () -> new SpecificMobHarvesterBlock(getMetalBlockProperties(), 8));

    public static final RegistryObject<HeatGeneratorBlock> HEAT_GENERATOR = BLOCKS.register("heat_generator",
            () -> new HeatGeneratorBlock(getMetalBlockProperties()));

    public static final RegistryObject<DimensionalApplicatorBlock> DIMENSIONAL_APPLICATOR = BLOCKS
            .register("dimensional_applicator", () -> new DimensionalApplicatorBlock(getMetalBlockProperties()));

    public static final RegistryObject<ActivatorBlock> REGENERATION_ACTIVATOR = BLOCKS
            .register("regeneration_activator", () -> new ActivatorBlock(getMetalBlockProperties()));

    public static final RegistryObject<ActivatorBlock> RESISTANCE_ACTIVATOR = BLOCKS.register("resistance_activator",
            () -> new ActivatorBlock(getMetalBlockProperties()));

    public static final RegistryObject<ActivatorBlock> ABSORPTION_ACTIVATOR = BLOCKS.register("absorption_activator",
            () -> new ActivatorBlock(getMetalBlockProperties()));

    public static final RegistryObject<ActivatorBlock> HASTE_ACTIVATOR = BLOCKS.register("haste_activator",
            () -> new ActivatorBlock(getMetalBlockProperties()));

    public static final RegistryObject<ActivatorBlock> SPEED_ACTIVATOR = BLOCKS.register("speed_activator",
            () -> new ActivatorBlock(getMetalBlockProperties()));

    public static final RegistryObject<ActivatorBlock> JUMP_BOOST_ACTIVATOR = BLOCKS.register("jump_boost_activator",
            () -> new ActivatorBlock(getMetalBlockProperties()));

    public static final RegistryObject<ActivatorBlock> INVISIBILITY_ACTIVATOR = BLOCKS
            .register("invisibility_activator", () -> new ActivatorBlock(getMetalBlockProperties()));

    public static final RegistryObject<ActivatorBlock> NIGHT_VISION_ACTIVATOR = BLOCKS
            .register("night_vision_activator", () -> new ActivatorBlock(getMetalBlockProperties()));

    public static final RegistryObject<ActivatorBlock> STRENGTH_ACTIVATOR = BLOCKS.register("strength_activator",
            () -> new ActivatorBlock(getMetalBlockProperties()));

    // Methods
    public static Block.Properties getMetalBlockProperties() {
        return Block.Properties
            .of(Material.METAL)
            .sound(SoundType.METAL)
            .strength(block_hardness, block_resistance)
            .requiresCorrectToolForDrops();
    }
}
