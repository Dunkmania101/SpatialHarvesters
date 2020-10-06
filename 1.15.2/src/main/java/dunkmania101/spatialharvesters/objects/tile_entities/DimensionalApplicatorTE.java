package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomProperties;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.objects.blocks.ActiveCustomHorizontalShapedBlock;
import dunkmania101.spatialharvesters.objects.blocks.ActiveCustomCustomShapedBlock;
import dunkmania101.spatialharvesters.objects.blocks.SpaceRipperBlock;
import dunkmania101.spatialharvesters.objects.items.EffectKeyItem;
import dunkmania101.spatialharvesters.objects.items.PlayerKeyItem;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class DimensionalApplicatorTE extends TickingRedstoneEnergyMachineTE {
    public DimensionalApplicatorTE() {
        super(TileEntityInit.DIMENSIONAL_APPLICATOR.get(), false, true, true);
    }

    private Integer player_id = null;
    private ArrayList<Integer> NBTEffects = new ArrayList<>();
    private final int duration = 40;
    private final int amplifier = CommonConfig.DIMENSIONAL_APPLICATOR_AMPLIFIER.get();
    private final int price = CommonConfig.DIMENSIONAL_APPLICATOR_PRICE.get();

    @Override
    public void tick() {
        setEnergyCapacity(this.price * 10);
        super.tick();
    }

    @Override
    public void customTickActions() {
        if (world != null && !world.isRemote) {
            if (getCountedTicks() >= duration / 2) {
                resetCountedTicks();
                boolean active = false;
                boolean has_space_ripper = false;
                for (Direction side : Direction.values()) {
                    if (world.getBlockState(pos.offset(side)).getBlock() instanceof SpaceRipperBlock) {
                        has_space_ripper = true;
                        break;
                    }
                }
                if (has_space_ripper && this.player_id != null) {
                    Entity entity = null;
                    if (world.getServer() != null) {
                        for (World check_world : world.getServer().getWorlds()) {
                            entity = check_world.getEntityByID(this.player_id);
                            if (entity != null) {
                                break;
                            }
                        }
                        if (entity != null) {
                            if (entity instanceof PlayerEntity) {
                                PlayerEntity player = (PlayerEntity) entity;
                                ArrayList<EffectInstance> effects = getEffects(world, pos);
                                for (EffectInstance effect : effects) {
                                    active = true;
                                    if (getEnergyStorage().getEnergyStored() >= price) {
                                        player.addPotionEffect(effect);
                                        getEnergyStorage().consumeEnergy(price);
                                    }
                                }
                            }
                        }
                    }
                }
                Block this_block = getBlockState().getBlock();
                if (this_block instanceof ActiveCustomCustomShapedBlock || this_block instanceof ActiveCustomHorizontalShapedBlock) {
                    world.setBlockState(pos, getBlockState().with(CustomProperties.ACTIVE, active));
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

        if (player_id != null) {
            nbt.putInt(PlayerKeyItem.playerNBTKey, player_id);
        }

        if (NBTEffects.size() > 0){
            nbt.putIntArray(EffectKeyItem.potionsNBTKey, NBTEffects);
        }

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        super.deserializeNBT(nbt);

        if (nbt.contains(PlayerKeyItem.removePlayerNBTKey)) {
            this.player_id = null;
        }
        if (nbt.contains(PlayerKeyItem.playerNBTKey)) {
            this.player_id = nbt.getInt(PlayerKeyItem.playerNBTKey);
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
