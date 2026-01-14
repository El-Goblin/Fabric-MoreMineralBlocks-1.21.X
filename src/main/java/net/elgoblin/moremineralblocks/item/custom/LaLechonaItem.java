package net.elgoblin.moremineralblocks.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class LaLechonaItem extends MilkBucketItem {
    public LaLechonaItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));

            EntityAttributeInstance currentScale = user.getAttributeInstance(EntityAttributes.GENERIC_SCALE);
            if (currentScale != null) {
                currentScale.setBaseValue(1);
            }
        }

        if (!world.isClient) {
            user.clearStatusEffects();
            applyAttributeChange(EntityAttributes.GENERIC_MAX_HEALTH, 20, user);
            applyAttributeChange(EntityAttributes.GENERIC_STEP_HEIGHT, 0.6, user);
            applyAttributeChange(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE, 3, user);
            applyAttributeChange(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1, user);
            applyAttributeChange(EntityAttributes.GENERIC_JUMP_STRENGTH, 0.42, user);
            applyAttributeChange(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE, 4.5, user);
            applyAttributeChange(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, 3, user);
            applyAttributeChange(EntityAttributes.PLAYER_BLOCK_BREAK_SPEED, 1, user);
        }

        if (user instanceof PlayerEntity playerEntity) {
            return ItemUsage.exchangeStack(stack, playerEntity, new ItemStack(Items.BUCKET), false);
        } else {
            stack.decrementUnlessCreative(1, user);
            return stack;
        }
    }

    private void applyAttributeChange(RegistryEntry<EntityAttribute> attribute, double value, LivingEntity player) {
        EntityAttributeInstance currentStat = player.getAttributeInstance(attribute);
        if (currentStat != null) {
            currentStat.setBaseValue(value);
        }
    }
}
