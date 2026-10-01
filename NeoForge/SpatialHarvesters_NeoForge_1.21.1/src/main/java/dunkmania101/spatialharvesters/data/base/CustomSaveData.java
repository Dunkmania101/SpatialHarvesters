package dunkmania101.spatialharvesters.data.base;

import javax.annotation.Nonnull;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class CustomSaveData extends SavedData {
    private CompoundTag thisTag = new CompoundTag();

    public static SavedData.Factory<CustomSaveData> factory() {
        return new SavedData.Factory<>(CustomSaveData::new, CustomSaveData::ofNbt, null);
    }

    public static CustomSaveData ofNbt(CompoundTag nbt, HolderLookup.Provider registries) {
        CustomSaveData state = new CustomSaveData();
        state.readNbt(nbt);
        return state;
    }

    public void readNbt(CompoundTag tag) {
        this.thisTag = tag;
    }

    public CompoundTag getTag() {
        return this.thisTag;
    }

    @Nonnull
    @Override
    public CompoundTag save(@Nonnull CompoundTag nbt, @Nonnull HolderLookup.Provider registries) {
        return this.thisTag;
    }
}
