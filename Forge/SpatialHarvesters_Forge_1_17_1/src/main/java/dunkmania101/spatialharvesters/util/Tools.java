package dunkmania101.spatialharvesters.util;

import java.util.ArrayList;
import java.util.List;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.BaseComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;

public class Tools {
    // Math
    public static VoxelShape getRotatedVoxelShape(VoxelShape shape, Direction from, Direction to) {
        if (from == to) {
            return shape;
        }

        VoxelShape[] buffer = new VoxelShape[] { shape, Shapes.empty() };

        int verticalTimes = 0;
        if (from.get2DDataValue() == -1) {
            if (from == to.getOpposite()) {
                verticalTimes = 2;
            } else {
                verticalTimes = 1;
            }
        } else if (to.get2DDataValue() == -1) {
            verticalTimes = 1;
        }

        for (int i = 0; i < verticalTimes; i++) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.or(buffer[1],
                    Shapes.create(minX, 1 - maxZ, minY, maxX, 1 - minZ, maxY)));
            if (from.getAxis() == Direction.Axis.Z || from.getAxis() == Direction.Axis.Y) {
                from = Direction.from3DDataValue(from.get3DDataValue() + 2);
            }
            buffer[0] = buffer[1];
            buffer[1] = Shapes.empty();
        }

        int horizontalTimes = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4;
        for (int i = 0; i < horizontalTimes; i++) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.or(buffer[1],
                    Shapes.create(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = Shapes.empty();
        }
        return buffer[0];
    }

    public static int getBlocksInChunk(Level worldIn, ChunkPos cpos, Block blockIn) {
        int count = 0;
        ChunkAccess chunk = worldIn.getChunk(cpos.getWorldPosition());
        int height = Math.min(chunk.getHighestSectionPosition() + 16, chunk.getHeight());
        for (BlockPos checkPos : BlockPos.betweenClosed(cpos.getMinBlockX(), 0, cpos.getMinBlockZ(),
                cpos.getMaxBlockX(), height, cpos.getMaxBlockZ())) {
            if (worldIn.getBlockState(checkPos).getBlock() == blockIn) {
                count++;
            }
        }
        return count;
    }

    // NBT
    public static CompoundTag stripTileNBT(CompoundTag nbt) {
        CompoundTag newNBT = nbt.copy();
        newNBT.remove("id");
        newNBT.remove("x");
        newNBT.remove("y");
        newNBT.remove("z");
        return newNBT;
    }

    public static List<ItemStack> getPreservedDataBlockDrops(List<ItemStack> drops, BlockState state,
            CompoundTag tileNBT) {
        if (tileNBT != null) {
            for (ItemStack stack : drops) {
                if (stack.getItem() == state.getBlock().asItem()) {
                    int i = drops.indexOf(stack);
                    stack.getOrCreateTag().put(CustomValues.stackTileNBTKey, stripTileNBT(tileNBT));
                    drops.set(i, stack);
                    break;
                }
            }
        }
        return drops;
    }

    // Resources
    public static ArrayList<Item> getLoadedOres() {
        ArrayList<ArrayList<String>> customTags = CommonConfig.CUSTOM_ORE_TAGS.get();
        ArrayList<ArrayList<String>> configItems = CommonConfig.CUSTOM_ORES.get();
        ArrayList<ArrayList<String>> blacklistItems = CommonConfig.BLACKLIST_ORES.get();
        ArrayList<ArrayList<String>> blacklistItemsTag = CommonConfig.BLACKLIST_ORES_TAG.get();
        ArrayList<String> blacklistItemsMod = CommonConfig.BLACKLIST_ORES_MOD.get();
        return getLoadedResources(customTags, configItems, blacklistItems, blacklistItemsTag, blacklistItemsMod);
    }

    public static ArrayList<Item> getLoadedBios() {
        ArrayList<ArrayList<String>> customTags = CommonConfig.CUSTOM_BIO_TAGS.get();
        ArrayList<ArrayList<String>> configItems = CommonConfig.CUSTOM_BIOS.get();
        ArrayList<ArrayList<String>> blacklistItems = CommonConfig.BLACKLIST_BIOS.get();
        ArrayList<ArrayList<String>> blacklistItemsTag = CommonConfig.BLACKLIST_BIOS_TAG.get();
        ArrayList<String> blacklistItemsMod = CommonConfig.BLACKLIST_BIOS_MOD.get();
        return getLoadedResources(customTags, configItems, blacklistItems, blacklistItemsTag, blacklistItemsMod);
    }

    public static ArrayList<Item> getLoadedStones() {
        ArrayList<ArrayList<String>> customTags = CommonConfig.CUSTOM_STONE_TAGS.get();
        ArrayList<ArrayList<String>> configItems = CommonConfig.CUSTOM_STONES.get();
        ArrayList<ArrayList<String>> blacklistItems = CommonConfig.BLACKLIST_STONES.get();
        ArrayList<ArrayList<String>> blacklistItemsTag = CommonConfig.BLACKLIST_STONES_TAG.get();
        ArrayList<String> blacklistItemsMod = CommonConfig.BLACKLIST_STONES_MOD.get();
        return getLoadedResources(customTags, configItems, blacklistItems, blacklistItemsTag, blacklistItemsMod);
    }

    public static ArrayList<Item> getLoadedSoils() {
        ArrayList<ArrayList<String>> customTags = CommonConfig.CUSTOM_SOIL_TAGS.get();
        ArrayList<ArrayList<String>> configItems = CommonConfig.CUSTOM_SOILS.get();
        ArrayList<ArrayList<String>> blacklistItems = CommonConfig.BLACKLIST_SOILS.get();
        ArrayList<ArrayList<String>> blacklistItemsTag = CommonConfig.BLACKLIST_SOILS_TAG.get();
        ArrayList<String> blacklistItemsMod = CommonConfig.BLACKLIST_SOILS_MOD.get();
        return getLoadedResources(customTags, configItems, blacklistItems, blacklistItemsTag, blacklistItemsMod);
    }

    public static ArrayList<Item> getLoadedResources(ArrayList<ArrayList<String>> customTags,
            ArrayList<ArrayList<String>> configItems, ArrayList<ArrayList<String>> blacklistItems,
            ArrayList<ArrayList<String>> blacklistItemsTag, ArrayList<String> blacklistItemsMod) {
        ArrayList<Item> ITEMS = new ArrayList<>();
        for (ArrayList<String> configItem : configItems) {
            if (configItem.size() >= 2) {
                ResourceLocation itemRN = new ResourceLocation(configItem.get(0), configItem.get(1));
                Item item = ForgeRegistries.ITEMS.getValue(itemRN);
                if (item != Items.AIR && !ITEMS.contains(item)) {
                    ITEMS.add(item);
                }
            }
        }
        for (ArrayList<String> customTag : customTags) {
            if (customTag.size() >= 2) {
                ResourceLocation customTagRN = new ResourceLocation(customTag.get(0), customTag.get(1));
                Tag<Item> itemTag = ItemTags.getAllTags().getTag(customTagRN);
                if (itemTag != null) {
                    for (Item checkItem : itemTag.getValues()) {
                        if (!ITEMS.contains(checkItem)
                                && !isResourceBanned(checkItem.getRegistryName(), blacklistItems, blacklistItemsMod)) {
                            boolean tag_allowed = true;
                            for (ArrayList<String> bannedTag : blacklistItemsTag) {
                                ResourceLocation bannedTagRN = new ResourceLocation(bannedTag.get(0), bannedTag.get(1));
                                Tag<Item> bannedItemTag = ItemTags.getAllTags().getTag(bannedTagRN);
                                if (bannedItemTag != null && bannedItemTag.contains(checkItem)) {
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
                Tag<Block> blockTag = BlockTags.getAllTags().getTag(customTagRN);
                if (blockTag != null) {
                    for (Block checkBlock : blockTag.getValues()) {
                        Item checkItem = checkBlock.asItem();
                        if (checkItem != Items.AIR && !ITEMS.contains(checkItem)
                                && !isResourceBanned(checkItem.getRegistryName(), blacklistItems, blacklistItemsMod)) {
                            boolean tag_allowed = true;
                            for (ArrayList<String> bannedTag : blacklistItemsTag) {
                                ResourceLocation bannedTagRN = new ResourceLocation(bannedTag.get(0), bannedTag.get(1));
                                Tag<Block> bannedBlockTag = BlockTags.getAllTags().getTag(bannedTagRN);
                                if (bannedBlockTag != null && bannedBlockTag.contains(checkBlock)) {
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
        }
        return ITEMS;
    }

    // Resource Locations
    public static ArrayList<String> getModResourceArray(ResourceLocation rn) {
        ArrayList<String> modRN = new ArrayList<>();
        modRN.add(rn.getNamespace());
        modRN.add(rn.getPath());
        return modRN;
    }

    public static boolean isResourceBanned(ResourceLocation rn, ArrayList<ArrayList<String>> blacklist,
            ArrayList<String> blacklist_mod) {
        if (rn != null) {
            return blacklist.contains(getModResourceArray(rn)) || blacklist_mod.contains(rn.getNamespace());
        }
        return true;
    }

    // Text
    public static BaseComponent getTranslatedFormattedText(String key, ChatFormatting... formats) {
        BaseComponent text = new TranslatableComponent(key);
        if (formats != null) {
            return (BaseComponent) text.withStyle(formats);
        }
        return text;
    }

    public static BaseComponent getDividerText() {
        return getTranslatedFormattedText("msg.spatialharvesters.divider", ChatFormatting.GRAY, ChatFormatting.BOLD);
    }

    public static ArrayList<BaseComponent> getMultiLineText(String key, ChatFormatting... formats) {
        ArrayList<BaseComponent> texts = new ArrayList<>();
        for (String txt : new TranslatableComponent(key).getString().split("\n")) {
            TextComponent text = new TextComponent(txt);
            if (formats != null) {
                texts.add((TextComponent) text.withStyle(formats));
            } else {
                texts.add(text);
            }
        }
        return texts;
    }
}
