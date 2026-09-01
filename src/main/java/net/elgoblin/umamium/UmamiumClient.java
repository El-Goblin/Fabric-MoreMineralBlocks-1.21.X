package net.elgoblin.umamium;

import net.elgoblin.umamium.client.*;
import net.elgoblin.umamium.client.models.ModModelLayers;
import net.elgoblin.umamium.client.renderer.ModSpecialModelRenderers;
import net.elgoblin.umamium.component.ModDataComponentTypes;
import net.elgoblin.umamium.entity.ModEntities;
import net.elgoblin.umamium.item.ModItems;
import net.elgoblin.umamium.util.LegendaryItemUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public class UmamiumClient implements ClientModInitializer {

	private long blockPlacedCount = 0;

	@Override
	public void onInitializeClient() {
		ClientEvents.registerClientEvents();
		ModKeybinds.registerModKeybinds();
		DimensionalPocketOverlay.register();
		EntityRendererRegistry.register(ModEntities.CHAOS_ORB, ThrownItemRenderer::new);

		ModModelLayers.register();
		ModSpecialModelRenderers.register();

//		ParticleProviderRegistry.getInstance().register(
//				ModParticles.CHAOS_ORB_FRAGILE_PARTICLE,
//				2
//		);



//		ItemEvents.USE_ON.register((context) -> {
//			Player player = context.getPlayer();
//			System.out.println("entre");
//			if (player == null) {
//				return null;
//			}
//			System.out.println("player no null");
//
//			Long seed = player.getAttached(ModAttachmentTypes.ADYACENT_BLOCK_PLACING);
//			if (seed == null) {
//				return null;
//			}
//			System.out.println("seed no null");
//			seed = seed + blockPlacedCount;
//			blockPlacedCount++;
//
//			ItemStack stack = context.getItemInHand();
//			if (!(stack.getItem() instanceof BlockItem blockItem)) {
//				return null;
//			}
//
//			List<Vec3i> positions = new ArrayList<>(List.of(
//					new Vec3i(1, 0, 0),
//					new Vec3i(0, 1, 0),
//					new Vec3i(0, 0, 1),
//					new Vec3i(-1, 0, 0),
//					new Vec3i(0, -1, 0),
//					new Vec3i(0, 0, -1)
//			));
//
//			Collections.shuffle(positions, new Random(seed));
//
//			for (Vec3i offset : positions) {
//				BlockPos newPos = context.getClickedPos().offset(offset);
//
//				BlockHitResult hit = new BlockHitResult(
//						Vec3.atCenterOf(newPos),
//						context.getClickedFace(),
//						newPos,
//						context.isInside()
//				);
//
//				BlockPlaceContext placeContext = new BlockPlaceContext(player, context.getHand(), stack, hit);
//				System.out.println("Client " + placeContext.getClickedPos());
//
//				InteractionResult result = blockItem.place(placeContext);
//
//				if (result.consumesAction()) {
//					return InteractionResult.SUCCESS;
//				}
//			}
//
//			return null;
//		});

		HudElementRegistry.attachElementAfter(
				VanillaHudElements.HOTBAR,
				Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "dimensional_pocket_render_main_stack"),
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