package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.mixin.MobEntityMixinCastable;
import dunkmania101.spatialharvesters.objects.tile_entities.base.SpatialHarvesterTE;
import dunkmania101.spatialharvesters.util.FakePlayer;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class MobHarvesterTE extends SpatialHarvesterTE {
    protected String entity = null;
    protected PlayerEntity player = null;
    protected NbtCompound weapon = new NbtCompound();

    public MobHarvesterTE(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    @Override
    protected void lastMinuteActions() {
        ArrayList<ItemStack> newOutputs = new ArrayList<>();
        try {
            if (this.entity != null && !this.entity.isEmpty()) {
                MobEntity mobEntity = getMobEntity();
                if (mobEntity != null) {
                    if (this.player == null || this.player.isDead())  {
                        setPlayer();
                    }
                    if (this.player != null) {
                        Identifier entityRN = Registries.ENTITY_TYPE.getId(mobEntity.getType());
                        ArrayList<ArrayList<ArrayList<String>>> custom_mob_drops = CommonConfig.custom_mob_drops;
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
                                Item drop = Registries.ITEM.get(mobDropRN);
                                if (drop != Items.AIR) {
                                    newOutputs.add(new ItemStack(drop));
                                }
                            }
                        }
                        updateWeapon();
                        DamageSource playerDamage = this.player.getDamageSources().playerAttack(this.player);
                        mobEntity.setAttacking(this.player);
                        int lootingLevel = EnchantmentHelper.getLooting(this.player);
                        ((MobEntityMixinCastable) mobEntity).invokeDropLoot(playerDamage, true);
                        ((MobEntityMixinCastable) mobEntity).invokeDropEquipment(playerDamage, lootingLevel, true);
                        NbtCompound getDropsTag = new NbtCompound();
                        getDropsTag.putString(CustomValues.shouldSaveDropsKey, "");
                        NbtCompound savedDropsData = mobEntity.writeNbt(getDropsTag).getCompound(CustomValues.savedDropsKey);
                        for (String key : savedDropsData.getKeys()) {
                            NbtCompound stackNBT = savedDropsData.getCompound(key);
                            if (!stackNBT.isEmpty()) {
                                ItemStack stack = ItemStack.fromNbt(stackNBT);
                                if (!stack.isEmpty()) {
                                    newOutputs.add(stack);
                                }
                            }
                        }
                        mobEntity.discard();
                    }
                }
            }
        } catch (Exception error) {
            SpatialHarvesters.LOGGER.catching(error);
        }
        setOutputStacks(newOutputs);
    }

    protected MobEntity getMobEntity() {
        MobEntity mobEntity = null;
        try {
            if (getWorld() != null && this.entity != null && !this.entity.isEmpty()) {
                if (getWorld() instanceof ServerWorld serverWorld) {
                    Optional<EntityType<?>> optionalEntityType = EntityType.get(this.entity);
                    if (optionalEntityType.isPresent()) {
                        EntityType<?> entityType = optionalEntityType.get();
                        Identifier mobRN = EntityType.getId(entityType);
                        if (mobRN != null) {
                            ArrayList<ArrayList<String>> blacklist_mobs = CommonConfig.blacklist_mobs;
                            ArrayList<String> blacklist_mobs_mod = CommonConfig.blacklist_mobs_mod;
                            if (!Tools.isResourceBanned(mobRN, blacklist_mobs, blacklist_mobs_mod)) {
                                Entity entity = entityType.create(serverWorld);
                                if (entity != null) {
                                    if (entity instanceof MobEntity) {
                                        mobEntity = (MobEntity) entity;
                                        try {
                                            mobEntity.initialize(serverWorld, serverWorld.getLocalDifficulty(getPos()), SpawnReason.NATURAL, null, null);
                                        } catch (Exception error) {
                                            SpatialHarvesters.LOGGER.catching(error);
                                        }
                                    }
                                    entity.discard();
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception error) {
            SpatialHarvesters.LOGGER.catching(error);
        }
        return mobEntity;
    }

    protected void setPlayer() {
        if (getWorld() != null) {
            if (getWorld() instanceof ServerWorld serverWorld) {
                removePlayer();
                UUID uuid = UUID.randomUUID();
                this.player = new FakePlayer(serverWorld, uuid, uuid.toString());
                this.player.setSilent(true);
            }
        }
    }

    protected void updateWeapon() {
        if (this.player != null) {
            ItemStack stack = ItemStack.EMPTY;
            if (this.weapon != null) {
                if (!this.weapon.isEmpty()) {
                    stack = ItemStack.fromNbt(this.weapon);
                }
            }
            NbtCompound stackNBT = stack.getOrCreateNbt();
            ItemStack mainHandStack = this.player.getMainHandStack();
            if (!ItemStack.areEqual(mainHandStack, stack)) {
                this.player.setStackInHand(Hand.MAIN_HAND, stack);
                mainHandStack = this.player.getMainHandStack();
            }
            if (mainHandStack.getOrCreateNbt() != stackNBT) {
                mainHandStack.setNbt(stackNBT);
            }
            ItemStack offHandStack = this.player.getOffHandStack();
            if (!ItemStack.areEqual(offHandStack, stack)) {
                this.player.setStackInHand(Hand.OFF_HAND, stack);
                offHandStack = this.player.getOffHandStack();
            }
            if (offHandStack.getOrCreateNbt() != stackNBT) {
                offHandStack.setNbt(stackNBT);
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
            this.player.discard();
            this.player = null;
        }
    }

    protected void removeWeapon() {
        this.weapon = new NbtCompound();
    }

    @Override
    public NbtCompound saveSerializedValues() {
        NbtCompound nbt = super.saveSerializedValues();
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
    public void setDeserializedValues(NbtCompound nbt) {
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
        removeAll();
        super.markRemoved();
    }

    @Override
    public Item getShard(Block block) {
        return ItemInit.MOB_SHARD;
    }
}
