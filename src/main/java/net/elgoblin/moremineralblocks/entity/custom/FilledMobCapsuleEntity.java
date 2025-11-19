package net.elgoblin.moremineralblocks.entity.custom;

import net.elgoblin.moremineralblocks.entity.ModEntities;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class FilledMobCapsuleEntity extends ThrownItemEntity {
    private Random random = Random.create();
    MobEntity captured;

    public FilledMobCapsuleEntity(EntityType<? extends FilledMobCapsuleEntity> entityType, World world) {
        super(entityType, world);
    }

    public FilledMobCapsuleEntity(World world, LivingEntity owner) {
        super(ModEntities.FILLED_MOB_CAPSULE, owner, world);
    }

    public FilledMobCapsuleEntity(World world, double x, double y, double z) {
        super(ModEntities.FILLED_MOB_CAPSULE, x, y, z, world);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 0);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        this.discard();
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.FILLED_MOB_CAPSULE;
    }
}
