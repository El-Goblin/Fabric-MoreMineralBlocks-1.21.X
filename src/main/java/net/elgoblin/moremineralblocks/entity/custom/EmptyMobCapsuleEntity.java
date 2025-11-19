package net.elgoblin.moremineralblocks.entity.custom;

import net.elgoblin.moremineralblocks.effect.ModEffects;
import net.elgoblin.moremineralblocks.entity.ModEntities;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.item.custom.ChaosOrbItem;
import net.elgoblin.moremineralblocks.item.custom.FilledMobCapsuleItem;
import net.elgoblin.moremineralblocks.particle.ModParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.TropicalFishEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class EmptyMobCapsuleEntity extends ThrownItemEntity {
    private Random random = Random.create();

    public EmptyMobCapsuleEntity(EntityType<? extends EmptyMobCapsuleEntity> entityType, World world) {
        super(entityType, world);
    }

    public EmptyMobCapsuleEntity(World world, LivingEntity owner) {
        super(ModEntities.EMPTY_MOB_CAPSULE, owner, world);
    }

    public EmptyMobCapsuleEntity(World world, double x, double y, double z) {
        super(ModEntities.EMPTY_MOB_CAPSULE, x, y, z, world);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if (entity instanceof MobEntity) {
            ItemStack filledMobCapsule = ModItems.FILLED_MOB_CAPSULE.getDefaultStack();
        }
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 0);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        this.discard();
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.EMPTY_MOB_CAPSULE;
    }
}
