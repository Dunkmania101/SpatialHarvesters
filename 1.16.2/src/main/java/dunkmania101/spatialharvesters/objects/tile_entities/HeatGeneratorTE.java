package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.Config;
import dunkmania101.spatialharvesters.data.CustomEnergyStorage;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HeatGeneratorTE extends TileEntity implements ITickableTileEntity {
    public HeatGeneratorTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public HeatGeneratorTE() {
        this(TileEntityInit.HEAT_GENERATOR.get());
    }

    private CustomEnergyStorage energyStorage = createEnergy();

    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    private CustomEnergyStorage createEnergy() {
        return new CustomEnergyStorage(Config.HEAT_GENERATOR_CAPACITY.get(), Config.HEAT_GENERATOR_TRANSFER.get()) {
            @Override
            protected void onEnergyChanged() {
                markDirty();
            }

            @Override
            public boolean canExtract() {
                return true;
            }

            @Override
            public boolean canReceive() {
                return true;
            }
        };
    }

    @Override
    public void remove() {
        super.remove();
        energy.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {
        if (world != null) {
            int speed = Config.HEAT_GENERATOR_SPEED.get();
            for (Direction direction : Direction.values()) {
                int energy = energyStorage.getEnergyStored();
                if (energy + speed <= energyStorage.getMaxEnergyStored()) {
                    Block block = world.getBlockState(pos.offset(direction)).getBlock();
                    if (block == Blocks.MAGMA_BLOCK || block == Blocks.LAVA) {
                        energyStorage.addEnergy(speed);
                    }
                }
                if (energy > 0) {
                    TileEntity te = world.getTileEntity(pos.offset(direction));
                    if (te != null) {
                        LazyOptional<IEnergyStorage> te_energy = te.getCapability(CapabilityEnergy.ENERGY, direction);
                        if (te_energy.isPresent()) {
                            IEnergyStorage te_energy_handler = te_energy.orElse(null);
                            int te_available = te_energy_handler.getMaxEnergyStored() - te_energy_handler.getEnergyStored();
                            int te_received = te_energy_handler.receiveEnergy(energy - te_available, false);
                            if (te_received > 0) {
                                energyStorage.consumeEnergy(te_received);
                            }
                        }
                    }
                }
            }
        }
    }
}
