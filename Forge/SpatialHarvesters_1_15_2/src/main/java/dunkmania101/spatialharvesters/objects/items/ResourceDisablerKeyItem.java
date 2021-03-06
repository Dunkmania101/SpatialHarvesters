package dunkmania101.spatialharvesters.objects.items;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.objects.tile_entities.SpatialHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;

public class ResourceDisablerKeyItem extends Item {
    public ResourceDisablerKeyItem(Properties properties) {
        super(properties);
    }

    @Override
    public float getDestroySpeed(@Nonnull ItemStack stack, @Nonnull BlockState state) {
        int multiplier = CommonConfig.KEY_BREAK_SPEED_MULTIPLIER.get();
        return super.getDestroySpeed(stack, state) * multiplier;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
        if (player.isCrouching()) {
            ItemStack otherStack = player.getHeldItemOffhand();
            if (otherStack.isEmpty()) {
                player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.clear_disabled_resource"), true);
                itemstack.getOrCreateTag().remove(CustomValues.disabledResourceKey);
            } else {
                ResourceLocation rn = otherStack.getItem().getRegistryName();
                if (rn != null) {
                    player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.set_disabled_resource"), true);
                    itemstack.getOrCreateTag().putString(CustomValues.disabledResourceKey, rn.toString());
                }
            }
        }
        return true;
    }

    @Nonnull
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            World world = context.getWorld();
            if (!world.isRemote) {
                BlockPos pos = context.getPos();
                TileEntity tile = world.getTileEntity(pos);
                if (tile != null) {
                    if (tile instanceof SpatialHarvesterTE) {
                        CompoundNBT disabledNBT = new CompoundNBT();
                        if (player.isCrouching()) {
                            disabledNBT.putString(CustomValues.removeDisabledNBTKey, "");
                        } else {
                            CompoundNBT itemNBT = context.getItem().getTag();
                            if (itemNBT != null) {
                                if (itemNBT.contains(CustomValues.disabledResourceKey)) {
                                    disabledNBT.putString(CustomValues.disabledResourceKey, itemNBT.getString(CustomValues.disabledResourceKey));
                                }
                            }
                        }
                        if (disabledNBT.isEmpty()) {
                            player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.set_disabled_resource_failed"), true);
                        } else {
                            if (disabledNBT.contains(CustomValues.removeDisabledNBTKey)) {
                                player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.clear_disabled_resources"), true);
                            } else {
                                player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.add_disabled_resource"), true);
                            }
                            tile.deserializeNBT(Tools.correctTileNBT(tile, disabledNBT));
                        }
                    }
                }
            }
        }
        return super.onItemUse(context);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("msg.spatialharvesters.resource_disabler_key_description"));
        CompoundNBT nbt = stack.getTag();
        if (nbt != null) {
            tooltip.add(new TranslationTextComponent("msg.spatialharvesters.divider"));
            tooltip.add(new TranslationTextComponent("msg.spatialharvesters.disabled_resource"));
            if (nbt.contains(CustomValues.disabledResourceKey)) {
                String resource = nbt.getString(CustomValues.disabledResourceKey);
                if (!StringUtils.isNullOrEmpty(resource)) {
                    Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(resource));
                    if (item != null) {
                        tooltip.add(item.getName());
                    }
                }
            }
        }
        tooltip.add(new TranslationTextComponent("msg.spatialharvesters.divider"));
    }
}
