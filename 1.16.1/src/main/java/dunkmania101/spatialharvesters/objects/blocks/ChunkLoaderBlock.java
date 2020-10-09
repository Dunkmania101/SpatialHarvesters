package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.ChunkLoaderData;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ChunkLoaderBlock extends Block {
    public ChunkLoaderBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isRemote) {
            if (worldIn instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) worldIn;
                ChunkPos cpos = serverWorld.getChunk(pos).getPos();
                ChunkLoaderData data = ChunkLoaderData.get(serverWorld);
                data.addChunk(cpos);
                serverWorld.forceChunk(cpos.x, cpos.z, true);
            }
        }
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
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
