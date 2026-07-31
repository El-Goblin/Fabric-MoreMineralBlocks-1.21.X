package net.elgoblin.moremineralblocks;

import net.elgoblin.moremineralblocks.datagen.ModModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MoreMineralBlocksDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		 var pack = fabricDataGenerator.createPack();

		 pack.addProvider(ModModelProvider::new);
	}
}
