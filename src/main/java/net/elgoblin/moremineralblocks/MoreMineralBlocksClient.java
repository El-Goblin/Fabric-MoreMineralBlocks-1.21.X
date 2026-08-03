package net.elgoblin.moremineralblocks;

import com.mojang.blaze3d.platform.InputConstants;
import net.elgoblin.moremineralblocks.client.ClientEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class MoreMineralBlocksClient implements ClientModInitializer {

	public static final KeyMapping.Category LEGENDARY_TOOLS = KeyMapping.Category.register(
			Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID, "legendary_tools")
	);

	public static KeyMapping SWITCH_ENCHANTMENTS_TOGGLE_SAFE_MODE;
	public static KeyMapping intraGroupScroll;
	public static KeyMapping interGroupScroll;

	@Override
	public void onInitializeClient() {
		SWITCH_ENCHANTMENTS_TOGGLE_SAFE_MODE = KeyMappingHelper.registerKeyMapping(
				new KeyMapping(
						"key." + MoreMineralBlocks.MOD_ID +".switch_enchantments_toggle_safe_mode",
						InputConstants.Type.KEYSYM,
						GLFW.GLFW_KEY_K,
						LEGENDARY_TOOLS
				)
		);

		intraGroupScroll = KeyMappingHelper.registerKeyMapping(
				new KeyMapping(
						"key." + MoreMineralBlocks.MOD_ID +".intra_group_scroll",
						InputConstants.Type.KEYSYM,
						GLFW.GLFW_KEY_TAB,
						LEGENDARY_TOOLS
				)
		);

		interGroupScroll = KeyMappingHelper.registerKeyMapping(
				new KeyMapping(
						"key." + MoreMineralBlocks.MOD_ID +".inter_group_scroll",
						InputConstants.Type.KEYSYM,
						GLFW.GLFW_KEY_GRAVE_ACCENT,
						LEGENDARY_TOOLS
				)
		);
		ClientEvents.registerClientEvents();
	}
}