package net.elgoblin.moremineralblocks.component;

import com.mojang.serialization.Codec;
import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.effect.LaLechonaConsumeEffect;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

import static net.minecraft.component.type.ConsumableComponents.drink;

public class ModDataComponentTypes {

    public static final ComponentType<ItemEnchantmentsComponent> OTHER_STORED_ENCHANTMENTS = register(
		"other_stored_enchantments", builder -> builder.codec(ItemEnchantmentsComponent.CODEC).packetCodec(ItemEnchantmentsComponent.PACKET_CODEC).cache()
    );
    public static final ComponentType<ItemEnchantmentsComponent> OTHER_ENCHANTMENTS = register(
            "other_enchantments", builder -> builder.codec(ItemEnchantmentsComponent.CODEC).packetCodec(ItemEnchantmentsComponent.PACKET_CODEC).cache()
    );
    public static final ComponentType<String> ENCHANTMENT_SET = register("enchantment_set", builder -> builder.codec(Codec.STRING));


    public static final ComponentType<BlockPos> LINKED_CHEST = register("linked_chest", builder -> builder.codec(BlockPos.CODEC));
    public static final ComponentType<BlockPos> OTHER_LINKED_CHEST = register("other_linked_chest", builder -> builder.codec(BlockPos.CODEC));
    public static final ComponentType<Vec3d> COORDINATES = register("coordinates", builder -> builder.codec(Vec3d.CODEC));
    public static final ComponentType<Identifier> SERVERWORLD = register("server_id", builder -> builder.codec(Identifier.CODEC));
    public static final ComponentType<Identifier> OTHER_SERVERWORLD = register("other_server_id", builder -> builder.codec(Identifier.CODEC));
    public static final ComponentType<Integer> INTER_GROUP_POINTER = register("inter_group_pointer", builder -> builder.codec(Codec.INT));

    public static final ComponentType<Integer> SELECTED_COLOR = register("selected_color", builder -> builder.codec(Codec.INT));
    public static final ComponentType<List<Integer>> INTRA_GROUP_POINTERS = register("intra_group_pointers", builder -> builder.codec(Codec.INT.listOf()));

    public static final ComponentType<Boolean> SAFE_MODE = register("safe_mode", builder -> builder.codec(Codec.BOOL));

    public static final Map<DyeColor, ComponentType<List<Integer>>> COLOR_INVENTORIES = new EnumMap<>(DyeColor.class);

    public static void registerComponents() {
        for (DyeColor color : DyeColor.values()) {
            final String name = color.name().toLowerCase() + "_inventory_pointers";

            ComponentType<List<Integer>> component = register(name, builder -> builder
                    .codec(Codec.list(Codec.INT))
                    .packetCodec(PacketCodecs.INTEGER.collect(PacketCodecs.toList()))
            );

            COLOR_INVENTORIES.put(color, component);
        }
    }


//    public static final ComponentType<Integer> CUMULATED_DAMAGE_TAKEN = register(
//            "cumulated_damage_taken", builder -> builder.codec(Codecs.rangedInt(0, 100)).packetCodec(PacketCodecs.VAR_INT).cache()
//    );
    public static final ComponentType<ItemStack> CHOSEN_INFINITE_ITEM = register("chosen_infinite_item",
        builder -> builder.codec(ItemStack.CODEC).packetCodec(ItemStack.PACKET_CODEC).cache());

    public static final ConsumableComponent LA_LECHONA = drink().consumeEffect(LaLechonaConsumeEffect.INSTANCE).build();


    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(MoreMineralBlocks.MOD_ID, name),
                        builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        MoreMineralBlocks.LOGGER.info("Registering Data Components for " + MoreMineralBlocks.MOD_ID);
        registerComponents();
    }
}
