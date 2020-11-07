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
        for (long chunkLong : getWorldTag().getLongArray(CustomValues.chunkLoaderDataKey)) {
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
            save();
        }
    }

    public void removeChunk(ChunkPos chunkPos) {
        this.chunkLoaders.remove(chunkPos.toLong());
        save();
    }

    public void save() {
        getWorldTag().putLongArray(CustomValues.chunkLoaderDataKey, this.chunkLoaders);
    }

    public CompoundTag getWorldTag() {
        return this.thisServerWorld.getPersistentStateManager().getOrCreate(null, CustomValues.chunkLoaderDataKey).toTag(new CompoundTag());
    }
}
