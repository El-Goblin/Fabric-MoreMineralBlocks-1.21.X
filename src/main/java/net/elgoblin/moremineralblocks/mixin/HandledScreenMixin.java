package net.elgoblin.moremineralblocks.mixin;

import net.elgoblin.moremineralblocks.client.InlineColorPanel;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.inventory.DoubleInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ShulkerBoxScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Comparator;
import java.util.List;


@Mixin(HandledScreen.class)
public class HandledScreenMixin {

    @Shadow protected Slot focusedSlot;
    @Shadow protected int x;
    @Shadow protected int y;
    @Shadow protected int backgroundWidth;
    @Shadow protected int backgroundHeight;

    @Unique
    private InlineColorPanel activeColorPanel = null;
    @Unique
    private ItemStack infiniteItemStack;

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void handleOverlayClicks(Click click, boolean doubled, CallbackInfoReturnable<Boolean> cir) {

        if (activeColorPanel != null && activeColorPanel.active) {

            // Click en el panel, lo mantengo como esta
            if (activeColorPanel.checkClick(click.x(), click.y())) {
                cir.setReturnValue(true);
                cir.cancel();
                return;
            }

            // Click en slot de inventario con el panel abierto.
            if (click.button() == 0 && this.focusedSlot != null) {
                ScreenHandler handler = ((HandledScreen<?>) (Object) this).getScreenHandler();

                if (isContainerSlot(handler, this.focusedSlot)) {
                    int clickedSlotId = this.focusedSlot.id;

                    this.activeColorPanel.toggleSlotAssignment(clickedSlotId);

                    cir.setReturnValue(true);
                    cir.cancel();
                    return;
                }
            }
        }

        if (click.button() == 0 && this.focusedSlot != null && this.focusedSlot.hasStack()) {
            if (this.focusedSlot.getStack().isOf(ModItems.DIMENSIONAL_POCKET)) {

                ItemStack infiniteItem = this.focusedSlot.getStack();

                infiniteItemStack = infiniteItem;

                BlockPos storagePosition = infiniteItem.get(ModDataComponentTypes.LINKED_CHEST);
                Identifier dimension = infiniteItem.get(ModDataComponentTypes.SERVERWORLD);

                if (dimension != null && storagePosition != null) {
                    ScreenHandler handler = ((HandledScreen<?>) (Object) this).getScreenHandler();

                    if (handler instanceof GenericContainerScreenHandler || handler instanceof ShulkerBoxScreenHandler) {

                        MinecraftClient client = MinecraftClient.getInstance();

                        if (client.crosshairTarget instanceof BlockHitResult blockHitResult) {
                            BlockPos crosshairPos = blockHitResult.getBlockPos();
                            World world = client.world;

                            if (world != null && world.getRegistryKey().getValue().equals(dimension)) {
                                BlockState blockState = world.getBlockState(crosshairPos);

                                if (blockState.getBlock() instanceof ChestBlock chestBlock) {
                                    Inventory clickedInventory = ChestBlock.getInventory(chestBlock, blockState, world, crosshairPos, true);
                                    if (clickedInventory instanceof DoubleInventory doubleChest) {
                                        if (world.getBlockEntity(storagePosition) instanceof Inventory linkedInventory) {
                                            if (!doubleChest.isPart(linkedInventory)) {return;} //Es un cofre doble no vinculado
                                        }
                                    }
                                    else {
                                        if (!crosshairPos.equals(storagePosition)) {return;} // Es un cofre simple no vinculado
                                    }
                                }
                                else {
                                    if (!crosshairPos.equals(storagePosition)) {return;} // Es algo no vinculado
                                }
                                int panelX = this.x + this.backgroundWidth + 10;
                                int panelY = (int) (this.y + this.backgroundHeight * 0.66);

                                int currentItemColor = this.focusedSlot.getStack().getOrDefault(ModDataComponentTypes.SELECTED_COLOR, 0);

                                this.activeColorPanel = new InlineColorPanel(panelX, panelY, this.focusedSlot.id, currentItemColor);

                                cir.setReturnValue(true);
                                cir.cancel();
                            }
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void renderInlineOverlay(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (activeColorPanel != null && activeColorPanel.active) {
            activeColorPanel.render(context, mouseX, mouseY);
        }
    }

    @Inject(method = "drawSlot", at = @At("HEAD"))
    private void drawAssignedSlotColor(DrawContext context, Slot slot, int x, int y, CallbackInfo ci) {
        if (this.activeColorPanel != null && this.activeColorPanel.active) {
            ScreenHandler handler = ((HandledScreen<?>) (Object) this).getScreenHandler();

            if (isContainerSlot(handler, slot)) {
                int selectedColorIndex = this.activeColorPanel.getSelectedColorIndex();

                if (this.activeColorPanel.isSlotAssignedToColor(slot.id, selectedColorIndex)) {
                    int rawColor = this.activeColorPanel.getColorHex(selectedColorIndex);

                    int tintColor = (rawColor & 0x00FFFFFF) | 0xAA000000;
                    if (selectedColorIndex == 8) {tintColor = 0x80FFFFFF;}
                    context.fill(slot.x, slot.y, slot.x + 16, slot.y + 16, tintColor);
                }
            }
        }
    }

    @Inject(method = "removed", at = @At("HEAD"))
    private void onScreenClose(CallbackInfo ci) {
        if (this.activeColorPanel != null && this.activeColorPanel.active) {
            for (int i = 0 ; i < 16 ; i++) {
                List<Integer> group = infiniteItemStack.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(i)));
                if (group != null) {group.sort(Comparator.naturalOrder());}
            }
        }
    }

    @Unique
    private boolean isContainerSlot(ScreenHandler handler, Slot slot) {
        if (slot == null || slot.inventory == null) return false;

        if (slot.inventory instanceof net.minecraft.entity.player.PlayerInventory) {
            return false;
        }

        return handler instanceof ShulkerBoxScreenHandler || handler instanceof GenericContainerScreenHandler;
    }
}