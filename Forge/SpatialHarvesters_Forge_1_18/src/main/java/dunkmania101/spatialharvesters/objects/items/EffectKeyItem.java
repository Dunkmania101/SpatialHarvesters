package dunkmania101.spatialharvesters.objects.items;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.DimensionalApplicatorTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class EffectKeyItem extends Item {
    public EffectKeyItem(Properties properties) {
        super(properties);
    }

    @Override
    public @Nonnull InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        if (!world.isClientSide()) {
            BlockPos pos = context.getClickedPos();
            BlockEntity tile = world.getBlockEntity(pos);
            if (tile != null) {
                if (tile instanceof DimensionalApplicatorTE) {
                    Player player = context.getPlayer();
                    if (player != null) {
                        CompoundTag potionsNBT = new CompoundTag();
                        ArrayList<Integer> effects = new ArrayList<>();
                        boolean doContinue = true;
                        if (player.isCrouching()) {
                            potionsNBT.putString(CustomValues.removePotionsNBTKey, "");
                            player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.remove_dimensional_applicator_nbt_effects", ChatFormatting.RED), true);
                        } else {
                            for (MobEffectInstance effectInstance : player.getActiveEffects()) {
                                effects.add(MobEffect.getId(effectInstance.getEffect()));
                            }
                            if (effects.size() > 0) {
                                player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_dimensional_applicator_nbt_effects", ChatFormatting.BLUE), true);
                            } else {
                                doContinue = false;
                                player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.remove_dimensional_applicator_nbt_effects_failed", ChatFormatting.DARK_RED), true);
                            }
                        }
                        if (doContinue) {
                            potionsNBT.putIntArray(CustomValues.potionsNBTKey, effects);
                            tile.load(Tools.stripTileNBT(potionsNBT));
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
        tooltip.addAll(Tools.getMultiLineText("msg.spatialharvesters.effect_key_description", ChatFormatting.GOLD));
        tooltip.add(Tools.getDividerText());
    }
}
