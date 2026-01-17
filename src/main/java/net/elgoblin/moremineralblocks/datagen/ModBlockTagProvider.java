package net.elgoblin.moremineralblocks.datagen;

import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.elgoblin.moremineralblocks.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        //valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
        //        .add(ModBlocks.PINK_GARNET_BLOCK)
        //        .add(ModBlocks.RAW_PINK_GARNET_BLOCK)
        //        .add(ModBlocks.PINK_GARNET_ORE)
        //        .add(ModBlocks.PINK_GARNET_DEEPSLATE_ORE)
        //        .add(ModBlocks.MAGIC_BLOCK);

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.GOLD_STAIRS)
                .add(ModBlocks.GOLD_SLAB)
                .add(ModBlocks.GOLD_FENCE)
                .add(ModBlocks.GOLD_FENCE_GATE)
                .add(ModBlocks.POLISHED_GOLD_BLOCK)
                .add(ModBlocks.GOLD_DOOR)
                .add(ModBlocks.GOLD_TRAPDOOR)
                .add(ModBlocks.CALCIFIED_GOLD_BLOCK)
                .add(ModBlocks.GOLD_BRICKS)
                .add(ModBlocks.GOLD_WALL)

                .add(ModBlocks.GOLD_BRICK_STAIRS)
                .add(ModBlocks.GOLD_BRICK_SLAB)
                .add(ModBlocks.GOLD_BRICK_WALL)
                .add(ModBlocks.GOLD_BRICK_FENCE)
                .add(ModBlocks.GOLD_BRICK_FENCE_GATE)
                .add(ModBlocks.CALCIFIED_GOLD_STAIRS)
                .add(ModBlocks.CALCIFIED_GOLD_SLAB)
                .add(ModBlocks.CALCIFIED_GOLD_WALL)
                .add(ModBlocks.CALCIFIED_GOLD_FENCE)
                .add(ModBlocks.CALCIFIED_GOLD_FENCE_GATE)
                .add(ModBlocks.POLISHED_GOLD_STAIRS)
                .add(ModBlocks.POLISHED_GOLD_SLAB)
                .add(ModBlocks.POLISHED_GOLD_WALL)
                .add(ModBlocks.POLISHED_GOLD_FENCE)
                .add(ModBlocks.POLISHED_GOLD_FENCE_GATE)

                .add(ModBlocks.DIAMOND_STAIRS)
                .add(ModBlocks.DIAMOND_SLAB)
                .add(ModBlocks.DIAMOND_FENCE)
                .add(ModBlocks.DIAMOND_FENCE_GATE)
                .add(ModBlocks.POLISHED_DIAMOND_BLOCK)
                .add(ModBlocks.DIAMOND_DOOR)
                .add(ModBlocks.DIAMOND_TRAPDOOR)
                .add(ModBlocks.CALCIFIED_DIAMOND_BLOCK)
                .add(ModBlocks.DIAMOND_BRICKS)
                .add(ModBlocks.DIAMOND_WALL)

                .add(ModBlocks.DIAMOND_BRICK_STAIRS)
                .add(ModBlocks.DIAMOND_BRICK_SLAB)
                .add(ModBlocks.DIAMOND_BRICK_WALL)
                .add(ModBlocks.DIAMOND_BRICK_FENCE)
                .add(ModBlocks.DIAMOND_BRICK_FENCE_GATE)
                .add(ModBlocks.CALCIFIED_DIAMOND_STAIRS)
                .add(ModBlocks.CALCIFIED_DIAMOND_SLAB)
                .add(ModBlocks.CALCIFIED_DIAMOND_WALL)
                .add(ModBlocks.CALCIFIED_DIAMOND_FENCE)
                .add(ModBlocks.CALCIFIED_DIAMOND_FENCE_GATE)
                .add(ModBlocks.POLISHED_DIAMOND_STAIRS)
                .add(ModBlocks.POLISHED_DIAMOND_SLAB)
                .add(ModBlocks.POLISHED_DIAMOND_WALL)
                .add(ModBlocks.POLISHED_DIAMOND_FENCE)
                .add(ModBlocks.POLISHED_DIAMOND_FENCE_GATE)

                .add(ModBlocks.EMERALD_STAIRS)
                .add(ModBlocks.EMERALD_SLAB)
                .add(ModBlocks.EMERALD_FENCE)
                .add(ModBlocks.EMERALD_FENCE_GATE)
                .add(ModBlocks.POLISHED_EMERALD_BLOCK)
                .add(ModBlocks.EMERALD_DOOR)
                .add(ModBlocks.EMERALD_TRAPDOOR)
                .add(ModBlocks.CALCIFIED_EMERALD_BLOCK)
                .add(ModBlocks.CHISELED_EMERALD_BLOCK)
                .add(ModBlocks.EMERALD_BRICKS)
                .add(ModBlocks.EMERALD_WALL)

                .add(ModBlocks.EMERALD_BRICK_STAIRS)
                .add(ModBlocks.EMERALD_BRICK_SLAB)
                .add(ModBlocks.EMERALD_BRICK_WALL)
                .add(ModBlocks.EMERALD_BRICK_FENCE)
                .add(ModBlocks.EMERALD_BRICK_FENCE_GATE)
                .add(ModBlocks.CALCIFIED_EMERALD_STAIRS)
                .add(ModBlocks.CALCIFIED_EMERALD_SLAB)
                .add(ModBlocks.CALCIFIED_EMERALD_WALL)
                .add(ModBlocks.CALCIFIED_EMERALD_FENCE)
                .add(ModBlocks.CALCIFIED_EMERALD_FENCE_GATE)
                .add(ModBlocks.POLISHED_EMERALD_STAIRS)
                .add(ModBlocks.POLISHED_EMERALD_SLAB)
                .add(ModBlocks.POLISHED_EMERALD_WALL)
                .add(ModBlocks.POLISHED_EMERALD_FENCE)
                .add(ModBlocks.POLISHED_EMERALD_FENCE_GATE)
                .add(ModBlocks.CHISELED_EMERALD_STAIRS)
                .add(ModBlocks.CHISELED_EMERALD_SLAB)
                .add(ModBlocks.CHISELED_EMERALD_WALL)
                .add(ModBlocks.CHISELED_EMERALD_FENCE)
                .add(ModBlocks.CHISELED_EMERALD_FENCE_GATE)

                .add(ModBlocks.REDSTONE_STAIRS)
                .add(ModBlocks.REDSTONE_SLAB)
                .add(ModBlocks.REDSTONE_FENCE)
                .add(ModBlocks.REDSTONE_FENCE_GATE)
                .add(ModBlocks.POLISHED_REDSTONE_BLOCK)
                .add(ModBlocks.REDSTONE_DOOR)
                .add(ModBlocks.REDSTONE_TRAPDOOR)
                .add(ModBlocks.CALCIFIED_REDSTONE_BLOCK)
                .add(ModBlocks.REDSTONE_BRICKS)
                .add(ModBlocks.REDSTONE_WALL)

                .add(ModBlocks.REDSTONE_BRICK_STAIRS)
                .add(ModBlocks.REDSTONE_BRICK_SLAB)
                .add(ModBlocks.REDSTONE_BRICK_WALL)
                .add(ModBlocks.REDSTONE_BRICK_FENCE)
                .add(ModBlocks.REDSTONE_BRICK_FENCE_GATE)
                .add(ModBlocks.CALCIFIED_REDSTONE_STAIRS)
                .add(ModBlocks.CALCIFIED_REDSTONE_SLAB)
                .add(ModBlocks.CALCIFIED_REDSTONE_WALL)
                .add(ModBlocks.CALCIFIED_REDSTONE_FENCE)
                .add(ModBlocks.CALCIFIED_REDSTONE_FENCE_GATE)
                .add(ModBlocks.POLISHED_REDSTONE_STAIRS)
                .add(ModBlocks.POLISHED_REDSTONE_SLAB)
                .add(ModBlocks.POLISHED_REDSTONE_WALL)
                .add(ModBlocks.POLISHED_REDSTONE_FENCE)
                .add(ModBlocks.POLISHED_REDSTONE_FENCE_GATE)
                .add(ModBlocks.PROTECTOR_BLOCK);


        valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.IRON_STAIRS)
                .add(ModBlocks.IRON_SLAB)
                .add(ModBlocks.IRON_FENCE)
                .add(ModBlocks.IRON_FENCE_GATE)
                .add(ModBlocks.POLISHED_IRON_BLOCK)
                .add(ModBlocks.CALCIFIED_IRON_BLOCK)
                .add(ModBlocks.IRON_BRICKS)
                .add(ModBlocks.IRON_WALL)

                .add(ModBlocks.IRON_BRICK_STAIRS)
                .add(ModBlocks.IRON_BRICK_SLAB)
                .add(ModBlocks.IRON_BRICK_WALL)
                .add(ModBlocks.IRON_BRICK_FENCE)
                .add(ModBlocks.IRON_BRICK_FENCE_GATE)
                .add(ModBlocks.CALCIFIED_IRON_STAIRS)
                .add(ModBlocks.CALCIFIED_IRON_SLAB)
                .add(ModBlocks.CALCIFIED_IRON_WALL)
                .add(ModBlocks.CALCIFIED_IRON_FENCE)
                .add(ModBlocks.CALCIFIED_IRON_FENCE_GATE)
                .add(ModBlocks.POLISHED_IRON_STAIRS)
                .add(ModBlocks.POLISHED_IRON_SLAB)
                .add(ModBlocks.POLISHED_IRON_WALL)
                .add(ModBlocks.POLISHED_IRON_FENCE)
                .add(ModBlocks.POLISHED_IRON_FENCE_GATE)

                .add(ModBlocks.LAPIS_STAIRS)
                .add(ModBlocks.LAPIS_SLAB)
                .add(ModBlocks.LAPIS_FENCE)
                .add(ModBlocks.LAPIS_FENCE_GATE)
                .add(ModBlocks.POLISHED_LAPIS_BLOCK)
                .add(ModBlocks.LAPIS_DOOR)
                .add(ModBlocks.LAPIS_TRAPDOOR)
                .add(ModBlocks.CALCIFIED_LAPIS_BLOCK)
                .add(ModBlocks.LAPIS_BRICKS)
                .add(ModBlocks.LAPIS_WALL)

                .add(ModBlocks.LAPIS_BRICK_STAIRS)
                .add(ModBlocks.LAPIS_BRICK_SLAB)
                .add(ModBlocks.LAPIS_BRICK_WALL)
                .add(ModBlocks.LAPIS_BRICK_FENCE)
                .add(ModBlocks.LAPIS_BRICK_FENCE_GATE)
                .add(ModBlocks.CALCIFIED_LAPIS_STAIRS)
                .add(ModBlocks.CALCIFIED_LAPIS_SLAB)
                .add(ModBlocks.CALCIFIED_LAPIS_WALL)
                .add(ModBlocks.CALCIFIED_LAPIS_FENCE)
                .add(ModBlocks.CALCIFIED_LAPIS_FENCE_GATE)
                .add(ModBlocks.POLISHED_LAPIS_STAIRS)
                .add(ModBlocks.POLISHED_LAPIS_SLAB)
                .add(ModBlocks.POLISHED_LAPIS_WALL)
                .add(ModBlocks.POLISHED_LAPIS_FENCE)
                .add(ModBlocks.POLISHED_LAPIS_FENCE_GATE);

        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.NETHERITE_STAIRS)
                .add(ModBlocks.NETHERITE_SLAB)
                .add(ModBlocks.NETHERITE_FENCE)
                .add(ModBlocks.NETHERITE_FENCE_GATE)
                .add(ModBlocks.POLISHED_NETHERITE_BLOCK)
                .add(ModBlocks.NETHERITE_DOOR)
                .add(ModBlocks.NETHERITE_TRAPDOOR)
                .add(ModBlocks.CALCIFIED_NETHERITE_BLOCK)
                .add(ModBlocks.NETHERITE_BRICKS)
                .add(ModBlocks.NETHERITE_WALL)

                .add(ModBlocks.NETHERITE_BRICK_STAIRS)
                .add(ModBlocks.NETHERITE_BRICK_SLAB)
                .add(ModBlocks.NETHERITE_BRICK_WALL)
                .add(ModBlocks.NETHERITE_BRICK_FENCE)
                .add(ModBlocks.NETHERITE_BRICK_FENCE_GATE)
                .add(ModBlocks.CALCIFIED_NETHERITE_STAIRS)
                .add(ModBlocks.CALCIFIED_NETHERITE_SLAB)
                .add(ModBlocks.CALCIFIED_NETHERITE_WALL)
                .add(ModBlocks.CALCIFIED_NETHERITE_FENCE)
                .add(ModBlocks.CALCIFIED_NETHERITE_FENCE_GATE)
                .add(ModBlocks.POLISHED_NETHERITE_STAIRS)
                .add(ModBlocks.POLISHED_NETHERITE_SLAB)
                .add(ModBlocks.POLISHED_NETHERITE_WALL)
                .add(ModBlocks.POLISHED_NETHERITE_FENCE)
                .add(ModBlocks.POLISHED_NETHERITE_FENCE_GATE)

                .add(ModBlocks.OBSIDIAN_STAIRS)
                .add(ModBlocks.OBSIDIAN_SLAB)
                .add(ModBlocks.OBSIDIAN_FENCE)
                .add(ModBlocks.OBSIDIAN_FENCE_GATE)
                .add(ModBlocks.OBSIDIAN_WALL);

        //valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.PINK_GARNET_FENCE);
        //valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.PINK_GARNET_FENCE_GATE);
        //valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.PINK_GARNET_WALL);

        // GOLD

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.GOLD_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.GOLD_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.GOLD_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.CALCIFIED_GOLD_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.CALCIFIED_GOLD_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.CALCIFIED_GOLD_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.GOLD_BRICK_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.GOLD_BRICK_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.GOLD_BRICK_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.POLISHED_GOLD_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.POLISHED_GOLD_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.POLISHED_GOLD_WALL);

        // DIAMOND

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.DIAMOND_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.DIAMOND_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.DIAMOND_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.CALCIFIED_DIAMOND_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.CALCIFIED_DIAMOND_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.CALCIFIED_DIAMOND_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.DIAMOND_BRICK_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.DIAMOND_BRICK_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.DIAMOND_BRICK_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.POLISHED_DIAMOND_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.POLISHED_DIAMOND_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.POLISHED_DIAMOND_WALL);

        // EMERALD

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.EMERALD_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.EMERALD_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.EMERALD_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.CALCIFIED_EMERALD_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.CALCIFIED_EMERALD_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.CALCIFIED_EMERALD_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.EMERALD_BRICK_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.EMERALD_BRICK_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.EMERALD_BRICK_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.POLISHED_EMERALD_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.POLISHED_EMERALD_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.POLISHED_EMERALD_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.CHISELED_EMERALD_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.CHISELED_EMERALD_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.CHISELED_EMERALD_WALL);

        // IRON

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.IRON_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.IRON_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.IRON_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.CALCIFIED_IRON_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.CALCIFIED_IRON_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.CALCIFIED_IRON_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.IRON_BRICK_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.IRON_BRICK_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.IRON_BRICK_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.POLISHED_IRON_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.POLISHED_IRON_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.POLISHED_IRON_WALL);

        // LAPIS

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.LAPIS_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.LAPIS_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.LAPIS_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.CALCIFIED_LAPIS_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.CALCIFIED_LAPIS_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.CALCIFIED_LAPIS_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.LAPIS_BRICK_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.LAPIS_BRICK_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.LAPIS_BRICK_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.POLISHED_LAPIS_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.POLISHED_LAPIS_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.POLISHED_LAPIS_WALL);

        // NETHERITE

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.NETHERITE_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.NETHERITE_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.NETHERITE_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.CALCIFIED_NETHERITE_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.CALCIFIED_NETHERITE_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.CALCIFIED_NETHERITE_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.NETHERITE_BRICK_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.NETHERITE_BRICK_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.NETHERITE_BRICK_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.POLISHED_NETHERITE_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.POLISHED_NETHERITE_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.POLISHED_NETHERITE_WALL);

        // AMETHYST

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.AMETHYST_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.AMETHYST_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.AMETHYST_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.CALCIFIED_AMETHYST_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.CALCIFIED_AMETHYST_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.CALCIFIED_AMETHYST_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.AMETHYST_BRICK_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.AMETHYST_BRICK_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.AMETHYST_BRICK_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.POLISHED_AMETHYST_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.POLISHED_AMETHYST_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.POLISHED_AMETHYST_WALL);

        // CALCITE

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.CALCITE_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.CALCITE_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.CALCITE_WALL);

        // COAL

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.COAL_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.COAL_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.COAL_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.CALCIFIED_COAL_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.CALCIFIED_COAL_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.CALCIFIED_COAL_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.COAL_BRICK_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.COAL_BRICK_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.COAL_BRICK_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.POLISHED_COAL_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.POLISHED_COAL_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.POLISHED_COAL_WALL);

        // REDSTONE

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.REDSTONE_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.REDSTONE_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.REDSTONE_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.CALCIFIED_REDSTONE_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.CALCIFIED_REDSTONE_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.CALCIFIED_REDSTONE_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.REDSTONE_BRICK_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.REDSTONE_BRICK_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.REDSTONE_BRICK_WALL);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.POLISHED_REDSTONE_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.POLISHED_REDSTONE_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.POLISHED_REDSTONE_WALL);

        // OBSIDIAN

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.OBSIDIAN_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.OBSIDIAN_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.OBSIDIAN_WALL);

        // BEDROCK

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.BEDROCK_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.BEDROCK_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.BEDROCK_WALL);

        // SCULK

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.SCULK_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.SCULK_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.SCULK_WALL);

        // PACKED_ICE

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.PACKED_ICE_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.PACKED_ICE_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.PACKED_ICE_WALL);

        // BLUE ICE

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.BLUE_ICE_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.BLUE_ICE_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.BLUE_ICE_WALL);

        // ICE

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.ICE_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.ICE_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.ICE_WALL);

