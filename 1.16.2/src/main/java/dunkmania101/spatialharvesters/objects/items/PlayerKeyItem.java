package dunkmania101.spatialharvesters.objects.items;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.objects.tile_entities.DimensionalApplicatorTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class PlayerKeyItem extends Item {
    public PlayerKeyItem(Properties properties) {
        super(properties);
    }

    public static final String playerNBTKey = SpatialHarvesters.modid + "_DimensionalApplicatorEntity";
    public static final String removePlayerNBTKey = SpatialHarvesters.modid + "removePlayer";

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote) {
            TileEntity tile = world.getTileEntity(context.getPos());
            if (tile != null) {
                if (tile instanceof DimensionalApplicatorTE) {
                    PlayerEntity player = context.getPlayer();
                    if (player != null) {
                        CompoundNBT nbt = new CompoundNBT();
                        if (player.isCrouching()) {
                            nbt.putString(removePlayerNBTKey, "");
                            player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.remove_dimensional_applicator"), true);
                        } else {
                            nbt.putInt(playerNBTKey, player.getEntityId());
                            player.sendStatusMessage(new TranslationTextComponent("msg.spatialharvesters.set_dimensional_applicator"), true);
                        }
                        tile.deserializeNBT(Tools.correctTileNBT(tile, nbt));
                        tile.markDirty();
                    }
                }
            }
        }
        return super.onItemUse(context);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("msg.spatialharvesters.player_key_description"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
