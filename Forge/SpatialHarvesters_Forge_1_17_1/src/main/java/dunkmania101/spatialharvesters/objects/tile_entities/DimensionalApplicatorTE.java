package dunkmania101.spatialharvesters.objects.tile_entities;

import java.util.ArrayList;
import java.util.UUID;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.objects.blocks.SpaceRipperBlock;
import dunkmania101.spatialharvesters.objects.tile_entities.base.TickingRedstoneEnergyMachineTE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class DimensionalApplicatorTE extends TickingRedstoneEnergyMachineTE {
    private UUID playerId = null;
    private final ArrayList<Integer> NBTEffects = new ArrayList<>();

    public DimensionalApplicatorTE(BlockPos pos, BlockState state) {
        super(TileEntityInit.DIMENSIONAL_APPLICATOR.get(), pos, state, true, true, true);
    }

    @Override
    public void customTickActions() {
        boolean enabled = CommonConfig.ENABLE_DIMENSIONAL_APPLICATOR.get();
        if (enabled) {
            super.customTickActions();
            if (this.playerId != null && getLevel() != null && !getLevel().isClientSide()) {
                double divisor = CommonConfig.DIMENSIONAL_APPLICATOR_DIVISOR.get();
                if (getCountedTicks() >= (getDuration() / divisor)) {
                    resetCountedTicks();
                    setActive(false);
                    if (getEnergyStorage().getEnergyStored() >= getPrice()) {
                        if (getSpaceRippers(getLevel(), getBlockPos()) > 0) {
                            if (getLevel().getServer() != null) {
                                Player player = getLevel().getServer().getPlayerList().getPlayer(this.playerId);
                                if (player != null) {
                                    ArrayList<MobEffectInstance> effects = getEffects(getLevel(), getBlockPos());
                                    for (MobEffectInstance effect : effects) {
                                        if (effect != null) {
                                            if (getEnergyStorage().getEnergyStored() >= getPrice()) {
                                                setActive(true);
                                                player.addEffect(effect);
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

    private ArrayList<MobEffectInstance> getEffects(Level worldIn, BlockPos pos) {
        ArrayList<MobEffectInstance> effectInstances = new ArrayList<>();
        ArrayList<MobEffect> effects = new ArrayList<>();
        for (int id : this.NBTEffects) {
            MobEffect effect = MobEffect.byId(id);
            if (effect != null) {
                if (!effects.contains(effect)) {
                    effects.add(effect);
                }
            }
        }
        for (Direction check_direction : Direction.values()) {
            Block block = worldIn.getBlockState(pos.relative(check_direction)).getBlock();
            MobEffect effect = null;
            if (block == BlockInit.REGENERATION_ACTIVATOR.get()) {
                effect = MobEffects.REGENERATION;
            } else if (block == BlockInit.RESISTANCE_ACTIVATOR.get()) {
                effect = MobEffects.DAMAGE_RESISTANCE;
            } else if (block == BlockInit.ABSORPTION_ACTIVATOR.get()) {
                effect = MobEffects.ABSORPTION;
            } else if (block == BlockInit.HASTE_ACTIVATOR.get()) {
                effect = MobEffects.DIG_SPEED;
            } else if (block == BlockInit.SPEED_ACTIVATOR.get()) {
                effect = MobEffects.MOVEMENT_SPEED;
            } else if (block == BlockInit.JUMP_BOOST_ACTIVATOR.get()) {
                effect = MobEffects.JUMP;
            } else if (block == BlockInit.INVISIBILITY_ACTIVATOR.get()) {
                effect = MobEffects.INVISIBILITY;
            } else if (block == BlockInit.NIGHT_VISION_ACTIVATOR.get()) {
                effect = MobEffects.NIGHT_VISION;
            } else if (block == BlockInit.STRENGTH_ACTIVATOR.get()) {
                effect = MobEffects.DAMAGE_BOOST;
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
            for (MobEffect effect : effects) {
                if (effect != null) {
                    MobEffectInstance effectInstance = new MobEffectInstance(effect, getDuration(), getAmplifier() * getSpaceRippers(worldIn, pos), isBeacon, showParticles, showIcon);
                    if (!effectInstances.contains(effectInstance)) {
                        effectInstances.add(effectInstance);
                    }
                }
            }
        }
        return effectInstances;
    }

    protected int getSpaceRippers(Level world, BlockPos pos) {
        int count = 0;
        for (Direction side : Direction.values()) {
            Block block = world.getBlockState(pos.relative(side)).getBlock();
            if (block instanceof SpaceRipperBlock) {
                count++;
            }
        }
        return count;
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
            nbt.putUUID(CustomValues.playerNBTKey, playerId);
        }
        if (this.NBTEffects.size() > 0) {
            nbt.putIntArray(CustomValues.potionsNBTKey, this.NBTEffects);
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
            this.NBTEffects.clear();
        }
        if (nbt.contains(CustomValues.playerNBTKey)) {
            this.playerId = nbt.getUUID(CustomValues.playerNBTKey);
        }
        if (nbt.contains(CustomValues.potionsNBTKey)) {
            this.NBTEffects.clear();
            for (int potion : nbt.getIntArray(CustomValues.potionsNBTKey)) {
                if (!NBTEffects.contains(potion)) {
                    NBTEffects.add(potion);
                }
            }
        }
    }
}
