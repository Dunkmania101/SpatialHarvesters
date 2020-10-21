package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.objects.blocks.block_items.MachineBlockItem;
import dunkmania101.spatialharvesters.objects.items.EffectKeyItem;
import dunkmania101.spatialharvesters.objects.items.MobKeyItem;
import dunkmania101.spatialharvesters.objects.items.PlayerKeyItem;
import dunkmania101.spatialharvesters.objects.items.ResourceDisablerKeyItem;
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

    public static final RegistryObject<PlayerKeyItem> PLAYER_KEY = ITEMS.register("player_key",
            () -> new PlayerKeyItem(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
                            .maxStackSize(1)
            ));

    public static final RegistryObject<EffectKeyItem> EFFECT_KEY = ITEMS.register("effect_key",
            () -> new EffectKeyItem(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
                            .maxStackSize(1)
            ));

    public static final RegistryObject<MobKeyItem> MOB_KEY = ITEMS.register("mob_key",
            () -> new MobKeyItem(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
                            .maxStackSize(1)
            ));

    public static final RegistryObject<ResourceDisablerKeyItem> RESOURCE_DISABLER_KEY = ITEMS.register("resource_disabler_key",
            () -> new ResourceDisablerKeyItem(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
                            .maxStackSize(1)
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

    public static final RegistryObject<Item> STONE_CORE = ITEMS.register("stone_core",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> SOIL_CORE = ITEMS.register("soil_core",
            () -> new Item(
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<Item> MOB_CORE = ITEMS.register("mob_core",
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
    public static final RegistryObject<MachineBlockItem> ORE_HARVESTER_1 = ITEMS.register("ore_harvester_1",
            () -> new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_1.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> ORE_HARVESTER_2 = ITEMS.register("ore_harvester_2",
            () -> new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_2.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> ORE_HARVESTER_3 = ITEMS.register("ore_harvester_3",
            () -> new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_3.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> ORE_HARVESTER_4 = ITEMS.register("ore_harvester_4",
            () -> new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_4.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> ORE_HARVESTER_5 = ITEMS.register("ore_harvester_5",
            () -> new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_5.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> ORE_HARVESTER_6 = ITEMS.register("ore_harvester_6",
            () -> new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_6.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> ORE_HARVESTER_7 = ITEMS.register("ore_harvester_7",
            () -> new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_7.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> ORE_HARVESTER_8 = ITEMS.register("ore_harvester_8",
            () -> new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_8.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> BIO_HARVESTER_1 = ITEMS.register("bio_harvester_1",
            () -> new MachineBlockItem(
                    BlockInit.BIO_HARVESTER_1.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> BIO_HARVESTER_2 = ITEMS.register("bio_harvester_2",
            () -> new MachineBlockItem(
                    BlockInit.BIO_HARVESTER_2.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> BIO_HARVESTER_3 = ITEMS.register("bio_harvester_3",
            () -> new MachineBlockItem(
                    BlockInit.BIO_HARVESTER_3.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> BIO_HARVESTER_4 = ITEMS.register("bio_harvester_4",
            () -> new MachineBlockItem(
                    BlockInit.BIO_HARVESTER_4.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> BIO_HARVESTER_5 = ITEMS.register("bio_harvester_5",
            () -> new MachineBlockItem(
                    BlockInit.BIO_HARVESTER_5.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> BIO_HARVESTER_6 = ITEMS.register("bio_harvester_6",
            () -> new MachineBlockItem(
                    BlockInit.BIO_HARVESTER_6.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> BIO_HARVESTER_7 = ITEMS.register("bio_harvester_7",
            () -> new MachineBlockItem(
                    BlockInit.BIO_HARVESTER_7.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> BIO_HARVESTER_8 = ITEMS.register("bio_harvester_8",
            () -> new MachineBlockItem(
                    BlockInit.BIO_HARVESTER_8.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> STONE_HARVESTER_1 = ITEMS.register("stone_harvester_1",
            () -> new MachineBlockItem(
                    BlockInit.STONE_HARVESTER_1.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> STONE_HARVESTER_2 = ITEMS.register("stone_harvester_2",
            () -> new MachineBlockItem(
                    BlockInit.STONE_HARVESTER_2.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> STONE_HARVESTER_3 = ITEMS.register("stone_harvester_3",
            () -> new MachineBlockItem(
                    BlockInit.STONE_HARVESTER_3.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> STONE_HARVESTER_4 = ITEMS.register("stone_harvester_4",
            () -> new MachineBlockItem(
                    BlockInit.STONE_HARVESTER_4.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> STONE_HARVESTER_5 = ITEMS.register("stone_harvester_5",
            () -> new MachineBlockItem(
                    BlockInit.STONE_HARVESTER_5.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> STONE_HARVESTER_6 = ITEMS.register("stone_harvester_6",
            () -> new MachineBlockItem(
                    BlockInit.STONE_HARVESTER_6.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> STONE_HARVESTER_7 = ITEMS.register("stone_harvester_7",
            () -> new MachineBlockItem(
                    BlockInit.STONE_HARVESTER_7.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> STONE_HARVESTER_8 = ITEMS.register("stone_harvester_8",
            () -> new MachineBlockItem(
                    BlockInit.STONE_HARVESTER_8.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> SOIL_HARVESTER_1 = ITEMS.register("soil_harvester_1",
            () -> new MachineBlockItem(
                    BlockInit.SOIL_HARVESTER_1.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> SOIL_HARVESTER_2 = ITEMS.register("soil_harvester_2",
            () -> new MachineBlockItem(
                    BlockInit.SOIL_HARVESTER_2.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> SOIL_HARVESTER_3 = ITEMS.register("soil_harvester_3",
            () -> new MachineBlockItem(
                    BlockInit.SOIL_HARVESTER_3.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> SOIL_HARVESTER_4 = ITEMS.register("soil_harvester_4",
            () -> new MachineBlockItem(
                    BlockInit.SOIL_HARVESTER_4.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> SOIL_HARVESTER_5 = ITEMS.register("soil_harvester_5",
            () -> new MachineBlockItem(
                    BlockInit.SOIL_HARVESTER_5.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> SOIL_HARVESTER_6 = ITEMS.register("soil_harvester_6",
            () -> new MachineBlockItem(
                    BlockInit.SOIL_HARVESTER_6.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> SOIL_HARVESTER_7 = ITEMS.register("soil_harvester_7",
            () -> new MachineBlockItem(
                    BlockInit.SOIL_HARVESTER_7.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> SOIL_HARVESTER_8 = ITEMS.register("soil_harvester_8",
            () -> new MachineBlockItem(
                    BlockInit.SOIL_HARVESTER_8.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> MOB_HARVESTER = ITEMS.register("mob_harvester",
            () -> new MachineBlockItem(
                    BlockInit.MOB_HARVESTER.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> HEAT_GENERATOR = ITEMS.register("heat_generator",
            () -> new MachineBlockItem(
                    BlockInit.HEAT_GENERATOR.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<MachineBlockItem> DIMENSIONAL_APPLICATOR = ITEMS.register("dimensional_applicator",
            () -> new MachineBlockItem(
                    BlockInit.DIMENSIONAL_APPLICATOR.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> REGENERATION_ACTIVATOR = ITEMS.register("regeneration_activator",
            () -> new BlockItem(
                    BlockInit.REGENERATION_ACTIVATOR.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> RESISTANCE_ACTIVATOR = ITEMS.register("resistance_activator",
            () -> new BlockItem(
                    BlockInit.RESISTANCE_ACTIVATOR.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> ABSORPTION_ACTIVATOR = ITEMS.register("absorption_activator",
            () -> new BlockItem(
                    BlockInit.ABSORPTION_ACTIVATOR.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> HASTE_ACTIVATOR = ITEMS.register("haste_activator",
            () -> new BlockItem(
                    BlockInit.HASTE_ACTIVATOR.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> SPEED_ACTIVATOR = ITEMS.register("speed_activator",
            () -> new BlockItem(
                    BlockInit.SPEED_ACTIVATOR.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> JUMP_BOOST_ACTIVATOR = ITEMS.register("jump_boost_activator",
            () -> new BlockItem(
                    BlockInit.JUMP_BOOST_ACTIVATOR.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> INVISIBILITY_ACTIVATOR = ITEMS.register("invisibility_activator",
            () -> new BlockItem(
                    BlockInit.INVISIBILITY_ACTIVATOR.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> NIGHT_VISION_ACTIVATOR = ITEMS.register("night_vision_activator",
            () -> new BlockItem(
                    BlockInit.NIGHT_VISION_ACTIVATOR.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

    public static final RegistryObject<BlockItem> STRENGTH_ACTIVATOR = ITEMS.register("strength_activator",
            () -> new BlockItem(
                    BlockInit.STRENGTH_ACTIVATOR.get(),
                    new Item.Properties()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP.instance)
            ));

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
}
