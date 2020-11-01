package dunkmania101.spatialharvesters.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ChunkPos;

import java.util.ArrayList;

public class ChunkLoaderData {
    private final ArrayList<Long> chunkLoaders;
    private final ServerWorld thisServerWorld;

    public ChunkLoaderData(ServerWorld serverWorld) {
        thisServerWorld = serverWorld;
        this.chunkLoaders = new ArrayList<>();
        CompoundTag data = serverWorld.getPersistentStateManager().getOrCreate(null, CustomValues.chunkLoaderDataKey).toTag(new CompoundTag());
        for (long chunkLong : data.getLongArray(CustomValues.chunkLoaderDataKey)) {
            chunkLoaders.add(chunkLong);
        }
    }

    public ArrayList<Long> getChunkLoaders() {
        return this.chunkLoaders;
    }

    public void addChunk(ChunkPos chunkPos) {
        long posLong = chunkPos.toLong();
        if (!this.chunkLoaders.contains(posLong)) {
            this.chunkLoaders.add(posLong);
            save(this.thisServerWorld);
        }
    }

    public void removeChunk(ChunkPos chunkPos) {
        this.chunkLoaders.remove(chunkPos.toLong());
        save(this.thisServerWorld);
    }

    public void save(ServerWorld serverWorld) {
        getWorldTag(serverWorld).putLongArray(CustomValues.chunkLoaderDataKey, this.chunkLoaders);
    }

    public CompoundTag getWorldTag(ServerWorld serverWorld) {
        return serverWorld.getPersistentStateManager().getOrCreate(null, CustomValues.chunkLoaderDataKey).toTag(new CompoundTag());
    }
}
