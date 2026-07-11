package net.elgoblin.moremineralblocks.networking;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ToggleSlotPayload(int targetSlotId, int clickedSlotId, int colorIndex) implements CustomPayload {

    public static final CustomPayload.Id<ToggleSlotPayload> ID = new CustomPayload.Id<>(
            Identifier.of(MoreMineralBlocks.MOD_ID, "toggle_slot")
    );

    public static final PacketCodec<RegistryByteBuf, ToggleSlotPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.VAR_INT, ToggleSlotPayload::targetSlotId,
            PacketCodecs.VAR_INT, ToggleSlotPayload::clickedSlotId,
            PacketCodecs.VAR_INT, ToggleSlotPayload::colorIndex,
            ToggleSlotPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
