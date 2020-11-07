package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.MagmaBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.Direction;
import team.reborn.energy.Energy;
import team.reborn.energy.EnergyHandler;

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
            if (getWorld() != null && !getWorld().isClient) {
                setActive(false);
                ArrayList<EnergyHandler> outBatteries = new ArrayList<>();
                for (Direction side : Direction.values()) {
                    Block block = getWorld().getBlockState(pos.offset(side)).getBlock();
                    if (block instanceof MagmaBlock || block == Blocks.LAVA || block instanceof FireBlock) {
                        if (getEnergyStorage().getEnergy() < getEnergyStorage().getMaxStored()) {
                            getEnergyStorage().insert(getSpeed());
                            setActive(true);
                        }
                    }
                    BlockEntity out = getWorld().getBlockEntity(pos.offset(side));
                    if (out != null) {
                        if (Energy.valid(out)) {
                            outBatteries.add(Energy.of(out));
                        }
                    }
                }
                if (!outBatteries.isEmpty()) {
                    for (EnergyHandler outBattery : outBatteries) {
                        double energy = getEnergyStorage().getEnergy();
                        if (energy > 0) {
                            double outReceived = outBattery.insert(energy);
                            if (outReceived > 0) {
                                setActive(true);
                                getEnergyStorage().use(outReceived);
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
