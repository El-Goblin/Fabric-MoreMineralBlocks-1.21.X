package net.elgoblin.umamium.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.elgoblin.umamium.Umamium;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrowShootersManager extends SavedData {
    private final List<ArrowShooter> arrowShooters = new ArrayList<>();
    private ServerLevel level;
    private int tickCount = 0;
    private RandomSource random = RandomSource.create();

    public static final Codec<ArrowShootersManager> CODEC =
            RecordCodecBuilder.create(instance ->
                    instance.group(
                            ArrowShooter.CODEC
                                    .listOf()
                                    .fieldOf("arrowShooters")
                                    .forGetter(data -> data.arrowShooters),

                            Codec.INT
                                    .fieldOf("tickCount")
                                    .forGetter(data -> data.tickCount)
                    ).apply(instance, (arrowShooters, tickCount) -> {
                        ArrowShootersManager data = new ArrowShootersManager(null);
                        data.arrowShooters.addAll(arrowShooters);
                        data.tickCount = tickCount;
                        return data;
                    })
            );

    ArrowShootersManager(ServerLevel level) {
        this.level = level;
    }

    public void tick() {
        if (!arrowShooters.isEmpty()) {
            tickCount++;

            Iterator<ArrowShooter> iterator = arrowShooters.iterator();

            while (iterator.hasNext()) {
                ArrowShooter shooter = iterator.next();

                Arrow arrow = EntityTypes.ARROW.create(level, EntitySpawnReason.DISPENSER);
                if (arrow != null) {
                    arrow.setCritArrow(true);
                    arrow.setPos(shooter.x, shooter.y + (1200 - shooter.lastUntilTick + tickCount) * 0.0015, shooter.z);
                    arrow.shoot(random.nextDouble() * 2 - 1, Math.pow(random.nextDouble(), 0.5) * 2 - 1, random.nextDouble() * 2 -1, 20, 0);
                    level.addFreshEntity(arrow);
                }
                if (shooter.lastUntilTick <= tickCount) {
                    iterator.remove();
                }
            }
        }
        else {
            tickCount = 0;
        }
    }

    public void addShooter(double x, double y, double z) {
        arrowShooters.add(new ArrowShooter(x, y, z, tickCount + 1200));
        setDirty();
    }

    public static ArrowShootersManager get(ServerLevel level) {
        ArrowShootersManager data = level.getDataStorage().computeIfAbsent(TYPE);
        data.level = level;
        data.random = RandomSource.create();
        return data;
    }

    public static final SavedDataType<ArrowShootersManager> TYPE =
            new SavedDataType<>(
                    Identifier.fromNamespaceAndPath(
                            Umamium.MOD_ID,
                            "arrow_shooters_manager"
                    ),
                    () -> new ArrowShootersManager(null),
                    CODEC,
                    DataFixTypes.LEVEL
            );

    public record ArrowShooter(double x, double y, double z, int lastUntilTick) {
        public static final Codec<ArrowShooter> CODEC =
                RecordCodecBuilder.create(instance ->
                        instance.group(
                                Codec.DOUBLE.fieldOf("x").forGetter(ArrowShooter::x),
                                Codec.DOUBLE.fieldOf("y").forGetter(ArrowShooter::y),
                                Codec.DOUBLE.fieldOf("z").forGetter(ArrowShooter::z),
                                Codec.INT.fieldOf("lastUntilTick").forGetter(ArrowShooter::lastUntilTick)
                        ).apply(instance, ArrowShooter::new)
                );
    }
}
