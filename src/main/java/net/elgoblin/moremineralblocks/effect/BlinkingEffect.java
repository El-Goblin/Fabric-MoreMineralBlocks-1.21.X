package net.elgoblin.moremineralblocks.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.fox.Fox;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class BlinkingEffect extends MobEffect {

    public BlinkingEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {

        if (!level.isClientSide()) {
            teleportNearby(entity, level);
        }
        return super.applyEffectTick(level, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int i = 200 >> amplifier;
        return i <= 0 || duration % i == 1;
    }

    public static void teleportNearby(LivingEntity entity, ServerLevel level) {
        double newX = entity.getX() + (entity.getRandom().nextDouble() - 0.5) * 16.0F;
        double newY = Mth.clamp(entity.getY() + (entity.getRandom().nextDouble() - 0.5) * 8.0F, level.getMinY(), level.getMinY() + level.getLogicalHeight() - 1);
        double newZ = entity.getZ() + (entity.getRandom().nextDouble() - 0.5) * 16.0F;

        if (entity.isPassenger()) {
            entity.stopRiding();
        }

        Vec3 pos = entity.position();
        if (entity.randomTeleport(newX, newY, newZ, true)) {
            level.gameEvent(GameEvent.TELEPORT, pos, GameEvent.Context.of(entity));

            SoundSource soundSource;
            SoundEvent soundEvent;
            if (entity instanceof Fox) {
                soundEvent = SoundEvents.FOX_TELEPORT;
                soundSource = SoundSource.NEUTRAL;
            } else {
                soundEvent = SoundEvents.CHORUS_FRUIT_TELEPORT;
                soundSource = SoundSource.PLAYERS;
            }

            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), soundEvent, soundSource);
            entity.resetFallDistance();
        }
    }
}