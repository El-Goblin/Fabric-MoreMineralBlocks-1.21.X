package net.elgoblin.moremineralblocks.particle.custom;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

public class ChaosOrbFeedbackParticle extends BillboardParticle {
    public ChaosOrbFeedbackParticle(ClientWorld clientWorld, double x, double y, double z,
                           SpriteProvider spriteProvider, double xSpeed, double ySpeed, double zSpeed) {
        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed, spriteProvider.getFirst());

        this.maxAge = 100;
        this.scale = 1.5f;
        this.velocityX = 0.0;
        this.velocityY = 1.0;
        this.velocityZ = 0.0;
        this.collidesWithWorld = false;
    }

    @Override
    protected RenderType getRenderType() {
        return RenderType.BLOCK_ATLAS_TRANSLUCENT;
    }

    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Random random) {
            return new ChaosOrbFeedbackParticle(world, x, y, z, this.spriteProvider, velocityX, velocityY, velocityZ);
        }
    }
}
