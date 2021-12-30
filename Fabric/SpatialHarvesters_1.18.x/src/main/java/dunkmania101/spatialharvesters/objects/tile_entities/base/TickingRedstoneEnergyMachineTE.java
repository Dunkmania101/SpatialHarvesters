package dunkmania101.spatialharvesters.objects.tile_entities.base;

import dunkmania101.spatialharvesters.data.CustomProperties;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.blocks.base.ActivePreservedDataCustomHorizontalShapedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TickingRedstoneEnergyMachineTE extends CustomEnergyMachineTE {
    private final boolean countTicks;
    protected boolean active = false;
    protected int ticks = 0;

    public TickingRedstoneEnergyMachineTE(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, boolean canExtract, boolean canReceive, boolean countTicks) {
        super(tileEntityTypeIn, pos, state, canExtract, canReceive);

        this.countTicks = countTicks;
    }

    public TickingRedstoneEnergyMachineTE(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, boolean canExtract, boolean canReceive) {
        this(tileEntityTypeIn, pos, state, canExtract, canReceive, false);
    }

    public static void tick(World world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (blockEntity instanceof TickingRedstoneEnergyMachineTE) {
            ((TickingRedstoneEnergyMachineTE) blockEntity).internalTick(world, pos, state, blockEntity);
        }
    }

    public void internalTick(World world, BlockPos pos, BlockState state, BlockEntity ignored) {
        if (world != null && !world.isClient()) {
            if (world.isReceivingRedstonePower(pos)) {
                setActive(false);
                world.addParticle(DustParticleEffect.DEFAULT, getPos().getX(), getPos().getY(), getPos().getZ(), 5, 5, 5);
            } else {
                customTickActions();
                if (this.countTicks) {
                    this.ticks++;
                }
            }
            if (state.getBlock() instanceof ActivePreservedDataCustomHorizontalShapedBlock) {
                if (state.get(CustomProperties.ACTIVE) != getActive()) {
                    world.setBlockState(getPos(), state.with(CustomProperties.ACTIVE, getActive()));
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
    public NbtCompound saveSerializedValues() {
        NbtCompound nbt = super.saveSerializedValues();
        if (countTicks) {
            nbt.putInt(CustomValues.countedTicksKey, getCountedTicks());
        }
        return nbt;
    }

    @Override
    public void setDeserializedValues(NbtCompound nbt) {
        super.setDeserializedValues(nbt);
        if (nbt.contains(CustomValues.countedTicksKey)) {
            this.ticks = nbt.getInt(CustomValues.countedTicksKey);
        }
    }
}
