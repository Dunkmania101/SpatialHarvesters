package dunkmania101.spatialharvesters.mixin;

import dunkmania101.spatialharvesters.data.ChunkLoaderData;
import dunkmania101.spatialharvesters.data.CommonConfig;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

@Mixin(ServerWorld.class)
public interface WorldMixin {
    @Shadow
    ServerWorld toServerWorld();

    @Inject(at = @At("TAIL"), method = "tick")
    default void tick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        if (toServerWorld() != null) {
            boolean chunkLoaderEnabled = CommonConfig.ENABLE_CHUNK_LOADER;
            ChunkLoaderData data = new ChunkLoaderData(toServerWorld());
            ArrayList<Long> CHUNK_LOADERS = data.getChunkLoaders();
            for (long long_pos : CHUNK_LOADERS) {
                ChunkPos chunkPos = toServerWorld().getChunk(BlockPos.fromLong(long_pos)).getPos();
                if (chunkLoaderEnabled) {
                    toServerWorld().setChunkForced(chunkPos.x, chunkPos.z, true);
                    toServerWorld().tickChunk(toServerWorld().getChunk(chunkPos.x, chunkPos.z), toServerWorld().getLevelProperties().getGameRules().getInt(GameRules.RANDOM_TICK_SPEED));
                } else {
                    if (toServerWorld().isChunkLoaded(chunkPos.getStartX(), chunkPos.getStartZ())) {
                        toServerWorld().setChunkForced(chunkPos.x, chunkPos.z, false);
                    }
                }
            }
        }
    }
}
