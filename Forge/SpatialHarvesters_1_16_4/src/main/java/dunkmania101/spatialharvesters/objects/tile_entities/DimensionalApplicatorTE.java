package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.objects.blocks.SpaceRipperBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
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
            if (this.playerId != null && getWorld() != null && !getWorld().isRemote) {
                double divisor = CommonConfig.DIMENSIONAL_APPLICATOR_DIVISOR.get();
                if (getCountedTicks() >= (getDuration() / divisor)) {
                    resetCountedTicks();
                    setActive(false);
                    if (getEnergyStorage().getEnergyStored() >= getPrice()) {
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
                                    ArrayList<EffectInstance> effects = getEffects(getWorld(), getPos());
                                    for (EffectInstance effect : effects) {
                                        if (effect != null) {
                                            if (getEnergyStorage().getEnergyStored() >= getPrice()) {
                                                setActive(true);
                                                player.addPotionEffect(effect);
                                                getEnergyStorage().consumeEnergy(getPrice());
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

    private ArrayList<EffectInstance> getEffects(World worldIn, BlockPos pos) {
        ArrayList<EffectInstance> effectInstances = new ArrayList<>();
        ArrayList<Effect> effects = new ArrayList<>();
        for (int id : this.NBTEffects) {
            Effect effect = Effect.get(id);
            if (effect != null) {
                if (!effects.contains(effect)) {
                    effects.add(effect);
                }
            }
        }
        for (Direction check_direction : Direction.values()) {
            Block block = worldIn.getBlockState(pos.offset(check_direction)).getBlock();
            Effect effect = null;
            if (block == BlockInit.REGENERATION_ACTIVATOR.get()) {
                effect = Effects.REGENERATION;
            } else if (block == BlockInit.RESISTANCE_ACTIVATOR.get()) {
                effect = Effects.RESISTANCE;
            } else if (block == BlockInit.ABSORPTION_ACTIVATOR.get()) {
                effect = Effects.ABSORPTION;
            } else if (block == BlockInit.HASTE_ACTIVATOR.get()) {
                effect = Effects.HASTE;
            } else if (block == BlockInit.SPEED_ACTIVATOR.get()) {
                effect = Effects.SPEED;
            } else if (block == BlockInit.JUMP_BOOST_ACTIVATOR.get()) {
                effect = Effects.JUMP_BOOST;
            } else if (block == BlockInit.INVISIBILITY_ACTIVATOR.get()) {
                effect = Effects.INVISIBILITY;
            } else if (block == BlockInit.NIGHT_VISION_ACTIVATOR.get()) {
                effect = Effects.NIGHT_VISION;
            } else if (block == BlockInit.STRENGTH_ACTIVATOR.get()) {
                effect = Effects.STRENGTH;
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
            for (Effect effect : effects) {
                if (effect != null) {
                    EffectInstance effectInstance = new EffectInstance(effect, getDuration(), getAmplifier(), isBeacon, showParticles, showIcon);
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
    public CompoundNBT saveSerializedValues() {
        CompoundNBT nbt = super.saveSerializedValues();
        if (playerId != null) {
            nbt.putUniqueId(CustomValues.playerNBTKey, playerId);
        }
        if (NBTEffects.size() > 0) {
            nbt.putIntArray(CustomValues.potionsNBTKey, NBTEffects);
        }
        return nbt;
    }

    @Override
    public void setDeserializedValues(CompoundNBT nbt) {
        super.setDeserializedValues(nbt);
        if (nbt.contains(CustomValues.removePlayerNBTKey)) {
            this.playerId = null;
        }
        if (nbt.contains(CustomValues.removePotionsNBTKey)) {
            this.NBTEffects = new ArrayList<>();
        }
        if (nbt.contains(CustomValues.playerNBTKey)) {
            this.playerId = nbt.getUniqueId(CustomValues.playerNBTKey);
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
