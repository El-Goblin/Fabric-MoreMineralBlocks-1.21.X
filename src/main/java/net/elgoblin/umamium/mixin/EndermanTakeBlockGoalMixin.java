package net.elgoblin.umamium.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.elgoblin.umamium.gamerule.ModGameRules;
import net.elgoblin.umamium.util.ProtectorManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.entity.monster.EnderMan$EndermanTakeBlockGoal")
public abstract class EndermanTakeBlockGoalMixin extends Goal {

    @Shadow
    @Final
    private EnderMan enderman;

    @Inject(method = "canUse",
            at = @At("HEAD"),
            cancellable = true)
    private void canUse(CallbackInfoReturnable<Boolean> cir) {
        if (!getServerLevel(this.enderman).getGameRules().get(ModGameRules.ENDERMAN_GRIEFING)) {
            cir.setReturnValue(false);
        }
    }

    @ModifyExpressionValue(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z"
            )
    )
    private boolean stopEnderManGriefInProtectedArea(boolean original, @Local BlockPos pos, @Local Level level) {
        MinecraftServer server = level.getServer();
        if (server != null) {
            ProtectorManager protectorManager = ProtectorManager.getProtectorManager(server);
            return original && !protectorManager.isProtected(pos);
        }
        return original;
    }
}
