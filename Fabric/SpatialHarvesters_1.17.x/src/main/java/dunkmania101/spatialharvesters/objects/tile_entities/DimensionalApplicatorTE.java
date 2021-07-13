package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.init.BlockEntityInit;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.objects.blocks.SpaceRipperBlock;
import dunkmania101.spatialharvesters.objects.tile_entities.base.TickingRedstoneEnergyMachineTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.UUID;

public class DimensionalApplicatorTE extends TickingRedstoneEnergyMachineTE {
    private UUID playerId = null;
    private ArrayList<Integer> NBTEffects = new ArrayList<>();

    public DimensionalApplicatorTE(BlockPos pos, BlockState state) {
        super(BlockEntityInit.DIMENSIONAL_APPLICATOR, pos, state, true, true, true);
    }

    @Override
    public void customTickActions() {
        boolean enabled = CommonConfig.enable_dimensional_applicator;
        if (enabled) {
            super.customTickActions();
            if (this.playerId != null && getWorld() != null && !getWorld().isClient()) {
                double divisor = CommonConfig.dimensional_applicator_divisor;
                if (getCountedTicks() >= (getDuration() / divisor)) {
                    resetCountedTicks();
                    setActive(false);
                    if (getEnergyStorage().getEnergy() >= getPrice()) {
                        if (getSpaceRippers(getWorld(), getPos()) > 0) {
                            if (getWorld().getServer() != null) {
                                PlayerEntity player = getWorld().getServer().getPlayerManager().getPlayer(this.playerId);
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
            if (block == BlockInit.REGENERATION_ACTIVATOR) {
                effect = StatusEffects.REGENERATION;
            } else if (block == BlockInit.RESISTANCE_ACTIVATOR) {
                effect = StatusEffects.RESISTANCE;
            } else if (block == BlockInit.ABSORPTION_ACTIVATOR) {
                effect = StatusEffects.ABSORPTION;
            } else if (block == BlockInit.HASTE_ACTIVATOR) {
                effect = StatusEffects.HASTE;
            } else if (block == BlockInit.SPEED_ACTIVATOR) {
                effect = StatusEffects.SPEED;
            } else if (block == BlockInit.JUMP_BOOST_ACTIVATOR) {
                effect = StatusEffects.JUMP_BOOST;
            } else if (block == BlockInit.INVISIBILITY_ACTIVATOR) {
                effect = StatusEffects.INVISIBILITY;
            } else if (block == BlockInit.NIGHT_VISION_ACTIVATOR) {
                effect = StatusEffects.NIGHT_VISION;
            } else if (block == BlockInit.STRENGTH_ACTIVATOR) {
                effect = StatusEffects.STRENGTH;
            }
            if (effect != null) {
                if (!effects.contains(effect)) {
                    effects.add(effect);
                }
            }
        }
        if (!effects.isEmpty()) {
            boolean isBeacon = CommonConfig.dimensional_applicator_is_beacon_effect;
            boolean showParticles = CommonConfig.dimensional_applicator_show_particles;
            boolean showIcon = CommonConfig.dimensional_applicator_show_icon;
            for (StatusEffect effect : effects) {
                if (effect != null) {
                    StatusEffectInstance effectInstance = new StatusEffectInstance(effect, getDuration(), getAmplifier() * getSpaceRippers(world, pos), isBeacon, showParticles, showIcon);
                    if (!effectInstances.contains(effectInstance)) {
                        effectInstances.add(effectInstance);
                    }
                }
            }
        }
        return effectInstances;
    }

    protected int getSpaceRippers(World world, BlockPos pos) {
        int count = 0;
        for (Direction side : Direction.values()) {
            Block block = world.getBlockState(pos.offset(side)).getBlock();
            if (block instanceof SpaceRipperBlock) {
                count++;
            }
        }
        return count;
    }

    protected int getDuration() {
        return CommonConfig.dimensional_applicator_duration;
    }

    protected int getAmplifier() {
        return CommonConfig.dimensional_applicator_amplifier;
    }

    protected double getPrice() {
        return CommonConfig.dimensional_applicator_price;
    }

    @Override
    public double getMaxStoredPower() {
        int multiplier = CommonConfig.dimensional_applicator_capacity_multiplier;
        return getPrice() * multiplier;
    }

    @Override
    public double getCustomMaxInput() {
        return getMaxStoredPower();
    }

    @Override
    public double getCustomMaxOutput() {
        return getMaxStoredPower();
    }

    @Override
    public NbtCompound saveSerializedValues() {
        NbtCompound nbt = super.saveSerializedValues();
        if (playerId != null) {
            nbt.putUuid(CustomValues.playerNBTKey, playerId);
        }
        if (!NBTEffects.isEmpty()) {
            nbt.putIntArray(CustomValues.potionsNBTKey, NBTEffects);
        }
        return nbt;
    }

    @Override
    public void setDeserializedValues(NbtCompound nbt) {
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
