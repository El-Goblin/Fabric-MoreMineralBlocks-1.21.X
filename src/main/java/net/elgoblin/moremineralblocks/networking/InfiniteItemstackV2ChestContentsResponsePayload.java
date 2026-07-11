package net.elgoblin.moremineralblocks.networking;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record InfiniteItemstackV2ChestContentsResponsePayload(ItemStack mainStack, ItemStack sameGroupPrevStack, ItemStack sameGroupNextStack, ItemStack prevGroupStack, ItemStack nextGroupStack) implements CustomPayload {
    public static final Id<InfiniteItemstackV2ChestContentsResponsePayload> ID = new Id<>(Identifier.of(MoreMineralBlocks.MOD_ID, "infinite_item_chest_contents_response"));
    public static final PacketCodec<RegistryByteBuf, InfiniteItemstackV2ChestContentsResponsePayload> CODEC = PacketCodec.tuple(
            ItemStack.OPTIONAL_PACKET_CODEC, InfiniteItemstackV2ChestContentsResponsePayload::mainStack,
            ItemStack.OPTIONAL_PACKET_CODEC, InfiniteItemstackV2ChestContentsResponsePayload::sameGroupPrevStack,
            ItemStack.OPTIONAL_PACKET_CODEC, InfiniteItemstackV2ChestContentsResponsePayload::sameGroupNextStack,
            ItemStack.OPTIONAL_PACKET_CODEC, InfiniteItemstackV2ChestContentsResponsePayload::prevGroupStack,
            ItemStack.OPTIONAL_PACKET_CODEC, InfiniteItemstackV2ChestContentsResponsePayload::nextGroupStack,
            InfiniteItemstackV2ChestContentsResponsePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
