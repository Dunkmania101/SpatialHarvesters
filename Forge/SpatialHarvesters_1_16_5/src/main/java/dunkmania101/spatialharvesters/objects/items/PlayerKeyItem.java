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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class PlayerKeyItem extends Item {
    public PlayerKeyItem(Properties properties) {
        super(properties);
    }

    @Override
    public @Nonnull
    ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote) {
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
