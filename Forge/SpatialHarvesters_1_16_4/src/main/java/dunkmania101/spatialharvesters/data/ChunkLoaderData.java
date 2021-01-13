package dunkmania101.spatialharvesters.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.LongArrayNBT;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class ChunkLoaderData extends WorldSavedData {
    private final ArrayList<Long> chunkLoaders;
    private final ArrayList<Long> disabledChunks;

    public ChunkLoaderData() {
        super(CustomValues.chunkLoaderDataKey);
        this.chunkLoaders = new ArrayList<>();
        this.disabledChunks = new ArrayList<>();
    }

    public static ChunkLoaderData get(ServerWorld worldIn) {
        return worldIn.getSavedData().getOrCreate(ChunkLoaderData::new, CustomValues.chunkLoaderDataKey);
    }

    public ArrayList<Long> getChunkLoaders() {
        return this.chunkLoaders;
    }

    public void addChunk(ChunkPos cpos) {
        long posLong = cpos.asLong();
        if (!this.chunkLoaders.contains(posLong)) {
            this.chunkLoaders.add(posLong);
            this.markDirty();
        }
    }

    public void removeChunk(ChunkPos cpos) {
        this.chunkLoaders.remove(cpos.asLong());
        this.markDirty();
    }

    public ArrayList<Long> getDisabledChunks() {
        return this.disabledChunks;
    }

    public void addDisabledChunk(ChunkPos cpos) {
        long posLong = cpos.asLong();
        if (!this.disabledChunks.contains(posLong)) {
            this.disabledChunks.add(posLong);
            this.markDirty();
        }
    }

    @Override
    public void read(CompoundNBT nbt) {
        long[] chunkLoadersNBT = nbt.getLongArray(CustomValues.chunkLoaderDataKey);
        for (long check_pos : chunkLoadersNBT) {
            if (!this.chunkLoaders.contains(check_pos)) {
                this.chunkLoaders.add(check_pos);
            }
        }
        boolean enableChunkLoader = CommonConfig.ENABLE_CHUNK_LOADER.get();
        if (!enableChunkLoader) {
            long[] disabledChunksNBT = nbt.getLongArray(CustomValues.chunkLoaderDataKey);
            for (long check_pos : disabledChunksNBT) {
                if (!this.disabledChunks.contains(check_pos)) {
                    this.disabledChunks.add(check_pos);
                }
            }
        }
    }

    @Nonnull
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        LongArrayNBT chunkLoadersNBT = new LongArrayNBT(chunkLoaders);
        compound.put(CustomValues.chunkLoaderDataKey, chunkLoadersNBT);
        LongArrayNBT disabledChunksNBT = new LongArrayNBT(disabledChunks);
        compound.put(CustomValues.disabledChunksKey, disabledChunksNBT);
        return compound;
    }
}
