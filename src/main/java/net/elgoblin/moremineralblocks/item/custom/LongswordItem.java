package net.elgoblin.moremineralblocks.item.custom;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class LongswordItem extends Item {

    Identifier nonFreeOffHandPenalty = Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID,"non_free_offhand_penalty");

    public LongswordItem(final Item.Properties properties, final ToolMaterial material, final float attackDamageBaseline, final float attackSpeedBaseline, final float entityInteractionRange, final float sweepingDamage) {

        super(properties.sword(material, attackDamageBaseline, attackSpeedBaseline)
                .attributes(createAttributeModifiers(material, attackDamageBaseline, attackSpeedBaseline, entityInteractionRange, sweepingDamage)));
    }

    public static ItemAttributeModifiers createAttributeModifiers(
            ToolMaterial material,
            float attackDamage,
            float attackSpeed,
            float interactionRange,
            float sweepingDamage
    ) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID,
                                attackDamage + material.attackDamageBonus(),
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                BASE_ATTACK_SPEED_ID,
                                attackSpeed,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(
                                Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID, "entity_interaction_range"),
                                interactionRange,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.SWEEPING_DAMAGE_RATIO,
                        new AttributeModifier(
                                Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID, "sweeping_damage_ratio"),
                                sweepingDamage,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel world, Entity entity, @Nullable EquipmentSlot slot) {
        if (!world.isClientSide()) {
            if (entity instanceof Player player) {
                boolean inMainHand = player.getMainHandItem() == stack;
                boolean offHandFree = player.getOffhandItem().isEmpty();

                AttributeInstance attackSpeed = player.getAttribute(Attributes.ATTACK_SPEED);

                if (inMainHand && !offHandFree) {

                    if (attackSpeed != null && !attackSpeed.hasModifier(nonFreeOffHandPenalty)) {
                        attackSpeed.addTransientModifier(new AttributeModifier(nonFreeOffHandPenalty, -0.6F, AttributeModifier.Operation.ADD_VALUE));
                    }
                }
                else {
                    if (attackSpeed != null && attackSpeed.hasModifier(nonFreeOffHandPenalty)) {
                        attackSpeed.removeModifier(nonFreeOffHandPenalty);
                    }
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot);
    }
}
