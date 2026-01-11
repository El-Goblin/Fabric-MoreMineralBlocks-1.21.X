package net.elgoblin.moremineralblocks.item.custom;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class LongSwordItem extends SwordItem {

    public LongSwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, int baseAttackDamage, float attackSpeed, float interactionRange, float sweepingDamage) {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, baseAttackDamage + material.getAttackDamage(), EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(Identifier.of(MoreMineralBlocks.MOD_ID), interactionRange, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_SWEEPING_DAMAGE_RATIO,
                        new EntityAttributeModifier(Identifier.of(MoreMineralBlocks.MOD_ID), sweepingDamage, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }



    Identifier nonFreeOffHandPenalty = Identifier.of("non_free_offhand_penalty");

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient) {
            boolean inMainHand = ((PlayerEntity) entity).getMainHandStack() == stack;
            boolean offHandFree = ((PlayerEntity) entity).getOffHandStack().isEmpty();

            EntityAttributeInstance attackSpeed = ((PlayerEntity) entity).getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED);

            if (inMainHand && !offHandFree) {

                if (attackSpeed != null && !attackSpeed.hasModifier(nonFreeOffHandPenalty)) {
                    attackSpeed.addTemporaryModifier(new EntityAttributeModifier(nonFreeOffHandPenalty, -0.6f, EntityAttributeModifier.Operation.ADD_VALUE));
                }
            }
            else {
                if (attackSpeed != null && attackSpeed.hasModifier(nonFreeOffHandPenalty)) {
                    attackSpeed.removeModifier(nonFreeOffHandPenalty);
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

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