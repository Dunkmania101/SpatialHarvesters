package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.objects.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SpatialHarvesters.modid);

    public static final RegistryObject<ChunkLoaderBlock> CHUNK_LOADER = BLOCKS.register("chunk_loader",
            () -> new ChunkLoaderBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.3f, 2f)
            ));

    public static final RegistryObject<Block> CASING = BLOCKS.register("casing",
            () -> new Block(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.3f, 2f)
            ));

    public static final RegistryObject<SpaceRipperBlock> SPACE_RIPPER = BLOCKS.register("space_ripper",
            () -> new SpaceRipperBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<OreHarvesterBlock> ORE_HARVESTER_1 = BLOCKS.register("ore_harvester_1",
            () -> new OreHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<OreHarvesterBlock> ORE_HARVESTER_2 = BLOCKS.register("ore_harvester_2",
            () -> new OreHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<OreHarvesterBlock> ORE_HARVESTER_3 = BLOCKS.register("ore_harvester_3",
            () -> new OreHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<OreHarvesterBlock> ORE_HARVESTER_4 = BLOCKS.register("ore_harvester_4",
            () -> new OreHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<OreHarvesterBlock> ORE_HARVESTER_5 = BLOCKS.register("ore_harvester_5",
            () -> new OreHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<OreHarvesterBlock> ORE_HARVESTER_6 = BLOCKS.register("ore_harvester_6",
            () -> new OreHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<OreHarvesterBlock> ORE_HARVESTER_7 = BLOCKS.register("ore_harvester_7",
            () -> new OreHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<OreHarvesterBlock> ORE_HARVESTER_8 = BLOCKS.register("ore_harvester_8",
            () -> new OreHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<BioHarvesterBlock> BIO_HARVESTER_1 = BLOCKS.register("bio_harvester_1",
            () -> new BioHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<BioHarvesterBlock> BIO_HARVESTER_2 = BLOCKS.register("bio_harvester_2",
            () -> new BioHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<BioHarvesterBlock> BIO_HARVESTER_3 = BLOCKS.register("bio_harvester_3",
            () -> new BioHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<BioHarvesterBlock> BIO_HARVESTER_4 = BLOCKS.register("bio_harvester_4",
            () -> new BioHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<BioHarvesterBlock> BIO_HARVESTER_5 = BLOCKS.register("bio_harvester_5",
            () -> new BioHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<BioHarvesterBlock> BIO_HARVESTER_6 = BLOCKS.register("bio_harvester_6",
            () -> new BioHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<BioHarvesterBlock> BIO_HARVESTER_7 = BLOCKS.register("bio_harvester_7",
            () -> new BioHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<BioHarvesterBlock> BIO_HARVESTER_8 = BLOCKS.register("bio_harvester_8",
            () -> new BioHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<StoneHarvesterBlock> STONE_HARVESTER_1 = BLOCKS.register("stone_harvester_1",
            () -> new StoneHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<StoneHarvesterBlock> STONE_HARVESTER_2 = BLOCKS.register("stone_harvester_2",
            () -> new StoneHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<StoneHarvesterBlock> STONE_HARVESTER_3 = BLOCKS.register("stone_harvester_3",
            () -> new StoneHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<StoneHarvesterBlock> STONE_HARVESTER_4 = BLOCKS.register("stone_harvester_4",
            () -> new StoneHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<StoneHarvesterBlock> STONE_HARVESTER_5 = BLOCKS.register("stone_harvester_5",
            () -> new StoneHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<StoneHarvesterBlock> STONE_HARVESTER_6 = BLOCKS.register("stone_harvester_6",
            () -> new StoneHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<StoneHarvesterBlock> STONE_HARVESTER_7 = BLOCKS.register("stone_harvester_7",
            () -> new StoneHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<StoneHarvesterBlock> STONE_HARVESTER_8 = BLOCKS.register("stone_harvester_8",
            () -> new StoneHarvesterBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));

    public static final RegistryObject<HeatGeneratorBlock> HEAT_GENERATOR = BLOCKS.register("heat_generator",
            () -> new HeatGeneratorBlock(
                    Block.Properties
                            .create(Material.IRON)
                            .sound(SoundType.METAL)
                            .hardnessAndResistance(0.7f, 2f)
            ));
}
