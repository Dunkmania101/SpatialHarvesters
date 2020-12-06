package dunkmania101.spatialharvesters.objects.items;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.SpatialHarvesterTE;
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
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
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
        if (miner instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) miner;
            if (player.isSneaking()) {
                ItemStack otherStack = player.getOffHandStack();
                if (otherStack.isEmpty()) {
                    player.sendMessage(new TranslatableText("msg.spatialharvesters.clear_disabled_resource"), true);
                    stack.getOrCreateTag().remove(CustomValues.disabledResourceKey);
                } else {
                    Identifier rn = Identifier.tryParse(otherStack.getItem().getTranslationKey());
                    if (rn != null) {
                        player.sendMessage(new TranslatableText("msg.spatialharvesters.set_disabled_resource"), true);
                        stack.getOrCreateTag().putString(CustomValues.disabledResourceKey, rn.toString());
                    }
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
                    if (tile instanceof SpatialHarvesterTE) {
                        CompoundTag disabledNBT = new CompoundTag();
                        if (player.isSneaking()) {
                            disabledNBT.putString(CustomValues.removeDisabledNBTKey, "");
                        } else {
                            CompoundTag itemNBT = context.getStack().getTag();
                            if (itemNBT != null) {
                                if (itemNBT.contains(CustomValues.disabledResourceKey)) {
                                    disabledNBT.putString(CustomValues.disabledResourceKey, itemNBT.getString(CustomValues.disabledResourceKey));
                                }
                            }
                        }
                        if (disabledNBT.isEmpty()) {
                            player.sendMessage(new TranslatableText("msg.spatialharvesters.set_disabled_resource_failed"), true);
                        } else {
                            if (disabledNBT.contains(CustomValues.removeDisabledNBTKey)) {
                                player.sendMessage(new TranslatableText("msg.spatialharvesters.clear_disabled_resources"), true);
                            } else {
                                player.sendMessage(new TranslatableText("msg.spatialharvesters.add_disabled_resource"), true);
                            }
                            tile.fromTag(world.getBlockState(pos), Tools.correctTileNBT(tile, disabledNBT));
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
        tooltip.add(new TranslatableText("msg.spatialharvesters.resource_disabler_key_description"));
        CompoundTag nbt = stack.getTag();
        if (nbt != null) {
            tooltip.add(new TranslatableText("msg.spatialharvesters.divider"));
            tooltip.add(new TranslatableText("msg.spatialharvesters.disabled_resource"));
            if (nbt.contains(CustomValues.disabledResourceKey)) {
                String resource = nbt.getString(CustomValues.disabledResourceKey);
                if (resource != null && !resource.isEmpty()) {
                    Item item = Registry.ITEM.get(new Identifier(resource));
                    if (item != Items.AIR) {
                        tooltip.add(item.getName());
                    }
                }
            }
        }
        tooltip.add(new TranslatableText("msg.spatialharvesters.divider"));
    }
}
