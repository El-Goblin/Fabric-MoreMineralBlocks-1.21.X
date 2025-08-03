package net.elgoblin.moremineralblocks.effect;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.InstantStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class ChaosEffect extends InstantStatusEffect {

    @FunctionalInterface
    interface PentaConsumer<A, B, C, D, E> {
        void accept(A a, B b, C c, D d, E e);
    }

    @FunctionalInterface
    interface ChaosEvent
            extends PentaConsumer<@Nullable Entity, @Nullable Entity, LivingEntity, Integer, Double> {}

    private Random random = Random.create();
    private int effectsCount = 1;
    private List<BiConsumer<LivingEntity, Integer>> chaosEffects;

    public ChaosEffect(StatusEffectCategory statusEffectCategory, int i) {
        super(statusEffectCategory, i);

        this.chaosEffects = new ArrayList<>(effectsCount);
        //chaosEffects.add(this::spawnCow);
        //chaosEffects.add(this::spawnPig);
        //chaosEffects.add(this::beginThunderstorm);
        //chaosEffects.add(this::goDownXBlocks);
        //chaosEffects.add(this::applyBeaconEffect);
        chaosEffects.add(this::randomizePlayersPositions);
    }

    @Override
    public void applyInstantEffect(@Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        target.sendMessage(Text.of("instant"));
        int nextEffect = this.random.nextBetween(0, this.effectsCount-1);
        chaosEffects.get(nextEffect).accept(target, amplifier);
    }

    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {

        if (!entity.getWorld().isClient) {
            int nextEffect = this.random.nextBetween(0, this.effectsCount - 1);
            chaosEffects.get(nextEffect).accept(entity, amplifier);
        }
        return true;
    }

//    private void spawnCow(@Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
//        target.damageShield(100f);
//    }

    private void spawnCow(LivingEntity entity, Integer amplifier) {
        entity.sendMessage(Text.of("SPAWNCOW"));
        entity.setOnFireFor(10f);
        CowEntity chickenEntity = EntityType.COW.create(entity.getWorld());
        if (chickenEntity != null) {
            chickenEntity.setBreedingAge(-24000);
            chickenEntity.refreshPositionAndAngles(entity.getX(), entity.getY(), entity.getZ(), entity.getYaw(), 0.0F);

            entity.getWorld().spawnEntity(chickenEntity);
        }
    }

//    private void spawnPig(@Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
//        target.damageShield(1f);
//    }

    private void spawnPig(LivingEntity entity, Integer amplifier) {
        entity.sendMessage(Text.of("SPAWNPIG"));

        PigEntity chickenEntity = EntityType.PIG.create(entity.getWorld());
        if (chickenEntity != null) {
            chickenEntity.setBreedingAge(0);
            chickenEntity.refreshPositionAndAngles(entity.getX(), entity.getY(), entity.getZ(), entity.getYaw(), 0.0F);

            entity.getWorld().spawnEntity(chickenEntity);
        }
    }

    private void beginThunderstorm(LivingEntity entity, Integer amplifier) {
        // En el comando no se preocuparon por si es null. Si en algun momento se rompe quizas chequear esto
        if (!entity.getWorld().isClient) {
            ServerWorld world = entity.getServer().getOverworld();
            world.setWeather(0, UniformIntProvider.create(3600, 15600).get(world.getRandom()), true, true);
            //entity.sendMessage(() -> Text.translatable("chaoesEvents.weather.set.thunder", true));
            entity.sendMessage(Text.of("Its raining"));
        }

        //source.getServer().getOverworld().setWeather(0, processDuration(source, duration, ServerWorld.THUNDER_WEATHER_DURATION_PROVIDER), true, true);
        //source.sendFeedback(() -> Text.translatable("commands.weather.set.thunder"), true);
        //return duration;
    }

    private void goDownXBlocks(LivingEntity entity, Integer amplifier) {
        ServerWorld server = entity.getServer().getWorld(entity.getWorld().getRegistryKey());
        TeleportTarget teleportTarget = new TeleportTarget(server,
                new Vec3d(entity.getX(),
                        entity.getY() - 20.0,
                        entity.getZ()),
                new Vec3d(0, 0, 0),
                entity.getYaw(),
                entity.getPitch(),
                TeleportTarget.NO_OP);
        entity.teleportTo(teleportTarget);
        entity.sendMessage(Text.of(String.format("-20")));
    }

    private void randomizePlayersPositions(LivingEntity entity, Integer amplifier) {
        List<ServerPlayerEntity> players = entity.getServer().getPlayerManager().getPlayerList();
        MinecraftServer server = entity.getServer();

        Iterable<ServerWorld> worlds = server.getWorlds();

        // for (LivingEntity livingEntity : this.getWorld().getEntitiesByClass(LivingEntity.class, box, AFFECTED_BY_WATER)) {

        for (ServerPlayerEntity playerEntity : players) {
            ServerWorld dimension = playerEntity.getServer().getWorld(playerEntity.getWorld().getRegistryKey());
            TeleportTarget teleportTarget = new TeleportTarget(dimension,
                    new Vec3d((double)this.random.nextBetween(-10000000, 10000000),
                            playerEntity.getY(),
                            (double)this.random.nextBetween(-10000000, 10000000)),
                    new Vec3d(0, 0, 0),
                    playerEntity.getYaw(),
                    playerEntity.getPitch(),
                    TeleportTarget.NO_OP);
            playerEntity.teleportTo(teleportTarget);
            playerEntity.sendMessage(Text.of(String.format("tpeado")));
        }

    }

    private void applyBeaconEffect(LivingEntity entity, Integer amplifier) {
        List<RegistryEntry<StatusEffect>> pool = BeaconBlockEntity.EFFECTS_BY_LEVEL.stream().flatMap(List::stream).toList();
        long poolSize = pool.size();
        int nextEffect = this.random.nextBetween(0, (int) poolSize-1);
        StatusEffectInstance effect = new StatusEffectInstance(pool.get(nextEffect), 72000);
        entity.addStatusEffect(effect);
        entity.sendMessage(Text.of(String.format("%d", poolSize)));
    }

}
