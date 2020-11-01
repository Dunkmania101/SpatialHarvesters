package dunkmania101.spatialharvesters.objects.tile_entities;

import com.mojang.authlib.GameProfile;
import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.mixin.MobEntityMixin;
import dunkmania101.spatialharvesters.util.Tools;
import net.fabricmc.fabric.impl.tag.extension.FabricTagHooks;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class MobHarvesterTE extends SpatialHarvesterTE {
    protected String entity = null;
    protected PlayerEntity player = null;
    protected CompoundNBT weapon = new CompoundNBT();

    public MobHarvesterTE(TileEntityType<?> tileEntityTypeIn) {
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
            MobEntityMixin mobEntity = ((MobEntityMixin) getMobEntity());
            if (mobEntity != null) {
                if (this.player == null) {
                    setPlayer();
                }
                if (this.player != null) {
                    Identifier entityRN = Identifier.tryParse(mobEntity.getType().toString());
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
                    DamageSource playerDamage = DamageSource.player(this.player);
                    ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, mobEntity, this.player, "field_70717_bb");
                    mobEntity.captureDrops(new ArrayList<>());
                    int lootingLevel = EnchantmentHelper.getLooting(this.player);
                    mobEntity.invokeDropLoot(playerDamage, true);
                    mobEntity.invokeDropEquipment(playerDamage, lootingLevel, true);
                    Collection<ItemEntity> mobDrops = mobEntity.(null);
                    mobEntity.remove();
                }
            }
        }
        setOutputStacks(newOutputs);
    }

    protected MobEntity getMobEntity() {
        MobEntity mobEntity = null;
        if (getWorld() != null && !StringUtils.isNullOrEmpty(this.entity)) {
            if (getWorld() instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) getWorld();
                Optional<EntityType<?>> optionalEntityType = EntityType.byKey(this.entity);
                if (optionalEntityType.isPresent()) {
                    EntityType<?> entityType = optionalEntityType.get();
                    ResourceLocation mobRN = entityType.getRegistryName();
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
    public CompoundNBT saveSerializedValues() {
        CompoundNBT nbt = super.saveSerializedValues();
        if (!StringUtils.isNullOrEmpty(this.entity)) {
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
    public void setDeserializedValues(CompoundNBT nbt) {
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
