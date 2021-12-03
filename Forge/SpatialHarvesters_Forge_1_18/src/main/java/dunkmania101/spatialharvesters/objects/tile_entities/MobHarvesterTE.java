package dunkmania101.spatialharvesters.objects.tile_entities;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.mixin.MobEntityMixinCastable;
import dunkmania101.spatialharvesters.objects.tile_entities.base.SpatialHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.registries.ForgeRegistries;

public class MobHarvesterTE extends SpatialHarvesterTE {
    protected String entity = null;
    protected Player player = null;
    protected CompoundTag weapon = new CompoundTag();

    public MobHarvesterTE(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    @Override
    protected void lastMinuteActions() {
        ArrayList<ItemStack> newOutputs = new ArrayList<>();
        try {
            if (this.entity != null && !this.entity.isEmpty()) {
                Mob mobEntity = getMobEntity();
                if (mobEntity != null) {
                    if (this.player == null) {
                        setPlayer();
                    }
                    if (this.player != null) {
                        ResourceLocation entityRN = mobEntity.getType().getRegistryName();
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
                        DamageSource playerDamage = DamageSource.playerAttack(this.player);
                        mobEntity.setLastHurtByPlayer(this.player);
                        mobEntity.captureDrops(new ArrayList<>());
                        int lootingLevel = ForgeHooks.getLootingLevel(mobEntity, this.player, playerDamage);
                        ((MobEntityMixinCastable) mobEntity).invokeDropLootFromTable(playerDamage, true);
                        ((MobEntityMixinCastable) mobEntity).invokeDropCustomDeathLoot(playerDamage, lootingLevel, true);
                        CompoundTag getDropsTag = new CompoundTag();
                        getDropsTag.putString(CustomValues.shouldSaveDropsKey, "");
                        CompoundTag savedDropsData = mobEntity.saveWithoutId(getDropsTag).getCompound(CustomValues.savedDropsKey);
                        for (String key : savedDropsData.getAllKeys()) {
                            CompoundTag stackNBT = savedDropsData.getCompound(key);
                            if (!stackNBT.isEmpty()) {
                                ItemStack stack = ItemStack.of(stackNBT);
                                if (!stack.isEmpty()) {
                                    newOutputs.add(stack);
                                }
                            }
                        }
                        mobEntity.onRemovedFromWorld();
                    }
                }
            }
        } catch (Exception error) {
            SpatialHarvesters.LOGGER.catching(error);
        }
        setOutputStacks(newOutputs);
    }

    protected Mob getMobEntity() {
        Mob mobEntity = null;
        try {
            if (getLevel() != null && this.entity != null && !this.entity.isEmpty()) {
                if (getLevel() instanceof ServerLevel) {
                    ServerLevel serverWorld = (ServerLevel) getLevel();
                    Optional<EntityType<?>> optionalEntityType = EntityType.byString(this.entity);
                    if (optionalEntityType.isPresent()) {
                        EntityType<?> entityType = optionalEntityType.get();
                        ResourceLocation mobRN = entityType.getRegistryName();
                        if (mobRN != null) {
                            ArrayList<ArrayList<String>> blacklist_mobs = CommonConfig.BLACKLIST_MOBS.get();
                            ArrayList<String> blacklist_mobs_mod = CommonConfig.BLACKLIST_MOBS_MOD.get();
                            if (!Tools.isResourceBanned(mobRN, blacklist_mobs, blacklist_mobs_mod)) {
                                Entity entity = entityType.create(serverWorld);
                                if (entity != null) {
                                    if (entity instanceof Mob) {
                                        mobEntity = (Mob) entity;
                                        try {
                                            mobEntity.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(getBlockPos()), MobSpawnType.NATURAL, null, null);
                                        } catch (Exception error) {
                                            SpatialHarvesters.LOGGER.catching(error);
                                        }
                                    }
                                    entity.onRemovedFromWorld();
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
        if (getLevel() != null) {
            if (getLevel() instanceof ServerLevel) {
                removePlayer();
                ServerLevel serverWorld = (ServerLevel) getLevel();
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
                    stack = ItemStack.of(this.weapon);
                }
            }
            CompoundTag stackNBT = stack.getTag();
            ItemStack mainHandStack = this.player.getMainHandItem();
            if (!ItemStack.isSame(mainHandStack, stack)) {
                this.player.setItemInHand(InteractionHand.MAIN_HAND, stack);
                mainHandStack = this.player.getMainHandItem();
            }
            if (mainHandStack.getTag() != stackNBT) {
                mainHandStack.deserializeNBT(stackNBT);
            }
            ItemStack offHandStack = this.player.getOffhandItem();
            if (!ItemStack.isSame(offHandStack, stack)) {
                this.player.setItemInHand(InteractionHand.OFF_HAND, stack);
                offHandStack = this.player.getOffhandItem();
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
            this.player.onRemovedFromWorld();
            this.player = null;
        }
    }

    protected void removeWeapon() {
        this.weapon = new CompoundTag();
    }

    @Override
    public int getTier(Block block) {
        return 8;
    }

    @Override
    public Item getShard(Block block) {
        return ItemInit.MOB_SHARD.get();
    }

    @Override
    public boolean overrideSetOutputs() {
        return true;
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
    public void setRemoved() {
        removeAll();
        super.setRemoved();
    }
}
