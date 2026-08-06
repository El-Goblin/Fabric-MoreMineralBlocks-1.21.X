package net.elgoblin.moremineralblocks.client.renderer;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.resources.Identifier;

public class ModSpecialModelRenderers {

    public static final Identifier DIMENSIONAL_POCKET =
            Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID, "dimensional_pocket");

    public static void register() {
        SpecialModelRenderers.ID_MAPPER.put(
                DIMENSIONAL_POCKET,
                DimensionalPocketRenderer.Unbaked.MAP_CODEC
        );
    }
}
