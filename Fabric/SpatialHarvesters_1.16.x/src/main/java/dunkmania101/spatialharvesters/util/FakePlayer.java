package dunkmania101.spatialharvesters.util;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.UUID;

public class FakePlayer extends PlayerEntity {
    public FakePlayer(World world) {
        super(world, BlockPos.ORIGIN, 0, new GameProfile(UUID.randomUUID(), "John Smith"));
    }

    @Override
    public boolean isSpectator() {
        return false;
    }

    @Override
    public boolean isCreative() {
        return false;
    }
}