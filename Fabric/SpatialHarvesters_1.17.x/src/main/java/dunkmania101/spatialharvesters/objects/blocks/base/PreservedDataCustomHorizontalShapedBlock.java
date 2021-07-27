package dunkmania101.spatialharvesters.objects.blocks.base;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.*;
import dunkmania101.spatialharvesters.objects.tile_entities.base.CustomEnergyMachineTE;
import dunkmania101.spatialharvesters.objects.tile_entities.base.SpatialHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.base.TickingRedstoneEnergyMachineTE;
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
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
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
    private NbtCompound thisTileNBT = new NbtCompound();

    public PreservedDataCustomHorizontalShapedBlock(Settings settings, VoxelShape shape, Direction frontDirection) {
        super(settings, shape, frontDirection);
    }

    @Override
    public void onBreak(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity tile = worldIn.getBlockEntity(pos);
        if (tile != null) {
            this.thisTileNBT = tile.writeNbt(new NbtCompound());
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
        if (!world.isClient()) {
            BlockEntity tile = world.getBlockEntity(pos);
            NbtCompound stackTileNBT = stack.getSubNbt(CustomValues.stackTileNBTKey);
            if (tile != null && stackTileNBT != null) {
                if (!stackTileNBT.isEmpty()) {
                    tile.readNbt(Tools.correctTileNBT(tile, stackTileNBT));
                }
            }
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            if (player.isSneaking()) {
                BlockEntity tile = world.getBlockEntity(pos);
                if (tile != null) {
                    player.sendMessage(Tools.getDividerText(), false);
                    NbtCompound data = tile.writeNbt(new NbtCompound());
                    if (tile instanceof CustomEnergyMachineTE) {
                        player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.energy_message", Formatting.DARK_GREEN), false);
                        player.sendMessage(new LiteralText(Integer.toString(data.getInt(CustomValues.energyStorageKey))).copy().formatted(Formatting.GREEN, Formatting.BOLD), false);
                    }
                    if (tile instanceof TickingRedstoneEnergyMachineTE) {
                        if (data.contains(CustomValues.countedTicksKey)) {
                            player.sendMessage(Tools.getDividerText(), false);
                            player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.counted_ticks_message", Formatting.YELLOW), false);
                            int countedTicks = data.getInt(CustomValues.countedTicksKey);
                            player.sendMessage(new LiteralText(Integer.toString(countedTicks)).copy().formatted(Formatting.YELLOW, Formatting.BOLD), false);
                        }
                    }
                    if (tile instanceof SpatialHarvesterTE) {
                        player.sendMessage(Tools.getDividerText(), false);
                        player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.disabled_resources", Formatting.RED), false);
                        if (data.contains(CustomValues.disabledResourcesKey)) {
                            NbtCompound disabledResources = data.getCompound(CustomValues.disabledResourcesKey);
                            for (String key : disabledResources.getKeys()) {
                                Item item = Registry.ITEM.get(Identifier.tryParse(disabledResources.getString(key)));
                                if (item != Items.AIR) {
                                    player.sendMessage(Tools.getTranslatedFormattedText(item.getTranslationKey(), Formatting.DARK_PURPLE, Formatting.BOLD), false);
                                }
                            }
                        }
                    }
                    if (tile instanceof MobHarvesterTE) {
                        player.sendMessage(Tools.getDividerText(), false);
                        player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.mob_key_bound_mob", Formatting.DARK_RED), false);
                        String mob = data.getString(CustomValues.entityNBTKey);
                        if (mob != null && !mob.isEmpty()) {
                            Optional<EntityType<?>> optionalEntityType = EntityType.get(mob);
                            if (optionalEntityType.isPresent()) {
                                EntityType<?> entityType = optionalEntityType.get();
                                player.sendMessage(Tools.getTranslatedFormattedText(entityType.getTranslationKey(), Formatting.RED, Formatting.BOLD), false);
                            }
                        }
                        player.sendMessage(Tools.getDividerText(), false);
                        player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.weapon_key_bound_weapon", Formatting.DARK_GRAY), false);
                        NbtCompound weapon = data.getCompound(CustomValues.weaponNBTKey);
                        if (!weapon.isEmpty()) {
                            ItemStack weaponStack = ItemStack.fromNbt(weapon);
                            if (!weaponStack.isEmpty()) {
                                player.sendMessage(Tools.getTranslatedFormattedText(weaponStack.getTranslationKey(), Formatting.GRAY, Formatting.BOLD), false);
                            }
                        }
                    } else if (tile instanceof DimensionalApplicatorTE) {
                        player.sendMessage(Tools.getDividerText(), false);
                        player.sendMessage(Tools.getTranslatedFormattedText("msg.spatialharvesters.dimensional_applicator_saved_effects", Formatting.BLUE), false);
                        if (data.contains(CustomValues.potionsNBTKey)) {
                            for (int id : data.getIntArray(CustomValues.potionsNBTKey)) {
                                StatusEffect effect = StatusEffect.byRawId(id);
                                if (effect != null) {
                                    player.sendMessage(Tools.getTranslatedFormattedText(effect.getTranslationKey(), effect.getType().getFormatting(), Formatting.BOLD), false);
                                }
                            }
                        }
                    }
                    player.sendMessage(Tools.getDividerText(), false);
                }
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
