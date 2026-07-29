package net.elgoblin.moremineralblocks.client;

import net.elgoblin.moremineralblocks.networking.DimensionalPocketDepositContentsQueryPayload;
import net.elgoblin.moremineralblocks.networking.DimensionalPocketDepositContentsResponsePayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.item.ItemStack;

public class InfiniteItemClientCache {
    public static ItemStack mainStack = ItemStack.EMPTY;
    public static int mainCount = 0;

    public static ItemStack sameGroupPrevStack = ItemStack.EMPTY;
    public static int sameGroupPrevCount = 0;

    public static ItemStack sameGroupNextStack = ItemStack.EMPTY;
    public static int sameGroupNextCount = 0;

    public static ItemStack prevGroupStack = ItemStack.EMPTY;
    public static int prevGroupCount = 0;

    public static ItemStack nextGroupStack = ItemStack.EMPTY;
    public static int nextGroupCount = 0;

    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(DimensionalPocketDepositContentsResponsePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                mainStack = payload.mainStack();
                mainCount = payload.mainCount();

                sameGroupPrevStack = payload.sameGroupPrevStack();
                sameGroupPrevCount = payload.sameGroupPrevCount();

                sameGroupNextStack = payload.sameGroupNextStack();
                sameGroupNextCount = payload.sameGroupNextCount();

                prevGroupStack = payload.prevGroupStack();
                prevGroupCount = payload.prevGroupCount();

                nextGroupStack = payload.nextGroupStack();
                nextGroupCount = payload.nextGroupCount();
            });
        });
    }

    public static void requestUpdate(ItemStack stack) {
        ClientPlayNetworking.send(new DimensionalPocketDepositContentsQueryPayload(stack));
    }
}
