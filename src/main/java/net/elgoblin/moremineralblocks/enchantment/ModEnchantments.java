package net.elgoblin.moremineralblocks.enchantment;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.util.ModTags;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEnchantments {

    public static final RegistryKey<Enchantment> LINKER = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(MoreMineralBlocks.MOD_ID, "linker"));

    public static void bootstrap(Registerable<Enchantment> registerable) {
        RegistryEntryLookup<Enchantment> enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        RegistryEntryLookup<Item> items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(registerable, LINKER, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ModTags.Items.LEGENDARY_TOOLS),
                items.getOrThrow(ModTags.Items.LEGENDARY_TOOLS),
                1,
                1,
                Enchantment.leveledCost(1, 1),
                Enchantment.leveledCost(50, 1),
                2,
                AttributeModifierSlot.MAINHAND)));
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }

    public static int getLevel(ItemStack stack, RegistryKey<Enchantment> enchantmentKey){
        ItemEnchantmentsComponent enchantments = stack.getEnchantments();

        for (RegistryEntry<Enchantment> entry : enchantments.getEnchantments()){
            if (entry.matchesKey(enchantmentKey)) {
                return enchantments.getLevel(entry);
            }
        }
        return 0;
    }
}
