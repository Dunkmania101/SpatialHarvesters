package dunkmania101.spatialharvesters.objects.items;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.base.SpatialHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ResourceDisablerKeyItem extends Item {
    public ResourceDisablerKeyItem(Settings settings) {
        super(settings);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        int multiplier = CommonConfig.key_break_speed_multiplier;
        return super.getMiningSpeedMultiplier(stack, state) * multiplier;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (miner instanceof PlayerEntity player) {
            if (player.isSneaking()) {
                ItemStack otherStack = player.getOffHandStack();
                if (otherStack.isEmpty()) {
                    player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.clear_disabled_resource", Formatting.RED), true);
                    stack.getOrCreateNbt().remove(CustomValues.disabledResourceKey);
                } else {
                    player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_disabled_resource", Formatting.BLUE), true);
                    stack.getOrCreateNbt().putString(CustomValues.disabledResourceKey, Registries.ITEM.getId(otherStack.getItem()).toString());
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
            if (!world.isClient()) {
                BlockPos pos = context.getBlockPos();
                BlockEntity tile = world.getBlockEntity(pos);
                if (tile != null) {
                    if (tile instanceof SpatialHarvesterTE) {
                        NbtCompound disabledNBT = new NbtCompound();
                        if (player.isSneaking()) {
                            disabledNBT.putString(CustomValues.removeDisabledNBTKey, "");
                        } else {
                            NbtCompound itemNBT = context.getStack().getNbt();
                            if (itemNBT != null) {
                                if (itemNBT.contains(CustomValues.disabledResourceKey)) {
                                    disabledNBT.putString(CustomValues.disabledResourceKey, itemNBT.getString(CustomValues.disabledResourceKey));
                                }
                            }
                        }
                        if (disabledNBT.isEmpty()) {
                            player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_disabled_resource_failed", Formatting.DARK_RED), true);
                        } else {
                            if (disabledNBT.contains(CustomValues.removeDisabledNBTKey)) {
                                player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.clear_disabled_resources", Formatting.RED), true);
                            } else {
                                player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.add_disabled_resource", Formatting.BLUE), true);
                            }
                            tile.readNbt(Tools.correctTileNBT(tile, disabledNBT));
                        }
                    }
                }
            }
        }
        return super.useOnBlock(context);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, java.util.List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.addAll(Tools.getMultiLineText("msg.spatialharvesters.resource_disabler_key_description", Formatting.GOLD));
        NbtCompound nbt = stack.getNbt();
        if (nbt != null) {
            tooltip.add(Tools.getDividerText());
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.disabled_resource", Formatting.RED));
            if (nbt.contains(CustomValues.disabledResourceKey)) {
                String resource = nbt.getString(CustomValues.disabledResourceKey);
                if (resource != null && !resource.isEmpty()) {
                    Identifier rn = Identifier.tryParse(resource);
                    if (rn != null) {
                        Item item = Registries.ITEM.get(rn);
                        if (item != Items.AIR) {
                            tooltip.add(Tools.getTranslatedFormattedText(item.getTranslationKey(), Formatting.DARK_PURPLE, Formatting.BOLD));
                        }
                    }
                }
            }
        }
        tooltip.add(Tools.getDividerText());
    }
}
