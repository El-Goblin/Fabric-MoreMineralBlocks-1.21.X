package net.elgoblin.moremineralblocks.item.custom;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.UUID;

public class LongSwordItem extends SwordItem {
    public LongSwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    private final EntityAttributeModifier reduceAttackSpeed = new EntityAttributeModifier(
            Identifier.of("longsword_reduce_attack_speed"),-0.4, EntityAttributeModifier.Operation.ADD_VALUE
            );
    private final EntityAttributeModifier increase_attack_speed = new EntityAttributeModifier(
            Identifier.of("longsword_reduce_attack_speed"),0.6, EntityAttributeModifier.Operation.ADD_VALUE
    );



    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

//        AttributeModifiersComponent.Entry entryReduceAttackSpeed = new AttributeModifiersComponent.Entry(
//                EntityAttributes.GENERIC_ATTACK_SPEED,
//                reduceAttackSpeed,
//                AttributeModifierSlot.MAINHAND);
//
//        if (!attacker.getOffHandStack().isEmpty()) {
//            stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, new AttributeModifiersComponent(List.of(entryReduceAttackSpeed), true));
//        }
//        else {
//            AttributeModifiersComponent.Entry entryIncreaseAttackSpeed = new AttributeModifiersComponent.Entry(
//                    EntityAttributes.GENERIC_ATTACK_SPEED,
//                    reduceAttackSpeed,
//                    AttributeModifierSlot.MAINHAND);
//
//            stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, new AttributeModifiersComponent(List.of(entryIncreaseAttackSpeed), true));
//        }

//        AttributeContainer attributeContainer = attacker.getAttributes();
//        if (attributeContainer.hasModifierForAttribute(EntityAttributes.GENERIC_ATTACK_SPEED, reduceAttackSpeed.id())) {
//            if (attacker.getOffHandStack().isEmpty()) {
//                Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifiersToRemove = HashMultimap.create();
//                modifiersToRemove.put(EntityAttributes.GENERIC_ATTACK_SPEED, reduceAttackSpeed);
//
//                attributeContainer.removeModifiers(modifiersToRemove);
//            }
//        }
//        else {
//            if (!attacker.getOffHandStack().isEmpty()) {
//                stack.applyAttributeModifiers(EquipmentSlot.MAINHAND, (attribute, modifier) -> {
//                    EntityAttributeInstance entityAttributeInstance = attributeContainer.getCustomInstance(attribute);
//                    if (entityAttributeInstance != null) {
//                        entityAttributeInstance.addTemporaryModifier(reduceAttackSpeed);
//                    }
//
//                });
//            }
//        }
        return super.postHit(stack, target, attacker);
    }
}