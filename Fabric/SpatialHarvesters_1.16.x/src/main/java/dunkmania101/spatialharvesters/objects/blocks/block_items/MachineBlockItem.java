package dunkmania101.spatialharvesters.objects.blocks.block_items;

import dunkmania101.spatialharvesters.data.CustomValues;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class MachineBlockItem extends BlockItem {
    public MachineBlockItem(Block blockIn, Settings settings) {
        super(blockIn, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        CompoundTag data = stack.getOrCreateTag().getCompound(CustomValues.stackTileNBTKey);
        tooltip.add(new TranslatableText("msg.spatialharvesters.energy_message"));
        int energy = data.getInt(CustomValues.energyStorageKey);
        tooltip.add(Text.of(Integer.toString(energy)));
        if (data.contains(CustomValues.countedTicksKey)) {
            tooltip.add(new TranslatableText("msg.spatialharvesters.divider"));
            tooltip.add(new TranslatableText("msg.spatialharvesters.counted_ticks_message"));
            int ticks = data.getInt(CustomValues.countedTicksKey);
            tooltip.add(Text.of(Integer.toString(ticks)));
        }
        if (data.contains(CustomValues.disabledResourcesKey)) {
            tooltip.add(new TranslatableText("msg.spatialharvesters.divider"));
            tooltip.add(new TranslatableText("msg.spatialharvesters.disabled_resources"));
            if (data.contains(CustomValues.disabledResourcesKey)) {
                CompoundTag disabledResources = data.getCompound(CustomValues.disabledResourcesKey);
                for (String key : disabledResources.getKeys()) {
                    Item item = Registry.ITEM.get(Identifier.tryParse(disabledResources.getString(key)));
                    if (item != Items.AIR) {
                        tooltip.add(item.getName());
                    }
                }
            }
        }
        if (data.contains(CustomValues.entityNBTKey)) {
            tooltip.add(new TranslatableText("msg.spatialharvesters.divider"));
            tooltip.add(new TranslatableText("msg.spatialharvesters.mob_key_bound_mob"));
            String entity = data.getString(CustomValues.entityNBTKey);
            if (entity != null && !entity.isEmpty()) {
                Optional<EntityType<?>> optionalEntityType = EntityType.get(entity);
                if (optionalEntityType.isPresent()) {
                    EntityType<?> entityType = optionalEntityType.get();
                    tooltip.add(entityType.getName());
                }
            }
        }
        if (data.contains(CustomValues.weaponNBTKey)) {
            tooltip.add(new TranslatableText("msg.spatialharvesters.divider"));
            tooltip.add(new TranslatableText("msg.spatialharvesters.weapon_key_bound_weapon"));
            CompoundTag weaponNBT = data.getCompound(CustomValues.weaponNBTKey);
            if (!weaponNBT.isEmpty()) {
                ItemStack weapon = ItemStack.fromTag(weaponNBT);
                if (!weapon.isEmpty()) {
                    tooltip.add(weapon.getName());
                }
            }
        }
        if (data.contains(CustomValues.potionsNBTKey)) {
            tooltip.add(new TranslatableText("msg.spatialharvesters.divider"));
            tooltip.add(new TranslatableText("msg.spatialharvesters.dimensional_applicator_saved_effects"));
            for (int id : data.getIntArray(CustomValues.potionsNBTKey)) {
                StatusEffect effect = StatusEffect.byRawId(id);
                if (effect != null) {
                    tooltip.add(effect.getName());
                }
            }
        }
        tooltip.add(new TranslatableText("msg.spatialharvesters.divider"));
    }
}
