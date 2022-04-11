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
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;

public class PreservedDataCustomHorizontalShapedBlock extends CustomHorizontalShapedBlock {
    private CompoundTag thisTileNBT = new CompoundTag();

    public PreservedDataCustomHorizontalShapedBlock(Properties properties, VoxelShape shape, Direction frontDirection) {
        super(properties, shape, frontDirection);
    }

    @Override
    public void playerWillDestroy(Level worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state,
            @Nonnull Player player) {
        BlockEntity tile = worldIn.getBlockEntity(pos);
        if (tile != null) {
            this.thisTileNBT = tile.serializeNBT();
        }
        super.playerWillDestroy(worldIn, pos, state, player);
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
    public void setPlacedBy(@Nonnull Level worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state,
            LivingEntity placer, @Nonnull ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isClientSide()) {
            BlockEntity tile = worldIn.getBlockEntity(pos);
            CompoundTag stackTileNBT = stack.getTagElement(CustomValues.stackTileNBTKey);
            if (tile != null && stackTileNBT != null) {
                if (!stackTileNBT.isEmpty()) {
                    tile.deserializeNBT(Tools.stripTileNBT(stackTileNBT));
                }
            }
        }
    }

    @Nonnull
    @Override
    public InteractionResult use(@Nonnull BlockState state, Level worldIn, @Nonnull BlockPos pos,
            @Nonnull Player player, @Nonnull InteractionHand handIn, @Nonnull BlockHitResult hit) {
        if (player.isCrouching()) {
            if (worldIn.isClientSide()) {
                return InteractionResult.SUCCESS;
            } else {
                BlockEntity tile = worldIn.getBlockEntity(pos);
                if (tile != null) {
                    player.displayClientMessage(Tools.getDividerText(), false);
                    CompoundTag data = tile.serializeNBT();
                    if (tile instanceof CustomEnergyMachineTE) {
                        player.displayClientMessage(Tools.getTranslatedFormattedText(
                                "msg.spatialharvesters.energy_message", ChatFormatting.DARK_GREEN), false);
                        player.displayClientMessage(
                                new TextComponent(Integer.toString(data.getInt(CustomValues.energyStorageKey)))
                                        .withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD),
                                false);
                    }
                    if (tile instanceof TickingRedstoneEnergyMachineTE) {
                        if (data.contains(CustomValues.countedTicksKey)) {
                            player.displayClientMessage(Tools.getDividerText(), false);
                            player.displayClientMessage(Tools.getTranslatedFormattedText(
                                    "msg.spatialharvesters.counted_ticks_message", ChatFormatting.YELLOW), false);
                            int countedTicks = data.getInt(CustomValues.countedTicksKey);
                            player.displayClientMessage(new TextComponent(Integer.toString(countedTicks))
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
                                Item item = ForgeRegistries.ITEMS
                                        .getValue(new ResourceLocation(disabledResources.getString(key)));
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
                            ItemStack weaponStack = ItemStack.of(weapon);
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
                            for (String id : data.getCompound(CustomValues.potionsNBTKey).getAllKeys()) {
                                Potion effect = Potion.byName(id);
                                if (effect != null) {
                                    player.displayClientMessage(
                                            Tools.getTranslatedFormattedText(effect.getName(""), ChatFormatting.BOLD)
                                                    .copy()
                                                    .withStyle(Style.EMPTY.withColor(PotionUtils.getColor(effect))),
                                            false);
                                }
                            }
                        }
                    }
                    player.displayClientMessage(Tools.getDividerText(), false);
                }
            }
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }
}
