package net.elgoblin.umamium.networking.dimensionalpocket;

import net.elgoblin.umamium.Umamium;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public record DimensionalPocketScrollInsideGroupPayload(Integer scroll, ItemStack prevItem, ItemStack nextItem) implements CustomPacketPayload {
    public static final Type<DimensionalPocketScrollInsideGroupPayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(Umamium.MOD_ID,
                    "dimensional_pocket_scroll_inside_group_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DimensionalPocketScrollInsideGroupPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, DimensionalPocketScrollInsideGroupPayload::scroll,
            ItemStack.OPTIONAL_STREAM_CODEC, DimensionalPocketScrollInsideGroupPayload::prevItem,
            ItemStack.OPTIONAL_STREAM_CODEC, DimensionalPocketScrollInsideGroupPayload::nextItem,
            DimensionalPocketScrollInsideGroupPayload::new);

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
