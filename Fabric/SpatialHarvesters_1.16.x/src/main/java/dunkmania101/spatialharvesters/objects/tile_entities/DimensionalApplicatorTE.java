package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.blocks.SpaceRipperBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.UUID;

public class DimensionalApplicatorTE extends TickingRedstoneEnergyMachineTE {
    private UUID playerId = null;
    private ArrayList<Integer> NBTEffects = new ArrayList<>();

    public DimensionalApplicatorTE() {
        super(TileEntityInit.DIMENSIONAL_APPLICATOR.get(), true, true, true);
    }

    @Override
    public void customTickActions() {
        boolean enabled = CommonConfig.ENABLE_DIMENSIONAL_APPLICATOR.get();
        if (enabled) {
            super.customTickActions();
            if (this.playerId != null && getWorld() != null && !getWorld().isClient) {
                double divisor = CommonConfig.DIMENSIONAL_APPLICATOR_DIVISOR.get();
                if (getCountedTicks() >= (getDuration() / divisor)) {
                    resetCountedTicks();
                    setActive(false);
                    if (getEnergyStorage().getEnergy() >= getPrice()) {
                        boolean hasSpaceRipper = false;
                        for (Direction side : Direction.values()) {
                            if (getWorld().getBlockState(pos.offset(side)).getBlock() instanceof SpaceRipperBlock) {
                                hasSpaceRipper = true;
                                break;
                            }
                        }
                        if (hasSpaceRipper) {
                            if (getWorld().getServer() != null) {
                                PlayerEntity player = null;
                                for (World checkWorld : getWorld().getServer().getWorlds()) {
                                    player = checkWorld.getPlayerByUuid(this.playerId);
                                    if (player != null) {
                                        break;
                                    }
                                }
                                if (player != null) {
                                    ArrayList<StatusEffectInstance> effects = getEffects(getWorld(), getPos());
                                    for (StatusEffectInstance effect : effects) {
                                        if (effect != null) {
                                            if (getEnergyStorage().getEnergy() >= getPrice()) {
                                                setActive(true);
                                                player.addStatusEffect(effect);
                                                getEnergyStorage().use(getPrice());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            setActive(false);
        }
    }

    private ArrayList<StatusEffectInstance> getEffects(World worldIn, BlockPos pos) {
        ArrayList<StatusEffectInstance> effectInstances = new ArrayList<>();
        ArrayList<StatusEffect> effects = new ArrayList<>();
        for (int id : this.NBTEffects) {
            StatusEffect effect = StatusEffect.byRawId(id);
            if (effect != null) {
                if (!effects.contains(effect)) {
                    effects.add(effect);
                }
            }
        }
        for (Direction check_direction : Direction.values()) {
            Block block = worldIn.getBlockState(pos.offset(check_direction)).getBlock();
            StatusEffect effect = null;
            if (block == BlockInit.REGENERATION_ACTIVATOR.get()) {
                effect = StatusEffects.REGENERATION;
            } else if (block == BlockInit.RESISTANCE_ACTIVATOR.get()) {
                effect = StatusEffects.RESISTANCE;
            } else if (block == BlockInit.ABSORPTION_ACTIVATOR.get()) {
                effect = StatusEffects.ABSORPTION;
            } else if (block == BlockInit.HASTE_ACTIVATOR.get()) {
                effect = StatusEffects.HASTE;
            } else if (block == BlockInit.SPEED_ACTIVATOR.get()) {
                effect = StatusEffects.SPEED;
            } else if (block == BlockInit.JUMP_BOOST_ACTIVATOR.get()) {
                effect = StatusEffects.JUMP_BOOST;
            } else if (block == BlockInit.INVISIBILITY_ACTIVATOR.get()) {
                effect = StatusEffects.INVISIBILITY;
            } else if (block == BlockInit.NIGHT_VISION_ACTIVATOR.get()) {
                effect = StatusEffects.NIGHT_VISION;
            } else if (block == BlockInit.STRENGTH_ACTIVATOR.get()) {
                effect = StatusEffects.STRENGTH;
            }
            if (effect != null) {
                if (!effects.contains(effect)) {
                    effects.add(effect);
                }
            }
        }
        if (!effects.isEmpty()) {
            boolean isBeacon = CommonConfig.DIMENSIONAL_APPLICATOR_IS_BEACON_EFFECT.get();
            boolean showParticles = CommonConfig.DIMENSIONAL_APPLICATOR_SHOW_PARTICLES.get();
            boolean showIcon = CommonConfig.DIMENSIONAL_APPLICATOR_SHOW_ICON.get();
            for (StatusEffect effect : effects) {
                if (effect != null) {
                    StatusEffectInstance effectInstance = new StatusEffectInstance(effect, getDuration(), getAmplifier(), isBeacon, showParticles, showIcon);
                    if (!effectInstances.contains(effectInstance)) {
                        effectInstances.add(effectInstance);
                    }
                }
            }
        }
        return effectInstances;
    }

    protected int getDuration() {
        return CommonConfig.DIMENSIONAL_APPLICATOR_DURATION.get();
    }

    protected int getAmplifier() {
        return CommonConfig.DIMENSIONAL_APPLICATOR_AMPLIFIER.get();
    }

    protected int getPrice() {
        return CommonConfig.DIMENSIONAL_APPLICATOR_PRICE.get();
    }

    @Override
    protected int getCapacity() {
        int multiplier = CommonConfig.DIMENSIONAL_APPLICATOR_CAPACITY_MULTIPLIER.get();
        return getPrice() * multiplier;
    }

    @Override
    protected int getMaxInput() {
        return getCapacity();
    }

    @Override
    protected int getMaxExtract() {
        return getCapacity();
    }

    @Override
    public CompoundTag saveSerializedValues() {
        CompoundTag nbt = super.saveSerializedValues();
        if (playerId != null) {
            nbt.putUuid(CustomValues.playerNBTKey, playerId);
        }
        if (!NBTEffects.isEmpty()) {
            nbt.putIntArray(CustomValues.potionsNBTKey, NBTEffects);
        }
        return nbt;
    }

    @Override
    public void setDeserializedValues(CompoundTag nbt) {
        super.setDeserializedValues(nbt);
        if (nbt.contains(CustomValues.removePlayerNBTKey)) {
            this.playerId = null;
        }
        if (nbt.contains(CustomValues.removePotionsNBTKey)) {
            this.NBTEffects = new ArrayList<>();
        }
        if (nbt.contains(CustomValues.playerNBTKey)) {
            this.playerId = nbt.getUuid(CustomValues.playerNBTKey);
        }
        if (nbt.contains(CustomValues.potionsNBTKey)) {
            NBTEffects = new ArrayList<>();
            for (int potion : nbt.getIntArray(CustomValues.potionsNBTKey)) {
                if (!NBTEffects.contains(potion)) {
                    NBTEffects.add(potion);
                }
            }
        }
    }
}
