package dunkmania101.spatialharvesters.objects.blocks.block_items;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
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
        tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.energy_message", Formatting.DARK_GREEN));
        int energy = data.getInt(CustomValues.energyStorageKey);
        tooltip.add(new LiteralText(Integer.toString(energy)).copy().formatted(Formatting.GREEN, Formatting.BOLD));
        if (data.contains(CustomValues.countedTicksKey)) {
            tooltip.add(Tools.getDividerText());
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.counted_ticks_message", Formatting.YELLOW));
            int ticks = data.getInt(CustomValues.countedTicksKey);
            tooltip.add(new LiteralText(Integer.toString(ticks)).copy().formatted(Formatting.YELLOW, Formatting.BOLD));
        }
        if (data.contains(CustomValues.disabledResourcesKey)) {
            tooltip.add(Tools.getDividerText());
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.disabled_resources", Formatting.RED));
            if (data.contains(CustomValues.disabledResourcesKey)) {
                CompoundTag disabledResources = data.getCompound(CustomValues.disabledResourcesKey);
                for (String key : disabledResources.getKeys()) {
                    Item item = Registry.ITEM.get(Identifier.tryParse(disabledResources.getString(key)));
                    if (item != Items.AIR) {
                        tooltip.add(Tools.getTranslatedFormattedText(item.getTranslationKey(), Formatting.DARK_PURPLE, Formatting.BOLD));
                    }
                }
            }
        }
        if (data.contains(CustomValues.entityNBTKey)) {
            tooltip.add(Tools.getDividerText());
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.mob_key_bound_mob", Formatting.DARK_RED));
            String entity = data.getString(CustomValues.entityNBTKey);
            if (entity != null && !entity.isEmpty()) {
                Optional<EntityType<?>> optionalEntityType = EntityType.get(entity);
                if (optionalEntityType.isPresent()) {
                    EntityType<?> entityType = optionalEntityType.get();
                    tooltip.add(Tools.getTranslatedFormattedText(entityType.getTranslationKey(), Formatting.RED, Formatting.BOLD));
                }
            }
        }
        if (data.contains(CustomValues.weaponNBTKey)) {
            tooltip.add(Tools.getDividerText());
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.weapon_key_bound_weapon", Formatting.DARK_GRAY));
            CompoundTag weaponNBT = data.getCompound(CustomValues.weaponNBTKey);
            if (!weaponNBT.isEmpty()) {
                ItemStack weapon = ItemStack.fromTag(weaponNBT);
                if (!weapon.isEmpty()) {
                    tooltip.add(Tools.getTranslatedFormattedText(weapon.getTranslationKey(), Formatting.GRAY, Formatting.BOLD));
                }
            }
        }
        if (data.contains(CustomValues.potionsNBTKey)) {
            tooltip.add(Tools.getDividerText());
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.dimensional_applicator_saved_effects", Formatting.BLUE));
            for (int id : data.getIntArray(CustomValues.potionsNBTKey)) {
                StatusEffect effect = StatusEffect.byRawId(id);
                if (effect != null) {
                    tooltip.add(Tools.getTranslatedFormattedText(effect.getTranslationKey(), effect.getType().getFormatting(), Formatting.BOLD));
                }
            }
        }
        tooltip.add(Tools.getDividerText());
    }
}
