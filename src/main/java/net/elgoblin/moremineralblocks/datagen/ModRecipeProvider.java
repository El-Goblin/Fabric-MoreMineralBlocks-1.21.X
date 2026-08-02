package net.elgoblin.moremineralblocks.datagen;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {

                List<ItemLike> COBBLESTONE = List.of(Items.COBBLESTONE);
                List<ItemLike> STONE = List.of(Items.STONE);
                List<ItemLike> NETHERRACK = List.of(Items.NETHERRACK);

                this.oreBlasting(COBBLESTONE, RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, Items.STONE, 0.11f, 100, "cobblestone");
                this.oreBlasting(STONE, RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, Items.SMOOTH_STONE, 0.11f, 100, "stone");
                this.oreBlasting(NETHERRACK, RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, Items.NETHER_BRICK, 0.11f, 100, "netherrack");

                // FLINT

                nineBlockStorageRecipes(RecipeCategory.MISC, Items.FLINT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLINT_BLOCK);
                shapeless(RecipeCategory.BUILDING_BLOCKS, Items.FLINT, 9)
                        .requires(ModBlocks.FLINT_BLOCK)
                        .unlockedBy(getHasName(Items.FLINT), has(ModBlocks.FLINT_BLOCK))
                        .group("flint")
                        .save(output, "flint_from_flint_block");

                shaped(RecipeCategory.MISC, ModBlocks.FLINT_SET.stairs(), 4)
                        .pattern("F  ")
                        .pattern("FF ")
                        .pattern("FFF")
                        .define('F', ModBlocks.FLINT_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.FLINT_BLOCK), has(ModBlocks.FLINT_BLOCK))
                        .group("flint")
                        .save(output);

                shaped(RecipeCategory.MISC, ModBlocks.FLINT_SET.slab(), 6)
                        .pattern("FFF")
                        .define('F', ModBlocks.FLINT_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.FLINT_BLOCK), has(ModBlocks.FLINT_BLOCK))
                        .group("flint")
                        .save(output);

                shaped(RecipeCategory.MISC, ModBlocks.FLINT_SET.wall(), 6)
                        .pattern("FFF")
                        .pattern("FFF")
                        .define('F', ModBlocks.FLINT_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.FLINT_BLOCK), has(ModBlocks.FLINT_BLOCK))
                        .group("flint")
                        .save(output);

                shapeless(RecipeCategory.MISC, ModItems.MAGIC_MIRROR, 1)
                        .requires(ModItems.REFLECTIVE_MIRROR)
                        .unlockedBy(getHasName(ModItems.REFLECTIVE_MIRROR), has(ModItems.REFLECTIVE_MIRROR))
                        .group("magic_mirror")
                        .save(output);
            }
        };
    }

    @Override
    public String getName() {
        return MoreMineralBlocks.MOD_ID + " Recipes";
    }
}
