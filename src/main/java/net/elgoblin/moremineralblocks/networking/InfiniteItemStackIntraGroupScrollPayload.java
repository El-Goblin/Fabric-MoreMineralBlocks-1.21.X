package net.elgoblin.moremineralblocks.networking;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record InfiniteItemStackIntraGroupScrollPayload(Integer scroll) implements CustomPayload {
    public static final Id<InfiniteItemStackIntraGroupScrollPayload> ID = new Id<>(Identifier.of(MoreMineralBlocks.MOD_ID, "infinite_item_intra_group_scroll"));
    public static final PacketCodec<RegistryByteBuf, InfiniteItemStackIntraGroupScrollPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, InfiniteItemStackIntraGroupScrollPayload::scroll,
            InfiniteItemStackIntraGroupScrollPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
