package dunkmania101.spatialharvesters.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public class MobEntityMixin {
	@Inject(at = @At("HEAD"), method = "dropLoot")
	public void dropLoot(DamageSource source, boolean causedByPlayer, CallbackInfo ci) {
	}

	@Inject(at = @At("HEAD"), method = "dropEquipment")
	public void dropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops, CallbackInfo ci) {
	}
}
