package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Random;

public class DarkMobHarvesterTE extends MobHarvesterTE {
    private final ArrayList<String> MOBS = new ArrayList<>();

    public DarkMobHarvesterTE() {
        super(TileEntityInit.DARK_MOB_HARVESTER.get());
    }

    @Override
    protected void lastMinuteActions() {
        if (getWorld() != null && !getWorld().isRemote) {
            if (this.MOBS.isEmpty()) {
                if (getWorld() != null && !getWorld().isRemote) {
                    for (EntityType<?> entityType : ForgeRegistries.ENTITIES.getValues()) {
                        if (entityType != null) {
                            ArrayList<ArrayList<String>> blacklist_mobs = CommonConfig.BLACKLIST_MOBS.get();
                            ArrayList<String> blacklist_mobs_mod = CommonConfig.BLACKLIST_MOBS_MOD.get();
                            ResourceLocation rn = entityType.getRegistryName();
                            if (rn != null) {
                                if (!Tools.isResourceBanned(rn, blacklist_mobs, blacklist_mobs_mod)) {
                                    Entity entity = entityType.create(getWorld());
                                    if (entity != null) {
                                        if (entity instanceof MobEntity) {
                                            if (entity.isNonBoss()) {
                                                this.MOBS.add(rn.toString());
                                            }
                                        }
                                        entity.remove();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!this.MOBS.isEmpty()) {
                Random rand = getWorld().getRandom();
                this.entity = this.MOBS.get(rand.nextInt(this.MOBS.size()));
            }
        }
        if (getWorld() != null && !getWorld().isRemote) {
            if (!this.MOBS.isEmpty()) {
                Random rand = getWorld().getRandom();
                this.entity = this.MOBS.get(rand.nextInt(this.MOBS.size()));
            }
        }
        super.lastMinuteActions();
    }

    @Override
    public int getPrice(Block block) {
        return CommonConfig.DARK_MOB_PRICE.get();
    }

    @Override
    public int getSpeed(Block block) {
        return CommonConfig.DARK_MOB_SPEED.get();
    }
}
