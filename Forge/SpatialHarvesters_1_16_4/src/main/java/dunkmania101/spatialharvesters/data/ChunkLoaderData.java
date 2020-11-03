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

    public ChunkLoaderData() {
        super(CustomValues.chunkLoaderDataKey);
        this.chunkLoaders = new ArrayList<>();
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

    @Override
    public void read(CompoundNBT nbt) {
        long[] chunkLoadersNBT = nbt.getLongArray(CustomValues.chunkLoaderDataKey);
        for (long check_pos : chunkLoadersNBT) {
            this.chunkLoaders.add(check_pos);
        }
    }

    @Nonnull
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        LongArrayNBT chunkLoadersNBT = new LongArrayNBT(chunkLoaders);
        compound.put(CustomValues.chunkLoaderDataKey, chunkLoadersNBT);
        return compound;
    }
}
