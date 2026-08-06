package net.elgoblin.moremineralblocks;

import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.elgoblin.moremineralblocks.client.DimensionalPocketCache;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.creativemodetab.ModCreativeModeTabs;
import net.elgoblin.moremineralblocks.effect.BlinkingEffect;
import net.elgoblin.moremineralblocks.effect.ModEffects;
import net.elgoblin.moremineralblocks.enchantment.ModEnchantmentEffects;
import net.elgoblin.moremineralblocks.enchantment.ModEnchantments;
import net.elgoblin.moremineralblocks.entity.ModEntities;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.item.ModToolMaterials;
import net.elgoblin.moremineralblocks.networking.ModPayloads;
import net.elgoblin.moremineralblocks.networking.ServerPayloadReceivers;
import net.elgoblin.moremineralblocks.networking.SwitchEnchantmentToggleSafeModePayload;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.resources.Identifier;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreMineralBlocks implements ModInitializer {
	public static final String MOD_ID = "moremineralblocks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeModeTabs();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModDataComponentTypes.registerDataComponentTypes();
		ModEffects.registerEffects();
		ModEnchantmentEffects.registerEnchantmentEffects();
		ModPayloads.registerPayloads();
		ServerPayloadReceivers.registerServerGlobalReceivers();
		DimensionalPocketCache.init();
		ModEntities.registerModEntities();

		ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamageTaken, damageTaken, blocked) -> {
			if (entity.hasEffect(ModEffects.FRAGILE) && !blocked) {
				if (!entity.level().isClientSide() && entity.level() instanceof ServerLevel serverLevel) {
					DamageSource newSource = serverLevel.damageSources().generic();

					if (source.typeHolder() != newSource.typeHolder()) {
						entity.hurtServer(serverLevel, newSource, damageTaken * 2.0F);
					}
				}
			}
			if (entity.hasEffect(ModEffects.COUNTER_BLINK) && !blocked) {
				if (!entity.level().isClientSide() && entity.level() instanceof ServerLevel serverLevel) {
					BlinkingEffect.teleportNearby(entity, serverLevel);
				}
			}
		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
