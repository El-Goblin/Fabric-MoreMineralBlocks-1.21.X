package net.elgoblin.moremineralblocks;

import net.elgoblin.moremineralblocks.client.*;
import net.elgoblin.moremineralblocks.client.models.DimensionalPocketOpenModel;
import net.elgoblin.moremineralblocks.client.models.ModModelLayers;
import net.elgoblin.moremineralblocks.client.renderer.DimensionalPocketRenderer;
import net.elgoblin.moremineralblocks.client.renderer.ModSpecialModelRenderers;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.entity.ModEntities;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.item.custom.DimensionalPocketItem;
import net.elgoblin.moremineralblocks.util.LegendaryItemUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public class MoreMineralBlocksClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ClientEvents.registerClientEvents();
		ModKeybinds.registerModKeybinds();
		DimensionalPocketOverlay.register();
		EntityRendererRegistry.register(ModEntities.CHAOS_ORB, ThrownItemRenderer::new);

		ModModelLayers.register();
		ModSpecialModelRenderers.register();


		HudElementRegistry.attachElementAfter(
				VanillaHudElements.HOTBAR,
				Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID, "dimensional_pocket_render_main_stack"),
				(guiGraphics, deltaTracker) -> {

					Minecraft minecraft = Minecraft.getInstance();

					if (minecraft.player == null) {
						return;
					}

					ItemStack mainHand = minecraft.player.getMainHandItem();
					ItemStack offHand = minecraft.player.getOffhandItem();

					if (mainHand.is(ModItems.DIMENSIONAL_POCKET)) {
						renderSelectedStack(guiGraphics, minecraft, mainHand);
					} else if (offHand.is(ModItems.DIMENSIONAL_POCKET)) {
						renderSelectedStack(guiGraphics, minecraft, offHand);
					}
				}
		);
	}

	private void renderSelectedStack(GuiGraphicsExtractor guiGraphicsExtractor, Minecraft client, ItemStack stack) {
		if (!LegendaryItemUtils.isLinked(stack)) { return; }

		ItemStack selectedStack = DimensionalPocketCache.mainStack;
		int stackCount = DimensionalPocketCache.mainStackCount;

		if (!selectedStack.isEmpty() && client.player != null) {
			int width = client.getWindow().getGuiScaledWidth();
			int height = client.getWindow().getGuiScaledHeight();

			int hotbarLeftX = (width / 2) - 90;
			int selectedSlot = client.player.getInventory().getSelectedSlot();
			int x = hotbarLeftX + (selectedSlot * 20) + 2;
			int y = height - 19;

			if (client.player.getOffhandItem() == stack) {
				x = (width / 2) - 117;
			}

			boolean safeMode = stack.getOrDefault(ModDataComponentTypes.SAFE_MODE, false);

//			guiGraphicsExtractor.item(selectedStack, x, y);
			if (safeMode && stackCount <= DimensionalPocketCache.mainStackDuplicateCount) {
				guiGraphicsExtractor.text(
						client.font,
						String.valueOf(stackCount),
						x + 17 - client.font.width(String.valueOf(stackCount)),
						y + 9,
						0xFFD46763,
						true
				);
			}
			else {
				guiGraphicsExtractor.itemDecorations(
						client.font,
						selectedStack,
						x,
						y,
						String.valueOf(stackCount));
			}
		}
	}
}