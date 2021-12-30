package dunkmania101.spatialharvesters.objects.tile_entities;

import java.util.ArrayList;

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
import team.reborn.energy.api.EnergyStorage;

public class HeatGeneratorTE extends TickingRedstoneEnergyMachineTE {
    public HeatGeneratorTE(BlockPos pos, BlockState state) {
        super(BlockEntityInit.HEAT_GENERATOR, pos, state, true, true);
    }

    @Override
    public void customTickActions() {
        super.customTickActions();
        boolean enabled = CommonConfig.enable_heat_generator;
        if (enabled) {
            if (getWorld() != null && !getWorld().isClient()) {
                setActive(false);
                ArrayList<EnergyStorage> outBatteries = new ArrayList<>();
                for (Direction side : Direction.values()) {
                    Identifier blockRN = Registry.BLOCK.getId(getWorld().getBlockState(getPos().offset(side)).getBlock());
                    if (CommonConfig.valid_heat_sources.contains(Tools.getModResourceArray(blockRN))) {
                        if ((getAmount() + getSpeed()) <= getCapacity()) {
                            insert(getSpeed());
                            setActive(true);
                        }
                    }
                    BlockEntity out = getWorld().getBlockEntity(getPos().offset(side));
                    if (out != null) {
                        if (out instanceof EnergyStorage) {
                            outBatteries.add((EnergyStorage) out);
                        }
                    }
                }
                if (!outBatteries.isEmpty()) {
                    for (EnergyStorage outBattery : outBatteries) {
                        long energy = getAmount();
                        if (energy > 0) {
                            long outReceived = outBattery.insert(energy, null);
                            if (outReceived > 0) {
                                setActive(true);
                                extract(outReceived);
                            }
                        }
                    }
                }
            }
        } else {
            setActive(false);
        }
    }

    public long getSpeed() {
        return CommonConfig.speed_heat_generator;
    }

    @Override
    public long getCapacity() {
        int multiplier = CommonConfig.heat_generator_capacity_multiplier;
        return getSpeed() * multiplier;
    }
}
