package dunkmania101.spatialharvesters.util;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class Tools {
    public static VoxelShape getRotatedVoxelShape(VoxelShape shape, Direction from, Direction to) {
        if (from == to) {
            return shape;
        }

        VoxelShape[] buffer = new VoxelShape[]{shape, VoxelShapes.empty()};

        int verticalTimes = 0;
        if (from.getHorizontalIndex() == -1) {
            if (from == to.getOpposite()) {
                verticalTimes = 2;
            } else {
                verticalTimes = 1;
            }
        } else if (to.getHorizontalIndex() == -1) {
            verticalTimes = 1;
        }

        for (int i = 0; i < verticalTimes; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.or(buffer[1], VoxelShapes.create(minX, 1 - maxZ, minY, maxX, 1 - minZ, maxY)));
            if (from.getAxis() == Direction.Axis.Z || from.getAxis() == Direction.Axis.Y) {
                from = Direction.byIndex(from.getIndex() + 2);
            }
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }

        int horizontalTimes = (to.getHorizontalIndex() - from.getHorizontalIndex() + 4) % 4;
        for (int i = 0; i < horizontalTimes; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.or(buffer[1], VoxelShapes.create(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }
        return buffer[0];
    }

    public static int getBlocksInChunk(World worldIn, ChunkPos cpos, Block blockIn) {
        int count = 0;
        int height = worldIn.getChunk(cpos.asBlockPos()).getHeight();
        for (BlockPos checkPos : BlockPos.getAllInBoxMutable(cpos.getXStart(), 0, cpos.getZStart(), cpos.getXEnd(), height, cpos.getZEnd())) {
            if (worldIn.getBlockState(checkPos).getBlock() == blockIn.getBlock()) {
                count++;
            }
        }
        return count;
    }

    public static CompoundNBT correctTileNBT(TileEntity tile, CompoundNBT nbt) {
        CompoundNBT newNBT = nbt.copy();
        newNBT.remove("id");
        ResourceLocation id = TileEntityType.getId(tile.getType());
        if (id != null) {
            newNBT.putString("id", id.toString());
        }
        BlockPos pos = tile.getPos();
        newNBT.remove("x");
        newNBT.remove("y");
        newNBT.remove("z");
        newNBT.putInt("x", pos.getX());
        newNBT.putInt("y", pos.getY());
        newNBT.putInt("z", pos.getZ());
        return newNBT;
    }

    public static List<ItemStack> getPreservedDataBlockDrops(List<ItemStack> drops, BlockState state, CompoundNBT tileNBT) {
        if (tileNBT != null) {
            for (ItemStack stack : drops) {
                if (stack.getItem() == state.getBlock().asItem()) {
                    int i = drops.indexOf(stack);
                    stack.getOrCreateTag().put(CustomValues.stackTileNBTKey, tileNBT);
                    drops.set(i, stack);
                    break;
                }
            }
        }
        return drops;
    }

    public static ArrayList<Item> getLoadedOres() {
        ArrayList<Item> ITEMS = new ArrayList<>();
        ArrayList<ArrayList<String>> configItems = CommonConfig.CUSTOM_ORES.get();
        ArrayList<ArrayList<String>> blacklistItems = CommonConfig.BLACKLIST_ORES.get();
        ArrayList<String> blacklistItemsMod = CommonConfig.BLACKLIST_ORES_MOD.get();
        for (Item checkItem : ForgeRegistries.ITEMS.getValues()) {
            ResourceLocation itemRN = checkItem.getRegistryName();
            if (itemRN != null) {
                if (!isResourceBanned(itemRN, blacklistItems, blacklistItemsMod)) {
                    ArrayList<ArrayList<String>> customTags = CommonConfig.CUSTOM_ORE_TAGS.get();
                    if (configItems.contains(getModResourceArray(itemRN))) {
                        ITEMS.add(checkItem);
                    } else {
                        for (ResourceLocation tagRN : checkItem.getTags()) {
                            if (customTags.contains(getModResourceArray(tagRN)) && !ITEMS.contains(checkItem)) {
                                ITEMS.add(checkItem);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return ITEMS;
    }

    public static ArrayList<Item> getLoadedBios() {
        ArrayList<Item> ITEMS = new ArrayList<>();
        ArrayList<ArrayList<String>> configItems = CommonConfig.CUSTOM_BIOS.get();
        ArrayList<ArrayList<String>> blacklistItems = CommonConfig.BLACKLIST_BIOS.get();
        ArrayList<String> blacklistItemsMod = CommonConfig.BLACKLIST_BIOS_MOD.get();
        for (Item checkItem : ForgeRegistries.ITEMS.getValues()) {
            ResourceLocation itemRN = checkItem.getRegistryName();
            if (itemRN != null) {
                if (!isResourceBanned(itemRN, blacklistItems, blacklistItemsMod)) {
                    ArrayList<ArrayList<String>> customTags = CommonConfig.CUSTOM_BIO_TAGS.get();
                    if (configItems.contains(getModResourceArray(itemRN))) {
                        ITEMS.add(checkItem);
                    } else {
                        for (ResourceLocation tagRN : checkItem.getTags()) {
                            if (customTags.contains(getModResourceArray(tagRN)) && !ITEMS.contains(checkItem)) {
                                ITEMS.add(checkItem);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return ITEMS;
    }

    public static ArrayList<Item> getLoadedStones() {
        ArrayList<Item> ITEMS = new ArrayList<>();
        ArrayList<ArrayList<String>> configItems = CommonConfig.CUSTOM_STONES.get();
        ArrayList<ArrayList<String>> blacklistItems = CommonConfig.BLACKLIST_STONES.get();
        ArrayList<String> blacklistItemsMod = CommonConfig.BLACKLIST_STONES_MOD.get();
        for (Item checkItem : ForgeRegistries.ITEMS.getValues()) {
            ResourceLocation itemRN = checkItem.getRegistryName();
            if (itemRN != null) {
                if (!isResourceBanned(itemRN, blacklistItems, blacklistItemsMod)) {
                    ArrayList<ArrayList<String>> customTags = CommonConfig.CUSTOM_STONE_TAGS.get();
                    if (configItems.contains(getModResourceArray(itemRN))) {
                        ITEMS.add(checkItem);
                    } else {
                        for (ResourceLocation tagRN : checkItem.getTags()) {
                            if (customTags.contains(getModResourceArray(tagRN)) && !ITEMS.contains(checkItem)) {
                                ITEMS.add(checkItem);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return ITEMS;
    }

    public static ArrayList<Item> getLoadedSoils() {
        ArrayList<Item> ITEMS = new ArrayList<>();
        ArrayList<ArrayList<String>> configItems = CommonConfig.CUSTOM_SOILS.get();
        ArrayList<ArrayList<String>> blacklistItems = CommonConfig.BLACKLIST_SOILS.get();
        ArrayList<String> blacklistItemsMod = CommonConfig.BLACKLIST_SOILS_MOD.get();
        for (Block checkBlock : ForgeRegistries.BLOCKS.getValues()) {
            Item checkItem = checkBlock.asItem();
            if (checkItem != Items.AIR) {
                ResourceLocation itemRN = checkItem.getRegistryName();
                if (itemRN != null) {
                    if (!isResourceBanned(itemRN, blacklistItems, blacklistItemsMod)) {
                        ArrayList<ArrayList<String>> customTags = CommonConfig.CUSTOM_SOIL_TAGS.get();
                        if (configItems.contains(getModResourceArray(itemRN))) {
                            ITEMS.add(checkItem);
                        } else {
                            for (ResourceLocation tagRN : checkBlock.getTags()) {
                                if (customTags.contains(getModResourceArray(tagRN)) && !ITEMS.contains(checkItem)) {
                                    ITEMS.add(checkItem);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return ITEMS;
    }

    public static ArrayList<String> getModResourceArray(ResourceLocation rn) {
        ArrayList<String> modRN = new ArrayList<>();
        modRN.add(rn.getNamespace());
        modRN.add(rn.getPath());
        return modRN;
    }

    public static boolean isResourceBanned(ResourceLocation rn, ArrayList<ArrayList<String>> blacklist, ArrayList<String> blacklist_mod) {
        if (rn != null) {
            return blacklist.contains(getModResourceArray(rn)) || blacklist_mod.contains(rn.getNamespace());
        }
        return true;
    }
}
