package net.elgoblin.moremineralblocks.networking;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record DimensionalPocketDepositContentsQueryPayload(ItemStack itemStack) implements CustomPayload {
    public static final Id<DimensionalPocketDepositContentsQueryPayload> ID = new Id<>(Identifier.of(MoreMineralBlocks.MOD_ID, "dimensional_pocket_deposit_contents_query_payload"));
    public static final PacketCodec<RegistryByteBuf, DimensionalPocketDepositContentsQueryPayload> CODEC = PacketCodec.tuple(
            ItemStack.OPTIONAL_PACKET_CODEC, DimensionalPocketDepositContentsQueryPayload::itemStack,
            DimensionalPocketDepositContentsQueryPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
