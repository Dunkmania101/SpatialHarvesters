package dunkmania101.spatialharvesters.objects.tile_entities.base;

import dunkmania101.spatialharvesters.data.CustomValues;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import team.reborn.energy.*;

public class CustomEnergyMachineTE extends BlockEntity implements EnergyStorage {
    private double energyStored = 0;

    private boolean setCanExtract = false;
    private boolean setCanReceive = false;

    public CustomEnergyMachineTE(BlockEntityType<?> blockEntityTypeIn) {
        super(blockEntityTypeIn);
    }

    public CustomEnergyMachineTE(BlockEntityType<?> blockEntityTypeIn, boolean canExtract, boolean canReceive) {
        this(blockEntityTypeIn);

        this.setCanExtract = canExtract;
        this.setCanReceive = canReceive;
    }

    protected EnergyHandler getEnergyStorage() {
        return Energy.of(this);
    }

    public CompoundTag saveSerializedValues() {
        CompoundTag nbt = new CompoundTag();
        double energy = getEnergyStorage().getEnergy();
        nbt.putDouble(CustomValues.energyStorageKey, energy);
        return nbt;
    }

    public void setDeserializedValues(CompoundTag nbt) {
        if (nbt.contains(CustomValues.energyStorageKey)) {
            double energy = nbt.getDouble(CustomValues.energyStorageKey);
            getEnergyStorage().set(energy);
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        CompoundTag nbt = super.toTag(tag);
        return nbt.copyFrom(saveSerializedValues());
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        setDeserializedValues(tag);
        markDirty();
    }

    public double getCustomMaxInput() {
        return 0;
    }

    public double getCustomMaxOutput() {
        return 0;
    }

    @Override
    public double getMaxInput(EnergySide side) {
        if (this.setCanReceive) {
            return getCustomMaxInput();
        }
        return 0;
    }

    @Override
    public double getMaxOutput(EnergySide side) {
        if (this.setCanExtract) {
            return getCustomMaxOutput();
        }
        return 0;
    }

    @Override
    public double getStored(EnergySide energySide) {
        return this.energyStored;
    }

    protected void onEnergyChanged() {
        double energy = getStored(EnergySide.UNKNOWN);
        if (energy > getMaxStoredPower()) {
            setStored(getMaxStoredPower());
        } else if (energy < 0) {
            setStored(0);
        }
        markDirty();
    }

    @Override
    public void setStored(double energy) {
        this.energyStored = energy;
        onEnergyChanged();
    }

    @Override
    public double getMaxStoredPower() {
        return Double.MAX_VALUE;
    }

    @Override
    public EnergyTier getTier() {
        return null;
    }
}
