package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.BlockEntityInit;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;

public class DarkMobHarvesterTE extends MobHarvesterTE {
    private final ArrayList<String> MOBS = new ArrayList<>();

    public DarkMobHarvesterTE(BlockPos pos, BlockState state) {
        super(BlockEntityInit.DARK_MOB_HARVESTER, pos, state);
    }

    @Override
    protected void lastMinuteActions() {
        if (getWorld() != null && !getWorld().isClient()) {
            if (this.MOBS.isEmpty()) {
                for (EntityType<?> entityType : Registries.ENTITY_TYPE) {
                    try {
                        if (entityType != null) {
                            Identifier rn = EntityType.getId(entityType);
                            ArrayList<ArrayList<String>> blacklist_mobs = CommonConfig.blacklist_mobs;
                            ArrayList<String> blacklist_mobs_mod = CommonConfig.blacklist_mobs_mod;
                            if (!Tools.isResourceBanned(rn, blacklist_mobs, blacklist_mobs_mod)) {
                                Entity entity = entityType.create(getWorld());
                                if (entity != null) {
                                    if (entity instanceof MobEntity) {
                                        if (((MobEntity) entity).isMobOrPlayer()) {
                                            this.MOBS.add(rn.toString());
                                        }
                                    }
                                    entity.discard();
                                }
                            }
                        }
                    } catch (Exception error) {
                        SpatialHarvesters.LOGGER.catching(error);
                    }
                }
            }
            if (!this.MOBS.isEmpty()) {
                this.entity = this.MOBS.get(getWorld().getRandom().nextInt(this.MOBS.size()));
            }
        }
        super.lastMinuteActions();
    }

    @Override
    public long getPrice(Block block) {
        return CommonConfig.price_dark_mob;
    }

    @Override
    public int getSpeed(Block block) {
        return CommonConfig.speed_dark_mob;
    }

    @Override
    public Item getShard(Block block) {
        if (getWorld() != null && !getWorld().isClient()) {
            int i = getWorld().getRandom().nextInt(8);
            if (i == 0) {
                return ItemInit.SHARD_1;
            } else if (i == 1) {
                return ItemInit.SHARD_2;
            } else if (i == 2) {
                return ItemInit.SHARD_3;
            } else if (i == 3) {
                return ItemInit.SHARD_4;
            } else if (i == 4) {
                return ItemInit.SHARD_5;
            } else if (i == 5) {
                return ItemInit.SHARD_6;
            } else if (i == 6) {
                return ItemInit.SHARD_7;
            }
        }
        return ItemInit.MOB_SHARD;
    }

    @Override
    protected boolean isEnabled() {
        return CommonConfig.enable_dark_mob_harvester;
    }

}
