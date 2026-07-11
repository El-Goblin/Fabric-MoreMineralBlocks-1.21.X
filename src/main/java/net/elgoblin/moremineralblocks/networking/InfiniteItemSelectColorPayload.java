package net.elgoblin.moremineralblocks.networking;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record InfiniteItemSelectColorPayload(int colorIndex, int slot) implements CustomPayload {
    public static final Id<InfiniteItemSelectColorPayload> ID = new Id<>(Identifier.of("moremineralblocks", "infinite_item_select_color"));
    public static final PacketCodec<RegistryByteBuf, InfiniteItemSelectColorPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, InfiniteItemSelectColorPayload::colorIndex,
            PacketCodecs.INTEGER, InfiniteItemSelectColorPayload::slot,
            InfiniteItemSelectColorPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
