package net.elgoblin.moremineralblocks.networking;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record LegendaryToolsSwitchEnchantmentPayload() implements CustomPayload {
    public static final Id<LegendaryToolsSwitchEnchantmentPayload> ID = new Id<>(Identifier.of(MoreMineralBlocks.MOD_ID, "switch_enchantments"));
    public static final PacketCodec<RegistryByteBuf, LegendaryToolsSwitchEnchantmentPayload> CODEC = PacketCodec.unit(new LegendaryToolsSwitchEnchantmentPayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
