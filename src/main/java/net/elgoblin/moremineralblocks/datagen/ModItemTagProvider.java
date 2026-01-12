package net.elgoblin.moremineralblocks.datagen;

import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
//        getOrCreateTagBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
//                .add(Items.COAL)
//                .add(Items.STICK)
//                .add(Items.APPLE);

        getOrCreateTagBuilder(ItemTags.PICKAXES).add(ModItems.LEGENDARY_PICKAXE);
        getOrCreateTagBuilder(ItemTags.SHOVELS).add(ModItems.LEGENDARY_SHOVEL);
        getOrCreateTagBuilder(ItemTags.AXES).add(ModItems.LEGENDARY_AXE);
        getOrCreateTagBuilder(ItemTags.HOES).add(ModItems.LEGENDARY_HOE);
        getOrCreateTagBuilder(ItemTags.SWORDS).add(ModItems.LEGENDARY_SWORD);
        getOrCreateTagBuilder(ItemTags.SWORDS).add(ModItems.WOODEN_LONGSWORD);
        getOrCreateTagBuilder(ItemTags.SWORDS).add(ModItems.STONE_LONGSWORD);
        getOrCreateTagBuilder(ItemTags.SWORDS).add(ModItems.GOLDEN_LONGSWORD);
        getOrCreateTagBuilder(ItemTags.SWORDS).add(ModItems.IRON_LONGSWORD);
        getOrCreateTagBuilder(ItemTags.SWORDS).add(ModItems.DIAMOND_LONGSWORD);
        getOrCreateTagBuilder(ItemTags.SWORDS).add(ModItems.NETHERITE_LONGSWORD);
        getOrCreateTagBuilder(ItemTags.SWORDS).add(ModItems.FIENDBLADE_LONGSWORD);
        getOrCreateTagBuilder(ItemTags.SWORDS).add(ModItems.FIRE_DRAGONSWORD_LONGSWORD);
        getOrCreateTagBuilder(ItemTags.SWORDS).add(ModItems.FLAMEBERGE_LONGSWORD);
        getOrCreateTagBuilder(ItemTags.SWORDS).add(ModItems.LEGENDARY_LONGSWORD);
    }
}
