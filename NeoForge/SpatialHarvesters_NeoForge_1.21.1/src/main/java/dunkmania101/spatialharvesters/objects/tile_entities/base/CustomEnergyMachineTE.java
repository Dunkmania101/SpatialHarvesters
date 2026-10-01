package dunkmania101.spatialharvesters.objects.tile_entities.base;

import javax.annotation.Nonnull;

import dunkmania101.spatialharvesters.data.CustomEnergyStorage;
import dunkmania101.spatialharvesters.data.CustomValues;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CustomEnergyMachineTE extends BlockEntity {
    private boolean setCanExtract = false;
    private boolean setCanReceive = false;
    private CustomEnergyStorage energyStorage = null;

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

    protected void updateEnergyStorage() {
        boolean changed = false;
        if (getEnergyStorage().getMaxEnergyStored() != getCapacity()) {
            getEnergyStorage().setMaxEnergyStored(getCapacity());
            changed = true;
        }
        if (getEnergyStorage().getMaxInput() != getMaxInput()) {
            getEnergyStorage().setMaxInput(getMaxInput());
            changed = true;
        }
        if (getEnergyStorage().getMaxExtract() != getMaxExtract()) {
            getEnergyStorage().setMaxExtract(getMaxExtract());
            changed = true;
        }
        if (changed) {
            invalidateCapabilities();
        }
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        invalidateCapabilities();
    }

    public CustomEnergyStorage getEnergyStorage() {
        if (this.energyStorage == null) {
            this.energyStorage = createEnergyStorage(getCapacity(), getMaxInput(), getMaxExtract());
        }
        return this.energyStorage;
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

    @Override
    public void saveAdditional(@Nonnull CompoundTag nbt, @Nonnull HolderLookup.Provider registries) {
        super.saveAdditional(nbt, registries);
        nbt.merge(saveSerializedValues());
    }

    @Override
    public void loadAdditional(@Nonnull CompoundTag nbt, @Nonnull HolderLookup.Provider registries) {
        super.loadAdditional(nbt, registries);
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
