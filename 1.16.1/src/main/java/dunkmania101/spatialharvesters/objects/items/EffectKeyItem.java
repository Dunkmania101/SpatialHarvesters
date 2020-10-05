package dunkmania101.spatialharvesters.objects.items;

import dunkmania101.spatialharvesters.SpatialHarvesters;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EffectKeyItem extends Item {
    public static final String potionsNBTKey = SpatialHarvesters.modid + "potionsNBTKey";

    public EffectKeyItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote) {
            TileEntity tile = world.getTileEntity(context.getPos());
            if (tile != null) {
                if (tile instanceof DimensionalApplicatorTE) {
                    PlayerEntity player = context.getPlayer();
                    if (player != null) {
                        CompoundNBT potionsNBT = new CompoundNBT();
                        ArrayList<Integer> effects = new ArrayList<>();
                        boolean doContinue = true;
                        if (player.isCrouching()) {
                            player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.remove_dimensional_applicator_nbt_effects"), true);
                        } else {
                            for (EffectInstance effectInstance : player.getActivePotionEffects()) {
                                effects.add(Effect.getId(effectInstance.getPotion()));
                            }
                            if (effects.size() > 0) {
                                player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.set_dimensional_applicator_nbt_effects"), true);
                            } else {
                                doContinue = false;
                                player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.remove_dimensional_applicator_nbt_effects_failed"), true);
                            }
                        }
                        if (doContinue) {
                            potionsNBT.putIntArray(potionsNBTKey, effects);
                            tile.deserializeNBT(Tools.correctTileNBT(tile, potionsNBT));
                        }
                    }
                }
            }
        }
        return super.onItemUse(context);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("msg.spatialharvesters.effect_key_description"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
