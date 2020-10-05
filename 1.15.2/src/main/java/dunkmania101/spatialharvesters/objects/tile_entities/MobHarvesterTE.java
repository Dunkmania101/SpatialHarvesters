package dunkmania101.spatialharvesters.objects.tile_entities;

import com.mojang.authlib.GameProfile;
import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.objects.items.MobKeyItem;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
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
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class MobHarvesterTE extends SpatialHarvesterTE {
    private CompoundNBT entity = null;
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

    public void setEntityDrops() {
        ArrayList<ItemStack> newOutputs = new ArrayList<>();
        if (this.player != null && this.entity != null) {
            LivingEntity newLivingEntity = getLivingEntity();
            newLivingEntity.onDeath(DamageSource.causePlayerDamage(this.player));
            Collection<ItemEntity> drops = newLivingEntity.captureDrops();
            if (drops != null) {
                for (ItemEntity item : drops) {
                    if (item != null) {
                        newOutputs.add(item.getItem());
                        item.remove();
                    }
                }
            }
            newLivingEntity.remove();
        }
        setOutputStacks(newOutputs);
    }

    public LivingEntity getLivingEntity() {
        if (world != null) {
            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) world;
                if (this.entity.contains("id")) {
                    Optional<EntityType<?>> optionalEntityType = EntityType.byKey(this.entity.getString("id"));
                    if (optionalEntityType.isPresent()) {
                        EntityType<?> entityType = optionalEntityType.get();
                        Entity entity = entityType.create(serverWorld);
                        if (entity != null) {
                            entity.deserializeNBT(this.entity);
                            if (entity instanceof LivingEntity) {
                                return (LivingEntity) entity;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public void removeEntities() {
        removeLivingEntity();
        removePlayer();
        removeWeapon();
    }

    public void removeLivingEntity() {
        this.entity = null;
    }

    public void removePlayer() {
        if (this.player != null) {
            this.player.remove();
            this.player = null;
        }
    }

    public void removeWeapon() {
        this.weapon = ItemStack.EMPTY;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = super.serializeNBT();
        if (this.entity != null) {
            nbt.put(MobKeyItem.entityNBTKey, this.entity);
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
            removeEntities();
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
            this.entity = nbt.getCompound(MobKeyItem.entityNBTKey);
        }
        if (nbt.contains(MobKeyItem.weaponNBTKey) && this.player != null) {
            removeWeapon();
            this.weapon.deserializeNBT(nbt.getCompound(MobKeyItem.weaponNBTKey));
            this.player.setHeldItem(Hand.MAIN_HAND, this.weapon);
        }
        markDirty();
    }

    @Override
    public void remove() {
        super.remove();
        removeEntities();
        this.weapon = null;
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
