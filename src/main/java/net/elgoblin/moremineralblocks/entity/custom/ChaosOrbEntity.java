package net.elgoblin.moremineralblocks.entity.custom;

import net.elgoblin.moremineralblocks.entity.ModEntities;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ChaosOrbEntity extends ThrowableItemProjectile {

    public ChaosOrbEntity(final EntityType<? extends ChaosOrbEntity> type, final Level level) {
        super(type, level);
    }

    public ChaosOrbEntity(final Level level, final LivingEntity mob, final ItemStack itemStack) {
        super(ModEntities.CHAOS_ORB, mob, level, itemStack);
    }

    public ChaosOrbEntity(final Level level, final double x, final double y, final double z, final ItemStack itemStack) {
        super(ModEntities.CHAOS_ORB, x, y, z, level, itemStack);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.CHAOS_ORB;
    }

    private ParticleOptions getParticle() {
        ItemStack item = this.getItem();
        return item.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, ItemStackTemplate.fromNonEmptyStack(item));
    }

    @Override
    public void handleEntityEvent(final byte id) {
        if (id == 3) {
            ParticleOptions particle = this.getParticle();

            for (int i = 0; i < 8; i++) {
                this.level().addParticle(particle, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected void onHitEntity(final EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity entity = hitResult.getEntity();
        double knockback = random.nextInt(1,20);
        knockback = knockback * 0.4;

        if (this.getOwner() == null) { return; }

        double x = entity.getX() - this.getOwner().getX();
        double z = entity.getZ() - this.getOwner().getZ();

        double distance = Math.sqrt(x * x + z * z);
        x = x / distance;
        z = z / distance;

        if (distance > 0) {
            entity.push(x * knockback, 0.2, z * knockback);
        }
    }

    @Override
    protected void onHit(final HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }
}
