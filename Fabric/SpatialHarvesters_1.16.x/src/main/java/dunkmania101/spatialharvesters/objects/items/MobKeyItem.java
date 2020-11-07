package dunkmania101.spatialharvesters.objects.items;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.blocks.SpecificMobHarvesterBlockPreservedData;
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
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
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
            String entityKey = target.getType().getTranslationKey();
            if (entityKey != null && !entityKey.isEmpty()) {
                Identifier mobRN = Identifier.tryParse(entityKey);
                if (mobRN != null) {
                    ArrayList<ArrayList<String>> blacklist_mobs = CommonConfig.BLACKLIST_MOBS.get();
                    ArrayList<String> blacklist_mobs_mod = CommonConfig.BLACKLIST_MOBS_MOD.get();
                    boolean banned = false;
                    if (Tools.isResourceBanned(mobRN, blacklist_mobs, blacklist_mobs_mod)) {
                        banned = true;
                    } else {
                        stack.getOrCreateTag().putString(CustomValues.entityNBTKey, entityKey);
                    }
                    if (attacker instanceof PlayerEntity) {
                        PlayerEntity player = (PlayerEntity) attacker;
                        if (banned) {
                            player.sendMessage(new TranslatableText("msg.spatialharvesters.set_mob_key_entity_failed"), true);
                        } else {
                            player.sendMessage(new TranslatableText("msg.spatialharvesters.set_mob_key_entity"), true);
                        }
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
            if (!world.isClient) {
                BlockPos pos = context.getBlockPos();
                Block block = world.getBlockState(pos).getBlock();
                if (block instanceof SpecificMobHarvesterBlockPreservedData) {
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
                                player.sendMessage(new TranslatableText("msg.spatialharvesters.set_mob_harvester_failed"), true);
                            } else {
                                if (harvesterNBT.contains(CustomValues.removeEntityNBTKey)) {
                                    player.sendMessage(new TranslatableText("msg.spatialharvesters.clear_mob_harvester"), true);
                                } else {
                                    player.sendMessage(new TranslatableText("msg.spatialharvesters.set_mob_harvester"), true);
                                }
                                tile.fromTag(context.getWorld().getBlockState(pos), Tools.correctTileNBT(tile, harvesterNBT));
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
        tooltip.add(new TranslatableText("msg.spatialharvesters.mob_key_description"));
        CompoundTag nbt = stack.getTag();
        if (nbt != null) {
            tooltip.add(new TranslatableText("msg.spatialharvesters.divider"));
            tooltip.add(new TranslatableText("msg.spatialharvesters.mob_key_bound_mob"));
            if (nbt.contains(CustomValues.entityNBTKey)) {
                String entity = nbt.getString(CustomValues.entityNBTKey);
                if (entity != null && !entity.isEmpty()) {
                    Optional<EntityType<?>> optionalEntityType = EntityType.get(entity);
                    if (optionalEntityType.isPresent()) {
                        EntityType<?> entityType = optionalEntityType.get();
                        tooltip.add(entityType.getName());
                    }
                }
            }
        }
        tooltip.add(new TranslatableText("msg.spatialharvesters.divider"));
    }
}