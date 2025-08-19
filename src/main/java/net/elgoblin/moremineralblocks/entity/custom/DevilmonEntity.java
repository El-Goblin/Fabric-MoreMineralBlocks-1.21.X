package net.elgoblin.moremineralblocks.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DevilmonEntity extends CatEntity {

    //@Nullable
    //private DevilmonEntity.CatFleeGoal<PlayerEntity> fleeGoal = new DevilmonEntity.CatFleeGoal<>(this, PlayerEntity.class, 16.0F, 1.8, 3.33);


    public final AnimationState flyAnimationState = new AnimationState();
    private int flyAnimationTimeout = 0;

    public DevilmonEntity(EntityType<? extends CatEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 20, true);
    }

//    @Override
//    public boolean isBreedingItem(ItemStack stack) {
//        return false;
//    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new FleeEntityGoal<>(this, PlayerEntity.class, 32.0F, 2, 3));

        //this.goalSelector.add(1, fleeGoal);

        //this.goalSelector.add(2, new WanderAroundFarGoal(this, 1.0D));
        //this.goalSelector.add(3, new LookAroundGoal(this));


    }

    @Override
    public boolean isPanicking() {
        return true;
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.1F)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1F)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8.0);
    }

    private void setupAnimationStates() {
        if (this.flyAnimationTimeout <= 0) {
            this.flyAnimationTimeout = 15;
            this.flyAnimationState.start(this.age);
        }
        else {
            --this.flyAnimationTimeout;
        }
    }

    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
        else {
            //this.brain.remember(MemoryModuleType.IS_PANICKING, true);
            //this.brain.forget(MemoryModuleType.WALK_TARGET);
        }
    }

    static class CatFleeGoal<T extends LivingEntity> extends FleeEntityGoal<T> {
        private final DevilmonEntity cat;

        public CatFleeGoal(DevilmonEntity cat, Class<T> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
            super(cat, fleeFromType, distance, slowSpeed, fastSpeed, EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR::test);
            this.cat = cat;
        }

        @Override
        public boolean canStart() {
            return true;
        }

        @Override
        public boolean shouldContinue() {
            return true;
        }
    }

//    @Override
//    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
//        return null;
//    }
}
