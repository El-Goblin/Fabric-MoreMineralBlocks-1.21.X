package net.elgoblin.moremineralblocks.item;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.item.custom.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    //public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings()));
    //public static final Item RAW_PINK_GARNET = registerItem("raw_pink_garnet", new Item(new Item.Settings()));

    public static final Item LEGENDARY_PICKAXE = registerItem(
            "legendary_pickaxe",
            new LegendaryPickaxeItem(ModToolMaterials.LEGENDARY, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.LEGENDARY, 1.0F, -2.8F)))
    );
    public static final Item LEGENDARY_ROCKET = registerItem("legendary_rocket", new LegendaryRocketItem(new Item.Settings().maxCount(1)));
    public static final Item WOODEN_LONGSWORD = registerItem(
            "wooden_longsword",
            new LongSwordItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 3, -2.4F)))
    );
    public static final Item STONE_LONGSWORD = registerItem(
            "stone_longsword",
            new LongSwordItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 3, -2.4F)))
    );
    public static final Item IRON_LONGSWORD = registerItem(
            "iron_longsword",
            new LongSwordItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 3, -2.4F)))
    );
    public static final Item GOLDEN_LONGSWORD = registerItem(
            "golden_longsword",
            new LongSwordItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 3, -2.4F)))
    );
    public static final Item DIAMOND_LONGSWORD = registerItem(
            "diamond_longsword",
            new LongSwordItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 3, -2.4F)))
    );
    public static final Item NETHERITE_LONGSWORD = registerItem(
            "netherite_longsword",
            new LongSwordItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 3, -2.4F)))
    );
    public static final Item FLAMEBERGE_LONGSWORD = registerItem(
            "flameberge_longsword",
            new LongSwordItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 3, -2.4F)))
    );
    public static final Item FIRE_DRAGONSWORD_LONGSWORD = registerItem(
            "fire_dragonsword_longsword",
            new LongSwordItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 3, -2.4F)))
    );
    public static final Item FIENDBLADE_LONGSWORD = registerItem(
            "fiendblade_longsword",
            new LongSwordItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(LongSwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 3, -2.4F)))
    );


    //
    public static final Item CHAOS_ORB = registerItem("chaos_orb", new ChaosOrbItem(new Item.Settings()));

    public static final Item CHISEL = registerItem("chisel", new ChiselItem(new Item.Settings().maxDamage(32)));

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

        // Esto lo pone en la tab de ingredients en creativo
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(LEGENDARY_PICKAXE);
            entries.add(CHISEL);
            entries.add(WOODEN_LONGSWORD);
            entries.add(STONE_LONGSWORD);
            entries.add(IRON_LONGSWORD);
            entries.add(GOLDEN_LONGSWORD);
            entries.add(DIAMOND_LONGSWORD);
            entries.add(NETHERITE_LONGSWORD);
            entries.add(FLAMEBERGE_LONGSWORD);
            entries.add(FIRE_DRAGONSWORD_LONGSWORD);
            entries.add(FIENDBLADE_LONGSWORD);
            entries.add(LEGENDARY_ROCKET);
        });
    }
}
