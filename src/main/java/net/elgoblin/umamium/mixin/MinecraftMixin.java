package net.elgoblin.umamium.mixin;

import net.elgoblin.umamium.item.ModItems;
import net.elgoblin.umamium.networking.dimensionalpocket.DimensionalPocketMiddleClickQueryPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow
    public LocalPlayer player;

    @Shadow
    public HitResult hitResult;

    @Shadow
    public ClientLevel level;

    @Inject(method = "pickBlockOrEntity", at = @At("HEAD"), cancellable = true)
    private void onPickBlock(CallbackInfo ci) {
        if (this.player == null || this.hitResult == null) {
            return;
        }

        if (this.hitResult.getType() != HitResult.Type.BLOCK) {
            return;
        }

        BlockHitResult blockHit = (BlockHitResult) this.hitResult;
        BlockState state = this.level.getBlockState(blockHit.getBlockPos());
        Block block = state.getBlock();

        ItemStack selectedItem = this.player.getItemInHand(InteractionHand.MAIN_HAND);
        if (!selectedItem.is(ModItems.DIMENSIONAL_POCKET)) {
            selectedItem = this.player.getItemInHand(InteractionHand.OFF_HAND);
        }

        if (!selectedItem.is(ModItems.DIMENSIONAL_POCKET)) {
            return;
        }

        ClientPlayNetworking.send(new DimensionalPocketMiddleClickQueryPayload(selectedItem, block));
        ci.cancel();
    }
}