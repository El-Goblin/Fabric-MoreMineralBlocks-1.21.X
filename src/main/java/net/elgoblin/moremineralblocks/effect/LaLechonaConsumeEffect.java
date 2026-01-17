package net.elgoblin.moremineralblocks.effect;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ClearAllEffectsConsumeEffect;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

public record LaLechonaConsumeEffect() implements ConsumeEffect {
    public static final LaLechonaConsumeEffect INSTANCE = new LaLechonaConsumeEffect();

    @Override
    public Type<? extends ConsumeEffect> getType() {
        return Type.CLEAR_ALL_EFFECTS;
    }

    @Override
    public boolean onConsume(World world, ItemStack stack, LivingEntity user) {
        applyAttributeChange(EntityAttributes.MAX_HEALTH, 20, user);
        applyAttributeChange(EntityAttributes.STEP_HEIGHT, 0.6, user);
        applyAttributeChange(EntityAttributes.SAFE_FALL_DISTANCE, 3, user);
        applyAttributeChange(EntityAttributes.MOVEMENT_SPEED, 0.1, user);
        applyAttributeChange(EntityAttributes.JUMP_STRENGTH, 0.42, user);
        applyAttributeChange(EntityAttributes.BLOCK_INTERACTION_RANGE, 4.5, user);
        applyAttributeChange(EntityAttributes.ENTITY_INTERACTION_RANGE, 3, user);
        applyAttributeChange(EntityAttributes.BLOCK_BREAK_SPEED, 1, user);
        return user.clearStatusEffects();
    }

    private void applyAttributeChange(RegistryEntry<EntityAttribute> attribute, double value, LivingEntity player) {
        EntityAttributeInstance currentStat = player.getAttributeInstance(attribute);
        if (currentStat != null) {
            currentStat.setBaseValue(value);
        }
    }
}
