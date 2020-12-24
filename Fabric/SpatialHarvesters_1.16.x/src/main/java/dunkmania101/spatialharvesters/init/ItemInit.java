package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.objects.blocks.block_items.MachineBlockItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit implements ModInitializer {
    // BlockItems
    public static final MachineBlockItem ORE_HARVESTER_1 = registerItem(
            new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_1,
                    new FabricItemSettings()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP)
            ),
            "ore_harvester_1"
    );

    public static final MachineBlockItem ORE_HARVESTER_2 = registerItem(
            new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_2,
                    new FabricItemSettings()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP)
            ),
            "ore_harvester_2"
    );

    public static final MachineBlockItem ORE_HARVESTER_3 = registerItem(
            new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_3,
                    new FabricItemSettings()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP)
            ),
            "ore_harvester_3"
    );

    public static final MachineBlockItem ORE_HARVESTER_4 = registerItem(
            new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_4,
                    new FabricItemSettings()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP)
            ),
            "ore_harvester_4"
    );

    public static final MachineBlockItem ORE_HARVESTER_5 = registerItem(
            new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_5,
                    new FabricItemSettings()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP)
            ),
            "ore_harvester_5"
    );

    public static final MachineBlockItem ORE_HARVESTER_6 = registerItem(
            new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_6,
                    new FabricItemSettings()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP)
            ),
            "ore_harvester_6"
    );

    public static final MachineBlockItem ORE_HARVESTER_7 = registerItem(
            new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_7,
                    new FabricItemSettings()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP)
            ),
            "ore_harvester_7"
    );

    public static final MachineBlockItem ORE_HARVESTER_8 = registerItem(
            new MachineBlockItem(
                    BlockInit.ORE_HARVESTER_8,
                    new FabricItemSettings()
                            .group(SpatialHarvesters.SPATIAL_HARVESTERS_GROUP)
            ),
            "ore_harvester_8"
    );

    public static <I extends Item> I registerItem(I item, String name) {
        Registry.register(Registry.ITEM, new Identifier(SpatialHarvesters.modid, name), item);
        return item;
    }

    @Override
    public void onInitialize() {
    }
}
