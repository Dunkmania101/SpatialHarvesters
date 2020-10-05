package dunkmania101.spatialharvesters.objects.tile_entities;

import com.mojang.authlib.GameProfile;
import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.objects.entities.FakeMobEntity;
import dunkmania101.spatialharvesters.objects.items.MobKeyItem;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.StringUtils;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayerFactory;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class MobHarvesterTE extends SpatialHarvesterTE {
    private String entity = null;
    private PlayerEntity player = null;
    private ItemStack weapon = ItemStack.EMPTY;

    public MobHarvesterTE() {
        super(TileEntityInit.MOB_HARVESTER.get(), new ArrayList<>());
    }

    @Override
    public void customTickActions() {
        setEntityDrops();
        super.customTickActions();
    }

    protected void setEntityDrops() {
        ArrayList<ItemStack> newOutputs = new ArrayList<>();
        if (this.player != null && this.entity != null) {
            FakeMobEntity fakeMobEntity = getFakeMobEntity();
            if (fakeMobEntity != null) {
                this.player.setHeldItem(Hand.MAIN_HAND, this.weapon);
                DamageSource playerDamage = DamageSource.causePlayerDamage(this.player);
                fakeMobEntity.onDeath(playerDamage);
                newOutputs = fakeMobEntity.getDrops();
                fakeMobEntity.remove();
            }
        }
        setOutputStacks(newOutputs);
    }

    protected FakeMobEntity getFakeMobEntity() {
        FakeMobEntity fakeMobEntity = null;
        if (world != null && this.entity != null) {
            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) world;
                Optional<EntityType<?>> optionalEntityType = EntityType.byKey(this.entity);
                if (optionalEntityType.isPresent()) {
                    EntityType<?> entityType = optionalEntityType.get();
                    Entity entity = entityType.create(serverWorld);
                    if (entity != null) {
                        if (entity instanceof MobEntity) {
                            MobEntity mobEntity = (MobEntity) entity;
                            EntityType<? extends MobEntity> mobEntityType = (EntityType<? extends MobEntity>)mobEntity.getType();
                            fakeMobEntity = new FakeMobEntity(mobEntityType, serverWorld);
                            fakeMobEntity.onInitialSpawn(serverWorld, serverWorld.getDifficultyForLocation(pos), SpawnReason.NATURAL, null, null);
                            fakeMobEntity.copyDataFromOld(mobEntity);
                            mobEntity.remove();
                        }
                        entity.remove();
                    }
                }
            }
        }
        return fakeMobEntity;
    }

    protected void removeAll() {
        removeLivingEntity();
        removePlayer();
        removeWeapon();
        this.weapon = null;
    }

    protected void removeLivingEntity() {
        this.entity = null;
    }

    protected void removePlayer() {
        if (this.player != null) {
            this.player.remove();
            this.player = null;
        }
    }

    protected void removeWeapon() {
        this.weapon = ItemStack.EMPTY;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = super.serializeNBT();
        if (this.entity != null) {
            nbt.putString(MobKeyItem.entityNBTKey, this.entity);
        }
        if (this.player != null) {
            nbt.putString(MobKeyItem.playerNameNBTKey, this.player.getName().getString());
        }
        if (this.weapon != null) {
            if (!this.weapon.isEmpty()) {
                nbt.put(MobKeyItem.weaponNBTKey, this.weapon.serializeNBT());
            }
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        super.deserializeNBT(nbt);
        if (nbt.contains(MobKeyItem.removeEntityNBTKey)) {
            removeAll();
        }
        if (world != null) {
            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) world;
                if (nbt.contains(MobKeyItem.playerNameNBTKey)) {
                    removePlayer();
                    String name = nbt.getString(MobKeyItem.playerNameNBTKey);
                    if (!StringUtils.isNullOrEmpty(name)) {
                        GameProfile profile = new GameProfile(UUID.randomUUID(), name);
                        this.player = FakePlayerFactory.get(serverWorld, profile);
                    }
                }
            }
        }
        if (nbt.contains(MobKeyItem.entityNBTKey)) {
            removeLivingEntity();
            this.entity = nbt.getString(MobKeyItem.entityNBTKey);
        }
        if (nbt.contains(MobKeyItem.weaponNBTKey) && this.player != null) {
            removeWeapon();
            this.weapon.deserializeNBT(nbt.getCompound(MobKeyItem.weaponNBTKey));
        }
        markDirty();
    }

    @Override
    public void remove() {
        super.remove();
        removeAll();
    }

    @Override
    public int getPrice(Block block) {
        return CommonConfig.MOB_PRICE.get();
    }

    @Override
    public int getSpeed(Block block) {
        return CommonConfig.MOB_SPEED.get();
    }

    @Override
    public Item getShard(Block block) {
        return ItemInit.SHARD_7.get();
    }
}