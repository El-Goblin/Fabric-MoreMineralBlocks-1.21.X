package net.elgoblin.moremineralblocks.mixin;

import net.elgoblin.moremineralblocks.util.KillerToolSaver;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "dropLoot", at = @At("HEAD"))
    private void captureTool(ServerWorld world, DamageSource damageSource, boolean causedByPlayer, CallbackInfo ci) {
        if (damageSource.getAttacker() instanceof LivingEntity killer) {
            ((KillerToolSaver) this).setKillerTool(killer.getMainHandStack());
        }
    }

    @Inject(method = "dropLoot", at = @At("TAIL"))
    private void clearTool(ServerWorld world, DamageSource damageSource, boolean causedByPlayer, CallbackInfo ci) {
        ((KillerToolSaver) this).setKillerTool(ItemStack.EMPTY);
    }
}
