package net.elgoblin.umamium.gamerule;

import net.elgoblin.umamium.Umamium;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

public class ChaosOrbGameRules {

    public static final GameRule<Double> VOID_SPHERE_SIZE_MULTIPLIER = GameRuleBuilder
            .forDouble(1)
            .category(GameRuleCategory.MISC)
            .buildAndRegister(Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "void_sphere_size_multiplier"));

    public static final GameRule<Integer> VOID_SPHERE_FIXED_SIZE = GameRuleBuilder
            .forInteger(-1)
            .category(GameRuleCategory.MISC)
            .buildAndRegister(Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "void_sphere_fixed_size"));

    public static final GameRule<Integer> SNOW_GOLEM_LIFETIME = GameRuleBuilder
            .forInteger(1200)
            .category(GameRuleCategory.MISC)
            .buildAndRegister(Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "snow_golem_lifetime"));

    public static final GameRule<Integer> SKYBLOCK_RADIUS = GameRuleBuilder
            .forInteger(7)
            .category(GameRuleCategory.MISC)
            .buildAndRegister(Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "skyblock_radius"));

    public static final GameRule<Integer> BLOCKS_PER_TICK = GameRuleBuilder
            .forInteger(50000)
            .category(GameRuleCategory.MISC)
            .buildAndRegister(Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "terrain_manager_blocks_per_tick"));

    public static void init() {}
}
