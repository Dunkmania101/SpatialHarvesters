package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.objects.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SpatialHarvesters.modid);

    private static final float block_hardness = CommonConfig.BLOCK_HARDNESS.get().floatValue();
    private static final float block_resistance = CommonConfig.BLOCK_RESISTANCE.get().floatValue();

    public static final RegistryObject<ChunkLoaderBlock> CHUNK_LOADER = BLOCKS.register("chunk_loader",
            () -> new ChunkLoaderBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<CasingBlock> CASING = BLOCKS.register("casing",
            () -> new CasingBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<SpaceRipperBlock> SPACE_RIPPER = BLOCKS.register("space_ripper",
            () -> new SpaceRipperBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<OreHarvesterBlockPreservedData> ORE_HARVESTER_1 = BLOCKS.register("ore_harvester_1",
            () -> new OreHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<OreHarvesterBlockPreservedData> ORE_HARVESTER_2 = BLOCKS.register("ore_harvester_2",
            () -> new OreHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<OreHarvesterBlockPreservedData> ORE_HARVESTER_3 = BLOCKS.register("ore_harvester_3",
            () -> new OreHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<OreHarvesterBlockPreservedData> ORE_HARVESTER_4 = BLOCKS.register("ore_harvester_4",
            () -> new OreHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<OreHarvesterBlockPreservedData> ORE_HARVESTER_5 = BLOCKS.register("ore_harvester_5",
            () -> new OreHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<OreHarvesterBlockPreservedData> ORE_HARVESTER_6 = BLOCKS.register("ore_harvester_6",
            () -> new OreHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<OreHarvesterBlockPreservedData> ORE_HARVESTER_7 = BLOCKS.register("ore_harvester_7",
            () -> new OreHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<OreHarvesterBlockPreservedData> ORE_HARVESTER_8 = BLOCKS.register("ore_harvester_8",
            () -> new OreHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<BioHarvesterBlockPreservedData> BIO_HARVESTER_1 = BLOCKS.register("bio_harvester_1",
            () -> new BioHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<BioHarvesterBlockPreservedData> BIO_HARVESTER_2 = BLOCKS.register("bio_harvester_2",
            () -> new BioHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<BioHarvesterBlockPreservedData> BIO_HARVESTER_3 = BLOCKS.register("bio_harvester_3",
            () -> new BioHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<BioHarvesterBlockPreservedData> BIO_HARVESTER_4 = BLOCKS.register("bio_harvester_4",
            () -> new BioHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<BioHarvesterBlockPreservedData> BIO_HARVESTER_5 = BLOCKS.register("bio_harvester_5",
            () -> new BioHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<BioHarvesterBlockPreservedData> BIO_HARVESTER_6 = BLOCKS.register("bio_harvester_6",
            () -> new BioHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<BioHarvesterBlockPreservedData> BIO_HARVESTER_7 = BLOCKS.register("bio_harvester_7",
            () -> new BioHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<BioHarvesterBlockPreservedData> BIO_HARVESTER_8 = BLOCKS.register("bio_harvester_8",
            () -> new BioHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<StoneHarvesterBlockPreservedData> STONE_HARVESTER_1 = BLOCKS.register("stone_harvester_1",
            () -> new StoneHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<StoneHarvesterBlockPreservedData> STONE_HARVESTER_2 = BLOCKS.register("stone_harvester_2",
            () -> new StoneHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<StoneHarvesterBlockPreservedData> STONE_HARVESTER_3 = BLOCKS.register("stone_harvester_3",
            () -> new StoneHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<StoneHarvesterBlockPreservedData> STONE_HARVESTER_4 = BLOCKS.register("stone_harvester_4",
            () -> new StoneHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<StoneHarvesterBlockPreservedData> STONE_HARVESTER_5 = BLOCKS.register("stone_harvester_5",
            () -> new StoneHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<StoneHarvesterBlockPreservedData> STONE_HARVESTER_6 = BLOCKS.register("stone_harvester_6",
            () -> new StoneHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<StoneHarvesterBlockPreservedData> STONE_HARVESTER_7 = BLOCKS.register("stone_harvester_7",
            () -> new StoneHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<StoneHarvesterBlockPreservedData> STONE_HARVESTER_8 = BLOCKS.register("stone_harvester_8",
            () -> new StoneHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<SoilHarvesterBlockPreservedData> SOIL_HARVESTER_1 = BLOCKS.register("soil_harvester_1",
            () -> new SoilHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<SoilHarvesterBlockPreservedData> SOIL_HARVESTER_2 = BLOCKS.register("soil_harvester_2",
            () -> new SoilHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<SoilHarvesterBlockPreservedData> SOIL_HARVESTER_3 = BLOCKS.register("soil_harvester_3",
            () -> new SoilHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<SoilHarvesterBlockPreservedData> SOIL_HARVESTER_4 = BLOCKS.register("soil_harvester_4",
            () -> new SoilHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<SoilHarvesterBlockPreservedData> SOIL_HARVESTER_5 = BLOCKS.register("soil_harvester_5",
            () -> new SoilHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<SoilHarvesterBlockPreservedData> SOIL_HARVESTER_6 = BLOCKS.register("soil_harvester_6",
            () -> new SoilHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<SoilHarvesterBlockPreservedData> SOIL_HARVESTER_7 = BLOCKS.register("soil_harvester_7",
            () -> new SoilHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<SoilHarvesterBlockPreservedData> SOIL_HARVESTER_8 = BLOCKS.register("soil_harvester_8",
            () -> new SoilHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<DarkMobHarvesterBlockPreservedData> DARK_MOB_HARVESTER = BLOCKS.register("dark_mob_harvester",
            () -> new DarkMobHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<SpecificMobHarvesterBlockPreservedData> SPECIFIC_MOB_HARVESTER = BLOCKS.register("specific_mob_harvester",
            () -> new SpecificMobHarvesterBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<HeatGeneratorBlockPreservedData> HEAT_GENERATOR = BLOCKS.register("heat_generator",
            () -> new HeatGeneratorBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<DimensionalApplicatorBlockPreservedData> DIMENSIONAL_APPLICATOR = BLOCKS.register("dimensional_applicator",
            () -> new DimensionalApplicatorBlockPreservedData(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<ActivatorBlock> REGENERATION_ACTIVATOR = BLOCKS.register("regeneration_activator",
            () -> new ActivatorBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<ActivatorBlock> RESISTANCE_ACTIVATOR = BLOCKS.register("resistance_activator",
            () -> new ActivatorBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<ActivatorBlock> ABSORPTION_ACTIVATOR = BLOCKS.register("absorption_activator",
            () -> new ActivatorBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<ActivatorBlock> HASTE_ACTIVATOR = BLOCKS.register("haste_activator",
            () -> new ActivatorBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<ActivatorBlock> SPEED_ACTIVATOR = BLOCKS.register("speed_activator",
            () -> new ActivatorBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<ActivatorBlock> JUMP_BOOST_ACTIVATOR = BLOCKS.register("jump_boost_activator",
            () -> new ActivatorBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<ActivatorBlock> INVISIBILITY_ACTIVATOR = BLOCKS.register("invisibility_activator",
            () -> new ActivatorBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<ActivatorBlock> NIGHT_VISION_ACTIVATOR = BLOCKS.register("night_vision_activator",
            () -> new ActivatorBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));

    public static final RegistryObject<ActivatorBlock> STRENGTH_ACTIVATOR = BLOCKS.register("strength_activator",
            () -> new ActivatorBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(block_hardness, block_resistance)
                            .harvestTool(ToolType.PICKAXE)
            ));
}
