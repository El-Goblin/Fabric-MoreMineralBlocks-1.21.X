package net.elgoblin.moremineralblocks.networking.dimensionalpocket;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record DimensionalPocketSelectColorPayload(int colorIndex, int slot) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<DimensionalPocketSelectColorPayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID,
                    "dimensional_pocket_select_color_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DimensionalPocketSelectColorPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, DimensionalPocketSelectColorPayload::colorIndex,
            ByteBufCodecs.VAR_INT, DimensionalPocketSelectColorPayload::slot,
            DimensionalPocketSelectColorPayload::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
