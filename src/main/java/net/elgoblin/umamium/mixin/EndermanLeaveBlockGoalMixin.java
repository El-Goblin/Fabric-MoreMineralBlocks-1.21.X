package net.elgoblin.umamium.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.elgoblin.umamium.gamerule.ModGameRules;
import net.elgoblin.umamium.util.ProtectorManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.entity.monster.EnderMan$EndermanLeaveBlockGoal")
public abstract class EndermanLeaveBlockGoalMixin extends Goal {

    @Shadow
    @Final
    private EnderMan enderman;

    @Inject(method = "canUse",
            at = @At("HEAD"),
            cancellable = true)
    private void checkEndermanGriefingAllowed(CallbackInfoReturnable<Boolean> cir) {
        if (!getServerLevel(this.enderman).getGameRules().get(ModGameRules.ENDERMAN_GRIEFING)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "canPlaceBlock", at = @At("RETURN"), cancellable = true)
    private void canPlaceBlock(Level level, BlockPos pos, BlockState carried, BlockState targetState, BlockState belowState, BlockPos below, CallbackInfoReturnable<Boolean> cir) {
        MinecraftServer server = level.getServer();
        if (server != null && ProtectorManager.getProtectorManager(server).isProtected(pos)) {
            cir.setReturnValue(false);
        }
    }
}
