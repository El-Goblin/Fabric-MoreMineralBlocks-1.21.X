package net.elgoblin.umamium.effect;

import net.elgoblin.umamium.Umamium;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModEffects {
    public static final Holder<MobEffect> BLINKING = registerStatusEffect("blinking",
            new BlinkingEffect(MobEffectCategory.NEUTRAL, 0x00ff20));

//    public static final Holder<MobEffect> ADVENTURE = registerStatusEffect("adventure",
//            new AdventureEffect(MobEffectCategory.NEUTRAL, 0x00ff20));

    public static final Holder<MobEffect> COUNTER_BLINK = registerStatusEffect("counter_blink",
            new OnDamageTakenEffect(MobEffectCategory.HARMFUL, 12779366));

    public static final Holder<MobEffect> FRAGILE = registerStatusEffect("fragile",
            new OnDamageTakenEffect(MobEffectCategory.HARMFUL, 12771234));

    public static final Holder<MobEffect> SNOWY_BODYGUARDS = registerStatusEffect("snowy_bodyguards",
            new OnDamageTakenEffect(MobEffectCategory.HARMFUL, 0xFFFFFFFF));

    public static final Holder<MobEffect> ADYACENT_BLOCK_PLACING = registerStatusEffect("adyacent_block_placing",
            new RemovedByLaLechonaEffect(MobEffectCategory.HARMFUL, 0xFFFF0090));

//    public static final Holder<MobEffect> ONANA_HANDS = registerStatusEffect("onana_hands",
//            new OnDamageTakenEffect(MobEffectCategory.HARMFUL, 12779366));

//    public static final Holder<MobEffect> CUMULATIVE_DAMAGE_TAKEN = registerStatusEffect("cumulative_damage_taken",
//            new OnDamageTakenEffect(MobEffectCategory.HARMFUL, 12779366)
//                    .addAttributeModifier(ModEntityAttributes.CUMULATIVE_DAMAGE_TAKEN, ResourceLocation.fromNamespaceAndPath(Umamium.MOD_ID, "cumulative_damage_taken"), 0.5, AttributeModifier.Operation.ADD_VALUE));

    private static Holder<MobEffect> registerStatusEffect(String name, MobEffect statusEffect) {
        return Registry.registerForHolder(
                BuiltInRegistries.MOB_EFFECT,
                Identifier.fromNamespaceAndPath(Umamium.MOD_ID, name),
                statusEffect
        );
    }

    public static void registerEffects() {
        Umamium.LOGGER.info("Registering Mod Effects for " + Umamium.MOD_ID);
    }
}