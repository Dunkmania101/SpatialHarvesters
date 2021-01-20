package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.*;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public class PreservedDataCustomHorizontalShapedBlock extends CustomHorizontalShapedBlock {
    private CompoundNBT thisTileNBT = new CompoundNBT();

    public PreservedDataCustomHorizontalShapedBlock(Properties properties, VoxelShape shape, Direction frontDirection) {
        super(properties, shape, frontDirection);
    }

    @Override
    public void onBlockHarvested(World worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull PlayerEntity player) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile != null) {
            this.thisTileNBT = tile.serializeNBT();
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Nonnull
    @Override
    public List<ItemStack> getDrops(@Nonnull BlockState state, @Nonnull LootContext.Builder builder) {
        List<ItemStack> drops = super.getDrops(state, builder);
        if (this.thisTileNBT != null) {
            if (!this.thisTileNBT.isEmpty()) {
                return Tools.getPreservedDataBlockDrops(drops, state, this.thisTileNBT);
            }
        }
        return drops;
    }

    @Override
    public void onBlockPlacedBy(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, LivingEntity placer, @Nonnull ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isRemote) {
            TileEntity tile = worldIn.getTileEntity(pos);
            CompoundNBT stackTileNBT = stack.getChildTag(CustomValues.stackTileNBTKey);
            if (tile != null && stackTileNBT != null) {
                if (!stackTileNBT.isEmpty()) {
                    tile.deserializeNBT(Tools.correctTileNBT(tile, stackTileNBT));
                }
            }
        }
    }

    @Override
    public void onBlockClicked(@Nonnull BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player) {
        if (!worldIn.isRemote) {
            if (player.isCrouching()) {
                TileEntity tile = worldIn.getTileEntity(pos);
                if (tile != null) {
                    player.sendStatusMessage(Tools.getDividerText(), false);
                    CompoundNBT data = tile.serializeNBT();
                    if (tile instanceof CustomEnergyMachineTE) {
                        player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.energy_message", TextFormatting.DARK_GREEN), false);
                        player.sendStatusMessage(new StringTextComponent(Integer.toString(data.getInt(CustomValues.energyStorageKey))).mergeStyle(TextFormatting.GREEN, TextFormatting.BOLD), false);
                    }
                    if (tile instanceof TickingRedstoneEnergyMachineTE) {
                        if (data.contains(CustomValues.countedTicksKey)) {
                            player.sendStatusMessage(Tools.getDividerText(), false);
                            player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.counted_ticks_message", TextFormatting.YELLOW), false);
                            int countedTicks = data.getInt(CustomValues.countedTicksKey);
                            player.sendStatusMessage(new StringTextComponent(Integer.toString(countedTicks)).mergeStyle(TextFormatting.YELLOW, TextFormatting.BOLD), false);
                        }
                    }
                    if (tile instanceof SpatialHarvesterTE) {
                        player.sendStatusMessage(Tools.getDividerText(), false);
                        player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.disabled_resources", TextFormatting.RED), false);
                        if (data.contains(CustomValues.disabledResourcesKey)) {
                            CompoundNBT disabledResources = data.getCompound(CustomValues.disabledResourcesKey);
                            for (String key : disabledResources.keySet()) {
                                Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(disabledResources.getString(key)));
                                if (item != null && item != Items.AIR) {
                                    player.sendStatusMessage(item.getName().copyRaw().mergeStyle(TextFormatting.DARK_PURPLE, TextFormatting.BOLD), false);
                                }
                            }
                        }
                    }
                    if (tile instanceof MobHarvesterTE) {
                        player.sendStatusMessage(Tools.getDividerText(), false);
                        player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.mob_key_bound_mob", TextFormatting.DARK_RED), false);
                        String mob = data.getString(CustomValues.entityNBTKey);
                        if (!StringUtils.isNullOrEmpty(mob)) {
                            Optional<EntityType<?>> optionalEntityType = EntityType.byKey(mob);
                            if (optionalEntityType.isPresent()) {
                                EntityType<?> entityType = optionalEntityType.get();
                                player.sendStatusMessage(entityType.getName().copyRaw().mergeStyle(TextFormatting.RED, TextFormatting.BOLD), false);
                            }
                        }
                        player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.weapon_key_bound_weapon", TextFormatting.DARK_GRAY), false);
                        CompoundNBT weapon = data.getCompound(CustomValues.weaponNBTKey);
                        if (!weapon.isEmpty()) {
                            ItemStack weaponStack = ItemStack.read(weapon);
                            if (!weaponStack.isEmpty()) {
                                player.sendStatusMessage(weaponStack.getDisplayName().copyRaw().mergeStyle(TextFormatting.GRAY, TextFormatting.BOLD), false);
                            }
                        }
                    } else if (tile instanceof DimensionalApplicatorTE) {
                        player.sendStatusMessage(Tools.getDividerText(), false);
                        player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.dimensional_applicator_saved_effects", TextFormatting.BLUE), false);
                        if (data.contains(CustomValues.potionsNBTKey)) {
                            for (int id : data.getIntArray(CustomValues.potionsNBTKey)) {
                                Effect effect = Effect.get(id);
                                if (effect != null) {
                                    player.sendStatusMessage(effect.getDisplayName().copyRaw().mergeStyle(TextFormatting.BLUE, TextFormatting.BOLD), false);
                                }
                            }
                        }
                    }
                    player.sendStatusMessage(Tools.getDividerText(), false);
                }
            }
        }
        super.onBlockClicked(state, worldIn, pos, player);
    }
}
