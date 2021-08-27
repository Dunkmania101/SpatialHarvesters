package dunkmania101.spatialharvesters.objects.items;

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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class PlayerKeyItem extends Item {
    public PlayerKeyItem(Properties properties) {
        super(properties);
    }

    @Override
    @Nonnull
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        if (!world.isClientSide()) {
            BlockPos pos = context.getClickedPos();
            BlockEntity tile = world.getBlockEntity(pos);
            if (tile != null) {
                if (tile instanceof DimensionalApplicatorTE) {
                    Player player = context.getPlayer();
                    if (player != null) {
                        CompoundTag nbt = new CompoundTag();
                        if (player.isCrouching()) {
                            nbt.putString(CustomValues.removePlayerNBTKey, "");
                            player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.clear_dimensional_applicator", ChatFormatting.RED), true);
                        } else {
                            nbt.putUUID(getDescriptionId(), BASE_ATTACK_DAMAGE_UUID);
                            player.displayClientMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_dimensional_applicator", ChatFormatting.BLUE), true);
                        }
                        tile.deserializeNBT(Tools.stripTileNBT(nbt));
                    }
                }
            }
        }
        return super.useOn(context);
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, Level worldIn, List<Component> tooltip, @Nonnull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.addAll(Tools.getMultiLineText("msg.spatialharvesters.player_key_descripaddInformationtion", ChatFormatting.GOLD));
        tooltip.add(Tools.getDividerText());
    }
}
