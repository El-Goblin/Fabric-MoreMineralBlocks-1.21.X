package net.elgoblin.moremineralblocks.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FlashItem extends Item {
    public FlashItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {

            Vec3d previousVelocity = user.getVelocity();
            float previousPitch = user.getPitch();
            float previousYaw = user.getYaw();
            float previousBodyYaw = user.getBodyYaw();
            float previousHeadYaw = user.getHeadYaw();

            user.getItemCooldownManager().set(user.getStackInHand(hand), 50);

            Vec3d target = user.getEntityPos().add(user.getRotationVec(1.0F).multiply(8));
            user.requestTeleport(target.x, target.y, target.z);

            user.setVelocity(previousVelocity);
            user.setPitch(previousPitch);
            user.setYaw(previousYaw);
            user.setBodyYaw(previousBodyYaw);
            user.setHeadYaw(previousHeadYaw);

            user.velocityDirty = true;

            user.fallDistance = 0;
        }
        return super.use(world, user, hand);
    }
}
