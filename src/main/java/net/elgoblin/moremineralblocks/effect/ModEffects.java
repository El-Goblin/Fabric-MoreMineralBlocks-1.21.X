package net.elgoblin.moremineralblocks.effect;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
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

    public static final RegistryEntry<StatusEffect> BLINKING = registerStatusEffect("blinking",
            new BlinkingEffect(StatusEffectCategory.NEUTRAL, 0x00ff20));

//    public static final RegistryEntry<StatusEffect> CHAOS = registerStatusEffect("chaos",
//            new ChaosEffect(StatusEffectCategory.NEUTRAL, 0x00ff20));

//    public static final RegistryEntry<StatusEffect> ADVENTURE = registerStatusEffect("adventure",
//            new AdventureEffect(StatusEffectCategory.NEUTRAL, 0x00ff20));

    public static final RegistryEntry<StatusEffect> COUNTER_BLINK = registerStatusEffect("counter_blink", new OnDamageTakenEffect(StatusEffectCategory.HARMFUL, 12779366));
    public static final RegistryEntry<StatusEffect> FRAGILE = registerStatusEffect("fragile", new OnDamageTakenEffect(StatusEffectCategory.HARMFUL, 12771234));
//    public static final RegistryEntry<StatusEffect> ONANA_HANDS = registerStatusEffect("onana_hands", new OnDamageTakenEffect(StatusEffectCategory.HARMFUL, 12779366));
//    public static final RegistryEntry<StatusEffect> CUMULATIVE_DAMAGE_TAKEN = registerStatusEffect("cumulative_damage_taken", new OnDamageTakenEffect(StatusEffectCategory.HARMFUL, 12779366)
//            .addAttributeModifier(ModEntityAttributes.CUMULATIVE_DAMAGE_TAKEN, Identifier.of(MoreMineralBlocks.MOD_ID, "cumulative_damage_taken"), 0.5, EntityAttributeModifier.Operation.ADD_VALUE));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(MoreMineralBlocks.MOD_ID, name), statusEffect);
    }
    public static void registerEffects() {
        MoreMineralBlocks.LOGGER.info("Registering Mod Effects for " + MoreMineralBlocks.MOD_ID);
    }
}
