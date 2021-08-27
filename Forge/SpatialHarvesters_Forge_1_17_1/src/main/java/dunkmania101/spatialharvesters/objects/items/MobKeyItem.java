package dunkmania101.spatialharvesters.objects.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.blocks.SpecificMobHarvesterBlock;
import dunkmania101.spatialharvesters.objects.tile_entities.SpecificMobHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public class MobKeyItem extends Item {
    public MobKeyItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hurtEnemy(@Nonnull ItemStack stack, @Nonnull LivingEntity target, LivingEntity attacker) {
        if (attacker.isCrouching()) {
            ResourceLocation mobRN = target.getType().getRegistryName();
            if (mobRN != null) {
                ArrayList<ArrayList<String>> blacklist_mobs = CommonConfig.BLACKLIST_MOBS.get();
                ArrayList<String> blacklist_mobs_mod = CommonConfig.BLACKLIST_MOBS_MOD.get();
                boolean banned = false;
                if (Tools.isResourceBanned(mobRN, blacklist_mobs, blacklist_mobs_mod)) {
                    banned = true;
                } else {
                    String id = target.getEncodeId();
                    if (id != null && !id.isEmpty()) {
                        stack.getOrCreateTag().putString(CustomValues.entityNBTKey, id);
                    }
                }
                if (attacker instanceof Player) {
                    Player player = (Player) attacker;
                    if (banned) {
                        player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_mob_key_entity_failed", ChatFormatting.DARK_RED), true);
                    } else {
                        player.displayClientMessage(getDescription(), banned);
                    }
                }
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Nonnull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player != null) {
            Level world = context.getLevel();
            if (!world.isClientSide()) {
                BlockPos pos = context.getClickedPos();
                Block block = world.getBlockState(pos).getBlock();
                if (block instanceof SpecificMobHarvesterBlock) {
                    BlockEntity tile = world.getBlockEntity(pos);
                    if (tile != null) {
                        if (tile instanceof SpecificMobHarvesterTE) {
                            CompoundTag harvesterNBT = new CompoundTag();
                            if (player.isCrouching()) {
                                harvesterNBT.putString(CustomValues.removeEntityNBTKey, "");
                            } else {
                                CompoundTag itemNBT = context.getItemInHand().getTag();
                                if (itemNBT != null) {
                                    if (itemNBT.contains(CustomValues.entityNBTKey)) {
                                        harvesterNBT.putString(CustomValues.entityNBTKey, itemNBT.getString(CustomValues.entityNBTKey));
                                    }
                                }
                            }
                            if (harvesterNBT.isEmpty()) {
                                player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_mob_harvester_failed", ChatFormatting.DARK_RED), true);
                            } else {
                                if (harvesterNBT.contains(CustomValues.removeEntityNBTKey)) {
                                    player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.clear_mob_harvester", ChatFormatting.RED), true);
                                } else {
                                    player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_mob_harvester", ChatFormatting.BLUE), true);
                                }
                                tile.load(Tools.stripTileNBT(harvesterNBT));
                            }
                        }
                    }
                }
            }
        }
        return super.useOn(context);
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, Level worldIn, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.addAll(Tools.getMultiLineText("msg.spatialharvesters.mob_key_description", ChatFormatting.GOLD));
        CompoundTag nbt = stack.getTag();
        if (nbt != null) {
            tooltip.add(Tools.getDividerText());
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.mob_key_bound_mob", ChatFormatting.DARK_RED));
            String entityMSG = "msg.spatialharvesters.none";
            if (nbt.contains(CustomValues.entityNBTKey)) {
                entityMSG = "msg.spatialharvesters.invalid";
                String entity = nbt.getString(CustomValues.entityNBTKey);
                if (!entity.isEmpty()) {
                    Optional<EntityType<?>> optionalEntityType = EntityType.byString(entity);
                    if (optionalEntityType.isPresent()) {
                        entityMSG = optionalEntityType.get().getDescriptionId();
                    }
                }
            }
            tooltip.add(Tools.getTranslatedFormattedText(entityMSG, ChatFormatting.RED, ChatFormatting.BOLD));
        }
        tooltip.add(Tools.getDividerText());
    }
}
