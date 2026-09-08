package net.elgoblin.umamium.mixin;

import net.elgoblin.umamium.tags.ModTags;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.portal.PortalShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PortalShape.class)
public abstract class PortalShapeMixin {

    @Shadow
    @Final
    @Mutable
    private static BlockBehaviour.StatePredicate FRAME;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void replaceFrame(CallbackInfo ci) {
        FRAME = (state, level, pos) -> state.is(ModTags.Blocks.NETHER_PORTAL_BLOCK);
    }
}