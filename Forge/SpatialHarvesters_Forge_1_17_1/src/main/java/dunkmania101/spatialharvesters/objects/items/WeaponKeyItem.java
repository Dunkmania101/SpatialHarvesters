package dunkmania101.spatialharvesters.objects.items;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.MobHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class WeaponKeyItem extends Item {
    public WeaponKeyItem(Properties properties) {
        super(properties);
    }

    @Override
    public float getDestroySpeed(@Nonnull ItemStack stack, @Nonnull BlockState state) {
        int multiplier = CommonConfig.KEY_BREAK_SPEED_MULTIPLIER.get();
        return super.getDestroySpeed(stack, state) * multiplier;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, PlayerEntity player) {
        if (player.isCrouching()) {
            ItemStack otherStack = player.getHeldItemOffhand();
            if (otherStack.isEmpty()) {
                stack.getOrCreateTag().remove(CustomValues.weaponNBTKey);
                player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.clear_weapon_key_weapon", TextFormatting.RED), true);
            } else {
                stack.getOrCreateTag().put(CustomValues.weaponNBTKey, otherStack.serializeNBT());
                player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_weapon_key_weapon", TextFormatting.BLUE), true);
            }
        }
        return true;
    }

    @Nonnull
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            World world = context.getWorld();
            if (!world.isRemote()) {
                BlockPos pos = context.getPos();
                TileEntity tile = world.getTileEntity(pos);
                if (tile != null) {
                    if (tile instanceof MobHarvesterTE) {
                        CompoundNBT harvesterNBT = new CompoundNBT();
                        if (player.isCrouching()) {
                            harvesterNBT.putString(CustomValues.removeWeaponNBTKey, "");
                        } else {
                            CompoundNBT itemNBT = context.getItem().getTag();
                            if (itemNBT != null) {
                                if (itemNBT.contains(CustomValues.weaponNBTKey)) {
                                    harvesterNBT.put(CustomValues.weaponNBTKey, itemNBT.getCompound(CustomValues.weaponNBTKey));
                                }
                            }
                        }
                        if (harvesterNBT.isEmpty()) {
                            player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_mob_harvester_failed", TextFormatting.DARK_RED), true);
                        } else {
                            if (harvesterNBT.contains(CustomValues.removeWeaponNBTKey)) {
                                player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.clear_mob_harvester", TextFormatting.RED), true);
                            } else {
                                player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_mob_harvester", TextFormatting.BLUE), true);
                            }
                            tile.deserializeNBT(Tools.correctTileNBT(tile, harvesterNBT));
                        }
                    }
                }
            }
        }
        return super.onItemUse(context);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.addAll(Tools.getMultiLineText("msg.spatialharvesters.weapon_key_description", TextFormatting.GOLD));
        CompoundNBT nbt = stack.getTag();
        if (nbt != null) {
            tooltip.add(Tools.getDividerText());
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.weapon_key_bound_weapon", TextFormatting.DARK_GRAY));
            if (nbt.contains(CustomValues.weaponNBTKey)) {
                CompoundNBT weaponNBT = nbt.getCompound(CustomValues.weaponNBTKey);
                if (!weaponNBT.isEmpty()) {
                    ItemStack weapon = ItemStack.read(weaponNBT);
                    if (!weapon.isEmpty()) {
                        tooltip.add(weapon.getDisplayName().copyRaw().mergeStyle(TextFormatting.GRAY));
                    }
                }
            }
        }
        tooltip.add(Tools.getDividerText());
    }
}
