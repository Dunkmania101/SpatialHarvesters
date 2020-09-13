package dunkmania101.spatialharvesters.util;

import dunkmania101.spatialharvesters.data.CommonConfig;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;

public class Tools {
    public static int checkChunkBlocks(World worldIn, ChunkPos cpos, Block blockIn) {
        int count = 0;
        int height = worldIn.getChunk(cpos.asBlockPos()).getHeight();
        for (BlockPos check_pos : BlockPos.getAllInBoxMutable(cpos.getXStart(), 0, cpos.getZStart(), cpos.getXEnd(), height, cpos.getZEnd())) {
            if (worldIn.getBlockState(check_pos).getBlock() == blockIn.getBlock()) {
                count++;
            }
        }
        return count;
    }

    public static ArrayList<Item> loadedOres = getLoadedOres();
    public static ArrayList<Item> getLoadedOres() {
        ArrayList<Item> ORES = new ArrayList<>();
        ArrayList<ArrayList<String>> config_ores = CommonConfig.CUSTOM_ORES.get();
        ArrayList<ArrayList<String>> blacklist_ores = CommonConfig.BLACKLIST_ORES.get();

        for (Item check_item : ForgeRegistries.ITEMS.getValues()) {
            ResourceLocation item_rn = check_item.getRegistryName();
            if (item_rn != null) {
                ArrayList<String> modOre = new ArrayList<>();
                modOre.add(item_rn.getNamespace());
                modOre.add(item_rn.getPath());
                if (!blacklist_ores.contains(modOre)) {
                    if (check_item.isIn(Tags.Items.ORES) || config_ores.contains(modOre)) {
                        ORES.add(check_item);
                    }
                }
            }
        }
        return ORES;
    }

    public static ArrayList<Item> loadedPlantsAndDyes = getLoadedPlantsAndDyes();
    public static ArrayList<Item> getLoadedPlantsAndDyes() {
        ArrayList<Item> PLANTS_DYES = new ArrayList<>();
        ArrayList<ArrayList<String>> config_plants = CommonConfig.CUSTOM_PLANTS.get();
        ArrayList<ArrayList<String>> blacklist_plants = CommonConfig.BLACKLIST_PLANTS.get();
        for (Item check_item : ForgeRegistries.ITEMS.getValues()) {
            ResourceLocation item_rn = check_item.getRegistryName();
            if (item_rn != null) {
                ArrayList<String> modPlant = new ArrayList<>();
                modPlant.add(item_rn.getNamespace());
                modPlant.add(item_rn.getPath());
                if (!blacklist_plants.contains(modPlant)) {
                    if ((!check_item.getRegistryName().getNamespace().contentEquals("botania")
                            && (
                            check_item.isIn(Tags.Items.CROPS)
                                    || check_item.isIn(Tags.Items.MUSHROOMS)
                                    || check_item.isIn(Tags.Items.LEATHER)
                                    || check_item.isIn(Tags.Items.FEATHERS)
                                    || check_item.isIn(Tags.Items.SEEDS)
                                    || check_item.isIn(Tags.Items.DYES)
                                    || check_item.isIn(Tags.Items.BONES)
                                    || check_item.isIn(ItemTags.SMALL_FLOWERS)
                                    || check_item.isIn(ItemTags.LOGS)
                                    || check_item.isIn(ItemTags.LEAVES)
                                    || check_item.isIn(ItemTags.SAPLINGS)
                                    || check_item.isIn(ItemTags.PLANKS)
                                    || check_item.isIn(Tags.Items.RODS_WOODEN)
                    )) || config_plants.contains(modPlant)) {
                        PLANTS_DYES.add(check_item);
                    }
                }
            }
        }
        return PLANTS_DYES;
    }

    public static ArrayList<Item> loadedStones = getLoadedStones();
    public static ArrayList<Item> getLoadedStones() {
        ArrayList<Item> STONES = new ArrayList<>();
        ArrayList<ArrayList<String>> config_stones = CommonConfig.CUSTOM_STONES.get();
        ArrayList<ArrayList<String>> blacklist_stones = CommonConfig.BLACKLIST_STONES.get();
        for (Item check_item : ForgeRegistries.ITEMS.getValues()) {
            ResourceLocation item_rn = check_item.getRegistryName();
            if (item_rn != null) {
                ArrayList<String> modStone = new ArrayList<>();
                modStone.add(item_rn.getNamespace());
                modStone.add(item_rn.getPath());
                if (!blacklist_stones.contains(modStone)) {
                    if ((
                            check_item.isIn(Tags.Items.STONE)
                                    || check_item.isIn(Tags.Items.COBBLESTONE)
                                    || check_item.isIn(Tags.Items.SANDSTONE)
                                    || check_item.isIn(Tags.Items.END_STONES)
                                    || check_item.isIn(Tags.Items.NETHERRACK)
                    ) || config_stones.contains(modStone)) {
                        STONES.add(check_item);
                    }
                }
            }
        }
        return STONES;
    }

    public static ArrayList<Item> loadedSoils = getLoadedSoils();
    public static ArrayList<Item> getLoadedSoils() {
        ArrayList<Item> SOILS = new ArrayList<>();
        ArrayList<ArrayList<String>> config_soils = CommonConfig.CUSTOM_SOILS.get();
        ArrayList<ArrayList<String>> blacklist_soils = CommonConfig.BLACKLIST_SOILS.get();
        SOILS.add(Items.CLAY);
        for (Block block : ForgeRegistries.BLOCKS.getValues()) {
            Item check_item = block.asItem();
            ResourceLocation item_rn = check_item.getRegistryName();
            if (item_rn != null) {
                ArrayList<String> modOre = new ArrayList<>();
                modOre.add(item_rn.getNamespace());
                modOre.add(item_rn.getPath());
                if (!blacklist_soils.contains(modOre)) {
                    if ((block.isIn(Tags.Blocks.DIRT)
                            || block.isIn(Tags.Blocks.SAND)
                            || block.isIn(Tags.Blocks.GRAVEL)
                    ) || config_soils.contains(modOre)) {
                        SOILS.add(check_item);
                    }
                }
            }
        }
        return SOILS;
    }
}
