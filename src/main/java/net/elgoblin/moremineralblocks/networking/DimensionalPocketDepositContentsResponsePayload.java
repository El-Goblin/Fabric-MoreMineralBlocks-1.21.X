package net.elgoblin.moremineralblocks.networking;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.fabricmc.fabric.impl.recipe.ingredient.OptionalCustomIngredientPacketCodec;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record DimensionalPocketDepositContentsResponsePayload(ItemStack mainStack, Integer mainCount,
                                                              ItemStack sameGroupPrevStack, Integer sameGroupPrevCount,
                                                              ItemStack sameGroupNextStack, Integer sameGroupNextCount,
                                                              ItemStack prevGroupStack, Integer prevGroupCount,
                                                              ItemStack nextGroupStack, Integer nextGroupCount) implements CustomPayload {
    public static final Id<DimensionalPocketDepositContentsResponsePayload> ID = new Id<>(Identifier.of(MoreMineralBlocks.MOD_ID, "dimensional_pocket_deposit_contents_response_payload"));
    public static final PacketCodec<RegistryByteBuf, DimensionalPocketDepositContentsResponsePayload> CODEC = PacketCodec.tuple(
            ItemStack.OPTIONAL_PACKET_CODEC, DimensionalPocketDepositContentsResponsePayload::mainStack,
            PacketCodecs.INTEGER, DimensionalPocketDepositContentsResponsePayload::mainCount,
            ItemStack.OPTIONAL_PACKET_CODEC, DimensionalPocketDepositContentsResponsePayload::sameGroupPrevStack,
            PacketCodecs.INTEGER, DimensionalPocketDepositContentsResponsePayload::sameGroupPrevCount,
            ItemStack.OPTIONAL_PACKET_CODEC, DimensionalPocketDepositContentsResponsePayload::sameGroupNextStack,
            PacketCodecs.INTEGER, DimensionalPocketDepositContentsResponsePayload::sameGroupNextCount,
            ItemStack.OPTIONAL_PACKET_CODEC, DimensionalPocketDepositContentsResponsePayload::prevGroupStack,
            PacketCodecs.INTEGER, DimensionalPocketDepositContentsResponsePayload::prevGroupCount,
            ItemStack.OPTIONAL_PACKET_CODEC, DimensionalPocketDepositContentsResponsePayload::nextGroupStack,
            PacketCodecs.INTEGER, DimensionalPocketDepositContentsResponsePayload::nextGroupCount,
            DimensionalPocketDepositContentsResponsePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
