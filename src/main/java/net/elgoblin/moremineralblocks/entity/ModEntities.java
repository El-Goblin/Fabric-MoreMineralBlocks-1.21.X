package net.elgoblin.moremineralblocks.entity;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.entity.custom.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<ChaosOrbEntity> CHAOS_ORB = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MoreMineralBlocks.MOD_ID, "chaos_orb"),
            EntityType.Builder.<ChaosOrbEntity>create(ChaosOrbEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25F,0.25F).maxTrackingRange(4).trackingTickInterval(10).build());

    public static final EntityType<MantisEntity> MANTIS = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MoreMineralBlocks.MOD_ID, "mantis"),
            EntityType.Builder.create(MantisEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1f, 2.5f).build());

    public static final EntityType<DevilmonEntity> DEVILMON = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MoreMineralBlocks.MOD_ID, "devilmon"),
            EntityType.Builder.create(DevilmonEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.35F, 0.6F)
                    .eyeHeight(0.36F)
                    .vehicleAttachment(0.04F)
                    .maxTrackingRange(8)
                    .trackingTickInterval(2)
                    .build());

    public static void registerModEntities() {
        MoreMineralBlocks.LOGGER.info("Registering Mod Entities for " + MoreMineralBlocks.MOD_ID);
    }
}
