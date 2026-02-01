package net.elgoblin.moremineralblocks.mixin;

import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.util.ModTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @ModifyArgs(
            method = "doSweepingAttack(Lnet/minecraft/entity/Entity;FFLnet/minecraft/entity/damage/DamageSource;F)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/math/Box;expand(DDD)Lnet/minecraft/util/math/Box;"
            )
    )
    private void modifyExpandArgs(Args args) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack tool = player.getMainHandStack();

        if (tool.isIn(ModTags.Items.LONGSWORDS)) {
            double x = args.get(0);
            double y = args.get(1);
            double z = args.get(2);

            args.set(0, x * 5);
            args.set(1, y * 5);
            args.set(2, z * 5);
        }
    }


    @ModifyConstant(
            method = "doSweepingAttack(Lnet/minecraft/entity/Entity;FLnet/minecraft/entity/damage/DamageSource;F)V",
            constant = @Constant(doubleValue = 9.0)
    )
    private double modifySweepDistance(double original) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack tool = player.getMainHandStack();

        if (tool.isIn(ModTags.Items.LONGSWORDS)) {
            return 25.0; // Example: increase range from 3 blocks squared (9.0) to 4 blocks squared (16.0)
        }
        return original;
    }
}