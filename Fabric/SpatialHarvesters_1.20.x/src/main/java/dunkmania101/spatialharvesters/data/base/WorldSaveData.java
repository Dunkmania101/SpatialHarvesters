package dunkmania101.spatialharvesters.data.base;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;

import java.io.IOException;

public class WorldSaveData {
    private final String thisKey;
    private final ServerWorld thisServerWorld;

    public WorldSaveData(ServerWorld serverWorld, String key) {
        this.thisKey = key;
        this.thisServerWorld = serverWorld;
    }

    public void save() {
        NbtCompound worldData = getWorldTag();
        customSaveActions(worldData);
        try {
            this.thisServerWorld.getPersistentStateManager().readNbt(this.thisKey, 1343);
        } catch (IOException e) {
            SpatialHarvesters.LOGGER.catching(e);
        }
        getPersistentState().markDirty();
    }

    public void customSaveActions(NbtCompound data) {
    }

    public NbtCompound getWorldTag() {
        return getPersistentState().writeNbt(new NbtCompound());
    }

    public PersistentState getPersistentState() {
        return this.thisServerWorld.getPersistentStateManager().getOrCreate(CustomPersistentState::ofNbt, CustomPersistentState::new, this.thisKey);
    }
}
