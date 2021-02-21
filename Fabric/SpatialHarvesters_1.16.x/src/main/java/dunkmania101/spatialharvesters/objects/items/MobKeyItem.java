package dunkmania101.spatialharvesters.objects.items;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.blocks.SpecificMobHarvesterBlock;
import dunkmania101.spatialharvesters.objects.tile_entities.SpecificMobHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MobKeyItem extends Item {
    public MobKeyItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker.isSneaking()) {
            Identifier mobRN = EntityType.getId(target.getType());
            if (mobRN != null) {
                ArrayList<ArrayList<String>> blacklist_mobs = CommonConfig.blacklist_mobs;
                ArrayList<String> blacklist_mobs_mod = CommonConfig.blacklist_mobs_mod;
                boolean banned = false;
                if (Tools.isResourceBanned(mobRN, blacklist_mobs, blacklist_mobs_mod)) {
                    banned = true;
                } else {
                    stack.getOrCreateTag().putString(CustomValues.entityNBTKey, mobRN.toString());
                }
                if (attacker instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) attacker;
                    if (banned) {
                        player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_mob_key_entity_failed", Formatting.DARK_RED), true);
                    } else {
                        player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_mob_key_entity", Formatting.BLUE), true);
                    }
                }
            }
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            World world = context.getWorld();
            if (world != null && !world.isClient()) {
                BlockPos pos = context.getBlockPos();
                Block block = world.getBlockState(pos).getBlock();
                if (block instanceof SpecificMobHarvesterBlock) {
                    BlockEntity tile = world.getBlockEntity(pos);
                    if (tile != null) {
                        if (tile instanceof SpecificMobHarvesterTE) {
                            CompoundTag harvesterNBT = new CompoundTag();
                            if (player.isSneaking()) {
                                harvesterNBT.putString(CustomValues.removeEntityNBTKey, "");
                            } else {
                                CompoundTag itemNBT = context.getStack().getTag();
                                if (itemNBT != null) {
                                    if (itemNBT.contains(CustomValues.entityNBTKey)) {
                                        harvesterNBT.putString(CustomValues.entityNBTKey, itemNBT.getString(CustomValues.entityNBTKey));
                                    }
                                }
                            }
                            if (harvesterNBT.isEmpty()) {
                                player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_mob_harvester_failed", Formatting.DARK_RED), true);
                            } else {
                                if (harvesterNBT.contains(CustomValues.removeEntityNBTKey)) {
                                    player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.clear_mob_harvester", Formatting.RED), true);
                                } else {
                                    player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_mob_harvester", Formatting.BLUE), true);
                                }
                                tile.fromTag(world.getBlockState(pos), Tools.correctTileNBT(tile, harvesterNBT));
                                return ActionResult.SUCCESS;
                            }
                        }
                    }
                }
            }
        }
        return super.useOnBlock(context);
    }

    @Override
    public void appendTooltip(ItemStack stack, World worldIn, List<Text> tooltip, TooltipContext flagIn) {
        super.appendTooltip(stack, worldIn, tooltip, flagIn);
        tooltip.addAll(Tools.getMultiLineText("msg.spatialharvesters.mob_key_description", Formatting.GOLD));
        CompoundTag nbt = stack.getTag();
        if (nbt != null) {
            tooltip.add(Tools.getDividerText());
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.mob_key_bound_mob", Formatting.DARK_RED));
            String entityMSG = "msg.spatialharvesters.none";
            if (nbt.contains(CustomValues.entityNBTKey)) {
                entityMSG = "msg.spatialharvesters.invalid";
                String entity = nbt.getString(CustomValues.entityNBTKey);
                if (entity != null && !entity.isEmpty()) {
                    Optional<EntityType<?>> optionalEntityType = EntityType.get(entity);
                    if (optionalEntityType.isPresent()) {
                        entityMSG = optionalEntityType.get().getTranslationKey();
                    }
                }
            }
            tooltip.add(Tools.getTranslatedFormattedText(entityMSG, Formatting.RED, Formatting.BOLD));
        }
        tooltip.add(Tools.getDividerText());
    }
}
