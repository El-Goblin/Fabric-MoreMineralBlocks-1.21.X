package net.elgoblin.moremineralblocks.networking.dimensionalpocket;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public record DimensionalPocketMiddleClickQueryPayload(ItemStack itemStack, Block clicked) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<DimensionalPocketMiddleClickQueryPayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID,
                    "dimensional_pocket_middle_click_query_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DimensionalPocketMiddleClickQueryPayload> CODEC = StreamCodec.composite(
            ItemStack.OPTIONAL_STREAM_CODEC, DimensionalPocketMiddleClickQueryPayload::itemStack,
            ByteBufCodecs.registry(Registries.BLOCK), DimensionalPocketMiddleClickQueryPayload::clicked,
            DimensionalPocketMiddleClickQueryPayload::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
