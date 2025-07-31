package net.elgoblin.moremineralblocks.datagen;

import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        //addDrop(ModBlocks.PINK_GARNET_BLOCK);
        //addDrop(ModBlocks.RAW_PINK_GARNET_BLOCK);
        //addDrop(ModBlocks.MAGIC_BLOCK);

        //addDrop(ModBlocks.PINK_GARNET_ORE, oreDrops(ModBlocks.PINK_GARNET_ORE, ModItems.RAW_PINK_GARNET));
        //addDrop(ModBlocks.PINK_GARNET_DEEPSLATE_ORE, multipleOreDrops(ModBlocks.PINK_GARNET_DEEPSLATE_ORE, ModItems.RAW_PINK_GARNET, 2, 5));

        //addDrop(ModBlocks.PINK_GARNET_STAIRS);
        //addDrop(ModBlocks.PINK_GARNET_FENCE);
        //addDrop(ModBlocks.PINK_GARNET_FENCE_GATE);
        //addDrop(ModBlocks.PINK_GARNET_WALL);
        //addDrop(ModBlocks.PINK_GARNET_SLAB, slabDrops(ModBlocks.PINK_GARNET_SLAB));

        addDrop(ModBlocks.GOLD_STAIRS);
        addDrop(ModBlocks.GOLD_FENCE);
        addDrop(ModBlocks.GOLD_FENCE_GATE);
        addDrop(ModBlocks.GOLD_WALL);
        addDrop(ModBlocks.GOLD_TRAPDOOR);
        addDrop(ModBlocks.GOLD_SLAB, slabDrops(ModBlocks.GOLD_SLAB));
        addDrop(ModBlocks.GOLD_DOOR, doorDrops(ModBlocks.GOLD_DOOR));
        addDrop(ModBlocks.POLISHED_GOLD_BLOCK);
        addDrop(ModBlocks.CALCIFIED_GOLD_BLOCK);
        addDrop(ModBlocks.GOLD_BRICKS);

        addDrop(ModBlocks.DIAMOND_STAIRS);
        addDrop(ModBlocks.DIAMOND_FENCE);
        addDrop(ModBlocks.DIAMOND_FENCE_GATE);
        addDrop(ModBlocks.DIAMOND_WALL);
        addDrop(ModBlocks.DIAMOND_TRAPDOOR);
        addDrop(ModBlocks.DIAMOND_SLAB, slabDrops(ModBlocks.DIAMOND_SLAB));
        addDrop(ModBlocks.DIAMOND_DOOR, doorDrops(ModBlocks.DIAMOND_DOOR));
        addDrop(ModBlocks.POLISHED_DIAMOND_BLOCK);
        addDrop(ModBlocks.CALCIFIED_DIAMOND_BLOCK);
        addDrop(ModBlocks.DIAMOND_BRICKS);

        addDrop(ModBlocks.IRON_STAIRS);
        addDrop(ModBlocks.IRON_FENCE);
        addDrop(ModBlocks.IRON_FENCE_GATE);
        addDrop(ModBlocks.IRON_WALL);
        addDrop(ModBlocks.IRON_SLAB, slabDrops(ModBlocks.IRON_SLAB));
        addDrop(ModBlocks.POLISHED_IRON_BLOCK);
        addDrop(ModBlocks.CALCIFIED_IRON_BLOCK);
        addDrop(ModBlocks.IRON_BRICKS);

        addDrop(ModBlocks.AMETHYST_STAIRS);
        addDrop(ModBlocks.AMETHYST_FENCE);
        addDrop(ModBlocks.AMETHYST_FENCE_GATE);
        addDrop(ModBlocks.AMETHYST_WALL);
        addDrop(ModBlocks.AMETHYST_TRAPDOOR);
        addDrop(ModBlocks.AMETHYST_SLAB, slabDrops(ModBlocks.AMETHYST_SLAB));
        addDrop(ModBlocks.AMETHYST_DOOR, doorDrops(ModBlocks.AMETHYST_DOOR));
        addDrop(ModBlocks.POLISHED_AMETHYST_BLOCK);
        addDrop(ModBlocks.CALCIFIED_AMETHYST_BLOCK);
        addDrop(ModBlocks.AMETHYST_BRICKS);

        addDrop(ModBlocks.EMERALD_STAIRS);
        addDrop(ModBlocks.EMERALD_FENCE);
        addDrop(ModBlocks.EMERALD_FENCE_GATE);
        addDrop(ModBlocks.EMERALD_WALL);
        addDrop(ModBlocks.EMERALD_TRAPDOOR);
        addDrop(ModBlocks.EMERALD_SLAB, slabDrops(ModBlocks.EMERALD_SLAB));
        addDrop(ModBlocks.EMERALD_DOOR, doorDrops(ModBlocks.EMERALD_DOOR));
        addDrop(ModBlocks.POLISHED_EMERALD_BLOCK);
        addDrop(ModBlocks.CHISELED_EMERALD_BLOCK);
        addDrop(ModBlocks.CALCIFIED_EMERALD_BLOCK);
        addDrop(ModBlocks.EMERALD_BRICKS);

        addDrop(ModBlocks.LAPIS_STAIRS);
        addDrop(ModBlocks.LAPIS_FENCE);
        addDrop(ModBlocks.LAPIS_FENCE_GATE);
        addDrop(ModBlocks.LAPIS_WALL);
        addDrop(ModBlocks.LAPIS_TRAPDOOR);
        addDrop(ModBlocks.LAPIS_SLAB, slabDrops(ModBlocks.LAPIS_SLAB));
        addDrop(ModBlocks.LAPIS_DOOR, doorDrops(ModBlocks.LAPIS_DOOR));
        addDrop(ModBlocks.POLISHED_LAPIS_BLOCK);
        addDrop(ModBlocks.CALCIFIED_LAPIS_BLOCK);
        addDrop(ModBlocks.LAPIS_BRICKS);

        addDrop(ModBlocks.COAL_STAIRS);
        addDrop(ModBlocks.COAL_FENCE);
        addDrop(ModBlocks.COAL_FENCE_GATE);
        addDrop(ModBlocks.COAL_WALL);
        addDrop(ModBlocks.COAL_TRAPDOOR);
        addDrop(ModBlocks.COAL_SLAB, slabDrops(ModBlocks.COAL_SLAB));
        addDrop(ModBlocks.COAL_DOOR, doorDrops(ModBlocks.COAL_DOOR));
        addDrop(ModBlocks.POLISHED_COAL_BLOCK);
        addDrop(ModBlocks.CALCIFIED_COAL_BLOCK);
        addDrop(ModBlocks.COAL_BRICKS);

        addDrop(ModBlocks.CALCITE_STAIRS);
        addDrop(ModBlocks.CALCITE_FENCE);
        addDrop(ModBlocks.CALCITE_FENCE_GATE);
        addDrop(ModBlocks.CALCITE_WALL);
        addDrop(ModBlocks.CALCITE_SLAB, slabDrops(ModBlocks.CALCITE_SLAB));

        addDrop(ModBlocks.NETHERITE_STAIRS);
        addDrop(ModBlocks.NETHERITE_FENCE);
        addDrop(ModBlocks.NETHERITE_FENCE_GATE);
        addDrop(ModBlocks.NETHERITE_WALL);
        addDrop(ModBlocks.NETHERITE_TRAPDOOR);
        addDrop(ModBlocks.NETHERITE_SLAB, slabDrops(ModBlocks.NETHERITE_SLAB));
        addDrop(ModBlocks.NETHERITE_DOOR, doorDrops(ModBlocks.NETHERITE_DOOR));
        addDrop(ModBlocks.POLISHED_NETHERITE_BLOCK);
        addDrop(ModBlocks.CALCIFIED_NETHERITE_BLOCK);
        addDrop(ModBlocks.NETHERITE_BRICKS);

        addDrop(ModBlocks.REDSTONE_STAIRS);
        addDrop(ModBlocks.REDSTONE_FENCE);
        addDrop(ModBlocks.REDSTONE_FENCE_GATE);
        addDrop(ModBlocks.REDSTONE_WALL);
        addDrop(ModBlocks.REDSTONE_TRAPDOOR);
        addDrop(ModBlocks.REDSTONE_SLAB, slabDrops(ModBlocks.REDSTONE_SLAB));
        addDrop(ModBlocks.REDSTONE_DOOR, doorDrops(ModBlocks.REDSTONE_DOOR));
        addDrop(ModBlocks.POLISHED_REDSTONE_BLOCK);
        addDrop(ModBlocks.CALCIFIED_REDSTONE_BLOCK);
        addDrop(ModBlocks.REDSTONE_BRICKS);

        addDrop(ModBlocks.OBSIDIAN_STAIRS);
        addDrop(ModBlocks.OBSIDIAN_FENCE);
        addDrop(ModBlocks.OBSIDIAN_FENCE_GATE);
        addDrop(ModBlocks.OBSIDIAN_WALL);
        addDrop(ModBlocks.OBSIDIAN_SLAB, slabDrops(ModBlocks.OBSIDIAN_SLAB));

        addDrop(ModBlocks.PACKED_ICE_STAIRS);
        addDrop(ModBlocks.PACKED_ICE_FENCE);
        addDrop(ModBlocks.PACKED_ICE_FENCE_GATE);
        addDrop(ModBlocks.PACKED_ICE_WALL);
        addDrop(ModBlocks.PACKED_ICE_SLAB, slabDrops(ModBlocks.PACKED_ICE_SLAB));

        addDrop(ModBlocks.BLUE_ICE_STAIRS);
        addDrop(ModBlocks.BLUE_ICE_FENCE);
        addDrop(ModBlocks.BLUE_ICE_FENCE_GATE);
        addDrop(ModBlocks.BLUE_ICE_WALL);
        addDrop(ModBlocks.BLUE_ICE_SLAB, slabDrops(ModBlocks.BLUE_ICE_SLAB));

        addDrop(ModBlocks.ICE_STAIRS);
        addDrop(ModBlocks.ICE_FENCE);
        addDrop(ModBlocks.ICE_FENCE_GATE);
        addDrop(ModBlocks.ICE_WALL);
        addDrop(ModBlocks.ICE_SLAB, slabDrops(ModBlocks.ICE_SLAB));

        addDrop(ModBlocks.DARK_PRISMARINE_WALL);
        addDrop(ModBlocks.PRISMARINE_BRICK_WALL);
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, (LootPoolEntry.Builder<?>)this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)ItemEntry.builder(item)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }
}
