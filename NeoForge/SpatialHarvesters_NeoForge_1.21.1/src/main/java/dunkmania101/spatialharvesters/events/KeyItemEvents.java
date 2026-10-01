package dunkmania101.spatialharvesters.events;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.objects.items.base.LeftClickBindingItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = SpatialHarvesters.modid)
public class KeyItemEvents {
    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof LeftClickBindingItem binding
                && binding.onLeftClickBlock(stack, event.getPos(), event.getEntity())) {
            event.setCanceled(true);
        }
    }
}
