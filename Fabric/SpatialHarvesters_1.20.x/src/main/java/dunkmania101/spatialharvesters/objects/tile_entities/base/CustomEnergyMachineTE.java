package dunkmania101.spatialharvesters.objects.tile_entities.base;

import dunkmania101.spatialharvesters.data.CustomValues;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import team.reborn.energy.api.EnergyStorage;

public class CustomEnergyMachineTE extends BlockEntity implements EnergyStorage {
    private long energyStored = 0;

    private boolean setCanExtract = false;
    private boolean setCanReceive = false;

    public CustomEnergyMachineTE(BlockEntityType<?> blockEntityTypeIn, BlockPos pos, BlockState state) {
        super(blockEntityTypeIn, pos, state);
    }

    public CustomEnergyMachineTE(BlockEntityType<?> blockEntityTypeIn, BlockPos pos, BlockState state, boolean canExtract, boolean canReceive) {
        this(blockEntityTypeIn, pos, state);

        this.setCanExtract = canExtract;
        this.setCanReceive = canReceive;
    }

    public NbtCompound saveSerializedValues() {
        NbtCompound nbt = new NbtCompound();
        long energy = getAmount();
        nbt.putLong(CustomValues.energyStorageKey, energy);
        return nbt;
    }

    public void setDeserializedValues(NbtCompound nbt) {
        if (nbt.contains(CustomValues.energyStorageKey)) {
            long energy = nbt.getLong(CustomValues.energyStorageKey);
            setAmount(energy);
        }
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.copyFrom(saveSerializedValues());
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        setDeserializedValues(tag);
        markDirty();
    }

    public long getCustomMaxInput() {
        return 0;
    }

    public long getCustomMaxOutput() {
        return 0;
    }

    @Override
    public long getAmount() {
        return this.energyStored;
    }

    protected void onEnergyChanged() {
        long energy = getAmount();
        if (energy > getCapacity()) {
            setAmount(getCapacity());
        } else if (energy < 0) {
            setAmount(0);
        }
        markDirty();
    }

    public void setAmount(long energy) {
        this.energyStored = energy;
        onEnergyChanged();
    }

    @Override
    public long getCapacity() {
        return Long.MAX_VALUE;
    }

    @Override
    public boolean supportsInsertion() {
        return this.setCanReceive;
    }

    @Override
    public boolean supportsExtraction() {
        return this.setCanExtract;
    }

    public long insert(long maxAmount, TransactionContext context) {
        long inserted = Math.min(maxAmount, getCapacity() - getAmount());
        setAmount(getAmount() + inserted);
        return inserted;
    }

    public long extract(long maxAmount, TransactionContext context) {
        long extracted = Math.min(maxAmount, getAmount());
        setAmount(getAmount() - extracted);
        return extracted;
    }

    public long insert(long maxAmount) {
        return insert(maxAmount, null);
    }

    public long extract(long maxAmount) {
        return extract(maxAmount, null);
    }
}
