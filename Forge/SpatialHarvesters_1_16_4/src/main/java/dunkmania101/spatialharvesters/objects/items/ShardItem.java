package dunkmania101.spatialharvesters.objects.items;

import java.util.List;

import dunkmania101.spatialharvesters.init.ItemInit;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ShardItem extends Item {
    private final int tier;
    public ShardItem(Properties properties, int tierIn) {
        super(properties);
        this.tier = tierIn;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (this.tier > 0) {
            tooltip.add(new TranslationTextComponent("msg.spatialharvesters.shard_description").appendString(Integer.toString(this.tier)));
        } else if (this == ItemInit.MOB_SHARD.get()) {
            tooltip.add(new TranslationTextComponent("msg.spatialharvesters.mob_shard_description"));
        }
    }
}
