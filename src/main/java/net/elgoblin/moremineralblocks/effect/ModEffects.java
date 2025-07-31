package net.elgoblin.moremineralblocks.effect;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.InstantStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {

    public static final RegistryEntry<StatusEffect> SLIMEY = registerStatusEffect("slimey",
            new SlimeyEffect(StatusEffectCategory.NEUTRAL, 0x00ff20)
                    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                            Identifier.of(MoreMineralBlocks.MOD_ID, "slimey"), -0.25f,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final RegistryEntry<StatusEffect> CHAOS = registerStatusEffect("chaos",
            new ChaosEffect(StatusEffectCategory.NEUTRAL, 0x00ff20));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(MoreMineralBlocks.MOD_ID, name), statusEffect);
    }
    public static void registerEffects() {
        MoreMineralBlocks.LOGGER.info("Registering Mod Effects for " + MoreMineralBlocks.MOD_ID);
    }
}
