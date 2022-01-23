package dunkmania101.spatialharvesters.objects.items;

import java.util.List;

import javax.annotation.Nonnull;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.base.SpatialHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class ResourceDisablerKeyItem extends Item {
    public ResourceDisablerKeyItem(Properties properties) {
        super(properties);
    }

    @Override
    public float getDestroySpeed(@Nonnull ItemStack stack, @Nonnull BlockState state) {
        int multiplier = CommonConfig.KEY_BREAK_SPEED_MULTIPLIER.get();
        return super.getDestroySpeed(stack, state) * multiplier;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
        if (player.isCrouching()) {
            ItemStack otherStack = player.getOffhandItem();
            if (otherStack.isEmpty()) {
                player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.clear_disabled_resource", ChatFormatting.RED), true);
                itemstack.getOrCreateTag().remove(CustomValues.disabledResourceKey);
            } else {
                ResourceLocation rn = otherStack.getItem().getRegistryName();
                if (rn != null) {
                    player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_disabled_resource", ChatFormatting.BLUE), true);
                    itemstack.getOrCreateTag().putString(CustomValues.disabledResourceKey, rn.toString());
                }
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
                BlockEntity tile= world.getBlockEntity(pos);
                if (tile != null) {
                    if (tile instanceof SpatialHarvesterTE) {
                        CompoundTag disabledNBT = new CompoundTag();
                        if (player.isCrouching()) {
                            disabledNBT.putString(CustomValues.removeDisabledNBTKey, "");
                        } else {
                            CompoundTag itemNBT = context.getItemInHand().getTag();
                            if (itemNBT != null) {
                                if (itemNBT.contains(CustomValues.disabledResourceKey)) {
                                    disabledNBT.putString(CustomValues.disabledResourceKey, itemNBT.getString(CustomValues.disabledResourceKey));
                                }
                            }
                        }
                        if (disabledNBT.isEmpty()) {
                            player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_disabled_resource_failed", ChatFormatting.DARK_RED), true);
                        } else {
                            if (disabledNBT.contains(CustomValues.removeDisabledNBTKey)) {
                                player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.clear_disabled_resources", ChatFormatting.RED), true);
                            } else {
                                player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.add_disabled_resource", ChatFormatting.BLUE), true);
                            }
                            tile.deserializeNBT(Tools.stripTileNBT(disabledNBT));
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
        tooltip.addAll(Tools.getMultiLineText("msg.spatialharvesters.resource_disabler_key_description", ChatFormatting.GOLD));
        CompoundTag nbt = stack.getTag();
        if (nbt != null) {
            tooltip.add(Tools.getDividerText());
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.disabled_resource", ChatFormatting.RED));
            if (nbt.contains(CustomValues.disabledResourceKey)) {
                String resource = nbt.getString(CustomValues.disabledResourceKey);
                if (resource != null && !resource.isEmpty()) {
                    ResourceLocation rn = ResourceLocation.tryParse(resource);
                    if (rn != null) {
                        Item item = ForgeRegistries.ITEMS.getValue(rn);
                        if (item != null && item != Items.AIR) {
                            tooltip.add(Tools.getTranslatedFormattedText(item.getDescriptionId(), ChatFormatting.DARK_PURPLE, ChatFormatting.BOLD));
                        }
                    }
                }
            }
        }
        tooltip.add(Tools.getDividerText());
    }
}
