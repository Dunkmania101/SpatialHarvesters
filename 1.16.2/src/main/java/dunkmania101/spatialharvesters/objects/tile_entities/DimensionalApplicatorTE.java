package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.objects.blocks.SpaceRipperBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.UUID;

public class DimensionalApplicatorTE extends TickingRedstoneEnergyMachineTE {
    public DimensionalApplicatorTE() {
        super(TileEntityInit.DIMENSIONAL_APPLICATOR.get(), true, true, true);
    }

    private UUID player_id = null;
    private ArrayList<Integer> NBTEffects = new ArrayList<>();
    private final int duration = CommonConfig.DIMENSIONAL_APPLICATOR_DURATION.get();

    @Override
    public void customTickActions() {
        super.customTickActions();
        if (this.player_id != null && world != null && !world.isRemote) {
            if (getCountedTicks() >= duration / 2) {
                resetCountedTicks();
                setActive(false);
                boolean has_space_ripper = false;
                for (Direction side : Direction.values()) {
                    if (world.getBlockState(pos.offset(side)).getBlock() instanceof SpaceRipperBlock) {
                        has_space_ripper = true;
                        break;
                    }
                }
                if (has_space_ripper) {
                    PlayerEntity player = null;
                    if (world.getServer() != null) {
                        for (World check_world : world.getServer().getWorlds()) {
                            player = check_world.getPlayerByUuid(this.player_id);
                            if (player != null) {
                                break;
                            }
                        }
                        if (player != null) {
                            ArrayList<EffectInstance> effects = getEffects(world, pos);
                            for (EffectInstance effect : effects) {
                                if (getEnergyStorage().getEnergyStored() >= getPrice()) {
                                    setActive(true);
                                    player.addPotionEffect(effect);
                                    getEnergyStorage().consumeEnergy(getPrice());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private ArrayList<EffectInstance> getEffects(World worldIn, BlockPos pos) {
        ArrayList<EffectInstance> EFFECTS = new ArrayList<>();
        for (int i : NBTEffects) {
            Effect effect = Effect.get(i);
            if (effect != null) {
                EffectInstance effectInstance = new EffectInstance(effect, this.duration, getAmplifier(), true, false);
                EFFECTS.add(effectInstance);
            }
        }
        for (Direction check_direction : Direction.values()) {
            Block block = worldIn.getBlockState(pos.offset(check_direction)).getBlock();
            Effect effect = null;
            if (block == BlockInit.REGENERATION_ACTIVATOR.get()) {
                effect = Effects.REGENERATION;
            } else if (block == BlockInit.RESISTANCE_ACTIVATOR.get()) {
                effect = Effects.RESISTANCE;
            } else if (block == BlockInit.ABSORPTION_ACTIVATOR.get()) {
                effect = Effects.ABSORPTION;
            } else if (block == BlockInit.HASTE_ACTIVATOR.get()) {
                effect = Effects.HASTE;
            } else if (block == BlockInit.SPEED_ACTIVATOR.get()) {
                effect = Effects.SPEED;
            } else if (block == BlockInit.JUMP_BOOST_ACTIVATOR.get()) {
                effect = Effects.JUMP_BOOST;
            } else if (block == BlockInit.INVISIBILITY_ACTIVATOR.get()) {
                effect = Effects.INVISIBILITY;
            } else if (block == BlockInit.NIGHT_VISION_ACTIVATOR.get()) {
                effect = Effects.NIGHT_VISION;
            } else if (block == BlockInit.STRENGTH_ACTIVATOR.get()) {
                effect = Effects.STRENGTH;
            }
            if (effect != null) {
                EFFECTS.add(new EffectInstance(effect, this.duration, getAmplifier(), true, false));
            }
        }
        return EFFECTS;
    }

    protected int getAmplifier() {
        return CommonConfig.DIMENSIONAL_APPLICATOR_AMPLIFIER.get();
    }

    protected int getPrice() {
        return CommonConfig.DIMENSIONAL_APPLICATOR_PRICE.get();
    }

    @Override
    protected int getCapacity() {
        return getPrice() * 10;
    }

    @Override
    protected int getMaxInput() {
        return getCapacity();
    }

    @Override
    protected int getMaxExtract() {
        return getCapacity();
    }

    @Override
    public CompoundNBT saveSerializedValues() {
        CompoundNBT nbt = super.saveSerializedValues();
        if (player_id != null) {
            nbt.putUniqueId(CustomValues.playerNBTKey, player_id);
        }
        if (NBTEffects.size() > 0){
            nbt.putIntArray(CustomValues.potionsNBTKey, NBTEffects);
        }
        return nbt;
    }

    @Override
    public void setDeserializedValues(CompoundNBT nbt) {
        super.setDeserializedValues(nbt);
        if (nbt.contains(CustomValues.removePlayerNBTKey)) {
            this.player_id = null;
        }
        if (nbt.contains(CustomValues.playerNBTKey)) {
            this.player_id = nbt.getUniqueId(CustomValues.playerNBTKey);
        }
        if (nbt.contains(CustomValues.potionsNBTKey)) {
            NBTEffects = new ArrayList<>();
            for (int potion : nbt.getIntArray(CustomValues.potionsNBTKey)) {
                if (!NBTEffects.contains(potion)) {
                    NBTEffects.add(potion);
                }
            }
        }
    }
}
