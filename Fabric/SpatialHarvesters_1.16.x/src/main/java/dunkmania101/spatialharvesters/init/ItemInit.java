package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.objects.blocks.block_items.MachineBlockItem;
import dunkmania101.spatialharvesters.objects.items.EffectKeyItem;
import dunkmania101.spatialharvesters.objects.items.MobKeyItem;
import dunkmania101.spatialharvesters.objects.items.PlayerKeyItem;
import dunkmania101.spatialharvesters.objects.items.ResourceDisablerKeyItem;
import dunkmania101.spatialharvesters.objects.items.ShardItem;
import dunkmania101.spatialharvesters.objects.items.WeaponKeyItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit implements ModInitializer {
        // Ingredients
        // Crafting
        public static final Item BASE_POWDER = registerItem(new Item(getBaseItemSettings()), "base_powder");

        // Cores
        public static final Item ORE_CORE = registerItem(new Item(getBaseItemSettings()), "ore_core");
        public static final Item BIO_CORE = registerItem(new Item(getBaseItemSettings()), "bio_core");
        public static final Item STONE_CORE = registerItem(new Item(getBaseItemSettings()), "stone_core");
        public static final Item SOIL_CORE = registerItem(new Item(getBaseItemSettings()), "soil_core");
        public static final Item MOB_CORE = registerItem(new Item(getBaseItemSettings()), "mob_core");

        // Shards
        public static final ShardItem SHARD_1 = registerItem(new ShardItem(getBaseItemSettings(), 1), "shard_1");
        public static final ShardItem SHARD_2 = registerItem(new ShardItem(getBaseItemSettings(), 2), "shard_2");
        public static final ShardItem SHARD_3 = registerItem(new ShardItem(getBaseItemSettings(), 3), "shard_3");
        public static final ShardItem SHARD_4 = registerItem(new ShardItem(getBaseItemSettings(), 4), "shard_4");
        public static final ShardItem SHARD_5 = registerItem(new ShardItem(getBaseItemSettings(), 5), "shard_5");
        public static final ShardItem SHARD_6 = registerItem(new ShardItem(getBaseItemSettings(), 6), "shard_6");
        public static final ShardItem SHARD_7 = registerItem(new ShardItem(getBaseItemSettings(), 7), "shard_7");
        public static final ShardItem MOB_SHARD = registerItem(new ShardItem(getBaseItemSettings(), -1), "mob_shard");

        // Crystals
        public static final Item CRYSTAL_1 = registerItem(new Item(getBaseItemSettings()), "crystal_1");
        public static final Item CRYSTAL_2 = registerItem(new Item(getBaseItemSettings()), "crystal_2");
        public static final Item CRYSTAL_3 = registerItem(new Item(getBaseItemSettings()), "crystal_3");
        public static final Item CRYSTAL_4 = registerItem(new Item(getBaseItemSettings()), "crystal_4");
        public static final Item CRYSTAL_5 = registerItem(new Item(getBaseItemSettings()), "crystal_5");
        public static final Item CRYSTAL_6 = registerItem(new Item(getBaseItemSettings()), "crystal_6");
        public static final Item CRYSTAL_7 = registerItem(new Item(getBaseItemSettings()), "crystal_7");
        public static final Item MOB_CRYSTAL = registerItem(new Item(getBaseItemSettings()), "mob_crystal");

        // Keys
        public static final PlayerKeyItem PLAYER_KEY = registerItem(new PlayerKeyItem(getBaseItemSettings()),
                        "player_key");
        public static final EffectKeyItem EFFECT_KEY = registerItem(new EffectKeyItem(getBaseItemSettings()),
                        "effect_key");
        public static final MobKeyItem MOB_KEY = registerItem(new MobKeyItem(getBaseItemSettings()), "mob_key");
        public static final WeaponKeyItem WEAPON_KEY = registerItem(new WeaponKeyItem(getBaseItemSettings()),
                        "weapon_key");
        public static final ResourceDisablerKeyItem RESOURCE_DISABLER_KEY = registerItem(
                        new ResourceDisablerKeyItem(getBaseItemSettings()), "resource_disabler_key");

        // BlockItems
        // Ore Harvesters
        public static final MachineBlockItem ORE_HARVESTER_1 = registerItem(
                        new MachineBlockItem(BlockInit.ORE_HARVESTER_1, getBaseItemSettings()), "ore_harvester_1");

        public static final MachineBlockItem ORE_HARVESTER_2 = registerItem(
                        new MachineBlockItem(BlockInit.ORE_HARVESTER_2, getBaseItemSettings()), "ore_harvester_2");

        public static final MachineBlockItem ORE_HARVESTER_3 = registerItem(
                        new MachineBlockItem(BlockInit.ORE_HARVESTER_3, getBaseItemSettings()), "ore_harvester_3");

        public static final MachineBlockItem ORE_HARVESTER_4 = registerItem(
                        new MachineBlockItem(BlockInit.ORE_HARVESTER_4, getBaseItemSettings()), "ore_harvester_4");

        public static final MachineBlockItem ORE_HARVESTER_5 = registerItem(
                        new MachineBlockItem(BlockInit.ORE_HARVESTER_5, getBaseItemSettings()), "ore_harvester_5");

        public static final MachineBlockItem ORE_HARVESTER_6 = registerItem(
                        new MachineBlockItem(BlockInit.ORE_HARVESTER_6, getBaseItemSettings()), "ore_harvester_6");

        public static final MachineBlockItem ORE_HARVESTER_7 = registerItem(
                        new MachineBlockItem(BlockInit.ORE_HARVESTER_7, getBaseItemSettings()), "ore_harvester_7");

        public static final MachineBlockItem ORE_HARVESTER_8 = registerItem(
                        new MachineBlockItem(BlockInit.ORE_HARVESTER_8, getBaseItemSettings()), "ore_harvester_8");

        // Bio Harvesters
        public static final MachineBlockItem BIO_HARVESTER_1 = registerItem(
                        new MachineBlockItem(BlockInit.BIO_HARVESTER_1, getBaseItemSettings()), "bio_harvester_1");

        public static final MachineBlockItem BIO_HARVESTER_2 = registerItem(
                        new MachineBlockItem(BlockInit.BIO_HARVESTER_2, getBaseItemSettings()), "bio_harvester_2");

        public static final MachineBlockItem BIO_HARVESTER_3 = registerItem(
                        new MachineBlockItem(BlockInit.BIO_HARVESTER_3, getBaseItemSettings()), "bio_harvester_3");

        public static final MachineBlockItem BIO_HARVESTER_4 = registerItem(
                        new MachineBlockItem(BlockInit.BIO_HARVESTER_4, getBaseItemSettings()), "bio_harvester_4");

        public static final MachineBlockItem BIO_HARVESTER_5 = registerItem(
                        new MachineBlockItem(BlockInit.BIO_HARVESTER_5, getBaseItemSettings()), "bio_harvester_5");

        public static final MachineBlockItem BIO_HARVESTER_6 = registerItem(
                        new MachineBlockItem(BlockInit.BIO_HARVESTER_6, getBaseItemSettings()), "bio_harvester_6");

        public static final MachineBlockItem BIO_HARVESTER_7 = registerItem(
                        new MachineBlockItem(BlockInit.BIO_HARVESTER_7, getBaseItemSettings()), "bio_harvester_7");

        public static final MachineBlockItem BIO_HARVESTER_8 = registerItem(
                        new MachineBlockItem(BlockInit.BIO_HARVESTER_8, getBaseItemSettings()), "bio_harvester_8");

        // Stone Harvesters
        public static final MachineBlockItem STONE_HARVESTER_1 = registerItem(
                        new MachineBlockItem(BlockInit.STONE_HARVESTER_1, getBaseItemSettings()), "stone_harvester_1");

        public static final MachineBlockItem STONE_HARVESTER_2 = registerItem(
                        new MachineBlockItem(BlockInit.STONE_HARVESTER_2, getBaseItemSettings()), "stone_harvester_2");

        public static final MachineBlockItem STONE_HARVESTER_3 = registerItem(
                        new MachineBlockItem(BlockInit.STONE_HARVESTER_3, getBaseItemSettings()), "stone_harvester_3");

        public static final MachineBlockItem STONE_HARVESTER_4 = registerItem(
                        new MachineBlockItem(BlockInit.STONE_HARVESTER_4, getBaseItemSettings()), "stone_harvester_4");

        public static final MachineBlockItem STONE_HARVESTER_5 = registerItem(
                        new MachineBlockItem(BlockInit.STONE_HARVESTER_5, getBaseItemSettings()), "stone_harvester_5");

        public static final MachineBlockItem STONE_HARVESTER_6 = registerItem(
                        new MachineBlockItem(BlockInit.STONE_HARVESTER_6, getBaseItemSettings()), "stone_harvester_6");

        public static final MachineBlockItem STONE_HARVESTER_7 = registerItem(
                        new MachineBlockItem(BlockInit.STONE_HARVESTER_7, getBaseItemSettings()), "stone_harvester_7");

        public static final MachineBlockItem STONE_HARVESTER_8 = registerItem(
                        new MachineBlockItem(BlockInit.STONE_HARVESTER_8, getBaseItemSettings()), "stone_harvester_8");

        // Soil Harvesters
        public static final MachineBlockItem SOIL_HARVESTER_1 = registerItem(
                        new MachineBlockItem(BlockInit.SOIL_HARVESTER_1, getBaseItemSettings()), "soil_harvester_1");

        public static final MachineBlockItem SOIL_HARVESTER_2 = registerItem(
                        new MachineBlockItem(BlockInit.SOIL_HARVESTER_2, getBaseItemSettings()), "soil_harvester_2");

        public static final MachineBlockItem SOIL_HARVESTER_3 = registerItem(
                        new MachineBlockItem(BlockInit.SOIL_HARVESTER_3, getBaseItemSettings()), "soil_harvester_3");

        public static final MachineBlockItem SOIL_HARVESTER_4 = registerItem(
                        new MachineBlockItem(BlockInit.SOIL_HARVESTER_4, getBaseItemSettings()), "soil_harvester_4");

        public static final MachineBlockItem SOIL_HARVESTER_5 = registerItem(
                        new MachineBlockItem(BlockInit.SOIL_HARVESTER_5, getBaseItemSettings()), "soil_harvester_5");

        public static final MachineBlockItem SOIL_HARVESTER_6 = registerItem(
                        new MachineBlockItem(BlockInit.SOIL_HARVESTER_6, getBaseItemSettings()), "soil_harvester_6");

        public static final MachineBlockItem SOIL_HARVESTER_7 = registerItem(
                        new MachineBlockItem(BlockInit.SOIL_HARVESTER_7, getBaseItemSettings()), "soil_harvester_7");

        public static final MachineBlockItem SOIL_HARVESTER_8 = registerItem(
                        new MachineBlockItem(BlockInit.SOIL_HARVESTER_8, getBaseItemSettings()), "soil_harvester_8");

        // Mob Harvesters
        public static final MachineBlockItem SPECIFIC_MOB_HARVESTER = registerItem(
                        new MachineBlockItem(BlockInit.SPECIFIC_MOB_HARVESTER, getBaseItemSettings()),
                        "specific_mob_harvester");

        public static final MachineBlockItem DARK_MOB_HARVESTER = registerItem(
                        new MachineBlockItem(BlockInit.DARK_MOB_HARVESTER, getBaseItemSettings()),
                        "dark_mob_harvester");

        // Activators
        public static final BlockItem REGENERATION_ACTIVATOR = registerItem(
                        new BlockItem(BlockInit.REGENERATION_ACTIVATOR, getBaseItemSettings()),
                        "regeneration_activator");

        public static final BlockItem RESISTANCE_ACTIVATOR = registerItem(
                        new BlockItem(BlockInit.RESISTANCE_ACTIVATOR, getBaseItemSettings()), "resistance_activator");

        public static final BlockItem ABSORPTION_ACTIVATOR = registerItem(
                        new BlockItem(BlockInit.ABSORPTION_ACTIVATOR, getBaseItemSettings()), "absorption_activator");

        public static final BlockItem HASTE_ACTIVATOR = registerItem(
                        new BlockItem(BlockInit.HASTE_ACTIVATOR, getBaseItemSettings()), "haste_activator");

        public static final BlockItem SPEED_ACTIVATOR = registerItem(
                        new BlockItem(BlockInit.SPEED_ACTIVATOR, getBaseItemSettings()), "speed_activator");

        public static final BlockItem JUMP_BOOST_ACTIVATOR = registerItem(
                        new BlockItem(BlockInit.JUMP_BOOST_ACTIVATOR, getBaseItemSettings()), "jump_boost_activator");

        public static final BlockItem INVISIBILITY_ACTIVATOR = registerItem(
                        new BlockItem(BlockInit.INVISIBILITY_ACTIVATOR, getBaseItemSettings()),
                        "invisibility_activator");

        public static final BlockItem NIGHT_VISION_ACTIVATOR = registerItem(
                        new BlockItem(BlockInit.NIGHT_VISION_ACTIVATOR, getBaseItemSettings()),
                        "night_vision_activator");

        public static final BlockItem STRENGTH_ACTIVATOR = registerItem(
                        new BlockItem(BlockInit.STRENGTH_ACTIVATOR, getBaseItemSettings()), "strength_activator");

        // Misc
        public static final BlockItem CHUNK_LOADER = registerItem(
                new BlockItem(BlockInit.CHUNK_LOADER, getBaseItemSettings()), "chunk_loader");

        public static final BlockItem SPACE_RIPPER = registerItem(
                new BlockItem(BlockInit.SPACE_RIPPER, getBaseItemSettings()), "space_ripper");

        public static final MachineBlockItem HEAT_GENERATOR = registerItem(
                        new MachineBlockItem(BlockInit.HEAT_GENERATOR, getBaseItemSettings()), "heat_generator");

        public static final MachineBlockItem DIMENSIONAL_APPLICATOR = registerItem(
                        new MachineBlockItem(BlockInit.DIMENSIONAL_APPLICATOR, getBaseItemSettings()),
                        "dimensional_applicator");

        // Methods
        public static <I extends Item> I registerItem(I item, String name) {
                Registry.register(Registry.ITEM, new Identifier(SpatialHarvesters.modid, name), item);
                return item;
        }

        public static FabricItemSettings getBaseItemSettings() {
                return new FabricItemSettings().group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP);
        }

        @Override
        public void onInitialize() {
        }
}
