package dunkmania101.spatialharvesters.mixin;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.BooleanSupplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dunkmania101.spatialharvesters.data.ChunkLoaderData;
import dunkmania101.spatialharvesters.data.CommonConfig;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Spawner;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.level.ServerWorldProperties;
import net.minecraft.world.level.storage.LevelStorage.Session;

@Mixin(ServerWorld.class)
public class WorldMixin extends ServerWorld {
    public WorldMixin(MinecraftServer server, Executor workerExecutor, Session session,
            ServerWorldProperties properties, RegistryKey<World> registryKey, DimensionType dimensionType,
            WorldGenerationProgressListener worldGenerationProgressListener, ChunkGenerator chunkGenerator,
            boolean debugWorld, long l, List<Spawner> list, boolean bl) {
        super(server, workerExecutor, session, properties, registryKey, dimensionType, worldGenerationProgressListener,
                chunkGenerator, debugWorld, l, list, bl);
    }

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
                        if (toServerWorld().isChunkLoaded(chunkPos.getStartPos())) {
                            toServerWorld().setChunkForced(chunkPos.x, chunkPos.z, false);
                            data.addDisabledChunk(chunkPos);
                        }
                    }
                }
            }
        }
    }
}
