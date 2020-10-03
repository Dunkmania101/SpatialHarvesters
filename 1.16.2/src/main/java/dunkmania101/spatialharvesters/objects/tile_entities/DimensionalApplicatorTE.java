package dunkmania101.spatialharvesters.objects.tile_entities;

import com.mojang.util.UUIDTypeAdapter;
import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.objects.blocks.SpaceRipperBlock;
import dunkmania101.spatialharvesters.objects.items.EffectKeyItem;
import dunkmania101.spatialharvesters.objects.items.PlayerKeyItem;
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
        super(TileEntityInit.DIMENSIONAL_APPLICATOR.get(), false, true, true);
    }

    private String entity = null;
    private ArrayList<Integer> NBTEffects = new ArrayList<>();
    private final int duration = 25;
    private final int amplifier = CommonConfig.DIMENSIONAL_APPLICATOR_AMPLIFIER.get();
    private final int price = CommonConfig.DIMENSIONAL_APPLICATOR_PRICE.get();
    @Override
    public void customTickActions() {
        if (world != null && !world.isRemote) {
            int set_capacity = price * 10;
            if (getEnergyStorage().getMaxEnergyStored() != set_capacity) {
                getEnergyStorage().setMaxEnergy(set_capacity);
            }
            if (getEnergyStorage().getMaxInput() != set_capacity) {
                getEnergyStorage().setMaxInput(set_capacity);
            }
            if (getEnergyStorage().getMaxExtract() != set_capacity) {
                getEnergyStorage().setMaxExtract(set_capacity);
            }
            if (getCountedTicks() >= duration - 1) {
                resetCountedTicks();
                boolean has_space_ripper = false;
                for (Direction side : Direction.values()) {
                    if (world.getBlockState(pos.offset(side)).getBlock() instanceof SpaceRipperBlock) {
                        has_space_ripper = true;
                        break;
                    }
                }
                if (has_space_ripper) {
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
                                ArrayList<EffectInstance> effects = getEffects(world, pos);
                                for (EffectInstance effect : effects) {
                                    if (getEnergyStorage().getEnergyStored() >= price) {
                                        player.addPotionEffect(effect);
                                        getEnergyStorage().consumeEnergy(price);
                                    }
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
                EffectInstance effectInstance = new EffectInstance(effect, this.duration, this.amplifier);
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
                EFFECTS.add(new EffectInstance(effect, this.duration, this.amplifier));
            }
        }
        return EFFECTS;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = super.serializeNBT();

        if (entity != null) {
            nbt.putString(PlayerKeyItem.playerNBTKey, entity);
        }

        if (NBTEffects.size() > 0){
            nbt.putIntArray(EffectKeyItem.potionsNBTKey, NBTEffects);
        }

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        super.deserializeNBT(nbt);

        if (nbt.contains(PlayerKeyItem.playerNBTKey)) {
            this.entity = nbt.getString(PlayerKeyItem.playerNBTKey);
            if (this.entity.isEmpty()) {
                this.entity = null;
            }
        }

        if (nbt.contains(EffectKeyItem.potionsNBTKey)) {
            NBTEffects = new ArrayList<>();
            for (int potion : nbt.getIntArray(EffectKeyItem.potionsNBTKey)) {
                if (!NBTEffects.contains(potion)) {
                    NBTEffects.add(potion);
                }
            }
        }

        markDirty();
    }
}
