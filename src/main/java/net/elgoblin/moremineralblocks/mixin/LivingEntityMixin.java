package net.elgoblin.moremineralblocks.mixin;

import net.elgoblin.moremineralblocks.util.LegendaryItemUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

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
}