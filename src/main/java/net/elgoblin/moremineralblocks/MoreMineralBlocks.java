package net.elgoblin.moremineralblocks;

import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.elgoblin.moremineralblocks.block.entity.ModBlockEntities;
import net.elgoblin.moremineralblocks.client.InfiniteItemClientCache;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.effect.ModEffects;
import net.elgoblin.moremineralblocks.enchantment.ModEnchantmentEffects;
import net.elgoblin.moremineralblocks.entity.ModEntities;
//import net.elgoblin.moremineralblocks.entity.custom.DevilmonEntity;
//import net.elgoblin.moremineralblocks.entity.custom.MantisEntity;
import net.elgoblin.moremineralblocks.item.ModItemGroups;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.item.ModToolMaterials;
import net.elgoblin.moremineralblocks.networking.*;
import net.elgoblin.moremineralblocks.particle.ModParticles;
import net.elgoblin.moremineralblocks.structure.MoreMineralBlocksStructure;
import net.elgoblin.moremineralblocks.structure.MoreMineralBlocksStructurePlacement;
import net.elgoblin.moremineralblocks.terrain.TerrainManager;
import net.elgoblin.moremineralblocks.util.ModLootTableModifiers;
import net.elgoblin.moremineralblocks.util.ModTags;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.event.GameEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoreMineralBlocks implements ModInitializer{
	public static final String MOD_ID = "moremineralblocks";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
//	private static StateSaverAndLoader stateSaverAndLoader;

	@Override
	public void onInitialize() {

		ModDataComponentTypes.registerDataComponentTypes();
		ModItemGroups.registerItemGroups();

		PayloadTypeRegistry.playC2S().register(SwitchEnchantmentToggleSafeModePayload.ID, SwitchEnchantmentToggleSafeModePayload.CODEC);

		PayloadTypeRegistry.playC2S().register(InfiniteItemstackV2ChestContentsQueryPayload.ID, InfiniteItemstackV2ChestContentsQueryPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(InfiniteItemstackV2ChestContentsResponsePayload.ID, InfiniteItemstackV2ChestContentsResponsePayload.CODEC);
		PayloadTypeRegistry.playC2S().register(InfiniteItemSelectColorPayload.ID, InfiniteItemSelectColorPayload.CODEC);
		PayloadTypeRegistry.playC2S().register(ToggleSlotPayload.ID, ToggleSlotPayload.CODEC);
		PayloadTypeRegistry.playC2S().register(InfiniteItemStackIntraGroupScrollPayload.ID, InfiniteItemStackIntraGroupScrollPayload.CODEC);
		PayloadTypeRegistry.playC2S().register(InfiniteItemStackInterGroupScrollPayload.ID, InfiniteItemStackInterGroupScrollPayload.CODEC);


		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEffects.registerEffects();
		ModEntities.registerModEntities();
		ModLootTableModifiers.modifyLootTables();
		ModParticles.registerParticles();
		ModBlockEntities.registerBlockEntities();
		MoreMineralBlocksStructurePlacement.registerStructurePlacementTypes();
		MoreMineralBlocksStructure.registerStructureTypes();
		ModEnchantmentEffects.registerEnchantmentEffects();
		InfiniteItemClientCache.init();



		BiomeModifications.create(Identifier.of("savanna_temp_change"))
				.add(ModificationPhase.REPLACEMENTS,
						BiomeSelectors.includeByKey(BiomeKeys.SAVANNA),
						(biomeSelectionContext, biomeModificationContext) -> {

							biomeModificationContext.getWeather().setTemperature(0.5f);
							biomeModificationContext.getWeather().setDownfall(1f);

						}
				);

		BiomeModifications.create(Identifier.of("savanna_temp_change"))
				.add(ModificationPhase.REPLACEMENTS,
						BiomeSelectors.includeByKey(BiomeKeys.SAVANNA_PLATEAU),
						(biomeSelectionContext, biomeModificationContext) -> {

							biomeModificationContext.getWeather().setTemperature(0.5f);
							biomeModificationContext.getWeather().setDownfall(1f);

						}
				);

		BiomeModifications.create(Identifier.of("savanna_temp_change"))
				.add(ModificationPhase.REPLACEMENTS,
						BiomeSelectors.includeByKey(BiomeKeys.WINDSWEPT_SAVANNA),
						(biomeSelectionContext, biomeModificationContext) -> {

							biomeModificationContext.getWeather().setTemperature(0.5f);
							biomeModificationContext.getWeather().setDownfall(1f);

						}
				);


//
//		FabricDefaultAttributeRegistry.register(ModEntities.MANTIS, MantisEntity.createAttributes());
//		FabricDefaultAttributeRegistry.register(ModEntities.DEVILMON, DevilmonEntity.createAttributes());

		ServerPlayNetworking.registerGlobalReceiver(SwitchEnchantmentToggleSafeModePayload.ID,
				(payload, context) -> {
			    	context.server().execute(() -> {
						ServerPlayerEntity player = context.player();
						ItemStack mainHandStack = player.getMainHandStack();
						if (mainHandStack.isIn(ModTags.Items.LEGENDARY_TOOLS)) {
							ModToolMaterials.advanceEnchantments(mainHandStack);
						}
						if (mainHandStack.isOf(ModItems.DIMENSION_POCKET)) {
							boolean currentSafeMode = mainHandStack.getOrDefault(ModDataComponentTypes.SAFE_MODE, false);
							mainHandStack.set(ModDataComponentTypes.SAFE_MODE, !currentSafeMode);
						}
					});
				});

		ServerPlayNetworking.registerGlobalReceiver(InfiniteItemstackV2ChestContentsQueryPayload.ID,
				(payload, context) -> {
					context.server().execute(() -> {
						ItemStack infiniteItem = payload.itemStack();

						if (!infiniteItem.isOf(ModItems.DIMENSION_POCKET)) return;

						BlockPos storagePos = infiniteItem.get(ModDataComponentTypes.LINKED_CHEST);
						Identifier dimension = infiniteItem.get(ModDataComponentTypes.SERVERWORLD);

						if (storagePos == null || dimension == null) {
							sendEmptyPayload(context);
							return;
						}

						ServerWorld targetWorld = context.server().getWorld(RegistryKey.of(RegistryKeys.WORLD, dimension));
						if (targetWorld == null || !targetWorld.isPosLoaded(storagePos)) {
							sendEmptyPayload(context);
							return;
						}

						if (!(targetWorld.getBlockEntity(storagePos) instanceof Inventory inventory)) {
							sendEmptyPayload(context);
							return;
						}

						Integer interGroupPointer = infiniteItem.get(ModDataComponentTypes.INTER_GROUP_POINTER);
						List<Integer> intraGroupPointers = infiniteItem.get(ModDataComponentTypes.INTRA_GROUP_POINTERS);

						if (interGroupPointer == null || intraGroupPointers == null) {
							sendEmptyPayload(context);
							return;
						}

						int currentGroup = interGroupPointer;
						int currentIntraIdx = intraGroupPointers.get(currentGroup);

						int prevGroup = findNextGroup(infiniteItem, currentGroup, -1);
						int nextGroup = findNextGroup(infiniteItem, currentGroup, 1);

						int prevGroupIntraIdx = intraGroupPointers.get(prevGroup);
						int nextGroupIntraIdx = intraGroupPointers.get(nextGroup);

						ItemStack mainStack = getStackFromGroup(infiniteItem, inventory, currentGroup, currentIntraIdx);

						int currentGroupPrevIndex = findNextItem(infiniteItem, inventory, currentGroup, currentIntraIdx, -1);
						int currentGroupNextIndex = findNextItem(infiniteItem, inventory, currentGroup, currentIntraIdx, 1);

						ItemStack prevHorizStack = getStackFromGroup(infiniteItem, inventory, currentGroup, currentGroupPrevIndex);
						ItemStack nextHorizStack = getStackFromGroup(infiniteItem, inventory, currentGroup, currentGroupNextIndex);

						ItemStack prevVertStack = getStackFromGroup(infiniteItem, inventory, prevGroup, prevGroupIntraIdx);
						ItemStack nextVertStack = getStackFromGroup(infiniteItem, inventory, nextGroup, nextGroupIntraIdx);

						context.responseSender().sendPacket(new InfiniteItemstackV2ChestContentsResponsePayload(
								mainStack,
								prevHorizStack,
								nextHorizStack,
								prevVertStack,
								nextVertStack
						));
					});
				});

		ServerPlayNetworking.registerGlobalReceiver(InfiniteItemStackIntraGroupScrollPayload.ID,
				(payload, context) -> {
					ServerPlayerEntity player = context.player();
					ItemStack activeHand = ItemStack.EMPTY;
					if (player.getMainHandStack().isOf(ModItems.DIMENSION_POCKET)) {activeHand = player.getMainHandStack();}
					else if (player.getOffHandStack().isOf(ModItems.DIMENSION_POCKET)) {activeHand = player.getOffHandStack();}

					if (activeHand.isEmpty()) {return;}

					BlockPos storagePosition = activeHand.get(ModDataComponentTypes.LINKED_CHEST);
					Identifier dimension = activeHand.get(ModDataComponentTypes.SERVERWORLD);
					Integer interGroupPointer = activeHand.get(ModDataComponentTypes.INTER_GROUP_POINTER);
					List<Integer> intraGroupPointers = activeHand.get(ModDataComponentTypes.INTRA_GROUP_POINTERS);

					if (storagePosition == null || dimension == null || interGroupPointer == null || intraGroupPointers == null) {return;}

					ServerWorld targetWorld = context.server().getWorld(RegistryKey.of(RegistryKeys.WORLD, dimension));
					if (targetWorld == null || !targetWorld.isPosLoaded(storagePosition)) {return;}

					if (targetWorld.getBlockEntity(storagePosition) instanceof Inventory inventory) {

						List<Integer> group = activeHand.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(interGroupPointer)));
						if (group == null) {return;}

						int oldPointer = intraGroupPointers.get(interGroupPointer);

						List<Integer> newIntraGroupPointers = new ArrayList<>(intraGroupPointers);
						int newPointer = newIntraGroupPointers.get(interGroupPointer) + payload.scroll();
						if (newPointer < 0) {newPointer = group.size()-1;}
						if (newPointer >= group.size()) {newPointer = 0;}

						while (!group.isEmpty() && inventory.getStack(group.get(newPointer)).isEmpty() && newPointer != oldPointer) {
							newPointer = newPointer + payload.scroll();
							if (newPointer < 0) {newPointer = group.size()-1;}
							if (newPointer >= group.size()) {newPointer = 0;}
						}
						newIntraGroupPointers.set(interGroupPointer, newPointer);
						activeHand.set(ModDataComponentTypes.INTRA_GROUP_POINTERS, newIntraGroupPointers);
					}
		        });

		ServerPlayNetworking.registerGlobalReceiver(InfiniteItemStackInterGroupScrollPayload.ID,
				(payload, context) -> {
					ServerPlayerEntity player = context.player();
					ItemStack activeHand = ItemStack.EMPTY;
					if (player.getMainHandStack().isOf(ModItems.DIMENSION_POCKET)) {
						activeHand = player.getMainHandStack();
					}
					else if (player.getOffHandStack().isOf(ModItems.DIMENSION_POCKET)) {
						activeHand = player.getOffHandStack();
					}

					if (activeHand.isEmpty()) {return;}

					BlockPos storagePosition = activeHand.get(ModDataComponentTypes.LINKED_CHEST);
					Identifier dimension = activeHand.get(ModDataComponentTypes.SERVERWORLD);
					Integer interGroupPointer = activeHand.get(ModDataComponentTypes.INTER_GROUP_POINTER);
					List<Integer> intraGroupPointers = activeHand.get(ModDataComponentTypes.INTRA_GROUP_POINTERS);

					if (storagePosition == null || dimension == null || interGroupPointer == null || intraGroupPointers == null) {return;}

					ServerWorld targetWorld = context.server().getWorld(RegistryKey.of(RegistryKeys.WORLD, dimension));
					if (targetWorld == null || !targetWorld.isPosLoaded(storagePosition)) {return;}

					if (targetWorld.getBlockEntity(storagePosition) instanceof Inventory inventory) {

						int newInterGroupPointer = interGroupPointer + payload.scroll();
						if (newInterGroupPointer < 0) {newInterGroupPointer = 15;}
						if (newInterGroupPointer >= 16) {newInterGroupPointer = 0;}
						List<Integer> group = activeHand.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(newInterGroupPointer)));

						int newIntraGroupPointer = 0;
						if (group != null) {
							newIntraGroupPointer = isGroupEmpty(inventory, group);
						}

						while (group != null && newIntraGroupPointer == -1 && newInterGroupPointer != interGroupPointer) {
							newInterGroupPointer = newInterGroupPointer + payload.scroll();
							if (newInterGroupPointer < 0) {newInterGroupPointer = 15;}
							if (newInterGroupPointer >= 16) {newInterGroupPointer = 0;}
							group = activeHand.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(newInterGroupPointer)));
							if (group != null) {
								newIntraGroupPointer = isGroupEmpty(inventory, group);
							}
						}
						if (newInterGroupPointer == interGroupPointer) { // Di la vuelta, todos los grupos vacios
							newInterGroupPointer = 0;
							List<Integer> newIntraGroupPointers = new ArrayList<>(Collections.nCopies(16, 0));
							List<Integer> group0 = activeHand.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(0)));
							if (group0 != null && !group0.isEmpty()) {
								newIntraGroupPointers.set(0, intraGroupPointers.getFirst());
							}
							activeHand.set(ModDataComponentTypes.INTRA_GROUP_POINTERS, newIntraGroupPointers);
							activeHand.set(ModDataComponentTypes.INTER_GROUP_POINTER, newInterGroupPointer);
						}
						else {
							if (group != null) {
								activeHand.set(ModDataComponentTypes.INTER_GROUP_POINTER, newInterGroupPointer);
								List<Integer> newIntraGroupPointers = new ArrayList<>(intraGroupPointers);
								newIntraGroupPointers.set(newInterGroupPointer, newIntraGroupPointer);
								activeHand.set(ModDataComponentTypes.INTRA_GROUP_POINTERS, newIntraGroupPointers);
							}
						}
					}
				});

		ServerPlayNetworking.registerGlobalReceiver(InfiniteItemSelectColorPayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				ServerPlayerEntity player = context.player();
				net.minecraft.screen.ScreenHandler currentHandler = player.currentScreenHandler;

				if (currentHandler == null) {return;}

				int targetSlot = payload.slot();

				if (targetSlot >= 0 && targetSlot < currentHandler.slots.size()) {
					ItemStack stack = currentHandler.getSlot(targetSlot).getStack();

					if (stack.isOf(ModItems.DIMENSION_POCKET)) {
						stack.set(ModDataComponentTypes.SELECTED_COLOR, payload.colorIndex());
					}
				}

			});
		});

		ServerPlayNetworking.registerGlobalReceiver(ToggleSlotPayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				ServerPlayerEntity player = context.player();
				ScreenHandler handler = player.currentScreenHandler;

				if (handler == null) return;

				Slot targetSlot = handler.getSlot(payload.targetSlotId());
				if (targetSlot == null || targetSlot.getStack().isEmpty()) {return;}
				ItemStack itemStack = targetSlot.getStack();

				if (!itemStack.isOf(ModItems.DIMENSION_POCKET)) {return;}

				DyeColor dyeColor = DyeColor.byIndex(payload.colorIndex());
				ComponentType<List<Integer>> colorComponent = ModDataComponentTypes.COLOR_INVENTORIES.get(dyeColor);

				if (colorComponent == null) {return;}

				List<Integer> existingSlots = itemStack.getOrDefault(colorComponent, List.of());
				List<Integer> updatedSlots = new ArrayList<>(existingSlots);

				int clickedSlotId = payload.clickedSlotId();

				if (updatedSlots.contains(clickedSlotId)) {
					updatedSlots.remove(Integer.valueOf(clickedSlotId));
				} else {
					updatedSlots.add(clickedSlotId);
				}
				itemStack.set(colorComponent, updatedSlots);
				targetSlot.markDirty();
				handler.syncState();

			});
		});


		ServerTickEvents.END_SERVER_TICK.register(minecraftServer -> {
			TerrainManager.TERRAIN_MANAGER.tick();
		});


		ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamageTaken, damageTaken, blocked) -> {
			if (entity.hasStatusEffect(ModEffects.FRAGILE) && !blocked) {
				DynamicRegistryManager registryManager = entity.getEntityWorld().getRegistryManager();
				DamageSources sources = new DamageSources(registryManager);
				DamageSource newSource = sources.generic();
				if (!entity.getEntityWorld().isClient() && source.getType() != newSource.getType()) {
					entity.damage((ServerWorld) entity.getEntityWorld(),newSource, damageTaken*2);
				}
			}

//			if (entity.hasStatusEffect(ModEffects.ONANA_HANDS) && !blocked) {
//				if (entity instanceof PlayerEntity) {
//					((PlayerEntity) entity).getInventory().dropAll();
//				}
//			}

//			if (entity.hasStatusEffect(ModEffects.CUMULATIVE_DAMAGE_TAKEN) && !blocked) {
//				entity.sendMessage(Text.of("dsa"));
//
//				DynamicRegistryManager registryManager = entity.getWorld().getRegistryManager();
//				DamageSources sources = new DamageSources(registryManager);
//				DamageSource newSource = sources.generic();
//
//				entity.sendMessage(Text.of("asd"));
//				entity.sendMessage(Text.of(source.getType().toString()));
//				entity.sendMessage(Text.of(newSource.getType().toString()));
//
//				if (!entity.getWorld().isClient && source.getType() != newSource.getType()) {
//					entity.sendMessage(Text.of("sss"));
//
//					double cumulative_damage_taken = entity.getAttributeValue(ModEntityAttributes.CUMULATIVE_DAMAGE_TAKEN);
//
//					entity.sendMessage(Text.of(String.format("%f", cumulative_damage_taken)));
//
//					double newDamage = damageTaken + cumulative_damage_taken;
//
//					entity.sendMessage(Text.of("punto b"));
//
//					entity.damage(newSource, (float) (newDamage));
//					entity.getAttributeInstance(ModEntityAttributes.CUMULATIVE_DAMAGE_TAKEN).setBaseValue(cumulative_damage_taken+0.5);
//				}
//			}

			if (entity.hasStatusEffect(ModEffects.COUNTER_BLINK) && !blocked) {
				World world = entity.getEntityWorld();
				if (!world.isClient()) {
					for (int i = 0; i < 16; i++) {
						double d = entity.getX() + (entity.getRandom().nextDouble() - 0.5) * 16.0;
						double e = MathHelper.clamp(
								entity.getY() + (entity.getRandom().nextInt(16) - 8), (double)world.getBottomY(), (double)(world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1)
						);
						double f = entity.getZ() + (entity.getRandom().nextDouble() - 0.5) * 16.0;
						if (entity.hasVehicle()) {
							entity.stopRiding();
						}

						Vec3d vec3d = entity.getEntityPos();
						if (entity.teleport(d, e, f, true)) {
							world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(entity));
							SoundCategory soundCategory;
							SoundEvent soundEvent;
							if (entity instanceof FoxEntity) {
								soundEvent = SoundEvents.ENTITY_FOX_TELEPORT;
								soundCategory = SoundCategory.NEUTRAL;
							} else {
								soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
								soundCategory = SoundCategory.PLAYERS;
							}

							world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), soundEvent, soundCategory);
							entity.onLanding();
							break;
						}
					}
				}
			}
		});