//        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.DEEPSLATE_EMERALD_ORE_FENCE);
//        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.DEEPSLATE_EMERALD_ORE_FENCE_GATE);
//        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.DEEPSLATE_EMERALD_ORE_WALL);

        // PRISMARINE

        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.DARK_PRISMARINE_WALL);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.PRISMARINE_BRICK_WALL);

        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.GOLD_STAIRS)
                .add(ModBlocks.GOLD_SLAB)
                .add(ModBlocks.GOLD_FENCE)
                .add(ModBlocks.GOLD_FENCE_GATE)
                .add(ModBlocks.POLISHED_GOLD_BLOCK)
                .add(ModBlocks.GOLD_DOOR)
                .add(ModBlocks.GOLD_TRAPDOOR)
                .add(ModBlocks.CALCIFIED_GOLD_BLOCK)
                .add(ModBlocks.GOLD_BRICKS)

                .add(ModBlocks.GOLD_BRICK_STAIRS)
                .add(ModBlocks.GOLD_BRICK_SLAB)
                .add(ModBlocks.GOLD_BRICK_WALL)
                .add(ModBlocks.GOLD_BRICK_FENCE)
                .add(ModBlocks.GOLD_BRICK_FENCE_GATE)
                .add(ModBlocks.CALCIFIED_GOLD_STAIRS)
                .add(ModBlocks.CALCIFIED_GOLD_SLAB)
                .add(ModBlocks.CALCIFIED_GOLD_WALL)
                .add(ModBlocks.CALCIFIED_GOLD_FENCE)
                .add(ModBlocks.CALCIFIED_GOLD_FENCE_GATE)
                .add(ModBlocks.POLISHED_GOLD_STAIRS)
                .add(ModBlocks.POLISHED_GOLD_SLAB)
                .add(ModBlocks.POLISHED_GOLD_WALL)
                .add(ModBlocks.POLISHED_GOLD_FENCE)
                .add(ModBlocks.POLISHED_GOLD_FENCE_GATE)

                .add(ModBlocks.DIAMOND_STAIRS)
                .add(ModBlocks.DIAMOND_SLAB)
                .add(ModBlocks.DIAMOND_FENCE)
                .add(ModBlocks.DIAMOND_FENCE_GATE)
                .add(ModBlocks.POLISHED_DIAMOND_BLOCK)
                .add(ModBlocks.DIAMOND_DOOR)
                .add(ModBlocks.DIAMOND_TRAPDOOR)
                .add(ModBlocks.CALCIFIED_DIAMOND_BLOCK)
                .add(ModBlocks.DIAMOND_BRICKS)

                .add(ModBlocks.DIAMOND_BRICK_STAIRS)
                .add(ModBlocks.DIAMOND_BRICK_SLAB)
                .add(ModBlocks.DIAMOND_BRICK_WALL)
                .add(ModBlocks.DIAMOND_BRICK_FENCE)
                .add(ModBlocks.DIAMOND_BRICK_FENCE_GATE)
                .add(ModBlocks.CALCIFIED_DIAMOND_STAIRS)
                .add(ModBlocks.CALCIFIED_DIAMOND_SLAB)
                .add(ModBlocks.CALCIFIED_DIAMOND_WALL)
                .add(ModBlocks.CALCIFIED_DIAMOND_FENCE)
                .add(ModBlocks.CALCIFIED_DIAMOND_FENCE_GATE)
                .add(ModBlocks.POLISHED_DIAMOND_STAIRS)
                .add(ModBlocks.POLISHED_DIAMOND_SLAB)
                .add(ModBlocks.POLISHED_DIAMOND_WALL)
                .add(ModBlocks.POLISHED_DIAMOND_FENCE)
                .add(ModBlocks.POLISHED_DIAMOND_FENCE_GATE)

                .add(ModBlocks.IRON_STAIRS)
                .add(ModBlocks.IRON_SLAB)
                .add(ModBlocks.IRON_FENCE)
                .add(ModBlocks.IRON_FENCE_GATE)
                .add(ModBlocks.POLISHED_IRON_BLOCK)
                .add(ModBlocks.CALCIFIED_IRON_BLOCK)
                .add(ModBlocks.IRON_BRICKS)

                .add(ModBlocks.IRON_BRICK_STAIRS)
                .add(ModBlocks.IRON_BRICK_SLAB)
                .add(ModBlocks.IRON_BRICK_WALL)
                .add(ModBlocks.IRON_BRICK_FENCE)
                .add(ModBlocks.IRON_BRICK_FENCE_GATE)
                .add(ModBlocks.CALCIFIED_IRON_STAIRS)
                .add(ModBlocks.CALCIFIED_IRON_SLAB)
                .add(ModBlocks.CALCIFIED_IRON_WALL)
                .add(ModBlocks.CALCIFIED_IRON_FENCE)
                .add(ModBlocks.CALCIFIED_IRON_FENCE_GATE)
                .add(ModBlocks.POLISHED_IRON_STAIRS)
                .add(ModBlocks.POLISHED_IRON_SLAB)
                .add(ModBlocks.POLISHED_IRON_WALL)
                .add(ModBlocks.POLISHED_IRON_FENCE)
                .add(ModBlocks.POLISHED_IRON_FENCE_GATE)

                .add(ModBlocks.EMERALD_STAIRS)
                .add(ModBlocks.EMERALD_SLAB)
                .add(ModBlocks.EMERALD_FENCE)
                .add(ModBlocks.EMERALD_FENCE_GATE)
                .add(ModBlocks.POLISHED_EMERALD_BLOCK)
                .add(ModBlocks.EMERALD_DOOR)
                .add(ModBlocks.EMERALD_TRAPDOOR)
                .add(ModBlocks.CALCIFIED_EMERALD_BLOCK)
                .add(ModBlocks.CHISELED_EMERALD_BLOCK)
                .add(ModBlocks.EMERALD_BRICKS)

                .add(ModBlocks.EMERALD_BRICK_STAIRS)
                .add(ModBlocks.EMERALD_BRICK_SLAB)
                .add(ModBlocks.EMERALD_BRICK_WALL)
                .add(ModBlocks.EMERALD_BRICK_FENCE)
                .add(ModBlocks.EMERALD_BRICK_FENCE_GATE)
                .add(ModBlocks.CALCIFIED_EMERALD_STAIRS)
                .add(ModBlocks.CALCIFIED_EMERALD_SLAB)
                .add(ModBlocks.CALCIFIED_EMERALD_WALL)
                .add(ModBlocks.CALCIFIED_EMERALD_FENCE)
                .add(ModBlocks.CALCIFIED_EMERALD_FENCE_GATE)
                .add(ModBlocks.POLISHED_EMERALD_STAIRS)
                .add(ModBlocks.POLISHED_EMERALD_SLAB)
                .add(ModBlocks.POLISHED_EMERALD_WALL)
                .add(ModBlocks.POLISHED_EMERALD_FENCE)
                .add(ModBlocks.POLISHED_EMERALD_FENCE_GATE)
                .add(ModBlocks.CHISELED_EMERALD_STAIRS)
                .add(ModBlocks.CHISELED_EMERALD_SLAB)
                .add(ModBlocks.CHISELED_EMERALD_WALL)
                .add(ModBlocks.CHISELED_EMERALD_FENCE)
                .add(ModBlocks.CHISELED_EMERALD_FENCE_GATE)

                .add(ModBlocks.AMETHYST_STAIRS)
                .add(ModBlocks.AMETHYST_SLAB)
                .add(ModBlocks.AMETHYST_FENCE)
                .add(ModBlocks.AMETHYST_FENCE_GATE)
                .add(ModBlocks.POLISHED_AMETHYST_BLOCK)
                .add(ModBlocks.AMETHYST_DOOR)
                .add(ModBlocks.AMETHYST_TRAPDOOR)
                .add(ModBlocks.CALCIFIED_AMETHYST_BLOCK)
                .add(ModBlocks.AMETHYST_BRICKS)
                .add(ModBlocks.AMETHYST_BRICK_STAIRS)
                .add(ModBlocks.AMETHYST_BRICK_SLAB)
                .add(ModBlocks.AMETHYST_BRICK_WALL)
                .add(ModBlocks.AMETHYST_BRICK_FENCE)
                .add(ModBlocks.AMETHYST_BRICK_FENCE_GATE)
                .add(ModBlocks.CALCIFIED_AMETHYST_STAIRS)
                .add(ModBlocks.CALCIFIED_AMETHYST_SLAB)
                .add(ModBlocks.CALCIFIED_AMETHYST_WALL)
                .add(ModBlocks.CALCIFIED_AMETHYST_FENCE)
                .add(ModBlocks.CALCIFIED_AMETHYST_FENCE_GATE)
                .add(ModBlocks.POLISHED_AMETHYST_STAIRS)
                .add(ModBlocks.POLISHED_AMETHYST_SLAB)
                .add(ModBlocks.POLISHED_AMETHYST_WALL)
                .add(ModBlocks.POLISHED_AMETHYST_FENCE)
                .add(ModBlocks.POLISHED_AMETHYST_FENCE_GATE)

                .add(ModBlocks.CALCITE_STAIRS)
                .add(ModBlocks.CALCITE_SLAB)
                .add(ModBlocks.CALCITE_FENCE)
                .add(ModBlocks.CALCITE_FENCE_GATE)

                .add(ModBlocks.COAL_STAIRS)
                .add(ModBlocks.COAL_SLAB)
                .add(ModBlocks.COAL_FENCE)
                .add(ModBlocks.COAL_FENCE_GATE)
                .add(ModBlocks.POLISHED_COAL_BLOCK)
                .add(ModBlocks.COAL_DOOR)
                .add(ModBlocks.COAL_TRAPDOOR)
                .add(ModBlocks.CALCIFIED_COAL_BLOCK)
                .add(ModBlocks.COAL_BRICKS)
                .add(ModBlocks.COAL_BRICK_STAIRS)
                .add(ModBlocks.COAL_BRICK_SLAB)
                .add(ModBlocks.COAL_BRICK_WALL)
                .add(ModBlocks.COAL_BRICK_FENCE)
                .add(ModBlocks.COAL_BRICK_FENCE_GATE)
                .add(ModBlocks.CALCIFIED_COAL_STAIRS)
                .add(ModBlocks.CALCIFIED_COAL_SLAB)
                .add(ModBlocks.CALCIFIED_COAL_WALL)
                .add(ModBlocks.CALCIFIED_COAL_FENCE)
                .add(ModBlocks.CALCIFIED_COAL_FENCE_GATE)
                .add(ModBlocks.POLISHED_COAL_STAIRS)
                .add(ModBlocks.POLISHED_COAL_SLAB)
                .add(ModBlocks.POLISHED_COAL_WALL)
                .add(ModBlocks.POLISHED_COAL_FENCE)
                .add(ModBlocks.POLISHED_COAL_FENCE_GATE)

                .add(ModBlocks.LAPIS_STAIRS)
                .add(ModBlocks.LAPIS_SLAB)
                .add(ModBlocks.LAPIS_FENCE)
                .add(ModBlocks.LAPIS_FENCE_GATE)
                .add(ModBlocks.POLISHED_LAPIS_BLOCK)
                .add(ModBlocks.LAPIS_DOOR)
                .add(ModBlocks.LAPIS_TRAPDOOR)
                .add(ModBlocks.CALCIFIED_LAPIS_BLOCK)
                .add(ModBlocks.LAPIS_BRICKS)
                .add(ModBlocks.LAPIS_BRICK_STAIRS)
                .add(ModBlocks.LAPIS_BRICK_SLAB)
                .add(ModBlocks.LAPIS_BRICK_WALL)
                .add(ModBlocks.LAPIS_BRICK_FENCE)
                .add(ModBlocks.LAPIS_BRICK_FENCE_GATE)
                .add(ModBlocks.CALCIFIED_LAPIS_STAIRS)
                .add(ModBlocks.CALCIFIED_LAPIS_SLAB)
                .add(ModBlocks.CALCIFIED_LAPIS_WALL)
                .add(ModBlocks.CALCIFIED_LAPIS_FENCE)
                .add(ModBlocks.CALCIFIED_LAPIS_FENCE_GATE)
                .add(ModBlocks.POLISHED_LAPIS_STAIRS)
                .add(ModBlocks.POLISHED_LAPIS_SLAB)
                .add(ModBlocks.POLISHED_LAPIS_WALL)
                .add(ModBlocks.POLISHED_LAPIS_FENCE)
                .add(ModBlocks.POLISHED_LAPIS_FENCE_GATE)

                .add(ModBlocks.REDSTONE_STAIRS)
                .add(ModBlocks.REDSTONE_SLAB)
                .add(ModBlocks.REDSTONE_FENCE)
                .add(ModBlocks.REDSTONE_FENCE_GATE)
                .add(ModBlocks.POLISHED_REDSTONE_BLOCK)
                .add(ModBlocks.REDSTONE_DOOR)
                .add(ModBlocks.REDSTONE_TRAPDOOR)
                .add(ModBlocks.CALCIFIED_REDSTONE_BLOCK)
                .add(ModBlocks.REDSTONE_BRICKS)
                .add(ModBlocks.REDSTONE_BRICK_STAIRS)
                .add(ModBlocks.REDSTONE_BRICK_SLAB)
                .add(ModBlocks.REDSTONE_BRICK_WALL)
                .add(ModBlocks.REDSTONE_BRICK_FENCE)
                .add(ModBlocks.REDSTONE_BRICK_FENCE_GATE)
                .add(ModBlocks.CALCIFIED_REDSTONE_STAIRS)
                .add(ModBlocks.CALCIFIED_REDSTONE_SLAB)
                .add(ModBlocks.CALCIFIED_REDSTONE_WALL)
                .add(ModBlocks.CALCIFIED_REDSTONE_FENCE)
                .add(ModBlocks.CALCIFIED_REDSTONE_FENCE_GATE)
                .add(ModBlocks.POLISHED_REDSTONE_STAIRS)
                .add(ModBlocks.POLISHED_REDSTONE_SLAB)
                .add(ModBlocks.POLISHED_REDSTONE_WALL)
                .add(ModBlocks.POLISHED_REDSTONE_FENCE)
                .add(ModBlocks.POLISHED_REDSTONE_FENCE_GATE)

                .add(ModBlocks.NETHERITE_STAIRS)
                .add(ModBlocks.NETHERITE_SLAB)
                .add(ModBlocks.NETHERITE_FENCE)
                .add(ModBlocks.NETHERITE_FENCE_GATE)
                .add(ModBlocks.POLISHED_NETHERITE_BLOCK)
                .add(ModBlocks.NETHERITE_DOOR)
                .add(ModBlocks.NETHERITE_TRAPDOOR)
                .add(ModBlocks.CALCIFIED_NETHERITE_BLOCK)
                .add(ModBlocks.NETHERITE_BRICKS)
                .add(ModBlocks.NETHERITE_BRICK_STAIRS)
                .add(ModBlocks.NETHERITE_BRICK_SLAB)
                .add(ModBlocks.NETHERITE_BRICK_WALL)
                .add(ModBlocks.NETHERITE_BRICK_FENCE)
                .add(ModBlocks.NETHERITE_BRICK_FENCE_GATE)
                .add(ModBlocks.CALCIFIED_NETHERITE_STAIRS)
                .add(ModBlocks.CALCIFIED_NETHERITE_SLAB)
                .add(ModBlocks.CALCIFIED_NETHERITE_WALL)
                .add(ModBlocks.CALCIFIED_NETHERITE_FENCE)
                .add(ModBlocks.CALCIFIED_NETHERITE_FENCE_GATE)
                .add(ModBlocks.POLISHED_NETHERITE_STAIRS)
                .add(ModBlocks.POLISHED_NETHERITE_SLAB)
                .add(ModBlocks.POLISHED_NETHERITE_WALL)
                .add(ModBlocks.POLISHED_NETHERITE_FENCE)
                .add(ModBlocks.POLISHED_NETHERITE_FENCE_GATE)

                .add(ModBlocks.OBSIDIAN_STAIRS)
                .add(ModBlocks.OBSIDIAN_SLAB)
                .add(ModBlocks.OBSIDIAN_FENCE)
                .add(ModBlocks.OBSIDIAN_FENCE_GATE)

                .add(ModBlocks.PACKED_ICE_STAIRS)
                .add(ModBlocks.PACKED_ICE_SLAB)
                .add(ModBlocks.PACKED_ICE_FENCE)
                .add(ModBlocks.PACKED_ICE_FENCE_GATE)

                .add(ModBlocks.BLUE_ICE_STAIRS)
                .add(ModBlocks.BLUE_ICE_SLAB)
                .add(ModBlocks.BLUE_ICE_FENCE)
                .add(ModBlocks.BLUE_ICE_FENCE_GATE)

                .add(ModBlocks.ICE_STAIRS)
                .add(ModBlocks.ICE_SLAB)
                .add(ModBlocks.ICE_FENCE)
                .add(ModBlocks.ICE_FENCE_GATE)

                .add(ModBlocks.PROTECTOR_BLOCK);



        valueLookupBuilder(ModTags.Blocks.NEEDS_LEGENDARY_TOOL);
    }
}
