package net.elgoblin.moremineralblocks;

import net.elgoblin.moremineralblocks.client.*;
import net.elgoblin.moremineralblocks.client.models.DimensionalPocketOpenModel;
import net.elgoblin.moremineralblocks.client.models.ModModelLayers;
import net.elgoblin.moremineralblocks.client.renderer.DimensionalPocketRenderer;
import net.elgoblin.moremineralblocks.client.renderer.ModSpecialModelRenderers;
import net.elgoblin.moremineralblocks.component.ModAttachmentTypes;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.entity.ModEntities;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.item.custom.DimensionalPocketItem;
import net.elgoblin.moremineralblocks.particle.ModParticles;
import net.elgoblin.moremineralblocks.util.LegendaryItemUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteSet;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.fabricmc.fabric.api.event.player.ItemEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MoreMineralBlocksClient implements ClientModInitializer {

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