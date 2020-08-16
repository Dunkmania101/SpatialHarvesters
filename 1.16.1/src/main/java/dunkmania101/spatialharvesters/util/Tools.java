package dunkmania101.spatialharvesters.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Map;

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

    public static ArrayList<Item> getLoadedOres() {


        ArrayList<Item> ORES = new ArrayList<>();
        for (Map.Entry<ResourceLocation, Item> check_item : ForgeRegistries.ITEMS.getEntries()) {
            Item item = check_item.getValue();
            ResourceLocation item_rn = item.getRegistryName();
            if (
                    item.isIn(Tags.Items.ORES)
                    || (item_rn != null
                            && item_rn.getNamespace().contentEquals("appliedenergistics2")
                            && (
                                    item_rn.getPath().contentEquals("quartz_ore")
                                    || item_rn.getPath().contentEquals("charged_quartz_ore"))
                    )
                    || (item_rn != null
                            && item_rn.getNamespace().contentEquals("rftoolsbase")
                            && (
                                    item_rn.getPath().contentEquals("dimensionalshard_overworld")
                                    || item_rn.getPath().contentEquals("dimensionalshard_nether")
                                    || item_rn.getPath().contentEquals("dimensionalshard_end"))
                    )) {
                ORES.add(item);
            }
        }
        return ORES;
    }

    public static ArrayList<Item> getLoadedPlantsAndDyes() {
        ArrayList<Item> PLANTS_DYES = new ArrayList<>();
        for (Map.Entry<ResourceLocation, Item> check_item : ForgeRegistries.ITEMS.getEntries()) {
            Item item = check_item.getValue();
            if (
                    item.isIn(Tags.Items.CROPS)
                    || item.isIn(Tags.Items.MUSHROOMS)
                    || item.isIn(Tags.Items.LEATHER)
                    || item.isIn(Tags.Items.FEATHERS)
                    || item.isIn(Tags.Items.SEEDS)
                    || item.isIn(Tags.Items.DYES)
            ) {
                PLANTS_DYES.add(item);
            }
        }
        return PLANTS_DYES;
    }
}
