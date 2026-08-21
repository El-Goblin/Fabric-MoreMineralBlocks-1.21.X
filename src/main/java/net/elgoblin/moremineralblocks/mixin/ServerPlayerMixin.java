package net.elgoblin.moremineralblocks.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.elgoblin.moremineralblocks.component.ModAttachmentTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {

    @WrapOperation(
            method = "startSleepInBed",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/attribute/BedRule;canSleep(Lnet/minecraft/world/level/Level;)Z"
            )
    )
    private boolean invertSleepRuleIfNightOwl(BedRule instance, Level level, Operation<Boolean> original) {
        ServerPlayer player = (ServerPlayer) (Object) this;

        if (player.hasAttached(ModAttachmentTypes.NIGHT_OWL)) {
            return level.isBrightOutside() || level.isThundering();
        }

        return original.call(instance, level);
    }

    @WrapOperation(
            method = "startSleepInBed",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/attribute/BedRule;asProblem()Lnet/minecraft/world/entity/player/Player$BedSleepingProblem;"
            )
    )
    private Player.BedSleepingProblem changeSleepProblemMessageNightOwl(BedRule instance, Operation<Player.BedSleepingProblem> original) {
        ServerPlayer player = (ServerPlayer) (Object) this;

        if (player.hasAttached(ModAttachmentTypes.NIGHT_OWL)) {
            return new Player.BedSleepingProblem(Component.translatable("block.moremineralblocks.bed.no_sleep_night_owl"));
        }
        return original.call(instance);
    }
}