//		ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamageTaken, damageTaken, blocked) -> {
//			if (entity.hasStatusEffect(ModEffects.DOUBLE_DAMAGE_TAKEN) && !blocked) {
//				if (!entity.getWorld().isClient) {
//					entity.damage(source, damageTaken);
//				}
//			}
//
//		});

//		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
//			ItemStack item = player.getStackInHand(hand);
//			List<Vec3i> possiblePositions = new ArrayList<>(List.of(
//					new Vec3i(1,0,0),
//					new Vec3i(0,1,0),
//					new Vec3i(0,0,1),
//					new Vec3i(-1,0,0),
//					new Vec3i(0,-1,0),
//					new Vec3i(0,0,-1)
//			));
//
//			ActionResult result = ActionResult.FAIL;
//
//			while (result != ActionResult.SUCCESS) {
//				Random random = Random.create();
//				Vec3i chosenPosition = possiblePositions.get(random.nextBetween(0,5));
//				BlockHitResult adjacentHitResult;
//
//				if (!world.isClient) {
//					adjacentHitResult = hitResult.withBlockPos(hitResult.getBlockPos().add(chosenPosition));
//				}
//				else {
//					adjacentHitResult = hitResult;
//				}
//
//				result = item.useOnBlock(new ItemPlacementContext(player, hand, item, adjacentHitResult));
//				if (result.isAccepted()) {
//					return ActionResult.SUCCESS;
//				}
//			}
//			return ActionResult.PASS;
//        });
	}
	private int isGroupEmpty(Inventory inventory, List<Integer> group) {
		for (int i = 0 ; i < group.size() ; i++) {
			if (!inventory.getStack(group.get(i)).isEmpty()) {
				return i; // El grupo no esta vacio y su slot i contiene algo
			}
		}
		return -1; // Ninguno de los slots del grupo contiene algo
	}

