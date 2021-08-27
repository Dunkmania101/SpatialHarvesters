package dunkmania101.spatialharvesters.data.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class CustomSaveData extends SavedData {
    CompoundTag thisTag = new CompoundTag();

    public static CustomSaveData ofNbt(CompoundTag nbt) {
        CustomSaveData state = new CustomSaveData();
        state.readNbt(nbt);
        return state;
    }

    public void readNbt(CompoundTag tag) {
        this.thisTag = tag;
    }

    @Override
    public CompoundTag save(CompoundTag nbt) {
        return this.thisTag;
    }
}
