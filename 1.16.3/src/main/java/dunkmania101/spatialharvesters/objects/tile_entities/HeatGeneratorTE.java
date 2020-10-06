package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomProperties;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.objects.blocks.ActiveCustomHorizontalShapedBlock;
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

    public static int getSpeed() {
        return CommonConfig.HEAT_GENERATOR_SPEED.get();
    }

    @Override
    public void tick() {
        setEnergyCapacity(getSpeed() * 10);
        super.tick();
    }

    @Override
    public void customTickActions() {
        if (world != null && !world.isRemote) {
            boolean active = false;
            ArrayList<IEnergyStorage> outBatteries = new ArrayList<>();
            for (Direction side : Direction.values()) {
                Block block = world.getBlockState(pos.offset(side)).getBlock();
                if (block instanceof MagmaBlock || block == Blocks.LAVA || block instanceof FireBlock) {
                    getEnergyStorage().addEnergy(getSpeed());
                    active = true;
                }
                TileEntity out = world.getTileEntity(pos.offset(side));
                if (out != null) {
                    LazyOptional<IEnergyStorage> outCap = out.getCapability(CapabilityEnergy.ENERGY, side.getOpposite());
                    outCap.ifPresent(outBatteries::add);
                }
            }
            Block this_block = getBlockState().getBlock();
            if (this_block instanceof ActiveCustomHorizontalShapedBlock) {
                world.setBlockState(pos, getBlockState().with(CustomProperties.ACTIVE, active));
            }
            for (IEnergyStorage outBattery : outBatteries) {
                int energy = getEnergyStorage().getEnergyStored();
                if (energy > 0) {
                    int outReceived = outBattery.receiveEnergy(energy, false);
                    getEnergyStorage().consumeEnergy(outReceived);
                }
            }
        }
    }
}
