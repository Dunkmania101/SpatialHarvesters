package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.objects.tile_entities.BioHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.DarkMobHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.DimensionalApplicatorTE;
import dunkmania101.spatialharvesters.objects.tile_entities.HeatGeneratorTE;
import dunkmania101.spatialharvesters.objects.tile_entities.LootHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.OreHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.SoilHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.SpecificMobHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.StoneHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.base.CustomEnergyMachineTE;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import team.reborn.energy.api.EnergyStorage;

public class BlockEntityInit implements ModInitializer {
        public static final BlockEntityType<OreHarvesterTE> ORE_HARVESTER = registerBlockEntityType(OreHarvesterTE::new,
                        "ore_harvester", BlockInit.ORE_HARVESTER_1, BlockInit.ORE_HARVESTER_2,
                        BlockInit.ORE_HARVESTER_3, BlockInit.ORE_HARVESTER_4, BlockInit.ORE_HARVESTER_5,
                        BlockInit.ORE_HARVESTER_6, BlockInit.ORE_HARVESTER_7, BlockInit.ORE_HARVESTER_8);

        public static final BlockEntityType<BioHarvesterTE> BIO_HARVESTER = registerBlockEntityType(BioHarvesterTE::new,
                        "bio_harvester", BlockInit.BIO_HARVESTER_1, BlockInit.BIO_HARVESTER_2,
                        BlockInit.BIO_HARVESTER_3, BlockInit.BIO_HARVESTER_4, BlockInit.BIO_HARVESTER_5,
                        BlockInit.BIO_HARVESTER_6, BlockInit.BIO_HARVESTER_7, BlockInit.BIO_HARVESTER_8);

        public static final BlockEntityType<StoneHarvesterTE> STONE_HARVESTER = registerBlockEntityType(
                        StoneHarvesterTE::new, "stone_harvester", BlockInit.STONE_HARVESTER_1,
                        BlockInit.STONE_HARVESTER_2, BlockInit.STONE_HARVESTER_3, BlockInit.STONE_HARVESTER_4,
                        BlockInit.STONE_HARVESTER_5, BlockInit.STONE_HARVESTER_6, BlockInit.STONE_HARVESTER_7,
                        BlockInit.STONE_HARVESTER_8);

        public static final BlockEntityType<SoilHarvesterTE> SOIL_HARVESTER = registerBlockEntityType(
                        SoilHarvesterTE::new, "soil_harvester", BlockInit.SOIL_HARVESTER_1, BlockInit.SOIL_HARVESTER_2,
                        BlockInit.SOIL_HARVESTER_3, BlockInit.SOIL_HARVESTER_4, BlockInit.SOIL_HARVESTER_5,
                        BlockInit.SOIL_HARVESTER_6, BlockInit.SOIL_HARVESTER_7, BlockInit.SOIL_HARVESTER_8);

        public static final BlockEntityType<LootHarvesterTE> LOOT_HARVESTER = registerBlockEntityType(
                        LootHarvesterTE::new, "loot_harvester", BlockInit.LOOT_HARVESTER);

        public static final BlockEntityType<SpecificMobHarvesterTE> SPECIFIC_MOB_HARVESTER = registerBlockEntityType(
                        SpecificMobHarvesterTE::new, "specific_mob_harvester", BlockInit.SPECIFIC_MOB_HARVESTER);

        public static final BlockEntityType<DarkMobHarvesterTE> DARK_MOB_HARVESTER = registerBlockEntityType(
                        DarkMobHarvesterTE::new, "dark_mob_harvester", BlockInit.DARK_MOB_HARVESTER);

        public static final BlockEntityType<HeatGeneratorTE> HEAT_GENERATOR = registerBlockEntityType(
                        HeatGeneratorTE::new, "heat_generator", BlockInit.HEAT_GENERATOR);

        public static final BlockEntityType<DimensionalApplicatorTE> DIMENSIONAL_APPLICATOR = registerBlockEntityType(
                        DimensionalApplicatorTE::new, "dimensional_applicator", BlockInit.DIMENSIONAL_APPLICATOR);

        public static <E extends BlockEntityType<?>> E registerBlockEntityType(
                        FabricBlockEntityTypeBuilder.Factory<? extends BlockEntity> factory, String name,
                        Block... blocks) {
                E blockEntityType = (E) FabricBlockEntityTypeBuilder.create(factory, blocks).build(null);
                Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(SpatialHarvesters.modid, name),
                                blockEntityType);
                if (factory.create(BlockPos.ORIGIN, blocks[0].getDefaultState()) instanceof CustomEnergyMachineTE) {
                        EnergyStorage.SIDED.registerSelf(blockEntityType);
                }
                return blockEntityType;
        }

        @Override
        public void onInitialize() {
        }
}
