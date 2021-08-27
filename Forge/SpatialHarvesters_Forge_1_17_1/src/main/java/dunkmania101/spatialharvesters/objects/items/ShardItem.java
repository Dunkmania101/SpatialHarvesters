package dunkmania101.spatialharvesters.objects.items;

import java.util.List;

import javax.annotation.Nonnull;

import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ShardItem extends Item {
    private final int tier;
    public ShardItem(Properties properties, int tierIn) {
        super(properties);
        this.tier = tierIn;
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, Level worldIn, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if (this == ItemInit.MOB_SHARD.get()) {
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.mob_shard_description", ChatFormatting.GOLD));
        } else if (this.tier > 0) {
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.shard_description", ChatFormatting.GOLD));
            tooltip.add(new TextComponent((Integer.toString(this.tier))).copy().withStyle(ChatFormatting.BLUE,
                    ChatFormatting.BOLD));
        }
    }
}
