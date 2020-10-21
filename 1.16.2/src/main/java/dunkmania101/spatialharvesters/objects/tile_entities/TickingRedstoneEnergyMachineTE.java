package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CustomProperties;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.blocks.ActiveCustomHorizontalShapedBlock;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TickingRedstoneEnergyMachineTE extends CustomEnergyMachineTE implements ITickableTileEntity {
    private final boolean countTicks;
    protected boolean active = false;
    protected int ticks = 0;

    public TickingRedstoneEnergyMachineTE(TileEntityType<?> tileEntityTypeIn, boolean canExtract, boolean canReceive, boolean countTicks) {
        super(tileEntityTypeIn, canExtract, canReceive);

        this.countTicks = countTicks;
    }

    public TickingRedstoneEnergyMachineTE(TileEntityType<?> tileEntityTypeIn, boolean canExtract, boolean canReceive) {
        this(tileEntityTypeIn, canExtract, canReceive, false);
    }

    @Override
    public void tick() {
        if (getWorld() != null) {
            if (getWorld().isBlockPowered(pos)) {
                setActive(false);
                getWorld().addParticle(RedstoneParticleData.REDSTONE_DUST, getPos().getX(), getPos().getY(), getPos().getZ(), 5, 5, 5);
            } else {
                customTickActions();
                if (this.countTicks) {
                    this.ticks++;
                }
            }
            if (getBlockState().getBlock() instanceof ActiveCustomHorizontalShapedBlock) {
                if (getBlockState().get(CustomProperties.ACTIVE) != getActive()) {
                    getWorld().setBlockState(getPos(), getBlockState().with(CustomProperties.ACTIVE, getActive()));
                }
            }
        }
    }

    public void customTickActions() {
    }

    public void resetCountedTicks() {
        this.ticks = 0;
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
    public CompoundNBT saveSerializedValues() {
        CompoundNBT nbt = super.saveSerializedValues();
        if (countTicks) {
            nbt.putInt(CustomValues.countedTicksKey, getCountedTicks());
        }
        return nbt;
    }

    @Override
    public void setDeserializedValues(CompoundNBT nbt) {
        super.setDeserializedValues(nbt);
        if (nbt.contains(CustomValues.countedTicksKey)) {
            this.ticks = nbt.getInt(CustomValues.countedTicksKey);
        }
    }
}
