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

    public static ArrayList<Block> getLoadedOres() {
        ArrayList<Block> ORES = new ArrayList<>();
        for (Map.Entry<ResourceLocation, Block> check_block : ForgeRegistries.BLOCKS.getEntries()) {
            Block block = check_block.getValue();
            if (block.isIn(Tags.Blocks.ORES)) {
                ORES.add(block);
            }
        }
        return ORES;
    }

    public static ArrayList<Item> getLoadedPlantsAndDyes() {
        ArrayList<Item> PLANTS_DYES = new ArrayList<>();
        for (Map.Entry<ResourceLocation, Item> check_item : ForgeRegistries.ITEMS.getEntries()) {
            Item item = check_item.getValue();
            if (item.isIn(Tags.Items.CROPS) || item.isIn(Tags.Items.SEEDS) || item.isIn(Tags.Items.DYES)) {
                PLANTS_DYES.add(item);
            }
        }
        return PLANTS_DYES;
    }
}
