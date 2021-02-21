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
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
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
        for (int i = 0; i < inventory.size(); ++i) {
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
        ArrayList<ArrayList<String>> customTags = CommonConfig.custom_ore_tags;
        ArrayList<ArrayList<String>> configItems = CommonConfig.custom_ores;
        ArrayList<ArrayList<String>> blacklistItems = CommonConfig.blacklist_ores;
        ArrayList<String> blacklistItemsMod = CommonConfig.blacklist_ores_mod;
        return getLoadedResources(customTags, configItems, blacklistItems, blacklistItemsMod);
    }

    public static ArrayList<Item> getLoadedBios() {
        ArrayList<ArrayList<String>> customTags = CommonConfig.custom_bio_tags;
        ArrayList<ArrayList<String>> configItems = CommonConfig.custom_bios;
        ArrayList<ArrayList<String>> blacklistItems = CommonConfig.blacklist_bios;
        ArrayList<String> blacklistItemsMod = CommonConfig.blacklist_bios_mod;
        return getLoadedResources(customTags, configItems, blacklistItems, blacklistItemsMod);
    }

    public static ArrayList<Item> getLoadedStones() {
        ArrayList<ArrayList<String>> customTags = CommonConfig.custom_stone_tags;
        ArrayList<ArrayList<String>> configItems = CommonConfig.custom_stones;
        ArrayList<ArrayList<String>> blacklistItems = CommonConfig.blacklist_stones;
        ArrayList<String> blacklistItemsMod = CommonConfig.blacklist_stones_mod;
        return getLoadedResources(customTags, configItems, blacklistItems, blacklistItemsMod);
    }

    public static ArrayList<Item> getLoadedResources(ArrayList<ArrayList<String>> customTags, ArrayList<ArrayList<String>> configItems, ArrayList<ArrayList<String>>  blacklistItems, ArrayList<String> blacklistItemsMod) {
        ArrayList<Item> ITEMS = new ArrayList<>();
        for (ArrayList<String> configItem : configItems) {
            if (configItem.size() >= 2) {
                Identifier itemRN = new Identifier(configItem.get(0), configItem.get(1));
                Item item = Registry.ITEM.get(itemRN);
                if (item != Items.AIR && !ITEMS.contains(item)) {
                    ITEMS.add(item);
                }
            }
        }
        for (ArrayList<String> customTag : customTags) {
            if (customTag.size() >= 2) {
                Identifier customTagRN = new Identifier(customTag.get(0), customTag.get(1));
                Tag<Item> tag = ItemTags.getTagGroup().getTagOrEmpty(customTagRN);
                if (tag != null) {
                    for (Item checkItem : tag.values()) {
                        if (!ITEMS.contains(checkItem) && !isResourceBanned(Identifier.tryParse(checkItem.toString()), blacklistItems, blacklistItemsMod)) {
                            ITEMS.add(checkItem);
                        }
                    }
                }
            }
        }
        return ITEMS;
    }

    public static ArrayList<Item> getLoadedSoils() {
        ArrayList<Item> ITEMS = new ArrayList<>();
        ArrayList<ArrayList<String>> customTags = CommonConfig.custom_soil_tags;
        ArrayList<ArrayList<String>> configItems = CommonConfig.custom_soils;
        ArrayList<ArrayList<String>> blacklistItems = CommonConfig.blacklist_soils;
        ArrayList<String> blacklistItemsMod = CommonConfig.blacklist_soils_mod;
        for (ArrayList<String> configItem : configItems) {
            if (configItem.size() >= 2) {
                Identifier itemRN = new Identifier(configItem.get(0), configItem.get(1));
                Item item = Registry.ITEM.get(itemRN);
                if (item != Items.AIR && !ITEMS.contains(item)) {
                    ITEMS.add(item);
                }
            }
        }
        for (ArrayList<String> customTag : customTags) {
            if (customTag.size() >= 2) {
                Identifier customTagRN = new Identifier(customTag.get(0), customTag.get(1));
                Tag<Block> tag = BlockTags.getTagGroup().getTagOrEmpty(customTagRN);
                if (tag != null) {
                    for (Block checkBlock : tag.values()) {
                        Item checkItem = checkBlock.asItem();
                        if (checkItem != Items.AIR && !ITEMS.contains(checkItem) && !isResourceBanned(Identifier.tryParse(checkItem.toString()), blacklistItems, blacklistItemsMod)) {
                            ITEMS.add(checkItem);
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

    public static Text getTranslatedFormattedText(String key, Formatting...formats) {
        TranslatableText text = new TranslatableText(key);
        if (formats != null) {
            return text.formatted(formats);
        }
        return text;
    }

    public static Text getDividerText() {
        return getTranslatedFormattedText("msg.spatialharvesters.divider", Formatting.GRAY, Formatting.BOLD);
    }

    public static ArrayList<Text> getMultiLineText(String key, Formatting...formats) {
        ArrayList<Text> texts = new ArrayList<>();
        for (String txt : new TranslatableText(key).getString().split("\n")) {
            Text text = new LiteralText(txt);
            if (formats != null) {
                texts.add(text.copy().formatted(formats));
            } else {
                texts.add(text);
            }
        }
        return texts;
    }
}
