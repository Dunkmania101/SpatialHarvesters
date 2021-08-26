package dunkmania101.spatialharvesters.objects.items;

import javax.annotation.Nonnull;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.DimensionalApplicatorTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
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
                    PlayerEntity player = context.getPlayer();
                    if (player != null) {
                        CompoundNBT potionsNBT = new CompoundNBT();
                        ArrayList<Integer> effects = new ArrayList<>();
                        boolean doContinue = true;
                        if (player.isSneaking()) {
                            potionsNBT.putString(CustomValues.removePotionsNBTKey, "");
                            player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.remove_dimensional_applicator_nbt_effects", TextFormatting.RED), true);
                        } else {
                            for (EffectInstance effectInstance : player.getActivePotionEffects()) {
                                effects.add(Effect.getId(effectInstance.getPotion()));
                            }
                            if (effects.size() > 0) {
                                player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_dimensional_applicator_nbt_effects", TextFormatting.BLUE), true);
                            } else {
                                doContinue = false;
                                player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.remove_dimensional_applicator_nbt_effects_failed", TextFormatting.DARK_RED), true);
                            }
                        }
                        if (doContinue) {
                            potionsNBT.putIntArray(CustomValues.potionsNBTKey, effects);
                            tile.read(context.getWorld().getBlockState(pos), Tools.correctTileNBT(tile, potionsNBT));
                        }
                    }
                }
            }
        }
        return super.use(context);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.addAll(Tools.getMultiLineText("msg.spatialharvesters.effect_key_description", TextFormatting.GOLD));
        tooltip.add(Tools.getDividerText());
    }
}
