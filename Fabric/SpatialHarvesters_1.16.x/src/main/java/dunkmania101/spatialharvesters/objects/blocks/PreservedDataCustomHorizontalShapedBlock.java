package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.*;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public class PreservedDataCustomHorizontalShapedBlock extends CustomHorizontalShapedBlock {
    private CompoundTag thisTileNBT = new CompoundTag();

    public PreservedDataCustomHorizontalShapedBlock(Settings settings, VoxelShape shape, Direction frontDirection) {
        super(settings, shape, frontDirection);
    }

    @Override
    public void onBreak(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity tile = worldIn.getBlockEntity(pos);
        if (tile != null) {
            this.thisTileNBT = tile.toTag(new CompoundTag());
        }
        super.onBreak(worldIn, pos, state, player);
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> drops = super.getDroppedStacks(state, builder);
        if (this.thisTileNBT != null) {
            if (!this.thisTileNBT.isEmpty()) {
                return Tools.getPreservedDataBlockDrops(drops, state, this.thisTileNBT);
            }
        }
        return drops;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onPlaced(world, pos, state, placer, stack);
        if (!world.isClient) {
            BlockEntity tile = world.getBlockEntity(pos);
            CompoundTag stackTileNBT = stack.getSubTag(CustomValues.stackTileNBTKey);
            if (tile != null && stackTileNBT != null) {
                if (!stackTileNBT.isEmpty()) {
                    tile.fromTag(state, Tools.correctTileNBT(tile, stackTileNBT));
                }
            }
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            if (player.isSneaking()) {
                BlockEntity tile = world.getBlockEntity(pos);
                if (tile != null) {
                    player.sendMessage(new LiteralText("msg.spatialharvesters.divider"), false);
                    CompoundTag data = tile.toTag(new CompoundTag());
                    if (tile instanceof CustomEnergyMachineTE) {
                        player.sendMessage(new TranslatableText("msg.spatialharvesters.energy_message"), false);
                        player.sendMessage(new LiteralText(Integer.toString(data.getInt(CustomValues.energyStorageKey))) {
                        }, false);
                    }
                    if (tile instanceof TickingRedstoneEnergyMachineTE) {
                        if (data.contains(CustomValues.countedTicksKey)) {
                            player.sendMessage(new TranslatableText("msg.spatialharvesters.divider"), false);
                            player.sendMessage(new TranslatableText("msg.spatialharvesters.counted_ticks_message"), false);
                            int countedTicks = data.getInt(CustomValues.countedTicksKey);
                            player.sendMessage(new LiteralText(Integer.toString(countedTicks)), false);
                        }
                    }
                    if (tile instanceof SpatialHarvesterTE) {
                        player.sendMessage(new TranslatableText("msg.spatialharvesters.divider"), false);
                        player.sendMessage(new TranslatableText("msg.spatialharvesters.disabled_resources"), false);
                        if (data.contains(CustomValues.disabledResourcesKey)) {
                            CompoundTag disabledResources = data.getCompound(CustomValues.disabledResourcesKey);
                            for (String key : disabledResources.getKeys()) {
                                Item item = Registry.ITEM.get(new Identifier(disabledResources.getString(key)));
                                if (item != null && item != Items.AIR) {
                                    player.sendMessage(item.getName(), false);
                                }
                            }
                        }
                    }
                    if (tile instanceof MobHarvesterTE) {
                        player.sendMessage(new TranslatableText("msg.spatialharvesters.divider"), false);
                        player.sendMessage(new TranslatableText("msg.spatialharvesters.mob_key_bound_mob"), false);
                        String mob = data.getString(CustomValues.entityNBTKey);
                        if (mob != null && !mob.isEmpty()) {
                            Optional<EntityType<?>> optionalEntityType = EntityType.get(mob);
                            if (optionalEntityType.isPresent()) {
                                EntityType<?> entityType = optionalEntityType.get();
                                player.sendMessage(entityType.getName(), false);
                            }
                        }
                        player.sendMessage(new TranslatableText("msg.spatialharvesters.weapon_key_bound_weapon"), false);
                        CompoundTag weapon = data.getCompound(CustomValues.weaponNBTKey);
                        if (!weapon.isEmpty()) {
                            ItemStack weaponStack = ItemStack.fromTag(weapon);
                            if (!weaponStack.isEmpty()) {
                                player.sendMessage(new TranslatableText(weaponStack.getTranslationKey()), false);
                            }
                        }
                    } else if (tile instanceof DimensionalApplicatorTE) {
                        player.sendMessage(new TranslatableText("msg.spatialharvesters.divider"), false);
                        player.sendMessage(new TranslatableText("msg.spatialharvesters.dimensional_applicator_saved_effects"), false);
                        if (data.contains(CustomValues.potionsNBTKey)) {
                            for (int id : data.getIntArray(CustomValues.potionsNBTKey)) {
                                StatusEffect effect = StatusEffect.byRawId(id);
                                if (effect != null) {
                                    player.sendMessage(effect.getName(), false);
                                }
                            }
                        }
                    }
                    player.sendMessage(new TranslatableText("msg.spatialharvesters.divider"), false);
                }
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