//	@Override
//	public void onWorldLoad(MinecraftServer minecraftServer, ServerWorld serverWorld) {
//		StateSaverAndLoader.loadSave(minecraftServer);
//	}
//
//	public static StateSaverAndLoader getStateSaverAndLoader(MinecraftServer minecraftServer) {
//		return StateSaverAndLoader.loadSave(minecraftServer);
//	}

	private static void sendEmptyPayload(ServerPlayNetworking.Context context) {
		context.responseSender().sendPacket(new InfiniteItemstackV2ChestContentsResponsePayload(
				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY
		));
	}

	private static ItemStack getStackFromGroup(ItemStack infiniteItem, Inventory inventory, int groupIndex, int intraIndex) {
		List<Integer> group = infiniteItem.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(groupIndex)));
		if (group == null || group.isEmpty()) {
			return ItemStack.EMPTY;
		}

		int newIndex = intraIndex;
		if (intraIndex >= group.size()) {newIndex = 0;}
		if (intraIndex < 0) {newIndex = group.size()-1;}
		int targetInventorySlot = group.get(newIndex);

		return inventory.getStack(targetInventorySlot).copy();
	}

	private static int findNextItem(ItemStack infiniteItemstack, Inventory inventory, int currentGroup, int intraGroupPointer, int direction) {
		List<Integer> group = infiniteItemstack.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(currentGroup)));
		if (group == null) {
			return 0;
		}
		int groupSize = group.size();

		int newPointer = intraGroupPointer + direction;
		if (newPointer >= groupSize) {newPointer = 0;}
		if (newPointer < 0) {newPointer = groupSize-1;}
		while (newPointer != intraGroupPointer && inventory.getStack(group.get(newPointer)).isEmpty()) {
			newPointer = newPointer + direction;
			if (newPointer >= groupSize) {newPointer = 0;}
			if (newPointer < 0) {newPointer = groupSize-1;}
		}
		return newPointer;
	}

	private static int findNextGroup(ItemStack infiniteItemstack, int currentGroup, int direction) {
		int newGroup = currentGroup + direction;
		if (newGroup >= 16) newGroup = 0;
		if (newGroup < 0) newGroup = 15;

		List<Integer> group = infiniteItemstack.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(newGroup)));
		while (group != null && group.isEmpty() && newGroup != currentGroup) {
			newGroup = newGroup + direction;
			if (newGroup >= 16) newGroup = 0;
			if (newGroup < 0) newGroup = 15;
			group = infiniteItemstack.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(newGroup)));
		}
		return newGroup;
	}
}