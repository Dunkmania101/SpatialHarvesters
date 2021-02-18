package dunkmania101.spatialharvesters.objects.items;

import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ShardItem extends Item {
    private final int tier;
    public ShardItem(Properties properties, int tierIn) {
        super(properties);
        this.tier = tierIn;
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (this.tier > 0) {
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.shard_description", TextFormatting.GOLD));
            tooltip.add(new StringTextComponent((Integer.toString(this.tier))).copyRaw().mergeStyle(TextFormatting.BLUE,
                    TextFormatting.BOLD));
        } else if (this == ItemInit.MOB_SHARD.get()) {
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.mob_shard_description", TextFormatting.GOLD));
        }
    }
}
