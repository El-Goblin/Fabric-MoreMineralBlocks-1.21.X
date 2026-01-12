package net.elgoblin.moremineralblocks.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FlashItem extends Item {
    public FlashItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            ItemStack itemstack = user.getStackInHand(hand);

            user.getItemCooldownManager().set(this, 50);

            Vec3d target = user.getPos().add(user.getRotationVec(1.0F).multiply(5));
//            user.setVelocity(0,0,0);
            user.requestTeleport(target.x, target.y, target.z);
            user.fallDistance = 0;
        }
        return super.use(world, user, hand);
    }
}
