package dunkmania101.spatialharvesters.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;

public class WorldSaveData {
    private final String thisKey;
    private final ServerWorld thisServerWorld;

    public WorldSaveData(ServerWorld serverWorld, String key) {
        this.thisKey = key;
        this.thisServerWorld = serverWorld;
    }

    public void save() {
        CompoundTag worldData = getWorldTag();
        customSaveActions(worldData);
        getPersistentState().fromTag(worldData);
        getPersistentState().markDirty();
    }

    public void customSaveActions(CompoundTag data) {
    }

    public CompoundTag getWorldTag() {
        return getPersistentState().toTag(new CompoundTag());
    }

    public <P extends PersistentState> P getPersistentState() {
        return this.thisServerWorld.getPersistentStateManager().getOrCreate(null, this.thisKey);
    }
}
