package net.elgoblin.moremineralblocks.mixin;

import net.elgoblin.moremineralblocks.util.ProtectorManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiConsumer;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {

    @Inject(
            method = "onExplosionHit",
            at = @At("HEAD"),
            cancellable = true
    )
    private void protectBlocksFromExplosion(BlockState state, ServerLevel world, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> stackMerger, CallbackInfo ci) {
        MinecraftServer server = world.getServer();
        ProtectorManager manager = ProtectorManager.getProtectorManager(server);
        if (manager.isProtected(pos)) {
            ci.cancel();
        }
    }
}