package net.elgoblin.moremineralblocks.particle;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {

    public static final SimpleParticleType PICKAXE_PARTICLE = registerParticle("pickaxe_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_HASTE_PARTICLE = registerParticle("haste_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_JUMP_BOOST_PARTICLE = registerParticle("jump_boost_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_REGENERATION_PARTICLE = registerParticle("regeneration_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_RESISTANCE_PARTICLE = registerParticle("resistance_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_SPEED_PARTICLE = registerParticle("speed_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_STRENGTH_PARTICLE = registerParticle("strength_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_TELEPORTITIS_DODGE_PARTICLE = registerParticle("teleportitis_dodge_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_CHORUS_FRUIT_TP_PARTICLE = registerParticle("chorus_fruit_tp_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CHAOS_ORB_DOUBLE_DAMAGE_TAKEN_PARTICLE = registerParticle("double_damage_taken_particle", FabricParticleTypes.simple());

    //

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MoreMineralBlocks.MOD_ID, name), particleType);
    }

    public static void registerParticles() {
        MoreMineralBlocks.LOGGER.info("Registering Particles for " + MoreMineralBlocks.MOD_ID);
    }
}
