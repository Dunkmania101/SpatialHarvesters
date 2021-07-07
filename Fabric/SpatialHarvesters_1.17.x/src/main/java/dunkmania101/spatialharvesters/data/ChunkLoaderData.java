package dunkmania101.spatialharvesters.data;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ChunkPos;

import java.util.ArrayList;

public class ChunkLoaderData extends WorldSaveData {
    private final ArrayList<Long> chunkLoaders;
    private final ArrayList<Long> disabledChunks;

    public ChunkLoaderData(ServerWorld serverWorld) {
        super(serverWorld, CustomValues.chunkLoaderDataKey);
        this.chunkLoaders = new ArrayList<>();
        for (long chunkLong : getWorldTag().getLongArray(CustomValues.chunkLoaderDataKey)) {
            this.chunkLoaders.add(chunkLong);
        }
        this.disabledChunks = new ArrayList<>();
        if (!CommonConfig.enable_chunk_loader) {
            for (long chunkLong : getWorldTag().getLongArray(CustomValues.disabledChunksKey)) {
                this.disabledChunks.add(chunkLong);
            }
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

    public ArrayList<Long> getDisabledChunks() {
        return this.disabledChunks;
    }

    public void addDisabledChunk(ChunkPos cpos) {
        long posLong = cpos.toLong();
        if (!this.disabledChunks.contains(posLong)) {
            this.disabledChunks.add(posLong);
            save();
        }
    }

    @Override
    public void customSaveActions(NbtCompound data) {
        super.customSaveActions(data);
        data.putLongArray(CustomValues.chunkLoaderDataKey, getChunkLoaders());
        if (!CommonConfig.enable_chunk_loader) {
            data.putLongArray(CustomValues.disabledChunksKey, getDisabledChunks());
        }
    }
}
