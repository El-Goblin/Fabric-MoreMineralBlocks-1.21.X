package net.elgoblin.moremineralblocks.datagen;

import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    private record BlockPack(
            ItemConvertible stairs,
            ItemConvertible slab,
            ItemConvertible wall,
            ItemConvertible fence,
            ItemConvertible fenceGate,
            Block baseBlock

    ) {
        public void createPackRecipes(RecipeExporter exporter) {
            createStairsRecipe(this.stairs, Ingredient.ofItems(this.baseBlock)).criterion(hasItem(this.baseBlock), conditionsFromItem(this.baseBlock)).offerTo(exporter);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, this.stairs, this.baseBlock);
            offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, this.slab, this.baseBlock);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, this.slab, this.baseBlock, 2);
            createFenceRecipe(this.fence, Ingredient.ofItems(this.baseBlock)).criterion(hasItem(this.baseBlock), conditionsFromItem(this.baseBlock)).offerTo(exporter);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, this.fence, this.baseBlock, 2);
            createFenceGateRecipe(this.fenceGate, Ingredient.ofItems(this.baseBlock)).criterion(hasItem(this.baseBlock), conditionsFromItem(this.baseBlock)).offerTo(exporter);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, this.fenceGate, this.baseBlock, 2);
            offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, this.wall, this.baseBlock);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, this.wall, this.baseBlock);
        }
    }

    private record DoorPack(
            ItemConvertible trapdoor,
            ItemConvertible door,
            Item trapDoorIngredient,
            Item doorIngredient
    ) {
        public void createPackRecipes(RecipeExporter exporter) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, this.trapdoor)
                    .pattern("RR")
                    .pattern("RR")
                    .input('R', this.trapDoorIngredient)
                    .criterion(hasItem(this.trapDoorIngredient), conditionsFromItem(this.trapDoorIngredient))
                    .offerTo(exporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, this.door, 3)
                    .pattern("RR")
                    .pattern("RR")
                    .pattern("RR")
                    .input('R',this.doorIngredient)
                    .criterion(hasItem(this.doorIngredient), conditionsFromItem(this.doorIngredient))
                    .offerTo(exporter);
        }
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

        offerBlasting(exporter, List.of(Blocks.COBBLESTONE), RecipeCategory.BUILDING_BLOCKS, Blocks.STONE, 0.10F, 100, "stone");
        offerBlasting(exporter, List.of(Blocks.NETHERRACK), RecipeCategory.BUILDING_BLOCKS, Items.NETHER_BRICK, 0.10F, 100, "nether_brick");

        BlockPack goldPack = new BlockPack(
                ModBlocks.GOLD_STAIRS,
                ModBlocks.GOLD_SLAB,
                ModBlocks.GOLD_WALL,
                ModBlocks.GOLD_FENCE,
                ModBlocks.GOLD_FENCE_GATE,
                Blocks.GOLD_BLOCK
        );
        goldPack.createPackRecipes(exporter);

        BlockPack goldBrickPack = new BlockPack(
                ModBlocks.GOLD_BRICK_STAIRS,
                ModBlocks.GOLD_BRICK_SLAB,
                ModBlocks.GOLD_BRICK_WALL,
                ModBlocks.GOLD_BRICK_FENCE,
                ModBlocks.GOLD_BRICK_FENCE_GATE,
                ModBlocks.GOLD_BRICKS
        );
        goldBrickPack.createPackRecipes(exporter);

        BlockPack calcifiedGoldPack = new BlockPack(
                ModBlocks.CALCIFIED_GOLD_STAIRS,
                ModBlocks.CALCIFIED_GOLD_SLAB,
                ModBlocks.CALCIFIED_GOLD_WALL,
                ModBlocks.CALCIFIED_GOLD_FENCE,
                ModBlocks.CALCIFIED_GOLD_FENCE_GATE,
                ModBlocks.CALCIFIED_GOLD_BLOCK
        );
        calcifiedGoldPack.createPackRecipes(exporter);

        BlockPack polishedGoldPack = new BlockPack(
                ModBlocks.POLISHED_GOLD_STAIRS,
                ModBlocks.POLISHED_GOLD_SLAB,
                ModBlocks.POLISHED_GOLD_WALL,
                ModBlocks.POLISHED_GOLD_FENCE,
                ModBlocks.POLISHED_GOLD_FENCE_GATE,
                ModBlocks.POLISHED_GOLD_BLOCK
        );
        polishedGoldPack.createPackRecipes(exporter);

        DoorPack goldDoorPack = new DoorPack(ModBlocks.GOLD_TRAPDOOR, ModBlocks.GOLD_DOOR, Items.GOLD_INGOT, Items.GOLD_INGOT);
        goldDoorPack.createPackRecipes(exporter);

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

        BlockPack diamondPack = new BlockPack(
                ModBlocks.DIAMOND_STAIRS,
                ModBlocks.DIAMOND_SLAB,
                ModBlocks.DIAMOND_WALL,
                ModBlocks.DIAMOND_FENCE,
                ModBlocks.DIAMOND_FENCE_GATE,
                Blocks.DIAMOND_BLOCK
        );
        diamondPack.createPackRecipes(exporter);

        BlockPack diamondBrickPack = new BlockPack(
                ModBlocks.DIAMOND_BRICK_STAIRS,
                ModBlocks.DIAMOND_BRICK_SLAB,
                ModBlocks.DIAMOND_BRICK_WALL,
                ModBlocks.DIAMOND_BRICK_FENCE,
                ModBlocks.DIAMOND_BRICK_FENCE_GATE,
                ModBlocks.DIAMOND_BRICKS
        );
        diamondBrickPack.createPackRecipes(exporter);

        BlockPack calcifiedDiamondPack = new BlockPack(
                ModBlocks.CALCIFIED_DIAMOND_STAIRS,
                ModBlocks.CALCIFIED_DIAMOND_SLAB,
                ModBlocks.CALCIFIED_DIAMOND_WALL,
                ModBlocks.CALCIFIED_DIAMOND_FENCE,
                ModBlocks.CALCIFIED_DIAMOND_FENCE_GATE,
                ModBlocks.CALCIFIED_DIAMOND_BLOCK
        );
        calcifiedDiamondPack.createPackRecipes(exporter);

        BlockPack polishedDiamondPack = new BlockPack(
                ModBlocks.POLISHED_DIAMOND_STAIRS,
                ModBlocks.POLISHED_DIAMOND_SLAB,
                ModBlocks.POLISHED_DIAMOND_WALL,
                ModBlocks.POLISHED_DIAMOND_FENCE,
                ModBlocks.POLISHED_DIAMOND_FENCE_GATE,
                ModBlocks.POLISHED_DIAMOND_BLOCK
        );
        polishedDiamondPack.createPackRecipes(exporter);

        DoorPack diamondDoorPack = new DoorPack(ModBlocks.DIAMOND_TRAPDOOR, ModBlocks.DIAMOND_DOOR, Items.DIAMOND, Items.DIAMOND);
        diamondDoorPack.createPackRecipes(exporter);

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

        BlockPack ironPack = new BlockPack(
                ModBlocks.IRON_STAIRS,
                ModBlocks.IRON_SLAB,
                ModBlocks.IRON_WALL,
                ModBlocks.IRON_FENCE,
                ModBlocks.IRON_FENCE_GATE,
                Blocks.IRON_BLOCK
        );
        ironPack.createPackRecipes(exporter);

        BlockPack ironBrickPack = new BlockPack(
                ModBlocks.IRON_BRICK_STAIRS,
                ModBlocks.IRON_BRICK_SLAB,
                ModBlocks.IRON_BRICK_WALL,
                ModBlocks.IRON_BRICK_FENCE,
                ModBlocks.IRON_BRICK_FENCE_GATE,
                ModBlocks.IRON_BRICKS
        );
        ironBrickPack.createPackRecipes(exporter);

        BlockPack calcifiedIronPack = new BlockPack(
                ModBlocks.CALCIFIED_IRON_STAIRS,
                ModBlocks.CALCIFIED_IRON_SLAB,
                ModBlocks.CALCIFIED_IRON_WALL,
                ModBlocks.CALCIFIED_IRON_FENCE,
                ModBlocks.CALCIFIED_IRON_FENCE_GATE,
                ModBlocks.CALCIFIED_IRON_BLOCK
        );
        calcifiedIronPack.createPackRecipes(exporter);

        BlockPack polishedIronPack = new BlockPack(
                ModBlocks.POLISHED_IRON_STAIRS,
                ModBlocks.POLISHED_IRON_SLAB,
                ModBlocks.POLISHED_IRON_WALL,
                ModBlocks.POLISHED_IRON_FENCE,
                ModBlocks.POLISHED_IRON_FENCE_GATE,
                ModBlocks.POLISHED_IRON_BLOCK
        );
        polishedIronPack.createPackRecipes(exporter);

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

        BlockPack emeraldPack = new BlockPack(
                ModBlocks.EMERALD_STAIRS,
                ModBlocks.EMERALD_SLAB,
                ModBlocks.EMERALD_WALL,
                ModBlocks.EMERALD_FENCE,
                ModBlocks.EMERALD_FENCE_GATE,
                Blocks.EMERALD_BLOCK
        );
        emeraldPack.createPackRecipes(exporter);

        BlockPack emeraldBrickPack = new BlockPack(
                ModBlocks.EMERALD_BRICK_STAIRS,
                ModBlocks.EMERALD_BRICK_SLAB,
                ModBlocks.EMERALD_BRICK_WALL,
                ModBlocks.EMERALD_BRICK_FENCE,
                ModBlocks.EMERALD_BRICK_FENCE_GATE,
                ModBlocks.EMERALD_BRICKS
        );
        emeraldBrickPack.createPackRecipes(exporter);

        BlockPack calcifiedEmeraldPack = new BlockPack(
                ModBlocks.CALCIFIED_EMERALD_STAIRS,
                ModBlocks.CALCIFIED_EMERALD_SLAB,
                ModBlocks.CALCIFIED_EMERALD_WALL,
                ModBlocks.CALCIFIED_EMERALD_FENCE,
                ModBlocks.CALCIFIED_EMERALD_FENCE_GATE,
                ModBlocks.CALCIFIED_EMERALD_BLOCK
        );
        calcifiedEmeraldPack.createPackRecipes(exporter);

        BlockPack polishedEmeraldPack = new BlockPack(
                ModBlocks.POLISHED_EMERALD_STAIRS,
                ModBlocks.POLISHED_EMERALD_SLAB,
                ModBlocks.POLISHED_EMERALD_WALL,
                ModBlocks.POLISHED_EMERALD_FENCE,
                ModBlocks.POLISHED_EMERALD_FENCE_GATE,
                ModBlocks.POLISHED_EMERALD_BLOCK
        );
        polishedEmeraldPack.createPackRecipes(exporter);

        BlockPack chiseledEmeraldPack = new BlockPack(
                ModBlocks.CHISELED_EMERALD_STAIRS,
                ModBlocks.CHISELED_EMERALD_SLAB,
                ModBlocks.CHISELED_EMERALD_WALL,
                ModBlocks.CHISELED_EMERALD_FENCE,
                ModBlocks.CHISELED_EMERALD_FENCE_GATE,
                ModBlocks.CHISELED_EMERALD_BLOCK
        );
        chiseledEmeraldPack.createPackRecipes(exporter);

        DoorPack emeraldDoorPack = new DoorPack(ModBlocks.EMERALD_TRAPDOOR, ModBlocks.EMERALD_DOOR, Items.EMERALD, Items.EMERALD);
        emeraldDoorPack.createPackRecipes(exporter);

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

        BlockPack lapisPack = new BlockPack(
                ModBlocks.LAPIS_STAIRS,
                ModBlocks.LAPIS_SLAB,
                ModBlocks.LAPIS_WALL,
                ModBlocks.LAPIS_FENCE,
                ModBlocks.LAPIS_FENCE_GATE,
                Blocks.LAPIS_BLOCK
        );
        lapisPack.createPackRecipes(exporter);

        BlockPack lapisBrickPack = new BlockPack(
                ModBlocks.LAPIS_BRICK_STAIRS,
                ModBlocks.LAPIS_BRICK_SLAB,
                ModBlocks.LAPIS_BRICK_WALL,
                ModBlocks.LAPIS_BRICK_FENCE,
                ModBlocks.LAPIS_BRICK_FENCE_GATE,
                ModBlocks.LAPIS_BRICKS
        );
        lapisBrickPack.createPackRecipes(exporter);

        BlockPack calcifiedLapisPack = new BlockPack(
                ModBlocks.CALCIFIED_LAPIS_STAIRS,
                ModBlocks.CALCIFIED_LAPIS_SLAB,
                ModBlocks.CALCIFIED_LAPIS_WALL,
                ModBlocks.CALCIFIED_LAPIS_FENCE,
                ModBlocks.CALCIFIED_LAPIS_FENCE_GATE,
                ModBlocks.CALCIFIED_LAPIS_BLOCK
        );
        calcifiedLapisPack.createPackRecipes(exporter);

        BlockPack polishedLapisPack = new BlockPack(
                ModBlocks.POLISHED_LAPIS_STAIRS,
                ModBlocks.POLISHED_LAPIS_SLAB,
                ModBlocks.POLISHED_LAPIS_WALL,
                ModBlocks.POLISHED_LAPIS_FENCE,
                ModBlocks.POLISHED_LAPIS_FENCE_GATE,
                ModBlocks.POLISHED_LAPIS_BLOCK
        );
        polishedLapisPack.createPackRecipes(exporter);

        DoorPack lapisDoorPack = new DoorPack(ModBlocks.LAPIS_TRAPDOOR, ModBlocks.LAPIS_DOOR, Items.LAPIS_LAZULI, Items.LAPIS_LAZULI);
        lapisDoorPack.createPackRecipes(exporter);

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

        BlockPack coalPack = new BlockPack(
                ModBlocks.COAL_STAIRS,
                ModBlocks.COAL_SLAB,
                ModBlocks.COAL_WALL,
                ModBlocks.COAL_FENCE,
                ModBlocks.COAL_FENCE_GATE,
                Blocks.COAL_BLOCK
        );
        coalPack.createPackRecipes(exporter);

        BlockPack coalBrickPack = new BlockPack(
                ModBlocks.COAL_BRICK_STAIRS,
                ModBlocks.COAL_BRICK_SLAB,
                ModBlocks.COAL_BRICK_WALL,
                ModBlocks.COAL_BRICK_FENCE,
                ModBlocks.COAL_BRICK_FENCE_GATE,
                ModBlocks.COAL_BRICKS
        );
        coalBrickPack.createPackRecipes(exporter);

        BlockPack calcifiedCoalPack = new BlockPack(
                ModBlocks.CALCIFIED_COAL_STAIRS,
                ModBlocks.CALCIFIED_COAL_SLAB,
                ModBlocks.CALCIFIED_COAL_WALL,
                ModBlocks.CALCIFIED_COAL_FENCE,
                ModBlocks.CALCIFIED_COAL_FENCE_GATE,
                ModBlocks.CALCIFIED_COAL_BLOCK
        );
        calcifiedCoalPack.createPackRecipes(exporter);

        BlockPack polishedCoalPack = new BlockPack(
                ModBlocks.POLISHED_COAL_STAIRS,
                ModBlocks.POLISHED_COAL_SLAB,
                ModBlocks.POLISHED_COAL_WALL,
                ModBlocks.POLISHED_COAL_FENCE,
                ModBlocks.POLISHED_COAL_FENCE_GATE,
                ModBlocks.POLISHED_COAL_BLOCK
        );
        polishedCoalPack.createPackRecipes(exporter);

        DoorPack coalDoorPack = new DoorPack(ModBlocks.COAL_TRAPDOOR, ModBlocks.COAL_DOOR, Items.COAL, Items.COAL);
        coalDoorPack.createPackRecipes(exporter);

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

        BlockPack calcitePack = new BlockPack(
                ModBlocks.CALCITE_STAIRS,
                ModBlocks.CALCITE_SLAB,
                ModBlocks.CALCITE_WALL,
                ModBlocks.CALCITE_FENCE,
                ModBlocks.CALCITE_FENCE_GATE,
                Blocks.CALCITE
        );
        calcitePack.createPackRecipes(exporter);

        // NETHERITE

        BlockPack netheritePack = new BlockPack(
                ModBlocks.NETHERITE_STAIRS,
                ModBlocks.NETHERITE_SLAB,
                ModBlocks.NETHERITE_WALL,
                ModBlocks.NETHERITE_FENCE,
                ModBlocks.NETHERITE_FENCE_GATE,
                Blocks.NETHERITE_BLOCK
        );
        netheritePack.createPackRecipes(exporter);

        BlockPack netheriteBrickPack = new BlockPack(
                ModBlocks.NETHERITE_BRICK_STAIRS,
                ModBlocks.NETHERITE_BRICK_SLAB,
                ModBlocks.NETHERITE_BRICK_WALL,
                ModBlocks.NETHERITE_BRICK_FENCE,
                ModBlocks.NETHERITE_BRICK_FENCE_GATE,
                ModBlocks.NETHERITE_BRICKS
        );
        netheriteBrickPack.createPackRecipes(exporter);

        BlockPack calcifiedNetheritePack = new BlockPack(
                ModBlocks.CALCIFIED_NETHERITE_STAIRS,
                ModBlocks.CALCIFIED_NETHERITE_SLAB,
                ModBlocks.CALCIFIED_NETHERITE_WALL,
                ModBlocks.CALCIFIED_NETHERITE_FENCE,
                ModBlocks.CALCIFIED_NETHERITE_FENCE_GATE,
                ModBlocks.CALCIFIED_NETHERITE_BLOCK
        );
        calcifiedNetheritePack.createPackRecipes(exporter);

        BlockPack polishedNetheritePack = new BlockPack(
                ModBlocks.POLISHED_NETHERITE_STAIRS,
                ModBlocks.POLISHED_NETHERITE_SLAB,
                ModBlocks.POLISHED_NETHERITE_WALL,
                ModBlocks.POLISHED_NETHERITE_FENCE,
                ModBlocks.POLISHED_NETHERITE_FENCE_GATE,
                ModBlocks.POLISHED_NETHERITE_BLOCK
        );
        polishedNetheritePack.createPackRecipes(exporter);

        DoorPack netheriteDoorPack = new DoorPack(ModBlocks.NETHERITE_TRAPDOOR, ModBlocks.NETHERITE_DOOR, Items.NETHERITE_INGOT, Items.NETHERITE_INGOT);
        netheriteDoorPack.createPackRecipes(exporter);

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

        BlockPack amethystPack = new BlockPack(
                ModBlocks.AMETHYST_STAIRS,
                ModBlocks.AMETHYST_SLAB,
                ModBlocks.AMETHYST_WALL,
                ModBlocks.AMETHYST_FENCE,
                ModBlocks.AMETHYST_FENCE_GATE,
                Blocks.AMETHYST_BLOCK
        );
        amethystPack.createPackRecipes(exporter);

        BlockPack amethystBrickPack = new BlockPack(
                ModBlocks.AMETHYST_BRICK_STAIRS,
                ModBlocks.AMETHYST_BRICK_SLAB,
                ModBlocks.AMETHYST_BRICK_WALL,
                ModBlocks.AMETHYST_BRICK_FENCE,
                ModBlocks.AMETHYST_BRICK_FENCE_GATE,
                ModBlocks.AMETHYST_BRICKS
        );
        amethystBrickPack.createPackRecipes(exporter);

        BlockPack calcifiedAmethystPack = new BlockPack(
                ModBlocks.CALCIFIED_AMETHYST_STAIRS,
                ModBlocks.CALCIFIED_AMETHYST_SLAB,
                ModBlocks.CALCIFIED_AMETHYST_WALL,
                ModBlocks.CALCIFIED_AMETHYST_FENCE,
                ModBlocks.CALCIFIED_AMETHYST_FENCE_GATE,
                ModBlocks.CALCIFIED_AMETHYST_BLOCK
        );
        calcifiedAmethystPack.createPackRecipes(exporter);

        BlockPack polishedAmethystPack = new BlockPack(
                ModBlocks.POLISHED_AMETHYST_STAIRS,
                ModBlocks.POLISHED_AMETHYST_SLAB,
                ModBlocks.POLISHED_AMETHYST_WALL,
                ModBlocks.POLISHED_AMETHYST_FENCE,
                ModBlocks.POLISHED_AMETHYST_FENCE_GATE,
                ModBlocks.POLISHED_AMETHYST_BLOCK
        );
        polishedAmethystPack.createPackRecipes(exporter);

        // ESTA ES DISTINTA PORQUE SE HACEN BLOQUES DE AMATISTA DE LA OTRA FORMA
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

        BlockPack redstonePack = new BlockPack(
                ModBlocks.REDSTONE_STAIRS,
                ModBlocks.REDSTONE_SLAB,
                ModBlocks.REDSTONE_WALL,
                ModBlocks.REDSTONE_FENCE,
                ModBlocks.REDSTONE_FENCE_GATE,
                Blocks.REDSTONE_BLOCK
        );
        redstonePack.createPackRecipes(exporter);

        BlockPack redstoneBrickPack = new BlockPack(
                ModBlocks.REDSTONE_BRICK_STAIRS,
                ModBlocks.REDSTONE_BRICK_SLAB,
                ModBlocks.REDSTONE_BRICK_WALL,
                ModBlocks.REDSTONE_BRICK_FENCE,
                ModBlocks.REDSTONE_BRICK_FENCE_GATE,
                ModBlocks.REDSTONE_BRICKS
        );
        redstoneBrickPack.createPackRecipes(exporter);

        BlockPack calcifiedRedstonePack = new BlockPack(
                ModBlocks.CALCIFIED_REDSTONE_STAIRS,
                ModBlocks.CALCIFIED_REDSTONE_SLAB,
                ModBlocks.CALCIFIED_REDSTONE_WALL,
                ModBlocks.CALCIFIED_REDSTONE_FENCE,
                ModBlocks.CALCIFIED_REDSTONE_FENCE_GATE,
                ModBlocks.CALCIFIED_REDSTONE_BLOCK
        );
        calcifiedRedstonePack.createPackRecipes(exporter);

        BlockPack polishedRedstonePack = new BlockPack(
                ModBlocks.POLISHED_REDSTONE_STAIRS,
                ModBlocks.POLISHED_REDSTONE_SLAB,
                ModBlocks.POLISHED_REDSTONE_WALL,
                ModBlocks.POLISHED_REDSTONE_FENCE,
                ModBlocks.POLISHED_REDSTONE_FENCE_GATE,
                ModBlocks.POLISHED_REDSTONE_BLOCK
        );
        polishedRedstonePack.createPackRecipes(exporter);

        DoorPack redstoneDoorPack = new DoorPack(ModBlocks.REDSTONE_TRAPDOOR, ModBlocks.REDSTONE_DOOR, Items.REDSTONE, Items.REDSTONE);
        redstoneDoorPack.createPackRecipes(exporter);

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

        BlockPack obsidianPack = new BlockPack(
                ModBlocks.OBSIDIAN_STAIRS,
                ModBlocks.OBSIDIAN_SLAB,
                ModBlocks.OBSIDIAN_WALL,
                ModBlocks.OBSIDIAN_FENCE,
                ModBlocks.OBSIDIAN_FENCE_GATE,
                Blocks.OBSIDIAN
        );
        obsidianPack.createPackRecipes(exporter);

        // BEDROCK

        BlockPack bedrockPack = new BlockPack(
                ModBlocks.BEDROCK_STAIRS,
                ModBlocks.BEDROCK_SLAB,
                ModBlocks.BEDROCK_WALL,
                ModBlocks.BEDROCK_FENCE,
                ModBlocks.BEDROCK_FENCE_GATE,
                Blocks.BEDROCK
        );
        bedrockPack.createPackRecipes(exporter);

        // SCULK

        BlockPack sculkPack = new BlockPack(
                ModBlocks.SCULK_STAIRS,
                ModBlocks.SCULK_SLAB,
                ModBlocks.SCULK_WALL,
                ModBlocks.SCULK_FENCE,
                ModBlocks.SCULK_FENCE_GATE,
                Blocks.SCULK
        );
        sculkPack.createPackRecipes(exporter);

        // PACKED ICE

        BlockPack packedIcePack = new BlockPack(
                ModBlocks.PACKED_ICE_STAIRS,
                ModBlocks.PACKED_ICE_SLAB,
                ModBlocks.PACKED_ICE_WALL,
                ModBlocks.PACKED_ICE_FENCE,
                ModBlocks.PACKED_ICE_FENCE_GATE,
                Blocks.PACKED_ICE
        );
        packedIcePack.createPackRecipes(exporter);

        // BLUE ICE

        BlockPack blueIcePack = new BlockPack(
                ModBlocks.BLUE_ICE_STAIRS,
                ModBlocks.BLUE_ICE_SLAB,
                ModBlocks.BLUE_ICE_WALL,
                ModBlocks.BLUE_ICE_FENCE,
                ModBlocks.BLUE_ICE_FENCE_GATE,
                Blocks.BLUE_ICE
        );
        blueIcePack.createPackRecipes(exporter);

        // ICE

        BlockPack icePack = new BlockPack(
                ModBlocks.ICE_STAIRS,
                ModBlocks.ICE_SLAB,
                ModBlocks.ICE_WALL,
                ModBlocks.ICE_FENCE,
                ModBlocks.ICE_FENCE_GATE,
                Blocks.ICE
        );
        icePack.createPackRecipes(exporter);

        // PRISMARINE

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PRISMARINE_BRICK_WALL, Blocks.PRISMARINE_BRICKS);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_PRISMARINE_WALL, Blocks.DARK_PRISMARINE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PRISMARINE_BRICK_WALL, Blocks.PRISMARINE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_PRISMARINE_WALL, Blocks.DARK_PRISMARINE);

        // OTHER

        ShapelessRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, ModItems.MAGIC_MIRROR, 1)
                .input(ModItems.REFLECTIVE_MIRROR)
                .criterion(hasItem(ModItems.REFLECTIVE_MIRROR), conditionsFromItem(ModItems.REFLECTIVE_MIRROR))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.WOODEN_LONGSWORD)
                .pattern("  P")
                .pattern(" P ")
                .pattern("S  ")
                .input('S', Items.WOODEN_SWORD)
                .input('P', ItemTags.PLANKS)
                .criterion(hasItem(Items.WOODEN_SWORD), conditionsFromItem(Items.WOODEN_SWORD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.STONE_LONGSWORD)
                .pattern("  C")
                .pattern(" C ")
                .pattern("S  ")
                .input('S', Items.STONE_SWORD)
                .input('C', ItemTags.STONE_TOOL_MATERIALS)
                .criterion(hasItem(Items.STONE_SWORD), conditionsFromItem(Items.STONE_SWORD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.IRON_LONGSWORD)
                .pattern("  I")
                .pattern(" I ")
                .pattern("S  ")
                .input('S', Items.IRON_SWORD)
                .input('I', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_SWORD), conditionsFromItem(Items.IRON_SWORD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.GOLDEN_LONGSWORD)
                .pattern("  G")
                .pattern(" G ")
                .pattern("S  ")
                .input('S', Items.GOLDEN_SWORD)
                .input('G', Items.GOLD_INGOT)
                .criterion(hasItem(Items.GOLDEN_SWORD), conditionsFromItem(Items.GOLDEN_SWORD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DIAMOND_LONGSWORD)
                .pattern("  D")
                .pattern(" D ")
                .pattern("S  ")
                .input('S', Items.DIAMOND_SWORD)
                .input('D', Items.DIAMOND)
                .criterion(hasItem(Items.DIAMOND_SWORD), conditionsFromItem(Items.DIAMOND_SWORD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.PROTECTOR_BLOCK)
                .pattern("DED")
                .pattern("DHD")
                .pattern("ABA")
                .input('D', Blocks.DIAMOND_BLOCK)
                .input('E', Items.END_CRYSTAL)
                .input('H', Items.HEAVY_CORE)
                .input('B', Blocks.BEACON)
                .input('A', Blocks.RESPAWN_ANCHOR)
                .criterion(hasItem(Blocks.DIAMOND_BLOCK), conditionsFromItem(Blocks.DIAMOND_BLOCK))
                .criterion(hasItem(Blocks.BEACON), conditionsFromItem(Blocks.BEACON))
                .criterion(hasItem(Blocks.RESPAWN_ANCHOR), conditionsFromItem(Blocks.RESPAWN_ANCHOR))
                .criterion(hasItem(Items.END_CRYSTAL), conditionsFromItem(Items.END_CRYSTAL))
                .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(Items.HEAVY_CORE))
                .offerTo(exporter);

        offerNetheriteUpgradeRecipe(exporter, ModItems.DIAMOND_LONGSWORD, RecipeCategory.COMBAT,ModItems.NETHERITE_LONGSWORD);
    }
}
