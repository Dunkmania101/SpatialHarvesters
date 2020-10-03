package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class PreservedDataBlock extends Block {
    private static final String stackTileNBTKey = SpatialHarvesters.modid + "_stackTileNBT";

    public PreservedDataBlock(Properties properties) {
        super(properties);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> drops = super.getDrops(state, builder);
        TileEntity tileEntity = builder.get(LootParameters.BLOCK_ENTITY);
        if (tileEntity != null) {
            for (ItemStack stack : drops) {
                if (stack.getItem() == state.getBlock().asItem()) {
                    int i = drops.indexOf(stack);
                    CompoundNBT tileNBT = tileEntity.serializeNBT();
                    stack.getOrCreateTag().put(stackTileNBTKey, Tools.reduceTileNBT(tileNBT));
                    drops.set(i, stack);
                    break;
                }
            }
        }
        return drops;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            CompoundNBT stackTileNBT = stack.getChildTag(stackTileNBTKey);
            if (tileEntity != null && stackTileNBT != null) {
                tileEntity.deserializeNBT(stackTileNBT);
            }
        }
    }
}
