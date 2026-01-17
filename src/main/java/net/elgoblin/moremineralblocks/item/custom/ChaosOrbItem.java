package net.elgoblin.moremineralblocks.item.custom;

import net.elgoblin.moremineralblocks.entity.custom.ChaosOrbEntity;
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
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ChaosOrbItem extends SnowballItem {
    public ChaosOrbItem(Item.Settings settings) {
        super(settings);
    }

    private List<ItemStack> spawnEggs = new ArrayList<>();
    private long spawnEggsReady = 0;
    private net.minecraft.util.math.random.Random random = Random.create();

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
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

            ChaosOrbEntity chaosOrbEntity = new ChaosOrbEntity(world, user, user.getStackInHand(hand));
            chaosOrbEntity.setItem(itemStack);
            chaosOrbEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(chaosOrbEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
        return ActionResult.SUCCESS;
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        ChaosOrbEntity chaosOrbEntity = new ChaosOrbEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
        chaosOrbEntity.setItem(stack);
        return chaosOrbEntity;
    }

    public List<ItemStack> getOrCreateSpawnEggList() {
        if (spawnEggsReady != 1) {
            spawnEggs = new ArrayList<>();

            for (Item item : Registries.ITEM) {
                if (item instanceof SpawnEggItem) {
                    ItemStack egg = item.getDefaultStack();
                    egg.setCount(16);
                    spawnEggs.add(egg);
                }
            }
            spawnEggsReady = 1;
        }
        return spawnEggs;
    }
}
