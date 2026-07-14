package net.elgoblin.moremineralblocks.client;

import net.elgoblin.moremineralblocks.networking.DimensionalPocketDepositContentsQueryPayload;
import net.elgoblin.moremineralblocks.networking.DimensionalPocketDepositContentsResponsePayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.item.ItemStack;

public class InfiniteItemClientCache {
    public static ItemStack mainRenderedStack = ItemStack.EMPTY;
    public static ItemStack sameGroupPrevStack = ItemStack.EMPTY;
    public static ItemStack sameGroupNextStack = ItemStack.EMPTY;
    public static ItemStack prevGroupStack = ItemStack.EMPTY;
    public static ItemStack nextGroupStack = ItemStack.EMPTY;

    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(DimensionalPocketDepositContentsResponsePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                mainRenderedStack = payload.mainStack();
                sameGroupPrevStack = payload.sameGroupPrevStack();
                sameGroupNextStack = payload.sameGroupNextStack();
                prevGroupStack = payload.prevGroupStack();
                nextGroupStack = payload.nextGroupStack();
            });
        });
    }

    public static void requestUpdate(ItemStack stack) {
        ClientPlayNetworking.send(new DimensionalPocketDepositContentsQueryPayload(stack));
    }
}
