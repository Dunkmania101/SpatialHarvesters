package dunkmania101.spatialharvesters.mixin;

import dunkmania101.spatialharvesters.data.CustomValues;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    protected CompoundTag SAVED_DROPS = new CompoundTag();

    @Inject(at = @At("TAIL"), method = "toTag")
    public void injectToTag(CompoundTag tag, CallbackInfoReturnable<CompoundTag> cir) {
        tag.put(CustomValues.savedDropsKey, this.SAVED_DROPS);
    }

    @Inject(at = @At("TAIL"), method = "dropStack(Lnet/minecraft/item/ItemStack;F)Lnet/minecraft/entity/ItemEntity;")
    public void injectDropStack(ItemStack stack, float yOffset, CallbackInfoReturnable<ItemEntity> cir) {
        int i = 0;
        while (this.SAVED_DROPS.getKeys().contains(Integer.toString(i))) {
            i++;
        }
        this.SAVED_DROPS.put(Integer.toString(i), stack.toTag(new CompoundTag()));
    }
}
