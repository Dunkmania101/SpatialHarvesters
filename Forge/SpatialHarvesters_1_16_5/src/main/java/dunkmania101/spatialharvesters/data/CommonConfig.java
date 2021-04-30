package dunkmania101.spatialharvesters.data;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;
import java.util.ArrayList;

@Mod.EventBusSubscriber
public class CommonConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> CUSTOM_ORE_TAGS;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> CUSTOM_BIO_TAGS;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> CUSTOM_STONE_TAGS;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> CUSTOM_SOIL_TAGS;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> CUSTOM_LOOT_TABLES;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> CUSTOM_ORES;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> CUSTOM_BIOS;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> CUSTOM_STONES;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> CUSTOM_SOILS;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<ArrayList<String>>>> CUSTOM_MOB_DROPS;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> MIN_TIER_ORES;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> MIN_TIER_BIOS;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> MIN_TIER_STONES;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> MIN_TIER_SOILS;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> BLACKLIST_ORES;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> BLACKLIST_BIOS;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> BLACKLIST_STONES;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> BLACKLIST_SOILS;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> BLACKLIST_MOBS;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> BLACKLIST_LOOT;
    public static ForgeConfigSpec.ConfigValue<ArrayList<String>> BLACKLIST_ORES_MOD;
    public static ForgeConfigSpec.ConfigValue<ArrayList<String>> BLACKLIST_BIOS_MOD;
    public static ForgeConfigSpec.ConfigValue<ArrayList<String>> BLACKLIST_STONES_MOD;
    public static ForgeConfigSpec.ConfigValue<ArrayList<String>> BLACKLIST_SOILS_MOD;
    public static ForgeConfigSpec.ConfigValue<ArrayList<String>> BLACKLIST_MOBS_MOD;
    public static ForgeConfigSpec.ConfigValue<ArrayList<String>> BLACKLIST_LOOT_MOD;
    public static ForgeConfigSpec.IntValue ORE_1_SPEED;
    public static ForgeConfigSpec.IntValue ORE_2_SPEED;
    public static ForgeConfigSpec.IntValue ORE_3_SPEED;
    public static ForgeConfigSpec.IntValue ORE_4_SPEED;
    public static ForgeConfigSpec.IntValue ORE_5_SPEED;
    public static ForgeConfigSpec.IntValue ORE_6_SPEED;
    public static ForgeConfigSpec.IntValue ORE_7_SPEED;
    public static ForgeConfigSpec.IntValue ORE_8_SPEED;
    public static ForgeConfigSpec.IntValue BIO_1_SPEED;
    public static ForgeConfigSpec.IntValue BIO_2_SPEED;
    public static ForgeConfigSpec.IntValue BIO_3_SPEED;
    public static ForgeConfigSpec.IntValue BIO_4_SPEED;
    public static ForgeConfigSpec.IntValue BIO_5_SPEED;
    public static ForgeConfigSpec.IntValue BIO_6_SPEED;
    public static ForgeConfigSpec.IntValue BIO_7_SPEED;
    public static ForgeConfigSpec.IntValue BIO_8_SPEED;
    public static ForgeConfigSpec.IntValue STONE_1_SPEED;
    public static ForgeConfigSpec.IntValue STONE_2_SPEED;
    public static ForgeConfigSpec.IntValue STONE_3_SPEED;
    public static ForgeConfigSpec.IntValue STONE_4_SPEED;
    public static ForgeConfigSpec.IntValue STONE_5_SPEED;
    public static ForgeConfigSpec.IntValue STONE_6_SPEED;
    public static ForgeConfigSpec.IntValue STONE_7_SPEED;
    public static ForgeConfigSpec.IntValue STONE_8_SPEED;
    public static ForgeConfigSpec.IntValue LOOT_SPEED;
    public static ForgeConfigSpec.IntValue DARK_MOB_SPEED;
    public static ForgeConfigSpec.IntValue SPECIFIC_MOB_SPEED;
    public static ForgeConfigSpec.IntValue SOIL_1_SPEED;
    public static ForgeConfigSpec.IntValue SOIL_2_SPEED;
    public static ForgeConfigSpec.IntValue SOIL_3_SPEED;
    public static ForgeConfigSpec.IntValue SOIL_4_SPEED;
    public static ForgeConfigSpec.IntValue SOIL_5_SPEED;
    public static ForgeConfigSpec.IntValue SOIL_6_SPEED;
    public static ForgeConfigSpec.IntValue SOIL_7_SPEED;
    public static ForgeConfigSpec.IntValue SOIL_8_SPEED;
    public static ForgeConfigSpec.IntValue ORE_1_PRICE;
    public static ForgeConfigSpec.IntValue ORE_2_PRICE;
    public static ForgeConfigSpec.IntValue ORE_3_PRICE;
    public static ForgeConfigSpec.IntValue ORE_4_PRICE;
    public static ForgeConfigSpec.IntValue ORE_5_PRICE;
    public static ForgeConfigSpec.IntValue ORE_6_PRICE;
    public static ForgeConfigSpec.IntValue ORE_7_PRICE;
    public static ForgeConfigSpec.IntValue ORE_8_PRICE;
    public static ForgeConfigSpec.IntValue BIO_1_PRICE;
    public static ForgeConfigSpec.IntValue BIO_2_PRICE;
    public static ForgeConfigSpec.IntValue BIO_3_PRICE;
    public static ForgeConfigSpec.IntValue BIO_4_PRICE;
    public static ForgeConfigSpec.IntValue BIO_5_PRICE;
    public static ForgeConfigSpec.IntValue BIO_6_PRICE;
    public static ForgeConfigSpec.IntValue BIO_7_PRICE;
    public static ForgeConfigSpec.IntValue BIO_8_PRICE;
    public static ForgeConfigSpec.IntValue STONE_1_PRICE;
    public static ForgeConfigSpec.IntValue STONE_2_PRICE;
    public static ForgeConfigSpec.IntValue STONE_3_PRICE;
    public static ForgeConfigSpec.IntValue STONE_4_PRICE;
    public static ForgeConfigSpec.IntValue STONE_5_PRICE;
    public static ForgeConfigSpec.IntValue STONE_6_PRICE;
    public static ForgeConfigSpec.IntValue STONE_7_PRICE;
    public static ForgeConfigSpec.IntValue STONE_8_PRICE;
    public static ForgeConfigSpec.IntValue SOIL_1_PRICE;
    public static ForgeConfigSpec.IntValue SOIL_2_PRICE;
    public static ForgeConfigSpec.IntValue SOIL_3_PRICE;
    public static ForgeConfigSpec.IntValue SOIL_4_PRICE;
    public static ForgeConfigSpec.IntValue SOIL_5_PRICE;
    public static ForgeConfigSpec.IntValue SOIL_6_PRICE;
    public static ForgeConfigSpec.IntValue SOIL_7_PRICE;
    public static ForgeConfigSpec.IntValue SOIL_8_PRICE;
    public static ForgeConfigSpec.IntValue LOOT_PRICE;
    public static ForgeConfigSpec.IntValue DARK_MOB_PRICE;
    public static ForgeConfigSpec.IntValue SPECIFIC_MOB_PRICE;
    public static ForgeConfigSpec.IntValue HARVESTER_SHARD_CHANCE;
    public static ForgeConfigSpec.IntValue MOB_HARVESTER_MOB_SHARD_CHANCE;
    public static ForgeConfigSpec.IntValue HEAT_GENERATOR_SPEED;
    public static ForgeConfigSpec.IntValue DIMENSIONAL_APPLICATOR_PRICE;
    public static ForgeConfigSpec.IntValue DIMENSIONAL_APPLICATOR_AMPLIFIER;
    public static ForgeConfigSpec.IntValue DIMENSIONAL_APPLICATOR_DURATION;
    public static ForgeConfigSpec.DoubleValue DIMENSIONAL_APPLICATOR_DIVISOR;
    public static ForgeConfigSpec.BooleanValue DIMENSIONAL_APPLICATOR_IS_BEACON_EFFECT;
    public static ForgeConfigSpec.BooleanValue DIMENSIONAL_APPLICATOR_SHOW_PARTICLES;
    public static ForgeConfigSpec.BooleanValue DIMENSIONAL_APPLICATOR_SHOW_ICON;
    public static ForgeConfigSpec.DoubleValue BLOCK_HARDNESS;
    public static ForgeConfigSpec.DoubleValue BLOCK_RESISTANCE;
    public static ForgeConfigSpec.IntValue MACHINE_LIGHT_LEVEL;
    public static ForgeConfigSpec.IntValue KEY_BREAK_SPEED_MULTIPLIER;
    public static ForgeConfigSpec.IntValue HARVESTER_CAPACITY_MULTIPLIER;
    public static ForgeConfigSpec.IntValue DIMENSIONAL_APPLICATOR_CAPACITY_MULTIPLIER;
    public static ForgeConfigSpec.IntValue HEAT_GENERATOR_CAPACITY_MULTIPLIER;
    public static ForgeConfigSpec.BooleanValue ENABLE_CHUNK_LOADER;
    public static ForgeConfigSpec.BooleanValue ENABLE_DIMENSIONAL_APPLICATOR;
    public static ForgeConfigSpec.BooleanValue ENABLE_HEAT_GENERATOR;
    public static ForgeConfigSpec.BooleanValue ENABLE_ORE_HARVESTERS;
    public static ForgeConfigSpec.BooleanValue ENABLE_BIO_HARVESTERS;
    public static ForgeConfigSpec.BooleanValue ENABLE_STONE_HARVESTERS;
    public static ForgeConfigSpec.BooleanValue ENABLE_SOIL_HARVESTERS;
    public static ForgeConfigSpec.BooleanValue ENABLE_LOOT_HARVESTER;
    public static ForgeConfigSpec.BooleanValue ENABLE_DARK_MOB_HARVESTER;
    public static ForgeConfigSpec.BooleanValue ENABLE_SPECIFIC_MOB_HARVESTER;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> VALID_HEAT_SOURCES;

    static {
        BUILDER.push("Spatial Harvesters - Common Config: ");
        setup();
        BUILDER.pop();
    }

    public static void init(Path file) {
        final CommentedFileConfig data = CommentedFileConfig.builder(file)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        data.load();
        CONFIG.setConfig(data);
    }

    private static void setup() {
        BUILDER.push("Custom Harvester Outputs: ");
        ArrayList<ArrayList<String>> custom_ores = new ArrayList<>();

        String mod = "appliedenergistics2";
        String ore = "quartz_ore";
        ArrayList<String> modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "appliedenergistics2";
        ore = "charged_quartz_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "rftoolsbase";
        ore = "dimensionalshard_overworld";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "rftoolsbase";

        ore = "dimensionalshard_nether";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "rftoolsbase";
        ore = "dimensionalshard_end";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "rhodonite";
        ore = "block_ore_fluorite";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "rhodonite";
        ore = "block_ore_rhodonite";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "exp_ore";
        ore = "block_exp_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "rockcandy";
        ore = "candy_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "lightestlamp";
        ore = "boron_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "zombie_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "blaze_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "ghast_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "magma_cube_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "wither_skeleton_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "zombie_pigman_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "salmon_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "squid_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "cod_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "sheep_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "rabbit_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "pig_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "cow_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "chicken_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "bat_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "witch_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "spider_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "slime_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "phantom_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "skeleton_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "enderman_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "guardian_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "pufferfish_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "funores";
        ore = "creeper_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "gobber2";
        ore = "gobber2_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "gobber2";
        ore = "gobber2_ore_nether";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "gobber2";
        ore = "gobber2_ore_end";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        mod = "minecraft";
        ore = "glowstone";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);

        CUSTOM_ORES = BUILDER.comment("Custom outputs for the Ore Harvester.")
                .define("custom_ores", custom_ores);
        ArrayList<ArrayList<String>> custom_bios = new ArrayList<>();
        CUSTOM_BIOS = BUILDER.comment("Custom outputs for the Bio Harvester.")
                .define("custom_bios", custom_bios);
        ArrayList<ArrayList<String>> custom_stones = new ArrayList<>();
        CUSTOM_STONES = BUILDER.comment("Custom outputs for the Stone Harvester.")
                .define("custom_stones", custom_stones);

        ArrayList<ArrayList<String>> custom_soils = new ArrayList<>();

        mod = "minecraft";
        String soil = "clay";
        ArrayList<String> modSoil = new ArrayList<>();
        modSoil.add(mod);
        modSoil.add(soil);
        custom_soils.add(modSoil);

        CUSTOM_SOILS = BUILDER.comment("Custom outputs for the Soil Harvester.")
                .define("custom_soils", custom_soils);

        ArrayList<ArrayList<String>> custom_loot_tables = new ArrayList<>();

        mod = "minecraft";
        String table = "chests/simple_dungeon";
        ArrayList<String> modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/buried_treasure";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/desert_pyramid";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/abandoned_mineshaft";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/end_city_treasure";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/igloo_chest";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/jungle_temple";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/nether_bridge";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/pillager_outpost";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/shipwreck_map";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/shipwreck_treasure";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/stronghold_corridor";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/stronghold_corridor";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/stronghold_corridor";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/stronghold_crossing";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/stronghold_library";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/underwater_ruin_big";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/underwater_ruin_small";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        mod = "minecraft";
        table = "chests/woodland_mansion";
        modTable = new ArrayList<>();
        modTable.add(mod);
        modTable.add(table);
        custom_loot_tables.add(modTable);

        CUSTOM_LOOT_TABLES = BUILDER.comment("Custom loot tables for the Loot Harvester.")
                .define("custom_loot_tables", custom_loot_tables);

        ArrayList<ArrayList<ArrayList<String>>> custom_mob_drops = new ArrayList<>();

        ArrayList<ArrayList<String>> modMobDrop = new ArrayList<>();
        mod = "minecraft";
        String mob = "ender_dragon";
        ArrayList<String> modMob = new ArrayList<>();
        modMob.add(mod);
        modMob.add(mob);
        modMobDrop.add(modMob);
        mod = "minecraft";
        String mobDrop = "elytra";
        ArrayList<String> modDrop = new ArrayList<>();
        modDrop.add(mod);
        modDrop.add(mobDrop);
        modMobDrop.add(modDrop);
        custom_mob_drops.add(modMobDrop);

        modMobDrop = new ArrayList<>();
        mod = "minecraft";
        mob = "ender_dragon";
        modMob = new ArrayList<>();
        modMob.add(mod);
        modMob.add(mob);
        modMobDrop.add(modMob);
        mod = "minecraft";
        mobDrop = "dragon_head";
        modDrop = new ArrayList<>();
        modDrop.add(mod);
        modDrop.add(mobDrop);
        modMobDrop.add(modDrop);
        custom_mob_drops.add(modMobDrop);

        modMobDrop = new ArrayList<>();
        mod = "minecraft";
        mob = "ender_dragon";
        modMob = new ArrayList<>();
        modMob.add(mod);
        modMob.add(mob);
        modMobDrop.add(modMob);
        mod = "minecraft";
        mobDrop = "dragon_egg";
        modDrop = new ArrayList<>();
        modDrop.add(mod);
        modDrop.add(mobDrop);
        modMobDrop.add(modDrop);
        custom_mob_drops.add(modMobDrop);

        modMobDrop = new ArrayList<>();
        mod = "minecraft";
        mob = "ender_dragon";
        modMob = new ArrayList<>();
        modMob.add(mod);
        modMob.add(mob);
        modMobDrop.add(modMob);
        mod = "minecraft";
        mobDrop = "dragon_breath";
        modDrop = new ArrayList<>();
        modDrop.add(mod);
        modDrop.add(mobDrop);
        modMobDrop.add(modDrop);
        custom_mob_drops.add(modMobDrop);

        CUSTOM_MOB_DROPS = BUILDER.comment("Custom drops for the Mob Harvester.")
                .define("custom_mob_drops", custom_mob_drops);
        BUILDER.pop();

        BUILDER.push("Custom Harvester Tags: ");
        ArrayList<ArrayList<String>> custom_ore_tags = new ArrayList<>();

        mod = "forge";
        String tag = "ores";
        ArrayList<String> modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        CUSTOM_ORE_TAGS = BUILDER.comment("Custom tags for the Ore Harvester.")
                .define("custom_ore_tags", custom_ore_tags);

        ArrayList<ArrayList<String>> custom_bio_tags = new ArrayList<>();

        mod = "forge";
        tag = "crops";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "forge";
        tag = "leather";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "forge";
        tag = "feathers";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "forge";
        tag = "seeds";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "forge";
        tag = "dyes";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "forge";
        tag = "bones";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "forge";
        tag = "rods_wooden";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "minecraft";
        tag = "small_flowers";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "minecraft";
        tag = "logs";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "minecraft";
        tag = "planks";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "minecraft";
        tag = "saplings";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "minecraft";
        tag = "leaves";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        CUSTOM_BIO_TAGS = BUILDER.comment("Custom tags for the Bio Harvester.")
                .define("custom_bio_tags", custom_bio_tags);

        ArrayList<ArrayList<String>> custom_stone_tags = new ArrayList<>();

        mod = "forge";
        tag = "cobblestone";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_stone_tags.add(modTag);

        mod = "forge";
        tag = "sandstone";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_stone_tags.add(modTag);

        mod = "forge";
        tag = "end_stones";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_stone_tags.add(modTag);

        mod = "forge";
        tag = "netherrack";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_stone_tags.add(modTag);

        CUSTOM_STONE_TAGS = BUILDER.comment("Custom tags for the Stone Harvester.")
                .define("custom_stone_tags", custom_stone_tags);

        ArrayList<ArrayList<String>> custom_soil_tags = new ArrayList<>();

        mod = "forge";
        tag = "dirt";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_soil_tags.add(modTag);

        mod = "forge";
        tag = "sand";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_soil_tags.add(modTag);

        mod = "forge";
        tag = "gravel";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_soil_tags.add(modTag);

        CUSTOM_SOIL_TAGS = BUILDER.comment("Custom tags for the Soil Harvester.")
                .comment("Note that this one uses block tags, rather than item tags, because most soil-like things are only tagged in their blocks.")
                .define("custom_soil_tags", custom_soil_tags);
        BUILDER.pop();

        BUILDER.push("Minimum Harvester Tier Items - Format: [[modid, name, tier (1-8, must be string)], [modid, name, tier (1-8, must be string)]]: ");
        MIN_TIER_ORES = BUILDER.comment("Minimum Tier Ore Harvesters for given Ores.")
                .define("min_tier_ores", new ArrayList<>());
        MIN_TIER_BIOS = BUILDER.comment("Minimum Tier Bio Harvesters for given Bios.")
                .define("min_tier_bios", new ArrayList<>());
        MIN_TIER_STONES = BUILDER.comment("Minimum Tier Stone Harvesters for given Stones.")
                .define("min_tier_stones", new ArrayList<>());
        MIN_TIER_SOILS = BUILDER.comment("Minimum Tier Soil Harvesters for given Soils.")
                .define("min_tier_soils", new ArrayList<>());
        BUILDER.pop();

        BUILDER.push("Harvester Blacklists: ");
        ArrayList<ArrayList<String>> blacklist_ores = new ArrayList<>();
        BLACKLIST_ORES = BUILDER.comment("Blacklist for the Ore Harvesters")
                .define("blacklist_ores", blacklist_ores);
        ArrayList<ArrayList<String>> blacklist_bios = new ArrayList<>();
        BLACKLIST_BIOS = BUILDER.comment("Blacklist for the Bio Harvesters")
                .define("blacklist_bios", blacklist_bios);
        ArrayList<ArrayList<String>> blacklist_stones = new ArrayList<>();
        BLACKLIST_STONES = BUILDER.comment("Blacklist for the Stone Harvesters")
                .define("blacklist_stones", blacklist_stones);
        ArrayList<ArrayList<String>> blacklist_soils = new ArrayList<>();
        BLACKLIST_SOILS = BUILDER.comment("Blacklist for the Soil Harvesters")
                .define("blacklist_soils", blacklist_soils);
        ArrayList<ArrayList<String>> blacklist_loot = new ArrayList<>();
        BLACKLIST_LOOT = BUILDER.comment("Blacklist for the Loot Harvesters")
                .define("blacklist_loot", blacklist_loot);
        ArrayList<ArrayList<String>> blacklist_mobs = new ArrayList<>();
        BLACKLIST_MOBS = BUILDER.comment("Blacklist for the Mob Harvesters")
                .define("blacklist_mobs", blacklist_mobs);
        BUILDER.pop();

        BUILDER.push("Harvester Blacklists (by mod): ");
        ArrayList<String> blacklist_ores_mod = new ArrayList<>();
        BLACKLIST_ORES_MOD = BUILDER.comment("Blacklist for the Soil Harvesters (By mod)")
                .define("blacklist_ores_mod", blacklist_ores_mod);
        ArrayList<String> blacklist_bios_mod = new ArrayList<>();
        blacklist_bios_mod.add("botania");
        BLACKLIST_BIOS_MOD = BUILDER.comment("Blacklist for the Soil Harvesters (By mod)")
                .define("blacklist_bios_mod", blacklist_bios_mod);
        ArrayList<String> blacklist_stones_mod = new ArrayList<>();
        BLACKLIST_STONES_MOD = BUILDER.comment("Blacklist for the Soil Harvesters (By mod)")
                .define("blacklist_stones_mod", blacklist_stones_mod);
        ArrayList<String> blacklist_soils_mod = new ArrayList<>();
        BLACKLIST_SOILS_MOD = BUILDER.comment("Blacklist for the Soil Harvesters (By mod)")
                .define("blacklist_soils_mod", blacklist_soils_mod);
        ArrayList<String> blacklist_loot_mod = new ArrayList<>();
        BLACKLIST_LOOT_MOD = BUILDER.comment("Blacklist for the Loot Harvesters (By mod)")
                .define("blacklist_loot_mod", blacklist_loot_mod);
        ArrayList<String> blacklist_mobs_mod = new ArrayList<>();
        BLACKLIST_MOBS_MOD = BUILDER.comment("Blacklist for the Mob Harvesters (By mod)")
                .define("blacklist_mobs_mod", blacklist_mobs_mod);
        BUILDER.pop();

        BUILDER.push("Machine Speeds: ");

        HEAT_GENERATOR_SPEED = BUILDER.comment("Speed for the Heat Generator (in FE per tick)")
                .defineInRange("speed_heat_generator", 3, 0, Integer.MAX_VALUE);

        BUILDER.push("Ore Harvester Speeds: ");
        ORE_1_SPEED = BUILDER.comment("Speed for the tier 1 Ore Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_ore_1", 400, 0, Integer.MAX_VALUE);
        ORE_2_SPEED = BUILDER.comment("Speed for the tier 2 Ore Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_ore_2", 350, 0, Integer.MAX_VALUE);
        ORE_3_SPEED = BUILDER.comment("Speed for the tier 3 Ore Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_ore_3", 300, 0, Integer.MAX_VALUE);
        ORE_4_SPEED = BUILDER.comment("Speed for the tier 4 Ore Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_ore_4", 250, 0, Integer.MAX_VALUE);
        ORE_5_SPEED = BUILDER.comment("Speed for the tier 5 Ore Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_ore_5", 200, 0, Integer.MAX_VALUE);
        ORE_6_SPEED = BUILDER.comment("Speed for the tier 6 Ore Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_ore_6", 150, 0, Integer.MAX_VALUE);
        ORE_7_SPEED = BUILDER.comment("Speed for the tier 7 Ore Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_ore_7", 100, 0, Integer.MAX_VALUE);
        ORE_8_SPEED = BUILDER.comment("Speed for the tier 8 Ore Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_ore_8", 5, 0, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("Bio Harvester Speeds: ");
        BIO_1_SPEED = BUILDER.comment("Speed for the tier 1 Bio Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_bio_1", 400, 0, Integer.MAX_VALUE);
        BIO_2_SPEED = BUILDER.comment("Speed for the tier 2 Bio Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_bio_2", 350, 0, Integer.MAX_VALUE);
        BIO_3_SPEED = BUILDER.comment("Speed for the tier 3 Bio Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_bio_3", 300, 0, Integer.MAX_VALUE);
        BIO_4_SPEED = BUILDER.comment("Speed for the tier 4 Bio Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_bio_4", 250, 0, Integer.MAX_VALUE);
        BIO_5_SPEED = BUILDER.comment("Speed for the tier 5 Bio Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_bio_5", 200, 0, Integer.MAX_VALUE);
        BIO_6_SPEED = BUILDER.comment("Speed for the tier 6 Bio Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_bio_6", 150, 0, Integer.MAX_VALUE);
        BIO_7_SPEED = BUILDER.comment("Speed for the tier 7 Bio Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_bio_7", 100, 0, Integer.MAX_VALUE);
        BIO_8_SPEED = BUILDER.comment("Speed for the tier 8 Bio Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_bio_8", 5, 0, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("Stone Harvester Speeds: ");
        STONE_1_SPEED = BUILDER.comment("Speed for the tier 1 Stone Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_stone_1", 400, 0, Integer.MAX_VALUE);
        STONE_2_SPEED = BUILDER.comment("Speed for the tier 2 Stone Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_stone_2", 350, 0, Integer.MAX_VALUE);
        STONE_3_SPEED = BUILDER.comment("Speed for the tier 3 Stone Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_stone_3", 300, 0, Integer.MAX_VALUE);
        STONE_4_SPEED = BUILDER.comment("Speed for the tier 4 Stone Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_stone_4", 250, 0, Integer.MAX_VALUE);
        STONE_5_SPEED = BUILDER.comment("Speed for the tier 5 Stone Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_stone_5", 200, 0, Integer.MAX_VALUE);
        STONE_6_SPEED = BUILDER.comment("Speed for the tier 6 Stone Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_stone_6", 150, 0, Integer.MAX_VALUE);
        STONE_7_SPEED = BUILDER.comment("Speed for the tier 7 Stone Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_stone_7", 100, 0, Integer.MAX_VALUE);
        STONE_8_SPEED = BUILDER.comment("Speed for the tier 8 Stone Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_stone_8", 5, 0, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("Soil Harvester Speeds: ");
        SOIL_1_SPEED = BUILDER.comment("Speed for the tier 1 Soil Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_soil_1", 400, 0, Integer.MAX_VALUE);
        SOIL_2_SPEED = BUILDER.comment("Speed for the tier 2 Soil Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_soil_2", 350, 0, Integer.MAX_VALUE);
        SOIL_3_SPEED = BUILDER.comment("Speed for the tier 3 Soil Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_soil_3", 300, 0, Integer.MAX_VALUE);
        SOIL_4_SPEED = BUILDER.comment("Speed for the tier 4 Soil Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_soil_4", 250, 0, Integer.MAX_VALUE);
        SOIL_5_SPEED = BUILDER.comment("Speed for the tier 5 Soil Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_soil_5", 200, 0, Integer.MAX_VALUE);
        SOIL_6_SPEED = BUILDER.comment("Speed for the tier 6 Soil Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_soil_6", 150, 0, Integer.MAX_VALUE);
        SOIL_7_SPEED = BUILDER.comment("Speed for the tier 7 Soil Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_soil_7", 100, 0, Integer.MAX_VALUE);
        SOIL_8_SPEED = BUILDER.comment("Speed for the tier 8 Soil Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_soil_8", 5, 0, Integer.MAX_VALUE);
        BUILDER.pop();

        LOOT_SPEED = BUILDER.comment("Speed for the Loot Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("loot_speed", 50, 0, Integer.MAX_VALUE);

        BUILDER.push("Mob Harvester Speeds: ");
        DARK_MOB_SPEED = BUILDER.comment("Speed for the Dark Mob Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("dark_mob_speed", 50, 0, Integer.MAX_VALUE);
        SPECIFIC_MOB_SPEED = BUILDER.comment("Speed for the Specific Mob Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("specific_mob_speed", 50, 0, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.pop();

        BUILDER.push("Machine Prices: ");

        HARVESTER_CAPACITY_MULTIPLIER = BUILDER.comment("Amount to multiply the price of the harvesters by to set their energy capacity.")
                .defineInRange("harvester_capacity_multiplier", 100, 0, Integer.MAX_VALUE);
        DIMENSIONAL_APPLICATOR_CAPACITY_MULTIPLIER = BUILDER.comment("Amount to multiply the price of the Dimensional Applicator by to set its energy capacity.")
                .defineInRange("dimensional_applicator_capacity_multiplier", 100, 0, Integer.MAX_VALUE);
        HEAT_GENERATOR_CAPACITY_MULTIPLIER = BUILDER.comment("Amount to multiply the price of the Heat Generator by to set its energy capacity.")
                .defineInRange("heat_generator_capacity_multiplier", 100, 0, Integer.MAX_VALUE);

        BUILDER.push("Ore Harvester Prices: ");
        ORE_1_PRICE = BUILDER.comment("Price for the tier 1 Ore Harvester (in FE per operation)")
                .defineInRange("price_ore_1", 400, 0, Integer.MAX_VALUE);
        ORE_2_PRICE = BUILDER.comment("Price for the tier 2 Ore Harvester (in FE per operation)")
                .defineInRange("price_ore_2", 600, 0, Integer.MAX_VALUE);
        ORE_3_PRICE = BUILDER.comment("Price for the tier 3 Ore Harvester (in FE per operation)")
                .defineInRange("price_ore_3", 1000, 0, Integer.MAX_VALUE);
        ORE_4_PRICE = BUILDER.comment("Price for the tier 4 Ore Harvester (in FE per operation)")
                .defineInRange("price_ore_4", 1500, 0, Integer.MAX_VALUE);
        ORE_5_PRICE = BUILDER.comment("Price for the tier 5 Ore Harvester (in FE per operation)")
                .defineInRange("price_ore_5", 4000, 0, Integer.MAX_VALUE);
        ORE_6_PRICE = BUILDER.comment("Price for the tier 6 Ore Harvester (in FE per operation)")
                .defineInRange("price_ore_6", 7000, 0, Integer.MAX_VALUE);
        ORE_7_PRICE = BUILDER.comment("Price for the tier 7 Ore Harvester (in FE per operation)")
                .defineInRange("price_ore_7", 7500, 0, Integer.MAX_VALUE);
        ORE_8_PRICE = BUILDER.comment("Price for the tier 8 Ore Harvester (in FE per operation)")
                .defineInRange("price_ore_8", 8000, 0, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("Bio Harvester Prices: ");
        BIO_1_PRICE = BUILDER.comment("Price for the tier 1 Bio Harvester (in FE per operation)")
                .defineInRange("price_bio_1", 400, 0, Integer.MAX_VALUE);
        BIO_2_PRICE = BUILDER.comment("Price for the tier 2 Bio Harvester (in FE per operation)")
                .defineInRange("price_bio_2", 600, 0, Integer.MAX_VALUE);
        BIO_3_PRICE = BUILDER.comment("Price for the tier 3 Bio Harvester (in FE per operation)")
                .defineInRange("price_bio_3", 1000, 0, Integer.MAX_VALUE);
        BIO_4_PRICE = BUILDER.comment("Price for the tier 4 Bio Harvester (in FE per operation)")
                .defineInRange("price_bio_4", 1500, 0, Integer.MAX_VALUE);
        BIO_5_PRICE = BUILDER.comment("Price for the tier 5 Bio Harvester (in FE per operation)")
                .defineInRange("price_bio_5", 4000, 0, Integer.MAX_VALUE);
        BIO_6_PRICE = BUILDER.comment("Price for the tier 6 Bio Harvester (in FE per operation)")
                .defineInRange("price_bio_6", 7000, 0, Integer.MAX_VALUE);
        BIO_7_PRICE = BUILDER.comment("Price for the tier 7 Bio Harvester (in FE per operation)")
                .defineInRange("price_bio_7", 7500, 0, Integer.MAX_VALUE);
        BIO_8_PRICE = BUILDER.comment("Price for the tier 8 Bio Harvester (in FE per operation)")
                .defineInRange("price_bio_8", 8000, 0, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("Stone Harvester Prices: ");
        STONE_1_PRICE = BUILDER.comment("Price for the tier 1 Stone Harvester (in FE per operation)")
                .defineInRange("price_stone_1", 400, 0, Integer.MAX_VALUE);
        STONE_2_PRICE = BUILDER.comment("Price for the tier 2 Stone Harvester (in FE per operation)")
                .defineInRange("price_stone_2", 600, 0, Integer.MAX_VALUE);
        STONE_3_PRICE = BUILDER.comment("Price for the tier 3 Stone Harvester (in FE per operation)")
                .defineInRange("price_stone_3", 1000, 0, Integer.MAX_VALUE);
        STONE_4_PRICE = BUILDER.comment("Price for the tier 4 Stone Harvester (in FE per operation)")
                .defineInRange("price_stone_4", 1500, 0, Integer.MAX_VALUE);
        STONE_5_PRICE = BUILDER.comment("Price for the tier 5 Stone Harvester (in FE per operation)")
                .defineInRange("price_stone_5", 4000, 0, Integer.MAX_VALUE);
        STONE_6_PRICE = BUILDER.comment("Price for the tier 6 Stone Harvester (in FE per operation)")
                .defineInRange("price_stone_6", 7000, 0, Integer.MAX_VALUE);
        STONE_7_PRICE = BUILDER.comment("Price for the tier 7 Stone Harvester (in FE per operation)")
                .defineInRange("price_stone_7", 7500, 0, Integer.MAX_VALUE);
        STONE_8_PRICE = BUILDER.comment("Price for the tier 8 Stone Harvester (in FE per operation)")
                .defineInRange("price_stone_8", 8000, 0, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("Soil Harvester Prices: ");
        SOIL_1_PRICE = BUILDER.comment("Price for the tier 1 Soil Harvester (in FE per operation)")
                .defineInRange("price_soil_1", 400, 0, Integer.MAX_VALUE);
        SOIL_2_PRICE = BUILDER.comment("Price for the tier 2 Soil Harvester (in FE per operation)")
                .defineInRange("price_soil_2", 600, 0, Integer.MAX_VALUE);
        SOIL_3_PRICE = BUILDER.comment("Price for the tier 3 Soil Harvester (in FE per operation)")
                .defineInRange("price_soil_3", 1000, 0, Integer.MAX_VALUE);
        SOIL_4_PRICE = BUILDER.comment("Price for the tier 4 Soil Harvester (in FE per operation)")
                .defineInRange("price_soil_4", 1500, 0, Integer.MAX_VALUE);
        SOIL_5_PRICE = BUILDER.comment("Price for the tier 5 Soil Harvester (in FE per operation)")
                .defineInRange("price_soil_5", 4000, 0, Integer.MAX_VALUE);
        SOIL_6_PRICE = BUILDER.comment("Price for the tier 6 Soil Harvester (in FE per operation)")
                .defineInRange("price_soil_6", 7000, 0, Integer.MAX_VALUE);
        SOIL_7_PRICE = BUILDER.comment("Price for the tier 7 Soil Harvester (in FE per operation)")
                .defineInRange("price_soil_7", 7500, 0, Integer.MAX_VALUE);
        SOIL_8_PRICE = BUILDER.comment("Price for the tier 8 Soil Harvester (in FE per operation)")
                .defineInRange("price_soil_8", 8000, 0, Integer.MAX_VALUE);
        BUILDER.pop();

        DIMENSIONAL_APPLICATOR_PRICE = BUILDER.comment("Price for the Dimensional Applicator (in FE per operation)")
                .defineInRange("price_dimensional_applicator", 1000, 0, Integer.MAX_VALUE);

        LOOT_PRICE = BUILDER.comment("Price for the Loot Harvester (in FE per operation)")
                .defineInRange("loot_price", 8000, 0, Integer.MAX_VALUE);

        BUILDER.push("Mob Harvester Prices: ");
        DARK_MOB_PRICE = BUILDER.comment("Price for the Dark Mob Harvester (in FE per operation)")
                .defineInRange("dark_mob_price", 8000, 0, Integer.MAX_VALUE);
        SPECIFIC_MOB_PRICE = BUILDER.comment("Price for the Specific Mob Harvester (in FE per operation)")
                .defineInRange("specific_mob_price", 8000, 0, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.pop();

        BUILDER.push("Dimensional Applicator Stats: ");
        DIMENSIONAL_APPLICATOR_AMPLIFIER = BUILDER.comment("Amplifier for the Dimensional Applicator (The final value will be this * the number of Space Rippers.)")
                .defineInRange("amplifier_dimensional_applicator", 1, 0, Integer.MAX_VALUE);
        DIMENSIONAL_APPLICATOR_DURATION = BUILDER.comment("Duration for the Dimensional Applicator (in ticks, 20 = one second, 220 = 11 seconds (Night Vision flashes at 10 or less)).")
                .defineInRange("dimensional_applicator_duration", 240, 0, Integer.MAX_VALUE);
        DIMENSIONAL_APPLICATOR_DIVISOR = BUILDER.comment("How much to divide the Dimensional Applicator's duration by to get it's reapplication frequency (10 makes it every second if the duration is 220, just over if 240).")
                .comment("This can be less than one to make it take longer than the effect lasts (not recommended, though).")
                .defineInRange("dimensional_applicator_divisor", 10, 0, Double.MAX_VALUE);
        DIMENSIONAL_APPLICATOR_IS_BEACON_EFFECT = BUILDER.comment("Whether the game should think the effects came from a beacon.")
                .define("dimensional_applicator_is_beacon_effect", true);
        DIMENSIONAL_APPLICATOR_SHOW_PARTICLES = BUILDER.comment("Whether to show the particles of the effects.")
                .define("dimensional_applicator_show_particles", false);
        DIMENSIONAL_APPLICATOR_SHOW_ICON = BUILDER.comment("Whether to show the icons of the effects.")
                .define("dimensional_applicator_show_icon", false);
        BUILDER.pop();

        BUILDER.push("Enable / Disable: ");
        ENABLE_CHUNK_LOADER = BUILDER.comment("Should the Chunk Loader have functionality?")
                .define("enable_chunk_loader", true);
        ENABLE_DIMENSIONAL_APPLICATOR = BUILDER.comment("Should the Dimensional Applicator have functionality?")
                .define("enable_dimensional_applicator", true);
        ENABLE_HEAT_GENERATOR = BUILDER.comment("Should the Heat Generator have functionality?")
                .define("enable_heat_generator", true);
        ENABLE_ORE_HARVESTERS = BUILDER.comment("Should the Ore Harvesters have functionality?")
                .define("enable_ore_harvesters", true);
        ENABLE_BIO_HARVESTERS = BUILDER.comment("Should the Bio Harvesters have functionality?")
                .define("enable_bio_harvesters", true);
        ENABLE_STONE_HARVESTERS = BUILDER.comment("Should the Stone Harvesters have functionality?")
                .define("enable_stone_harvesters", true);
        ENABLE_SOIL_HARVESTERS = BUILDER.comment("Should the Soil Harvesters have functionality?")
                .define("enable_soil_harvesters", true);
        ENABLE_LOOT_HARVESTER = BUILDER.comment("Should the Loot Harvester have functionality?")
                .define("enable_loot_harvester", true);
        ENABLE_DARK_MOB_HARVESTER = BUILDER.comment("Should the Dark Mob Harvester have functionality?")
                .define("enable_dark_mob_harvester", true);
        ENABLE_SPECIFIC_MOB_HARVESTER = BUILDER.comment("Should the Specific Mob Harvester have functionality?")
                .define("enable_specific_mob_harvester", true);
        BUILDER.pop();

        BUILDER.push("Misc.: ");
        ArrayList<ArrayList<String>> valid_heat_sources = new ArrayList<>();

        mod = "minecraft";
        String heat_source = "magma_block";
        ArrayList<String> mod_heat_source = new ArrayList<>();
        mod_heat_source.add(mod);
        mod_heat_source.add(heat_source);
        valid_heat_sources.add(mod_heat_source);

        mod = "minecraft";
        heat_source = "lava";
        mod_heat_source = new ArrayList<>();
        mod_heat_source.add(mod);
        mod_heat_source.add(heat_source);
        valid_heat_sources.add(mod_heat_source);

        mod = "minecraft";
        heat_source = "fire";
        mod_heat_source = new ArrayList<>();
        mod_heat_source.add(mod);
        mod_heat_source.add(heat_source);
        valid_heat_sources.add(mod_heat_source);
        
        VALID_HEAT_SOURCES = BUILDER.comment("Valid heat sources for the Heat Generator.")
                .define("valid_heat_sources", valid_heat_sources);

        BLOCK_HARDNESS = BUILDER.comment("Hardness for the blocks (Iron Block is 5.)")
                .defineInRange("block_hardness", 5.0f, 0, Float.MAX_VALUE);
        BLOCK_RESISTANCE = BUILDER.comment("Resistance for the blocks (Iron Block is 6.)")
                .defineInRange("block_resistance", 6.0f, 0, Float.MAX_VALUE);
        HARVESTER_SHARD_CHANCE = BUILDER.comment("Chance for a harvester to output a shard (the higher the value, the less likely the shard, 0 means no shards.)")
                .defineInRange("harvester_shard_chance", 75, 0, Integer.MAX_VALUE);
        MOB_HARVESTER_MOB_SHARD_CHANCE = BUILDER.comment("Chance for a Mob Harvester to output a Mob Shard (the higher the value, the less likely the shard, 0 means no shards.)")
                .defineInRange("mob_harvester_mob_shard_chance", 25, 0, Integer.MAX_VALUE);
        MACHINE_LIGHT_LEVEL = BUILDER.comment("Light level for the machines to emit when active (a regular torch is 14, 0 to disable).")
                .defineInRange("machine_light_level", 7, 0, Integer.MAX_VALUE);
        KEY_BREAK_SPEED_MULTIPLIER = BUILDER.comment("Multiplier for the break speed of the keys when configuring in that way (they won't actually break blocks).")
                .defineInRange("key_break_speed_multiplier", 10, 0, Integer.MAX_VALUE);
        BUILDER.pop();
    }
    public static final ForgeConfigSpec CONFIG = BUILDER.build();
}
