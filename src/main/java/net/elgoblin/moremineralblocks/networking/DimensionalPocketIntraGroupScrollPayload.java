package net.elgoblin.moremineralblocks.networking;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record DimensionalPocketIntraGroupScrollPayload(Integer scroll) implements CustomPayload {
    public static final Id<DimensionalPocketIntraGroupScrollPayload> ID = new Id<>(Identifier.of(MoreMineralBlocks.MOD_ID, "dimensional_pocket_intra_group_scroll_payload"));
    public static final PacketCodec<RegistryByteBuf, DimensionalPocketIntraGroupScrollPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, DimensionalPocketIntraGroupScrollPayload::scroll,
            DimensionalPocketIntraGroupScrollPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
