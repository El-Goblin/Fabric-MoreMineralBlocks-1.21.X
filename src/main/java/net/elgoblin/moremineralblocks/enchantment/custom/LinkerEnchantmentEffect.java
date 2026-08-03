package net.elgoblin.moremineralblocks.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;

public record LinkerEnchantmentEffect() implements EnchantmentValueEffect {
    public static final MapCodec<LinkerEnchantmentEffect> CODEC = MapCodec.unit(LinkerEnchantmentEffect::new);

    @Override
    public float process(int enchantmentLevel, RandomSource random, float inputValue) {
        return 0;
    }

    @Override
    public MapCodec<? extends EnchantmentValueEffect> codec() {
        return null;
    }
}