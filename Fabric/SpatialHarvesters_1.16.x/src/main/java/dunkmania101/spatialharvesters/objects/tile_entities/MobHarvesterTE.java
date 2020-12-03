package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.mixin.MobEntityMixinCastable;
import dunkmania101.spatialharvesters.util.FakePlayer;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Optional;

public class MobHarvesterTE extends SpatialHarvesterTE {
    protected String entity = null;
    protected PlayerEntity player = null;
    protected CompoundTag weapon = new CompoundTag();

    public MobHarvesterTE(BlockEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn, new ArrayList<>());
    }

    @Override
    protected void lastMinuteActions() {
        super.lastMinuteActions();
        setEntityDrops();
    }

    protected void setEntityDrops() {
        ArrayList<ItemStack> newOutputs = new ArrayList<>();
        if (this.entity != null && !this.entity.isEmpty()) {
            MobEntity mobEntity = getMobEntity();
            if (mobEntity != null) {
                if (this.player == null) {
                    setPlayer();
                }
                if (this.player != null && this.player.isDead()) {
                    setPlayer();
                }
                if (this.player != null) {
                    Identifier entityRN = Identifier.tryParse(mobEntity.getType().getTranslationKey());
                    if (entityRN != null) {
                        ArrayList<ArrayList<ArrayList<String>>> custom_mob_drops = CommonConfig.CUSTOM_MOB_DROPS.get();
                        String mod = entityRN.getNamespace();
                        String path = entityRN.getPath();
                        ArrayList<String> modMob = new ArrayList<>();
                        modMob.add(mod);
                        modMob.add(path);
                        for (ArrayList<ArrayList<String>> modMobDrop : custom_mob_drops) {
                            ArrayList<String> customModMob = modMobDrop.get(0);
                            if (customModMob.containsAll(modMob)) {
                                ArrayList<String> customMobDrop = modMobDrop.get(1);
                                Identifier mobDropRN = new Identifier(customMobDrop.get(0), customMobDrop.get(1));
                                Item drop = Registry.ITEM.get(mobDropRN);
                                if (drop != Items.AIR) {
                                    newOutputs.add(new ItemStack(drop));
                                }
                            }
                        }
                    }
                    updateWeapon();
                    DamageSource playerDamage = DamageSource.player(this.player);
                    getMobEntity().setAttacking(this.player);
                    int lootingLevel = EnchantmentHelper.getLooting(this.player);
                    ((MobEntityMixinCastable) mobEntity).invokeDropLoot(playerDamage, true);
                    ((MobEntityMixinCastable) mobEntity).invokeDropEquipment(playerDamage, lootingLevel, true);
                    CompoundTag savedDropsData = mobEntity.toTag(new CompoundTag()).getCompound(CustomValues.savedDropsKey);
                    for (String key : savedDropsData.getKeys()) {
                        CompoundTag stackNBT = savedDropsData.getCompound(key);
                        if (!stackNBT.isEmpty()) {
                            ItemStack stack = ItemStack.fromTag(stackNBT);
                            if (!stack.isEmpty()) {
                                newOutputs.add(stack);
                            }
                        }
                    }
                    mobEntity.remove();
                }
            }
        }
        setOutputStacks(newOutputs);
    }

    protected MobEntity getMobEntity() {
        MobEntity mobEntity = null;
        if (getWorld() != null && this.entity != null && !this.entity.isEmpty()) {
            if (getWorld() instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) getWorld();
                Optional<EntityType<?>> optionalEntityType = EntityType.get(this.entity);
                if (optionalEntityType.isPresent()) {
                    EntityType<?> entityType = optionalEntityType.get();
                    Identifier mobRN = Identifier.tryParse(entityType.getTranslationKey());
                    if (mobRN != null) {
                        ArrayList<ArrayList<String>> blacklist_mobs = CommonConfig.BLACKLIST_MOBS.get();
                        ArrayList<String> blacklist_mobs_mod = CommonConfig.BLACKLIST_MOBS_MOD.get();
                        if (!Tools.isResourceBanned(mobRN, blacklist_mobs, blacklist_mobs_mod)) {
                            Entity entity = entityType.create(serverWorld);
                            if (entity != null) {
                                if (entity instanceof MobEntity) {
                                    mobEntity = (MobEntity) entity;
                                    mobEntity.initialize(serverWorld, serverWorld.getLocalDifficulty(getPos()), SpawnReason.NATURAL, null, null);
                                }
                                entity.remove();
                            }
                        }
                    }
                }
            }
        }
        return mobEntity;
    }

    protected void setPlayer() {
        if (getWorld() != null) {
            if (getWorld() instanceof ServerWorld) {
                removePlayer();
                ServerWorld serverWorld = (ServerWorld) getWorld();
                this.player = new FakePlayer(serverWorld);
                this.player.setSilent(true);
            }
        }
    }

    protected void updateWeapon() {
        if (this.player != null) {
            ItemStack stack = ItemStack.EMPTY;
            if (this.weapon != null) {
                if (!this.weapon.isEmpty()) {
                    stack = ItemStack.fromTag(this.weapon);
                }
            }
            CompoundTag stackNBT = stack.getTag();
            ItemStack mainHandStack = this.player.getMainHandStack();
            if (!ItemStack.areEqual(mainHandStack, stack)) {
                this.player.setStackInHand(Hand.MAIN_HAND, stack);
                mainHandStack = this.player.getMainHandStack();
            }
            if (mainHandStack.getTag() != stackNBT) {
                mainHandStack.setTag(stackNBT);
            }
            ItemStack offHandStack = this.player.getOffHandStack();
            if (!ItemStack.areEqual(offHandStack, stack)) {
                this.player.setStackInHand(Hand.OFF_HAND, stack);
                offHandStack = this.player.getOffHandStack();
            }
            if (offHandStack.getTag() != stackNBT) {
                offHandStack.setTag(stackNBT);
            }
        }
    }

    protected void removeAll() {
        removeMobEntity();
        removePlayer();
        removeWeapon();
        this.weapon = null;
    }

    protected void removeMobEntity() {
        this.entity = null;
    }

    protected void removePlayer() {
        if (this.player != null) {
            this.player.remove();
            this.player = null;
        }
    }

    protected void removeWeapon() {
        this.weapon = new CompoundTag();
    }

    @Override
    public CompoundTag saveSerializedValues() {
        CompoundTag nbt = super.saveSerializedValues();
        if (this.entity != null && !this.entity.isEmpty()) {
            nbt.putString(CustomValues.entityNBTKey, this.entity);
        }
        if (this.weapon != null) {
            if (!this.weapon.isEmpty()) {
                nbt.put(CustomValues.weaponNBTKey, this.weapon);
            }
        }
        return nbt;
    }

    @Override
    public void setDeserializedValues(CompoundTag nbt) {
        super.setDeserializedValues(nbt);
        if (nbt.contains(CustomValues.removeEntityNBTKey)) {
            removePlayer();
            removeMobEntity();
        }
        if (nbt.contains(CustomValues.removeWeaponNBTKey)) {
            removeWeapon();
        }
        if (nbt.contains(CustomValues.entityNBTKey)) {
            removeMobEntity();
            this.entity = nbt.getString(CustomValues.entityNBTKey);
        }
        if (nbt.contains(CustomValues.weaponNBTKey)) {
            removeWeapon();
            this.weapon = nbt.getCompound(CustomValues.weaponNBTKey);
        }
    }

    @Override
    public void markRemoved() {
        super.markRemoved();
        removeAll();
    }

    @Override
    public Item getShard(Block block) {
        return ItemInit.MOB_SHARD.get();
    }
}
