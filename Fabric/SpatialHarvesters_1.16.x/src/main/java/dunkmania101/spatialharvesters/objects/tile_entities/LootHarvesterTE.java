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
                                .filter(stack -> !stack.isEmpty() && !Tools.isResourceBanned(Identifier.tryParse(stack.getItem().getTranslationKey()),
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
        return ItemInit.SHARD_7;
    }
}
