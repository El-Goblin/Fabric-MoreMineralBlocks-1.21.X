package net.elgoblin.moremineralblocks.mixin;


import net.elgoblin.moremineralblocks.tags.ModTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @ModifyArgs(
            method = "doSweepAttack(Lnet/minecraft/world/entity/Entity;FLnet/minecraft/world/damagesource/DamageSource;F)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/phys/AABB;inflate(DDD)Lnet/minecraft/world/phys/AABB;"
            )
    )
    private void modifyExpandArgs(Args args) {
        Player player = (Player) (Object) this;
        ItemStack tool = player.getMainHandItem();

        if (tool.is(ModTags.Items.LONGSWORDS)) {
            double x = args.get(0);
            double y = args.get(1);
            double z = args.get(2);

            args.set(0, x * 5);
            args.set(1, y * 5);
            args.set(2, z * 5);
        }
    }

    @ModifyConstant(
            method = "doSweepAttack(Lnet/minecraft/world/entity/Entity;FLnet/minecraft/world/damagesource/DamageSource;F)V",
            constant = @Constant(doubleValue = 9.0)
    )
    private double modifySweepDistance(double original) {
        Player player = (Player) (Object) this;
        ItemStack tool = player.getMainHandItem();

        if (tool.is(ModTags.Items.LONGSWORDS)) {
            return 25.0;
        }
        return original;
    }
}