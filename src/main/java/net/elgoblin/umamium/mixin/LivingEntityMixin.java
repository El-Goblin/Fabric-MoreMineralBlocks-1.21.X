package net.elgoblin.umamium.mixin;

import net.elgoblin.umamium.effect.ModEffects;
import net.elgoblin.umamium.util.LegendaryItemUtils;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow
    public abstract boolean hasEffect(Holder<MobEffect> effect);

    @Inject(
            method = "dropAllDeathLoot",
            at = @At("HEAD")
    )
    private void captureTool(ServerLevel level, DamageSource source, CallbackInfo ci) {
        if (source.getEntity() instanceof LivingEntity killer) {
            ((LegendaryItemUtils.KillerToolSaver) this).setKillerTool(killer.getMainHandItem());
        }
    }

    @Inject(
            method = "dropAllDeathLoot",
            at = @At("TAIL")
    )
    private void clearTool(ServerLevel level, DamageSource source, CallbackInfo ci) {
        ((LegendaryItemUtils.KillerToolSaver) this).setKillerTool(ItemStack.EMPTY);
    }

    @Inject(
            method = "isSensitiveToWater",
            at = @At("HEAD"),
            cancellable = true)
    private void sensitiveIfWaterWeakness(CallbackInfoReturnable<Boolean> cir) {
        if (this.hasEffect(ModEffects.WATER_WEAKNESS)) {cir.setReturnValue(true);}
    }
}