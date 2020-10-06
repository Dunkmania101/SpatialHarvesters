package dunkmania101.spatialharvesters.objects.entities;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import java.util.ArrayList;

public class FakeMobEntity extends MobEntity {
    public FakeMobEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);

        setSilent(true);
        setNoAI(true);
    }

    protected ArrayList<ItemStack> drops = new ArrayList<>();

    public ArrayList<ItemStack> getDrops() {
        return this.drops;
    }

    public void publicSpawnDrops(DamageSource cause) {
        this.spawnDrops(cause);
    }

    @Override
    public ItemEntity entityDropItem(ItemStack stack) {
        if (world != null) {
            if (!world.isRemote) {
                this.drops.add(stack);
            }
        }
        return null;
    }

    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropSpecialItems(source, looting, recentlyHitIn);

        for (EquipmentSlotType equipmentslottype : EquipmentSlotType.values()) {
            ItemStack itemstack = this.getItemStackFromSlot(equipmentslottype);
            float f = this.getDropChance(equipmentslottype);
            boolean flag = f > 1.0F;
            if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack) && (recentlyHitIn || flag) && Math.max(this.rand.nextFloat() - (float) looting * 0.01F, 0.0F) < f) {
                if (!flag && itemstack.isDamageable()) {
                    itemstack.setDamage(itemstack.getMaxDamage() - this.rand.nextInt(1 + this.rand.nextInt(Math.max(itemstack.getMaxDamage() - 3, 1))));
                }
                this.entityDropItem(itemstack);
            }
        }
    }

    @Override
    protected void spawnDrops(DamageSource damageSourceIn) {
        Entity entity = damageSourceIn.getTrueSource();

        int i = ForgeHooks.getLootingLevel(this, entity, damageSourceIn);
        this.captureDrops(new ArrayList<>());

        if (this.canDropLoot()) {
            this.dropLoot(damageSourceIn, true);
            this.dropSpecialItems(damageSourceIn, i, true);
        }
    }
}
