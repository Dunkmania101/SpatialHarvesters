package dunkmania101.spatialharvesters.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import dunkmania101.spatialharvesters.data.CustomValues;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

@Mixin(Entity.class)
public abstract class EntityMixin {
    protected CompoundTag SAVED_DROPS = new CompoundTag();

    @Inject(at = @At("TAIL"), method = "saveWithoutId")
    public void injectSaveWithoutId(CompoundTag tag, CallbackInfoReturnable<CompoundTag> cir) {
        if (tag.contains(CustomValues.shouldSaveDropsKey)) {
            tag.put(CustomValues.savedDropsKey, this.SAVED_DROPS);
        }
    }

    @Inject(at = @At("TAIL"), method = "spawnAtLocation(Lnet/minecraft/world/item/ItemStack;F)Lnet/minecraft/world/entity/item/ItemEntity;")
    public void injectSpawnAtLocation(ItemStack stack, float yOffset, CallbackInfoReturnable<ItemEntity> cir) {
        int i = 0;
        while (this.SAVED_DROPS.getAllKeys().contains(Integer.toString(i))) {
            i++;
        }
        this.SAVED_DROPS.put(Integer.toString(i), stack.save(new CompoundTag()));
    }
}
