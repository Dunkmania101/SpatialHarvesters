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
        if (!worldIn.isRemote) {
            ServerWorld sworld = (ServerWorld) worldIn;
            ChunkPos cpos = worldIn.getChunk(pos).getPos();
            ChunkLoaderData data = ChunkLoaderData.get(sworld);
            data.addChunk(cpos);
            sworld.forceChunk(cpos.x, cpos.z, true);
        }
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!worldIn.isRemote) {
            ChunkPos cpos = worldIn.getChunk(pos).getPos();
            if (!(Tools.checkChunkBlocks(worldIn, cpos, BlockInit.CHUNK_LOADER.get().getBlock()) > 1)) {
                ServerWorld sworld = (ServerWorld) worldIn;
                ChunkLoaderData data = ChunkLoaderData.get(sworld);
                data.removeChunk(cpos);
                sworld.forceChunk(cpos.x, cpos.z, false);
            }
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }
}
