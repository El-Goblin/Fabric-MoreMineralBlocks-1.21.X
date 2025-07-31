package net.elgoblin.moremineralblocks.datagen;

import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        //List<ItemConvertible> PINK_GARNET_SMELTABLES = List.of(ModItems.RAW_PINK_GARNET, ModBlocks.PINK_GARNET_ORE, ModBlocks.PINK_GARNET_DEEPSLATE_ORE);

        //offerSmelting(exporter, PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET, 0.25f, 200, "pink_garnet");
        //offerBlasting(exporter, PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET, 0.25f, 100, "pink_garnet");

        // Buscar en RecipeProvider.java (haciendo ctrl + click en offerReversibleCompactingRecipes
        //offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.PINK_GARNET, RecipeCategory.DECORATIONS, ModBlocks.PINK_GARNET_BLOCK);

        //ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.RAW_PINK_GARNET_BLOCK)
        //        .pattern("RRR")
        //        .pattern("RRR")
        //        .pattern("RRR")
        //        .input('R', ModItems.RAW_PINK_GARNET)
        //        .criterion(hasItem(ModItems.RAW_PINK_GARNET), conditionsFromItem(ModItems.RAW_PINK_GARNET))
        //        .offerTo(exporter);

        //ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PINK_GARNET, 9)
        //        .input(ModBlocks.RAW_PINK_GARNET_BLOCK)
        //        .criterion(hasItem(ModBlocks.RAW_PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.RAW_PINK_GARNET_BLOCK))
        //        .offerTo(exporter);

        // Aca le agregamos un identifier para que cambie el nombre del json
        // Por default le pone el nombre del resultado para este tipo de crafteo pero como seria el mismo que el de arriba habria quilombo
        //ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PINK_GARNET, 32)
        //        .input(ModBlocks.MAGIC_BLOCK)
        //        .criterion(hasItem(ModBlocks.MAGIC_BLOCK), conditionsFromItem(ModBlocks.MAGIC_BLOCK))
        //        .offerTo(exporter, Identifier.of(MoreMineralBlocks.MOD_ID, "raw_pink_garnet_from_magic_block"));

        //createStairsRecipe(ModBlocks.PINK_GARNET_STAIRS, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK))
        //        .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
        //        .offerTo(exporter);
        //offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PINK_GARNET_SLAB, ModBlocks.PINK_GARNET_BLOCK);
        //createFenceRecipe(ModBlocks.PINK_GARNET_FENCE, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK))
        //        .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
        //        .offerTo(exporter);
        //createFenceGateRecipe(ModBlocks.PINK_GARNET_FENCE_GATE, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK));

        // GOLD

        createStairsRecipe(ModBlocks.GOLD_STAIRS, Ingredient.ofItems(Blocks.GOLD_BLOCK)).criterion(hasItem(Blocks.GOLD_BLOCK), conditionsFromItem(Blocks.GOLD_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_STAIRS, Blocks.GOLD_BLOCK);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_SLAB, Blocks.GOLD_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_SLAB, Blocks.GOLD_BLOCK, 2);

        createFenceRecipe(ModBlocks.GOLD_FENCE, Ingredient.ofItems(Blocks.GOLD_BLOCK)).criterion(hasItem(Blocks.GOLD_BLOCK), conditionsFromItem(Blocks.GOLD_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_FENCE, Blocks.GOLD_BLOCK, 2);

        createFenceGateRecipe(ModBlocks.GOLD_FENCE_GATE, Ingredient.ofItems(Blocks.GOLD_BLOCK)).criterion(hasItem(Blocks.GOLD_BLOCK), conditionsFromItem(Blocks.GOLD_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_FENCE_GATE, Blocks.GOLD_BLOCK, 2);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_WALL, Blocks.GOLD_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_WALL, Blocks.GOLD_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_TRAPDOOR)
                .pattern("RR")
                .pattern("RR")
                .input('R', Items.GOLD_INGOT)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_DOOR, 3)
                .pattern("RR")
                .pattern("RR")
                .pattern("RR")
                .input('R',Items.GOLD_INGOT)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GOLD_BLOCK, Blocks.GOLD_BLOCK);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCIFIED_GOLD_BLOCK, 2)
                .input(Blocks.GOLD_BLOCK)
                .input(Blocks.CALCITE)
                .criterion(hasItem(Blocks.GOLD_BLOCK), conditionsFromItem(Blocks.GOLD_BLOCK))
                .criterion(hasItem(Blocks.CALCITE), conditionsFromItem(Blocks.CALCITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_BRICKS, 4)
                .pattern("RR")
                .pattern("RR")
                .input('R',Blocks.GOLD_BLOCK)
                .criterion(hasItem(Blocks.GOLD_BLOCK), conditionsFromItem(Blocks.GOLD_BLOCK))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_BRICKS, Blocks.GOLD_BLOCK);

        // DIAMOND

        createStairsRecipe(ModBlocks.DIAMOND_STAIRS, Ingredient.ofItems(Blocks.DIAMOND_BLOCK)).criterion(hasItem(Blocks.DIAMOND_BLOCK), conditionsFromItem(Blocks.DIAMOND_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIAMOND_STAIRS, Blocks.DIAMOND_BLOCK);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIAMOND_SLAB, Blocks.DIAMOND_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIAMOND_SLAB, Blocks.DIAMOND_BLOCK, 2);

        createFenceRecipe(ModBlocks.DIAMOND_FENCE, Ingredient.ofItems(Blocks.DIAMOND_BLOCK)).criterion(hasItem(Blocks.DIAMOND_BLOCK), conditionsFromItem(Blocks.DIAMOND_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIAMOND_FENCE, Blocks.DIAMOND_BLOCK, 2);

        createFenceGateRecipe(ModBlocks.DIAMOND_FENCE_GATE, Ingredient.ofItems(Blocks.DIAMOND_BLOCK)).criterion(hasItem(Blocks.DIAMOND_BLOCK), conditionsFromItem(Blocks.DIAMOND_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIAMOND_FENCE_GATE, Blocks.DIAMOND_BLOCK, 2);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIAMOND_WALL, Blocks.DIAMOND_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIAMOND_WALL, Blocks.DIAMOND_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIAMOND_TRAPDOOR)
                .pattern("RR")
                .pattern("RR")
                .input('R', Items.DIAMOND)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIAMOND_DOOR, 3)
                .pattern("RR")
                .pattern("RR")
                .pattern("RR")
                .input('R',Items.DIAMOND)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_DIAMOND_BLOCK, Blocks.DIAMOND_BLOCK);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCIFIED_DIAMOND_BLOCK, 2)
                .input(Blocks.DIAMOND_BLOCK)
                .input(Blocks.CALCITE)
                .criterion(hasItem(Blocks.DIAMOND_BLOCK), conditionsFromItem(Blocks.DIAMOND_BLOCK))
                .criterion(hasItem(Blocks.CALCITE), conditionsFromItem(Blocks.CALCITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIAMOND_BRICKS, 4)
                .pattern("RR")
                .pattern("RR")
                .input('R',Blocks.DIAMOND_BLOCK)
                .criterion(hasItem(Blocks.DIAMOND_BLOCK), conditionsFromItem(Blocks.DIAMOND_BLOCK))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIAMOND_BRICKS, Blocks.DIAMOND_BLOCK);

        // IRON

        createStairsRecipe(ModBlocks.IRON_STAIRS, Ingredient.ofItems(Blocks.IRON_BLOCK)).criterion(hasItem(Blocks.IRON_BLOCK), conditionsFromItem(Blocks.IRON_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_STAIRS, Blocks.IRON_BLOCK);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_SLAB, Blocks.IRON_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_SLAB, Blocks.IRON_BLOCK, 2);

        createFenceRecipe(ModBlocks.IRON_FENCE, Ingredient.ofItems(Blocks.IRON_BLOCK)).criterion(hasItem(Blocks.IRON_BLOCK), conditionsFromItem(Blocks.IRON_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_FENCE, Blocks.IRON_BLOCK, 2);

        createFenceGateRecipe(ModBlocks.IRON_FENCE_GATE, Ingredient.ofItems(Blocks.IRON_BLOCK)).criterion(hasItem(Blocks.IRON_BLOCK), conditionsFromItem(Blocks.IRON_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_FENCE_GATE, Blocks.IRON_BLOCK, 2);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_WALL, Blocks.IRON_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_WALL, Blocks.IRON_BLOCK);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_IRON_BLOCK, Blocks.IRON_BLOCK);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCIFIED_IRON_BLOCK, 2)
                .input(Blocks.IRON_BLOCK)
                .input(Blocks.CALCITE)
                .criterion(hasItem(Blocks.IRON_BLOCK), conditionsFromItem(Blocks.IRON_BLOCK))
                .criterion(hasItem(Blocks.CALCITE), conditionsFromItem(Blocks.CALCITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_BRICKS, 4)
                .pattern("RR")
                .pattern("RR")
                .input('R',Blocks.IRON_BLOCK)
                .criterion(hasItem(Blocks.IRON_BLOCK), conditionsFromItem(Blocks.IRON_BLOCK))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_BRICKS, Blocks.IRON_BLOCK);

        // EMERALD

        createStairsRecipe(ModBlocks.EMERALD_STAIRS, Ingredient.ofItems(Blocks.EMERALD_BLOCK)).criterion(hasItem(Blocks.EMERALD_BLOCK), conditionsFromItem(Blocks.EMERALD_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.EMERALD_STAIRS, Blocks.EMERALD_BLOCK);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.EMERALD_SLAB, Blocks.EMERALD_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.EMERALD_SLAB, Blocks.EMERALD_BLOCK, 2);

        createFenceRecipe(ModBlocks.EMERALD_FENCE, Ingredient.ofItems(Blocks.EMERALD_BLOCK)).criterion(hasItem(Blocks.EMERALD_BLOCK), conditionsFromItem(Blocks.EMERALD_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.EMERALD_FENCE, Blocks.EMERALD_BLOCK, 2);

        createFenceGateRecipe(ModBlocks.EMERALD_FENCE_GATE, Ingredient.ofItems(Blocks.EMERALD_BLOCK)).criterion(hasItem(Blocks.EMERALD_BLOCK), conditionsFromItem(Blocks.EMERALD_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.EMERALD_FENCE_GATE, Blocks.EMERALD_BLOCK, 2);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.EMERALD_WALL, Blocks.EMERALD_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.EMERALD_WALL, Blocks.EMERALD_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.EMERALD_TRAPDOOR)
                .pattern("RR")
                .pattern("RR")
                .input('R', Items.EMERALD)
                .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.EMERALD_DOOR, 3)
                .pattern("RR")
                .pattern("RR")
                .pattern("RR")
                .input('R',Items.EMERALD)
                .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_EMERALD_BLOCK, Blocks.EMERALD_BLOCK);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_EMERALD_BLOCK, Blocks.EMERALD_BLOCK);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCIFIED_EMERALD_BLOCK, 2)
                .input(Blocks.EMERALD_BLOCK)
                .input(Blocks.CALCITE)
                .criterion(hasItem(Blocks.EMERALD_BLOCK), conditionsFromItem(Blocks.EMERALD_BLOCK))
                .criterion(hasItem(Blocks.CALCITE), conditionsFromItem(Blocks.CALCITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.EMERALD_BRICKS, 4)
                .pattern("RR")
                .pattern("RR")
                .input('R',Blocks.EMERALD_BLOCK)
                .criterion(hasItem(Blocks.EMERALD_BLOCK), conditionsFromItem(Blocks.EMERALD_BLOCK))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.EMERALD_BRICKS, Blocks.EMERALD_BLOCK);

        // LAPIS

        createStairsRecipe(ModBlocks.LAPIS_STAIRS, Ingredient.ofItems(Blocks.LAPIS_BLOCK)).criterion(hasItem(Blocks.LAPIS_BLOCK), conditionsFromItem(Blocks.LAPIS_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAPIS_STAIRS, Blocks.LAPIS_BLOCK);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAPIS_SLAB, Blocks.LAPIS_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAPIS_SLAB, Blocks.LAPIS_BLOCK, 2);

        createFenceRecipe(ModBlocks.LAPIS_FENCE, Ingredient.ofItems(Blocks.LAPIS_BLOCK)).criterion(hasItem(Blocks.LAPIS_BLOCK), conditionsFromItem(Blocks.LAPIS_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAPIS_FENCE, Blocks.LAPIS_BLOCK, 2);

        createFenceGateRecipe(ModBlocks.LAPIS_FENCE_GATE, Ingredient.ofItems(Blocks.LAPIS_BLOCK)).criterion(hasItem(Blocks.LAPIS_BLOCK), conditionsFromItem(Blocks.LAPIS_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAPIS_FENCE_GATE, Blocks.LAPIS_BLOCK, 2);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAPIS_WALL, Blocks.LAPIS_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAPIS_WALL, Blocks.LAPIS_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAPIS_TRAPDOOR)
                .pattern("RR")
                .pattern("RR")
                .input('R', Items.LAPIS_LAZULI)
                .criterion(hasItem(Items.LAPIS_LAZULI), conditionsFromItem(Items.LAPIS_LAZULI))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAPIS_DOOR, 3)
                .pattern("RR")
                .pattern("RR")
                .pattern("RR")
                .input('R',Items.LAPIS_LAZULI)
                .criterion(hasItem(Items.LAPIS_LAZULI), conditionsFromItem(Items.LAPIS_LAZULI))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LAPIS_BLOCK, Blocks.LAPIS_BLOCK);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCIFIED_LAPIS_BLOCK, 2)
                .input(Blocks.LAPIS_BLOCK)
                .input(Blocks.CALCITE)
                .criterion(hasItem(Blocks.LAPIS_BLOCK), conditionsFromItem(Blocks.LAPIS_BLOCK))
                .criterion(hasItem(Blocks.CALCITE), conditionsFromItem(Blocks.CALCITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAPIS_BRICKS, 4)
                .pattern("RR")
                .pattern("RR")
                .input('R',Blocks.LAPIS_BLOCK)
                .criterion(hasItem(Blocks.LAPIS_BLOCK), conditionsFromItem(Blocks.LAPIS_BLOCK))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAPIS_BRICKS, Blocks.LAPIS_BLOCK);

        // COAL

        createStairsRecipe(ModBlocks.COAL_STAIRS, Ingredient.ofItems(Blocks.COAL_BLOCK)).criterion(hasItem(Blocks.COAL_BLOCK), conditionsFromItem(Blocks.COAL_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COAL_STAIRS, Blocks.COAL_BLOCK);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COAL_SLAB, Blocks.COAL_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COAL_SLAB, Blocks.COAL_BLOCK, 2);

        createFenceRecipe(ModBlocks.COAL_FENCE, Ingredient.ofItems(Blocks.COAL_BLOCK)).criterion(hasItem(Blocks.COAL_BLOCK), conditionsFromItem(Blocks.COAL_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COAL_FENCE, Blocks.COAL_BLOCK, 2);

        createFenceGateRecipe(ModBlocks.COAL_FENCE_GATE, Ingredient.ofItems(Blocks.COAL_BLOCK)).criterion(hasItem(Blocks.COAL_BLOCK), conditionsFromItem(Blocks.COAL_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COAL_FENCE_GATE, Blocks.COAL_BLOCK, 2);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COAL_WALL, Blocks.COAL_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COAL_WALL, Blocks.COAL_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COAL_TRAPDOOR)
                .pattern("RR")
                .pattern("RR")
                .input('R', Items.COAL)
                .criterion(hasItem(Items.COAL), conditionsFromItem(Items.COAL))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COAL_DOOR, 3)
                .pattern("RR")
                .pattern("RR")
                .pattern("RR")
                .input('R',Items.COAL)
                .criterion(hasItem(Items.COAL), conditionsFromItem(Items.COAL))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_COAL_BLOCK, Blocks.COAL_BLOCK);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCIFIED_COAL_BLOCK, 2)
                .input(Blocks.COAL_BLOCK)
                .input(Blocks.CALCITE)
                .criterion(hasItem(Blocks.COAL_BLOCK), conditionsFromItem(Blocks.COAL_BLOCK))
                .criterion(hasItem(Blocks.CALCITE), conditionsFromItem(Blocks.CALCITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COAL_BRICKS, 4)
                .pattern("RR")
                .pattern("RR")
                .input('R',Blocks.COAL_BLOCK)
                .criterion(hasItem(Blocks.COAL_BLOCK), conditionsFromItem(Blocks.COAL_BLOCK))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COAL_BRICKS, Blocks.COAL_BLOCK);

        // CALCITE

        createStairsRecipe(ModBlocks.CALCITE_STAIRS, Ingredient.ofItems(Blocks.CALCITE)).criterion(hasItem(Blocks.CALCITE), conditionsFromItem(Blocks.CALCITE)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_STAIRS, Blocks.CALCITE);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_SLAB, Blocks.CALCITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_SLAB, Blocks.CALCITE, 2);

        createFenceRecipe(ModBlocks.CALCITE_FENCE, Ingredient.ofItems(Blocks.CALCITE)).criterion(hasItem(Blocks.CALCITE), conditionsFromItem(Blocks.CALCITE)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_FENCE, Blocks.CALCITE, 2);

        createFenceGateRecipe(ModBlocks.CALCITE_FENCE_GATE, Ingredient.ofItems(Blocks.CALCITE)).criterion(hasItem(Blocks.CALCITE), conditionsFromItem(Blocks.CALCITE)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_FENCE_GATE, Blocks.CALCITE, 2);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_WALL, Blocks.CALCITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_WALL, Blocks.CALCITE);

        // NETHERITE

        createStairsRecipe(ModBlocks.NETHERITE_STAIRS, Ingredient.ofItems(Blocks.NETHERITE_BLOCK)).criterion(hasItem(Blocks.NETHERITE_BLOCK), conditionsFromItem(Blocks.NETHERITE_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHERITE_STAIRS, Blocks.NETHERITE_BLOCK);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHERITE_SLAB, Blocks.NETHERITE_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHERITE_SLAB, Blocks.NETHERITE_BLOCK, 2);

        createFenceRecipe(ModBlocks.NETHERITE_FENCE, Ingredient.ofItems(Blocks.NETHERITE_BLOCK)).criterion(hasItem(Blocks.NETHERITE_BLOCK), conditionsFromItem(Blocks.NETHERITE_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHERITE_FENCE, Blocks.NETHERITE_BLOCK, 2);

        createFenceGateRecipe(ModBlocks.NETHERITE_FENCE_GATE, Ingredient.ofItems(Blocks.NETHERITE_BLOCK)).criterion(hasItem(Blocks.NETHERITE_BLOCK), conditionsFromItem(Blocks.NETHERITE_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHERITE_FENCE_GATE, Blocks.NETHERITE_BLOCK, 2);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHERITE_WALL, Blocks.NETHERITE_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHERITE_WALL, Blocks.NETHERITE_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHERITE_TRAPDOOR)
                .pattern("RR")
                .pattern("RR")
                .input('R', Items.NETHERITE_INGOT)
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHERITE_DOOR, 3)
                .pattern("RR")
                .pattern("RR")
                .pattern("RR")
                .input('R',Items.NETHERITE_INGOT)
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_NETHERITE_BLOCK, Blocks.NETHERITE_BLOCK);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCIFIED_NETHERITE_BLOCK, 2)
                .input(Blocks.NETHERITE_BLOCK)
                .input(Blocks.CALCITE)
                .criterion(hasItem(Blocks.NETHERITE_BLOCK), conditionsFromItem(Blocks.NETHERITE_BLOCK))
                .criterion(hasItem(Blocks.CALCITE), conditionsFromItem(Blocks.CALCITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHERITE_BRICKS, 4)
                .pattern("RR")
                .pattern("RR")
                .input('R',Blocks.NETHERITE_BLOCK)
                .criterion(hasItem(Blocks.NETHERITE_BLOCK), conditionsFromItem(Blocks.NETHERITE_BLOCK))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHERITE_BRICKS, Blocks.NETHERITE_BLOCK);

        // AMETHYST

        createStairsRecipe(ModBlocks.AMETHYST_STAIRS, Ingredient.ofItems(Blocks.AMETHYST_BLOCK)).criterion(hasItem(Blocks.AMETHYST_BLOCK), conditionsFromItem(Blocks.AMETHYST_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.AMETHYST_STAIRS, Blocks.AMETHYST_BLOCK);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.AMETHYST_SLAB, Blocks.AMETHYST_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.AMETHYST_SLAB, Blocks.AMETHYST_BLOCK, 2);

        createFenceRecipe(ModBlocks.AMETHYST_FENCE, Ingredient.ofItems(Blocks.AMETHYST_BLOCK)).criterion(hasItem(Blocks.AMETHYST_BLOCK), conditionsFromItem(Blocks.AMETHYST_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.AMETHYST_FENCE, Blocks.AMETHYST_BLOCK, 2);

        createFenceGateRecipe(ModBlocks.AMETHYST_FENCE_GATE, Ingredient.ofItems(Blocks.AMETHYST_BLOCK)).criterion(hasItem(Blocks.AMETHYST_BLOCK), conditionsFromItem(Blocks.AMETHYST_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.AMETHYST_FENCE_GATE, Blocks.AMETHYST_BLOCK, 2);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.AMETHYST_WALL, Blocks.AMETHYST_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.AMETHYST_WALL, Blocks.AMETHYST_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.AMETHYST_TRAPDOOR)
                .pattern("RRR")
                .pattern("RRR")
                .input('R', Items.AMETHYST_SHARD)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.AMETHYST_DOOR, 3)
                .pattern("RR")
                .pattern("RR")
                .pattern("RR")
                .input('R',Items.AMETHYST_SHARD)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.AMETHYST_SHARD, 4)
                .input(Blocks.AMETHYST_BLOCK)
                .criterion(hasItem(Blocks.AMETHYST_BLOCK), conditionsFromItem(Blocks.AMETHYST_BLOCK))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_AMETHYST_BLOCK, Blocks.AMETHYST_BLOCK);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCIFIED_AMETHYST_BLOCK, 2)
                .input(Blocks.AMETHYST_BLOCK)
                .input(Blocks.CALCITE)
                .criterion(hasItem(Blocks.AMETHYST_BLOCK), conditionsFromItem(Blocks.AMETHYST_BLOCK))
                .criterion(hasItem(Blocks.CALCITE), conditionsFromItem(Blocks.CALCITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.AMETHYST_BRICKS, 4)
                .pattern("RR")
                .pattern("RR")
                .input('R',Blocks.AMETHYST_BLOCK)
                .criterion(hasItem(Blocks.AMETHYST_BLOCK), conditionsFromItem(Blocks.AMETHYST_BLOCK))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.AMETHYST_BRICKS, Blocks.AMETHYST_BLOCK);

        // REDSTONE

        createStairsRecipe(ModBlocks.REDSTONE_STAIRS, Ingredient.ofItems(Blocks.REDSTONE_BLOCK)).criterion(hasItem(Blocks.REDSTONE_BLOCK), conditionsFromItem(Blocks.REDSTONE_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.REDSTONE_STAIRS, Blocks.REDSTONE_BLOCK);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.REDSTONE_SLAB, Blocks.REDSTONE_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.REDSTONE_SLAB, Blocks.REDSTONE_BLOCK, 2);

        createFenceRecipe(ModBlocks.REDSTONE_FENCE, Ingredient.ofItems(Blocks.REDSTONE_BLOCK)).criterion(hasItem(Blocks.REDSTONE_BLOCK), conditionsFromItem(Blocks.REDSTONE_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.REDSTONE_FENCE, Blocks.REDSTONE_BLOCK, 2);

        createFenceGateRecipe(ModBlocks.REDSTONE_FENCE_GATE, Ingredient.ofItems(Blocks.REDSTONE_BLOCK)).criterion(hasItem(Blocks.REDSTONE_BLOCK), conditionsFromItem(Blocks.REDSTONE_BLOCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.REDSTONE_FENCE_GATE, Blocks.REDSTONE_BLOCK, 2);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.REDSTONE_WALL, Blocks.REDSTONE_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.REDSTONE_WALL, Blocks.REDSTONE_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.REDSTONE_TRAPDOOR)
                .pattern("RR")
                .pattern("RR")
                .input('R', Items.REDSTONE)
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.REDSTONE_DOOR, 3)
                .pattern("RR")
                .pattern("RR")
                .pattern("RR")
                .input('R',Items.REDSTONE)
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_REDSTONE_BLOCK, Blocks.REDSTONE_BLOCK);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCIFIED_REDSTONE_BLOCK, 2)
                .input(Blocks.REDSTONE_BLOCK)
                .input(Blocks.CALCITE)
                .criterion(hasItem(Blocks.REDSTONE_BLOCK), conditionsFromItem(Blocks.REDSTONE_BLOCK))
                .criterion(hasItem(Blocks.CALCITE), conditionsFromItem(Blocks.CALCITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.REDSTONE_BRICKS, 4)
                .pattern("RR")
                .pattern("RR")
                .input('R',Blocks.REDSTONE_BLOCK)
                .criterion(hasItem(Blocks.REDSTONE_BLOCK), conditionsFromItem(Blocks.REDSTONE_BLOCK))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.REDSTONE_BRICKS, Blocks.REDSTONE_BLOCK);

        // OBSIDIAN

        createStairsRecipe(ModBlocks.OBSIDIAN_STAIRS, Ingredient.ofItems(Blocks.OBSIDIAN)).criterion(hasItem(Blocks.OBSIDIAN), conditionsFromItem(Blocks.OBSIDIAN)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OBSIDIAN_STAIRS, Blocks.OBSIDIAN);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OBSIDIAN_SLAB, Blocks.OBSIDIAN);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OBSIDIAN_SLAB, Blocks.OBSIDIAN, 2);

        createFenceRecipe(ModBlocks.OBSIDIAN_FENCE, Ingredient.ofItems(Blocks.OBSIDIAN)).criterion(hasItem(Blocks.OBSIDIAN), conditionsFromItem(Blocks.OBSIDIAN)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OBSIDIAN_FENCE, Blocks.OBSIDIAN, 2);

        createFenceGateRecipe(ModBlocks.OBSIDIAN_FENCE_GATE, Ingredient.ofItems(Blocks.OBSIDIAN)).criterion(hasItem(Blocks.OBSIDIAN), conditionsFromItem(Blocks.OBSIDIAN)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OBSIDIAN_FENCE_GATE, Blocks.OBSIDIAN, 2);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OBSIDIAN_WALL, Blocks.OBSIDIAN);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OBSIDIAN_WALL, Blocks.OBSIDIAN);

        // BEDROCK

        createStairsRecipe(ModBlocks.BEDROCK_STAIRS, Ingredient.ofItems(Blocks.BEDROCK)).criterion(hasItem(Blocks.BEDROCK), conditionsFromItem(Blocks.BEDROCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BEDROCK_STAIRS, Blocks.BEDROCK);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BEDROCK_SLAB, Blocks.BEDROCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BEDROCK_SLAB, Blocks.BEDROCK, 2);

        createFenceRecipe(ModBlocks.BEDROCK_FENCE, Ingredient.ofItems(Blocks.BEDROCK)).criterion(hasItem(Blocks.BEDROCK), conditionsFromItem(Blocks.BEDROCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BEDROCK_FENCE, Blocks.BEDROCK, 2);

        createFenceGateRecipe(ModBlocks.BEDROCK_FENCE_GATE, Ingredient.ofItems(Blocks.BEDROCK)).criterion(hasItem(Blocks.BEDROCK), conditionsFromItem(Blocks.BEDROCK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BEDROCK_FENCE_GATE, Blocks.BEDROCK, 2);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BEDROCK_WALL, Blocks.BEDROCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BEDROCK_WALL, Blocks.BEDROCK);

        // SCULK

        createStairsRecipe(ModBlocks.SCULK_STAIRS, Ingredient.ofItems(Blocks.SCULK)).criterion(hasItem(Blocks.SCULK), conditionsFromItem(Blocks.SCULK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SCULK_STAIRS, Blocks.SCULK);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SCULK_SLAB, Blocks.SCULK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SCULK_SLAB, Blocks.SCULK, 2);

        createFenceRecipe(ModBlocks.SCULK_FENCE, Ingredient.ofItems(Blocks.SCULK)).criterion(hasItem(Blocks.SCULK), conditionsFromItem(Blocks.SCULK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SCULK_FENCE, Blocks.SCULK, 2);

        createFenceGateRecipe(ModBlocks.SCULK_FENCE_GATE, Ingredient.ofItems(Blocks.SCULK)).criterion(hasItem(Blocks.SCULK), conditionsFromItem(Blocks.SCULK)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SCULK_FENCE_GATE, Blocks.SCULK, 2);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SCULK_WALL, Blocks.SCULK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SCULK_WALL, Blocks.SCULK);

        // PACKED ICE

        createStairsRecipe(ModBlocks.PACKED_ICE_STAIRS, Ingredient.ofItems(Blocks.PACKED_ICE)).criterion(hasItem(Blocks.PACKED_ICE), conditionsFromItem(Blocks.PACKED_ICE)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_ICE_STAIRS, Blocks.PACKED_ICE);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_ICE_SLAB, Blocks.PACKED_ICE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_ICE_SLAB, Blocks.PACKED_ICE, 2);

        createFenceRecipe(ModBlocks.PACKED_ICE_FENCE, Ingredient.ofItems(Blocks.PACKED_ICE)).criterion(hasItem(Blocks.PACKED_ICE), conditionsFromItem(Blocks.PACKED_ICE)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_ICE_FENCE, Blocks.PACKED_ICE, 2);

        createFenceGateRecipe(ModBlocks.PACKED_ICE_FENCE_GATE, Ingredient.ofItems(Blocks.PACKED_ICE)).criterion(hasItem(Blocks.PACKED_ICE), conditionsFromItem(Blocks.PACKED_ICE)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_ICE_FENCE_GATE, Blocks.PACKED_ICE, 2);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_ICE_WALL, Blocks.PACKED_ICE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_ICE_WALL, Blocks.PACKED_ICE);

        // BLUE ICE

        createStairsRecipe(ModBlocks.BLUE_ICE_STAIRS, Ingredient.ofItems(Blocks.BLUE_ICE)).criterion(hasItem(Blocks.BLUE_ICE), conditionsFromItem(Blocks.BLUE_ICE)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_ICE_STAIRS, Blocks.BLUE_ICE);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_ICE_SLAB, Blocks.BLUE_ICE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_ICE_SLAB, Blocks.BLUE_ICE, 2);

        createFenceRecipe(ModBlocks.BLUE_ICE_FENCE, Ingredient.ofItems(Blocks.BLUE_ICE)).criterion(hasItem(Blocks.BLUE_ICE), conditionsFromItem(Blocks.BLUE_ICE)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_ICE_FENCE, Blocks.BLUE_ICE, 2);

        createFenceGateRecipe(ModBlocks.BLUE_ICE_FENCE_GATE, Ingredient.ofItems(Blocks.BLUE_ICE)).criterion(hasItem(Blocks.BLUE_ICE), conditionsFromItem(Blocks.BLUE_ICE)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_ICE_FENCE_GATE, Blocks.BLUE_ICE, 2);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_ICE_WALL, Blocks.BLUE_ICE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_ICE_WALL, Blocks.BLUE_ICE);

        // ICE

        createStairsRecipe(ModBlocks.ICE_STAIRS, Ingredient.ofItems(Blocks.ICE)).criterion(hasItem(Blocks.ICE), conditionsFromItem(Blocks.ICE)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_STAIRS, Blocks.ICE);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_SLAB, Blocks.ICE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_SLAB, Blocks.ICE, 2);

        createFenceRecipe(ModBlocks.ICE_FENCE, Ingredient.ofItems(Blocks.ICE)).criterion(hasItem(Blocks.ICE), conditionsFromItem(Blocks.ICE)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_FENCE, Blocks.ICE, 2);

        createFenceGateRecipe(ModBlocks.ICE_FENCE_GATE, Ingredient.ofItems(Blocks.ICE)).criterion(hasItem(Blocks.ICE), conditionsFromItem(Blocks.ICE)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_FENCE_GATE, Blocks.ICE, 2);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_WALL, Blocks.ICE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_WALL, Blocks.ICE);

        // PRISMARINE

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PRISMARINE_BRICK_WALL, Blocks.PRISMARINE_BRICKS);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_PRISMARINE_WALL, Blocks.DARK_PRISMARINE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PRISMARINE_BRICK_WALL, Blocks.PRISMARINE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_PRISMARINE_WALL, Blocks.DARK_PRISMARINE);




    }
}
