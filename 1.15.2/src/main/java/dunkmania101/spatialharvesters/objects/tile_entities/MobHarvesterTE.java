package dunkmania101.spatialharvesters.objects.tile_entities;

import com.mojang.authlib.GameProfile;
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
    private LivingEntity livingEntity = null;
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
        if (this.player != null && this.livingEntity != null) {
            LivingEntity newLivingEntity = livingEntity;
            newLivingEntity.onDeath(DamageSource.causePlayerDamage(this.player));
            Collection<ItemEntity> drops = livingEntity.captureDrops();
            if (drops != null) {
                for (ItemEntity item : drops) {
                    newOutputs.add(item.getItem());
                }
            }
            newLivingEntity.remove();
        }
        setOutputStacks(newOutputs);
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = super.serializeNBT();
        if (this.livingEntity != null) {
            nbt.put(MobKeyItem.entityNBTKey, this.livingEntity.serializeNBT());
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
            this.livingEntity = null;
            this.player = null;
            this.weapon = ItemStack.EMPTY;
        }
        if (world != null) {
            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) world;
                if (nbt.contains(MobKeyItem.entityNBTKey)) {
                    CompoundNBT mobNBT = nbt.getCompound(MobKeyItem.entityNBTKey);
                    if (mobNBT.contains("id")) {
                        Optional<EntityType<?>> optionalEntityType = EntityType.byKey(mobNBT.getString("id"));
                        if (optionalEntityType.isPresent()) {
                            EntityType<?> entityType = optionalEntityType.get();
                            Entity entity = entityType.create(serverWorld);
                            if (entity != null) {
                                entity.deserializeNBT(mobNBT);
                                if (entity instanceof LivingEntity) {
                                    this.livingEntity = (LivingEntity) entity;
                                }
                            }
                        }
                    }
                    if (nbt.contains(MobKeyItem.playerNameNBTKey)) {
                        String name = nbt.getString(MobKeyItem.playerNameNBTKey);
                        if (!StringUtils.isNullOrEmpty(name)) {
                            GameProfile profile = new GameProfile(UUID.randomUUID(), name);
                            this.player = FakePlayerFactory.get(serverWorld, profile);
                        }
                    }
                    if (nbt.contains(MobKeyItem.weaponNBTKey) && this.player != null) {
                        ItemStack weapon = ItemStack.EMPTY;
                        weapon.deserializeNBT(nbt.getCompound(MobKeyItem.weaponNBTKey));
                        this.player.setHeldItem(Hand.MAIN_HAND, weapon);
                    }
                }
            }
        }
        markDirty();
    }

    @Override
    public void remove() {
        super.remove();
        if (this.livingEntity != null) {
            this.livingEntity.remove();
        }
        if (this.player != null) {
            this.player.remove();
        }
    }

    @Override
    public Item getShard(Block block) {
        return ItemInit.SHARD_7.get();
    }
}
