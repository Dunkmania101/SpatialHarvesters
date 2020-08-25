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
public class Events {
    @SubscribeEvent
    public static void tick(TickEvent.WorldTickEvent event) {
        if (event.world instanceof ServerWorld) {
            ServerWorld sworld = (ServerWorld) event.world;
            if (event.phase == TickEvent.Phase.END) {
                tickLoaders(sworld);
            }
        }
    }

    public static void tickLoaders(ServerWorld sworld) {
        ChunkLoaderData data = ChunkLoaderData.get(sworld);
        ArrayList<Long> CHUNK_LOADERS = data.getChunkLoaders();
        for (long long_pos : CHUNK_LOADERS) {
            ChunkPos cpos = sworld.getChunk(BlockPos.fromLong(long_pos)).getPos();
            if (!sworld.isAreaLoaded(cpos.asBlockPos(), 0)) {
                sworld.forceChunk(cpos.x, cpos.z, true);
            }
            sworld.tickEnvironment(sworld.getChunk(cpos.x, cpos.z), sworld.getWorldInfo().getGameRulesInstance().getInt(GameRules.RANDOM_TICK_SPEED));
        }
    }
}
