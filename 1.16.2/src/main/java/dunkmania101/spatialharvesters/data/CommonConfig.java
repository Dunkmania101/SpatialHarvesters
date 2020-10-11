package dunkmania101.spatialharvesters.data;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;
import java.util.ArrayList;

@Mod.EventBusSubscriber
public class CommonConfig {
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> CUSTOM_ORES;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> CUSTOM_BIOS;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> CUSTOM_STONES;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> CUSTOM_SOILS;

    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> BLACKLIST_ORES;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> BLACKLIST_BIOS;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> BLACKLIST_STONES;
    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> BLACKLIST_SOILS;

    public static ForgeConfigSpec.ConfigValue<ArrayList<String>> BLACKLIST_ORES_MOD;
    public static ForgeConfigSpec.ConfigValue<ArrayList<String>> BLACKLIST_BIOS_MOD;
    public static ForgeConfigSpec.ConfigValue<ArrayList<String>> BLACKLIST_STONES_MOD;
    public static ForgeConfigSpec.ConfigValue<ArrayList<String>> BLACKLIST_SOILS_MOD;

    public static ForgeConfigSpec.ConfigValue<ArrayList<ArrayList<String>>> BLACKLIST_MOBS;
    public static ForgeConfigSpec.ConfigValue<ArrayList<String>> BLACKLIST_MOBS_MOD;

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

    public static ForgeConfigSpec.IntValue MOB_SPEED;

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

    public static ForgeConfigSpec.IntValue MOB_PRICE;

    public static ForgeConfigSpec.IntValue HEAT_GENERATOR_SPEED;

    public static ForgeConfigSpec.IntValue DIMENSIONAL_APPLICATOR_PRICE;
    public static ForgeConfigSpec.IntValue DIMENSIONAL_APPLICATOR_AMPLIFIER;
    public static ForgeConfigSpec.IntValue DIMENSIONAL_APPLICATOR_DURATION;

    public static ForgeConfigSpec.DoubleValue BLOCK_HARDNESS;
    public static ForgeConfigSpec.DoubleValue BLOCK_RESISTANCE;

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static void init(Path file) {
        final CommentedFileConfig data = CommentedFileConfig.builder(file)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        data.load();
        CONFIG.setConfig(data);
    }

    static {
        BUILDER.push("Common Config:");
        setup();
        BUILDER.pop();
    }

    private static void setup() {
        ArrayList<ArrayList<String>> ores = new ArrayList<>();

        String mod = "appliedenergistics2";
        String ore = "quartz_ore";
        ArrayList<String> oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "appliedenergistics2";
        ore = "charged_quartz_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "rftoolsbase";
        ore = "dimensionalshard_overworld";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "rftoolsbase";

        ore = "dimensionalshard_nether";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "rftoolsbase";
        ore = "dimensionalshard_end";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "rhodonite";
        ore = "block_ore_fluorite";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "rhodonite";
        ore = "block_ore_rhodonite";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "exp_ore";
        ore = "block_exp_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "rockcandy";
        ore = "candy_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "lightestlamp";
        ore = "boron_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "zombie_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "blaze_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "ghast_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "magma_cube_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "wither_skeleton_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "zombie_pigman_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "salmon_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "squid_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "cod_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "sheep_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "rabbit_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "pig_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "cow_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "chicken_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "bat_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "witch_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "spider_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "slime_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "phantom_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "skeleton_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "enderman_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "guardian_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "pufferfish_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "funores";
        ore = "creeper_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "gobber2";
        ore = "gobber2_ore";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "gobber2";
        ore = "gobber2_ore_nether";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        mod = "gobber2";
        ore = "gobber2_ore_end";
        oreMod = new ArrayList<>();
        oreMod.add(mod);
        oreMod.add(ore);
        ores.add(oreMod);

        CUSTOM_ORES = BUILDER.comment("Custom outputs for the Ore Harvester.")
                .define("custom_ores", ores);
        ArrayList<ArrayList<String>> custom_plants = new ArrayList<>();
        CUSTOM_BIOS = BUILDER.comment("Custom outputs for the Bio Harvester.")
                .define("custom_plants", custom_plants);
        ArrayList<ArrayList<String>> custom_stones = new ArrayList<>();
        CUSTOM_STONES = BUILDER.comment("Custom outputs for the Stone Harvester.")
                .define("custom_stones", custom_stones);
        ArrayList<ArrayList<String>> custom_soils = new ArrayList<>();
        CUSTOM_SOILS = BUILDER.comment("Custom outputs for the Soil Harvester.")
                .define("custom_soils", custom_soils);

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
        ArrayList<ArrayList<String>> blacklist_mobs = new ArrayList<>();
        BLACKLIST_MOBS = BUILDER.comment("Blacklist for the Mob Harvesters")
                .define("blacklist_mobs", blacklist_mobs);

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
        ArrayList<String> blacklist_mobs_mod = new ArrayList<>();
        BLACKLIST_MOBS_MOD = BUILDER.comment("Blacklist for the Mob Harvesters (By mod)")
                .define("blacklist_mobs_mod", blacklist_mobs_mod);

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

        MOB_SPEED = BUILDER.comment("Speed for the Mob Harvester (in ticks, there are 20 in a second. The lower this value, the faster.)")
                .defineInRange("speed_mob", 50, 0, Integer.MAX_VALUE);

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

        MOB_PRICE = BUILDER.comment("Price for the Mob Harvester (in FE per operation)")
                .defineInRange("price_mob", 8000, 0, Integer.MAX_VALUE);

        DIMENSIONAL_APPLICATOR_PRICE = BUILDER.comment("Price for the Dimensional Applicator (in FE per operation)")
                .defineInRange("price_dimensional_applicator", 1000, 0, Integer.MAX_VALUE);
        DIMENSIONAL_APPLICATOR_AMPLIFIER = BUILDER.comment("Amplifier for the Dimensional Applicator (The final value will be this + 1.)")
                .defineInRange("amplifier_dimensional_applicator", 1, 0, Integer.MAX_VALUE);
        DIMENSIONAL_APPLICATOR_DURATION = BUILDER.comment("Duration for the Dimensional Applicator (in ticks.)")
                .defineInRange("dimensional_applicator_duration", 220, 0, Integer.MAX_VALUE);

        HEAT_GENERATOR_SPEED = BUILDER.comment("Speed for the Heat Generator (in FE per tick)")
                .defineInRange("speed_heat_generator", 3, 0, Integer.MAX_VALUE);

        BLOCK_HARDNESS = BUILDER.comment("Hardness for the blocks (Iron Block is 5.)")
                .defineInRange("block_hardness", 5.0f, 0, Float.MAX_VALUE);
        BLOCK_RESISTANCE = BUILDER.comment("Resistance for the blocks (Iron Block is 6.)")
                .defineInRange("block_resistance", 6.0f, 0, Float.MAX_VALUE);
    }
    public static final ForgeConfigSpec CONFIG = BUILDER.build();
}
