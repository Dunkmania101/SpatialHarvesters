package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeTabInit {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
            SpatialHarvesters.modid);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SPATIAL_HARVESTERS_GROUP = TABS.register(
            SpatialHarvesters.modid,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + SpatialHarvesters.modid))
                    .icon(() -> new ItemStack(ItemInit.ORE_HARVESTER_1.get()))
                    .displayItems((params, output) -> ItemInit.ITEMS.getEntries()
                            .forEach(item -> output.accept(item.get())))
                    .build());
}
