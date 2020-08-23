package dunkmania101.spatialharvesters.data;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.LongArrayNBT;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

import java.util.ArrayList;

public class ChunkLoaderData extends WorldSavedData {
    private static final String key = SpatialHarvesters.modid + "_ChunkLoaders";
    private final ArrayList<Long> CHUNK_LOADERS;
    public ChunkLoaderData() {
        super(key);
        this.CHUNK_LOADERS = new ArrayList<>();
    }

    public static ChunkLoaderData get(ServerWorld worldIn) {
        return worldIn.getSavedData().getOrCreate(ChunkLoaderData::new, key);
    }

    public ArrayList<Long> getChunkLoaders() {
        return this.CHUNK_LOADERS;
    }

    public void addChunk(ChunkPos cpos) {
        if (!this.CHUNK_LOADERS.contains(cpos.asLong())) {
            this.CHUNK_LOADERS.add(cpos.asLong());
            this.markDirty();
        }
    }

    public void removeChunk(ChunkPos cpos) {
        this.CHUNK_LOADERS.remove(cpos.asLong());
        this.markDirty();
    }

    @Override
    public void read(CompoundNBT nbt) {
        long[] CHUNK_LOADERS_NBT = nbt.getLongArray(key);
        for (long check_pos : CHUNK_LOADERS_NBT) {
            this.CHUNK_LOADERS.add(check_pos);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        LongArrayNBT CHUNK_LOADERS_NBT = new LongArrayNBT(CHUNK_LOADERS);
        compound.put(key, CHUNK_LOADERS_NBT);
        return compound;
    }
}