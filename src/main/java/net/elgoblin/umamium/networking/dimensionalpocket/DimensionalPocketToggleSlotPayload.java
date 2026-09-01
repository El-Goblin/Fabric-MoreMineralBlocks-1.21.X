package net.elgoblin.umamium.networking.dimensionalpocket;

import net.elgoblin.umamium.Umamium;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record DimensionalPocketToggleSlotPayload(int targetSlotId, int clickedSlotId, int colorIndex) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<DimensionalPocketToggleSlotPayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(Umamium.MOD_ID,
                    "dimensional_pocket_toggle_slot_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DimensionalPocketToggleSlotPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, DimensionalPocketToggleSlotPayload::targetSlotId,
            ByteBufCodecs.VAR_INT, DimensionalPocketToggleSlotPayload::clickedSlotId,
            ByteBufCodecs.VAR_INT, DimensionalPocketToggleSlotPayload::colorIndex,
            DimensionalPocketToggleSlotPayload::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}