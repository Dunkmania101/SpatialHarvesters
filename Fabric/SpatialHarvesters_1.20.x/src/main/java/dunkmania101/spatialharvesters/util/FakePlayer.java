package dunkmania101.spatialharvesters.util;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FakePlayer extends PlayerEntity {
    public FakePlayer(World world, UUID uuid, String name) {
        super(world, BlockPos.ORIGIN, 0F, new GameProfile(uuid, name));
    }

    public FakePlayer(World world) {
        this(world, UUID.randomUUID(), UUID.randomUUID().toString());
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
