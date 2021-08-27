package dunkmania101.spatialharvesters.events;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.data.ChunkLoaderData;
import dunkmania101.spatialharvesters.data.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = SpatialHarvesters.modid, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WorldEvents {
    @SubscribeEvent
    public static void tick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (event.world instanceof ServerLevel) {
                ServerLevel serverWorld = (ServerLevel) event.world;
                boolean chunkLoaderEnabled = CommonConfig.ENABLE_CHUNK_LOADER.get();
                ChunkLoaderData data = new ChunkLoaderData(serverWorld);
                ArrayList<Long> CHUNK_LOADERS = data.getChunkLoaders();
                for (long long_pos : CHUNK_LOADERS) {
                    ChunkPos cpos = serverWorld.getChunk(BlockPos.of(long_pos)).getPos();
                    if (chunkLoaderEnabled) {
                        serverWorld.setChunkForced(cpos.x, cpos.z, true);
                        serverWorld.tickChunk(serverWorld.getChunk(cpos.x, cpos.z), serverWorld.getGameRules().getInt(GameRules.RULE_RANDOMTICKING));
                    } else {
                        if (!data.getDisabledChunks().contains(long_pos)) {
                            if (serverWorld.isAreaLoaded(cpos.getWorldPosition(), 0)) {
                                serverWorld.setChunkForced(cpos.x, cpos.z, false);
                                data.addDisabledChunk(cpos);
                            }
                        }
                    }
                }
            }
        }
    }
}
