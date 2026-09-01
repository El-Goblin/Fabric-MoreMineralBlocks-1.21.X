package net.elgoblin.umamium.client.models;

import net.elgoblin.umamium.Umamium;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class ModModelLayers {

    public static final ModelLayerLocation DIMENSIONAL_POCKET_OPEN =
            new ModelLayerLocation(
                    Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "dimensional_pocket_open"
                    ),
                    "main"
            );

    public static final ModelLayerLocation DIMENSIONAL_POCKET_CLOSED =
            new ModelLayerLocation(
                    Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "dimensional_pocket_closed"
                    ),
                    "main"
            );

    public static void register() {
        ModelLayerRegistry.registerModelLayer(
                DIMENSIONAL_POCKET_OPEN,
                DimensionalPocketOpenModel::createBodyLayer
        );
        ModelLayerRegistry.registerModelLayer(
                DIMENSIONAL_POCKET_CLOSED,
                DimensionalPocketClosedModel::createBodyLayer
        );
    }
}