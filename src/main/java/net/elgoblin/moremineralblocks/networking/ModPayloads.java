package net.elgoblin.moremineralblocks.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class ModPayloads {

    public static void registerPayloads() {
        PayloadTypeRegistry.serverboundPlay().register(SwitchEnchantmentToggleSafeModePayload.TYPE, SwitchEnchantmentToggleSafeModePayload.CODEC);
    }
}
