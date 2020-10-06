package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CustomEnergyStorage;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.util.Tools;
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

    private final CustomEnergyStorage energyStorage = createEnergyStorage();
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    private CustomEnergyStorage createEnergyStorage() {
        int capacity = 500;
        return new CustomEnergyStorage(capacity, capacity, capacity) {
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

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = super.serializeNBT();
        nbt.put(CustomValues.tileEnergyKey, getEnergyStorage().serializeNBT());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        super.deserializeNBT(Tools.correctTileNBT(getTileEntity(), nbt));
        if (nbt.contains(CustomValues.tileEnergyKey)) {
            CompoundNBT energyNBT = nbt.getCompound(CustomValues.tileEnergyKey);
            getEnergyStorage().deserializeNBT(energyNBT);
        }
        markDirty();
    }
}
