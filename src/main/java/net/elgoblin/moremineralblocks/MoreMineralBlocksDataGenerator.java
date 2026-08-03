package net.elgoblin.moremineralblocks;

import net.elgoblin.moremineralblocks.datagen.*;
import net.elgoblin.moremineralblocks.enchantment.ModEnchantments;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class MoreMineralBlocksDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		 var pack = fabricDataGenerator.createPack();

		 pack.addProvider(ModModelProvider::new);
		 pack.addProvider(ModBlockTagsProvider::new);
		 pack.addProvider(ModItemTagsProvider::new);
		 pack.addProvider(ModBlockLootTableProvider::new);
		 pack.addProvider(ModRecipeProvider::new);
		 pack.addProvider(ModRegistryDataGeneration::new);
	}

	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);
	}
}
