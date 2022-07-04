package dunkmania101.spatialharvesters.objects.tile_entities;

import java.util.ArrayList;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class DarkMobHarvesterTE extends MobHarvesterTE {
    private final ArrayList<String> MOBS = new ArrayList<>();

    public DarkMobHarvesterTE(BlockPos pos, BlockState state) {
        super(TileEntityInit.DARK_MOB_HARVESTER.get(), pos, state);
    }

    @Override
    protected void lastMinuteActions() {
        if (getLevel() != null && !getLevel().isClientSide()) {
            if (this.MOBS.isEmpty()) {
                for (EntityType<?> entityType : ForgeRegistries.ENTITIES.getValues()) {
                    try {
                        if (entityType != null) {
                            ArrayList<ArrayList<String>> blacklist_mobs = CommonConfig.BLACKLIST_MOBS.get();
                            ArrayList<String> blacklist_mobs_mod = CommonConfig.BLACKLIST_MOBS_MOD.get();
                            ResourceLocation rn = ForgeRegistries.ENTITIES.getKey(entityType);
                            if (rn != null) {
                                if (!Tools.isResourceBanned(rn, blacklist_mobs, blacklist_mobs_mod)) {
                                    Entity entity = entityType.create(getLevel());
                                    if (entity != null) {
                                        if (entity instanceof Mob) {
                                            // if (((Mob) entity).isNonBoss()) { // Did they remove that method?
                                                this.MOBS.add(rn.toString());
                                            // }
                                        }
                                        entity.onRemovedFromWorld();
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
                RandomSource rand = getLevel().getRandom();
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
        if (getLevel() != null && !getLevel().isClientSide()) {
            int i = getLevel().getRandom().nextInt(8);
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
