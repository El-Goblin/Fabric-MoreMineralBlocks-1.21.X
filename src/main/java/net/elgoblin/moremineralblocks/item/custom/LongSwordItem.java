package net.elgoblin.moremineralblocks.item.custom;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class LongSwordItem extends SwordItem {
    public LongSwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }
}
