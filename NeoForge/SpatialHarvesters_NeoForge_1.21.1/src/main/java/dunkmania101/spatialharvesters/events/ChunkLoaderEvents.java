package dunkmania101.spatialharvesters.events;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.data.ChunkLoaderData;
import dunkmania101.spatialharvesters.data.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber(modid = SpatialHarvesters.modid)
public class ChunkLoaderEvents {
    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }
        ChunkLoaderData data = new ChunkLoaderData(level);
        boolean enabled = CommonConfig.ENABLE_CHUNK_LOADER.get();
        for (long posLong : data.getChunkLoaders()) {
            ChunkPos chunkPos = level.getChunk(BlockPos.of(posLong)).getPos();
            if (enabled) {
                level.setChunkForced(chunkPos.x, chunkPos.z, true);
                level.tickChunk(level.getChunk(chunkPos.x, chunkPos.z),
                        level.getLevelData().getGameRules().getInt(GameRules.RULE_RANDOMTICKING));
            } else if (!data.getDisabledChunks().contains(posLong)) {
                level.setChunkForced(chunkPos.x, chunkPos.z, false);
                data.addDisabledChunk(chunkPos);
            }
        }
    }
}
