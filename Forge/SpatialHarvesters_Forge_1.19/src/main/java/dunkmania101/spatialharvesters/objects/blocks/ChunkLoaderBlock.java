package dunkmania101.spatialharvesters.objects.blocks;

import dunkmania101.spatialharvesters.data.ChunkLoaderData;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.init.BlockInit;
import dunkmania101.spatialharvesters.objects.blocks.base.CustomHorizontalShapedBlock;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class ChunkLoaderBlock extends CustomHorizontalShapedBlock {
    public ChunkLoaderBlock(Properties properties) {
        super(properties, CustomValues.machineShape);
    }

    @Override
    public void setPlacedBy(@Nonnull Level worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, LivingEntity placer, @Nonnull ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isClientSide()) {
            if (worldIn instanceof ServerLevel) {
                ServerLevel serverWorld = (ServerLevel) worldIn;
                ChunkPos cpos = serverWorld.getChunk(pos).getPos();
                ChunkLoaderData data = new ChunkLoaderData(serverWorld);
                data.addChunk(cpos);
            }
        }
    }

    @Override
    public void playerWillDestroy(@Nonnull Level worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull Player player) {
        super.playerWillDestroy(worldIn, pos, state, player);
        if (!worldIn.isClientSide()) {
            if (worldIn instanceof ServerLevel) {
                ServerLevel serverWorld = (ServerLevel) worldIn;
                ChunkPos cpos = serverWorld.getChunk(pos).getPos();
                if (Tools.getBlocksInChunk(serverWorld, cpos, BlockInit.CHUNK_LOADER.get(), 1) <= 0) {
                    ChunkLoaderData data = new ChunkLoaderData(serverWorld);
                    data.removeChunk(cpos);
                    serverWorld.setChunkForced(cpos.x, cpos.z, false);
                }
            }
        }
    }
}
