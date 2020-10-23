package dunkmania101.spatialharvesters.data;

import net.minecraftforge.energy.EnergyStorage;

public class CustomEnergyStorage extends EnergyStorage {
    public CustomEnergyStorage(int capacity, int maxInput, int maxOutput, int energy) {
        super(capacity, maxInput, maxOutput, energy);
    }

    protected void onEnergyChanged() {
        if (getEnergyStored() > getMaxEnergyStored()) {
            setEnergyStored(getMaxEnergyStored());
        }
        if (getEnergyStored() < 0) {
            setEnergyStored(0);
        }
    }

    @Override
    public int getEnergyStored() {
        return this.energy;
    }

    public void setEnergyStored(int energy) {
        this.energy = energy;
        onEnergyChanged();
    }

    @Override
    public int getMaxEnergyStored() {
        return this.capacity;
    }

    public void setMaxEnergyStored(int energy) {
        this.capacity = energy;
        onEnergyChanged();
    }

    public int getMaxInput() {
        return this.maxReceive;
    }

    public void setMaxInput(int energy) {
        this.maxReceive = energy;
        onEnergyChanged();
    }

    public int getMaxExtract() {
        return this.maxExtract;
    }

    public void setMaxExtract(int energy) {
        this.maxExtract = energy;
        onEnergyChanged();
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int received = super.receiveEnergy(maxReceive, simulate);
        onEnergyChanged();
        return received;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int extracted = super.extractEnergy(maxExtract, simulate);
        onEnergyChanged();
        return extracted;
    }

    public void addEnergy(int energy) {
        receiveEnergy(energy, false);
    }

    public void consumeEnergy(int energy) {
        extractEnergy(energy, false);
    }
}
