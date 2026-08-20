package net.elgoblin.moremineralblocks.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.elgoblin.moremineralblocks.component.ModAttachmentTypes;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.attribute.BedRule;
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
            return !original.call(instance, level);
        }

        return original.call(instance, level);
    }
}
