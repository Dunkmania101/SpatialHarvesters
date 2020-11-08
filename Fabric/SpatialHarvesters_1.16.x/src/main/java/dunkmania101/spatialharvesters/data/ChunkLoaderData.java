package dunkmania101.spatialharvesters.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ChunkPos;

import java.util.ArrayList;

public class ChunkLoaderData extends WorldSaveData {
    private final ArrayList<Long> chunkLoaders;

    public ChunkLoaderData(ServerWorld serverWorld) {
        super(serverWorld, CustomValues.chunkLoaderDataKey);
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

    @Override
    public void customSaveActions(CompoundTag data) {
        super.customSaveActions(data);
        data.putLongArray(CustomValues.chunkLoaderDataKey, this.chunkLoaders);
    }
}
