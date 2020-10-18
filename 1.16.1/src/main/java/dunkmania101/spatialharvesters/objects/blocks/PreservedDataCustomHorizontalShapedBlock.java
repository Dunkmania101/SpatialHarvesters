package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.CustomEnergyMachineTE;
import dunkmania101.spatialharvesters.objects.tile_entities.MobHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.TickingRedstoneEnergyMachineTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public class PreservedDataCustomHorizontalShapedBlock extends CustomHorizontalShapedBlock {
    private CompoundNBT thisTileNBT = new CompoundNBT();

    public PreservedDataCustomHorizontalShapedBlock(Properties properties, VoxelShape shape, Direction frontDirection) {
        super(properties, shape, frontDirection);
    }

    @Override
    public void onBlockHarvested(World worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull PlayerEntity player) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile != null) {
            this.thisTileNBT = tile.serializeNBT();
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Nonnull
    @Override
    public List<ItemStack> getDrops(@Nonnull BlockState state, @Nonnull LootContext.Builder builder) {
        List<ItemStack> drops = super.getDrops(state, builder);
        if (this.thisTileNBT != null) {
            if (!this.thisTileNBT.isEmpty()) {
                return Tools.getPreservedDataBlockDrops(drops, state, this.thisTileNBT);
            }
        }
        return drops;
    }

    @Override
    public void onBlockPlacedBy(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, LivingEntity placer, @Nonnull ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isRemote) {
            TileEntity tile = worldIn.getTileEntity(pos);
            CompoundNBT stackTileNBT = stack.getChildTag(CustomValues.stackTileNBTKey);
            if (tile != null && stackTileNBT != null) {
                tile.deserializeNBT(Tools.correctTileNBT(tile, stackTileNBT));
            }
        }
    }

    @Override
    public void onBlockClicked(@Nonnull BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player) {
        if (!worldIn.isRemote) {
            if (player.isCrouching()) {
                TileEntity tile = worldIn.getTileEntity(pos);
                if (tile != null) {
                    CompoundNBT data = tile.serializeNBT();
                    if (tile instanceof CustomEnergyMachineTE) {
                        player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.divider"), false);
                        player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.energy_message"), false);
                        player.sendStatusMessage(new StringTextComponent(Integer.toString(data.getInt(CustomValues.energyStorageKey))), false);
                        player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.divider"), false);
                    }
                    if (tile instanceof TickingRedstoneEnergyMachineTE) {
                        int countedTicks = data.getInt(CustomValues.countedTicksKey);
                        if (countedTicks > 0) {
                            player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.divider"), false);
                            player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.counted_ticks_message"), false);
                            player.sendStatusMessage(new StringTextComponent(Integer.toString(countedTicks)), false);
                            player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.divider"), false);
                        }
                    }
                    if (tile instanceof MobHarvesterTE) {
                        player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.divider"), false);
                        player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.mob_key_bound_mob"), false);
                        String mob = data.getString(CustomValues.entityNBTKey);
                        if (!StringUtils.isNullOrEmpty(mob)) {
                            Optional<EntityType<?>> optionalEntityType = EntityType.byKey(mob);
                            if (optionalEntityType.isPresent()) {
                                EntityType<?> entityType = optionalEntityType.get();
                                player.sendStatusMessage(entityType.getName(), false);
                            }
                        }
                        player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.mob_key_bound_weapon"), false);
                        CompoundNBT weapon = data.getCompound(CustomValues.weaponNBTKey);
                        if (!weapon.isEmpty()) {
                            ItemStack weaponStack = ItemStack.read(weapon);
                            if (!weaponStack.isEmpty()) {
                                player.sendStatusMessage(new TranslationTextComponent(weaponStack.getTranslationKey()), false);
                            }
                        }
                        player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.divider"), false);
                    }
                }
            }
        }
        super.onBlockClicked(state, worldIn, pos, player);
    }
}
