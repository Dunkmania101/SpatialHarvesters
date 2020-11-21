package dunkmania101.spatialharvesters.init;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit {
    public static <I extends Item> I registerItem(I item, String name) {
        Registry.register(Registry.ITEM, new Identifier(SpatialHarvesters.modid, name), item);
        return item;
    }
}
