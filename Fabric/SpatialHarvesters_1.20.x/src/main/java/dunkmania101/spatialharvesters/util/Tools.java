package dunkmania101.spatialharvesters.util;

import java.util.ArrayList;
import java.util.List;

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
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

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
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack stackTwo = inventory.getStack(i);
            if (ItemStack.canCombine(stack, stackTwo)) {
                transferStacks(inventory, stack, stackTwo);
                if (stack.isEmpty()) {
                    return;
                }
            }
        }
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
        for (int i = 0; i < inventory.size(); ++i) {
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
        Chunk chunk = worldIn.getChunk(chunkPos.getStartPos());
        //noinspection removal
        int height = Math.min(chunk.getHighestNonEmptySectionYOffset() + 16, chunk.getHeight());
        for (BlockPos checkPos : BlockPos.Mutable.iterate(chunkPos.getStartX(), chunk.getBottomY(), chunkPos.getStartZ(), chunkPos.getEndX(), height, chunkPos.getEndZ())) {
            if (worldIn.getBlockState(checkPos).getBlock() == blockIn) {
                count++;
            }
        }
        return count;
    }

    public static NbtCompound correctTileNBT(BlockEntity tile, NbtCompound nbt) {
        NbtCompound newNBT = nbt.copy();
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

    public static List<ItemStack> getPreservedDataBlockDrops(List<ItemStack> drops, BlockState state, NbtCompound tileNBT) {
        if (tileNBT != null) {
            for (ItemStack stack : drops) {
                if (stack.getItem() == state.getBlock().asItem()) {
                    int i = drops.indexOf(stack);
                    stack.getOrCreateNbt().put(CustomValues.stackTileNBTKey, tileNBT);
                    drops.set(i, stack);
                    break;
                }
            }
        }
        return drops;
    }

    public static ArrayList<Item> getLoadedOres() {
        ArrayList<ArrayList<String>> customTags = CommonConfig.custom_ore_tags;
        ArrayList<ArrayList<String>> configItems = CommonConfig.custom_ores;
        ArrayList<ArrayList<String>> blacklistItems = CommonConfig.blacklist_ores;
        ArrayList<ArrayList<String>> blacklistItemsTag = CommonConfig.blacklist_ores_tag;
        ArrayList<String> blacklistItemsMod = CommonConfig.blacklist_ores_mod;
        return getLoadedResources(customTags, configItems, blacklistItems, blacklistItemsTag, blacklistItemsMod);
    }

    public static ArrayList<Item> getLoadedBios() {
        ArrayList<ArrayList<String>> customTags = CommonConfig.custom_bio_tags;
        ArrayList<ArrayList<String>> configItems = CommonConfig.custom_bios;
        ArrayList<ArrayList<String>> blacklistItems = CommonConfig.blacklist_bios;
        ArrayList<ArrayList<String>> blacklistItemsTag = CommonConfig.blacklist_bios_tag;
        ArrayList<String> blacklistItemsMod = CommonConfig.blacklist_bios_mod;
        return getLoadedResources(customTags, configItems, blacklistItems, blacklistItemsTag, blacklistItemsMod);
    }

    public static ArrayList<Item> getLoadedStones() {
        ArrayList<ArrayList<String>> customTags = CommonConfig.custom_stone_tags;
        ArrayList<ArrayList<String>> configItems = CommonConfig.custom_stones;
        ArrayList<ArrayList<String>> blacklistItems = CommonConfig.blacklist_stones;
        ArrayList<ArrayList<String>> blacklistItemsTag = CommonConfig.blacklist_stones_tag;
        ArrayList<String> blacklistItemsMod = CommonConfig.blacklist_stones_mod;
        return getLoadedResources(customTags, configItems, blacklistItems, blacklistItemsTag, blacklistItemsMod);
    }

    public static ArrayList<Item> getLoadedSoils() {
        ArrayList<ArrayList<String>> customTags = CommonConfig.custom_soil_tags;
        ArrayList<ArrayList<String>> configItems = CommonConfig.custom_soils;
        ArrayList<ArrayList<String>> blacklistItems = CommonConfig.blacklist_soils;
        ArrayList<ArrayList<String>> blacklistItemsTag = CommonConfig.blacklist_soils_tag;
        ArrayList<String> blacklistItemsMod = CommonConfig.blacklist_soils_mod;
        return getLoadedResources(customTags, configItems, blacklistItems, blacklistItemsTag, blacklistItemsMod);
    }

    public static ArrayList<Item> getLoadedResources(ArrayList<ArrayList<String>> customTags, ArrayList<ArrayList<String>> configItems, ArrayList<ArrayList<String>>  blacklistItems, ArrayList<ArrayList<String>> blacklistItemsTag, ArrayList<String> blacklistItemsMod) {
        ArrayList<Item> ITEMS = new ArrayList<>();
        for (ArrayList<String> configItem : configItems) {
            if (configItem.size() >= 2) {
                Identifier itemRN = new Identifier(configItem.get(0), configItem.get(1));
                Item item = Registries.ITEM.get(itemRN);
                if (item != Items.AIR && !ITEMS.contains(item)) {
                    ITEMS.add(item);
                }
            }
        }
        for (ArrayList<String> customTag : customTags) {
            if (customTag.size() >= 2) {
                Identifier customTagRN = new Identifier(customTag.get(0), customTag.get(1));
                for (Item checkItem : itemsMatchingId(customTagRN)) {
                    if (!ITEMS.contains(checkItem) && !isResourceBanned(Registries.ITEM.getId(checkItem), blacklistItems, blacklistItemsMod)) {
                        boolean tag_allowed = true;
                        for (ArrayList<String> bannedTag : blacklistItemsTag) {
                            Identifier bannedTagRN = new Identifier(bannedTag.get(0), bannedTag.get(1));
                            if (itemsMatchingId(bannedTagRN).contains(checkItem)) {
                                tag_allowed = false;
                                break;
                            }
                        }
                        if (tag_allowed) {
                            ITEMS.add(checkItem);
                        }
                    }
                }
                for (Block checkBlock : blocksMatchingId(customTagRN)) {
                    Item checkItem = checkBlock.asItem();
                    if (checkItem != Items.AIR && !ITEMS.contains(checkItem) && !isResourceBanned(Registries.ITEM.getId(checkItem), blacklistItems, blacklistItemsMod)) {
                        boolean tag_allowed = true;
                        for (ArrayList<String> bannedTag : blacklistItemsTag) {
                            Identifier bannedTagRN = new Identifier(bannedTag.get(0), bannedTag.get(1));
                            if (blocksMatchingId(bannedTagRN).contains(checkBlock)) {
                                tag_allowed = false;
                                break;
                            }
                        }
                        if (tag_allowed) {
                            ITEMS.add(checkItem);
                        }
                    }
                }
            }
        }
        return ITEMS;
    }

    public static ArrayList<Item> itemsMatchingId(Identifier id) {
        TagKey<Item> key = TagKey.of(RegistryKeys.ITEM, id);
        ArrayList<Item> result = new ArrayList<>();
        for (RegistryEntry<Item> entry : Registries.ITEM.iterateEntries(key)) {
            result.add(entry.value());
        }
        return result;
    }

    public static ArrayList<Block> blocksMatchingId(Identifier id) {
        TagKey<Block> key = TagKey.of(RegistryKeys.BLOCK, id);
        ArrayList<Block> result = new ArrayList<>();
        for (RegistryEntry<Block> entry : Registries.BLOCK.iterateEntries(key)) {
            result.add(entry.value());
        }
        return result;
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

    public static Text getTranslatedFormattedText(String key, Formatting...formats) {
        Text text = Text.translatable(key);
        if (formats != null) {
            return text.copy().formatted(formats);
        }
        return text;
    }

    public static Text getDividerText() {
        return getTranslatedFormattedText("msg.spatialharvesters.divider", Formatting.GRAY, Formatting.BOLD);
    }

    public static ArrayList<Text> getMultiLineText(String key, Formatting...formats) {
        ArrayList<Text> texts = new ArrayList<>();
        for (String txt : Text.translatable(key).getString().split("\n")) {
            Text text = Text.of(txt);
            if (formats != null) {
                texts.add(text.copy().formatted(formats));
            } else {
                texts.add(text);
            }
        }
        return texts;
    }
}
