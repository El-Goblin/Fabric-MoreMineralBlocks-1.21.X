package net.elgoblin.moremineralblocks.datagen;

import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {

        // LEGENDARY TOOLS


        // PICKAXE MINEABLE

        addSetToTag(ModBlocks.GOLD_SET, false, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.GOLD_BRICKS_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.CALCIFIED_GOLD_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.POLISHED_GOLD_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);

        addSetToTag(ModBlocks.DIAMOND_SET, false, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.DIAMOND_BRICKS_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.CALCIFIED_DIAMOND_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.POLISHED_DIAMOND_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);

        addSetToTag(ModBlocks.IRON_SET, false, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.IRON_BRICKS_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.CALCIFIED_IRON_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.POLISHED_IRON_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);

        addSetToTag(ModBlocks.EMERALD_SET, false, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.EMERALD_BRICKS_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.CALCIFIED_EMERALD_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.POLISHED_EMERALD_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.CHISELED_EMERALD_SET, false, BlockTags.MINEABLE_WITH_PICKAXE);

        addSetToTag(ModBlocks.AMETHYST_SET, false, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.AMETHYST_BRICKS_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.CALCIFIED_AMETHYST_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.POLISHED_AMETHYST_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);

        addSetToTag(ModBlocks.LAPIS_SET, false, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.LAPIS_BRICKS_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.CALCIFIED_LAPIS_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.POLISHED_LAPIS_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);

        addSetToTag(ModBlocks.COAL_SET, false, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.COAL_BRICKS_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.CALCIFIED_COAL_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.POLISHED_COAL_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);

        addSetToTag(ModBlocks.NETHERITE_SET, false, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.NETHERITE_BRICKS_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.CALCIFIED_NETHERITE_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.POLISHED_NETHERITE_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);

        addSetToTag(ModBlocks.REDSTONE_SET, false, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.REDSTONE_BRICKS_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.CALCIFIED_REDSTONE_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.POLISHED_REDSTONE_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);

        addSetToTag(ModBlocks.FLINT_SET, true, BlockTags.MINEABLE_WITH_PICKAXE);
//        addSetToTag(ModBlocks.GLASS_SET, false, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.OBSIDIAN_SET, false, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.ICE_SET, false, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.PACKED_ICE_SET, false, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.BLUE_ICE_SET, false, BlockTags.MINEABLE_WITH_PICKAXE);
        addSetToTag(ModBlocks.CALCITE_SET, false, BlockTags.MINEABLE_WITH_PICKAXE);

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getResourceKey(ModBlocks.SMOOTH_QUARTZ_WALL))
//                .add(ModBlocks.getResourceKey(ModBlocks.QUARTZ_WALL))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_ANDESITE_WALL))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_DIORITE_WALL))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_GRANITE_WALL))
                .add(ModBlocks.getResourceKey(ModBlocks.PURPUR_WALL))
                .add(ModBlocks.getResourceKey(ModBlocks.DARK_PRISMARINE_WALL))
                .add(ModBlocks.getResourceKey(ModBlocks.PRISMARINE_BRICK_WALL))
                .add(ModBlocks.getResourceKey(ModBlocks.NETHER_BRICK_FENCE_GATE))
                .add(ModBlocks.getResourceKey(ModBlocks.GLASS_STAIRS))
                .add(ModBlocks.getResourceKey(ModBlocks.GLASS_SLAB))
                .add(ModBlocks.getResourceKey(ModBlocks.PROTECTOR_BLOCK))
        ;

        // HOE MINEABLE

        addSetToTag(ModBlocks.SCULK_SET, false, BlockTags.MINEABLE_WITH_HOE);

        // NEEDS IRON TOOL

        addSetToTag(ModBlocks.GOLD_SET, false, BlockTags.NEEDS_IRON_TOOL);
        addSetToTag(ModBlocks.GOLD_BRICKS_SET, true, BlockTags.NEEDS_IRON_TOOL);
        addSetToTag(ModBlocks.CALCIFIED_GOLD_SET, true, BlockTags.NEEDS_IRON_TOOL);
        addSetToTag(ModBlocks.POLISHED_GOLD_SET, true, BlockTags.NEEDS_IRON_TOOL);

        addSetToTag(ModBlocks.DIAMOND_SET, false, BlockTags.NEEDS_IRON_TOOL);
        addSetToTag(ModBlocks.DIAMOND_BRICKS_SET, true, BlockTags.NEEDS_IRON_TOOL);
        addSetToTag(ModBlocks.CALCIFIED_DIAMOND_SET, true, BlockTags.NEEDS_IRON_TOOL);
        addSetToTag(ModBlocks.POLISHED_DIAMOND_SET, true, BlockTags.NEEDS_IRON_TOOL);

        addSetToTag(ModBlocks.EMERALD_SET, false, BlockTags.NEEDS_IRON_TOOL);
        addSetToTag(ModBlocks.EMERALD_BRICKS_SET, true, BlockTags.NEEDS_IRON_TOOL);
        addSetToTag(ModBlocks.CALCIFIED_EMERALD_SET, true, BlockTags.NEEDS_IRON_TOOL);
        addSetToTag(ModBlocks.POLISHED_EMERALD_SET, true, BlockTags.NEEDS_IRON_TOOL);
        addSetToTag(ModBlocks.CHISELED_EMERALD_SET, false, BlockTags.NEEDS_IRON_TOOL);

        addSetToTag(ModBlocks.REDSTONE_SET, false, BlockTags.NEEDS_IRON_TOOL);
        addSetToTag(ModBlocks.REDSTONE_BRICKS_SET, true, BlockTags.NEEDS_IRON_TOOL);
        addSetToTag(ModBlocks.CALCIFIED_REDSTONE_SET, true, BlockTags.NEEDS_IRON_TOOL);
        addSetToTag(ModBlocks.POLISHED_REDSTONE_SET, true, BlockTags.NEEDS_IRON_TOOL);

        tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.getResourceKey(ModBlocks.PROTECTOR_BLOCK));

        // NEEDS STONE TOOL

        addSetToTag(ModBlocks.IRON_SET, false, BlockTags.NEEDS_STONE_TOOL);
        addSetToTag(ModBlocks.IRON_BRICKS_SET, true, BlockTags.NEEDS_STONE_TOOL);
        addSetToTag(ModBlocks.CALCIFIED_IRON_SET, true, BlockTags.NEEDS_STONE_TOOL);
        addSetToTag(ModBlocks.POLISHED_IRON_SET, true, BlockTags.NEEDS_STONE_TOOL);

        addSetToTag(ModBlocks.LAPIS_SET, false, BlockTags.NEEDS_STONE_TOOL);
        addSetToTag(ModBlocks.LAPIS_BRICKS_SET, true, BlockTags.NEEDS_STONE_TOOL);
        addSetToTag(ModBlocks.CALCIFIED_LAPIS_SET, true, BlockTags.NEEDS_STONE_TOOL);
        addSetToTag(ModBlocks.POLISHED_LAPIS_SET, true, BlockTags.NEEDS_STONE_TOOL);

        // NEEDS DIAMOND TOOL

        addSetToTag(ModBlocks.NETHERITE_SET, false, BlockTags.NEEDS_DIAMOND_TOOL);
        addSetToTag(ModBlocks.NETHERITE_BRICKS_SET, true, BlockTags.NEEDS_DIAMOND_TOOL);
        addSetToTag(ModBlocks.CALCIFIED_NETHERITE_SET, true, BlockTags.NEEDS_DIAMOND_TOOL);
        addSetToTag(ModBlocks.POLISHED_NETHERITE_SET, true, BlockTags.NEEDS_DIAMOND_TOOL);
        addSetToTag(ModBlocks.OBSIDIAN_SET, false, BlockTags.NEEDS_DIAMOND_TOOL);

        tag(BlockTags.STAIRS)
                .add(ModBlocks.getResourceKey(ModBlocks.GOLD_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.GOLD_BRICKS_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_GOLD_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_GOLD_SET.stairs()))

                .add(ModBlocks.getResourceKey(ModBlocks.DIAMOND_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.DIAMOND_BRICKS_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_DIAMOND_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_DIAMOND_SET.stairs()))

                .add(ModBlocks.getResourceKey(ModBlocks.EMERALD_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.EMERALD_BRICKS_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_EMERALD_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_EMERALD_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.CHISELED_EMERALD_SET.stairs()))

                .add(ModBlocks.getResourceKey(ModBlocks.AMETHYST_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.AMETHYST_BRICKS_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_AMETHYST_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_AMETHYST_SET.stairs()))

                .add(ModBlocks.getResourceKey(ModBlocks.LAPIS_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.LAPIS_BRICKS_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_LAPIS_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_LAPIS_SET.stairs()))

                .add(ModBlocks.getResourceKey(ModBlocks.COAL_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.COAL_BRICKS_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_COAL_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_COAL_SET.stairs()))

                .add(ModBlocks.getResourceKey(ModBlocks.NETHERITE_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.NETHERITE_BRICKS_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_NETHERITE_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_NETHERITE_SET.stairs()))

                .add(ModBlocks.getResourceKey(ModBlocks.REDSTONE_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.REDSTONE_BRICKS_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_REDSTONE_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_REDSTONE_SET.stairs()))

                .add(ModBlocks.getResourceKey(ModBlocks.FLINT_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.OBSIDIAN_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.GLASS_STAIRS))
                .add(ModBlocks.getResourceKey(ModBlocks.ICE_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.PACKED_ICE_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.BLUE_ICE_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.BEDROCK_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.SCULK_SET.stairs()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCITE_SET.stairs()))
        ;



        tag(BlockTags.SLABS)
                .add(ModBlocks.getResourceKey(ModBlocks.GOLD_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.GOLD_BRICKS_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_GOLD_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_GOLD_SET.slab()))

                .add(ModBlocks.getResourceKey(ModBlocks.DIAMOND_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.DIAMOND_BRICKS_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_DIAMOND_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_DIAMOND_SET.slab()))

                .add(ModBlocks.getResourceKey(ModBlocks.IRON_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.IRON_BRICKS_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_IRON_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_IRON_SET.slab()))

                .add(ModBlocks.getResourceKey(ModBlocks.EMERALD_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.EMERALD_BRICKS_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_EMERALD_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_EMERALD_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.CHISELED_EMERALD_SET.slab()))

                .add(ModBlocks.getResourceKey(ModBlocks.AMETHYST_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.AMETHYST_BRICKS_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_AMETHYST_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_AMETHYST_SET.slab()))

                .add(ModBlocks.getResourceKey(ModBlocks.LAPIS_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.LAPIS_BRICKS_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_LAPIS_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_LAPIS_SET.slab()))

                .add(ModBlocks.getResourceKey(ModBlocks.COAL_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.COAL_BRICKS_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_COAL_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_COAL_SET.slab()))

                .add(ModBlocks.getResourceKey(ModBlocks.NETHERITE_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.NETHERITE_BRICKS_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_NETHERITE_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_NETHERITE_SET.slab()))

                .add(ModBlocks.getResourceKey(ModBlocks.REDSTONE_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.REDSTONE_BRICKS_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_REDSTONE_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_REDSTONE_SET.slab()))

                .add(ModBlocks.getResourceKey(ModBlocks.FLINT_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.OBSIDIAN_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.GLASS_SLAB))
                .add(ModBlocks.getResourceKey(ModBlocks.ICE_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.PACKED_ICE_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.BLUE_ICE_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.BEDROCK_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.SCULK_SET.slab()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCITE_SET.slab()))
        ;

        tag(BlockTags.FENCES)
                .add(ModBlocks.getResourceKey(ModBlocks.GOLD_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.GOLD_BRICKS_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_GOLD_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_GOLD_SET.fence()))

                .add(ModBlocks.getResourceKey(ModBlocks.DIAMOND_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.DIAMOND_BRICKS_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_DIAMOND_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_DIAMOND_SET.fence()))

                .add(ModBlocks.getResourceKey(ModBlocks.IRON_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.IRON_BRICKS_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_IRON_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_IRON_SET.fence()))

                .add(ModBlocks.getResourceKey(ModBlocks.EMERALD_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.EMERALD_BRICKS_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_EMERALD_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_EMERALD_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.CHISELED_EMERALD_SET.fence()))

                .add(ModBlocks.getResourceKey(ModBlocks.AMETHYST_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.AMETHYST_BRICKS_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_AMETHYST_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_AMETHYST_SET.fence()))

                .add(ModBlocks.getResourceKey(ModBlocks.LAPIS_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.LAPIS_BRICKS_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_LAPIS_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_LAPIS_SET.fence()))

                .add(ModBlocks.getResourceKey(ModBlocks.COAL_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.COAL_BRICKS_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_COAL_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_COAL_SET.fence()))

                .add(ModBlocks.getResourceKey(ModBlocks.NETHERITE_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.NETHERITE_BRICKS_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_NETHERITE_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_NETHERITE_SET.fence()))

                .add(ModBlocks.getResourceKey(ModBlocks.REDSTONE_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.REDSTONE_BRICKS_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_REDSTONE_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_REDSTONE_SET.fence()))

                .add(ModBlocks.getResourceKey(ModBlocks.FLINT_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.OBSIDIAN_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.ICE_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.PACKED_ICE_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.BLUE_ICE_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.BEDROCK_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.SCULK_SET.fence()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCITE_SET.fence()))
        ;

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.getResourceKey(ModBlocks.GOLD_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.GOLD_BRICKS_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_GOLD_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_GOLD_SET.fenceGate()))

                .add(ModBlocks.getResourceKey(ModBlocks.DIAMOND_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.DIAMOND_BRICKS_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_DIAMOND_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_DIAMOND_SET.fenceGate()))

                .add(ModBlocks.getResourceKey(ModBlocks.IRON_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.IRON_BRICKS_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_IRON_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_IRON_SET.fenceGate()))

                .add(ModBlocks.getResourceKey(ModBlocks.EMERALD_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.EMERALD_BRICKS_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_EMERALD_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_EMERALD_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.CHISELED_EMERALD_SET.fenceGate()))

                .add(ModBlocks.getResourceKey(ModBlocks.AMETHYST_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.AMETHYST_BRICKS_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_AMETHYST_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_AMETHYST_SET.fenceGate()))

                .add(ModBlocks.getResourceKey(ModBlocks.LAPIS_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.LAPIS_BRICKS_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_LAPIS_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_LAPIS_SET.fenceGate()))

                .add(ModBlocks.getResourceKey(ModBlocks.COAL_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.COAL_BRICKS_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_COAL_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_COAL_SET.fenceGate()))

                .add(ModBlocks.getResourceKey(ModBlocks.NETHERITE_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.NETHERITE_BRICKS_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_NETHERITE_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_NETHERITE_SET.fenceGate()))

                .add(ModBlocks.getResourceKey(ModBlocks.REDSTONE_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.REDSTONE_BRICKS_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_REDSTONE_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_REDSTONE_SET.fenceGate()))

                .add(ModBlocks.getResourceKey(ModBlocks.FLINT_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.OBSIDIAN_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.ICE_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.PACKED_ICE_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.BLUE_ICE_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.BEDROCK_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.SCULK_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCITE_SET.fenceGate()))
                .add(ModBlocks.getResourceKey(ModBlocks.NETHER_BRICK_FENCE_GATE))
        ;

        tag(BlockTags.WALLS)
                .add(ModBlocks.getResourceKey(ModBlocks.GOLD_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.GOLD_BRICKS_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_GOLD_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_GOLD_SET.wall()))

                .add(ModBlocks.getResourceKey(ModBlocks.DIAMOND_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.DIAMOND_BRICKS_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_DIAMOND_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_DIAMOND_SET.wall()))

                .add(ModBlocks.getResourceKey(ModBlocks.IRON_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.IRON_BRICKS_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_IRON_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_IRON_SET.wall()))

                .add(ModBlocks.getResourceKey(ModBlocks.EMERALD_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.EMERALD_BRICKS_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_EMERALD_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_EMERALD_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.CHISELED_EMERALD_SET.wall()))

                .add(ModBlocks.getResourceKey(ModBlocks.AMETHYST_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.AMETHYST_BRICKS_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_AMETHYST_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_AMETHYST_SET.wall()))

                .add(ModBlocks.getResourceKey(ModBlocks.LAPIS_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.LAPIS_BRICKS_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_LAPIS_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_LAPIS_SET.wall()))

                .add(ModBlocks.getResourceKey(ModBlocks.COAL_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.COAL_BRICKS_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_COAL_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_COAL_SET.wall()))

                .add(ModBlocks.getResourceKey(ModBlocks.NETHERITE_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.NETHERITE_BRICKS_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_NETHERITE_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_NETHERITE_SET.wall()))

                .add(ModBlocks.getResourceKey(ModBlocks.REDSTONE_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.REDSTONE_BRICKS_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCIFIED_REDSTONE_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_REDSTONE_SET.wall()))

                .add(ModBlocks.getResourceKey(ModBlocks.FLINT_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.OBSIDIAN_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.ICE_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.PACKED_ICE_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.BLUE_ICE_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.BEDROCK_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.SCULK_SET.wall()))
                .add(ModBlocks.getResourceKey(ModBlocks.CALCITE_SET.wall()))

                .add(ModBlocks.getResourceKey(ModBlocks.DARK_PRISMARINE_WALL))
                .add(ModBlocks.getResourceKey(ModBlocks.PRISMARINE_BRICK_WALL))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_DIORITE_WALL))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_GRANITE_WALL))
                .add(ModBlocks.getResourceKey(ModBlocks.POLISHED_ANDESITE_WALL))
                .add(ModBlocks.getResourceKey(ModBlocks.SMOOTH_QUARTZ_WALL))
                .add(ModBlocks.getResourceKey(ModBlocks.PURPUR_WALL))
        ;

        tag(BlockTags.DOORS)
                .add(ModBlocks.getResourceKey(ModBlocks.GOLD_SET.door()))
                .add(ModBlocks.getResourceKey(ModBlocks.DIAMOND_SET.door()))
                .add(ModBlocks.getResourceKey(ModBlocks.EMERALD_SET.door()))
                .add(ModBlocks.getResourceKey(ModBlocks.AMETHYST_SET.door()))
                .add(ModBlocks.getResourceKey(ModBlocks.LAPIS_SET.door()))
                .add(ModBlocks.getResourceKey(ModBlocks.COAL_SET.door()))
                .add(ModBlocks.getResourceKey(ModBlocks.NETHERITE_SET.door()))
                .add(ModBlocks.getResourceKey(ModBlocks.REDSTONE_SET.door()))
        ;

        tag(BlockTags.TRAPDOORS)
                .add(ModBlocks.getResourceKey(ModBlocks.GOLD_SET.trapdoor()))
                .add(ModBlocks.getResourceKey(ModBlocks.DIAMOND_SET.trapdoor()))
                .add(ModBlocks.getResourceKey(ModBlocks.EMERALD_SET.trapdoor()))
                .add(ModBlocks.getResourceKey(ModBlocks.AMETHYST_SET.trapdoor()))
                .add(ModBlocks.getResourceKey(ModBlocks.LAPIS_SET.trapdoor()))
                .add(ModBlocks.getResourceKey(ModBlocks.COAL_SET.trapdoor()))
                .add(ModBlocks.getResourceKey(ModBlocks.NETHERITE_SET.trapdoor()))
                .add(ModBlocks.getResourceKey(ModBlocks.REDSTONE_SET.trapdoor()))
        ;
    }

    private void addSetToTag(ModBlocks.BlockSet blockSet, boolean includeBase, TagKey<Block> tagKey) {
        if (includeBase) {
            tag(tagKey).add(ModBlocks.getResourceKey(blockSet.base()));
        }
        tag(tagKey)
                .add(ModBlocks.getResourceKey(blockSet.stairs()))
                .add(ModBlocks.getResourceKey(blockSet.slab()))
                .add(ModBlocks.getResourceKey(blockSet.fence()))
                .add(ModBlocks.getResourceKey(blockSet.fenceGate()))
                .add(ModBlocks.getResourceKey(blockSet.wall()));
        if (blockSet.door() != null) {
            tag(tagKey).add(ModBlocks.getResourceKey(blockSet.door()));
        }
        if (blockSet.trapdoor() != null) {
            tag(tagKey).add(ModBlocks.getResourceKey(blockSet.trapdoor()));
        }
    }
}
