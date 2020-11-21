package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.ChunkLoaderData;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ChunkLoaderBlock extends CustomHorizontalShapedBlock {
    public ChunkLoaderBlock(Settings settings) {
        super(settings, CustomValues.machineShape);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient) {
            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) world;
                ChunkPos chunkPos = serverWorld.getChunk(pos).getPos();
                ChunkLoaderData data = new ChunkLoaderData(serverWorld);
                data.addChunk(chunkPos);
            }
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if (!world.isClient) {
            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) world;
                ChunkPos chunkPos = serverWorld.getChunk(pos).getPos();
                if (Tools.getBlocksInChunk(serverWorld, chunkPos, BlockInit.CHUNK_LOADER) <= 0) {
                    ChunkLoaderData data = new ChunkLoaderData(serverWorld);
                    data.removeChunk(chunkPos);
                    serverWorld.setChunkForced(chunkPos.x, chunkPos.z, false);
                }
            }
        }
    }
}
