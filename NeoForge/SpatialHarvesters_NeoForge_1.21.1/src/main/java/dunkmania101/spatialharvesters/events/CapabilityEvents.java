package dunkmania101.spatialharvesters.events;

import java.util.List;

import dunkmania101.spatialharvesters.SpatialHarvesters;
import dunkmania101.spatialharvesters.init.TileEntityInit;
import dunkmania101.spatialharvesters.objects.tile_entities.base.CustomEnergyMachineTE;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

@EventBusSubscriber(modid = SpatialHarvesters.modid, bus = EventBusSubscriber.Bus.MOD)
public class CapabilityEvents {
    private static final List<DeferredHolder<BlockEntityType<?>, ? extends BlockEntityType<? extends CustomEnergyMachineTE>>> ENERGY_MACHINES = List.of(
            TileEntityInit.ORE_HARVESTER,
            TileEntityInit.BIO_HARVESTER,
            TileEntityInit.STONE_HARVESTER,
            TileEntityInit.SOIL_HARVESTER,
            TileEntityInit.LOOT_HARVESTER,
            TileEntityInit.DARK_MOB_HARVESTER,
            TileEntityInit.SPECIFIC_MOB_HARVESTER,
            TileEntityInit.HEAT_GENERATOR,
            TileEntityInit.DIMENSIONAL_APPLICATOR);

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        for (var holder : ENERGY_MACHINES) {
            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, holder.get(),
                    (machine, side) -> machine.getEnergyStorage());
        }
    }
}
