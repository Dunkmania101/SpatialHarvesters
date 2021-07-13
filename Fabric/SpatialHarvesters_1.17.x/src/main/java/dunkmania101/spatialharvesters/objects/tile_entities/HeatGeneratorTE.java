package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.BlockEntityInit;
import dunkmania101.spatialharvesters.objects.tile_entities.base.TickingRedstoneEnergyMachineTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import team.reborn.energy.Energy;
import team.reborn.energy.EnergyHandler;

import java.util.ArrayList;

public class HeatGeneratorTE extends TickingRedstoneEnergyMachineTE {
    public HeatGeneratorTE(BlockPos pos, BlockState state) {
        super(BlockEntityInit.HEAT_GENERATOR, pos, state, true, true);
    }

    @Override
    public void customTickActions() {
        boolean enabled = CommonConfig.enable_heat_generator;
        if (enabled) {
            super.customTickActions();
            if (getWorld() != null && !getWorld().isClient()) {
                setActive(false);
                ArrayList<EnergyHandler> outBatteries = new ArrayList<>();
                for (Direction side : Direction.values()) {
                    Identifier blockRN = Registry.BLOCK.getId(getWorld().getBlockState(pos.offset(side)).getBlock());
                    if (CommonConfig.valid_heat_sources.contains(Tools.getModResourceArray(blockRN))) {
                        if ((getEnergyStorage().getEnergy() + getSpeed()) <= getEnergyStorage().getMaxStored()) {
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
        return CommonConfig.speed_heat_generator;
    }

    @Override
    public double getMaxStoredPower() {
        int multiplier = CommonConfig.heat_generator_capacity_multiplier;
        return getSpeed() * multiplier;
    }

    @Override
    public double getCustomMaxInput() {
        return getMaxStoredPower();
    }

    @Override
    public double getCustomMaxOutput() {
        return getMaxStoredPower();
    }
}
