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
//        valueLookupBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
//                .add(Items.COAL)
//                .add(Items.STICK)
//                .add(Items.APPLE);

        valueLookupBuilder(ModTags.Items.LEGENDARY_REPAIR);

        valueLookupBuilder(ItemTags.PICKAXES).add(ModItems.LEGENDARY_PICKAXE);
        valueLookupBuilder(ItemTags.SHOVELS).add(ModItems.LEGENDARY_SHOVEL);
        valueLookupBuilder(ItemTags.AXES).add(ModItems.LEGENDARY_AXE);
        valueLookupBuilder(ItemTags.HOES).add(ModItems.LEGENDARY_HOE);
        valueLookupBuilder(ItemTags.SWORDS).add(ModItems.LEGENDARY_SWORD);
        valueLookupBuilder(ItemTags.SWORDS).add(ModItems.WOODEN_LONGSWORD);
        valueLookupBuilder(ItemTags.SWORDS).add(ModItems.STONE_LONGSWORD);
        valueLookupBuilder(ItemTags.SWORDS).add(ModItems.GOLDEN_LONGSWORD);
        valueLookupBuilder(ItemTags.SWORDS).add(ModItems.IRON_LONGSWORD);
        valueLookupBuilder(ItemTags.SWORDS).add(ModItems.DIAMOND_LONGSWORD);
        valueLookupBuilder(ItemTags.SWORDS).add(ModItems.NETHERITE_LONGSWORD);
        valueLookupBuilder(ItemTags.SWORDS).add(ModItems.FIENDBLADE_LONGSWORD);
        valueLookupBuilder(ItemTags.SWORDS).add(ModItems.FIRE_DRAGONSWORD_LONGSWORD);
        valueLookupBuilder(ItemTags.SWORDS).add(ModItems.FLAMEBERGE_LONGSWORD);
        valueLookupBuilder(ItemTags.SWORDS).add(ModItems.LEGENDARY_LONGSWORD);
    }
}
