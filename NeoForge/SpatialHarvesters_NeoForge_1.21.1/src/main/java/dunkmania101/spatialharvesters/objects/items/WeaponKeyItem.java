package dunkmania101.spatialharvesters.objects.items;

import java.util.List;

import javax.annotation.Nonnull;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.items.base.LeftClickBindingItem;
import dunkmania101.spatialharvesters.objects.tile_entities.MobHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WeaponKeyItem extends Item implements LeftClickBindingItem {
    public WeaponKeyItem(Properties properties) {
        super(properties);
    }

    @Override
    public float getDestroySpeed(@Nonnull ItemStack stack, @Nonnull BlockState state) {
        int multiplier = CommonConfig.KEY_BREAK_SPEED_MULTIPLIER.get();
        return super.getDestroySpeed(stack, state) * multiplier;
    }

    @Override
    public boolean onLeftClickBlock(ItemStack stack, BlockPos pos, Player player) {
        if (player.isCrouching()) {
            ItemStack otherStack = player.getOffhandItem();
            if (otherStack.isEmpty()) {
                Tools.mutateCustomData(stack, data -> data.remove(CustomValues.weaponNBTKey));
                player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.clear_weapon_key_weapon", ChatFormatting.RED), true);
            } else {
                CompoundTag weaponNBT = (CompoundTag) otherStack.save(player.registryAccess());
                Tools.mutateCustomData(stack, data -> data.put(CustomValues.weaponNBTKey, weaponNBT));
                player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_weapon_key_weapon", ChatFormatting.BLUE), true);
            }
        }
        return true;
    }

    @Nonnull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player != null) {
            Level world = context.getLevel();
            if (!world.isClientSide()) {
                BlockPos pos = context.getClickedPos();
                BlockEntity tile = world.getBlockEntity(pos);
                if (tile != null) {
                    if (tile instanceof MobHarvesterTE) {
                        CompoundTag harvesterNBT = new CompoundTag();
                        if (player.isCrouching()) {
                            harvesterNBT.putString(CustomValues.removeWeaponNBTKey, "");
                        } else {
                            CompoundTag itemNBT = Tools.getCustomData(context.getItemInHand());
                            if (itemNBT.contains(CustomValues.weaponNBTKey)) {
                                harvesterNBT.put(CustomValues.weaponNBTKey, itemNBT.getCompound(CustomValues.weaponNBTKey));
                            }
                        }
                        if (harvesterNBT.isEmpty()) {
                            player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_mob_harvester_failed", ChatFormatting.DARK_RED), true);
                        } else {
                            if (harvesterNBT.contains(CustomValues.removeWeaponNBTKey)) {
                                player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.clear_mob_harvester", ChatFormatting.RED), true);
                            } else {
                                player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_mob_harvester", ChatFormatting.BLUE), true);
                            }
                            ((MobHarvesterTE) tile).setDeserializedValues(Tools.stripTileNBT(harvesterNBT));
                            tile.setChanged();
                        }
                    }
                }
            }
        }
        return super.useOn(context);
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nonnull TooltipContext context, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flagIn) {
        super.appendHoverText(stack, context, tooltip, flagIn);
        tooltip.addAll(Tools.getMultiLineText("msg.spatialharvesters.weapon_key_description", ChatFormatting.GOLD));
        CompoundTag nbt = Tools.getCustomData(stack);
        tooltip.add(Tools.getDividerText());
        tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.weapon_key_bound_weapon", ChatFormatting.DARK_GRAY));
        if (nbt.contains(CustomValues.weaponNBTKey)) {
            CompoundTag weaponNBT = nbt.getCompound(CustomValues.weaponNBTKey);
            if (!weaponNBT.isEmpty()) {
                ItemStack weapon = ItemStack.parseOptional(context.registries(), weaponNBT);
                if (!weapon.isEmpty()) {
                    tooltip.add(weapon.getDisplayName().copy().withStyle(ChatFormatting.GRAY));
                }
            }
        }
        tooltip.add(Tools.getDividerText());
    }
}
