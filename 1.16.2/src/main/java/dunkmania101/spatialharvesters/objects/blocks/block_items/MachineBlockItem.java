package dunkmania101.spatialharvesters.objects.blocks.block_items;

import dunkmania101.spatialharvesters.data.CustomValues;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public class MachineBlockItem extends BlockItem {
    public MachineBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        CompoundNBT data = stack.getOrCreateTag().getCompound(CustomValues.stackTileNBTKey);
        tooltip.add(new TranslationTextComponent("msg.spatialharvesters.energy_message"));
        int energy = data.getInt(CustomValues.energyStorageKey);
        tooltip.add(new StringTextComponent(Integer.toString(energy)));
        if (data.contains(CustomValues.countedTicksKey)) {
            tooltip.add(new TranslationTextComponent("msg.spatialharvesters.divider"));
            tooltip.add(new TranslationTextComponent("msg.spatialharvesters.counted_ticks_message"));
            int ticks = data.getInt(CustomValues.countedTicksKey);
            tooltip.add(new StringTextComponent(Integer.toString(ticks)));
        }
        if (data.contains(CustomValues.disabledResourcesKey)) {
            tooltip.add(new TranslationTextComponent("msg.spatialharvesters.divider"));
            tooltip.add(new TranslationTextComponent("msg.spatialharvesters.disabled_resources"));
            if (data.contains(CustomValues.disabledResourcesKey)) {
                CompoundNBT disabledResources = data.getCompound(CustomValues.disabledResourcesKey);
                for (String key : disabledResources.keySet()) {
                    Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(disabledResources.getString(key)));
                    if (item != null && item != Items.AIR) {
                        tooltip.add(item.getName());
                    }
                }
            }
        }
        if (data.contains(CustomValues.entityNBTKey)) {
            tooltip.add(new TranslationTextComponent("msg.spatialharvesters.divider"));
            tooltip.add(new TranslationTextComponent("msg.spatialharvesters.mob_key_bound_mob"));
            String entity = data.getString(CustomValues.entityNBTKey);
            if (!StringUtils.isNullOrEmpty(entity)) {
                Optional<EntityType<?>> optionalEntityType = EntityType.byKey(entity);
                if (optionalEntityType.isPresent()) {
                    EntityType<?> entityType = optionalEntityType.get();
                    tooltip.add(entityType.getName());
                }
            }
        }
        if (data.contains(CustomValues.weaponNBTKey)) {
            tooltip.add(new TranslationTextComponent("msg.spatialharvesters.divider"));
            tooltip.add(new TranslationTextComponent("msg.spatialharvesters.weapon_key_bound_weapon"));
            CompoundNBT weaponNBT = data.getCompound(CustomValues.weaponNBTKey);
            if (!weaponNBT.isEmpty()) {
                ItemStack weapon = ItemStack.read(weaponNBT);
                if (!weapon.isEmpty()) {
                    tooltip.add(weapon.getDisplayName());
                }
            }
        }
        if (data.contains(CustomValues.potionsNBTKey)) {
            tooltip.add(new TranslationTextComponent("msg.spatialharvesters.divider"));
            tooltip.add(new TranslationTextComponent("msg.spatialharvesters.dimensional_applicator_saved_effects"));
            for (int id : data.getIntArray(CustomValues.potionsNBTKey)) {
                Effect effect = Effect.get(id);
                if (effect != null) {
                    tooltip.add(effect.getDisplayName());
                }
            }
        }
        tooltip.add(new TranslationTextComponent("msg.spatialharvesters.divider"));
    }
}
