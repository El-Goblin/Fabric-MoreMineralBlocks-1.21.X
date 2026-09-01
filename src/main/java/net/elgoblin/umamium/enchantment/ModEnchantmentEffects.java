package net.elgoblin.umamium.enchantment;

import com.mojang.serialization.MapCodec;
import net.elgoblin.umamium.Umamium;
import net.elgoblin.umamium.enchantment.custom.LinkerEnchantmentEffect;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;

public class ModEnchantmentEffects {

    public static final MapCodec<? extends EnchantmentValueEffect> LINKER = registerValueEffect("linker", LinkerEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentValueEffect> registerValueEffect(String name,
                                                                                  MapCodec<? extends EnchantmentValueEffect> codec) {
        return Registry.register(
                BuiltInRegistries.ENCHANTMENT_VALUE_EFFECT_TYPE,
                Identifier.fromNamespaceAndPath(Umamium.MOD_ID, name),
                codec
        );
    }

    public static void registerEnchantmentEffects() {
        Umamium.LOGGER.info("Registering Mod Enchantment Effects for " + Umamium.MOD_ID);
    }
}