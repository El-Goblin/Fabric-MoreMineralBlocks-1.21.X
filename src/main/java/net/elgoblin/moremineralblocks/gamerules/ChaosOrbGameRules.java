package net.elgoblin.moremineralblocks.gamerules;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.rule.GameRule;
import net.minecraft.world.rule.GameRuleCategory;

public class ChaosOrbGameRules {

    public static final GameRule<Double> VOID_SPHERE_SIZE_MULTIPLIER = GameRuleBuilder
            .forDouble(1)
            .category(GameRuleCategory.MISC)
            .buildAndRegister(Identifier.of(MoreMineralBlocks.MOD_ID, "void_sphere_size_multiplier"));

    public static final GameRule<Integer> VOID_SPHERE_FIXED_SIZE = GameRuleBuilder
            .forInteger(-1)
            .category(GameRuleCategory.MISC)
            .buildAndRegister(Identifier.of(MoreMineralBlocks.MOD_ID, "void_sphere_fixed_size"));

    public static void init() {}
}
