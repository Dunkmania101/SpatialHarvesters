package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;

import java.util.List;

public class PreservedDataCustomHorizontalShapedBlock extends CustomHorizontalShapedBlock {
    public PreservedDataCustomHorizontalShapedBlock(Properties properties, VoxelShape shape, Direction front_direction) {
        super(properties, shape, front_direction);
    }

    public PreservedDataCustomHorizontalShapedBlock(Properties properties, VoxelShape shape) {
        this(properties, shape, Direction.NORTH);
    }

    private CompoundNBT this_tileNBT = null;

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile != null) {
            this.this_tileNBT = tile.serializeNBT();
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> drops = super.getDrops(state, builder);
        if (this_tileNBT != null) {
            return Tools.getPreservedDataBlockDrops(drops, state, this.this_tileNBT);
        }
        return drops;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isRemote) {
            TileEntity tile = worldIn.getTileEntity(pos);
            CompoundNBT stackTileNBT = stack.getChildTag(CustomValues.stackTileNBTKey);
            if (tile != null && stackTileNBT != null) {
                tile.deserializeNBT(Tools.correctTileNBT(tile, stackTileNBT));
            }
        }
    }
}
