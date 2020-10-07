package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CustomEnergyStorage;
import dunkmania101.spatialharvesters.data.CustomValues;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CustomEnergyMachineTE extends TileEntity {
    public CustomEnergyMachineTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    private boolean setCanExtract = false;
    private boolean setCanReceive = false;
    public CustomEnergyMachineTE(TileEntityType<?> tileEntityTypeIn, boolean canExtract, boolean canReceive) {
        this(tileEntityTypeIn);

        this.setCanExtract = canExtract;
        this.setCanReceive = canReceive;
    }

    private final CustomEnergyStorage energyStorage = createEnergyStorage(getCapacity(), getMaxInput(), getMaxExtract());
    private final LazyOptional<IEnergyStorage> energy = createEnergy();

    protected CustomEnergyStorage createEnergyStorage(int capacity, int maxInput, int maxExtract) {
        return new CustomEnergyStorage(capacity, maxInput, maxExtract, 0) {
            @Override
            protected void onEnergyChanged() {
                super.onEnergyChanged();
                markDirty();
            }

            @Override
            public boolean canExtract() {
                return super.canExtract() && setCanExtract;
            }

            @Override
            public boolean canReceive() {
                return super.canReceive() && setCanReceive;
            }
        };
    }

    protected LazyOptional<IEnergyStorage> createEnergy() {
        if (this.energy != null) {
            this.energy.invalidate();
        }
        return LazyOptional.of(() -> this.energyStorage);
    }

    protected void updateEnergyStorage() {
        if (getEnergyStorage().getMaxEnergyStored() != getCapacity()) {
            getEnergyStorage().setMaxEnergyStored(getCapacity());
        }
        if (getEnergyStorage().getMaxInput() != getMaxInput()) {
            getEnergyStorage().setMaxInput(getMaxInput());
        }
        if (getEnergyStorage().getMaxExtract() != getMaxExtract()) {
            getEnergyStorage().setMaxExtract(getMaxExtract());
        }
    }

    @Override
    public void remove() {
        super.remove();
        this.energy.invalidate();
    }

    protected CustomEnergyStorage getEnergyStorage() {
        return this.energyStorage;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return this.energy.cast();
        }
        return super.getCapability(cap, side);
    }

    public CompoundNBT saveSerializedValues() {
        CompoundNBT nbt = new CompoundNBT();
        int energyNBT = getEnergyStorage().getEnergyStored();
        nbt.putInt(CustomValues.energyStorageKey, energyNBT);
        return nbt;
    }

    public void setDeserializedValues(CompoundNBT nbt) {
        if (nbt.contains(CustomValues.energyStorageKey)) {
            updateEnergyStorage();
            int energy = nbt.getInt(CustomValues.energyStorageKey);
            getEnergyStorage().setEnergyStored(energy);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT nbt = super.write(compound);
        return nbt.merge(saveSerializedValues());
    }

    @Override
    public void func_230337_a_(BlockState state, CompoundNBT nbt) {
        super.func_230337_a_(state, nbt);
        setDeserializedValues(nbt);
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
}
