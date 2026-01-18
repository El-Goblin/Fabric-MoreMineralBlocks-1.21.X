package net.elgoblin.moremineralblocks.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class BlinkingEffect extends StatusEffect {
    private Integer counter = 0;

    public BlinkingEffect(StatusEffectCategory category, int color) {
        super(category, color);
        counter = 0;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (!world.isClient()) {
            for (int i = 0; i < 16; i++) {
                double d = entity.getX() + (entity.getRandom().nextDouble() - 0.5) * 16.0;
                double e = MathHelper.clamp(
                        entity.getY() + (entity.getRandom().nextInt(16) - 8), (double)world.getBottomY(), (double)(world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1)
                );
                double f = entity.getZ() + (entity.getRandom().nextDouble() - 0.5) * 16.0;
                if (entity.hasVehicle()) {
                    entity.stopRiding();
                }

                Vec3d vec3d = entity.getEntityPos();
                if (entity.teleport(d, e, f, true)) {
                    world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(entity));
                    SoundCategory soundCategory;
                    SoundEvent soundEvent;
                    if (entity instanceof FoxEntity) {
                        soundEvent = SoundEvents.ENTITY_FOX_TELEPORT;
                        soundCategory = SoundCategory.NEUTRAL;
                    } else {
                        soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                        soundCategory = SoundCategory.PLAYERS;
                    }

                    world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), soundEvent, soundCategory);
                    entity.onLanding();
                    break;
                }
            }
        }

        return super.applyUpdateEffect(world, entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 200 >> amplifier;
        return i > 0 ? duration % i == 1 : true;
    }
}
