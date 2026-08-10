package net.elgoblin.moremineralblocks.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class FlashItem extends Item {
    public FlashItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        Vec3 previousVelocity = player.getDeltaMovement();
        float previousPitch = player.getXRot();
        float previousYaw = player.getYRot();
        float previousBodyYaw = player.yBodyRot;
        float previousHeadYaw = player.getYHeadRot();

        if (!level.isClientSide()) {
            player.getCooldowns().addCooldown(player.getItemInHand(hand), 50);

            Vec3 target = player.position().add(player.getViewVector(1.0F).scale(6));

            TeleportTransition teleportTarget = new TeleportTransition((ServerLevel) level,
                    target,
                    new Vec3(0, 0, 0),
                    player.getYRot(),
                    player.getXRot(),
                    TeleportTransition.DO_NOTHING);
            player.teleport(teleportTarget);

            player.setDeltaMovement(previousVelocity);
            player.setXRot(previousPitch);
            player.setYRot(previousYaw);
            player.yBodyRot = previousBodyYaw;
            player.setYHeadRot(previousHeadYaw);

            player.hurtMarked = true;
            player.fallDistance = 0;
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public boolean allowComponentsUpdateAnimation(Player player, InteractionHand hand, ItemStack oldStack, ItemStack newStack) {
        return true;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.NONE;
    }
}
