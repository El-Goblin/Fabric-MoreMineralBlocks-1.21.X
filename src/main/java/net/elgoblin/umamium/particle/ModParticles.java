package net.elgoblin.umamium.particle;

import net.elgoblin.umamium.Umamium;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModParticles {

    public static final SimpleParticleType PICKAXE_PARTICLE = registerParticle("pickaxe_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_HASTE_PARTICLE = registerParticle("haste_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_JUMP_BOOST_PARTICLE = registerParticle("jump_boost_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_REGENERATION_PARTICLE = registerParticle("regeneration_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_RESISTANCE_PARTICLE = registerParticle("resistance_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_SPEED_PARTICLE = registerParticle("speed_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_STRENGTH_PARTICLE = registerParticle("strength_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_COUNTER_BLINK_PARTICLE = registerParticle("counter_blink_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_BLINKING_PARTICLE = registerParticle("blinking_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_FRAGILE_PARTICLE = registerParticle("fragile_particle", FabricParticleTypes.simple());

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(Umamium.MOD_ID, name), particleType);
    }

    public static void registerParticles() {
        Umamium.LOGGER.info("Registering Particles for " + Umamium.MOD_ID);
    }
}
