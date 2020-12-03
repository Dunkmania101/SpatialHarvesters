package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CustomValues;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import team.reborn.energy.*;

public class CustomEnergyMachineTE extends BlockEntity implements EnergyStorage {
    private boolean setCanExtract = false;
    private boolean setCanReceive = false;
    private double energyStored = 0;
    private double setMaxStored = 0;
    private double setMaxExtract = 0;
    private double setMaxInput = 0;

    public CustomEnergyMachineTE(BlockEntityType<?> blockEntityTypeIn) {
        super(blockEntityTypeIn);
    }

    public CustomEnergyMachineTE(BlockEntityType<?> blockEntityTypeIn, boolean canExtract, boolean canReceive) {
        this(blockEntityTypeIn);

        this.setCanExtract = canExtract;
        this.setCanReceive = canReceive;
    }

    protected void updateEnergyStorage() {
        if (getEnergyStorage().getMaxStored() != getCapacity()) {
            this.setMaxStored = getCapacity();
        }
        if (getEnergyStorage().getMaxInput() != getMaxInput()) {
            this.setMaxInput = getMaxInput();
        }
        if (getEnergyStorage().getMaxOutput() != getMaxExtract()) {
            this.setMaxExtract = getMaxExtract();
        }
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
            updateEnergyStorage();
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

    protected int getCapacity() {
        return Integer.MAX_VALUE;
    }

    protected int getMaxInput() {
        return 0;
    }

    protected int getMaxExtract() {
        return 0;
    }

    @Override
    public double getMaxInput(EnergySide side) {
        if (this.setCanReceive) {
            return this.setMaxInput;
        }
        return 0;
    }

    @Override
    public double getMaxOutput(EnergySide side) {
        if (this.setCanExtract) {
            return this.setMaxExtract;
        }
        return 0;
    }

    @Override
    public double getStored(EnergySide energySide) {
        return this.energyStored;
    }

    @Override
    public void setStored(double energy) {
        this.energyStored = energy;
        onEnergyChanged();
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
    public double getMaxStoredPower() {
        return this.setMaxStored;
    }

    @Override
    public EnergyTier getTier() {
        return null;
    }
}
