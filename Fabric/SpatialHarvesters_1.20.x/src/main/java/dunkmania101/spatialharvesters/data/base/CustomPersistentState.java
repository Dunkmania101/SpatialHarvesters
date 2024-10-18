package dunkmania101.spatialharvesters.data.base;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.PersistentState;

public class CustomPersistentState extends PersistentState {
    NbtCompound thisTag = new NbtCompound();

    public static CustomPersistentState ofNbt(NbtCompound nbt) {
        CustomPersistentState state = new CustomPersistentState();
        state.readNbt(nbt);
        return state;
    }

    public void readNbt(NbtCompound tag) {
        this.thisTag = tag;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        return this.thisTag;
    }
}
