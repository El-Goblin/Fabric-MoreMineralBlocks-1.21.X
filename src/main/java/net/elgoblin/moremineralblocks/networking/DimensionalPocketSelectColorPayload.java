package net.elgoblin.moremineralblocks.networking;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record DimensionalPocketSelectColorPayload(int colorIndex, int slot) implements CustomPayload {
    public static final Id<DimensionalPocketSelectColorPayload> ID = new Id<>(Identifier.of(MoreMineralBlocks.MOD_ID, "dimensional_pocket_select_color_payload"));
    public static final PacketCodec<RegistryByteBuf, DimensionalPocketSelectColorPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, DimensionalPocketSelectColorPayload::colorIndex,
            PacketCodecs.INTEGER, DimensionalPocketSelectColorPayload::slot,
            DimensionalPocketSelectColorPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
