package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpatialHarvesters.modid);

    public static final RegistryObject<Item> BASE_POWDER = ITEMS.register("base_powder",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> ORE_CORE = ITEMS.register("ore_core",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> BIO_CORE = ITEMS.register("bio_core",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> SHARD_1 = ITEMS.register("shard_1",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> SHARD_2 = ITEMS.register("shard_2",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> SHARD_3 = ITEMS.register("shard_3",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> SHARD_4 = ITEMS.register("shard_4",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> SHARD_5 = ITEMS.register("shard_5",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> SHARD_6 = ITEMS.register("shard_6",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> SHARD_7 = ITEMS.register("shard_7",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> CRYSTAL_1 = ITEMS.register("crystal_1",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> CRYSTAL_2 = ITEMS.register("crystal_2",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> CRYSTAL_3 = ITEMS.register("crystal_3",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> CRYSTAL_4 = ITEMS.register("crystal_4",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> CRYSTAL_5 = ITEMS.register("crystal_5",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> CRYSTAL_6 = ITEMS.register("crystal_6",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> CRYSTAL_7 = ITEMS.register("crystal_7",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    //BlockItems
    public static final RegistryObject<BlockItem> CHUNK_LOADER = ITEMS.register("chunk_loader",
            () -> new BlockItem(
                    BlockInit.CHUNK_LOADER.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> CASING = ITEMS.register("casing",
            () -> new BlockItem(
                    BlockInit.CASING.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> SPACE_RIPPER = ITEMS.register("space_ripper",
            () -> new BlockItem(
                    BlockInit.SPACE_RIPPER.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> ORE_HARVESTER_1 = ITEMS.register("ore_harvester_1",
            () -> new BlockItem(
                    BlockInit.ORE_HARVESTER_1.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> ORE_HARVESTER_2 = ITEMS.register("ore_harvester_2",
            () -> new BlockItem(
                    BlockInit.ORE_HARVESTER_2.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> ORE_HARVESTER_3 = ITEMS.register("ore_harvester_3",
            () -> new BlockItem(
                    BlockInit.ORE_HARVESTER_3.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> ORE_HARVESTER_4 = ITEMS.register("ore_harvester_4",
            () -> new BlockItem(
                    BlockInit.ORE_HARVESTER_4.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> ORE_HARVESTER_5 = ITEMS.register("ore_harvester_5",
            () -> new BlockItem(
                    BlockInit.ORE_HARVESTER_5.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> ORE_HARVESTER_6 = ITEMS.register("ore_harvester_6",
            () -> new BlockItem(
                    BlockInit.ORE_HARVESTER_6.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> ORE_HARVESTER_7 = ITEMS.register("ore_harvester_7",
            () -> new BlockItem(
                    BlockInit.ORE_HARVESTER_7.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> ORE_HARVESTER_8 = ITEMS.register("ore_harvester_8",
            () -> new BlockItem(
                    BlockInit.ORE_HARVESTER_8.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> BIO_HARVESTER_1 = ITEMS.register("bio_harvester_1",
            () -> new BlockItem(
                    BlockInit.BIO_HARVESTER_1.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> BIO_HARVESTER_2 = ITEMS.register("bio_harvester_2",
            () -> new BlockItem(
                    BlockInit.BIO_HARVESTER_2.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> BIO_HARVESTER_3 = ITEMS.register("bio_harvester_3",
            () -> new BlockItem(
                    BlockInit.BIO_HARVESTER_3.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> BIO_HARVESTER_4 = ITEMS.register("bio_harvester_4",
            () -> new BlockItem(
                    BlockInit.BIO_HARVESTER_4.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> BIO_HARVESTER_5 = ITEMS.register("bio_harvester_5",
            () -> new BlockItem(
                    BlockInit.BIO_HARVESTER_5.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> BIO_HARVESTER_6 = ITEMS.register("bio_harvester_6",
            () -> new BlockItem(
                    BlockInit.BIO_HARVESTER_6.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> BIO_HARVESTER_7 = ITEMS.register("bio_harvester_7",
            () -> new BlockItem(
                    BlockInit.BIO_HARVESTER_7.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> BIO_HARVESTER_8 = ITEMS.register("bio_harvester_8",
            () -> new BlockItem(
                    BlockInit.BIO_HARVESTER_8.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));
    public static final RegistryObject<BlockItem> HEAT_GENERATOR = ITEMS.register("heat_generator",
            () -> new BlockItem(
                    BlockInit.HEAT_GENERATOR.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));
}
