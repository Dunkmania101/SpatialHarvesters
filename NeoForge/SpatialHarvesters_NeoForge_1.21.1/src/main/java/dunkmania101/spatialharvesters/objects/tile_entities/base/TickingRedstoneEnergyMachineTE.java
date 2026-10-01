package dunkmania101.spatialharvesters.objects.tile_entities.base;

import dunkmania101.spatialharvesters.data.CustomProperties;
import dunkmania101.spatialharvesters.data.CustomValues;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class TickingRedstoneEnergyMachineTE extends CustomEnergyMachineTE {
    private final boolean countTicks;
    protected boolean active = false;
    protected int ticks = 0;

    public TickingRedstoneEnergyMachineTE(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, boolean canExtract, boolean canReceive, boolean countTicks) {
        super(tileEntityTypeIn, pos, state, canExtract, canReceive);

        this.countTicks = countTicks;
    }

    public TickingRedstoneEnergyMachineTE(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, boolean canExtract, boolean canReceive) {
        this(tileEntityTypeIn, pos, state, canExtract, canReceive, false);
    }

    public void tick() {
        if (getLevel() != null && !getLevel().isClientSide()) {
            if (getBlockState().getOptionalValue(BlockStateProperties.POWERED).orElseGet(() -> false)) {
                setActive(false);
                getLevel().addParticle(DustParticleOptions.REDSTONE, getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), 5, 5, 5);
            } else {
                customTickActions();
                if (this.countTicks) {
                    this.ticks++;
                }
            }
            if (getBlockState().hasProperty(CustomProperties.ACTIVE)) {
                if (getBlockState().getValue(CustomProperties.ACTIVE) != getActive()) {
                    getLevel().setBlockAndUpdate(getBlockPos(), getBlockState().setValue(CustomProperties.ACTIVE, getActive()));
                }
            }
        }
    }

    public void customTickActions() {
    }

    public void setCountedTicks(int ticks) {
        this.ticks = ticks;
    }

    public void resetCountedTicks() {
        setCountedTicks(0);
    }

    public int getCountedTicks() {
        return this.ticks;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getActive() {
        return this.active;
    }

    @Override
    public CompoundTag saveSerializedValues() {
        CompoundTag nbt = super.saveSerializedValues();
        if (this.countTicks) {
            nbt.putInt(CustomValues.countedTicksKey, getCountedTicks());
        }
        return nbt;
    }

    @Override
    public void setDeserializedValues(CompoundTag nbt) {
        super.setDeserializedValues(nbt);
        if (nbt.contains(CustomValues.countedTicksKey)) {
            this.ticks = nbt.getInt(CustomValues.countedTicksKey);
        }
    }
}
