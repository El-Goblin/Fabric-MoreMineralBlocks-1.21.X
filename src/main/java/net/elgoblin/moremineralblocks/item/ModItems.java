package net.elgoblin.moremineralblocks.item;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.elgoblin.moremineralblocks.item.custom.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DebugStickStateComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static net.minecraft.item.Items.BUCKET;

public class ModItems {

    public static final Item LEGENDARY_PICKAXE = registerItem(
            "legendary_pickaxe",
            new LegendaryPickaxeItem(ModToolMaterials.LEGENDARY, new Item.Settings().fireproof().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.LEGENDARY, 1.0F, -2.8F)).rarity(Rarity.EPIC))
    );
    public static final Item LEGENDARY_SHOVEL = registerItem(
            "legendary_shovel",
            new LegendaryShovelItem(ModToolMaterials.LEGENDARY, new Item.Settings().fireproof().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.LEGENDARY, 1.0F, -2.8F)).rarity(Rarity.EPIC))
    );
    public static final Item LEGENDARY_AXE = registerItem(
            "legendary_axe",
            new LegendaryAxeItem(ModToolMaterials.LEGENDARY, new Item.Settings().fireproof().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.LEGENDARY, 5.0F, -2.6F)).rarity(Rarity.EPIC))
    );
    public static final Item LEGENDARY_HOE = registerItem(
            "legendary_hoe",
            new LegendaryHoeItem(ModToolMaterials.LEGENDARY, new Item.Settings().fireproof().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.LEGENDARY, 1.0F, -2.8F)).rarity(Rarity.EPIC))
    );
    public static final Item LEGENDARY_SWORD = registerItem(
            "legendary_sword",
            new LegendarySwordItem(ModToolMaterials.LEGENDARY, new Item.Settings().fireproof().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.LEGENDARY, (int) 9.0F, -2.0F)).rarity(Rarity.EPIC))
    );
    public static final Item LEGENDARY_ROCKET = registerItem("legendary_rocket", new LegendaryRocketItem(new Item.Settings().maxCount(1).fireproof().rarity(Rarity.EPIC)));
    public static final Item INFINITE_ITEMSTACK = registerItem("infinite_itemstack", new InfiniteItem(EntityType.ARMADILLO, 2, 3, new Item.Settings().maxDamage(0).maxCount(1).rarity(Rarity.EPIC)));
    public static final Item SURVIVAL_DEBUG_STICK = registerItem("survival_debug_stick", new SurvivalDebugStickItem(new Item.Settings()
            .maxCount(1)
            .rarity(Rarity.EPIC)
            .component(DataComponentTypes.DEBUG_STICK_STATE, DebugStickStateComponent.DEFAULT)
            .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final Item WOODEN_LONGSWORD = registerItem(
            "wooden_longsword",
            new LongSwordItem(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.4F, 1, 2)))
    );
    public static final Item STONE_LONGSWORD = registerItem(
            "stone_longsword",
            new LongSwordItem(ToolMaterials.STONE, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.STONE, 5, -2.4F, 1, 2)))
    );
    public static final Item IRON_LONGSWORD = registerItem(
            "iron_longsword",
            new LongSwordItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.IRON, 5, -2.4F, 1, 2)))
    );
    public static final Item GOLDEN_LONGSWORD = registerItem(
            "golden_longsword",
            new LongSwordItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.GOLD, 5, -2.4F, 1, 2)))
    );
    public static final Item DIAMOND_LONGSWORD = registerItem(
            "diamond_longsword",
            new LongSwordItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 5, -2.4F, 1, 2)))
    );
    public static final Item NETHERITE_LONGSWORD = registerItem(
            "netherite_longsword",
            new LongSwordItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 5, -2.4F, 1, 2)))
    );
    public static final Item FLAMEBERGE_LONGSWORD = registerItem(
            "flameberge_longsword",
            new LongSwordItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.IRON, 5, -2.4F, 1, 2)))
    );
    public static final Item FIRE_DRAGONSWORD_LONGSWORD = registerItem(
            "fire_dragonsword_longsword",
            new LongSwordItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 5, -2.4F, 1, 2)))
    );
    public static final Item FIENDBLADE_LONGSWORD = registerItem(
            "fiendblade_longsword",
            new LongSwordItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 5, -2.4F, 1, 2)))
    );
    public static final Item LEGENDARY_LONGSWORD = registerItem(
            "legendary_longsword",
            new LegendaryLongSwordItem(ModToolMaterials.LEGENDARY, new Item.Settings().fireproof().attributeModifiers(LongSwordItem.createAttributeModifiers(ModToolMaterials.LEGENDARY, (int) 11.0F, -2.4F, 1, 5)).rarity(Rarity.EPIC))
    );

    public static final Item REFLECTIVE_MIRROR = registerItem("reflective_mirror", new MirrorItem(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));
    public static final Item MAGIC_MIRROR = registerItem("magic_mirror", new MagicMirrorItem(new Item.Settings().maxCount(64).rarity(Rarity.UNCOMMON)));
    public static final Item CHAOS_MIRROR = registerItem("chaos_mirror", new ChaosMirrorItem(new Item.Settings().maxCount(64).rarity(Rarity.UNCOMMON)));


    public static final Item CHAOS_ORB = registerItem("chaos_orb", new ChaosOrbItem(new Item.Settings()));
    public static final Item FLASH = registerItem("flash", new FlashItem(new Item.Settings()));

    public static final Item LA_LECHONA = registerItem("la_lechona", new LaLechonaItem(new Item.Settings().recipeRemainder(BUCKET).maxCount(1)));

//    public static final Item CHISEL = registerItem("chisel", new ChiselItem(new Item.Settings().maxDamage(32)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MoreMineralBlocks.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MoreMineralBlocks.LOGGER.info("Registering Mod Items for " + MoreMineralBlocks.MOD_ID);

        // Esto lo pone en la tab de ingredients en creativo
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            //entries.add(PINK_GARNET);
            //entries.add(RAW_PINK_GARNET);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(ModBlocks.PROTECTOR_BLOCK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(LEGENDARY_SWORD);
            entries.add(LEGENDARY_AXE);
            entries.add(WOODEN_LONGSWORD);
            entries.add(STONE_LONGSWORD);
            entries.add(GOLDEN_LONGSWORD);
            entries.add(IRON_LONGSWORD);
            entries.add(DIAMOND_LONGSWORD);
            entries.add(NETHERITE_LONGSWORD);
            entries.add(FIENDBLADE_LONGSWORD);
            entries.add(FLAMEBERGE_LONGSWORD);
            entries.add(FIRE_DRAGONSWORD_LONGSWORD);
            entries.add(LEGENDARY_LONGSWORD);
            entries.add(FLASH);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(CHAOS_ORB);
            entries.add(INFINITE_ITEMSTACK);
        });

        // Esto lo pone en la tab de ingredients en creativo
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(LEGENDARY_PICKAXE);
            entries.add(LEGENDARY_SHOVEL);
            entries.add(LEGENDARY_AXE);
            entries.add(LEGENDARY_HOE);
            entries.add(LEGENDARY_ROCKET);
            entries.add(SURVIVAL_DEBUG_STICK);
            entries.add(INFINITE_ITEMSTACK);
            entries.add(MAGIC_MIRROR);
            entries.add(REFLECTIVE_MIRROR);
            entries.add(CHAOS_MIRROR);
            entries.add(FLASH);
            entries.add(LA_LECHONA);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(LA_LECHONA);
        });
    }
}
