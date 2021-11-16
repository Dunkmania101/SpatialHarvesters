package dunkmania101.spatialharvesters.objects.blocks.block_items;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class MachineBlockItem extends BlockItem {
    public MachineBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, Level worldIn, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        CompoundTag data = stack.getOrCreateTag().getCompound(CustomValues.stackTileNBTKey);
        tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.energy_message", ChatFormatting.DARK_GREEN));
        int energy = data.getInt(CustomValues.energyStorageKey);
        tooltip.add(new TextComponent(Integer.toString(energy)).withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD));
        if (data.contains(CustomValues.countedTicksKey)) {
            tooltip.add(Tools.getDividerText());
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.counted_ticks_message", ChatFormatting.YELLOW));
            int ticks = data.getInt(CustomValues.countedTicksKey);
            tooltip.add(new TextComponent(Integer.toString(ticks)).withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD));
        }
        if (data.contains(CustomValues.disabledResourcesKey)) {
            tooltip.add(Tools.getDividerText());
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.disabled_resources", ChatFormatting.RED));
            if (data.contains(CustomValues.disabledResourcesKey)) {
                CompoundTag disabledResources = data.getCompound(CustomValues.disabledResourcesKey);
                for (String key : disabledResources.getAllKeys()) {
                    Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(disabledResources.getString(key)));
                    if (item != null && item != Items.AIR) {
                        tooltip.add(Tools.getTranslatedFormattedText(item.getDescriptionId(), ChatFormatting.DARK_PURPLE, ChatFormatting.BOLD));
                    }
                }
            }
        }
        if (data.contains(CustomValues.entityNBTKey)) {
            tooltip.add(Tools.getDividerText());
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.mob_key_bound_mob", ChatFormatting.DARK_RED));
            String entity = data.getString(CustomValues.entityNBTKey);
            if (entity != null && !entity.isEmpty()) {
                Optional<EntityType<?>> optionalEntityType = EntityType.byString(entity);
                if (optionalEntityType.isPresent()) {
                    EntityType<?> entityType = optionalEntityType.get();
                    tooltip.add(Tools.getTranslatedFormattedText(entityType.getDescriptionId(), ChatFormatting.RED, ChatFormatting.BOLD));
                }
            }
        }
        if (data.contains(CustomValues.weaponNBTKey)) {
            tooltip.add(Tools.getDividerText());
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.weapon_key_bound_weapon", ChatFormatting.DARK_GRAY));
            CompoundTag weaponNBT = data.getCompound(CustomValues.weaponNBTKey);
            if (!weaponNBT.isEmpty()) {
                ItemStack weapon = ItemStack.of(weaponNBT);
                if (!weapon.isEmpty()) {
                    tooltip.add(Tools.getTranslatedFormattedText(weapon.getDescriptionId(), ChatFormatting.GRAY, ChatFormatting.BOLD));
                }
            }
        }
        if (data.contains(CustomValues.potionsNBTKey)) {
            tooltip.add(Tools.getDividerText());
            tooltip.add(Tools.getTranslatedFormattedText("msg.spatialharvesters.dimensional_applicator_saved_effects", ChatFormatting.BLUE));
            for (int id : data.getIntArray(CustomValues.potionsNBTKey)) {
                MobEffect effect = MobEffect.byId(id);
                if (effect != null) {
                    tooltip.add(Tools.getTranslatedFormattedText(effect.getDescriptionId(), ChatFormatting.BOLD).copy().withStyle(Style.EMPTY.withColor(effect.getColor())));
                }
            }
        }
        tooltip.add(Tools.getDividerText());
    }
}
