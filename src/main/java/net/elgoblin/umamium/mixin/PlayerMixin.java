package net.elgoblin.umamium.mixin;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.elgoblin.umamium.component.ModAttachmentTypes;
import net.elgoblin.umamium.tags.ModTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
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

    @WrapOperation(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/attribute/BedRule;canSleep(Lnet/minecraft/world/level/Level;)Z"
            )
    )
    private boolean invertSleepRuleIfNightOwl(BedRule instance, Level level, Operation<Boolean> original) {
        Player player = (Player) (Object) this;

        if (player.hasAttached(ModAttachmentTypes.NIGHT_OWL)) {
            return level.isBrightOutside() || level.isThundering();
        }

        return original.call(instance, level);
    }
}