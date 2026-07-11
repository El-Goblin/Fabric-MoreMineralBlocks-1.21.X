package net.elgoblin.moremineralblocks.networking;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record SwitchEnchantmentToggleSafeModePayload() implements CustomPayload {
    public static final Id<SwitchEnchantmentToggleSafeModePayload> ID = new Id<>(Identifier.of(MoreMineralBlocks.MOD_ID, "switch_enchantments_toggle_safe_mode"));
    public static final PacketCodec<RegistryByteBuf, SwitchEnchantmentToggleSafeModePayload> CODEC = PacketCodec.unit(new SwitchEnchantmentToggleSafeModePayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
