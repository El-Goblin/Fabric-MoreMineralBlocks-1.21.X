package net.elgoblin.moremineralblocks.effect;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.InstantStatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class ChaosEffect extends InstantStatusEffect {

    @FunctionalInterface
    interface PentaConsumer<A, B, C, D, E> {
        void accept(A a, B b, C c, D d, E e);
    }

    @FunctionalInterface
    interface ChaosEvent
            extends PentaConsumer<@Nullable Entity, @Nullable Entity, LivingEntity, Integer, Double> {}

    private Random random = Random.create();
    private int effectsCount = 2;
    private List<BiConsumer<LivingEntity, Integer>> chaosEffects;

    public ChaosEffect(StatusEffectCategory statusEffectCategory, int i) {
        super(statusEffectCategory, i);

        this.chaosEffects = new ArrayList<>(effectsCount);
        chaosEffects.add(this::spawnCow);
        chaosEffects.add(this::spawnPig);
    }

    @Override
    public void applyInstantEffect(@Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
    }

    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(entity.getDamageSources().magic(), 6 << amplifier);

        entity.sendMessage(Text.of("asda"));
        int nextEffect = this.random.nextBetween(0, this.effectsCount-1);
        chaosEffects.get(nextEffect).accept(entity, amplifier);
        return true;
    }

//    private void spawnCow(@Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
//        target.damageShield(100f);
//    }

    private void spawnCow(LivingEntity entity, Integer amplifier) {
        entity.sendMessage(Text.of("SPAWNCOW"));
        entity.setOnFireFor(10f);
//        MinecraftServer server = entity.getWorld().getServer();
//        ServerCommandSource source = server.getCommandSource()
//                .withLevel(4)            // Permission level (4=highest)
//                .withSilent();
//        server.getWorld(World.OVERWORLD);
//        server.getCommandManager().executeWithPrefix(source, "summon minecraft:cow 20 -12 40");
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

}
