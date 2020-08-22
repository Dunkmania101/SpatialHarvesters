package dunkmania101.spatialharvesters.objects.items;

import com.mojang.util.UUIDTypeAdapter;
import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class PlayerKeyItem extends Item {
    public PlayerKeyItem(Properties properties) {
        super(properties);
    }

    private static final String key = SpatialHarvesters.modid + "_DimensionalApplicatorEntity";
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote) {
            BlockPos pos = context.getPos();
            TileEntity tile = world.getTileEntity(pos);
            if (tile != null) {
                if (tile.getType() == TileEntityInit.DIMENSIONAL_APPLICATOR.get()) {
                    PlayerEntity player = context.getPlayer();
                    if (player != null) {
                        CompoundNBT nbt = new CompoundNBT();
                        if (player.isCrouching()) {
                            player.sendStatusMessage(new StringTextComponent("Cleared Dimensional Applicator!"), true);
                        } else {
                            nbt.putString(key, UUIDTypeAdapter.fromUUID(player.getUniqueID()));
                            player.sendStatusMessage(new StringTextComponent("Set Dimensional Applicator to you!"), true);
                        }
                        tile.deserializeNBT(nbt);
                        tile.markDirty();
                    }
                }
            }
        }
        return super.onItemUse(context);
    }
}
