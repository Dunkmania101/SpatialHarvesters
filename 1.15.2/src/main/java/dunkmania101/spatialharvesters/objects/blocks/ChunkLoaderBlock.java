package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.ChunkLoaderData;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;

public class ChunkLoaderBlock extends CustomHorizontalShapedBlock {
    public ChunkLoaderBlock(Properties properties) {
        super(properties, CustomValues.machineShape, Direction.NORTH);
    }

    @Override
    public void onBlockPlacedBy(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, LivingEntity placer, @Nonnull ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isRemote) {
            if (worldIn instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) worldIn;
                ChunkPos cpos = serverWorld.getChunk(pos).getPos();
                ChunkLoaderData data = ChunkLoaderData.get(serverWorld);
                data.addChunk(cpos);
            }
        }
    }

    @Override
    public void onBlockHarvested(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull PlayerEntity player) {
        super.onBlockHarvested(worldIn, pos, state, player);
        if (!worldIn.isRemote) {
            if (worldIn instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) worldIn;
                ChunkPos cpos = serverWorld.getChunk(pos).getPos();
                if (Tools.getBlocksInChunk(serverWorld, cpos, BlockInit.CHUNK_LOADER.get().getBlock()) <= 0) {
                    ChunkLoaderData data = ChunkLoaderData.get(serverWorld);
                    data.removeChunk(cpos);
                    serverWorld.forceChunk(cpos.x, cpos.z, false);
                }
            }
        }
    }
}
