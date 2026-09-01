package net.elgoblin.umamium.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeaconRenderer.class)
public abstract class BeaconRendererMixin {

    @Inject(
            method = "renderQuad",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void cancelTransparentQuads(
            PoseStack.Pose pose, VertexConsumer builder, int color, int beamStart, int beamEnd, float wnx, float wnz, float enx, float enz, float uu1, float uu2, float vv1, float vv2, CallbackInfo ci
    ) {
        // A veces el renderer agrega 32 de alpha asi que no se puede chequear solo por 0
        if ((color & 0x00FFFFFF) == 0) {
            ci.cancel();
        }
    }
}