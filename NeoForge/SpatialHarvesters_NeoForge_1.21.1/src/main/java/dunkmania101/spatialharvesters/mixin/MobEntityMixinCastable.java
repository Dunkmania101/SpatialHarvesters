package dunkmania101.spatialharvesters.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

@Mixin(LivingEntity.class)
public interface MobEntityMixinCastable {
    @Invoker("dropFromLootTable")
    void invokeDropLootFromTable(DamageSource source, boolean causedByPlayer);

    @Invoker("dropCustomDeathLoot")
    void invokeDropCustomDeathLoot(ServerLevel level, DamageSource source, boolean causedByPlayer);
}
