package net.elgoblin.moremineralblocks;

import net.elgoblin.moremineralblocks.attribute.ModEntityAttributes;
import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.effect.ModEffects;
import net.elgoblin.moremineralblocks.entity.ModEntities;
import net.elgoblin.moremineralblocks.entity.custom.DevilmonEntity;
import net.elgoblin.moremineralblocks.entity.custom.MantisEntity;
import net.elgoblin.moremineralblocks.item.ModItemGroups;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.particle.ModParticles;
import net.elgoblin.moremineralblocks.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MoreMineralBlocks implements ModInitializer {
	public static final String MOD_ID = "moremineralblocks";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItemGroups.registerItemGroups();
		ModDataComponentTypes.registerDataComponentTypes();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEffects.registerEffects();
		ModEntities.registerModEntities();
		ModLootTableModifiers.modifyLootTables();
		ModEntityAttributes.registerEntityAttributes();
		ModParticles.registerParticles();

		FabricDefaultAttributeRegistry.register(ModEntities.MANTIS, MantisEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.DEVILMON, DevilmonEntity.createAttributes());

		ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamageTaken, damageTaken, blocked) -> {
			if (entity.hasStatusEffect(ModEffects.DOUBLE_DAMAGE_TAKEN) && !blocked) {
				DynamicRegistryManager registryManager = entity.getWorld().getRegistryManager();
				DamageSources sources = new DamageSources(registryManager);
				DamageSource newSource = sources.generic();
				if (!entity.getWorld().isClient && source.getType() != newSource.getType()) {
					entity.damage(newSource, damageTaken*2);
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

			if (entity.hasStatusEffect(ModEffects.TELEPORTITIS_DODGE) && !blocked) {
				World world = entity.getWorld();
				if (!world.isClient) {
					for (int i = 0; i < 16; i++) {
						double d = entity.getX() + (entity.getRandom().nextDouble() - 0.5) * 16.0;
						double e = MathHelper.clamp(
								entity.getY() + (entity.getRandom().nextInt(16) - 8), (double)world.getBottomY(), (double)(world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1)
						);
						double f = entity.getZ() + (entity.getRandom().nextDouble() - 0.5) * 16.0;
						if (entity.hasVehicle()) {
							entity.stopRiding();
						}

						Vec3d vec3d = entity.getPos();
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
}