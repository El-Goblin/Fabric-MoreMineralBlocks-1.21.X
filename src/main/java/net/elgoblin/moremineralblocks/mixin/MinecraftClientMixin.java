package net.elgoblin.moremineralblocks.mixin;

import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.networking.DimensionalPocketMiddleClickQueryPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Shadow
    public ClientPlayerEntity player;
    @Shadow public HitResult crosshairTarget;
    @Shadow public ClientWorld world;

    @Inject(method = "doItemPick", at = @At("HEAD"), cancellable = true)
    private void onDoItemPick(CallbackInfo ci) {
        if (this.player == null || this.crosshairTarget == null) {
            return;
        }

        if (this.crosshairTarget.getType() != HitResult.Type.BLOCK) {return;}

        BlockHitResult blockHit = (BlockHitResult) this.crosshairTarget;
        BlockState state = this.world.getBlockState(blockHit.getBlockPos());
        Block block = state.getBlock();

        ItemStack selectedItem = player.getStackInHand(Hand.MAIN_HAND);
        if (!selectedItem.isOf(ModItems.DIMENSIONAL_POCKET)) {
            selectedItem = player.getStackInHand(Hand.OFF_HAND);
        }
        if (!selectedItem.isOf(ModItems.DIMENSIONAL_POCKET)) {
            return;
        }

        ClientPlayNetworking.send(new DimensionalPocketMiddleClickQueryPayload(selectedItem, block));
        ci.cancel();
    }
}