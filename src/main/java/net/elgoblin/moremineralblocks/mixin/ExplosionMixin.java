package net.elgoblin.moremineralblocks.mixin;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.elgoblin.moremineralblocks.util.ProtectorManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;


@Mixin(Explosion.class)
public class ExplosionMixin {

    @Shadow
    private net.minecraft.world.World world; // shadow the private field

    @Shadow
    private ObjectArrayList<BlockPos> affectedBlocks; // shadow the private field

    @Inject(
            method = "collectBlocksAndDamageEntities",
            at = @At("TAIL")
    )
    private void protectBlocksFromExplosion(CallbackInfo ci) {
        if (!(world instanceof ServerWorld serverWorld)) return;

        ProtectorManager manager = ProtectorManager.getProtectorManager(serverWorld.getServer());
        affectedBlocks.removeIf(manager::isProtected);
    }
}