package net.elgoblin.moremineralblocks.enchantment;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.tags.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class ModEnchantments {

    public static final ResourceKey<Enchantment> LINKER = ResourceKey.create(
            Registries.ENCHANTMENT,
            Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID, "linker")
    );

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> items = context.lookup(Registries.ITEM);

        register(context, LINKER, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ModTags.Items.LEGENDARY_TOOLS),
                        items.getOrThrow(ModTags.Items.LEGENDARY_TOOLS),
                        1,
                        1,
                        Enchantment.dynamicCost(1, 1),
                        Enchantment.dynamicCost(50, 1),
                        2,
                        EquipmentSlotGroup.MAINHAND
                )
        ));
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.identifier()));
    }

    public static int getLevel(ItemStack stack, ResourceKey<Enchantment> enchantmentKey) {
        ItemEnchantments enchantments = stack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);

        for (Holder<Enchantment> entry : enchantments.keySet()) {
            if (entry.is(enchantmentKey)) {
                return enchantments.getLevel(entry);
            }
        }
        return 0;
    }
}