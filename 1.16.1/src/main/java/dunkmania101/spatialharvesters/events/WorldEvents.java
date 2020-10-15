package dunkmania101.spatialharvesters.events;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.data.ChunkLoaderData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = SpatialHarvesters.modid, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WorldEvents {
    @SubscribeEvent
    public static void tick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (event.world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) event.world;
                ChunkLoaderData data = ChunkLoaderData.get(serverWorld);
                ArrayList<Long> CHUNK_LOADERS = data.getChunkLoaders();
                for (long long_pos : CHUNK_LOADERS) {
                    ChunkPos cpos = serverWorld.getChunk(BlockPos.fromLong(long_pos)).getPos();
                    if (!serverWorld.isAreaLoaded(cpos.asBlockPos(), 0)) {
                        serverWorld.forceChunk(cpos.x, cpos.z, true);
                    }
                    serverWorld.tickEnvironment(serverWorld.getChunk(cpos.x, cpos.z), serverWorld.getWorldInfo().getGameRulesInstance().getInt(GameRules.RANDOM_TICK_SPEED));
                }
            }
        }
    }
}
