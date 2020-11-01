package dunkmania101.spatialharvesters.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MobEntity.class)
public interface MobEntityMixin {
	@Invoker("dropLoot")
	void invokeDropLoot(DamageSource source, boolean causedByPlayer);

	@Invoker("dropEquipment")
	void invokeDropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops);
}
