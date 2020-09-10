package dunkmania101.spatialharvesters.objects.tile_entities;

import com.mojang.util.UUIDTypeAdapter;
import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomEnergyStorage;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.UUID;

public class DimensionalApplicatorTE extends TileEntity implements ITickableTileEntity {
    public DimensionalApplicatorTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public DimensionalApplicatorTE() {
        this(TileEntityInit.DIMENSIONAL_APPLICATOR.get());
    }

    private final CustomEnergyStorage energyStorage = createEnergy();

    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    private CustomEnergyStorage createEnergy() {
        int capacity = CommonConfig.DIMENSIONAL_APPLICATOR_PRICE.get() * 10;
        return new CustomEnergyStorage(capacity, capacity) {
            @Override
            protected void onEnergyChanged() {
                markDirty();
            }

            @Override
            public boolean canExtract() {
                return false;
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

    private static final String key = SpatialHarvesters.modid + "_DimensionalApplicatorEntity";
    private static String entity = null;
    private static int ticks = 0;
    @Override
    public void tick() {
        if (ticks >= 100) {
            ticks = 0;
            if (world != null && !world.isRemote) {
                if (world.getBlockState(pos.offset(Direction.UP)).getBlock() == BlockInit.SPACE_RIPPER.get()) {
                    if (entity != null && !entity.isEmpty()) {
                        UUID uuid = UUIDTypeAdapter.fromString(entity);
                        PlayerEntity player = null;
                        if (world.getServer() != null) {
                            for (World check_world : world.getServer().getWorlds()) {
                                player = check_world.getPlayerByUuid(uuid);
                                if (player != null) {
                                    break;
                                }
                            }
                            if (player != null) {
                                ArrayList<EffectInstance> EFFECTS = getEffects(world, pos);
                                for (EffectInstance effect : EFFECTS) {
                                    int price = CommonConfig.DIMENSIONAL_APPLICATOR_PRICE.get();
                                    if (energyStorage.getEnergyStored() >= price) {
                                        player.addPotionEffect(effect);
                                        energyStorage.consumeEnergy(price);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            ticks++;
        }
    }

    private ArrayList<EffectInstance> getEffects(World worldIn, BlockPos pos) {
        ArrayList<EffectInstance> EFFECTS = new ArrayList<>();
        int amplifier = CommonConfig.DIMENSIONAL_APPLICATOR_AMPLIFIER.get();
        int duration = 500;
        for (Direction check_direction : Direction.values()) {
            Block block = worldIn.getBlockState(pos.offset(check_direction)).getBlock();
            if (block == BlockInit.REGENERATION_ACTIVATOR.get()) {
                EffectInstance effect = new EffectInstance(Effects.REGENERATION, duration, amplifier);
                EFFECTS.add(effect);
            } else if (block == BlockInit.RESISTANCE_ACTIVATOR.get()) {
                EffectInstance effect = new EffectInstance(Effects.RESISTANCE, duration, amplifier);
                EFFECTS.add(effect);
            } else if (block == BlockInit.ABSORPTION_ACTIVATOR.get()) {
                EffectInstance effect = new EffectInstance(Effects.ABSORPTION, duration, amplifier);
                EFFECTS.add(effect);
            } else if (block == BlockInit.HASTE_ACTIVATOR.get()) {
                EffectInstance effect = new EffectInstance(Effects.HASTE, duration, amplifier);
                EFFECTS.add(effect);
            } else if (block == BlockInit.SPEED_ACTIVATOR.get()) {
                EffectInstance effect = new EffectInstance(Effects.SPEED, duration, amplifier);
                EFFECTS.add(effect);
            } else if (block == BlockInit.JUMP_BOOST_ACTIVATOR.get()) {
                EffectInstance effect = new EffectInstance(Effects.JUMP_BOOST, duration, amplifier);
                EFFECTS.add(effect);
            } else if (block == BlockInit.INVISIBILITY_ACTIVATOR.get()) {
                EffectInstance effect = new EffectInstance(Effects.INVISIBILITY, duration, amplifier);
                EFFECTS.add(effect);
            } else if (block == BlockInit.NIGHT_VISION_ACTIVATOR.get()) {
                EffectInstance effect = new EffectInstance(Effects.NIGHT_VISION, duration, amplifier);
                EFFECTS.add(effect);
            } else if (block == BlockInit.STRENGTH_ACTIVATOR.get()) {
                EffectInstance effect = new EffectInstance(Effects.STRENGTH, duration, amplifier);
                EFFECTS.add(effect);
            }
        }
        return EFFECTS;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString(key, entity);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        entity = nbt.getString(key);
        if (entity.isEmpty()) {
            entity = null;
        }
        markDirty();
    }
}
