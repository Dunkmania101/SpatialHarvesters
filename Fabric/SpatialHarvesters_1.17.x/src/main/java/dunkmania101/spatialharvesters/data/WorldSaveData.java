package dunkmania101.spatialharvesters.data;

import net.minecraft.nbt.NbtCompound;
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
        NbtCompound worldData = getWorldTag();
        customSaveActions(worldData);
        getPersistentState().fromTag(worldData);
        getPersistentState().markDirty();
    }

    public void customSaveActions(NbtCompound data) {
    }

    public NbtCompound getWorldTag() {
        return getPersistentState().toTag(new NbtCompound());
    }

    public PersistentState getPersistentState() {
        return this.thisServerWorld.getPersistentStateManager().getOrCreate(() -> new PersistentState(this.thisKey) {
            NbtCompound thisTag = new NbtCompound();
            @Override
            public void fromTag(NbtCompound tag) {
                this.thisTag = tag;
            }

            @Override
            public NbtCompound toTag(NbtCompound tag) {
                return thisTag;
            }
        }, this.thisKey);
    }
}
