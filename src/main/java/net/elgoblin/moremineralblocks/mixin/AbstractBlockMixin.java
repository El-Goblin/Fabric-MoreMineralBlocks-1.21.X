package net.elgoblin.moremineralblocks.mixin;

import net.elgoblin.moremineralblocks.util.ProtectorManager;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiConsumer;


@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {

    @Inject(
            method = "onExploded",
            at = @At("HEAD"),
            cancellable = true
    )
    private void protectBlocksFromExplosion(BlockState state, ServerWorld world, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> stackMerger, CallbackInfo ci) {
        MinecraftServer server = world.getServer();
        if (server != null) {
            ProtectorManager manager = ProtectorManager.getProtectorManager(server);
            if (manager.isProtected(pos)) {
                ci.cancel();
            }
        }
    }
}