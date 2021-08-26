package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.util.Tools;
import dunkmania101.spatialharvesters.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.Item;
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
        if (getWorld() != null && !getWorld().isRemote()) {
            if (this.MOBS.isEmpty()) {
                for (EntityType<?> entityType : ForgeRegistries.ENTITIES.getValues()) {
                    try {
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
                    } catch (Exception error) {
                        SpatialHarvesters.LOGGER.catching(error);
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
    public int getPrice(Block block) {
        return CommonConfig.DARK_MOB_PRICE.get();
    }

    @Override
    public int getSpeed(Block block) {
        return CommonConfig.DARK_MOB_SPEED.get();
    }

    @Override
    public Item getShard(Block block) {
        if (getWorld() != null && !getWorld().isRemote()) {
            int i = getWorld().getRandom().nextInt(8);
            if (i == 0) {
                return ItemInit.SHARD_1.get();
            } else if (i == 1) {
                return ItemInit.SHARD_2.get();
            } else if (i == 2) {
                return ItemInit.SHARD_3.get();
            } else if (i == 3) {
                return ItemInit.SHARD_4.get();
            } else if (i == 4) {
                return ItemInit.SHARD_5.get();
            } else if (i == 5) {
                return ItemInit.SHARD_6.get();
            } else if (i == 6) {
                return ItemInit.SHARD_7.get();
            }
        }
        return ItemInit.MOB_SHARD.get();
    }
}
