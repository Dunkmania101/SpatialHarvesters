package dunkmania101.spatialharvesters.objects.tile_entities;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
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
    protected ArrayList<ItemStack> OUTPUTS = new ArrayList<>();
    protected ArrayList<Item> BLACKLIST = new ArrayList<>();
    private Block thisBlock = null;

    public SpatialHarvesterTE(BlockEntityType<?> blockEntityType, ArrayList<Item> OUTPUTS) {
        super(blockEntityType, true, true, true);

        setOutputs(OUTPUTS);
    }

    @Override
    public void tick() {
        this.thisBlock = getCachedState().getBlock();
        updateEnergyStorage();
        super.tick();
    }

    @Override
    public void customTickActions() {
        boolean enableOre = CommonConfig.ENABLE_ORE_HARVESTERS.get();
        boolean enableBio = CommonConfig.ENABLE_ORE_HARVESTERS.get();
        boolean enableStone = CommonConfig.ENABLE_ORE_HARVESTERS.get();
        boolean enableSoil = CommonConfig.ENABLE_ORE_HARVESTERS.get();
        boolean enableDarkMob = CommonConfig.ENABLE_DARK_MOB_HARVESTER.get();
        boolean enableSpecificMob = CommonConfig.ENABLE_SPECIFIC_MOB_HARVESTER.get();
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
            super.customTickActions();
            if (getWorld() != null && !getWorld().isClient && this.thisBlock != null) {
                if (getCountedTicks() >= getSpeed(this.thisBlock)) {
                    resetCountedTicks();
                    setActive(false);
                    int price = getPrice(this.thisBlock);
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
                                for (Direction ignored : spaceRippers) {
                                    if (getEnergyStorage().getEnergy() >= price) {
                                        ItemStack chosenOutput;
                                        Random rand = getWorld().getRandom();
                                        int shardChance = CommonConfig.HARVESTER_SHARD_CHANCE.get();
                                        if (rand.nextInt(shardChance) == 1) {
                                            chosenOutput = new ItemStack(getShard(this.thisBlock));
                                        } else {
                                            chosenOutput = this.OUTPUTS.get(rand.nextInt(this.OUTPUTS.size())).copy();
                                        }
                                        if (!chosenOutput.isEmpty()) {
                                            if (this.BLACKLIST.contains(chosenOutput.getItem())) {
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
        this.OUTPUTS = new ArrayList<>();
        for (Item item : OUTPUTS) {
            ItemStack stack = new ItemStack(item);
            if (!stack.isEmpty()) {
                this.OUTPUTS.add(stack);
            }
        }
    }

    public void setOutputStacks(ArrayList<ItemStack> OUTPUTS) {
        this.OUTPUTS = OUTPUTS;
    }

    @Override
    protected int getCapacity() {
        if (this.thisBlock != null) {
            int multiplier = CommonConfig.HARVESTER_CAPACITY_MULTIPLIER.get();
            return getPrice(this.thisBlock) * multiplier;
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
        return ItemInit.SHARD_1.get();
    }

    @Override
    public CompoundTag saveSerializedValues() {
        CompoundTag nbt = super.saveSerializedValues();
        CompoundTag disabledResources = new CompoundTag();
        int i = 0;
        for (Item item : this.BLACKLIST) {
            String rn = item.getTranslationKey();
            if (rn != null) {
                disabledResources.putString(Integer.toString(i), rn);
                i++;
            }
        }
        if (!disabledResources.isEmpty()) {
            nbt.put(CustomValues.disabledResourcesKey, disabledResources);
        }
        return nbt;
    }

    @Override
    public void setDeserializedValues(CompoundNBT nbt) {
        super.setDeserializedValues(nbt);
        if (nbt.contains(CustomValues.removeDisabledNBTKey)) {
            this.BLACKLIST = new ArrayList<>();
        }
        if (nbt.contains(CustomValues.disabledResourcesKey)) {
            this.BLACKLIST = new ArrayList<>();
            CompoundNBT disabledResources = nbt.getCompound(CustomValues.disabledResourcesKey);
            for (String key : disabledResources.keySet()) {
                Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(disabledResources.getString(key)));
                if (item != null && item != Items.AIR) {
                    this.BLACKLIST.add(item);
                }
            }
        }
        if (nbt.contains(CustomValues.disabledResourceKey)) {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(nbt.getString(CustomValues.disabledResourceKey)));
            if (item != null && item != Items.AIR) {
                this.BLACKLIST.add(item);
            }
        }
    }
}
