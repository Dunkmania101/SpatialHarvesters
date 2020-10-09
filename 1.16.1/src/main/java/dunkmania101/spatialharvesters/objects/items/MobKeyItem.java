package dunkmania101.spatialharvesters.objects.items;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.MobHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class MobKeyItem extends Item {
    public MobKeyItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker.isCrouching()) {
            ResourceLocation mobRN = target.getType().getRegistryName();
            if (mobRN != null) {
                ArrayList<ArrayList<String>> blacklist_mobs = CommonConfig.BLACKLIST_MOBS.get();
                ArrayList<String> blacklist_mobs_mod = CommonConfig.BLACKLIST_MOBS_MOD.get();
                ArrayList<String> modMob = new ArrayList<>();
                String modid = mobRN.getNamespace();
                modMob.add(modid);
                modMob.add(mobRN.getPath());
                boolean banned = false;
                if (!blacklist_mobs.contains(modMob) && !blacklist_mobs_mod.contains(modid)) {
                    stack.getOrCreateTag().putString(CustomValues.entityNBTKey, target.serializeNBT().getString("id"));
                } else {
                    banned = true;
                }
                if (attacker instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) attacker;
                    if (banned) {
                        player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.set_mob_key_entity_failed"), true);
                    } else {
                        player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.set_mob_key_entity"), true);
                    }
                }
            }
        }
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
        if (player.isCrouching()) {
            ItemStack other_stack = player.getHeldItemOffhand();
            if (other_stack.isEmpty()) {
                player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.clear_mob_key_weapon"), true);
                itemstack.getOrCreateTag().remove(CustomValues.weaponNBTKey);
            } else {
                player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.set_mob_key_weapon"), true);
                itemstack.getOrCreateTag().put(CustomValues.weaponNBTKey, other_stack.serializeNBT());
            }
        }
        return true;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            TileEntity tile = context.getWorld().getTileEntity(context.getPos());
            if (tile != null) {
                if (tile instanceof MobHarvesterTE) {
                    CompoundNBT harvesterNBT = new CompoundNBT();
                    if (player.isCrouching()) {
                        harvesterNBT.putString(CustomValues.removeEntityNBTKey, "");
                    } else {
                        CompoundNBT itemNBT = context.getItem().getTag();
                        if (itemNBT != null) {
                            if (itemNBT.contains(CustomValues.entityNBTKey)) {
                                harvesterNBT.putString(CustomValues.entityNBTKey, itemNBT.getString(CustomValues.entityNBTKey));
                            }
                            if (itemNBT.contains(CustomValues.weaponNBTKey)) {
                                harvesterNBT.put(CustomValues.weaponNBTKey, itemNBT.getCompound(CustomValues.weaponNBTKey));
                            }
                        }
                    }
                    if (harvesterNBT.isEmpty()) {
                        player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.set_mob_harvester_failed"), true);
                    } else {
                        if (harvesterNBT.contains(CustomValues.removeEntityNBTKey)) {
                            player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.clear_mob_harvester"), true);
                        } else {
                            harvesterNBT.putString(CustomValues.playerNameNBTKey, player.getName().getString());
                            player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.set_mob_harvester"), true);
                        }
                        tile.deserializeNBT(Tools.correctTileNBT(tile, harvesterNBT));
                    }
                }
            }
        }
        return super.onItemUse(context);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("msg.spatialharvesters.effect_key_description"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
