package dunkmania101.spatialharvesters.util;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class Tools {
    public static ItemStack insertItemStacked(Inventory inventory, ItemStack stack) {
        ItemStack stackTwo = stack.copy();
        addToExistingSlot(inventory, stackTwo);
        if (stackTwo.isEmpty()) {
            return ItemStack.EMPTY;
        } else {
            addStackToNewSlots(inventory, stackTwo);
            return stackTwo;
        }
    }

    private static void addToExistingSlot(Inventory inventory, ItemStack stack) {
        for(int i = 0; i < inventory.size(); ++i) {
            ItemStack stackTwo = inventory.getStack(i);
            if (canCombineStacks(stackTwo, stack)) {
                transferStacks(inventory, stack, stackTwo);
                if (stack.isEmpty()) {
                    return;
                }
            }
        }
    }

    private static boolean canCombineStacks(ItemStack one, ItemStack two) {
        return one.isItemEqual(two) && ItemStack.areTagsEqual(one, two);
    }

    private static void transferStacks(Inventory inventory, ItemStack source, ItemStack target) {
        int i = Math.min(inventory.getMaxCountPerStack(), target.getMaxCount());
        int j = Math.min(source.getCount(), i - target.getCount());
        if (j > 0) {
            target.increment(j);
            source.decrement(j);
            inventory.markDirty();
        }
    }

    public static void addStackToNewSlots(Inventory inventory, ItemStack stack) {
        for(int i = 0; i < inventory.size(); ++i) {
            ItemStack stackTwo = inventory.getStack(i);
            if (stackTwo.isEmpty()) {
                inventory.setStack(i, stack.copy());
                int newCount = stack.getCount() - inventory.getStack(i).getCount();
                stack.setCount(newCount);
                if (stack.getCount() <= 0) {
                    return;
                }
            }
        }

    }

    public static VoxelShape getRotatedVoxelShape(VoxelShape shape, Direction from, Direction to) {
        if (from == to) {
            return shape;
        }

        VoxelShape[] buffer = new VoxelShape[]{shape, VoxelShapes.empty()};

        int verticalTimes = 0;
        if (from.getHorizontal() == -1) {
            if (from == to.getOpposite()) {
                verticalTimes = 2;
            } else {
                verticalTimes = 1;
            }
        } else if (to.getHorizontal() == -1) {
            verticalTimes = 1;
        }

        for (int i = 0; i < verticalTimes; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.union(buffer[1], VoxelShapes.cuboid(minX, 1 - maxZ, minY, maxX, 1 - minZ, maxY)));
            if (from.getAxis() == Direction.Axis.Z || from.getAxis() == Direction.Axis.Y) {
                from = Direction.byId(from.getId() + 2);
            }
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }

        int horizontalTimes = (to.getHorizontal() - from.getHorizontal() + 4) % 4;
        for (int i = 0; i < horizontalTimes; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.union(buffer[1], VoxelShapes.cuboid(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }
        return buffer[0];
    }

    public static int getBlocksInChunk(World worldIn, ChunkPos chunkPos, Block blockIn) {
        int count = 0;
        int height = worldIn.getChunk(chunkPos.getStartPos()).getHeight();
        for (BlockPos checkPos : BlockPos.Mutable.iterate(chunkPos.getStartX(), 0, chunkPos.getStartZ(), chunkPos.getEndX(), height, chunkPos.getEndZ())) {
            if (worldIn.getBlockState(checkPos).getBlock() == blockIn) {
                count++;
            }
        }
        return count;
    }

    public static CompoundTag correctTileNBT(BlockEntity tile, CompoundTag nbt) {
        CompoundTag newNBT = nbt.copy();
        newNBT.remove("id");
        Identifier id = BlockEntityType.getId(tile.getType());
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

    public static List<ItemStack> getPreservedDataBlockDrops(List<ItemStack> drops, BlockState state, CompoundTag tileNBT) {
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
        ArrayList<ArrayList<String>> customTags = CommonConfig.CUSTOM_ORE_TAGS.get();
        ArrayList<ArrayList<String>> configItems = CommonConfig.CUSTOM_ORES.get();
        ArrayList<ArrayList<String>> blacklistItems = CommonConfig.BLACKLIST_ORES.get();
        ArrayList<String> blacklistItemsMod = CommonConfig.BLACKLIST_ORES_MOD.get();
        for (Identifier itemRN : Registry.ITEM.getIds()) {
            if (itemRN != null) {
                if (!isResourceBanned(itemRN, blacklistItems, blacklistItemsMod)) {
                    Item checkItem = Registry.ITEM.get(itemRN);
                    if (configItems.contains(getModResourceArray(itemRN))) {
                        ITEMS.add(checkItem);
                    } else {
                        for (Identifier tagRN : ItemTags.getTagGroup().getTagsFor(checkItem)) {
                            if (!ITEMS.contains(checkItem)) {
                                if (customTags.contains(getModResourceArray(tagRN))) {
                                    ITEMS.add(checkItem);
                                    break;
                                }
                            } else {
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
        }
        return ITEMS;
    }

    public static ArrayList<String> getModResourceArray(Identifier rn) {
        ArrayList<String> modRN = new ArrayList<>();
        modRN.add(rn.getNamespace());
        modRN.add(rn.getPath());
        return modRN;
    }

    public static boolean isResourceBanned(Identifier rn, ArrayList<ArrayList<String>> blacklist, ArrayList<String> blacklistMod) {
        if (rn != null) {
            return blacklist.contains(getModResourceArray(rn)) || blacklistMod.contains(rn.getNamespace());
        }
        return true;
    }
}
d