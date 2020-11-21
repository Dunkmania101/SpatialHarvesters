package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.objects.tile_entities.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TileEntityInit {
    public static final BlockEntityType<OreHarvesterTE> ORE_HARVESTER = registerBlockEntityType(
            BlockEntityType.Builder.create(OreHarvesterTE::new, BlockInit.ORE_HARVESTER).build(null),
            "ore_harvester"
    );

    public static final BlockEntityType<BioHarvesterTE> BIO_HARVESTER = registerBlockEntityType(
            BlockEntityType.Builder.create(BioHarvesterTE::new, BlockInit.BIO_HARVESTER).build(null),
            "bio_harvester"
    );

    public static final BlockEntityType<StoneHarvesterTE> STONE_HARVESTER = registerBlockEntityType(
            BlockEntityType.Builder.create(StoneHarvesterTE::new, BlockInit.BIO_HARVESTER).build(null),
            "stone_harvester"
    );

    public static final BlockEntityType<SoilHarvesterTE> SOIL_HARVESTER = registerBlockEntityType(
            BlockEntityType.Builder.create(SoilHarvesterTE::new, BlockInit.BIO_HARVESTER).build(null),
            "soil_harvester"
    );

    public static final BlockEntityType<SpecificMobHarvesterTE> SPECIFIC_MOB_HARVESTER = registerBlockEntityType(
            BlockEntityType.Builder.create(SpecificMobHarvesterTE::new, BlockInit.BIO_HARVESTER).build(null),
            "specific_mob_harvester"
    );

    public static final BlockEntityType<DarkMobHarvesterTE> DARK_MOB_HARVESTER = registerBlockEntityType(
            BlockEntityType.Builder.create(DarkMobHarvesterTE::new, BlockInit.BIO_HARVESTER).build(null),
            "dark_mob_harvester"
    );

    public static <E extends BlockEntityType<?>> E registerBlockEntityType(E blockEntityType, String name) {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(SpatialHarvesters.modid, name), blockEntityType);
        return blockEntityType;
    }
}
