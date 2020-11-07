package dunkmania101.spatialharvesters.objects.blocks;

import com.mojang.authlib.GameProfile;
import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.mixin.MobEntityMixinCastable;
import dunkmania101.spatialharvesters.objects.tile_entities.SpatialHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class MobHarvesterTE extends SpatialHarvesterTE {
    protected String entity = null;
    protected PlayerEntity player = null;
    protected CompoundTag weapon = new CompoundTag();

    public MobHarvesterTE(BlockEntityType<?> blockEntityTypeIn) {
        super(blockEntityTypeIn, new ArrayList<>());
    }

    @Override
    protected void lastMinuteActions() {
        super.lastMinuteActions();
        setEntityDrops();
    }

    protected void setEntityDrops() {
        ArrayList<ItemStack> newOutputs = new ArrayList<>();
        if (this.entity != null && !this.entity.isEmpty()) {
            MobEntityMixinCastable mobEntity = (MobEntityMixinCastable)getMobEntity();
            if (mobEntity != null) {
                if (this.player == null) {
                    setPlayer();
                }
                if (this.player != null) {
                    Identifier entityRN = mobEntity.;
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
                                ResourceLocation mobDropRN = new ResourceLocation(customMobDrop.get(0), customMobDrop.get(1));
                                Item drop = ForgeRegistries.ITEMS.getValue(mobDropRN);
                                if (drop != null && drop != Items.AIR) {
                                    newOutputs.add(new ItemStack(drop));
                                }
                            }
                        }
                    }
                    updateWeapon();
                    DamageSource playerDamage = DamageSource.causePlayerDamage(this.player);
                    ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, mobEntity, this.player, "field_70717_bb");
                    mobEntity.captureDrops(new ArrayList<>());
                    int lootingLevel = ForgeHooks.getLootingLevel(mobEntity, this.player, playerDamage);
                    try {
                        dropLoot.invoke(mobEntity, playerDamage, true);
                        dropSpecialItems.invoke(mobEntity, playerDamage, lootingLevel, true);
                    } catch (Exception error) {
                        throw new RuntimeException(error);
                    }
                    Collection<ItemEntity> mobDrops = mobEntity.captureDrops(null);

                    mobEntity.();
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
                    Identifier mobRN = new Identifier(entityType.toString());
                    if (mobRN != null) {
                        ArrayList<ArrayList<String>> blacklist_mobs = CommonConfig.BLACKLIST_MOBS.get();
                        ArrayList<String> blacklist_mobs_mod = CommonConfig.BLACKLIST_MOBS_MOD.get();
                        if (!Tools.isResourceBanned(mobRN, blacklist_mobs, blacklist_mobs_mod)) {
                            Entity entity = entityType.create(serverWorld);
                            if (entity != null) {
                                if (entity instanceof MobEntity) {
                                    mobEntity = (MobEntity) entity;
                                    mobEntity.onInitialSpawn(serverWorld, serverWorld.getDifficultyForLocation(getPos()), SpawnReason.NATURAL, null, null);
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
                UUID uuid = UUID.randomUUID();
                GameProfile profile = new GameProfile(uuid, uuid.toString());
                this.player = FakePlayerFactory.get(serverWorld, profile);
                this.player.setSilent(true);
            }
        }
    }

    protected void updateWeapon() {
        if (this.player != null) {
            ItemStack stack = ItemStack.EMPTY;
            if (this.weapon != null) {
                if (!this.weapon.isEmpty()) {
                    stack = ItemStack.read(this.weapon);
                }
            }
            CompoundNBT stackNBT = stack.getTag();
            ItemStack mainHandStack = this.player.getHeldItemMainhand();
            if (!ItemStack.areItemStacksEqual(mainHandStack, stack)) {
                this.player.setHeldItem(Hand.MAIN_HAND, stack);
                this.player.getHeldItemMainhand();
            }
            if (mainHandStack.getTag() != stackNBT) {
                mainHandStack.deserializeNBT(stackNBT);
            }
            ItemStack offHandStack = this.player.getHeldItemOffhand();
            if (!ItemStack.areItemStacksEqual(offHandStack, stack)) {
                this.player.setHeldItem(Hand.OFF_HAND, stack);
                offHandStack = this.player.getHeldItemOffhand();
            }
            if (offHandStack.getTag() != stackNBT) {
                offHandStack.deserializeNBT(stackNBT);
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
        this.weapon = new CompoundNBT();
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
    public void remove() {
        super.remove();
        removeAll();
    }

    @Override
    public Item getShard(Block block) {
        return ItemInit.MOB_SHARD.get();
    }
}
