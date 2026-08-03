package net.elgoblin.moremineralblocks.enchantment;

import com.mojang.serialization.MapCodec;
import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.enchantment.custom.LinkerEnchantmentEffect;
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
                Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID, name),
                codec
        );
    }

    public static void registerEnchantmentEffects() {
        MoreMineralBlocks.LOGGER.info("Registering Mod Enchantment Effects for " + MoreMineralBlocks.MOD_ID);
    }
}