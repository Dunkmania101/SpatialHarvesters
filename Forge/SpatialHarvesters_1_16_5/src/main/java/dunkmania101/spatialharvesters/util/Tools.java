package dunkmania101.spatialharvesters.util;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunk;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class Tools {
    // Math
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
        IChunk chunk = worldIn.getChunk(cpos.asBlockPos());
        int height = Math.min(chunk.getTopFilledSegment() + 16, chunk.getHeight());
        for (BlockPos checkPos : BlockPos.getAllInBoxMutable(cpos.getXStart(), 0, cpos.getZStart(), cpos.getXEnd(), height, cpos.getZEnd())) {
            if (worldIn.getBlockState(checkPos).getBlock() == blockIn.getBlock()) {
                count++;
            }
        }
        return count;
    }

    // NBT
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

    public static ArrayList<Item> getLoadedResources(ArrayList<ArrayList<String>> customTags, ArrayList<ArrayList<String>> configItems, ArrayList<ArrayList<String>> blacklistItems, ArrayList<ArrayList<String>> blacklistItemsTag, ArrayList<String> blacklistItemsMod) {
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
                ITag<Item> itemTag = ItemTags.getCollection().get(customTagRN);
                if (itemTag != null) {
                    for (Item checkItem : itemTag.getAllElements()) {
                        if (!ITEMS.contains(checkItem) && !isResourceBanned(checkItem.getRegistryName(), blacklistItems, blacklistItemsMod)) {
                            boolean tag_allowed = true;
                            for (ArrayList<String> bannedTag : blacklistItemsTag) {
                                ResourceLocation bannedTagRN = new ResourceLocation(bannedTag.get(0), bannedTag.get(1));
                                ITag<Item> bannedItemTag = ItemTags.getCollection().get(bannedTagRN);
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
                ITag<Block> blockTag = BlockTags.getCollection().get(customTagRN);
                if (blockTag != null) {
                    for (Block checkBlock : blockTag.getAllElements()) {
                        Item checkItem = checkBlock.asItem();
                        if (checkItem != Items.AIR && !ITEMS.contains(checkItem) && !isResourceBanned(checkItem.getRegistryName(), blacklistItems, blacklistItemsMod)) {
                            boolean tag_allowed = true;
                            for (ArrayList<String> bannedTag : blacklistItemsTag) {
                                ResourceLocation bannedTagRN = new ResourceLocation(bannedTag.get(0), bannedTag.get(1));
                                ITag<Block> bannedBlockTag = BlockTags.getCollection().get(bannedTagRN);
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

    public static TextComponent getTranslatedFormattedText(String key, TextFormatting...formats) {
        TranslationTextComponent text = new TranslationTextComponent(key);
        if (formats != null) {
            return (TextComponent) text.mergeStyle(formats);
        }
        return text;
    }

    public static TextComponent getDividerText() {
        return getTranslatedFormattedText("msg.spatialharvesters.divider", TextFormatting.GRAY, TextFormatting.BOLD);
    }

    public static ArrayList<TextComponent> getMultiLineText(String key, TextFormatting...formats) {
        ArrayList<TextComponent> texts = new ArrayList<>();
        for (String txt : new TranslationTextComponent(key).getString().split("\n")) {
            TextComponent text = new StringTextComponent(txt);
            if (formats != null) {
                texts.add((TextComponent) text.mergeStyle(formats));
            } else {
                texts.add(text);
            }
        }
        return texts;
    }
}
