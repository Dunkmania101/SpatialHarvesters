package dunkmania101.spatialharvesters.mixin;

import dunkmania101.spatialharvesters.data.CustomValues;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public abstract CompoundTag toTag(CompoundTag tag);

    @Inject(at = @At("TAIL"), method = "dropStack(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/entity/ItemEntity;")
    public void injectDropStack(ItemStack stack, CallbackInfoReturnable<ItemEntity> cir) {
        CompoundTag data = toTag(new CompoundTag());
        if (data.contains(CustomValues.shouldSaveDropsKey)) {
            CompoundTag savedDropsData = data.getCompound(CustomValues.savedDropsKey);
            int i = 0;
            while (savedDropsData.getKeys().contains(Integer.toString(i))) {
                i++;
            }
            savedDropsData.put(Integer.toString(i), stack.toTag(new CompoundTag()));
            data.put(CustomValues.savedDropsKey, savedDropsData);
        }
    }
}
