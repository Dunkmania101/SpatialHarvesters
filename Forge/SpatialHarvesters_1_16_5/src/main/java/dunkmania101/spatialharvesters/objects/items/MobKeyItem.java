package dunkmania101.spatialharvesters.objects.items;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.blocks.SpecificMobHarvesterBlock;
import dunkmania101.spatialharvesters.objects.tile_entities.SpecificMobHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MobKeyItem extends Item {
    public MobKeyItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hitEntity(@Nonnull ItemStack stack, @Nonnull LivingEntity target, LivingEntity attacker) {
        if (attacker.isCrouching()) {
            ResourceLocation mobRN = target.getType().getRegistryName();
            if (mobRN != null) {
                ArrayList<ArrayList<String>> blacklist_mobs = CommonConfig.BLACKLIST_MOBS.get();
                ArrayList<String> blacklist_mobs_mod = CommonConfig.BLACKLIST_MOBS_MOD.get();
                boolean banned = false;
                if (Tools.isResourceBanned(mobRN, blacklist_mobs, blacklist_mobs_mod)) {
                    banned = true;
                } else {
                    String id = target.getEntityString();
                    if (!StringUtils.isNullOrEmpty(id)) {
                        stack.getOrCreateTag().putString(CustomValues.entityNBTKey, id);
                    }
                }
                if (attacker instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) attacker;
                    if (banned) {
                        player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_mob_key_entity_failed", TextFormatting.DARK_RED), true);
                    } else {
                        player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_mob_key_entity", TextFormatting.BLUE), true);
                    }
                }
            }
        }
        return super.hitEntity(stack, target, attacker);
    }

    @Nonnull
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            World world = context.getWorld();
            if (!world.isRemote()) {
                BlockPos pos = context.getPos();
                Block block = world.getBlockState(pos).getBlock();
                if (block instanceof SpecificMobHarvesterBlock) {
                    TileEntity tile = world.getTileEntity(pos);
                    if (tile != null) {
                        if (tile instanceof SpecificMobHarvesterTE) {
                            CompoundNBT harvesterNBT = new CompoundNBT();
                            if (player.isSneaking()) {
                                harvesterNBT.putString(CustomValues.removeEntityNBTKey, "");
                            } else {
                                CompoundNBT itemNBT = context.getItem().getTag();
                                if (itemNBT != null) {
                                    if (itemNBT.contains(CustomValues.entityNBTKey)) {
                                        harvesterNBT.putString(CustomValues.entityNBTKey, itemNBT.getString(CustomValues.entityNBTKey));
                                    }
                                }
                            }
                            if (harvesterNBT.isEmpty()) {
                                player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_mob_harvester_failed", TextFormatting.DARK_RED), true);
                            } else {
                                if (harvesterNBT.contains(CustomValues.removeEntityNBTKey)) {
                                    player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.clear_mob_harvester", TextFormatting.RED), true);
                                } else {
                                    player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_mob_harvester", TextFormatting.BLUE), true);
                                }
                                tile.read(context.getWorld().getBlockState(pos), Tools.correctTileNBT(tile, harvesterNBT));
                            }
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
        tooltip.addAll(Tools.getMultiLineText("msg.spatialharvesters.mob_key_description", TextFormatting.GOLD));
        CompoundNBT nbt = stack.getTag();
        if (nbt != null) {
            tooltip.add(Tools.getDividerText());
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.mob_key_bound_mob", TextFormatting.DARK_RED));
            String entityMSG = "msg.spatialharvesters.none";
            if (nbt.contains(CustomValues.entityNBTKey)) {
                entityMSG = "msg.spatialharvesters.invalid";
                String entity = nbt.getString(CustomValues.entityNBTKey);
                if (!entity.isEmpty()) {
                    Optional<EntityType<?>> optionalEntityType = EntityType.byKey(entity);
                    if (optionalEntityType.isPresent()) {
                        entityMSG = optionalEntityType.get().getTranslationKey();
                    }
                }
            }
            tooltip.add(Tools.getTranslatedFormattedText(entityMSG, TextFormatting.RED, TextFormatting.BOLD));
        }
        tooltip.add(Tools.getDividerText());
    }
}
