package dunkmania101.spatialharvesters.data;

import java.util.ArrayList;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import me.lortseam.completeconfig.api.ConfigEntries;
import me.lortseam.completeconfig.data.Config;

@ConfigEntries(includeAll = true)
public class CommonConfig extends Config {
    public CommonConfig() {
        super(SpatialHarvesters.modid);
    }


    public static ArrayList<ArrayList<String>> custom_ores = new ArrayList<>();
    static {
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

        mod = "routerreborn";
        ore = "copper_ore";
        modOre = new ArrayList<>();
        modOre.add(mod);
        modOre.add(ore);
        custom_ores.add(modOre);
    }

    public static ArrayList<ArrayList<String>> custom_bios = new ArrayList<>();
    public static ArrayList<ArrayList<String>> custom_stones = new ArrayList<>();
    public static ArrayList<ArrayList<String>> custom_soils = new ArrayList<>();
    static {
        String mod = "minecraft";
        String soil = "clay";
        ArrayList<String> modSoil = new ArrayList<>();
        modSoil.add(mod);
        modSoil.add(soil);
        custom_soils.add(modSoil);
    }

    public static ArrayList<ArrayList<String>> custom_loot_tables = new ArrayList<>();
    static {
        String mod = "minecraft";
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

    }

    public static ArrayList<ArrayList<ArrayList<String>>> custom_mob_drops = new ArrayList<>();
    static {
        ArrayList<ArrayList<String>> modMobDrop = new ArrayList<>();
        String mod = "minecraft";
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
    }

    public static ArrayList<ArrayList<String>> custom_ore_tags = new ArrayList<>();
    static {
        ArrayList<String> modTag = new ArrayList<>();
        String mod = "c";
        String tag = "ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "bauxite_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "cinnabar_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "coal_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "diamond_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "emerald_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "galena_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "gold_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "iridium_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "iron_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "lapis_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "lead_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "peridot_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "pyrite_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "redstone_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "ruby_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "sheldonite_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "silver_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "sodalite_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "sphalerite_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "tin_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "tungsten_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);

