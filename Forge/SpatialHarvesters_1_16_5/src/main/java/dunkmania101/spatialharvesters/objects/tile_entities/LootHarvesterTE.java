package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.util.Tools;
import dunkmania101.spatialharvesters.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;

public class LootHarvesterTE extends SpatialHarvesterTE {
    public LootHarvesterTE() {
        super(TileEntityInit.LOOT_HARVESTER.get());
    }

    @Override
    protected void lastMinuteActions() {
        ArrayList<ItemStack> newOutputs = new ArrayList<>();
        if (getWorld() != null && getWorld() instanceof ServerWorld) {
            MinecraftServer server = getWorld().getServer();
            if (server != null) {
                for (ArrayList<String> rnString : CommonConfig.CUSTOM_LOOT_TABLES.get()) {
                    if (rnString.size() >= 2) {
                        ResourceLocation rn = new ResourceLocation(rnString.get(0), rnString.get(1));
                        LootContext context = new LootContext.Builder((ServerWorld) getWorld()).build(LootParameterSets.EMPTY);
                        server.getLootTableManager().getLootTableFromLocation(rn).generate(context).stream()
                                .filter(stack -> !stack.isEmpty() && !Tools.isResourceBanned(stack.getItem().getRegistryName(),
                                        CommonConfig.BLACKLIST_LOOT.get(),
                                        CommonConfig.BLACKLIST_LOOT_MOD.get()))
                                .forEach(newOutputs::add);
                    }
                }
            }
        }
        setOutputStacks(newOutputs);
    }

    @Override
    public int getPrice(Block block) {
        return CommonConfig.LOOT_PRICE.get();
    }

    @Override
    public int getSpeed(Block block) {
        return CommonConfig.LOOT_SPEED.get();
    }

    @Override
    public int getTier(Block block) {
        return 8;
    }

    @Override
    public boolean overrideSetOutputs() {
        return true;
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
