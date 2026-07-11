package net.elgoblin.moremineralblocks.networking;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record InfiniteItemstackV2ChestContentsQueryPayload(ItemStack itemStack) implements CustomPayload {
    public static final Id<InfiniteItemstackV2ChestContentsQueryPayload> ID = new Id<>(Identifier.of(MoreMineralBlocks.MOD_ID, "infinite_item_chest_contents_query"));
    public static final PacketCodec<RegistryByteBuf, InfiniteItemstackV2ChestContentsQueryPayload> CODEC = PacketCodec.tuple(
            ItemStack.OPTIONAL_PACKET_CODEC, InfiniteItemstackV2ChestContentsQueryPayload::itemStack,
            InfiniteItemstackV2ChestContentsQueryPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
