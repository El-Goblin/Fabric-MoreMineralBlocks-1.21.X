package net.elgoblin.moremineralblocks.client;

import net.elgoblin.moremineralblocks.MoreMineralBlocksClient;
import net.elgoblin.moremineralblocks.networking.SwitchEnchantmentToggleSafeModePayload;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;

public class ClientEvents {

    public static final Minecraft minecraft = Minecraft.getInstance();


    public static void registerClientEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (MoreMineralBlocksClient.SWITCH_ENCHANTMENTS_TOGGLE_SAFE_MODE.consumeClick()) {
                ClientPlayNetworking.send(new SwitchEnchantmentToggleSafeModePayload());
            }
        });
    }
}
