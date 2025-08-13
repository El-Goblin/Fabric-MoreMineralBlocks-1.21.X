package net.elgoblin.moremineralblocks.util;

import net.elgoblin.moremineralblocks.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;

public class ModLootTableModifiers {
    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {

            LootPool.Builder startChest = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(1))
                    .with(ItemEntry.builder(ModItems.CHAOS_ORB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(32.0f, 32.0f)).build());

            LootPool.Builder village = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.25f))
                    .with(ItemEntry.builder(ModItems.CHAOS_ORB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)).build());

            LootPool.Builder dungeon = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.6f))
                    .with(ItemEntry.builder(ModItems.CHAOS_ORB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)).build());

            LootPool.Builder stronghold = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.75f))
                    .with(ItemEntry.builder(ModItems.CHAOS_ORB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)).build());

            LootPool.Builder desertTemple = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.15f))
                    .with(ItemEntry.builder(ModItems.CHAOS_ORB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)).build());

            LootPool.Builder jungleTemple = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.6f))
                    .with(ItemEntry.builder(ModItems.CHAOS_ORB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f)).build());

            LootPool.Builder igloo = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(1))
                    .with(ItemEntry.builder(ModItems.CHAOS_ORB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

            LootPool.Builder mansion = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.8f))
                    .with(ItemEntry.builder(ModItems.CHAOS_ORB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 5.0f)).build());

            LootPool.Builder shipwrecks = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.2f))
                    .with(ItemEntry.builder(ModItems.CHAOS_ORB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());

            LootPool.Builder buriedTreasure = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(1))
                    .with(ItemEntry.builder(ModItems.CHAOS_ORB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 3.0f)).build());

            LootPool.Builder netherBridge = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.75f))
                    .with(ItemEntry.builder(ModItems.CHAOS_ORB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 5.0f)).build());

            LootPool.Builder bastion = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.9f))
                    .with(ItemEntry.builder(ModItems.CHAOS_ORB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)).build());

            LootPool.Builder ancientCity = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.666f))
                    .with(ItemEntry.builder(ModItems.CHAOS_ORB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(16.0f, 32.0f)).build());

            LootPool.Builder trialChambers = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.15f))
                    .with(ItemEntry.builder(ModItems.CHAOS_ORB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f)).build());

            LootPool.Builder fishingJunk = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.02f))
                    .with(ItemEntry.builder(ModItems.CHAOS_ORB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

            LootPool.Builder fishingTreasure = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.1f))
                    .with(ItemEntry.builder(ModItems.CHAOS_ORB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

            LootPool.Builder piglinBartering = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.05f))
                    .with(ItemEntry.builder(ModItems.CHAOS_ORB))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f)).build());

            // Start chest

            if (LootTables.SPAWN_BONUS_CHEST.equals(key)) {
                tableBuilder.pool(startChest.build());
            }

            // Villages

            if (LootTables.VILLAGE_WEAPONSMITH_CHEST.equals(key)) {
                tableBuilder.pool(village.build());
            }
            if (LootTables.VILLAGE_TOOLSMITH_CHEST.equals(key)) {
                tableBuilder.pool(village.build());
            }
            if (LootTables.VILLAGE_ARMORER_CHEST.equals(key)) {
                tableBuilder.pool(village.build());
            }
            if (LootTables.VILLAGE_CARTOGRAPHER_CHEST.equals(key)) {
                tableBuilder.pool(village.build());
            }
            if (LootTables.VILLAGE_MASON_CHEST.equals(key)) {
                tableBuilder.pool(village.build());
            }
            if (LootTables.VILLAGE_SHEPARD_CHEST.equals(key)) {
                tableBuilder.pool(village.build());
            }
            if (LootTables.VILLAGE_BUTCHER_CHEST.equals(key)) {
                tableBuilder.pool(village.build());
            }
            if (LootTables.VILLAGE_FLETCHER_CHEST.equals(key)) {
                tableBuilder.pool(village.build());
            }
            if (LootTables.VILLAGE_FISHER_CHEST.equals(key)) {
                tableBuilder.pool(village.build());
            }
            if (LootTables.VILLAGE_TANNERY_CHEST.equals(key)) {
                tableBuilder.pool(village.build());
            }
            if (LootTables.VILLAGE_TEMPLE_CHEST.equals(key)) {
                tableBuilder.pool(village.build());
            }
            if (LootTables.VILLAGE_DESERT_HOUSE_CHEST.equals(key)) {
                tableBuilder.pool(village.build());
            }
            if (LootTables.VILLAGE_PLAINS_CHEST.equals(key)) {
                tableBuilder.pool(village.build());
            }
            if (LootTables.VILLAGE_TAIGA_HOUSE_CHEST.equals(key)) {
                tableBuilder.pool(village.build());
            }
            if (LootTables.VILLAGE_SNOWY_HOUSE_CHEST.equals(key)) {
                tableBuilder.pool(village.build());
            }
            if (LootTables.VILLAGE_SAVANNA_HOUSE_CHEST.equals(key)) {
                tableBuilder.pool(village.build());
            }

            // dungeon

            if (LootTables.SIMPLE_DUNGEON_CHEST.equals(key)) {
                tableBuilder.pool(dungeon.build());
            }

            if (LootTables.ABANDONED_MINESHAFT_CHEST.equals(key)) {
                tableBuilder.pool(dungeon.build());
            }

            // Stronghold

            if (LootTables.STRONGHOLD_LIBRARY_CHEST.equals(key)) {
                tableBuilder.pool(stronghold.build());
            }
            if (LootTables.STRONGHOLD_CROSSING_CHEST.equals(key)) {
                tableBuilder.pool(stronghold.build());
            }
            if (LootTables.STRONGHOLD_CORRIDOR_CHEST.equals(key)) {
                tableBuilder.pool(stronghold.build());
            }

            // Desert Temple

            if (LootTables.DESERT_PYRAMID_CHEST.equals(key)) {
                tableBuilder.pool(desertTemple.build());
            }

            // Jungle Temple

            if (LootTables.JUNGLE_TEMPLE_CHEST.equals(key)) {
                tableBuilder.pool(jungleTemple.build());
            }
            if (LootTables.JUNGLE_TEMPLE_DISPENSER_CHEST.equals(key)) {
                tableBuilder.pool(jungleTemple.build());
            }

            // Igloo

            if (LootTables.IGLOO_CHEST_CHEST.equals(key)) {
                tableBuilder.pool(igloo.build());
            }

            // Mansion

            if (LootTables.WOODLAND_MANSION_CHEST.equals(key)) {
                tableBuilder.pool(mansion.build());
            }

            // Shipwrecks, ocean ruins, ruined portals

            if (LootTables.UNDERWATER_RUIN_SMALL_CHEST.equals(key)) {
                tableBuilder.pool(shipwrecks.build());
            }
            if (LootTables.UNDERWATER_RUIN_BIG_CHEST.equals(key)) {
                tableBuilder.pool(shipwrecks.build());
            }
            if (LootTables.SHIPWRECK_MAP_CHEST.equals(key)) {
                tableBuilder.pool(shipwrecks.build());
            }
            if (LootTables.SHIPWRECK_SUPPLY_CHEST.equals(key)) {
                tableBuilder.pool(shipwrecks.build());
            }
            if (LootTables.SHIPWRECK_TREASURE_CHEST.equals(key)) {
                tableBuilder.pool(shipwrecks.build());
            }
            if (LootTables.RUINED_PORTAL_CHEST.equals(key)) {
                tableBuilder.pool(shipwrecks.build());
            }

            // Buried Treasure

            if (LootTables.BURIED_TREASURE_CHEST.equals(key)) {
                tableBuilder.pool(buriedTreasure.build());
            }

            // Nether Bridge

            if (LootTables.NETHER_BRIDGE_CHEST.equals(key)) {
                tableBuilder.pool(netherBridge.build());
            }

            // Bastion

            if (LootTables.BASTION_TREASURE_CHEST.equals(key)) {
                tableBuilder.pool(bastion.build());
            }
            if (LootTables.BASTION_OTHER_CHEST.equals(key)) {
                tableBuilder.pool(bastion.build());
            }
            if (LootTables.BASTION_BRIDGE_CHEST.equals(key)) {
                tableBuilder.pool(bastion.build());
            }
            if (LootTables.BASTION_HOGLIN_STABLE_CHEST.equals(key)) {
                tableBuilder.pool(bastion.build());
            }

            // Ancient City

            if (LootTables.ANCIENT_CITY_CHEST.equals(key)) {
                tableBuilder.pool(ancientCity.build());
            }
            if (LootTables.ANCIENT_CITY_ICE_BOX_CHEST.equals(key)) {
                tableBuilder.pool(ancientCity.build());
            }

            // Trial Chambers

            if (LootTables.TRIAL_CHAMBERS_REWARD_CHEST.equals(key)) {
                tableBuilder.pool(trialChambers.build());
            }
            if (LootTables.TRIAL_CHAMBERS_REWARD_COMMON_CHEST.equals(key)) {
                tableBuilder.pool(trialChambers.build());
            }
            if (LootTables.TRIAL_CHAMBERS_REWARD_RARE_CHEST.equals(key)) {
                tableBuilder.pool(trialChambers.build());
            }
            if (LootTables.TRIAL_CHAMBERS_REWARD_UNIQUE_CHEST.equals(key)) {
                tableBuilder.pool(trialChambers.build());
            }
            if (LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_CHEST.equals(key)) {
                tableBuilder.pool(trialChambers.build());
            }
            if (LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_COMMON_CHEST.equals(key)) {
                tableBuilder.pool(trialChambers.build());
            }
            if (LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_RARE_CHEST.equals(key)) {
                tableBuilder.pool(trialChambers.build());
            }
            if (LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_UNIQUE_CHEST.equals(key)) {
                tableBuilder.pool(trialChambers.build());
            }
            if (LootTables.TRIAL_CHAMBERS_SUPPLY_CHEST.equals(key)) {
                tableBuilder.pool(trialChambers.build());
            }
            if (LootTables.TRIAL_CHAMBERS_CORRIDOR_CHEST.equals(key)) {
                tableBuilder.pool(trialChambers.build());
            }
            if (LootTables.TRIAL_CHAMBERS_INTERSECTION_CHEST.equals(key)) {
                tableBuilder.pool(trialChambers.build());
            }
            if (LootTables.TRIAL_CHAMBERS_INTERSECTION_BARREL_CHEST.equals(key)) {
                tableBuilder.pool(trialChambers.build());
            }
            if (LootTables.TRIAL_CHAMBERS_ENTRANCE_CHEST.equals(key)) {
                tableBuilder.pool(trialChambers.build());
            }

            // Fishing Junk

            if (LootTables.FISHING_JUNK_GAMEPLAY.equals(key)) {
                tableBuilder.pool(fishingJunk.build());
            }

            // Fishing Treasure

            if (LootTables.FISHING_TREASURE_GAMEPLAY.equals(key)) {
                tableBuilder.pool(fishingTreasure.build());
            }

            // Piglin Bartering

            if (LootTables.PIGLIN_BARTERING_GAMEPLAY.equals(key)) {
                tableBuilder.pool(piglinBartering.build());
            }

        });
    }
}
