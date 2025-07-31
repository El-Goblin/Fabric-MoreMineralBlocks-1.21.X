package net.elgoblin.moremineralblocks.component;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {

    public static final ComponentType<ItemEnchantmentsComponent> OTHER_STORED_ENCHANTMENTS = register(
		"other_stored_enchantments", builder -> builder.codec(ItemEnchantmentsComponent.CODEC).packetCodec(ItemEnchantmentsComponent.PACKET_CODEC).cache()
    );
    public static final ComponentType<ItemEnchantmentsComponent> OTHER_ENCHANTMENTS = register(
            "other_enchantments", builder -> builder.codec(ItemEnchantmentsComponent.CODEC).packetCodec(ItemEnchantmentsComponent.PACKET_CODEC).cache()
    );


    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(MoreMineralBlocks.MOD_ID, name),
                        builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        MoreMineralBlocks.LOGGER.info("Registering Data Components for " + MoreMineralBlocks.MOD_ID);
    }
}
