package net.elgoblin.moremineralblocks.mixin;

import net.elgoblin.moremineralblocks.client.DimensionalPocketColorSelectionPanel;
import net.elgoblin.moremineralblocks.client.ModKeybinds;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;

import static net.elgoblin.moremineralblocks.client.DimensionalPocketOverlay.COLOREABLE_GROUP_TEXTURE;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMixin {

    @Shadow
    protected Slot hoveredSlot;
    @Shadow protected int leftPos;
    @Shadow protected int topPos;
    @Shadow protected int imageWidth;
    @Shadow protected int imageHeight;

    @Unique
    protected final Set<Slot> selectedSlots = new HashSet<>();
    @Unique
    private DimensionalPocketColorSelectionPanel activeColorPanel = null;

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void handleOverlayClicks(MouseButtonEvent event, boolean doubleClick, CallbackInfoReturnable<Boolean> cir) {

        if (activeColorPanel != null && activeColorPanel.active) {

            if (slotIsDimensionalPocketLinkedToThisChest(event)) {
                activeColorPanel.active = false;
                cir.setReturnValue(true);
                cir.cancel();
                return;
            }

            // Al elegir un color, evito que el juego me cierre el cofre por haber clickeado afuera
            if (activeColorPanel.selectColorIfClickedInsidePanel(event.x(), event.y())) {
                selectedSlots.clear();
                cir.setReturnValue(true);
                cir.cancel();
                return;
            }

            // Al hacer click en un slot de un cofre, agregarlo al grupo y pintarlo
            if (event.button() == 0 && this.hoveredSlot != null) {
                AbstractContainerMenu menu = ((AbstractContainerScreen<?>) (Object)this).getMenu();

                if (isContainerSlot(menu, this.hoveredSlot)) {
                    int clickedSlotId = this.hoveredSlot.index;
                    this.activeColorPanel.toggleSlotAssigned(clickedSlotId);
                    selectedSlots.add(this.hoveredSlot);

                    cir.setReturnValue(true);
                    cir.cancel();
                    return;
                }
            }
        }

        if (!slotIsDimensionalPocketLinkedToThisChest(event)) { return; }

        int panelX = this.leftPos + this.imageWidth + 10;
        int panelY = (int) (this.topPos + this.imageHeight * 0.66);
        int currentItemColor = this.hoveredSlot.getItem()
                .getOrDefault(ModDataComponentTypes.SELECTED_COLORED_GROUP, 0);


        this.activeColorPanel = new DimensionalPocketColorSelectionPanel(
                panelX, panelY, this.hoveredSlot.index, currentItemColor);

        cir.setReturnValue(true);
        cir.cancel();
    }

    @Inject(
            method = "mouseDragged",
            at = @At("HEAD")
    )
    private void onMouseDragged(MouseButtonEvent event, double dx, double dy, CallbackInfoReturnable<Boolean> cir) {
        if (event.button() == 0) {
            if (this.hoveredSlot != null && activeColorPanel != null && activeColorPanel.active) {
                if (!selectedSlots.contains(this.hoveredSlot)) {
                    selectedSlots.add(this.hoveredSlot);
                    activeColorPanel.toggleSlotAssigned(this.hoveredSlot.index);
                }
            }
        }
    }

    @Inject(
            method = "mouseReleased",
            at = @At("HEAD")
    )
    private void onMouseReleased(MouseButtonEvent event, CallbackInfoReturnable<Boolean> cir) {
        selectedSlots.clear();
    }


    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void drawDimensionalPocketColorSelectionPanel(
            GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
        if (activeColorPanel != null) {
            activeColorPanel.drawColorSelectionPanel(graphics, mouseX, mouseY);
        }
    }



    @Inject(method = "extractSlot", at = @At("HEAD"))
    private void tintToggledSlot(GuiGraphicsExtractor graphics, Slot slot, int x, int y, CallbackInfo ci) {

        if (activeColorPanel != null) {
            AbstractContainerMenu menu = ((AbstractContainerScreen<?>) (Object)this).getMenu();

            if (isContainerSlot(menu, slot)) {
                int selectedColorIndex = activeColorPanel.getSelectedColor();

                if (activeColorPanel.isSlotAssignedToColor(slot.index, selectedColorIndex)) {

                    int color = activeColorPanel.getColorHex(selectedColorIndex);

                    graphics.blit(RenderPipelines.GUI_TEXTURED, COLOREABLE_GROUP_TEXTURE,
                            slot.x, slot.y,
                            0, 0,
                            16, 16,
                            16, 16,
                            color);
                }
            }
        }
    }

    @Unique
    private boolean isContainerSlot(AbstractContainerMenu menu, Slot slot) {
        if (slot == null || slot.container instanceof Inventory) { return false; }

        return menu instanceof ShulkerBoxMenu || menu instanceof ChestMenu;
    }

    @Unique
    private boolean isTheLinkedContainer(Level level, BlockPos crosshairPos, BlockPos storagePosition) {
        BlockState state = level.getBlockState(crosshairPos);
        if (state.getBlock() instanceof ChestBlock chestBlock) {
            Container inventory = ChestBlock.getContainer(chestBlock, state, level, crosshairPos, true);

            if (inventory instanceof CompoundContainer doubleChest
                    && level.getBlockEntity(storagePosition) instanceof Container linkedInventory
                    && doubleChest.contains(linkedInventory)) {
                return true;
            }
        }
        return crosshairPos.equals(storagePosition);
    }

    @Unique
    private boolean slotIsDimensionalPocketLinkedToThisChest(MouseButtonEvent event) {
        if (!(event.button() == 0) || this.hoveredSlot == null || !this.hoveredSlot.hasItem()) { return false; }
        ItemStack stack = this.hoveredSlot.getItem();

        if (!stack.is(ModItems.DIMENSIONAL_POCKET)) { return false; }

        BlockPos storagePosition = stack.get(ModDataComponentTypes.LINKED_CHEST);
        Identifier dimension = stack.get(ModDataComponentTypes.SERVERWORLD);

        if (dimension == null || storagePosition == null) { return false; }

        AbstractContainerMenu menu = ((AbstractContainerScreen<?>) (Object) this).getMenu();
        if (!(menu instanceof ChestMenu) && !(menu instanceof ShulkerBoxMenu)) { return false; }

        Minecraft client = Minecraft.getInstance();
        if (!(client.hitResult instanceof BlockHitResult blockHitResult)) { return false; }

        BlockPos crosshairPos = blockHitResult.getBlockPos();
        Level level = client.level;

        if (level == null || !level.dimension().identifier().equals(dimension)) { return false; }
        return isTheLinkedContainer(level, crosshairPos, storagePosition);
    }
}