package dunkmania101.spatialharvesters.objects.items;

import java.util.List;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.DimensionalApplicatorTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemStack;

public class PlayerKeyItem extends Item {
    public PlayerKeyItem(Properties properties) {
        super(properties);
    }

    @Override
    @Nonnull
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote()) {
            BlockPos pos = context.getPos();
            TileEntity tile = world.getTileEntity(pos);
            if (tile != null) {
                if (tile instanceof DimensionalApplicatorTE) {
                    PlayerEntity player = context.getPlayer();
                    if (player != null) {
                        CompoundNBT nbt = new CompoundNBT();
                        if (player.isCrouching()) {
                            nbt.putString(CustomValues.removePlayerNBTKey, "");
                            player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.clear_dimensional_applicator", TextFormatting.RED), true);
                        } else {
                            nbt.putUniqueId(CustomValues.playerNBTKey, player.getUniqueID());
                            player.sendStatusMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_dimensional_applicator", TextFormatting.BLUE), true);
                        }
                        tile.deserializeNBT(Tools.correctTileNBT(tile, nbt));
                    }
                }
            }
        }
        return super.onItemUse(context);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, World worldIn, List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.addAll(Tools.getMultiLineText("msg.spatialharvesters.player_key_description", TextFormatting.GOLD));
        tooltip.add(Tools.getDividerText());
    }
}
