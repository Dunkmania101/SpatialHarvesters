package dunkmania101.spatialharvesters.objects.items;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.DimensionalApplicatorTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class EffectKeyItem extends Item {
    public EffectKeyItem(Properties properties) {
        super(properties);
    }

    @Override
    public @Nonnull ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote()) {
            BlockPos pos = context.getPos();
            TileEntity tile = world.getTileEntity(pos);
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
        return super.onItemUse(context);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.addAll(Tools.getMultiLineText("msg.spatialharvesters.effect_key_description", TextFormatting.GOLD));
        tooltip.add(Tools.getDividerText());
    }
}
