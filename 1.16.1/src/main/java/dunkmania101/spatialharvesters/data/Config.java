package dunkmania101.spatialharvesters.data;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {
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

    public static ForgeConfigSpec.IntValue HEAT_GENERATOR_SPEED;

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
        BUILDER.push("Config:").push("Common settings");
        setup(BUILDER);
        BUILDER.pop();
    }

    private static void setup(ForgeConfigSpec.Builder BUILDER) {
        BUILDER.comment("Ore Harvesters:").push("Ore Harvesters (Speed)");
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

        BUILDER.comment("Ore Harvesters:").push("Bio Harvesters (Speed)");
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

        BUILDER.comment("Stone Harvesters:").push("Stone Harvesters (Speed)");
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

        BUILDER.comment("Ore Harvesters:").push("Ore Harvesters (Price)");
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
                .defineInRange("price_ore_16", 7000, 0, Integer.MAX_VALUE);
        ORE_7_PRICE = BUILDER.comment("Price for the tier 7 Ore Harvester (in FE per operation)")
                .defineInRange("price_ore_7", 7500, 0, Integer.MAX_VALUE);
        ORE_8_PRICE = BUILDER.comment("Price for the tier 8 Ore Harvester (in FE per operation)")
                .defineInRange("price_ore_8", 8000, 0, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.comment("Ore Harvesters:").push("Bio Harvesters (Price)");
        BIO_1_PRICE = BUILDER.comment("Price for the tier 1 Bio Harvester (in FE per operation)")
                .defineInRange("price_bio_1", 400, 0, Integer.MAX_VALUE);
        BIO_2_PRICE = BUILDER.comment("Price for the tier 2 Bio Harvester (in FE per operation)")
                .defineInRange("price_bio_2", 350, 0, Integer.MAX_VALUE);
        BIO_3_PRICE = BUILDER.comment("Price for the tier 3 Bio Harvester (in FE per operation)")
                .defineInRange("price_bio_3", 300, 0, Integer.MAX_VALUE);
        BIO_4_PRICE = BUILDER.comment("Price for the tier 4 Bio Harvester (in FE per operation)")
                .defineInRange("price_bio_4", 250, 0, Integer.MAX_VALUE);
        BIO_5_PRICE = BUILDER.comment("Price for the tier 5 Bio Harvester (in FE per operation)")
                .defineInRange("price_bio_5", 200, 0, Integer.MAX_VALUE);
        BIO_6_PRICE = BUILDER.comment("Price for the tier 6 Bio Harvester (in FE per operation)")
                .defineInRange("price_bio_6", 150, 0, Integer.MAX_VALUE);
        BIO_7_PRICE = BUILDER.comment("Price for the tier 7 Bio Harvester (in FE per operation)")
                .defineInRange("price_bio_7", 100, 0, Integer.MAX_VALUE);
        BIO_8_PRICE = BUILDER.comment("Price for the tier 8 Bio Harvester (in FE per operation)")
                .defineInRange("price_bio_8", 8000, 0, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.comment("Stone Harvesters:").push("Stone Harvesters (Price)");
        STONE_1_PRICE = BUILDER.comment("Price for the tier 1 Stone Harvester (in FE per operation)")
                .defineInRange("price_stone_1", 400, 0, Integer.MAX_VALUE);
        STONE_2_PRICE = BUILDER.comment("Price for the tier 2 Stone Harvester (in FE per operation)")
                .defineInRange("price_stone_2", 350, 0, Integer.MAX_VALUE);
        STONE_3_PRICE = BUILDER.comment("Price for the tier 3 Stone Harvester (in FE per operation)")
                .defineInRange("price_stone_3", 300, 0, Integer.MAX_VALUE);
        STONE_4_PRICE = BUILDER.comment("Price for the tier 4 Stone Harvester (in FE per operation)")
                .defineInRange("price_stone_4", 250, 0, Integer.MAX_VALUE);
        STONE_5_PRICE = BUILDER.comment("Price for the tier 5 Stone Harvester (in FE per operation)")
                .defineInRange("price_stone_5", 200, 0, Integer.MAX_VALUE);
        STONE_6_PRICE = BUILDER.comment("Price for the tier 6 Stone Harvester (in FE per operation)")
                .defineInRange("price_stone_6", 150, 0, Integer.MAX_VALUE);
        STONE_7_PRICE = BUILDER.comment("Price for the tier 7 Stone Harvester (in FE per operation)")
                .defineInRange("price_stone_7", 100, 0, Integer.MAX_VALUE);
        STONE_8_PRICE = BUILDER.comment("Price for the tier 8 Stone Harvester (in FE per operation)")
                .defineInRange("price_stone_8", 8000, 0, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.comment("Heat Generator:").push("Heat Generator (Speed)");
        HEAT_GENERATOR_SPEED = BUILDER.comment("Speed for the Heat Generator (in FE per tick)")
                .defineInRange("speed_heat_generator", 10, 0, Integer.MAX_VALUE);
        BUILDER.pop();
    }
    public static final ForgeConfigSpec CONFIG = BUILDER.build();
}
