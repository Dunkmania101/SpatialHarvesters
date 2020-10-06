package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;

import java.util.List;

public class PreservedDataCustomHorizontalShapedBlock extends CustomHorizontalShapedBlock {
    public PreservedDataCustomHorizontalShapedBlock(Properties properties, VoxelShape shape, Direction front_direction) {
        super(properties, shape, front_direction);
    }

    public PreservedDataCustomHorizontalShapedBlock(Properties properties, VoxelShape shape) {
        this(properties, shape, Direction.NORTH);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> drops = super.getDrops(state, builder);
        TileEntity tile = builder.get(LootParameters.BLOCK_ENTITY);
        if (tile != null) {
            return Tools.getPreservedDataBlockDrops(drops, state, tile);
        }
        return drops;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            CompoundNBT stackTileNBT = stack.getChildTag(CustomValues.stackTileNBTKey);
            if (tileEntity != null && stackTileNBT != null) {
                tileEntity.deserializeNBT(stackTileNBT);
            }
        }
    }
}
