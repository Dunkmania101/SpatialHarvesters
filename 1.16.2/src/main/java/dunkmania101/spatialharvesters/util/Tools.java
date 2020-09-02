package dunkmania101.spatialharvesters.util;

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
        for (Item check_item : ForgeRegistries.ITEMS.getValues()) {
            ResourceLocation item_rn = check_item.getRegistryName();
            if (item_rn != null) {
                if (
                        check_item.isIn(Tags.Items.ORES)
                                || (item_rn.getNamespace().contentEquals("appliedenergistics2")
                                && (
                                    item_rn.getPath().contentEquals("quartz_ore")
                                            || item_rn.getPath().contentEquals("charged_quartz_ore")
                        ))
                                || (item_rn.getNamespace().contentEquals("rftoolsbase")
                                && (
                                    item_rn.getPath().contentEquals("dimensionalshard_overworld")
                                            || item_rn.getPath().contentEquals("dimensionalshard_nether")
                                            || item_rn.getPath().contentEquals("dimensionalshard_end")
                        ))
                                || (item_rn.getNamespace().contentEquals("rhodonite")
                                && (
                                    item_rn.getPath().contentEquals("block_ore_fluorite")
                                            || item_rn.getPath().contentEquals("block_ore_rhodonite")
                        ))
                                || (item_rn.getNamespace().contentEquals("exp_ore")
                                && (
                                        item_rn.getPath().contentEquals("block_exp_ore")
                        ))
                                || (item_rn.getNamespace().contentEquals("funores")
                                && (
                                        !item_rn.getPath().contains("shard")
                                                && !item_rn.getPath().contains("scrap")
                        ))
                                || (item_rn.getNamespace().contentEquals("rockcandy")
                                && (
                                item_rn.getPath().contentEquals("candy_ore")
                        ))
                ) {
                    ORES.add(check_item);
                }
            }
            for (Block check_block : ForgeRegistries.BLOCKS.getValues()) {
                if (check_block.isIn(Tags.Blocks.ORES)) {
                    Item item = check_block.asItem();
                    if (!ORES.contains(item)) {
                        ORES.add(item);
                    }
                }
            }
        }
        return ORES;
    }

    public static ArrayList<Item> loadedPlantsAndDyes = getLoadedPlantsAndDyes();
    public static ArrayList<Item> getLoadedPlantsAndDyes() {
        ArrayList<Item> PLANTS_DYES = new ArrayList<>();
        for (Item check_item : ForgeRegistries.ITEMS.getValues()) {
            if (check_item.getRegistryName() != null) {
                if (
                        !check_item.getRegistryName().getNamespace().contentEquals("botania")
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
                        )
                ) {
                    PLANTS_DYES.add(check_item);
                }
            }
        }
        return PLANTS_DYES;
    }

    public static ArrayList<Item> loadedStones = getLoadedStones();
    public static ArrayList<Item> getLoadedStones() {
        ArrayList<Item> STONES = new ArrayList<>();
        for (Item check_item : ForgeRegistries.ITEMS.getValues()) {
            if (
                    check_item.isIn(Tags.Items.STONE)
                            || check_item.isIn(Tags.Items.COBBLESTONE)
                            || check_item.isIn(Tags.Items.SANDSTONE)
                            || check_item.isIn(Tags.Items.END_STONES)
                            || check_item.isIn(Tags.Items.NETHERRACK)
            ) {
                STONES.add(check_item);
            }
        }
        return STONES;
    }

    public static ArrayList<Item> loadedSoils = getLoadedSoils();
    public static ArrayList<Item> getLoadedSoils() {
        ArrayList<Item> SOILS = new ArrayList<>();
        SOILS.add(Items.CLAY);
        for (Block block : ForgeRegistries.BLOCKS.getValues()) {
            if (
                    block.isIn(Tags.Blocks.DIRT)
                            || block.isIn(Tags.Blocks.SAND)
                            || block.isIn(Tags.Blocks.GRAVEL)
            ) {
                SOILS.add(block.asItem());
            }
        }
        return SOILS;
    }
}
