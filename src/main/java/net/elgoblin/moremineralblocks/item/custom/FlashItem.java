package net.elgoblin.moremineralblocks.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FlashItem extends Item {
    public FlashItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        Vec3 previousVelocity = player.getDeltaMovement();
        float previousPitch = player.getXRot();
        float previousYaw = player.getYRot();
        float previousBodyYaw = player.yBodyRot;
        float previousHeadYaw = player.getYHeadRot();

        player.getCooldowns().addCooldown(player.getItemInHand(hand), 50);

        Vec3 target = player.position().add(player.getViewVector(1.0F).scale(6));
        player.teleportTo(target.x, target.y, target.z);

        player.setDeltaMovement(previousVelocity);
        player.setXRot(previousPitch);
        player.setYRot(previousYaw);
        player.yBodyRot = previousBodyYaw;
        player.setYHeadRot(previousHeadYaw);

        player.hurtMarked = true;
        player.fallDistance = 0;

        return super.use(level, player, hand);
    }
}
