package net.elgoblin.moremineralblocks;

import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.elgoblin.moremineralblocks.client.DimensionalPocketCache;
import net.elgoblin.moremineralblocks.component.ModAttachmentTypes;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.creativemodetab.ModCreativeModeTabs;
import net.elgoblin.moremineralblocks.effect.BlinkingEffect;
import net.elgoblin.moremineralblocks.effect.ModEffects;
import net.elgoblin.moremineralblocks.enchantment.ModEnchantmentEffects;
import net.elgoblin.moremineralblocks.entity.ModEntities;
import net.elgoblin.moremineralblocks.gamerule.ChaosOrbGameRules;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.networking.ModPayloads;
import net.elgoblin.moremineralblocks.networking.ServerPayloadReceivers;
import net.elgoblin.moremineralblocks.particle.ModParticles;
import net.elgoblin.moremineralblocks.terrain.TerrainJobsManager;
import net.elgoblin.moremineralblocks.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.ItemEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.Identifier;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.golem.SnowGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MoreMineralBlocks implements ModInitializer {
	public static final String MOD_ID = "moremineralblocks";
	public static final RandomSource random = RandomSource.create();
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private long blockPlacedCount = 0;

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeModeTabs();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModDataComponentTypes.registerDataComponentTypes();
		ModAttachmentTypes.registerAttachmentTypes();
		ModEffects.registerEffects();
		ModEnchantmentEffects.registerEnchantmentEffects();
		ModPayloads.registerPayloads();
		ServerPayloadReceivers.registerServerGlobalReceivers();
		DimensionalPocketCache.init();
		ModParticles.registerParticles();
		ModEntities.registerModEntities();
		ModLootTableModifiers.modifyLootTables();
		TerrainJobsManager.init();
		ChaosOrbGameRules.init();

		ServerTickEvents.END_SERVER_TICK.register(minecraftServer -> {
			TerrainJobsManager.TERRAIN_MANAGER.tick(minecraftServer);
		});

		ServerLivingEntityEvents.AFTER_DAMAGE.register(MoreMineralBlocks::applyAfterDamageEffects);

//		ItemEvents.USE_ON.register((context) -> {
//			Player player = context.getPlayer();
//			if (player == null) {
//				return null;
//			}
//
//			Long seed = player.getAttached(ModAttachmentTypes.ADYACENT_BLOCK_PLACING);
//			if (seed == null) {
//				return null;
//			}
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
//				System.out.println("Server " + placeContext.getClickedPos());
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
	}

	private static void applyAfterDamageEffects(LivingEntity entity, DamageSource source, float baseDamageTaken, float damageTaken, boolean blocked) {
		if (entity.level() instanceof ServerLevel serverLevel) {
			if (entity.hasEffect(ModEffects.FRAGILE) && !blocked) {
                DamageSource newSource = serverLevel.damageSources().generic();

                if (source.typeHolder() != newSource.typeHolder()) {
                    entity.hurtServer(serverLevel, newSource, damageTaken * 2.0F);
                }
            }
			if (entity.hasEffect(ModEffects.COUNTER_BLINK) && !blocked) {
                BlinkingEffect.teleportNearby(entity, serverLevel);
            }
			if (entity.hasEffect(ModEffects.SNOWY_BODYGUARDS) && !blocked) {

				SnowGolem golem = new SnowGolem(EntityTypes.SNOW_GOLEM, serverLevel);
				golem.setPos(entity.position().add(new Vec3(random.nextIntBetweenInclusive(-3, 3), 0, random.nextIntBetweenInclusive(-3, 3))));
				golem.setAggressive(true);
				serverLevel.addFreshEntity(golem);
			}
		}
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
