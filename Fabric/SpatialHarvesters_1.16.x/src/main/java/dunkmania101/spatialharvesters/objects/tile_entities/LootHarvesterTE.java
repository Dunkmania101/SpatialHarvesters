package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.BlockEntityInit;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

public class LootHarvesterTE extends SpatialHarvesterTE {
    public LootHarvesterTE() {
        super(BlockEntityInit.LOOT_HARVESTER);
    }

    @Override
    protected void lastMinuteActions() {
        ArrayList<ItemStack> newOutputs = new ArrayList<>();
        if (getWorld() != null && getWorld() instanceof ServerWorld) {
            MinecraftServer server = getWorld().getServer();
            if (server != null) {
                for (ArrayList<String> rnString : CommonConfig.custom_loot_tables) {
                    if (rnString.size() >= 2) {
                        Identifier rn = new Identifier(rnString.get(0), rnString.get(1));
                        LootContext context = new LootContext.Builder((ServerWorld) getWorld()).build(LootContextTypes.EMPTY);
                        server.getLootManager().getTable(rn).generateLoot(context).stream()
                                .filter(stack -> !stack.isEmpty() && !Tools.isResourceBanned(Registry.ITEM.getId(stack.getItem()),
                                        CommonConfig.blacklist_loot,
                                        CommonConfig.blacklist_loot_mod))
                                .forEach(newOutputs::add);
                    }
                }
            }
        }
        setOutputStacks(newOutputs);
    }

    @Override
    public double getPrice(Block block) {
        return CommonConfig.price_loot;
    }

    @Override
    public double getSpeed(Block block) {
        return CommonConfig.speed_loot;
    }

    @Override
    public int getTier(Block block) {
        return 8;
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
}
