package dunkmania101.spatialharvesters.objects.tile_entities.base;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import dunkmania101.spatialharvesters.data.CustomEnergyStorage;
import dunkmania101.spatialharvesters.data.CustomValues;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class CustomEnergyMachineTE extends BlockEntity {
    private final LazyOptional<IEnergyStorage> energy = createEnergy();
    private boolean setCanExtract = false;
    private boolean setCanReceive = false;
    private final CustomEnergyStorage energyStorage = createEnergyStorage(getCapacity(), getMaxInput(), getMaxExtract());

    public CustomEnergyMachineTE(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }
    public CustomEnergyMachineTE(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, boolean canExtract, boolean canReceive) {
        this(tileEntityTypeIn, pos, state);

        this.setCanExtract = canExtract;
        this.setCanReceive = canReceive;
    }

    protected CustomEnergyStorage createEnergyStorage(int capacity, int maxInput, int maxExtract) {
        return new CustomEnergyStorage(capacity, maxInput, maxExtract, 0) {
            @Override
            protected void onEnergyChanged() {
                super.onEnergyChanged();
                setChanged();
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
    public void setRemoved() {
        super.setRemoved();
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

    public CompoundTag saveSerializedValues() {
        CompoundTag nbt = new CompoundTag();
        int energy = getEnergyStorage().getEnergyStored();
        nbt.putInt(CustomValues.energyStorageKey, energy);
        return nbt;
    }

    public void setDeserializedValues(CompoundTag nbt) {
        if (nbt.contains(CustomValues.energyStorageKey)) {
            updateEnergyStorage();
            int energy = nbt.getInt(CustomValues.energyStorageKey);
            getEnergyStorage().setEnergyStored(energy);
        }
    }

    @Nonnull
    @Override
    public CompoundTag save(@Nonnull CompoundTag compound) {
        CompoundTag nbt = super.save(compound);
        nbt.merge(saveSerializedValues());
        return nbt;
    }

    @Override
    public void load(@Nonnull CompoundTag nbt) {
        super.load(nbt);
        setDeserializedValues(nbt);
        setChanged();
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
