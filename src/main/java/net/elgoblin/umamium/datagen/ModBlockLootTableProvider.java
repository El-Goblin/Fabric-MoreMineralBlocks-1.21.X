package net.elgoblin.umamium.datagen;

import net.elgoblin.umamium.block.ModBlocks;
import net.elgoblin.umamium.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        generateLootTablesForSet(ModBlocks.GOLD_SET, false);
        generateLootTablesForSet(ModBlocks.GOLD_BRICKS_SET, true);
        generateLootTablesForSet(ModBlocks.CALCIFIED_GOLD_SET, true);
        generateLootTablesForSet(ModBlocks.POLISHED_GOLD_SET, true);

        generateLootTablesForSet(ModBlocks.DIAMOND_SET, false);
        generateLootTablesForSet(ModBlocks.DIAMOND_BRICKS_SET, true);
        generateLootTablesForSet(ModBlocks.CALCIFIED_DIAMOND_SET, true);
        generateLootTablesForSet(ModBlocks.POLISHED_DIAMOND_SET, true);

        generateLootTablesForSet(ModBlocks.IRON_SET, false);
        generateLootTablesForSet(ModBlocks.IRON_BRICKS_SET, true);
        generateLootTablesForSet(ModBlocks.CALCIFIED_IRON_SET, true);
        generateLootTablesForSet(ModBlocks.POLISHED_IRON_SET, true);

        generateLootTablesForSet(ModBlocks.EMERALD_SET, false);
        generateLootTablesForSet(ModBlocks.EMERALD_BRICKS_SET, true);
        generateLootTablesForSet(ModBlocks.CALCIFIED_EMERALD_SET, true);
        generateLootTablesForSet(ModBlocks.POLISHED_EMERALD_SET, true);
        generateLootTablesForSet(ModBlocks.CHISELED_EMERALD_SET, false);

        generateLootTablesForSet(ModBlocks.AMETHYST_SET, false);
        generateLootTablesForSet(ModBlocks.AMETHYST_BRICKS_SET, true);
        generateLootTablesForSet(ModBlocks.CALCIFIED_AMETHYST_SET, true);
        generateLootTablesForSet(ModBlocks.POLISHED_AMETHYST_SET, true);

        generateLootTablesForSet(ModBlocks.LAPIS_SET, false);
        generateLootTablesForSet(ModBlocks.LAPIS_BRICKS_SET, true);
        generateLootTablesForSet(ModBlocks.CALCIFIED_LAPIS_SET, true);
        generateLootTablesForSet(ModBlocks.POLISHED_LAPIS_SET, true);

        generateLootTablesForSet(ModBlocks.COAL_SET, false);
        generateLootTablesForSet(ModBlocks.COAL_BRICKS_SET, true);
        generateLootTablesForSet(ModBlocks.CALCIFIED_COAL_SET, true);
        generateLootTablesForSet(ModBlocks.POLISHED_COAL_SET, true);

        generateLootTablesForSet(ModBlocks.NETHERITE_SET, false);
        generateLootTablesForSet(ModBlocks.NETHERITE_BRICKS_SET, true);
        generateLootTablesForSet(ModBlocks.CALCIFIED_NETHERITE_SET, true);
        generateLootTablesForSet(ModBlocks.POLISHED_NETHERITE_SET, true);

        generateLootTablesForSet(ModBlocks.REDSTONE_SET, false);
        generateLootTablesForSet(ModBlocks.REDSTONE_BRICKS_SET, true);
        generateLootTablesForSet(ModBlocks.CALCIFIED_REDSTONE_SET, true);
        generateLootTablesForSet(ModBlocks.POLISHED_REDSTONE_SET, true);

        generateLootTablesForSet(ModBlocks.OBSIDIAN_SET, false);
        generateLootTablesForSet(ModBlocks.FLINT_SET, true);
        generateLootTablesForSet(ModBlocks.ICE_SET, false);
        generateLootTablesForSet(ModBlocks.PACKED_ICE_SET, false);
        generateLootTablesForSet(ModBlocks.BLUE_ICE_SET, false);
        generateLootTablesForSet(ModBlocks.CALCITE_SET, false);
        generateLootTablesForSet(ModBlocks.SCULK_SET, false);

        dropSelf(ModBlocks.DARK_PRISMARINE_WALL);
        dropSelf(ModBlocks.PRISMARINE_BRICK_WALL);
        dropSelf(ModBlocks.POLISHED_ANDESITE_WALL);
        dropSelf(ModBlocks.POLISHED_GRANITE_WALL);
        dropSelf(ModBlocks.POLISHED_DIORITE_WALL);
        dropSelf(ModBlocks.STONE_WALL);
        dropSelf(ModBlocks.PURPUR_WALL);
        dropSelf(ModBlocks.SMOOTH_QUARTZ_WALL);
        dropSelf(ModBlocks.NETHER_BRICK_FENCE_GATE);
        dropSelf(ModBlocks.PROTECTOR_BLOCK);
        add(Blocks.SPAWNER, multipleDrops(ModItems.CHAOS_ORB, 5, 5));
    }

    private void generateLootTablesForSet(ModBlocks.BlockSet blockSet, boolean includeBase) {
        if (includeBase) {dropSelf(blockSet.base());}
        dropSelf(blockSet.stairs());
        add(blockSet.slab(), this::createSlabItemTable);
        dropSelf(blockSet.fence());
        dropSelf(blockSet.fenceGate());
        dropSelf(blockSet.wall());
        if (blockSet.door() != null) {add(blockSet.door(), this::createDoorTable);}
        if (blockSet.trapdoor() != null) {dropSelf(blockSet.trapdoor());}
    }

    public LootTable.Builder multipleDrops(Item item, int minDrops, int maxDrops) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(
                                        UniformGenerator.between(minDrops, maxDrops)
                                )))
                );
    }
}
