package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.objects.blocks.block_items.MachineBlockItem;
import dunkmania101.spatialharvesters.objects.items.EffectKeyItem;
import dunkmania101.spatialharvesters.objects.items.MobKeyItem;
import dunkmania101.spatialharvesters.objects.items.PlayerKeyItem;
import dunkmania101.spatialharvesters.objects.items.ResourceDisablerKeyItem;
import dunkmania101.spatialharvesters.objects.items.ShardItem;
import dunkmania101.spatialharvesters.objects.items.WeaponKeyItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemInit {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SpatialHarvesters.modid);

    public static final DeferredItem<Item> BASE_POWDER = ITEMS.register("base_powder",
            () -> new Item(
                    getBaseItemProperties()
            ));

    public static final DeferredItem<PlayerKeyItem> PLAYER_KEY = ITEMS.register("player_key",
            () -> new PlayerKeyItem(
                    getNonstackableItemProperties()
            ));

    public static final DeferredItem<EffectKeyItem> EFFECT_KEY = ITEMS.register("effect_key",
            () -> new EffectKeyItem(
                getNonstackableItemProperties()
            ));

    public static final DeferredItem<MobKeyItem> MOB_KEY = ITEMS.register("mob_key",
            () -> new MobKeyItem(
                getNonstackableItemProperties()
            ));

    public static final DeferredItem<WeaponKeyItem> WEAPON_KEY = ITEMS.register("weapon_key",
            () -> new WeaponKeyItem(
                getNonstackableItemProperties()
            ));

    public static final DeferredItem<ResourceDisablerKeyItem> RESOURCE_DISABLER_KEY = ITEMS.register("resource_disabler_key",
            () -> new ResourceDisablerKeyItem(
                getNonstackableItemProperties()
            ));

    public static final DeferredItem<Item> ORE_CORE = ITEMS.register("ore_core",
            () -> new Item(
                    getBaseItemProperties()
            ));

    public static final DeferredItem<Item> BIO_CORE = ITEMS.register("bio_core",
            () -> new Item(
                    getBaseItemProperties()
            ));

    public static final DeferredItem<Item> STONE_CORE = ITEMS.register("stone_core",
            () -> new Item(
                    getBaseItemProperties()
            ));

    public static final DeferredItem<Item> SOIL_CORE = ITEMS.register("soil_core",
            () -> new Item(
                    getBaseItemProperties()
            ));

    public static final DeferredItem<Item> LOOT_CORE = ITEMS.register("loot_core",
            () -> new Item(
                    getBaseItemProperties()
            ));

    public static final DeferredItem<Item> DARK_MOB_CORE = ITEMS.register("dark_mob_core",
            () -> new Item(
                    getBaseItemProperties()
            ));

    public static final DeferredItem<Item> MOB_CORE = ITEMS.register("mob_core",
            () -> new Item(
                    getBaseItemProperties()
            ));

    public static final DeferredItem<ShardItem> MOB_SHARD = ITEMS.register("mob_shard",
            () -> new ShardItem(
                    getBaseItemProperties(),
                    0
            ));

    public static final DeferredItem<Item> MOB_CRYSTAL = ITEMS.register("mob_crystal",
            () -> new Item(
                    getBaseItemProperties()
            ));

    public static final DeferredItem<ShardItem> SHARD_1 = ITEMS.register("shard_1",
            () -> new ShardItem(
                    getBaseItemProperties(),
                    1
            ));

    public static final DeferredItem<ShardItem> SHARD_2 = ITEMS.register("shard_2",
            () -> new ShardItem(
                    getBaseItemProperties(),
                    2
            ));

    public static final DeferredItem<ShardItem> SHARD_3 = ITEMS.register("shard_3",
            () -> new ShardItem(
                    getBaseItemProperties(),
                    3
            ));

    public static final DeferredItem<ShardItem> SHARD_4 = ITEMS.register("shard_4",
            () -> new ShardItem(
                    getBaseItemProperties(),
                    4
            ));

    public static final DeferredItem<ShardItem> SHARD_5 = ITEMS.register("shard_5",
            () -> new ShardItem(
                    getBaseItemProperties(),
                    5
            ));

    public static final DeferredItem<ShardItem> SHARD_6 = ITEMS.register("shard_6",
            () -> new ShardItem(
                    getBaseItemProperties(),
                    6
            ));

    public static final DeferredItem<ShardItem> SHARD_7 = ITEMS.register("shard_7",
            () -> new ShardItem(
                    getBaseItemProperties(),
                    7
            ));

    public static final DeferredItem<Item> CRYSTAL_1 = ITEMS.register("crystal_1",
            () -> new Item(
                    getBaseItemProperties()
            ));

    public static final DeferredItem<Item> CRYSTAL_2 = ITEMS.register("crystal_2",
            () -> new Item(
                    getBaseItemProperties()
            ));

    public static final DeferredItem<Item> CRYSTAL_3 = ITEMS.register("crystal_3",
            () -> new Item(
                    getBaseItemProperties()
            ));

    public static final DeferredItem<Item> CRYSTAL_4 = ITEMS.register("crystal_4",
            () -> new Item(
                    getBaseItemProperties()
            ));

    public static final DeferredItem<Item> CRYSTAL_5 = ITEMS.register("crystal_5",
            () -> new Item(
                    getBaseItemProperties()
            ));

    public static final DeferredItem<Item> CRYSTAL_6 = ITEMS.register("crystal_6",
            () -> new Item(
                    getBaseItemProperties()
            ));

    public static final DeferredItem<Item> CRYSTAL_7 = ITEMS.register("crystal_7",
            () -> new Item(
                    getBaseItemProperties()
            ));

    //BlockItems
    public static final DeferredItem<MachineBlockItem> ORE_HARVESTER_1 = ITEMS.register("ore_harvester_1",
            () -> new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_1.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> ORE_HARVESTER_2 = ITEMS.register("ore_harvester_2",
            () -> new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_2.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> ORE_HARVESTER_3 = ITEMS.register("ore_harvester_3",
            () -> new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_3.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> ORE_HARVESTER_4 = ITEMS.register("ore_harvester_4",
            () -> new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_4.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> ORE_HARVESTER_5 = ITEMS.register("ore_harvester_5",
            () -> new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_5.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> ORE_HARVESTER_6 = ITEMS.register("ore_harvester_6",
            () -> new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_6.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> ORE_HARVESTER_7 = ITEMS.register("ore_harvester_7",
            () -> new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_7.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> ORE_HARVESTER_8 = ITEMS.register("ore_harvester_8",
            () -> new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_8.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> BIO_HARVESTER_1 = ITEMS.register("bio_harvester_1",
            () -> new MachineBlockItem(
                    BlockInit.BIO_HARVESTER_1.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> BIO_HARVESTER_2 = ITEMS.register("bio_harvester_2",
            () -> new MachineBlockItem(
                    BlockInit.BIO_HARVESTER_2.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> BIO_HARVESTER_3 = ITEMS.register("bio_harvester_3",
            () -> new MachineBlockItem(
                    BlockInit.BIO_HARVESTER_3.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> BIO_HARVESTER_4 = ITEMS.register("bio_harvester_4",
            () -> new MachineBlockItem(
                    BlockInit.BIO_HARVESTER_4.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> BIO_HARVESTER_5 = ITEMS.register("bio_harvester_5",
            () -> new MachineBlockItem(
                    BlockInit.BIO_HARVESTER_5.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> BIO_HARVESTER_6 = ITEMS.register("bio_harvester_6",
            () -> new MachineBlockItem(
                    BlockInit.BIO_HARVESTER_6.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> BIO_HARVESTER_7 = ITEMS.register("bio_harvester_7",
            () -> new MachineBlockItem(
                    BlockInit.BIO_HARVESTER_7.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> BIO_HARVESTER_8 = ITEMS.register("bio_harvester_8",
            () -> new MachineBlockItem(
                    BlockInit.BIO_HARVESTER_8.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> STONE_HARVESTER_1 = ITEMS.register("stone_harvester_1",
            () -> new MachineBlockItem(
                    BlockInit.STONE_HARVESTER_1.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> STONE_HARVESTER_2 = ITEMS.register("stone_harvester_2",
            () -> new MachineBlockItem(
                    BlockInit.STONE_HARVESTER_2.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> STONE_HARVESTER_3 = ITEMS.register("stone_harvester_3",
            () -> new MachineBlockItem(
                    BlockInit.STONE_HARVESTER_3.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> STONE_HARVESTER_4 = ITEMS.register("stone_harvester_4",
            () -> new MachineBlockItem(
                    BlockInit.STONE_HARVESTER_4.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> STONE_HARVESTER_5 = ITEMS.register("stone_harvester_5",
            () -> new MachineBlockItem(
                    BlockInit.STONE_HARVESTER_5.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> STONE_HARVESTER_6 = ITEMS.register("stone_harvester_6",
            () -> new MachineBlockItem(
                    BlockInit.STONE_HARVESTER_6.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> STONE_HARVESTER_7 = ITEMS.register("stone_harvester_7",
            () -> new MachineBlockItem(
                    BlockInit.STONE_HARVESTER_7.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> STONE_HARVESTER_8 = ITEMS.register("stone_harvester_8",
            () -> new MachineBlockItem(
                    BlockInit.STONE_HARVESTER_8.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> SOIL_HARVESTER_1 = ITEMS.register("soil_harvester_1",
            () -> new MachineBlockItem(
                    BlockInit.SOIL_HARVESTER_1.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> SOIL_HARVESTER_2 = ITEMS.register("soil_harvester_2",
            () -> new MachineBlockItem(
                    BlockInit.SOIL_HARVESTER_2.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> SOIL_HARVESTER_3 = ITEMS.register("soil_harvester_3",
            () -> new MachineBlockItem(
                    BlockInit.SOIL_HARVESTER_3.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> SOIL_HARVESTER_4 = ITEMS.register("soil_harvester_4",
            () -> new MachineBlockItem(
                    BlockInit.SOIL_HARVESTER_4.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> SOIL_HARVESTER_5 = ITEMS.register("soil_harvester_5",
            () -> new MachineBlockItem(
                    BlockInit.SOIL_HARVESTER_5.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> SOIL_HARVESTER_6 = ITEMS.register("soil_harvester_6",
            () -> new MachineBlockItem(
                    BlockInit.SOIL_HARVESTER_6.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> SOIL_HARVESTER_7 = ITEMS.register("soil_harvester_7",
            () -> new MachineBlockItem(
                    BlockInit.SOIL_HARVESTER_7.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> SOIL_HARVESTER_8 = ITEMS.register("soil_harvester_8",
            () -> new MachineBlockItem(
                    BlockInit.SOIL_HARVESTER_8.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> LOOT_HARVESTER = ITEMS.register("loot_harvester",
            () -> new MachineBlockItem(
                    BlockInit.LOOT_HARVESTER.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> DARK_MOB_HARVESTER = ITEMS.register("dark_mob_harvester",
            () -> new MachineBlockItem(
                    BlockInit.DARK_MOB_HARVESTER.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> SPECIFIC_MOB_HARVESTER = ITEMS.register("specific_mob_harvester",
            () -> new MachineBlockItem(
                    BlockInit.SPECIFIC_MOB_HARVESTER.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> HEAT_GENERATOR = ITEMS.register("heat_generator",
            () -> new MachineBlockItem(
                    BlockInit.HEAT_GENERATOR.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<MachineBlockItem> DIMENSIONAL_APPLICATOR = ITEMS.register("dimensional_applicator",
            () -> new MachineBlockItem(
                    BlockInit.DIMENSIONAL_APPLICATOR.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<BlockItem> REGENERATION_ACTIVATOR = ITEMS.register("regeneration_activator",
            () -> new BlockItem(
                    BlockInit.REGENERATION_ACTIVATOR.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<BlockItem> RESISTANCE_ACTIVATOR = ITEMS.register("resistance_activator",
            () -> new BlockItem(
                    BlockInit.RESISTANCE_ACTIVATOR.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<BlockItem> ABSORPTION_ACTIVATOR = ITEMS.register("absorption_activator",
            () -> new BlockItem(
                    BlockInit.ABSORPTION_ACTIVATOR.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<BlockItem> HASTE_ACTIVATOR = ITEMS.register("haste_activator",
            () -> new BlockItem(
                    BlockInit.HASTE_ACTIVATOR.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<BlockItem> SPEED_ACTIVATOR = ITEMS.register("speed_activator",
            () -> new BlockItem(
                    BlockInit.SPEED_ACTIVATOR.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<BlockItem> JUMP_BOOST_ACTIVATOR = ITEMS.register("jump_boost_activator",
            () -> new BlockItem(
                    BlockInit.JUMP_BOOST_ACTIVATOR.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<BlockItem> INVISIBILITY_ACTIVATOR = ITEMS.register("invisibility_activator",
            () -> new BlockItem(
                    BlockInit.INVISIBILITY_ACTIVATOR.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<BlockItem> NIGHT_VISION_ACTIVATOR = ITEMS.register("night_vision_activator",
            () -> new BlockItem(
                    BlockInit.NIGHT_VISION_ACTIVATOR.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<BlockItem> STRENGTH_ACTIVATOR = ITEMS.register("strength_activator",
            () -> new BlockItem(
                    BlockInit.STRENGTH_ACTIVATOR.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<BlockItem> CHUNK_LOADER = ITEMS.register("chunk_loader",
            () -> new BlockItem(
                    BlockInit.CHUNK_LOADER.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<BlockItem> CASING = ITEMS.register("casing",
            () -> new BlockItem(
                    BlockInit.CASING.get(),
                    getBaseItemProperties()
            ));

    public static final DeferredItem<BlockItem> SPACE_RIPPER = ITEMS.register("space_ripper",
            () -> new BlockItem(
                    BlockInit.SPACE_RIPPER.get(),
                    getBaseItemProperties()
            ));

    // Methods
    public static Item.Properties getBaseItemProperties() {
        return new Item.Properties();
    }

    public static Item.Properties getNonstackableItemProperties() {
        return getBaseItemProperties()
                .stacksTo(1);
    }
}
