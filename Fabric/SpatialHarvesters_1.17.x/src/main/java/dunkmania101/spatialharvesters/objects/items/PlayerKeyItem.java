package dunkmania101.spatialharvesters.objects.items;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.DimensionalApplicatorTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PlayerKeyItem extends Item {
    public PlayerKeyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (!world.isClient()) {
            BlockPos pos = context.getBlockPos();
            BlockEntity tile = world.getBlockEntity(pos);
            if (tile != null) {
                if (tile instanceof DimensionalApplicatorTE) {
                    PlayerEntity player = context.getPlayer();
                    if (player != null) {
                        NbtCompound nbt = new NbtCompound();
                        if (player.isSneaking()) {
                            nbt.putString(CustomValues.removePlayerNBTKey, "");
                            player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.clear_dimensional_applicator", Formatting.RED), true);
                        } else {
                            nbt.putUuid(CustomValues.playerNBTKey, player.getUuid());
                            player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.set_dimensional_applicator", Formatting.BLUE), true);
                        }
                        tile.readNbt(Tools.correctTileNBT(tile, nbt));
                    }
                }
            }
        }
        return super.useOnBlock(context);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.addAll(Tools.getMultiLineText("msg.spatialharvesters.player_key_description", Formatting.GOLD));
        tooltip.add(Tools.getDividerText());
    }
}
