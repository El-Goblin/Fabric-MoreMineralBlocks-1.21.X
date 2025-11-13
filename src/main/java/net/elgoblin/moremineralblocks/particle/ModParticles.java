package net.elgoblin.moremineralblocks.particle;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {

    public static final SimpleParticleType PICKAXE_PARTICLE = registerParticle("pickaxe_particle", FabricParticleTypes.simple());

    //

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MoreMineralBlocks.MOD_ID, name), particleType);
    }

    public static void registerParticles() {
        MoreMineralBlocks.LOGGER.info("Registering Particles for " + MoreMineralBlocks.MOD_ID);
    }
}
