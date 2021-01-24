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

import java.util.function.BooleanSupplier;

@Mixin(ServerWorld.class)
public abstract class WorldMixin {
    @Shadow
    public abstract ServerWorld toServerWorld();

    @Inject(at = @At("TAIL"), method = "tick")
    public void injectTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        if (toServerWorld() != null) {
            ChunkLoaderData data = new ChunkLoaderData(toServerWorld());
            for (long long_pos : data.getChunkLoaders()) {
                ChunkPos chunkPos = toServerWorld().getChunk(BlockPos.fromLong(long_pos)).getPos();
                if (CommonConfig.enable_chunk_loader) {
                    toServerWorld().setChunkForced(chunkPos.x, chunkPos.z, true);
                    toServerWorld().tickChunk(toServerWorld().getChunk(chunkPos.x, chunkPos.z), toServerWorld().getLevelProperties().getGameRules().getInt(GameRules.RANDOM_TICK_SPEED));
                } else {
                    if (!data.getDisabledChunks().contains(long_pos)) {
                        if (toServerWorld().isChunkLoaded(chunkPos.getStartX(), chunkPos.getStartZ())) {
                            toServerWorld().setChunkForced(chunkPos.x, chunkPos.z, false);
                            data.addDisabledChunk(chunkPos);
                        }
                    }
                }
            }
        }
    }
}
