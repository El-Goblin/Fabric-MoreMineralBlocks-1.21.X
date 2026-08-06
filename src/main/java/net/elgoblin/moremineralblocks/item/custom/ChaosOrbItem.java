package net.elgoblin.moremineralblocks.item.custom;

import net.elgoblin.moremineralblocks.entity.custom.ChaosOrbEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class ChaosOrbItem extends SnowballItem {

    private List<ItemStack> spawnEggs = new ArrayList<>();
    private long spawnEggsReady = 0;


    public ChaosOrbItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        if (!world.isClientSide()) {

            ChaosOrbEntity chaosOrbEntity = new ChaosOrbEntity(world, user, itemStack);
            chaosOrbEntity.setItem(itemStack);
            chaosOrbEntity.shootFromRotation(user, user.getXRot(), user.getYRot(), 0.0F, 1.5F, 1.0F);
            world.addFreshEntity(chaosOrbEntity);
            user.awardStat(Stats.ITEM_USED.get(this));
            itemStack.consume(1, user);
        }
        return InteractionResult.SUCCESS;
    }

    public List<ItemStack> getOrCreateSpawnEggList() {
        if (spawnEggsReady != 1) {
            spawnEggs = new ArrayList<>();

            for (var item : BuiltInRegistries.ITEM) {
                if (item instanceof SpawnEggItem spawnEggItem) {
                    spawnEggs.add(spawnEggItem.getDefaultInstance());
                }
            }
            spawnEggsReady = 1;
        }
        return spawnEggs;
    }
}
