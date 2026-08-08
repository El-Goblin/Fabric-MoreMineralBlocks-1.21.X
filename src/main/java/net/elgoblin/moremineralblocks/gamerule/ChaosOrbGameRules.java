package net.elgoblin.moremineralblocks.gamerule;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

public class ChaosOrbGameRules {

    public static final GameRule<Double> VOID_SPHERE_SIZE_MULTIPLIER = GameRuleBuilder
            .forDouble(1)
            .category(GameRuleCategory.MISC)
            .buildAndRegister(Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID, "void_sphere_size_multiplier"));

    public static final GameRule<Integer> VOID_SPHERE_FIXED_SIZE = GameRuleBuilder
            .forInteger(-1)
            .category(GameRuleCategory.MISC)
            .buildAndRegister(Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID, "void_sphere_fixed_size"));

    public static final GameRule<Integer> SKYBLOCK_RADIUS = GameRuleBuilder
            .forInteger(7)
            .category(GameRuleCategory.MISC)
            .buildAndRegister(Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID, "skyblock_radius"));

    public static final GameRule<Integer> BLOCKS_PER_TICK = GameRuleBuilder
            .forInteger(50000)
            .category(GameRuleCategory.MISC)
            .buildAndRegister(Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID, "terrain_manager_blocks_per_tick"));

    public static void init() {}
}
