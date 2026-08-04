package net.elgoblin.moremineralblocks.networking.dimensionalpocket;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record DimensionalPocketScrollBetweenGroupsPayload(Integer scroll) implements CustomPacketPayload {
    public static final Type<DimensionalPocketScrollBetweenGroupsPayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID,
                    "dimensional_pocket_scroll_between_groups_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DimensionalPocketScrollBetweenGroupsPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, DimensionalPocketScrollBetweenGroupsPayload::scroll,
            DimensionalPocketScrollBetweenGroupsPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
