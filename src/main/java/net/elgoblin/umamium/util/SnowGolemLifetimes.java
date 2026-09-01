package net.elgoblin.umamium.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.elgoblin.umamium.Umamium;
import net.elgoblin.umamium.gamerule.ChaosOrbGameRules;
import net.minecraft.core.UUIDUtil;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SnowGolemLifetimes extends SavedData {
    private final List<EntityGroup> entityGroups = new ArrayList<>();
    private ServerLevel level;
    private boolean addedAnEntityThisTick = false;
    private int tickCount = 0;

    public static final Codec<SnowGolemLifetimes> CODEC =
            RecordCodecBuilder.create(instance ->
                    instance.group(
                            EntityGroup.CODEC.listOf()
                                    .fieldOf("entityGroups")
                                    .forGetter(data -> data.entityGroups),

                            Codec.INT
                                    .fieldOf("tickCount")
                                    .forGetter(data -> data.tickCount)
                    ).apply(instance, (groups, tickCount) -> {
                        SnowGolemLifetimes data = new SnowGolemLifetimes(null);
                        data.entityGroups.addAll(groups);
                        data.tickCount = tickCount;
                        return data;
                    })
            );

    SnowGolemLifetimes(ServerLevel level) {
        this.level = level;
    }

    public void tick() {
        if (!entityGroups.isEmpty()) {
            tickCount++;
            EntityGroup group = entityGroups.getFirst();
            if (tickCount >= group.lifetime) {
                killNextGroup();
            }
            setDirty();
        }
        else {
            tickCount = 0;
        }
        addedAnEntityThisTick = false;
    }

    private void killNextGroup() {
        for (UUID uuid : entityGroups.getFirst().entities) {
            Entity entity = level.getEntity(uuid);
            if (entity != null && entity.isAlive()) {
                entity.kill(level);
            }
        }
        entityGroups.removeFirst();
    }

    public void addEntity(Entity entity) {
        if (!addedAnEntityThisTick) {
            entityGroups.add(new EntityGroup(new ArrayList<>(), level.getGameRules().get(ChaosOrbGameRules.SNOW_GOLEM_LIFETIME) + tickCount));
        }
        entityGroups.getLast().entities.add(entity.getUUID());
        addedAnEntityThisTick = true;
        setDirty();
    }

    public static SnowGolemLifetimes get(ServerLevel level) {
        SnowGolemLifetimes data = level.getDataStorage().computeIfAbsent(TYPE);
        data.level = level;
        return data;
    }

    public static final SavedDataType<SnowGolemLifetimes> TYPE =
            new SavedDataType<>(
                    Identifier.fromNamespaceAndPath(
                            Umamium.MOD_ID,
                            "snow_golem_lifetimes"
                    ),
                    () -> new SnowGolemLifetimes(null),
                    CODEC,
                    DataFixTypes.LEVEL
            );

    private static class EntityGroup {
        private final List<UUID> entities;
        private int lifetime;

        private static final Codec<EntityGroup> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        UUIDUtil.CODEC.listOf()
                                .fieldOf("entities")
                                .forGetter(group -> group.entities),

                        Codec.INT
                                .fieldOf("lifetime")
                                .forGetter(group -> group.lifetime)
                ).apply(instance, EntityGroup::new)
        );

        private EntityGroup(List<UUID> entities, int lifetime) {
            this.entities = entities;
            this.lifetime = lifetime;
        }
    }
}
