package dunkmania101.spatialharvesters.objects.tile_entities.base;

import java.util.ArrayList;
import java.util.Random;

import dunkmania101.spatialharvesters.data.CommonConfig;
import dunkmania101.spatialharvesters.data.CustomValues;
import dunkmania101.spatialharvesters.init.ItemInit;
import dunkmania101.spatialharvesters.objects.blocks.SpaceRipperBlock;
import dunkmania101.spatialharvesters.objects.tile_entities.BioHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.DarkMobHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.MobHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.OreHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.SoilHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.SpecificMobHarvesterTE;
import dunkmania101.spatialharvesters.objects.tile_entities.StoneHarvesterTE;
import dunkmania101.spatialharvesters.util.Tools;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class SpatialHarvesterTE extends TickingRedstoneEnergyMachineTE {
    protected final ArrayList<ItemStack> OUTPUTS = new ArrayList<>();
    protected ArrayList<String> BLACKLIST = new ArrayList<>();
    public Block thisBlock = null;

    public SpatialHarvesterTE(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state, true, true, true);
    }

    public static void tick(World world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (blockEntity instanceof SpatialHarvesterTE) {
            ((SpatialHarvesterTE) blockEntity).thisBlock = state.getBlock();
        }
        TickingRedstoneEnergyMachineTE.tick(world, pos, state, blockEntity);
    }

    @Override
    public void customTickActions() {
        boolean enableOre = CommonConfig.enable_ore_harvesters;
        boolean enableBio = CommonConfig.enable_bio_harvesters;
        boolean enableStone = CommonConfig.enable_stone_harvesters;
        boolean enableSoil = CommonConfig.enable_soil_harvesters;
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
            if (getWorld() != null && !getWorld().isClient() && this.thisBlock != null) {
                if (getCountedTicks() >= getSpeed(this.thisBlock)) {
                    resetCountedTicks();
                    setActive(false);
                    long price = getPrice(this.thisBlock);
                    if (getAmount() >= price) {
                        ArrayList<Direction> spaceRippers = new ArrayList<>();
                        ArrayList<Inventory> outInventories = new ArrayList<>();
                        for (Direction side : Direction.values()) {
                            if (getWorld().getBlockState(getPos().offset(side)).getBlock() instanceof SpaceRipperBlock) {
                                spaceRippers.add(side);
                            } else {
                                BlockEntity out = getWorld().getBlockEntity(getPos().offset(side));
                                if (out != null) {
                                    if (out instanceof Inventory inventory) {
                                        outInventories.add(inventory);
                                    }
                                }
                            }
                        }
                        if (!spaceRippers.isEmpty() && !outInventories.isEmpty()) {
                            lastMinuteActions();
                            if (!this.OUTPUTS.isEmpty()) {
                                filterOutputsMinTier(this.thisBlock);
                                for (Direction ignored : spaceRippers) {
                                    if (getAmount() >= price) {
                                        ItemStack chosenOutput;
                                        Random rand = getWorld().getRandom();
                                        int shardChance = CommonConfig.harvester_shard_chance;
                                        if (this instanceof MobHarvesterTE) {
                                            shardChance = CommonConfig.mob_harvester_mob_shard_chance;
                                        }
                                        if (shardChance > 0 && rand.nextInt(shardChance) == 1) {
                                            chosenOutput = new ItemStack(getShard(this.thisBlock));
                                        } else {
                                            chosenOutput = this.OUTPUTS.get(rand.nextInt(this.OUTPUTS.size())).copy();
                                        }
                                        if (!chosenOutput.isEmpty()) {
                                            if (this.BLACKLIST.contains(Registry.ITEM.getId(chosenOutput.getItem()).toString())) {
                                                extract(price);
                                                setActive(true);
                                            } else {
                                                int originalCount = chosenOutput.getCount();
                                                Inventory inventory = outInventories.get(rand.nextInt(outInventories.size()));
                                                ItemStack resultStack = Tools.insertItemStacked(inventory, chosenOutput);
                                                if (resultStack.getCount() != originalCount) {
                                                    extract(price);
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
                int tier = Integer.getInteger(itemTier.get(2), 0);
                if (getTier(block) < tier) {
                    Identifier itemRN = new Identifier(itemTier.get(0), itemTier.get(1));
                    this.OUTPUTS.removeIf(stack -> Registry.ITEM.getId(stack.getItem()).equals(itemRN));
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
    public long getCapacity() {
        if (this.thisBlock != null) {
            int multiplier = CommonConfig.harvester_capacity_multiplier;
            return getPrice(this.thisBlock) * multiplier;
        }
        return Long.MAX_VALUE;
    }

    public long getPrice(Block block) {
        return Long.MAX_VALUE;
    }

    public int getSpeed(Block block) {
        return Integer.MAX_VALUE;
    }

    public Item getShard(Block block) {
        return ItemInit.SHARD_1;
    }

    @Override
    public NbtCompound saveSerializedValues() {
        NbtCompound nbt = super.saveSerializedValues();
        if (!this.BLACKLIST.isEmpty()) {
            NbtCompound disabledResources = new NbtCompound();
            int i = 0;
            for (String rn : this.BLACKLIST) {
                disabledResources.putString(Integer.toString(i), rn);
                i++;
            }
            if (!disabledResources.isEmpty()) {
                nbt.put(CustomValues.disabledResourcesKey, disabledResources);
            }
        }
        return nbt;
    }

    @Override
    public void setDeserializedValues(NbtCompound nbt) {
        super.setDeserializedValues(nbt);
        if (nbt.contains(CustomValues.removeDisabledNBTKey)) {
            this.BLACKLIST.clear();
        }
        if (nbt.contains(CustomValues.disabledResourcesKey)) {
            this.BLACKLIST.clear();
            NbtCompound disabledResources = nbt.getCompound(CustomValues.disabledResourcesKey);
            for (String key : disabledResources.getKeys()) {
                String airRN = Items.AIR.toString();
                String rn = disabledResources.getString(key);
                if (!key.equals(airRN) && !this.BLACKLIST.contains(rn)) {
                    this.BLACKLIST.add(rn);
                }
            }
        }
        if (nbt.contains(CustomValues.disabledResourceKey)) {
            String airRN = Items.AIR.toString();
            String rn = nbt.getString(CustomValues.disabledResourceKey);
            if (!rn.equals(airRN) && !this.BLACKLIST.contains(rn)) {
                this.BLACKLIST.add(rn);
            }
        }
    }
}
