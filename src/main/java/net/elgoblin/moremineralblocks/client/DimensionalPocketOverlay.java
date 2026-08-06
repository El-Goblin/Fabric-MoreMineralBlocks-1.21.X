package net.elgoblin.moremineralblocks.client;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class DimensionalPocketOverlay {
    private static final Identifier HOTBAR_SELECTED_TEXTURE =
            Identifier.fromNamespaceAndPath("minecraft","textures/gui/sprites/hud/hotbar_selection.png");
    private static final Identifier HOTBAR_TEXTURE =
            Identifier.fromNamespaceAndPath("minecraft","textures/gui/sprites/hud/hotbar.png");
    public static final Identifier COLOREABLE_GROUP_TEXTURE = Identifier.fromNamespaceAndPath(
            MoreMineralBlocks.MOD_ID, "textures/item/dimensional_pocket/overlay/dimensional_pocket_coloreable_group_texture.png");

    public static void register() {
        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                Identifier.fromNamespaceAndPath(MoreMineralBlocks.MOD_ID, "infinite_item_overlay"),
                (guiGraphicsExtractor, deltaTracker) -> {

                    Minecraft client = Minecraft.getInstance();
                    Player player = client.player;
                    if (player == null) {
                        return;
                    }

                    boolean scrollBetweenGroupsDown = ModKeybinds.SCROLL_BETWEEN_GROUPS.isDown();
                    boolean scrollInsideGroupDown = ModKeybinds.SCROLL_INSIDE_GROUP.isDown();
                    if (!scrollBetweenGroupsDown && !scrollInsideGroupDown) { return; }

                    ItemStack mainHandItem = player.getMainHandItem();
                    ItemStack offHandItem = player.getOffhandItem();
                    ItemStack activeItem = ItemStack.EMPTY;

                    if (mainHandItem.is(ModItems.DIMENSIONAL_POCKET)) {
                        activeItem = mainHandItem;
                    } else if (offHandItem.is(ModItems.DIMENSIONAL_POCKET)) {
                        activeItem = offHandItem;
                    }

                    if (activeItem.isEmpty()) { return; }

                    int screenWidth = client.getWindow().getGuiScaledWidth();
                    int screenHeight = client.getWindow().getGuiScaledHeight();

                    int hotbarLeftX = (screenWidth / 2) - 90;
                    int selectedSlot = player.getInventory().getSelectedSlot();

                    int slotCenterX = hotbarLeftX + (selectedSlot * 20) - 2;
                    int slotHeight = 22;
                    int hotbarTopY = screenHeight - slotHeight;


                    if (activeItem == offHandItem) {
                        slotCenterX = (screenWidth / 2) - 121;
                    }

                    guiGraphicsExtractor.blit(RenderPipelines.GUI_TEXTURED, HOTBAR_TEXTURE,
                            slotCenterX + 2, hotbarTopY - slotHeight + 1,
                            1, 1,
                            20, 20,
                            182, 22
                    );

                    guiGraphicsExtractor.blit(
                            RenderPipelines.GUI_TEXTURED, HOTBAR_TEXTURE,
                            slotCenterX + 2, hotbarTopY - (slotHeight * 3) + 2,
                            1, 1,
                            20, 20,
                            182, 22
                    );

                    guiGraphicsExtractor.blit(
                            RenderPipelines.GUI_TEXTURED, HOTBAR_SELECTED_TEXTURE,
                            slotCenterX + 1, hotbarTopY - slotHeight * 2,
                            1, 1,
                            22, 23,
                            24, 24
                    );


                    int nextGroup = DimensionalPocketCache.nextGroupIndex;
                    drawColorForIndex(guiGraphicsExtractor, nextGroup, slotCenterX + 4, hotbarTopY - slotHeight * 3 + 4, activeItem);

                    int selectedColoredGroup = activeItem.getOrDefault(ModDataComponentTypes.SELECTED_COLORED_GROUP, 0);
                    drawColorForIndex(guiGraphicsExtractor, selectedColoredGroup, slotCenterX + 4, hotbarTopY - slotHeight * 2 + 3, activeItem);
                    drawColorForIndex(guiGraphicsExtractor, selectedColoredGroup, slotCenterX + 4, hotbarTopY - slotHeight * 2 + 4, activeItem);

                    int prevGroup = DimensionalPocketCache.prevGroupIndex;
                    drawColorForIndex(guiGraphicsExtractor, prevGroup, slotCenterX + 4, hotbarTopY - slotHeight + 3, activeItem);

                    drawItem(guiGraphicsExtractor, DimensionalPocketCache.nextGroupStack, slotCenterX + 4, hotbarTopY - slotHeight * 3 + 4);
                    drawItem(guiGraphicsExtractor, DimensionalPocketCache.prevGroupStack, slotCenterX + 4, hotbarTopY - slotHeight + 3);

                    if (scrollInsideGroupDown) {
                        guiGraphicsExtractor.blit(RenderPipelines.GUI_TEXTURED, HOTBAR_TEXTURE,
                                slotCenterX - 19, hotbarTopY - slotHeight * 2 + 1,
                                1, 1,
                                20, 20,
                                182, 22);

                        guiGraphicsExtractor.blit(RenderPipelines.GUI_TEXTURED, HOTBAR_TEXTURE,
                                slotCenterX + 23, hotbarTopY - slotHeight * 2 + 1,
                                1, 1,
                                20, 20,
                                182, 22);

                        drawColorForIndex(guiGraphicsExtractor, selectedColoredGroup, slotCenterX - 17, hotbarTopY - slotHeight * 2 + 3, activeItem);
                        drawColorForIndex(guiGraphicsExtractor, selectedColoredGroup, slotCenterX + 25, hotbarTopY - slotHeight * 2 + 3, activeItem);

                        drawItem(guiGraphicsExtractor, DimensionalPocketCache.sameGroupPrevStack, slotCenterX - 17, hotbarTopY - slotHeight * 2 + 3);
                        drawItem(guiGraphicsExtractor, DimensionalPocketCache.sameGroupNextStack, slotCenterX + 25, hotbarTopY - slotHeight * 2 + 3);
                    }

                    drawItem(guiGraphicsExtractor, DimensionalPocketCache.mainStack, slotCenterX + 4, hotbarTopY - slotHeight * 2 + 4);
                }
        );
    }

    private static void drawColorForIndex(GuiGraphicsExtractor guiGraphicsExtractor, int colorIndex, int x, int y, ItemStack originalItem) {
        guiGraphicsExtractor.blit(RenderPipelines.GUI_TEXTURED, COLOREABLE_GROUP_TEXTURE,
                x, y,
                0, 0,
                16, 16,
                16, 16,
                DimensionalPocketColorSelectionPanel.COLORS[colorIndex]);
    }

    private static void drawItem(GuiGraphicsExtractor guiGraphicsExtractor, ItemStack stack, int x, int y) {
        if (stack != null && !stack.isEmpty()) {
            guiGraphicsExtractor.item(stack, x, y);
        }
    }
}
