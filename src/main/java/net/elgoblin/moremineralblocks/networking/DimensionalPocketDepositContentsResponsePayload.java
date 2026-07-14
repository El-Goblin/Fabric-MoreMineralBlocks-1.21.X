package net.elgoblin.moremineralblocks.networking;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record DimensionalPocketDepositContentsResponsePayload(ItemStack mainStack, ItemStack sameGroupPrevStack, ItemStack sameGroupNextStack, ItemStack prevGroupStack, ItemStack nextGroupStack) implements CustomPayload {
    public static final Id<DimensionalPocketDepositContentsResponsePayload> ID = new Id<>(Identifier.of(MoreMineralBlocks.MOD_ID, "dimensional_pocket_deposit_contents_response_payload"));
    public static final PacketCodec<RegistryByteBuf, DimensionalPocketDepositContentsResponsePayload> CODEC = PacketCodec.tuple(
            ItemStack.OPTIONAL_PACKET_CODEC, DimensionalPocketDepositContentsResponsePayload::mainStack,
            ItemStack.OPTIONAL_PACKET_CODEC, DimensionalPocketDepositContentsResponsePayload::sameGroupPrevStack,
            ItemStack.OPTIONAL_PACKET_CODEC, DimensionalPocketDepositContentsResponsePayload::sameGroupNextStack,
            ItemStack.OPTIONAL_PACKET_CODEC, DimensionalPocketDepositContentsResponsePayload::prevGroupStack,
            ItemStack.OPTIONAL_PACKET_CODEC, DimensionalPocketDepositContentsResponsePayload::nextGroupStack,
            DimensionalPocketDepositContentsResponsePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
