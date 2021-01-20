package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.objects.blocks.SpaceRipperBlock;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.Random;

public class SpatialHarvesterTE extends TickingRedstoneEnergyMachineTE {
    protected final ArrayList<ItemStack> OUTPUTS = new ArrayList<>();
    protected ArrayList<String> BLACKLIST = new ArrayList<>();
    private Block thisBlock = null;

    public SpatialHarvesterTE(BlockEntityType<?> blockEntityType) {
        super(blockEntityType, true, true, true);
    }

    @Override
    public void tick() {
        this.thisBlock = getCachedState().getBlock();
        super.tick();
    }

    @Override
    public void customTickActions() {
        boolean enableOre = CommonConfig.enable_ore_harvesters;
        boolean enableBio = CommonConfig.enable_ore_harvesters;
        boolean enableStone = CommonConfig.enable_ore_harvesters;
        boolean enableSoil = CommonConfig.enable_ore_harvesters;
        boolean enableDarkMob = CommonConfig.enable_dark_mob_harvester;
        boolean enableSpecificMob = CommonConfig.enable_specific_mob_harvester;
        if (this instanceof OreHarvesterTE && !enableOre) {
            setActive(false);
        } else if (this instanceof BioHarvesterTE && !enableBio) {
            setActive(false);
        } else if (this instanceof StoneHarvesterTE && !enableStone) {
            setActive(false);
        } else if (this instanceof SoilHarvesterTE && !enableSoil) {
            setActive(false);
        } else if (this instanceof DarkMobHarvesterTE && !enableDarkMob) {
            setActive(false);
        } else if (this instanceof SpecificMobHarvesterTE && !enableSpecificMob) {
            setActive(false);
        } else {
            if (this.OUTPUTS.isEmpty()) {
                setOutputs(getOutputs());
            }
            if (getWorld() != null && !getWorld().isClient && this.thisBlock != null) {
                if (getCountedTicks() >= getSpeed(this.thisBlock)) {
                    resetCountedTicks();
                    setActive(false);
                    double price = getPrice(this.thisBlock);
                    if (getEnergyStorage().getEnergy() >= price) {
                        ArrayList<Direction> spaceRippers = new ArrayList<>();
                        ArrayList<Inventory> outInventories = new ArrayList<>();
                        for (Direction side : Direction.values()) {
                            if (getWorld().getBlockState(getPos().offset(side)).getBlock() instanceof SpaceRipperBlock) {
                                spaceRippers.add(side);
                            } else {
                                BlockEntity out = getWorld().getBlockEntity(getPos().offset(side));
                                if (out != null) {
                                    if (out instanceof Inventory) {
                                        Inventory inventory = (Inventory) out;
                                        outInventories.add(inventory);
                                    }
                                }
                            }
                        }
                        if (!spaceRippers.isEmpty() && !outInventories.isEmpty()) {
                            lastMinuteActions();
                            if (!this.OUTPUTS.isEmpty()) {
                                filterOutputsMinTier(thisBlock);
                                for (Direction ignored : spaceRippers) {
                                    if (getEnergyStorage().getEnergy() >= price) {
                                        ItemStack chosenOutput;
                                        Random rand = getWorld().getRandom();
                                        int shardChance = CommonConfig.harvester_shard_chance;
                                        if (rand.nextInt(shardChance) == 1) {
                                            chosenOutput = new ItemStack(getShard(this.thisBlock));
                                        } else {
                                            chosenOutput = this.OUTPUTS.get(rand.nextInt(this.OUTPUTS.size())).copy();
                                        }
                                        if (!chosenOutput.isEmpty()) {
                                            if (this.BLACKLIST.contains(chosenOutput.getItem().getTranslationKey())) {
                                                getEnergyStorage().extract(price);
                                                setActive(true);
                                            } else {
                                                int originalCount = chosenOutput.getCount();
                                                Inventory inventory = outInventories.get(rand.nextInt(outInventories.size()));
                                                ItemStack resultStack = Tools.insertItemStacked(inventory, chosenOutput);
                                                if (resultStack.getCount() != originalCount) {
                                                    getEnergyStorage().extract(price);
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
        ArrayList<ArrayList<String>> minTierItems = getMinTierItems();
        for (ArrayList<String> itemTier : minTierItems) {
            if (itemTier.size() >= 3) {
                int tier = 0;
                try {
                    tier = Integer.parseInt(itemTier.get(2));
                } catch (NumberFormatException | NullPointerException ignored) {
                }
                if (getTier(block) < tier) {
                    Identifier itemRN = new Identifier(itemTier.get(0), itemTier.get(1));
                    this.OUTPUTS.removeIf(stack -> stack.getItem().getTranslationKey() != null && stack.getItem().getTranslationKey().equals(itemRN.toString()));
                }
            }
        }
    }

    public ArrayList<ArrayList<String>> getMinTierItems() {
        return new ArrayList<>();
    }

    public int getTier(Block block) {
        return 1;
    }

    @Override
    public double getMaxStoredPower() {
        if (this.thisBlock != null) {
            int multiplier = CommonConfig.harvester_capacity_multiplier;
            return getPrice(this.thisBlock) * multiplier;
        }
        return Double.MAX_VALUE;
    }

    @Override
    public double getCustomMaxInput() {
        return getMaxStoredPower();
    }

    @Override
    public double getCustomMaxOutput() {
        return getMaxStoredPower();
    }

    public double getPrice(Block block) {
        return Double.MAX_VALUE;
    }

    public double getSpeed(Block block) {
        return Double.MAX_VALUE;
    }

    public Item getShard(Block block) {
        return ItemInit.SHARD_1;
    }

    @Override
    public CompoundTag saveSerializedValues() {
        CompoundTag nbt = super.saveSerializedValues();
        CompoundTag disabledResources = new CompoundTag();
        int i = 0;
        for (String rn : this.BLACKLIST) {
            disabledResources.putString(Integer.toString(i), rn);
            i++;
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
            for (String key : disabledResources.getKeys()) {
                String airRN = Items.AIR.getTranslationKey();
                if (!key.equals(airRN) && !this.BLACKLIST.contains(key)) {
                    this.BLACKLIST.add(key);
                }
            }
        }
        if (nbt.contains(CustomValues.disabledResourceKey)) {
            String airRN = Items.AIR.getTranslationKey();
            String key = nbt.getString(CustomValues.disabledResourceKey);
            if (!key.equals(airRN) && !this.BLACKLIST.contains(key)) {
                this.BLACKLIST.add(key);
            }
        }
    }
}
