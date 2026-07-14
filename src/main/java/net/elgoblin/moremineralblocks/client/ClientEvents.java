package net.elgoblin.moremineralblocks.client;

import net.elgoblin.moremineralblocks.MoreMineralBlocksClient;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.networking.DimensionalPocketInterGroupScrollPayload;
import net.elgoblin.moremineralblocks.networking.DimensionalPocketIntraGroupScrollPayload;
import net.elgoblin.moremineralblocks.networking.SwitchEnchantmentToggleSafeModePayload;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;

public class ClientEvents {

    public static final MinecraftClient minecraft = MinecraftClient.getInstance();
    private static int tickCounter = 0;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            tickCounter++;
            if (tickCounter < 0) {tickCounter = 0;}

            while (MoreMineralBlocksClient.switchEnchantments_toggleSafeMode.wasPressed()) {
                ClientPlayNetworking.send(new SwitchEnchantmentToggleSafeModePayload());
            }
            while (MoreMineralBlocksClient.switchEnchantments_toggleSafeMode.wasPressed()) {
                ClientPlayNetworking.send(new SwitchEnchantmentToggleSafeModePayload());
            }

            if (client.player != null && tickCounter % 2 == 0) {
                ItemStack mainHoldedStack = client.player.getMainHandStack();
                ItemStack offHoldedStack = client.player.getOffHandStack();
                ItemStack activeItem = ItemStack.EMPTY;

                if (mainHoldedStack.isOf(ModItems.DIMENSIONAL_POCKET)) {
                    activeItem = mainHoldedStack;
                } else if (offHoldedStack.isOf(ModItems.DIMENSIONAL_POCKET)) {
                    activeItem = offHoldedStack;
                }

                if (!activeItem.isEmpty()) {
                    InfiniteItemClientCache.requestUpdate(activeItem);
                }
            }
        });
    }

    public static boolean onScroll(double delta) {
        ClientPlayerEntity player = minecraft.player;
        if (player != null && minecraft.getWindow() != null) {

            ItemStack mainHand = player.getMainHandStack();
            ItemStack offHand = player.getOffHandStack();

            boolean isHoldingInfiniteItem = mainHand.isOf(ModItems.DIMENSIONAL_POCKET) || offHand.isOf(ModItems.DIMENSIONAL_POCKET);
            boolean tabPressed = MoreMineralBlocksClient.intraGroupScroll.isPressed();
            boolean gravePressed = MoreMineralBlocksClient.interGroupScroll.isPressed();

            if (isHoldingInfiniteItem && delta != 0) {
                int scroll = delta > 0 ? 1 : -1;

                if (tabPressed) {
                    ClientPlayNetworking.send(new DimensionalPocketIntraGroupScrollPayload(scroll));
                    return true;
                }
                else if (gravePressed) {
                    ClientPlayNetworking.send(new DimensionalPocketInterGroupScrollPayload(scroll));
                    return true;
                }
            }
        }
        return false;
    }
}
