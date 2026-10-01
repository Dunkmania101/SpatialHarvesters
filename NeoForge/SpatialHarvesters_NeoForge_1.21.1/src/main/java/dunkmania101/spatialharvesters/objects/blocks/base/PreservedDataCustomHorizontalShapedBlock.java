package dunkmania101.spatialharvesters.objects.blocks.base;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.DimensionalApplicatorTE;
import dunkmania101.spatialharvesters.objects.tile_entities.MobHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.base.CustomEnergyMachineTE;
import dunkmania101.spatialharvesters.objects.tile_entities.base.SpatialHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.base.TickingRedstoneEnergyMachineTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.core.registries.BuiltInRegistries;

public class PreservedDataCustomHorizontalShapedBlock extends CustomHorizontalShapedBlock {
    private CompoundTag thisTileNBT = new CompoundTag();

    public PreservedDataCustomHorizontalShapedBlock(Properties properties, VoxelShape shape, Direction frontDirection) {
        super(properties, shape, frontDirection);
    }

    @Override
    public BlockState playerWillDestroy(Level worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state,
            @Nonnull Player player) {
        BlockEntity tile = worldIn.getBlockEntity(pos);
        if (tile != null) {
            this.thisTileNBT = tile.saveWithoutMetadata(worldIn.registryAccess());
        }
        return super.playerWillDestroy(worldIn, pos, state, player);
    }

    @Nonnull
    @Override
    public List<ItemStack> getDrops(@Nonnull BlockState state, @Nonnull LootParams.Builder builder) {
        List<ItemStack> drops = super.getDrops(state, builder);
        if (this.thisTileNBT != null) {
            if (!this.thisTileNBT.isEmpty()) {
                return Tools.getPreservedDataBlockDrops(drops, state, this.thisTileNBT);
            }
        }
        return drops;
    }

    @Override
    public void setPlacedBy(@Nonnull Level worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state,
            LivingEntity placer, @Nonnull ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isClientSide()) {
            BlockEntity tile = worldIn.getBlockEntity(pos);
            CompoundTag stackTileNBT = Tools.getCustomData(stack).getCompound(CustomValues.stackTileNBTKey);
            if (tile != null && !stackTileNBT.isEmpty()) {
                tile.loadWithComponents(Tools.stripTileNBT(stackTileNBT), worldIn.registryAccess());
            }
        }
    }

    @Nonnull
    @Override
    public InteractionResult useWithoutItem(@Nonnull BlockState state, Level worldIn, @Nonnull BlockPos pos,
            @Nonnull Player player, @Nonnull BlockHitResult hit) {
        if (player.isCrouching()) {
            if (worldIn.isClientSide()) {
                return InteractionResult.SUCCESS;
            } else {
                BlockEntity tile = worldIn.getBlockEntity(pos);
                if (tile != null) {
                    player.displayClientMessage(Tools.getDividerText(), false);
                    CompoundTag data = tile.saveWithoutMetadata(worldIn.registryAccess());
                    if (tile instanceof CustomEnergyMachineTE) {
                        player.displayClientMessage(Tools.getTranslatedFormattedText(
                                "msg.spatialharvesters.energy_message", ChatFormatting.DARK_GREEN), false);
                        player.displayClientMessage(
                                Component.literal(Integer.toString(data.getInt(CustomValues.energyStorageKey)))
                                        .withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD),
                                false);
                    }
                    if (tile instanceof TickingRedstoneEnergyMachineTE) {
                        if (data.contains(CustomValues.countedTicksKey)) {
                            player.displayClientMessage(Tools.getDividerText(), false);
                            player.displayClientMessage(Tools.getTranslatedFormattedText(
                                    "msg.spatialharvesters.counted_ticks_message", ChatFormatting.YELLOW), false);
                            int countedTicks = data.getInt(CustomValues.countedTicksKey);
                            player.displayClientMessage(Component.literal(Integer.toString(countedTicks))
                                    .withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD), false);
                        }
                    }
                    if (tile instanceof SpatialHarvesterTE) {
                        player.displayClientMessage(Tools.getDividerText(), false);
                        player.displayClientMessage(Tools.getTranslatedFormattedText(
                                "msg.spatialharvesters.disabled_resources", ChatFormatting.RED), false);
                        if (data.contains(CustomValues.disabledResourcesKey)) {
                            CompoundTag disabledResources = data.getCompound(CustomValues.disabledResourcesKey);
                            for (String key : disabledResources.getAllKeys()) {
                                Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(disabledResources.getString(key)));
                                if (item != null && item != Items.AIR) {
                                    player.displayClientMessage(
                                            Tools.getTranslatedFormattedText(item.getDescriptionId(),
                                                    ChatFormatting.DARK_PURPLE, ChatFormatting.BOLD),
                                            false);
                                }
                            }
                        }
                    }
                    if (tile instanceof MobHarvesterTE) {
                        player.displayClientMessage(Tools.getDividerText(), false);
                        player.displayClientMessage(Tools.getTranslatedFormattedText(
                                "msg.spatialharvesters.mob_key_bound_mob", ChatFormatting.DARK_RED), false);
                        String mob = data.getString(CustomValues.entityNBTKey);
                        if (mob != null && !mob.isEmpty()) {
                            Optional<EntityType<?>> optionalEntityType = EntityType.byString(mob);
                            if (optionalEntityType.isPresent()) {
                                EntityType<?> entityType = optionalEntityType.get();
                                player.displayClientMessage(Tools.getTranslatedFormattedText(
                                        entityType.getDescriptionId(), ChatFormatting.RED, ChatFormatting.BOLD), false);
                            }
                        }
                        player.displayClientMessage(Tools.getDividerText(), false);
                        player.displayClientMessage(Tools.getTranslatedFormattedText(
                                "msg.spatialharvesters.weapon_key_bound_weapon", ChatFormatting.DARK_GRAY), false);
                        CompoundTag weapon = data.getCompound(CustomValues.weaponNBTKey);
                        if (!weapon.isEmpty()) {
                            ItemStack weaponStack = ItemStack.parseOptional(worldIn.registryAccess(), weapon);
                            if (!weaponStack.isEmpty()) {
                                player.displayClientMessage(weaponStack.getDisplayName().copy()
                                        .withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD), false);
                            }
                        }
                    } else if (tile instanceof DimensionalApplicatorTE) {
                        player.displayClientMessage(Tools.getDividerText(), false);
                        player.displayClientMessage(Tools.getTranslatedFormattedText(
                                "msg.spatialharvesters.dimensional_applicator_saved_effects", ChatFormatting.BLUE),
                                false);
                        if (data.contains(CustomValues.potionsNBTKey)) {
                            for (int id : data.getIntArray(CustomValues.potionsNBTKey)) {
                                Holder<MobEffect> effect = BuiltInRegistries.MOB_EFFECT.getHolder(id).orElse(null);
                                if (effect != null) {
                                    player.displayClientMessage(
                                            Tools.getTranslatedFormattedText(effect.value().getDescriptionId(), ChatFormatting.BOLD)
                                                    .copy()
                                                    .withStyle(Style.EMPTY.withColor(effect.value().getColor())),
                                            false);
                                }
                            }
                        }
                    }
                    player.displayClientMessage(Tools.getDividerText(), false);
                }
            }
        }
        return super.useWithoutItem(state, worldIn, pos, player, hit);
    }
}
