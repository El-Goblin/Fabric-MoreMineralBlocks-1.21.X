package net.elgoblin.moremineralblocks.networking;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public record DimensionalPocketMiddleClickQueryPayload(ItemStack itemStack, Block clicked) implements CustomPayload {
    public static final Id<DimensionalPocketMiddleClickQueryPayload> ID = new Id<>(Identifier.of(MoreMineralBlocks.MOD_ID, "dimensional_pocket_middle_click_query_payload"));
    public static final PacketCodec<RegistryByteBuf, DimensionalPocketMiddleClickQueryPayload> CODEC = PacketCodec.tuple(
            ItemStack.OPTIONAL_PACKET_CODEC, DimensionalPocketMiddleClickQueryPayload::itemStack,
            PacketCodecs.registryValue(RegistryKeys.BLOCK), DimensionalPocketMiddleClickQueryPayload::clicked,
            DimensionalPocketMiddleClickQueryPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