        modTag = new ArrayList<>();
        mod = "c";
        tag = "nikolite_ores";
        modTag.add(mod);
        modTag.add(tag);
        custom_ore_tags.add(modTag);
    }

    public static ArrayList<ArrayList<String>> custom_bio_tags = new ArrayList<>();
    static {
        ArrayList<String> modTag = new ArrayList<>();
        String mod = "c";
        String tag = "veggies";
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "c";
        tag = "vegetables";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "c";
        tag = "leather";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "c";
        tag = "feathers";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "c";
        tag = "seeds";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "c";
        tag = "dyes";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "c";
        tag = "bones";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_bio_tags.add(modTag);

        mod = "c";
        tag = "wooden_rods";
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
    }

    public static ArrayList<ArrayList<String>> custom_stone_tags = new ArrayList<>();
    static {
        ArrayList<String> modTag = new ArrayList<>();
        String mod = "c";
        String tag = "cobblestone";
        modTag.add(mod);
        modTag.add(tag);
        custom_stone_tags.add(modTag);

        mod = "c";
        tag = "sandstone";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_stone_tags.add(modTag);

        mod = "c";
        tag = "end_stones";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_stone_tags.add(modTag);

        mod = "c";
        tag = "netherrack";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_stone_tags.add(modTag);
    }

    public static ArrayList<ArrayList<String>> custom_soil_tags = new ArrayList<>();
    static {
        ArrayList<String> modTag = new ArrayList<>();
        String mod = "c";
        String tag = "dirt";
        modTag.add(mod);
        modTag.add(tag);
        custom_soil_tags.add(modTag);

        mod = "c";
        tag = "sand";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_soil_tags.add(modTag);

        mod = "c";
        tag = "gravel";
        modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        custom_soil_tags.add(modTag);
    }

    public static ArrayList<ArrayList<String>> blacklist_ores = new ArrayList<>();
    public static ArrayList<ArrayList<String>> blacklist_bios = new ArrayList<>();
    public static ArrayList<ArrayList<String>> blacklist_stones = new ArrayList<>();
    public static ArrayList<ArrayList<String>> blacklist_soils = new ArrayList<>();
    public static ArrayList<ArrayList<String>> blacklist_loot = new ArrayList<>();
    public static ArrayList<ArrayList<String>> blacklist_mobs = new ArrayList<>();

    public static ArrayList<ArrayList<String>> blacklist_ores_tag = new ArrayList<>();
    public static ArrayList<ArrayList<String>> blacklist_bios_tag = new ArrayList<>();
    static {
        String mod = "botania";
        String tag = "special_flowers";
        ArrayList<String> modTag = new ArrayList<>();
        modTag.add(mod);
        modTag.add(tag);
        blacklist_bios_tag.add(modTag);
    }
    public static ArrayList<ArrayList<String>> blacklist_stones_tag = new ArrayList<>();
    public static ArrayList<ArrayList<String>> blacklist_soils_tag = new ArrayList<>();

    public static ArrayList<String> blacklist_ores_mod = new ArrayList<>();
    public static ArrayList<String> blacklist_bios_mod = new ArrayList<>();
    public static ArrayList<String> blacklist_stones_mod = new ArrayList<>();
    public static ArrayList<String> blacklist_soils_mod = new ArrayList<>();
    public static ArrayList<String> blacklist_loot_mod = new ArrayList<>();
    public static ArrayList<String> blacklist_mobs_mod = new ArrayList<>();

    public static ArrayList<ArrayList<String>> min_tier_ores = new ArrayList<>();
    public static ArrayList<ArrayList<String>> min_tier_bios = new ArrayList<>();
    public static ArrayList<ArrayList<String>> min_tier_stones = new ArrayList<>();
    public static ArrayList<ArrayList<String>> min_tier_soils = new ArrayList<>();

    public static int speed_ore_1 = 400;
    public static int speed_ore_2 = 350;
    public static int speed_ore_3 = 300;
    public static int speed_ore_4 = 250;
    public static int speed_ore_5 = 200;
    public static int speed_ore_6 = 150;
    public static int speed_ore_7 = 100;
    public static int speed_ore_8 = 5;

    public static int speed_bio_1 = 400;
    public static int speed_bio_2 = 350;
    public static int speed_bio_3 = 300;
    public static int speed_bio_4 = 250;
    public static int speed_bio_5 = 200;
    public static int speed_bio_6 = 150;
    public static int speed_bio_7 = 100;
    public static int speed_bio_8 = 5;

    public static int speed_stone_1 = 400;
    public static int speed_stone_2 = 350;
    public static int speed_stone_3 = 300;
    public static int speed_stone_4 = 250;
    public static int speed_stone_5 = 200;
    public static int speed_stone_6 = 150;
    public static int speed_stone_7 = 100;
    public static int speed_stone_8 = 5;

    public static int speed_soil_1 = 400;
    public static int speed_soil_2 = 350;
    public static int speed_soil_3 = 300;
    public static int speed_soil_4 = 250;
    public static int speed_soil_5 = 200;
    public static int speed_soil_6 = 150;
    public static int speed_soil_7 = 100;
    public static int speed_soil_8 = 5;

    public static int speed_loot = 50;

    public static int speed_dark_mob = 50;
    public static int speed_specific_mob = 50;

    public static int speed_heat_generator = 10;

    public static ArrayList<ArrayList<String>> valid_heat_sources = new ArrayList<>();
    static {
        String mod = "minecraft";
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
    }

    public static int harvester_capacity_multiplier = 100;
    public static int dimensional_applicator_capacity_multiplier = 100;
    public static int heat_generator_capacity_multiplier = 100;

    public static long price_ore_1 = 400;
    public static long price_ore_2 = 600;
    public static long price_ore_3 = 1000;
    public static long price_ore_4 = 1500;
    public static long price_ore_5 = 4000;
    public static long price_ore_6 = 7000;
    public static long price_ore_7 = 7500;
    public static long price_ore_8 = 8000;

    public static long price_bio_1 = 400;
    public static long price_bio_2 = 600;
    public static long price_bio_3 = 1000;
    public static long price_bio_4 = 1500;
    public static long price_bio_5 = 4000;
    public static long price_bio_6 = 7000;
    public static long price_bio_7 = 7500;
    public static long price_bio_8 = 8000;

    public static long price_stone_1 = 400;
    public static long price_stone_2 = 600;
    public static long price_stone_3 = 1000;
    public static long price_stone_4 = 1500;
    public static long price_stone_5 = 4000;
    public static long price_stone_6 = 7000;
    public static long price_stone_7 = 7500;
    public static long price_stone_8 = 8000;

    public static long price_soil_1 = 400;
    public static long price_soil_2 = 600;
    public static long price_soil_3 = 1000;
    public static long price_soil_4 = 1500;
    public static long price_soil_5 = 4000;
    public static long price_soil_6 = 7000;
    public static long price_soil_7 = 7500;
    public static long price_soil_8 = 8000;

    public static long dimensional_applicator_price = 1000;

    public static long price_loot = 8000;

    public static long price_dark_mob = 8000;
    public static long price_specific_mob = 8000;

    public static int dimensional_applicator_amplifier = 1;
    public static int dimensional_applicator_duration = 240;
    public static double dimensional_applicator_divisor = 20;

    public static boolean dimensional_applicator_is_beacon_effect = true;
    public static boolean dimensional_applicator_show_particles = false;
    public static boolean dimensional_applicator_show_icon = false;

    public static boolean enable_chunk_loader = true;
    public static boolean enable_dimensional_applicator = true;
    public static boolean enable_heat_generator = true;
    public static boolean enable_ore_harvesters = true;
    public static boolean enable_bio_harvesters = true;
    public static boolean enable_stone_harvesters = true;
    public static boolean enable_soil_harvesters = true;
    public static boolean enable_dark_mob_harvester = true;
    public static boolean enable_specific_mob_harvester = true;
    public static boolean enable_loot_harvester = true;

    public static float block_hardness = 5;
    public static float block_resistance = 6;
    public static int harvester_shard_chance = 75;
    public static int mob_harvester_mob_shard_chance = 25;
    public static int machine_light_level = 7;
    public static int key_break_speed_multiplier = 10;
}
