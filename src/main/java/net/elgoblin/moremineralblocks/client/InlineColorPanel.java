package net.elgoblin.moremineralblocks.client;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.networking.InfiniteItemSelectColorPayload;
import net.elgoblin.moremineralblocks.networking.ToggleSlotPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.DyeColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InlineColorPanel {
    public int x, y;
    public final int targetSlotId;
    public boolean active = true;

    private int selectedColorIndex = 0;

    private static final int[] COLORS = {
            0xFFFFFFFF, 0xFFD87F33, 0xFFB24CD8, 0xFF6699D8,
            0xFFE5E533, 0xFF7FCC19, 0xFFF27FA5, 0xFF4C4C4C,
            0xFF999999, 0xFF4C7F99, 0xFF7F3FB2, 0xFF334CB2,
            0xFF664C33, 0xFF667F33, 0xFF993333, 0xFF191919
    };

    public InlineColorPanel(int x, int y, int targetSlotId, int currentActiveColor) {
        this.x = x;
        this.y = y;
        this.targetSlotId = targetSlotId;
        this.selectedColorIndex = currentActiveColor;
    }

    public void render(DrawContext context, int mouseX, int mouseY) {
        if (!active) return;

        // Cuadrado fondo atras de los cuadraditos con los colores
        context.fill(this.x - 4, this.y - 4, this.x + (4 * 18) + 2, this.y + (4 * 18) + 2, 0xEE101010);

        int index = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                int cx = this.x + (col * 18);
                int cy = this.y + (row * 18);
                int color = COLORS[index];

                // Cuadrado blanco que indica que color estas hovereando con el mouse
                if (mouseX >= cx && mouseX < cx + 16 && mouseY >= cy && mouseY < cy + 16) {
                    context.fill(cx - 1, cy - 1, cx + 17, cy + 17, 0xFFFFFFFF);
                }

                // Dibujar los cuadrados de cada color en el panel
                context.fill(cx, cy, cx + 16, cy + 16, color);

                // Cuadrado blanco de seleccion de color
                if (index == selectedColorIndex) {
                    context.fill(cx - 1, cy - 1, cx + 17, cy, 0xFFFFFFFF); // Top line
                    context.fill(cx - 1, cy + 16, cx + 17, cy + 17, 0xFFFFFFFF); // Bottom line
                    context.fill(cx - 1, cy, cx, cy + 16, 0xFFFFFFFF); // Left line
                    context.fill(cx + 16, cy, cx + 17, cy + 16, 0xFFFFFFFF); // Right line
                }
                index++;
            }
        }
    }

    public boolean checkClick(double mouseX, double mouseY) {
        if (!active) return false;

        int totalWidth = 4 * 18;
        int totalHeight = 4 * 18;

        // Click adentro del panel?
        if (mouseX >= this.x && mouseX < this.x + totalWidth && mouseY >= this.y && mouseY < this.y + totalHeight) {
            int index = 0;
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    int cx = this.x + (col * 18);
                    int cy = this.y + (row * 18);

                    if (mouseX >= cx && mouseX < cx + 16 && mouseY >= cy && mouseY < cy + 16) {
                        this.selectedColorIndex = index;
                        ClientPlayNetworking.send(new InfiniteItemSelectColorPayload(index, this.targetSlotId));
                        return true;
                    }
                    index++;
                }
            }
            return true;
        }
        return false;
    }

    public void toggleSlotAssignment(int slotId) {
        if (selectedColorIndex == -1) return;

        DyeColor dyeColor = DyeColor.byIndex(selectedColorIndex);
        // Como quiero que esto sea generico para todos los colores, no puedo hacer get despues con un color en especifico
        ComponentType<List<Integer>> colorInventory = ModDataComponentTypes.COLOR_INVENTORIES.get(dyeColor);

        if (colorInventory != null) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.currentScreen instanceof HandledScreen<?> handledScreen) {

                // Para poder acceder las componentes, consigo el ItemStack del InfiniteItem
                Slot slot = handledScreen.getScreenHandler().getSlot(this.targetSlotId);

                if (slot != null && slot.hasStack()) {
                    ItemStack stack = slot.getStack();
                    if (stack.isOf(ModItems.DIMENSION_POCKET)) {
                        List<Integer> existingSlots = stack.getOrDefault(colorInventory, Collections.emptyList());
                        List<Integer> updatedSlots = new ArrayList<>(existingSlots);

                        if (updatedSlots.contains(slotId)) {
                            updatedSlots.remove(Integer.valueOf(slotId));
                        } else {
                            updatedSlots.add(slotId);
                        }
                        stack.set(colorInventory, updatedSlots);
                        slot.setStackNoCallbacks(stack);
                    }
                }
            }
        }
        ClientPlayNetworking.send(new ToggleSlotPayload(this.targetSlotId, slotId, selectedColorIndex));
    }

    public int getColorHex(int index) {
        if (index < 0 || index >= COLORS.length) return 0xFFFFFFFF;
        return COLORS[index];
    }

    public int getSelectedColorIndex() {
        return this.selectedColorIndex;
    }

    public boolean isSlotAssignedToColor(int slotId, int colorIndex) {
        DyeColor dyeColor = DyeColor.byIndex(colorIndex);
        ComponentType<List<Integer>> component = ModDataComponentTypes.COLOR_INVENTORIES.get(dyeColor);

        if (component != null) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.currentScreen instanceof HandledScreen<?> handledScreen) {
                Slot slot = handledScreen.getScreenHandler().getSlot(this.targetSlotId);
                if (slot != null && slot.hasStack()) {
                    ItemStack itemStack = slot.getStack();
                    if (itemStack.isOf(ModItems.DIMENSION_POCKET)) {
                        List<Integer> slots = slot.getStack().getOrDefault(component, java.util.Collections.emptyList());
                        return slots.contains(slotId);
                    }
                }
            }
        }
        return false;
    }
}