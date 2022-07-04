package dunkmania101.spatialharvesters.objects.tile_entities;

import java.util.ArrayList;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.objects.tile_entities.base.SpatialHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.ForgeRegistries;

public class LootHarvesterTE extends SpatialHarvesterTE {
    public LootHarvesterTE(BlockPos pos, BlockState state) {
        super(TileEntityInit.LOOT_HARVESTER.get(), pos, state);
    }

    @Override
    protected void lastMinuteActions() {
        ArrayList<ItemStack> newOutputs = new ArrayList<>();
        if (getLevel() != null && getLevel() instanceof ServerLevel) {
            MinecraftServer server = getLevel().getServer();
            if (server != null) {
                for (ArrayList<String> rnString : CommonConfig.CUSTOM_LOOT_TABLES.get()) {
                    if (rnString.size() >= 2) {
                        ResourceLocation rn = new ResourceLocation(rnString.get(0), rnString.get(1));
                        LootContext context = new LootContext.Builder((ServerLevel) getLevel()).create(LootContextParamSets.EMPTY);
                        server.getLootTables().get(rn).getRandomItems(context).stream()
                                .filter(stack -> !stack.isEmpty() && !Tools.isResourceBanned(ForgeRegistries.ITEMS.getKey(stack.getItem()),
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
