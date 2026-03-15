package net.elgoblin.moremineralblocks.item;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.util.ModTags;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModToolMaterials {
    public static final ToolMaterial LEGENDARY = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_LEGENDARY_TOOL, 3600000, 30.0F, 4.0F, 30, ModTags.Items.LEGENDARY_REPAIR);
    public static final ToolMaterial SURVIVAL_DEBUG_STICK = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_SURVIVAL_DEBUG_STICK, 3600000, 3600000.0F, 0.0F, 30, ModTags.Items.LEGENDARY_REPAIR);

    public static void advanceEnchantments(ItemStack itemStack) {
        ItemEnchantmentsComponent oldEnchantments = itemStack.get(DataComponentTypes.ENCHANTMENTS);
        ItemEnchantmentsComponent oldStoredEnchantments = itemStack.get(DataComponentTypes.STORED_ENCHANTMENTS);
        BlockPos oldLinkedChest = itemStack.get(ModDataComponentTypes.LINKED_CHEST);
        Identifier oldDimension = itemStack.get(ModDataComponentTypes.SERVERWORLD);

        ItemEnchantmentsComponent newEnchantments = itemStack.get(ModDataComponentTypes.OTHER_ENCHANTMENTS);
        ItemEnchantmentsComponent newStoredEnchantments = itemStack.get(ModDataComponentTypes.OTHER_STORED_ENCHANTMENTS);
        BlockPos newLinkedChest = itemStack.get(ModDataComponentTypes.OTHER_LINKED_CHEST);
        Identifier newDimension = itemStack.get(ModDataComponentTypes.OTHER_SERVERWORLD);

        itemStack.set(DataComponentTypes.ENCHANTMENTS, newEnchantments);
        itemStack.set(DataComponentTypes.STORED_ENCHANTMENTS, newStoredEnchantments);
        itemStack.set(ModDataComponentTypes.LINKED_CHEST, newLinkedChest);
        itemStack.set(ModDataComponentTypes.SERVERWORLD, newDimension);

//        String currentSet = itemStack.get(ModDataComponentTypes.ENCHANTMENT_SET);

//        if (currentSet == null) {
//            itemStack.set(ModDataComponentTypes.ENCHANTMENT_SET, "third");
//        }
//        else {
//            if (currentSet.equals("second")) {
//                itemStack.set(ModDataComponentTypes.ENCHANTMENT_SET, "first");
//            }
//            else {
//                itemStack.set(ModDataComponentTypes.ENCHANTMENT_SET, "second");
//            }
//        }

        itemStack.set(ModDataComponentTypes.OTHER_ENCHANTMENTS, oldEnchantments);
        itemStack.set(ModDataComponentTypes.OTHER_STORED_ENCHANTMENTS, oldStoredEnchantments);
        itemStack.set(ModDataComponentTypes.OTHER_LINKED_CHEST, oldLinkedChest);
        itemStack.set(ModDataComponentTypes.OTHER_SERVERWORLD, oldDimension);

        if (newEnchantments == null) {
            itemStack.set(DataComponentTypes.STORED_ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
            itemStack.set(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
        }
        if (newLinkedChest == null) {
            itemStack.set(ModDataComponentTypes.LINKED_CHEST, null);
            itemStack.set(ModDataComponentTypes.SERVERWORLD, null);
        }
    }
}

