package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.MagmaBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.ArrayList;

public class HeatGeneratorTE extends TickingRedstoneEnergyMachineTE {
    public HeatGeneratorTE() {
        super(TileEntityInit.HEAT_GENERATOR.get(), true, true);
    }

    @Override
    public void customTickActions() {
        boolean enabled = CommonConfig.ENABLE_HEAT_GENERATOR.get();
        if (enabled) {
            super.customTickActions();
            if (world != null && !world.isRemote) {
                setActive(false);
                ArrayList<IEnergyStorage> outBatteries = new ArrayList<>();
                for (Direction side : Direction.values()) {
                    Block block = world.getBlockState(pos.offset(side)).getBlock();
                    if (block instanceof MagmaBlock || block == Blocks.LAVA || block instanceof FireBlock) {
                        if (getEnergyStorage().getEnergyStored() < getEnergyStorage().getMaxEnergyStored()) {
                            getEnergyStorage().addEnergy(getSpeed());
                            setActive(true);
                        }
                    }
                    TileEntity out = world.getTileEntity(pos.offset(side));
                    if (out != null) {
                        LazyOptional<IEnergyStorage> outCap = out.getCapability(CapabilityEnergy.ENERGY, side.getOpposite());
                        outCap.ifPresent(outBatteries::add);
                    }
                }
                if (!outBatteries.isEmpty()) {
                    for (IEnergyStorage outBattery : outBatteries) {
                        int energy = getEnergyStorage().getEnergyStored();
                        if (energy > 0) {
                            int outReceived = outBattery.receiveEnergy(energy, false);
                            if (outReceived > 0) {
                                setActive(true);
                                getEnergyStorage().consumeEnergy(outReceived);
                            }
                        }
                    }
                }
            }
        } else {
            setActive(false);
        }
    }

    public int getSpeed() {
        return CommonConfig.HEAT_GENERATOR_SPEED.get();
    }

    @Override
    protected int getCapacity() {
        return getSpeed() * 100;
    }

    @Override
    protected int getMaxInput() {
        return getCapacity();
    }

    @Override
    protected int getMaxExtract() {
        return getCapacity();
    }
}
