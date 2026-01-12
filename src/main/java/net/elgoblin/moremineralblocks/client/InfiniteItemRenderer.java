package net.elgoblin.moremineralblocks.client;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.item.custom.InfiniteItem;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

public class InfiniteItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {

    private static final Identifier EMPTY_TEXTURE = Identifier.of("moremineralblocks", "textures/item/infinite_itemstack.png");

    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemStack storedStack = stack.get(ModDataComponentTypes.CHOSEN_INFINITE_ITEM);

        // If valid, use standard item rendering
        if (storedStack != null && !storedStack.isEmpty() && !(storedStack.getItem() instanceof InfiniteItem)) {
            renderStoredItem(storedStack, mode, matrices, vertexConsumers, light, overlay);
            return;
        }

        // FALLBACK: Manual Texture Rendering
        matrices.push();

        // 1. Center the texture in the block/slot space
        matrices.translate(0.5f, 0.5f, 0.5f);

        // 2. Make the texture face the camera (Billboard effect)
        // In GUI mode, it's already flat. In the world, we want it to look like a dropped item.
        if (mode != ModelTransformationMode.GUI) {
            matrices.multiply(MinecraftClient.getInstance().getEntityRenderDispatcher().getRotation());
        }

        // 3. Draw the quad
        VertexConsumer buffer = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(EMPTY_TEXTURE));
        Matrix4f matrix = matrices.peek().getPositionMatrix();

        // These coordinates create a 1x1 square centered at 0,0,0
        // Arguments: Matrix, X, Y, Z, Color (White), U (Texture X), V (Texture Y), Overlay, Light, Normal
        drawVertex(matrix, buffer, -0.5f, -0.5f, 0f, 0f, 1f, 0); // Bottom Left
        drawVertex(matrix, buffer, 0.5f, -0.5f, 0f, 1f, 1f, 0);  // Bottom Right
        drawVertex(matrix, buffer, 0.5f, 0.5f, 0f, 1f, 0f, 0);   // Top Right
        drawVertex(matrix, buffer, -0.5f, 0.5f, 0f, 0f, 0f, 0);  // Top Left

        matrices.pop();
    }

    // Helper to keep the code clean
    private void drawVertex(Matrix4f matrix, VertexConsumer buffer, float x, float y, float z, float u, float v, int light) {
        buffer.vertex(matrix, x, y, z)
                .color(0, 0, 0, 255)
                .texture(u, v)
                .overlay(0)
                .light(light)
                .normal(0f, 0f, 1f);
    }

    private void renderStoredItem(ItemStack storedStack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        var itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        var model = itemRenderer.getModels().getModel(storedStack);

        matrices.push();
        if (mode == ModelTransformationMode.GROUND) {
            float yOffset = storedStack.getItem() instanceof BlockItem ? 0.3f : 0.5f;
            matrices.translate(0.5, yOffset, 0.5);
        } else {
            matrices.translate(0.5f, 0.5f, 0.5f);
        }

        int renderLight = light;
        if (mode == ModelTransformationMode.GUI) {
            // 1. Boost the light level to full bright for the inventory
            renderLight = 0xF000F0;

            // 2. Set up the specific lighting for GUI rendering
            // This ensures front-lit items (flat) and side-lit items (blocks)
            // both get their expected diffuse lighting.
            if (model.isSideLit()) {
                net.minecraft.client.render.DiffuseLighting.enableGuiDepthLighting();
            } else {
                net.minecraft.client.render.DiffuseLighting.disableGuiDepthLighting();
            }
        }

        itemRenderer.renderItem(storedStack, mode, renderLight, overlay, matrices, vertexConsumers, MinecraftClient.getInstance().world, 0);

        if (mode == ModelTransformationMode.GUI) {
            net.minecraft.client.render.DiffuseLighting.enableGuiDepthLighting();
        }

        matrices.pop();
    }
}
