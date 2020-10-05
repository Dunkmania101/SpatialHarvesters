package dunkmania101.spatialharvesters.objects.items;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.objects.tile_entities.MobHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class MobKeyItem extends Item {
    public static final String removeEntityNBTKey = SpatialHarvesters.modid + "_removeEntityNBT";
    public static final String entityNBTKey = SpatialHarvesters.modid + "_entityNBT";
    public static final String playerNameNBTKey = SpatialHarvesters.modid + "_playerNameNBT";
    public static final String weaponNBTKey = SpatialHarvesters.modid + "_weaponNBT";
    public MobKeyItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker.isCrouching()) {
            stack.getOrCreateTag().putString(entityNBTKey, target.serializeNBT().getString("id"));
        }
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
        if (player.isCrouching()) {
            ItemStack other_stack = player.getHeldItemOffhand();
            itemstack.getOrCreateTag().put(weaponNBTKey, other_stack.serializeNBT());
        }
        return super.onBlockStartBreak(itemstack, pos, player);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            TileEntity tile = context.getWorld().getTileEntity(context.getPos());
            if (tile != null) {
                if (tile instanceof MobHarvesterTE) {
                    CompoundNBT harvesterNBT = new CompoundNBT();
                    if (player.isCrouching()) {
                        harvesterNBT.putString(removeEntityNBTKey, "");
                        player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.remove_mob_harvester"), true);
                    } else {
                        harvesterNBT.putString(playerNameNBTKey, player.getName().getString());
                        CompoundNBT itemNBT = context.getItem().getTag();
                        if (itemNBT != null) {
                            if (itemNBT.contains(entityNBTKey)) {
                                harvesterNBT.putString(entityNBTKey, itemNBT.getString(entityNBTKey));
                            }
                            if (itemNBT.contains(weaponNBTKey)) {
                                harvesterNBT.put(weaponNBTKey, itemNBT.getCompound(weaponNBTKey));
                            }
                        }
                    }
                    tile.deserializeNBT(Tools.correctTileNBT(tile, harvesterNBT));
                    player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.set_mob_harvester"), true);
                }
            }
        }
        return super.onItemUse(context);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("msg.spatialharvesters.mob_key_description"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
