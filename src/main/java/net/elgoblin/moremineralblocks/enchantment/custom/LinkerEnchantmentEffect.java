package net.elgoblin.moremineralblocks.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.enchantment.effect.EnchantmentValueEffect;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

public record LinkerEnchantmentEffect() implements EnchantmentValueEffect {
    public static final MapCodec<LinkerEnchantmentEffect> CODEC = MapCodec.unit(LinkerEnchantmentEffect::new);

    @Override
    public float apply(int level, Random random, float inputValue) {
        return 0;
    }

    @Override
    public MapCodec<? extends EnchantmentValueEffect> getCodec() {
        return CODEC;
    }
}
