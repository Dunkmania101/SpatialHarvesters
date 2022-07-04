package dunkmania101.spatialharvesters.objects.tile_entities.base;

import java.util.ArrayList;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.objects.blocks.HarvesterBlock;
import dunkmania101.spatialharvesters.objects.blocks.SpaceRipperBlock;
import dunkmania101.spatialharvesters.objects.tile_entities.BioHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.DarkMobHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.LootHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.OreHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.SoilHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.SpecificMobHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.StoneHarvesterTE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class SpatialHarvesterTE extends TickingRedstoneEnergyMachineTE {
    protected final ArrayList<ItemStack> OUTPUTS = new ArrayList<>();
    protected final ArrayList<String> BLACKLIST = new ArrayList<>();
    private Block thisBlock = null;

    public SpatialHarvesterTE(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state, true, true, true);
    }

    @Override
    public void tick() {
        if (this.thisBlock == null) {
            this.thisBlock = getBlockState().getBlock();
            updateEnergyStorage();
        }
        super.tick();
    }

    @Override
    public void customTickActions() {
        boolean enableOre = CommonConfig.ENABLE_ORE_HARVESTERS.get();
        boolean enableBio = CommonConfig.ENABLE_ORE_HARVESTERS.get();
        boolean enableStone = CommonConfig.ENABLE_ORE_HARVESTERS.get();
        boolean enableSoil = CommonConfig.ENABLE_ORE_HARVESTERS.get();
        boolean enableLoot = CommonConfig.ENABLE_LOOT_HARVESTER.get();
        boolean enableDarkMob = CommonConfig.ENABLE_DARK_MOB_HARVESTER.get();
        boolean enableSpecificMob = CommonConfig.ENABLE_SPECIFIC_MOB_HARVESTER.get();
        if (this instanceof OreHarvesterTE && !enableOre) {
            setActive(false);
            resetCountedTicks();
        } else if (this instanceof BioHarvesterTE && !enableBio) {
            setActive(false);
            resetCountedTicks();
        } else if (this instanceof StoneHarvesterTE && !enableStone) {
            setActive(false);
            resetCountedTicks();
        } else if (this instanceof SoilHarvesterTE && !enableSoil) {
            setActive(false);
            resetCountedTicks();
        } else if (this instanceof LootHarvesterTE && !enableLoot) {
            setActive(false);
            resetCountedTicks();
        } else if (this instanceof DarkMobHarvesterTE && !enableDarkMob) {
            setActive(false);
            resetCountedTicks();
        } else if (this instanceof SpecificMobHarvesterTE && !enableSpecificMob) {
            setActive(false);
            resetCountedTicks();
        } else {
            super.customTickActions();
            if (this.OUTPUTS.isEmpty() && !overrideSetOutputs()) {
                setOutputs(getOutputs());
            }
            if (getLevel() != null && !getLevel().isClientSide() && this.thisBlock != null) {
                if (getCountedTicks() >= getSpeed(this.thisBlock)) {
                    resetCountedTicks();
                    setActive(false);
                    int price = getPrice(this.thisBlock);
                    if (getEnergyStorage().getEnergyStored() >= price) {
                        ArrayList<Direction> spaceRippers = new ArrayList<>();
                        ArrayList<IItemHandler> outInventories = new ArrayList<>();
                        for (Direction side : Direction.values()) {
                            if (getLevel().getBlockState(getBlockPos().relative(side)).getBlock() instanceof SpaceRipperBlock) {
                                spaceRippers.add(side);
                            } else {
                                BlockEntity out = getLevel().getBlockEntity(getBlockPos().relative(side));
                                if (out != null) {
                                    LazyOptional<IItemHandler> outCap = out.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side.getOpposite());
                                    outCap.ifPresent(outInventories::add);
                                }
                            }
                        }
                        if (!spaceRippers.isEmpty() && !outInventories.isEmpty()) {
                            lastMinuteActions();
                            if (!this.OUTPUTS.isEmpty()) {
                                filterOutputsMinTier(thisBlock);
                                for (Direction ignored : spaceRippers) {
                                    if (getEnergyStorage().getEnergyStored() >= price) {
                                        ItemStack chosenOutput;
                                        RandomSource rand = getLevel().getRandom();
                                        int shardChance = CommonConfig.HARVESTER_SHARD_CHANCE.get();
                                        if (shardChance > 0 && rand.nextInt(shardChance) == 1) {
                                            chosenOutput = new ItemStack(getShard(this.thisBlock));
                                        } else {
                                            chosenOutput = this.OUTPUTS.get(rand.nextInt(this.OUTPUTS.size())).copy();
                                        }
                                        if (!chosenOutput.isEmpty()) {
                                            ResourceLocation itemRN = ForgeRegistries.ITEMS.getKey(chosenOutput.getItem());
                                            if (itemRN != null) {
                                                if (this.BLACKLIST.contains(itemRN.toString())) {
                                                    getEnergyStorage().consumeEnergy(price);
                                                    setActive(true);
                                                } else {
                                                    int originalCount = chosenOutput.getCount();
                                                    IItemHandler inventory = outInventories.get(rand.nextInt(outInventories.size()));
                                                    ItemStack resultStack = ItemHandlerHelper.insertItemStacked(inventory, chosenOutput, false);
                                                    if (resultStack.getCount() != originalCount) {
                                                        getEnergyStorage().consumeEnergy(price);
                                                        setActive(true);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected void lastMinuteActions() {
    }

    public void setOutputs(ArrayList<Item> OUTPUTS) {
        this.OUTPUTS.clear();
        for (Item item : OUTPUTS) {
            ItemStack stack = new ItemStack(item);
            if (!stack.isEmpty()) {
                this.OUTPUTS.add(stack);
            }
        }
    }

    public void setOutputStacks(ArrayList<ItemStack> OUTPUTS) {
        this.OUTPUTS.clear();
        this.OUTPUTS.addAll(OUTPUTS);
    }

    public ArrayList<Item> getOutputs() {
        return new ArrayList<>();
    }

    public void filterOutputsMinTier(Block block) {
        for (ArrayList<String> itemTier : getMinTierItems()) {
            if (itemTier.size() >= 3) {
                int tier = Integer.getInteger(itemTier.get(2), 0);
                if (getTier(block) < tier) {
                    ResourceLocation itemRN = new ResourceLocation(itemTier.get(0), itemTier.get(1));
                    this.OUTPUTS.removeIf(stack -> ForgeRegistries.ITEMS.getKey(stack.getItem()) != null && ForgeRegistries.ITEMS.getKey(stack.getItem()).toString().equals(itemRN.toString()));
                }
            }
        }
    }

    public ArrayList<ArrayList<String>> getMinTierItems() {
        return new ArrayList<>();
    }

    public int getTier(Block block) {
        if (block instanceof HarvesterBlock) {
            return ((HarvesterBlock) block).getTier();
        }
        return 1;
    }

    @Override
    protected int getCapacity() {
        if (this.thisBlock != null) {
            int price = getPrice(this.thisBlock);
            int multiplier = CommonConfig.HARVESTER_CAPACITY_MULTIPLIER.get();
            if (Integer.MAX_VALUE / multiplier < price) {
                return Integer.MAX_VALUE;
            }
            return price * multiplier;
        }
        return Integer.MAX_VALUE;
    }

    @Override
    protected int getMaxInput() {
        return getCapacity();
    }

    @Override
    protected int getMaxExtract() {
        return getCapacity();
    }

    public int getPrice(Block block) {
        return Integer.MAX_VALUE;
    }

    public int getSpeed(Block block) {
        return Integer.MAX_VALUE;
    }

    public Item getShard(Block block) {
        int tier = getTier(block);
        Item CHOSEN_SHARD = ItemInit.SHARD_1.get();
        if (tier == 2) {
            CHOSEN_SHARD = ItemInit.SHARD_2.get();
        } else if (tier == 3) {
            CHOSEN_SHARD = ItemInit.SHARD_3.get();
        } else if (tier == 4) {
            CHOSEN_SHARD = ItemInit.SHARD_4.get();
        } else if (tier == 5) {
            CHOSEN_SHARD = ItemInit.SHARD_5.get();
        } else if (tier == 6) {
            CHOSEN_SHARD = ItemInit.SHARD_6.get();
        } else if (tier == 7) {
            CHOSEN_SHARD = ItemInit.SHARD_7.get();
        } else if (tier == 8) {
            CHOSEN_SHARD = ItemInit.SHARD_7.get();
        }
        return CHOSEN_SHARD;
    }

    public boolean overrideSetOutputs() {
        return false;
    }

    @Override
    public CompoundTag saveSerializedValues() {
        CompoundTag nbt = super.saveSerializedValues();
        CompoundTag disabledResources = new CompoundTag();
        int i = 0;
        for (String itemRN : this.BLACKLIST) {
            if (itemRN != null) {
                disabledResources.putString(Integer.toString(i), itemRN);
                i++;
            }
        }
        if (!disabledResources.isEmpty()) {
            nbt.put(CustomValues.disabledResourcesKey, disabledResources);
        }
        return nbt;
    }

    @Override
    public void setDeserializedValues(CompoundTag nbt) {
        super.setDeserializedValues(nbt);
        if (nbt.contains(CustomValues.removeDisabledNBTKey)) {
            this.BLACKLIST.clear();
        }
        if (nbt.contains(CustomValues.disabledResourcesKey)) {
            this.BLACKLIST.clear();
            CompoundTag disabledResources = nbt.getCompound(CustomValues.disabledResourcesKey);
            for (String key : disabledResources.getAllKeys()) {
                String itemRN = disabledResources.getString(key);
                ResourceLocation airRN = ForgeRegistries.ITEMS.getKey(Items.AIR);
                if (airRN != null && !itemRN.equals(airRN.toString()) && !this.BLACKLIST.contains(itemRN)) {
                    this.BLACKLIST.add(itemRN);
                }
            }
        }
        if (nbt.contains(CustomValues.disabledResourceKey)) {
            String itemRN = nbt.getString(CustomValues.disabledResourceKey);
            ResourceLocation airRN = ForgeRegistries.ITEMS.getKey(Items.AIR);
            if (airRN != null && !itemRN.equals(airRN.toString()) && !this.BLACKLIST.contains(itemRN)) {
                this.BLACKLIST.add(itemRN);
            }
        }
    }
}
