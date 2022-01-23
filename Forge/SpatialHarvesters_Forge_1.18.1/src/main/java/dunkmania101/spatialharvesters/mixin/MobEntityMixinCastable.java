package dunkmania101.spatialharvesters.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Mob;

@Mixin(Mob.class)
public interface MobEntityMixinCastable {
    @Invoker("dropFromLootTable")
    void invokeDropLootFromTable(DamageSource source, boolean causedByPlayer);

    @Invoker("dropCustomDeathLoot")
    void invokeDropCustomDeathLoot(DamageSource source, int lootingMultiplier, boolean allowDrops);
}
