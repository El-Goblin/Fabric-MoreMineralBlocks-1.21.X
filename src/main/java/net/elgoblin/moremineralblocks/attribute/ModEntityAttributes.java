package net.elgoblin.moremineralblocks.attribute;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModEntityAttributes {

    public static final RegistryEntry<EntityAttribute> CUMULATIVE_DAMAGE_TAKEN = registerEntityAttribute(
            "player.cumulative_damage_taken", new ClampedEntityAttribute("attribute.name.player.cumulative_damage_taken", 0.0, 0.0, 9999999.0)
    );

    private static RegistryEntry<EntityAttribute> registerEntityAttribute(String name, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, Identifier.of(MoreMineralBlocks.MOD_ID, name), attribute);
    }

    public static void registerEntityAttributes() {
        MoreMineralBlocks.LOGGER.info("Registering Entity Attributes for " + MoreMineralBlocks.MOD_ID);
    }
}
