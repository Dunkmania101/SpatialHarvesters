package dunkmania101.spatialharvesters.objects.items;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.DimensionalApplicatorTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
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

import java.util.ArrayList;
import java.util.List;

public class EffectKeyItem extends Item {
    public EffectKeyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (!world.isClient) {
            BlockPos pos = context.getBlockPos();
            BlockEntity tile = world.getBlockEntity(pos);
            if (tile != null) {
                if (tile instanceof DimensionalApplicatorTE) {
                    PlayerEntity player = context.getPlayer();
                    if (player != null) {
                        CompoundTag potionsNBT = new CompoundTag();
                        ArrayList<Integer> effects = new ArrayList<>();
                        boolean doContinue = true;
                        if (player.isSneaking()) {
                            potionsNBT.putString(CustomValues.removePotionsNBTKey, "");
                            player.sendMessage(new TranslatableText("msg.spatialharvesters.remove_dimensional_applicator_nbt_effects"), true);
                        } else {
                            for (StatusEffectInstance effectInstance : player.getActiveStatusEffects().values()) {
                                effects.add(StatusEffect.getRawId(effectInstance.getEffectType()));
                            }
                            if (effects.size() > 0) {
                                player.sendMessage(new TranslatableText("msg.spatialharvesters.set_dimensional_applicator_nbt_effects"), true);
                            } else {
                                doContinue = false;
                                player.sendMessage(new TranslatableText("msg.spatialharvesters.remove_dimensional_applicator_nbt_effects_failed"), true);
                            }
                        }
                        if (doContinue) {
                            potionsNBT.putIntArray(CustomValues.potionsNBTKey, effects);
                            tile.fromTag(context.getWorld().getBlockState(pos), Tools.correctTileNBT(tile, potionsNBT));
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
        tooltip.add(new TranslatableText("msg.spatialharvesters.effect_key_description"));
        tooltip.add(new TranslatableText("msg.spatialharvesters.divider"));
    }
}
