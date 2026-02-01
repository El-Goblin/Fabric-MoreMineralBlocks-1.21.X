package net.elgoblin.moremineralblocks.enchantment;

import com.mojang.serialization.MapCodec;
import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.enchantment.custom.LinkerEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.enchantment.effect.EnchantmentValueEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {

    public static final MapCodec<? extends EnchantmentValueEffect> LINKER = registerValueEffect("linker", LinkerEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentValueEffect> registerValueEffect(String name,
                                                                                  MapCodec<? extends EnchantmentValueEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_VALUE_EFFECT_TYPE, Identifier.of(MoreMineralBlocks.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects() {
        MoreMineralBlocks.LOGGER.info("Registering Mod Enchantment Effects for " + MoreMineralBlocks.MOD_ID);
    }
}
