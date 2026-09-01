package net.elgoblin.umamium.networking.dimensionalpocket;

import net.elgoblin.umamium.Umamium;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public record DimensionalPocketDepositContentsResponsePayload(ItemStack mainStack, Integer mainCount,
                                                              ItemStack sameGroupPrevStack,
                                                              ItemStack sameGroupNextStack,
                                                              ItemStack prevGroupStack, Integer prevGroupIndex,
                                                              ItemStack nextGroupStack, Integer nextGroupIndex,
                                                              Integer mainStackDuplicateCount) implements CustomPacketPayload {
    public static final Type<DimensionalPocketDepositContentsResponsePayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(Umamium.MOD_ID,
                    "dimensional_pocket_deposit_contents_response_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DimensionalPocketDepositContentsResponsePayload> CODEC = StreamCodec.composite(
            ItemStack.OPTIONAL_STREAM_CODEC, DimensionalPocketDepositContentsResponsePayload::mainStack,
            ByteBufCodecs.VAR_INT, DimensionalPocketDepositContentsResponsePayload::mainCount,
            ItemStack.OPTIONAL_STREAM_CODEC, DimensionalPocketDepositContentsResponsePayload::sameGroupPrevStack,
            ItemStack.OPTIONAL_STREAM_CODEC, DimensionalPocketDepositContentsResponsePayload::sameGroupNextStack,
            ItemStack.OPTIONAL_STREAM_CODEC, DimensionalPocketDepositContentsResponsePayload::prevGroupStack,
            ByteBufCodecs.VAR_INT, DimensionalPocketDepositContentsResponsePayload::prevGroupIndex,
            ItemStack.OPTIONAL_STREAM_CODEC, DimensionalPocketDepositContentsResponsePayload::nextGroupStack,
            ByteBufCodecs.VAR_INT, DimensionalPocketDepositContentsResponsePayload::nextGroupIndex,
            ByteBufCodecs.VAR_INT, DimensionalPocketDepositContentsResponsePayload::mainStackDuplicateCount,
            DimensionalPocketDepositContentsResponsePayload::new);

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
