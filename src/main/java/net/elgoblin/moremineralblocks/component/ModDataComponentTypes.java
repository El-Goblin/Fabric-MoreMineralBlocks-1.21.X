package net.elgoblin.moremineralblocks.component;

import com.mojang.serialization.Codec;
import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {

    public static final ComponentType<ItemEnchantmentsComponent> OTHER_STORED_ENCHANTMENTS = register(
		"other_stored_enchantments", builder -> builder.codec(ItemEnchantmentsComponent.CODEC).packetCodec(ItemEnchantmentsComponent.PACKET_CODEC).cache()
    );
    public static final ComponentType<ItemEnchantmentsComponent> OTHER_ENCHANTMENTS = register(
            "other_enchantments", builder -> builder.codec(ItemEnchantmentsComponent.CODEC).packetCodec(ItemEnchantmentsComponent.PACKET_CODEC).cache()
    );

    public static final ComponentType<Vec3d> COORDINATES = register("coordinates", builder -> builder.codec(Vec3d.CODEC));
    public static final ComponentType<Identifier> SERVERWORLD = register("server_id", builder -> builder.codec(Identifier.CODEC));


//    public static final ComponentType<Integer> CUMULATED_DAMAGE_TAKEN = register(
//            "cumulated_damage_taken", builder -> builder.codec(Codecs.rangedInt(0, 100)).packetCodec(PacketCodecs.VAR_INT).cache()
//    );
    public static final ComponentType<ItemStack> CHOSEN_INFINITE_ITEM = register("chosen_infinite_item", builder -> builder.codec(ItemStack.CODEC));


    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(MoreMineralBlocks.MOD_ID, name),
                        builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        MoreMineralBlocks.LOGGER.info("Registering Data Components for " + MoreMineralBlocks.MOD_ID);
    }
}
