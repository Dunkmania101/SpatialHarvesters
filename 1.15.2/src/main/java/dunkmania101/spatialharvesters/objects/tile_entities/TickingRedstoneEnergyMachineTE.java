package dunkmania101.spatialharvesters.objects.tile_entities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TickingRedstoneEnergyMachineTE extends CustomEnergyMachineTE implements ITickableTileEntity {
    private static final String countedTicksKey = "countedTicks";
    private final boolean countTicks;
    public TickingRedstoneEnergyMachineTE(TileEntityType<?> tileEntityTypeIn, boolean canExtract, boolean canReceive, boolean countTicks) {
        super(tileEntityTypeIn, canExtract, canReceive);

        this.countTicks = countTicks;
    }

    public TickingRedstoneEnergyMachineTE(TileEntityType<?> tileEntityTypeIn, boolean canExtract, boolean canReceive) {
        this(tileEntityTypeIn, canExtract, canReceive, false);
    }

    protected int ticks = 0;
    @Override
    public void tick() {
        if (world != null) {
            if (world.isBlockPowered(pos)) {
                world.addParticle(RedstoneParticleData.REDSTONE_DUST, pos.getX(), pos.getY(), pos.getZ(), 5, 5, 5);
            } else {
                customTickActions();
                if (this.countTicks) {
                    this.ticks++;
                }
            }
        }
    }

    public void customTickActions() {
    }

    public int getCountedTicks() {
        return this.ticks;
    }

    public void resetCountedTicks() {
        this.ticks = 0;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = super.serializeNBT();
        if (countTicks) {
            nbt.putInt(countedTicksKey, getCountedTicks());
        }
        return  nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        super.deserializeNBT(nbt);
        if (nbt.contains(countedTicksKey)) {
            this.ticks = nbt.getInt(countedTicksKey);
        }
    }
}
