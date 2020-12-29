package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Random;

public class DarkMobHarvesterTE extends MobHarvesterTE {
    private final ArrayList<String> MOBS = new ArrayList<>();

    public DarkMobHarvesterTE() {
        super(TileEntityInit.DARK_MOB_HARVESTER);
    }

    @Override
    protected void lastMinuteActions() {
        if (getWorld() != null && !getWorld().isClient) {
            if (this.MOBS.isEmpty()) {
                if (getWorld() != null && !getWorld().isClient) {
                    for (EntityType<?> entityType : Registry.ENTITY_TYPE) {
                        try {
                            if (entityType != null) {
                                ArrayList<ArrayList<String>> blacklist_mobs = CommonConfig.blacklist_mobs;
                                ArrayList<String> blacklist_mobs_mod = CommonConfig.blacklist_mobs_mod;
                                Identifier rn = Identifier.tryParse(entityType.getTranslationKey());
                                if (rn != null) {
                                    if (!Tools.isResourceBanned(rn, blacklist_mobs, blacklist_mobs_mod)) {
                                        Entity entity = entityType.create(getWorld());
                                        if (entity != null) {
                                            if (entity instanceof MobEntity) {
                                                if (((MobEntity) entity).isMobOrPlayer()) {
                                                    this.MOBS.add(rn.toString());
                                                }
                                            }
                                            entity.remove();
                                        }
                                    }
                                }
                            }
                        } catch (Exception error) {
                            SpatialHarvesters.LOGGER.catching(error);
                        }
                    }
                }
            }
            if (!this.MOBS.isEmpty()) {
                Random rand = getWorld().getRandom();
                this.entity = this.MOBS.get(rand.nextInt(this.MOBS.size()));
            }
        }
        super.lastMinuteActions();
    }

    @Override
    public double getPrice(Block block) {
        return CommonConfig.price_dark_mob;
    }

    @Override
    public double getSpeed(Block block) {
        return CommonConfig.speed_dark_mob;
    }
}
