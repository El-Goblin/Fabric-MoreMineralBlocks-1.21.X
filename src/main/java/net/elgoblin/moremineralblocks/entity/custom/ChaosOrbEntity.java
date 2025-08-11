package net.elgoblin.moremineralblocks.entity.custom;

import net.elgoblin.moremineralblocks.entity.ModEntities;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class ChaosOrbEntity extends ThrownItemEntity {
//    public ChaosOrbEntity(EntityType<? extends SnowballEntity> entityType, World world) {
//        super(entityType, world);
//    }

    public ChaosOrbEntity(EntityType<? extends ChaosOrbEntity> entityType, World world) {
        super(entityType, world);
    }

    public ChaosOrbEntity(World world, LivingEntity owner) {
        super(ModEntities.CHAOS_ORB, owner, world);
    }

    public ChaosOrbEntity(World world, double x, double y, double z) {
        super(ModEntities.CHAOS_ORB, x, y, z, world);
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
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
        }

        if(this.getOwner() != null) {
            this.getOwner().sendMessage(Text.of("GG"));
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.CHAOS_ORB;
    }
}
