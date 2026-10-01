package dunkmania101.spatialharvesters.data.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;

public class WorldSaveData {
    private final String thisKey;
    private final ServerLevel thisServerLevel;

    public WorldSaveData(ServerLevel serverWorld, String key) {
        this.thisKey = key;
        this.thisServerLevel = serverWorld;
    }

    public void save() {
        CustomSaveData state = getPersistentState();
        customSaveActions(state.getTag());
        state.setDirty();
    }

    public void customSaveActions(CompoundTag data) {
    }

    public CompoundTag getWorldTag() {
        return getPersistentState().getTag();
    }

    public CustomSaveData getPersistentState() {
        return this.thisServerLevel.getDataStorage().computeIfAbsent(CustomSaveData.factory(), this.thisKey);
    }
}
