package net.elgoblin.moremineralblocks.item.custom;

import net.elgoblin.moremineralblocks.entity.custom.ChaosOrbEntity;
import net.elgoblin.moremineralblocks.entity.custom.EmptyMobCapsuleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SnowballItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EmptyMobCapsuleItem extends SnowballItem {
    public EmptyMobCapsuleItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ENTITY_SNOWBALL_THROW,
                SoundCategory.NEUTRAL,
                0.5F,
                0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        if (!world.isClient) {
            EmptyMobCapsuleEntity mobCapsuleEntity = new EmptyMobCapsuleEntity(world, user);
            mobCapsuleEntity.setItem(itemStack);
            mobCapsuleEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(mobCapsuleEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        EmptyMobCapsuleEntity mobCapsuleEntity = new EmptyMobCapsuleEntity(world, pos.getX(), pos.getY(), pos.getZ());
        mobCapsuleEntity.setItem(stack);
        return mobCapsuleEntity;
    }
}
