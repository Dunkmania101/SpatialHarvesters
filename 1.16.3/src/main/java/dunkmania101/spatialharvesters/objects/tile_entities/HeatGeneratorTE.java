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

    private final int speed = CommonConfig.HEAT_GENERATOR_SPEED.get();

    @Override
    public void tick() {
        setEnergyCapacity(this.speed * 10);
        super.tick();
    }

    @Override
    public void customTickActions() {
        if (world != null && !world.isRemote) {
            boolean active = false;
            ArrayList<IEnergyStorage> out_batteries = new ArrayList<>();
            for (Direction side : Direction.values()) {
                Block block = world.getBlockState(pos.offset(side)).getBlock();
                if (block instanceof MagmaBlock || block == Blocks.LAVA || block instanceof FireBlock) {
                    getEnergyStorage().addEnergy(speed);
                    active = true;
                }
                TileEntity out = world.getTileEntity(pos.offset(side));
                if (out != null) {
                    LazyOptional<IEnergyStorage> out_cap = out.getCapability(CapabilityEnergy.ENERGY, side.getOpposite());
                    out_cap.ifPresent(out_batteries::add);
                }
            }
            Block this_block = getBlockState().getBlock();
            if (this_block instanceof ActiveCustomHorizontalShapedBlock) {
                world.setBlockState(pos, getBlockState().with(CustomProperties.ACTIVE, active));
            }
            for (IEnergyStorage out_battery : out_batteries) {
                int energy = getEnergyStorage().getEnergyStored();
                if (energy > 0) {
                    int out_received = out_battery.receiveEnergy(energy, false);
                    getEnergyStorage().consumeEnergy(out_received);
                }
            }
        }
    }
}
