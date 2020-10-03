package dunkmania101.spatialharvesters.objects.items;

import com.mojang.util.UUIDTypeAdapter;
import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.init.TileEntityInit;
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
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote) {
            TileEntity tile = world.getTileEntity(context.getPos());
            if (tile != null) {
                if (tile.getType() == TileEntityInit.DIMENSIONAL_APPLICATOR.get()) {
                    PlayerEntity player = context.getPlayer();
                    if (player != null) {
                        CompoundNBT nbt = new CompoundNBT();
                        String playerUUID = "";
                        if (player.isCrouching()) {
                            player.sendStatusMessage(new TranslationTextComponent("msg.remove_dimensional_applicator"), true);
                        } else {
                            playerUUID = UUIDTypeAdapter.fromUUID(player.getUniqueID());
                            player.sendStatusMessage(new TranslationTextComponent("msg.set_dimensional_applicator"), true);
                        }
                        nbt.putString(playerNBTKey, playerUUID);
                        tile.deserializeNBT(nbt);
                        tile.markDirty();
                    }
                }
            }
        }
        return super.onItemUse(context);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("msg.player_key_description"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
