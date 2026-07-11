package net.elgoblin.moremineralblocks.client;

import net.elgoblin.moremineralblocks.MoreMineralBlocksClient;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.List;

public class InfiniteItemstackOverlay {
    private static final Identifier HOTBAR_SELECTED_TEXTURE =
            Identifier.ofVanilla("textures/gui/sprites/hud/hotbar_selection.png");
    private static final Identifier HOTBAR_TEXTURE =
            Identifier.ofVanilla("textures/gui/sprites/hud/hotbar.png");

    public static void register() {
        HudRenderCallback.EVENT.register((drawContext, renderTickCounter) -> {

            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null || client.options.hudHidden) return;

            boolean interGroupScrollPressed = MoreMineralBlocksClient.interGroupScroll.isPressed();
            boolean intraGroupScrollPressed = MoreMineralBlocksClient.intraGroupScroll.isPressed();
            if (!intraGroupScrollPressed && !interGroupScrollPressed) {
                return;
            }

            PlayerEntity player = client.player;
            ItemStack mainHand = player.getMainHandStack();
            ItemStack offHand = player.getOffHandStack();
            ItemStack activeItem = ItemStack.EMPTY;

            if (mainHand.isOf(ModItems.DIMENSION_POCKET)) {
                activeItem = mainHand;
            } else if (offHand.isOf(ModItems.DIMENSION_POCKET)) {
                activeItem = offHand;
            }

            if (activeItem.isEmpty()) return;

            Integer currentGroup = activeItem.get(ModDataComponentTypes.INTER_GROUP_POINTER);
            if (currentGroup == null) {
                currentGroup = 0;
            }

            int previousGroup = findNextGroup(activeItem, currentGroup, -1);
            int nextGroup = findNextGroup(activeItem, currentGroup, 1);

            int screenWidth = client.getWindow().getScaledWidth();
            int screenHeight = client.getWindow().getScaledHeight();

            int hotbarLeftX = screenWidth / 2 - 90;
            int selectedSlot = player.getInventory().getSelectedSlot();

            int slotCenterX = hotbarLeftX + (selectedSlot * 20) - 2;

            int hotbarTopY = screenHeight - 22;
            int slotHeight = 22;

            if (activeItem == offHand) {
                slotCenterX = (screenWidth / 2) - 90 - 29 -2;
            }

            drawContext.drawTexture(
                    RenderPipelines.GUI_TEXTURED,
                    HOTBAR_TEXTURE,
                    slotCenterX+2,
                    hotbarTopY - slotHeight + 1,
                    1,
                    1,
                    20,
                    20,
                    182,
                    22
            );
            drawContext.drawTexture(
                    RenderPipelines.GUI_TEXTURED,
                    HOTBAR_TEXTURE,
                    slotCenterX+2,
                    hotbarTopY - (slotHeight * 3) + 2,
                    1,
                    1,
                    20,
                    20,
                    182,
                    22
            );
            drawContext.drawTexture(
                    RenderPipelines.GUI_TEXTURED,
                    HOTBAR_SELECTED_TEXTURE,
                    slotCenterX + 1,
                    hotbarTopY - slotHeight * 2,
                    1,
                    1,
                    22,
                    23,
                    24,
                    24
            );

            drawColorIcon(drawContext, nextGroup, slotCenterX + 4, hotbarTopY - (slotHeight * 3) + 4, activeItem);

            // Duplicado para arreglar lo del 1 pixel vacio
            drawColorIcon(drawContext, currentGroup, slotCenterX + 4, hotbarTopY - (slotHeight * 2) + 3, activeItem);
            drawColorIcon(drawContext, currentGroup, slotCenterX + 4, hotbarTopY - (slotHeight * 2) + 4, activeItem);

            drawColorIcon(drawContext, previousGroup, slotCenterX + 4, hotbarTopY - slotHeight + 3, activeItem);

            renderSafeCachedStack(drawContext, InfiniteItemClientCache.nextGroupStack, slotCenterX + 4, hotbarTopY - (slotHeight * 3) + 4);
            renderSafeCachedStack(drawContext, InfiniteItemClientCache.prevGroupStack, slotCenterX + 4, hotbarTopY - slotHeight + 3);

            if (intraGroupScrollPressed) {
                drawContext.drawTexture(RenderPipelines.GUI_TEXTURED, HOTBAR_TEXTURE, slotCenterX - 20 + 1, hotbarTopY - (slotHeight * 2) + 1, 1, 1, 20, 20, 182, 22);
                drawContext.drawTexture(RenderPipelines.GUI_TEXTURED, HOTBAR_TEXTURE, slotCenterX + 22 + 1, hotbarTopY - (slotHeight * 2) + 1, 1, 1, 20, 20, 182, 22);

                drawColorIcon(drawContext, currentGroup, slotCenterX -20 + 3, hotbarTopY - (slotHeight * 2) + 3, activeItem);
                drawColorIcon(drawContext, currentGroup, slotCenterX +22 + 3, hotbarTopY - (slotHeight * 2) + 3, activeItem);

                renderSafeCachedStack(drawContext, InfiniteItemClientCache.sameGroupPrevStack, slotCenterX - 20 + 3, hotbarTopY - (slotHeight * 2) + 3);
                renderSafeCachedStack(drawContext, InfiniteItemClientCache.sameGroupNextStack, slotCenterX + 22 + 3, hotbarTopY - (slotHeight * 2) + 3);
            }

            renderSafeCachedStack(drawContext, InfiniteItemClientCache.mainRenderedStack, slotCenterX + 4, hotbarTopY - (slotHeight * 2) + 4);
        });
    }

    // Creamos una copia del itemStack y le cambiamos el interGroupPointer a ese stack para que se renderize distinto.
    private static void drawColorIcon(DrawContext context, int colorIndex, int x, int y, ItemStack originalItem) {
        ItemStack iconStack = originalItem.copy();
        iconStack.set(ModDataComponentTypes.INTER_GROUP_POINTER, colorIndex);
        context.drawItem(iconStack, x, y);
    }

    static int findNextGroup(ItemStack infiniteItemstack, int currentGroup, int direction) {
        int newGroup = currentGroup + direction;
        if (newGroup >= 16) {newGroup = 0;}
        if (newGroup < 0) {newGroup = 15;}
        List<Integer> group = infiniteItemstack.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(newGroup)));
        while (group != null && group.isEmpty() && newGroup != currentGroup) {
            newGroup = newGroup + direction;
            if (newGroup >= 16) {newGroup = 0;}
            if (newGroup < 0) {newGroup = 15;}
            group = infiniteItemstack.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(newGroup)));
        }

        return newGroup;
    }

    private static void renderSafeCachedStack(DrawContext context, ItemStack cachedStack, int x, int y) {
        if (cachedStack != null && !cachedStack.isEmpty()) {
            context.drawItem(cachedStack, x, y);
        }
    }
}