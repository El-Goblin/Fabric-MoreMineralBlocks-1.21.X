package net.elgoblin.umamium.networking;

import net.elgoblin.umamium.Umamium;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record SwitchEnchantmentToggleSafeModePayload() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<SwitchEnchantmentToggleSafeModePayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "switch_enchantments_toggle_safe_mode_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SwitchEnchantmentToggleSafeModePayload> CODEC =
            StreamCodec.unit(new SwitchEnchantmentToggleSafeModePayload());

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}