package dunkmania101.spatialharvesters.util;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
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
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class Tools {
    public static ArrayList<StringTextComponent> getSplitStringTextComponent(String textComponent, String splitOn) {
        String[] splitText = textComponent.split(splitOn);
        ArrayList<StringTextComponent> splitTextComponent = new ArrayList<>();
        for (String text : splitText) {
            splitTextComponent.add(new StringTextComponent(text));
        }
        return splitTextComponent;
    }

    public static VoxelShape getRotatedVoxelShape(VoxelShape shape, Direction from, Direction to) {
        if (from == to) {
            return shape;
        }

        VoxelShape[] buffer = new VoxelShape[]{shape, VoxelShapes.empty()};

        int vertical_times = 0;
        if (from.getHorizontalIndex() == -1) {
            if (from == to.getOpposite()) {
                vertical_times = 2;
            } else {
                vertical_times = 1;
            }
        } else if (to.getHorizontalIndex() == -1) {
            vertical_times = 1;
        }

        for (int i = 0; i < vertical_times; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.or(buffer[1], VoxelShapes.create(minX, 1-maxZ, minY, maxX, 1-minZ, maxY)));
            if (from.getAxis() == Direction.Axis.Z || from.getAxis() == Direction.Axis.Y) {
                from = Direction.byIndex(from.getIndex() + 2);
            }
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }

        int horizontal_times = (to.getHorizontalIndex() - from.getHorizontalIndex() + 4) % 4;
        for (int i = 0; i < horizontal_times; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.or(buffer[1], VoxelShapes.create(1-maxZ, minY, minX, 1-minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }
        return buffer[0];
    }

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

    public static CompoundNBT correctTileNBT(TileEntity tile, CompoundNBT nbt) {
        nbt.remove("id");
        ResourceLocation id = TileEntityType.getId(tile.getType());
        if (id != null) {
            nbt.putString("id", id.toString());
        }
        BlockPos pos = tile.getPos();
        nbt.putInt("x", pos.getX());
        nbt.putInt("y", pos.getY());
        nbt.putInt("z", pos.getZ());
        return nbt;
    }

    public static List<ItemStack> getPreservedDataBlockDrops(List<ItemStack> drops, BlockState state, TileEntity tile) {
        if (tile != null) {
            for (ItemStack stack : drops) {
                if (stack.getItem() == state.getBlock().asItem()) {
                    int i = drops.indexOf(stack);
                    CompoundNBT tileNBT = tile.serializeNBT();
                    stack.getOrCreateTag().put(CustomValues.stackTileNBTKey, correctTileNBT(tile, tileNBT));
                    drops.set(i, stack);
                    break;
                }
            }
        }
        return drops;
    }

    public static ArrayList<Item> getLoadedOres() {
        ArrayList<Item> ORES = new ArrayList<>();
        ArrayList<ArrayList<String>> config_ores = CommonConfig.CUSTOM_ORES.get();
        ArrayList<ArrayList<String>> blacklist_ores = CommonConfig.BLACKLIST_ORES.get();
        ArrayList<String> blacklist_ores_mod = CommonConfig.BLACKLIST_ORES_MOD.get();
        for (Item check_item : ForgeRegistries.ITEMS.getValues()) {
            ResourceLocation item_rn = check_item.getRegistryName();
            if (item_rn != null) {
                ArrayList<String> modOre = new ArrayList<>();
                String modid = item_rn.getNamespace();
                modOre.add(modid);
                modOre.add(item_rn.getPath());
                if (!blacklist_ores.contains(modOre) && !blacklist_ores_mod.contains(modid)) {
                    if (check_item.isIn(Tags.Items.ORES) || config_ores.contains(modOre)) {
                        ORES.add(check_item);
                    }
                }
            }
        }
        return ORES;
    }

    public static ArrayList<Item> getLoadedBios() {
        ArrayList<Item> BIOS = new ArrayList<>();
        ArrayList<ArrayList<String>> config_bios = CommonConfig.CUSTOM_BIOS.get();
        ArrayList<ArrayList<String>> blacklist_bios = CommonConfig.BLACKLIST_BIOS.get();
        ArrayList<String> blacklist_bios_mod = CommonConfig.BLACKLIST_BIOS_MOD.get();
        for (Item check_item : ForgeRegistries.ITEMS.getValues()) {
            ResourceLocation item_rn = check_item.getRegistryName();
            if (item_rn != null) {
                ArrayList<String> modBio = new ArrayList<>();
                String modid = item_rn.getNamespace();
                modBio.add(modid);
                modBio.add(item_rn.getPath());
                if (!blacklist_bios.contains(modBio) && !blacklist_bios_mod.contains(modid)) {
                    if (check_item.isIn(Tags.Items.CROPS)
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
                     || config_bios.contains(modBio)) {
                        BIOS.add(check_item);
                    }
                }
            }
        }
        return BIOS;
    }

    public static ArrayList<Item> getLoadedStones() {
        ArrayList<Item> STONES = new ArrayList<>();
        ArrayList<ArrayList<String>> config_stones = CommonConfig.CUSTOM_STONES.get();
        ArrayList<ArrayList<String>> blacklist_stones = CommonConfig.BLACKLIST_STONES.get();
        ArrayList<String> blacklist_stones_mod = CommonConfig.BLACKLIST_ORES_MOD.get();
        for (Item check_item : ForgeRegistries.ITEMS.getValues()) {
            ResourceLocation item_rn = check_item.getRegistryName();
            if (item_rn != null) {
                ArrayList<String> modStone = new ArrayList<>();
                String modid = item_rn.getNamespace();
                modStone.add(modid);
                modStone.add(item_rn.getPath());
                if (!blacklist_stones.contains(modStone) && !blacklist_stones_mod.contains(modid)) {
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

    public static ArrayList<Item> getLoadedSoils() {
        ArrayList<Item> SOILS = new ArrayList<>();
        ArrayList<ArrayList<String>> config_soils = CommonConfig.CUSTOM_SOILS.get();
        ArrayList<ArrayList<String>> blacklist_soils = CommonConfig.BLACKLIST_SOILS.get();
        ArrayList<String> blacklist_soils_mod = CommonConfig.BLACKLIST_ORES_MOD.get();
        SOILS.add(Items.CLAY);
        for (Block block : ForgeRegistries.BLOCKS.getValues()) {
            Item check_item = block.asItem();
            ResourceLocation item_rn = check_item.getRegistryName();
            if (item_rn != null) {
                ArrayList<String> modSoil = new ArrayList<>();
                String modid = item_rn.getNamespace();
                modSoil.add(modid);
                modSoil.add(item_rn.getPath());
                if (!blacklist_soils.contains(modSoil) && !blacklist_soils_mod.contains(modid)) {
                    if ((block.isIn(Tags.Blocks.DIRT)
                            || block.isIn(Tags.Blocks.SAND)
                            || block.isIn(Tags.Blocks.GRAVEL)
                    ) || config_soils.contains(modSoil)) {
                        SOILS.add(check_item);
                    }
                }
            }
        }
        return SOILS;
    }
}
