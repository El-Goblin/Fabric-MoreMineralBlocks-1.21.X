package net.elgoblin.umamium.client;

import net.elgoblin.umamium.component.ModDataComponentTypes;
import net.elgoblin.umamium.item.ModItems;
import net.elgoblin.umamium.networking.dimensionalpocket.DimensionalPocketSelectColorPayload;
import net.elgoblin.umamium.networking.dimensionalpocket.DimensionalPocketToggleSlotPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.elgoblin.umamium.client.DimensionalPocketOverlay.COLOREABLE_GROUP_TEXTURE;

public class DimensionalPocketColorSelectionPanel {

    public int x, y;
    public final int dimensionalPocketSlotId;
    public boolean active = true;

    private int selectedColor = 0;

    public static final int[] COLORS = {
            0xFFDA2403,
            0xFFFF6C16,
            DyeColor.YELLOW.getTextureDiffuseColor(),
            DyeColor.LIME.getTextureDiffuseColor(),
            DyeColor.GREEN.getTextureDiffuseColor(),
            0xFF009C7D,
            DyeColor.LIGHT_BLUE.getTextureDiffuseColor(),
            DyeColor.BLUE.getTextureDiffuseColor(),
            DyeColor.PURPLE.getTextureDiffuseColor(),
            DyeColor.MAGENTA.getTextureDiffuseColor(),
            DyeColor.PINK.getTextureDiffuseColor(),
            DyeColor.BROWN.getTextureDiffuseColor(),
            0xFFFFFFFF,
            0xFFCCCCCC, // LIGHT GRAY
            DyeColor.GRAY.getTextureDiffuseColor(),
            DyeColor.BLACK.getTextureDiffuseColor(),
    };

    public DimensionalPocketColorSelectionPanel(int x, int y, int dimensionalPocketSlotId, int selectedColor) {
        this.x = x;
        this.y = y;
        this.dimensionalPocketSlotId = dimensionalPocketSlotId;
        this.selectedColor = selectedColor;
    }

    public void drawColorSelectionPanel(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        if (!active) return;

        // Generar el cuadrado negro, el marco
        graphics.fill(this.x - 4, this.y - 4, this.x + (4 * 18) + 2, this.y + (4 * 18) + 2,
                0xEE101010);

        int colorIndex = 0;

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                int cx = this.x + (col * 18);
                int cy = this.y + (row * 18);
                int color = COLORS[colorIndex];

                // Si estoy hovereando el color, le pinto un borde blanco
                if (mouseX >= cx && mouseX < cx + 16 && mouseY >= cy && mouseY < cy + 16) {
                    graphics.fill(cx - 1, cy - 1, cx + 17, cy + 17, 0xFFFFFFFF);
                }

                // Pintar el cuadrado de cada color
                graphics.blit(RenderPipelines.GUI_TEXTURED, COLOREABLE_GROUP_TEXTURE,
                        cx, cy,
                        0, 0,
                        16, 16,
                        16, 16,
                        color);

                // Si este color esta seleccionado actualmente, le pinto un borde blanco?
                if (colorIndex == selectedColor) {
                    graphics.fill(cx - 1, cy - 1, cx + 17, cy, 0xFFFFFFFF);
                    graphics.fill(cx - 1, cy + 16, cx + 17, cy + 17, 0xFFFFFFFF);
                    graphics.fill(cx - 1, cy, cx, cy + 16, 0xFFFFFFFF);
                    graphics.fill(cx + 16, cy, cx + 17, cy + 16, 0xFFFFFFFF);
                }

                colorIndex++;
            }
        }
    }

    public boolean selectColorIfClickedInsidePanel(double mouseX, double mouseY) {
        if (!active) return false;

        int totalWidth = 4 * 18;
        int totalHeight = 4 * 18;

        if (    mouseX >= this.x && mouseX < this.x + totalWidth &&
                mouseY >= this.y && mouseY < this.y + totalHeight) {

            int index = 0;

            // Optimizable
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    int cx = this.x + (col * 18);
                    int cy = this.y + (row * 18);

                    if (mouseX >= cx && mouseX < cx + 16 && mouseY >= cy && mouseY < cy + 16) {
                        this.selectedColor = index;
                        ClientPlayNetworking.send(
                                new DimensionalPocketSelectColorPayload(index, this.dimensionalPocketSlotId)
                        );
                        return true;
                    }
                    index++;
                }
            }
            return true;
        }
        return false;
    }


    public void toggleSlotAssigned(int slotId) {
        ClientPlayNetworking.send(new DimensionalPocketToggleSlotPayload(this.dimensionalPocketSlotId, slotId, selectedColor));
    }

    public int getSelectedColor() {
        return this.selectedColor;
    }

    public boolean isSlotAssignedToColor(int slotId, int colorIndex) {
        DyeColor dyeColor = DyeColor.byId(colorIndex);
        DataComponentType<List<Integer>> coloredGroup = ModDataComponentTypes.COLOR_INVENTORIES.get(dyeColor);

        if (coloredGroup != null) {
            Minecraft client = Minecraft.getInstance();

            if (client.gui.screen() instanceof AbstractContainerScreen<?> screen) {
                Slot slot = screen.getMenu().getSlot(this.dimensionalPocketSlotId);

                if (slot.hasItem()) {
                    ItemStack itemStack = slot.getItem();

                    if (itemStack.is(ModItems.DIMENSIONAL_POCKET)) {
                        List<Integer> slots = itemStack.getOrDefault(coloredGroup, Collections.emptyList());
                        return slots.contains(slotId);
                    }
                }
            }
        }
        return false;
    }

    public int getColorHex(int index) {
        if (index < 0 || index >= COLORS.length) {
            return 0xFFFFFFFF;
        }
        return COLORS[index];
    }
}
