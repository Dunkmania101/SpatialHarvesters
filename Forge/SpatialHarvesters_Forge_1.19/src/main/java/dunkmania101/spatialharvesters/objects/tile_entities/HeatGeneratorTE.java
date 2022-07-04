package dunkmania101.spatialharvesters.objects.tile_entities;

import java.util.ArrayList;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.objects.tile_entities.base.TickingRedstoneEnergyMachineTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.registries.ForgeRegistries;

public class HeatGeneratorTE extends TickingRedstoneEnergyMachineTE {
    public HeatGeneratorTE(BlockPos pos, BlockState state) {
        super(TileEntityInit.HEAT_GENERATOR.get(), pos, state, true, true);
    }

    @Override
    public void customTickActions() {
        boolean enabled = CommonConfig.ENABLE_HEAT_GENERATOR.get();
        if (enabled) {
            super.customTickActions();
            if (getLevel() != null && !getLevel().isClientSide()) {
                setActive(false);
                ArrayList<IEnergyStorage> outBatteries = new ArrayList<>();
                for (Direction side : Direction.values()) {
                    ResourceLocation blockRN = ForgeRegistries.BLOCKS.getKey(getLevel().getBlockState(getBlockPos().relative(side)).getBlock());
                    if (blockRN != null && CommonConfig.VALID_HEAT_SOURCES.get().contains(Tools.getModResourceArray(blockRN))) {
                        if ((getEnergyStorage().getEnergyStored() + getSpeed()) <= getEnergyStorage().getMaxEnergyStored()) {
                            getEnergyStorage().addEnergy(getSpeed());
                            setActive(true);
                        }
                    }
                    BlockEntity out = getLevel().getBlockEntity(getBlockPos().relative(side));
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
        int speed = getSpeed();
        int multiplier = CommonConfig.HEAT_GENERATOR_CAPACITY_MULTIPLIER.get();
        if (Integer.MAX_VALUE / multiplier < speed) {
            return Integer.MAX_VALUE;
        }
        return speed * multiplier;
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
