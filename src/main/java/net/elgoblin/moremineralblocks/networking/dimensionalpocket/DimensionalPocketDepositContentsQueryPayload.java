package net.elgoblin.moremineralblocks.networking.dimensionalpocket;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public record DimensionalPocketDepositContentsQueryPayload(ItemStack itemStack) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<DimensionalPocketDepositContentsQueryPayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID,
                    "dimensional_pocket_deposit_contents_query_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DimensionalPocketDepositContentsQueryPayload> CODEC = StreamCodec.composite(
            ItemStack.OPTIONAL_STREAM_CODEC, DimensionalPocketDepositContentsQueryPayload::itemStack,
            DimensionalPocketDepositContentsQueryPayload::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}