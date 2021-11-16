package dunkmania101.spatialharvesters.mixin;

import java.util.function.BooleanSupplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dunkmania101.spatialharvesters.data.ChunkLoaderData;
import dunkmania101.spatialharvesters.data.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.GameRules;

@Mixin(ServerLevel.class)
public abstract class WorldMixin {
    @Shadow
    public abstract ServerLevel getLevel();

    @Inject(at = @At("TAIL"), method = "tick")
    public void injectTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        if (getLevel() != null) {
            ChunkLoaderData data = new ChunkLoaderData(getLevel());
            for (long long_pos : data.getChunkLoaders()) {
                ChunkPos chunkPos = getLevel().getChunk(BlockPos.of(long_pos)).getPos();
                if (CommonConfig.ENABLE_CHUNK_LOADER.get()) {
                    getLevel().setChunkForced(chunkPos.x, chunkPos.z, true);
                    getLevel().tickChunk(getLevel().getChunk(chunkPos.x, chunkPos.z), getLevel().getLevelData().getGameRules().getInt(GameRules.RULE_RANDOMTICKING));
                } else {
                    if (!data.getDisabledChunks().contains(long_pos)) {
                        getLevel().setChunkForced(chunkPos.x, chunkPos.z, false);
                        data.addDisabledChunk(chunkPos);
                    }
                }
            }
        }
    }
}
