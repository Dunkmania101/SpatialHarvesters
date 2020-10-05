package dunkmania101.spatialharvesters.objects.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.ArrayList;

public class FakeMobEntity extends MobEntity {
    public FakeMobEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected ArrayList<ItemStack> drops = new ArrayList<>();

    public ArrayList<ItemStack> getDrops() {
        return this.drops;
    }

    @Override
    public ItemEntity entityDropItem(ItemStack stack) {
        this.drops.add(stack);
        return null;
    }

    @Override
    protected void spawnDrops(DamageSource damageSourceIn) {
        Entity entity = damageSourceIn.getTrueSource();

        int i = net.minecraftforge.common.ForgeHooks.getLootingLevel(this, entity, damageSourceIn);
        this.captureDrops(new java.util.ArrayList<>());

        boolean flag = this.recentlyHit > 0;
        if (this.canDropLoot()) {
            this.dropLoot(damageSourceIn, flag);
            this.dropSpecialItems(damageSourceIn, i, flag);
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        if (net.minecraftforge.common.ForgeHooks.onLivingDeath(this, cause)) return;
        if (this.world != null && !this.world.isRemote) {
            this.spawnDrops(cause);
        }
    }
}
