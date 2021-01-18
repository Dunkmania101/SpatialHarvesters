package dunkmania101.spatialharvesters.objects.items;

import java.util.List;

import dunkmania101.spatialharvesters.init.ItemInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

public class ShardItem extends Item {
    private final int tier;
    public ShardItem(Settings settings, int tierIn) {
        super(settings);
        this.tier = tierIn;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (this.tier > 0) {
            tooltip.add(new TranslatableText("msg.spatialharvesters.shard_description").append(Integer.toString(this.tier)));
        } else if (this == ItemInit.MOB_SHARD) {
            tooltip.add(new TranslatableText("msg.spatialharvesters.mob_shard_description"));
        }
    }
}
