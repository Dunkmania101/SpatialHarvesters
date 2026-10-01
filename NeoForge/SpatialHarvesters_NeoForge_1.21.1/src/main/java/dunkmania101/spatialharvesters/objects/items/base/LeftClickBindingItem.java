package dunkmania101.spatialharvesters.objects.items.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface LeftClickBindingItem {
    boolean onLeftClickBlock(ItemStack stack, BlockPos pos, Player player);
}
