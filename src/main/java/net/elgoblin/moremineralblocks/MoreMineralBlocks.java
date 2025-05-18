package net.elgoblin.moremineralblocks;

import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.elgoblin.moremineralblocks.item.ModItemGroups;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreMineralBlocks implements ModInitializer {
	public static final String MOD_ID = "moremineralblocks";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}