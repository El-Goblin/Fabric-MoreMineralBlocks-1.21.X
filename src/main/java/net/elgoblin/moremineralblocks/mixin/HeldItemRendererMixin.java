package net.elgoblin.moremineralblocks.mixin;

import net.elgoblin.moremineralblocks.client.InfiniteItemClientCache;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;


@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    @ModifyArg(
            method = "renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/command/render/OrderedRenderCommandQueue;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/item/ItemModelManager;clearAndUpdate(Lnet/minecraft/client/render/item/ItemRenderState;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/world/World;Lnet/minecraft/client/render/item/HeldItemContext;I)V"
            ),
            index = 1
    )
    private ItemStack replaceRenderedStack(ItemStack original) {
        if (original.isOf(ModItems.DIMENSIONAL_POCKET)) {
            ItemStack replacement = InfiniteItemClientCache.mainStack;

            if (replacement != null && !replacement.isEmpty()) {
                return replacement;
            }
        }

        return original;
    }
}