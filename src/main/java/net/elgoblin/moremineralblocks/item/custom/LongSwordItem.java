package net.elgoblin.moremineralblocks.item.custom;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class LongSwordItem extends Item {

    public LongSwordItem(Settings settings, ToolMaterial material, float attackDamage, float attackSpeed, float entityInteractionRange, float sweepingDamage) {
        super(settings.sword(material, attackDamage, attackSpeed).attributeModifiers(createAttributeModifiers(attackDamage, attackSpeed, entityInteractionRange, sweepingDamage)));
    }

    public static AttributeModifiersComponent createAttributeModifiers(float attackDamage, float attackSpeed, float interactionRange, float sweepingDamage) {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.ATTACK_DAMAGE,
                        new EntityAttributeModifier(Identifier.of(MoreMineralBlocks.MOD_ID), attackDamage, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.ATTACK_SPEED,
                        new EntityAttributeModifier(Identifier.of(MoreMineralBlocks.MOD_ID), attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(Identifier.of(MoreMineralBlocks.MOD_ID), interactionRange, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.SWEEPING_DAMAGE_RATIO,
                        new EntityAttributeModifier(Identifier.of(MoreMineralBlocks.MOD_ID), sweepingDamage, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }



    Identifier nonFreeOffHandPenalty = Identifier.of("non_free_offhand_penalty");

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (!world.isClient()) {
            boolean inMainHand = ((PlayerEntity) entity).getMainHandStack() == stack;
            boolean offHandFree = ((PlayerEntity) entity).getOffHandStack().isEmpty();

            EntityAttributeInstance attackSpeed = ((PlayerEntity) entity).getAttributeInstance(EntityAttributes.ATTACK_SPEED);

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
        super.inventoryTick(stack, world, entity, slot);
    }
}