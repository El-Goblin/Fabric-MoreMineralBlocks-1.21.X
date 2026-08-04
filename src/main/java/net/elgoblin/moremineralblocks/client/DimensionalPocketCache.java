package net.elgoblin.moremineralblocks.client;

import net.elgoblin.moremineralblocks.networking.dimensionalpocket.DimensionalPocketDepositContentsQueryPayload;
import net.elgoblin.moremineralblocks.networking.dimensionalpocket.DimensionalPocketDepositContentsResponsePayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.world.item.ItemStack;

public class DimensionalPocketCache {
    public static ItemStack mainStack = ItemStack.EMPTY;
    public static int mainStackCount = 0;
    public static int mainStackDuplicateCount = 0;

    public static ItemStack sameGroupPrevStack = ItemStack.EMPTY;
    public static ItemStack sameGroupNextStack = ItemStack.EMPTY;

    public static ItemStack prevGroupStack = ItemStack.EMPTY;
    public static int prevGroupIndex = 0;

    public static ItemStack nextGroupStack = ItemStack.EMPTY;
    public static int nextGroupIndex = 0;

    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(DimensionalPocketDepositContentsResponsePayload.TYPE, (payload, context) -> {
            context.client().execute(() -> {
                mainStack = payload.mainStack();
                mainStackCount = payload.mainCount();
                mainStackDuplicateCount = payload.mainStackDuplicateCount();

                sameGroupPrevStack = payload.sameGroupPrevStack();

                sameGroupNextStack = payload.sameGroupNextStack();

                prevGroupStack = payload.prevGroupStack();
                prevGroupIndex = payload.prevGroupIndex();

                nextGroupStack = payload.nextGroupStack();
                nextGroupIndex = payload.nextGroupIndex();
            });
        });
    }

    public static void requestUpdate(ItemStack stack) {
        ClientPlayNetworking.send(new DimensionalPocketDepositContentsQueryPayload(stack));
    }
}