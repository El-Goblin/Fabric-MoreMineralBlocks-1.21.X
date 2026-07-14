package net.elgoblin.moremineralblocks.networking;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record DimensionalPocketToggleSlotPayload(int targetSlotId, int clickedSlotId, int colorIndex) implements CustomPayload {

    public static final CustomPayload.Id<DimensionalPocketToggleSlotPayload> ID = new CustomPayload.Id<>(
            Identifier.of(MoreMineralBlocks.MOD_ID, "dimensional_pocket_toggle_slot_payload")
    );

    public static final PacketCodec<RegistryByteBuf, DimensionalPocketToggleSlotPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.VAR_INT, DimensionalPocketToggleSlotPayload::targetSlotId,
            PacketCodecs.VAR_INT, DimensionalPocketToggleSlotPayload::clickedSlotId,
            PacketCodecs.VAR_INT, DimensionalPocketToggleSlotPayload::colorIndex,
            DimensionalPocketToggleSlotPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
