package dunkmania101.spatialharvesters.data.base;

import java.io.IOException;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class WorldSaveData {
    private final String thisKey;
    private final ServerLevel thisServerLevel;

    public WorldSaveData(ServerLevel serverWorld, String key) {
        this.thisKey = key;
        this.thisServerLevel = serverWorld;
    }

    public void save() {
        CompoundTag worldData = getWorldTag();
        customSaveActions(worldData);
        try {
            this.thisServerLevel.getDataStorage().readTagFromDisk(this.thisKey, 1343);
        } catch (IOException e) {
            SpatialHarvesters.LOGGER.catching(e);
        }
        getPersistentState().setDirty();
    }

    public void customSaveActions(CompoundTag data) {
        this.thisServerLevel.getDataStorage().computeIfAbsent(null, null, thisKey);
    }

    public CompoundTag getWorldTag() {
        return getPersistentState().save(new CompoundTag());
    }

    public SavedData getPersistentState() {
        return this.thisServerLevel.getDataStorage().computeIfAbsent(CustomSaveData::ofNbt, CustomSaveData::new, this.thisKey);
    }
}
