package dunkmania101.spatialharvesters.objects.items;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.MobHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WeaponKeyItem extends Item {
    public WeaponKeyItem(Settings settings) {
        super(settings);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        int multiplier = CommonConfig.key_break_speed_multiplier;
        return super.getMiningSpeedMultiplier(stack, state) * multiplier;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (miner instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) miner;
            if (player.isSneaking()) {
                ItemStack otherStack = player.getOffHandStack();
                if (otherStack.isEmpty()) {
                    stack.getOrCreateTag().remove(CustomValues.weaponNBTKey);
                    player.sendMessage(new TranslatableText("msg.spatialharvesters.clear_weapon_key_weapon"), true);
                } else {
                    stack.getOrCreateTag().put(CustomValues.weaponNBTKey, otherStack.toTag(new CompoundTag()));
                    player.sendMessage(new TranslatableText("msg.spatialharvesters.set_weapon_key_weapon"), true);
                }
            }
        }
        return true;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            World world = context.getWorld();
            if (!world.isClient) {
                BlockPos pos = context.getBlockPos();
                BlockEntity tile = world.getBlockEntity(pos);
                if (tile != null) {
                    if (tile instanceof MobHarvesterTE) {
                        CompoundTag harvesterNBT = new CompoundTag();
                        if (player.isSneaking()) {
                            harvesterNBT.putString(CustomValues.removeWeaponNBTKey, "");
                        } else {
                            CompoundTag itemNBT = context.getStack().getTag();
                            if (itemNBT != null) {
                                if (itemNBT.contains(CustomValues.weaponNBTKey)) {
                                    harvesterNBT.put(CustomValues.weaponNBTKey, itemNBT.getCompound(CustomValues.weaponNBTKey));
                                }
                            }
                        }
                        if (harvesterNBT.isEmpty()) {
                            player.sendMessage(new TranslatableText("msg.spatialharvesters.set_mob_harvester_failed"), true);
                        } else {
                            if (harvesterNBT.contains(CustomValues.removeWeaponNBTKey)) {
                                player.sendMessage(new TranslatableText("msg.spatialharvesters.clear_mob_harvester"), true);
                            } else {
                                player.sendMessage(new TranslatableText("msg.spatialharvesters.set_mob_harvester"), true);
                            }
                            tile.fromTag(world.getBlockState(pos), Tools.correctTileNBT(tile, harvesterNBT));
                        }
                    }
                }
            }
        }
        return super.useOnBlock(context);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("msg.spatialharvesters.weapon_key_description"));
        CompoundTag nbt = stack.getTag();
        if (nbt != null) {
            tooltip.add(new TranslatableText("msg.spatialharvesters.divider"));
            tooltip.add(new TranslatableText("msg.spatialharvesters.weapon_key_bound_weapon"));
            if (nbt.contains(CustomValues.weaponNBTKey)) {
                CompoundTag weaponNBT = nbt.getCompound(CustomValues.weaponNBTKey);
                if (!weaponNBT.isEmpty()) {
                    ItemStack weapon = ItemStack.fromTag(weaponNBT);
                    if (!weapon.isEmpty()) {
                        tooltip.add(weapon.getName());
                    }
                }
            }
        }
        tooltip.add(new TranslatableText("msg.spatialharvesters.divider"));
    }
}
