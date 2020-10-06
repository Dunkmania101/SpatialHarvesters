package dunkmania101.spatialharvesters.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class CustomEnergyStorage extends EnergyStorage implements INBTSerializable<CompoundNBT> {
    private static final String energyKey = "energy";
    public CustomEnergyStorage(int capacity, int maxInput, int maxOutput) {
        super(capacity, maxInput, maxOutput);
    }

    protected void onEnergyChanged() {
        if (getEnergyStored() > getMaxEnergyStored()) {
            setEnergyStored(getMaxEnergyStored());
        }
        if (getEnergyStored() < 0) {
            setEnergyStored(0);
        }
    }

    public void setEnergyStoredNoCheck(int energy) {
        this.energy = energy;
    }

    public void setEnergyStored(int energy) {
        setEnergyStoredNoCheck(energy);
        onEnergyChanged();
    }

    @Override
    public int getEnergyStored() {
        return this.energy;
    }

    public void setMaxEnergy(int energy) {
        this.capacity = energy;
    }

    @Override
    public int getMaxEnergyStored() {
        return this.capacity;
    }

    public void setMaxInput(int energy) {
        this.maxReceive = energy;
    }

    public int getMaxInput() {
        return this.maxReceive;
    }

    public void setMaxExtract(int energy) {
        this.maxExtract = energy;
    }

    public int getMaxExtract() {
        return this.maxExtract;
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

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt(energyKey, getEnergyStored());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (nbt.contains(energyKey)) {
            setEnergyStoredNoCheck(nbt.getInt(energyKey));
        }
    }
}
