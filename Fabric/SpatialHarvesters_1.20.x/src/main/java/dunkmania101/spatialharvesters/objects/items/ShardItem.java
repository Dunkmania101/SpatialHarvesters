package dunkmania101.spatialharvesters.objects.items;

import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class ShardItem extends Item {
    private final int tier;
    public ShardItem(Settings settings, int tierIn) {
        super(settings);
        this.tier = tierIn;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (this == ItemInit.MOB_SHARD) {
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.mob_shard_description", Formatting.RED));
        } else if (this.tier > 0) {
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.shard_description", Formatting.GOLD));
            tooltip.add(Text.of(Integer.toString(this.tier)).copy().formatted(Formatting.BLUE, Formatting.BOLD));
        }
    }
}
