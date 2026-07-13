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

public record DimensionPocketMiddleClickQueryPayload(ItemStack itemStack, Block clicked) implements CustomPayload {
    public static final Id<DimensionPocketMiddleClickQueryPayload> ID = new Id<>(Identifier.of(MoreMineralBlocks.MOD_ID, "dimension_pocket_middle_click_query"));
    public static final PacketCodec<RegistryByteBuf, DimensionPocketMiddleClickQueryPayload> CODEC = PacketCodec.tuple(
            ItemStack.OPTIONAL_PACKET_CODEC, DimensionPocketMiddleClickQueryPayload::itemStack,
            PacketCodecs.registryValue(RegistryKeys.BLOCK), DimensionPocketMiddleClickQueryPayload::clicked,
            DimensionPocketMiddleClickQueryPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
