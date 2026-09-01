package net.elgoblin.umamium.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.MapCodec;
import net.elgoblin.umamium.Umamium;
import net.elgoblin.umamium.client.DimensionalPocketCache;
import net.elgoblin.umamium.client.models.DimensionalPocketClosedModel;
import net.elgoblin.umamium.client.models.DimensionalPocketOpenModel;
import net.elgoblin.umamium.client.models.ModModelLayers;
import net.elgoblin.umamium.component.ModDataComponentTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class DimensionalPocketRenderer implements SpecialModelRenderer<DimensionalPocketRenderer.Data> {

    private final DimensionalPocketOpenModel openModel;
    private final DimensionalPocketClosedModel closedModel;

    private static final Identifier[] TEXTURES_BY_COLOR = {
            Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "textures/item/dimensional_pocket/open/dimensional_pocket_open_red.png"),
            Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "textures/item/dimensional_pocket/open/dimensional_pocket_open_orange.png"),
            Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "textures/item/dimensional_pocket/open/dimensional_pocket_open_yellow.png"),
            Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "textures/item/dimensional_pocket/open/dimensional_pocket_open_lime.png"),
            Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "textures/item/dimensional_pocket/open/dimensional_pocket_open_green.png"),
            Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "textures/item/dimensional_pocket/open/dimensional_pocket_open_cyan.png"),
            Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "textures/item/dimensional_pocket/open/dimensional_pocket_open_light_blue.png"),
            Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "textures/item/dimensional_pocket/open/dimensional_pocket_open_blue.png"),
            Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "textures/item/dimensional_pocket/open/dimensional_pocket_open_purple.png"),
            Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "textures/item/dimensional_pocket/open/dimensional_pocket_open_magenta.png"),
            Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "textures/item/dimensional_pocket/open/dimensional_pocket_open_pink.png"),
            Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "textures/item/dimensional_pocket/open/dimensional_pocket_open_brown.png"),
            Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "textures/item/dimensional_pocket/open/dimensional_pocket_open_white.png"),
            Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "textures/item/dimensional_pocket/open/dimensional_pocket_open_light_gray.png"),
            Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "textures/item/dimensional_pocket/open/dimensional_pocket_open_gray.png"),
            Identifier.fromNamespaceAndPath(Umamium.MOD_ID, "textures/item/dimensional_pocket/open/dimensional_pocket_open_black.png"),
    };

    public DimensionalPocketRenderer(DimensionalPocketOpenModel openModel, DimensionalPocketClosedModel closedModel) {
        this.openModel = openModel;
        this.closedModel = closedModel;
    }

    @Override
    public void submit(
            @Nullable Data argument,
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {

        poseStack.pushPose();

        poseStack.scale(1F, 1F, 1F);

        poseStack.translate(0, 1.7, 0);

        poseStack.mulPose(Axis.XN.rotationDegrees(155));
        poseStack.mulPose(Axis.YP.rotationDegrees(-55));
        poseStack.mulPose(Axis.ZP.rotationDegrees(35));

        ModelPart modelHips = openModel.getHips();
        if (argument != null && !argument.isBeingHeld()) {
            modelHips = closedModel.getHips();
            poseStack.translate(0, -0.15, 0);
            poseStack.scale(0.82F, 0.82F, 0.82F);
        }

        submitNodeCollector.submitModelPart(
                modelHips,
                poseStack,
                RenderTypes.entityCutout(TEXTURES_BY_COLOR[argument != null ? argument.color() : 0]),
                lightCoords,
                overlayCoords,
                null
        );

        poseStack.popPose();

        if (argument == null || argument.selectedStack().isEmpty()) { return; }
        ItemStack toDisplay = argument.selectedStack();

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) { return; }

        itemModelResolver.updateForNonLiving(
                selectedItemState,
                toDisplay,
                ItemDisplayContext.FIXED,
                minecraft.player
        );

        if (argument.isBeingHeld()) {
            poseStack.pushPose();

            poseStack.scale(0.85F, 0.85F, 0.85F);
            poseStack.translate(-0.50, 0.7, 0.130);

            poseStack.mulPose(Axis.XN.rotationDegrees(155));
            poseStack.mulPose(Axis.YP.rotationDegrees(-45));
            poseStack.mulPose(Axis.ZP.rotationDegrees(213));


            selectedItemState.submit(
                    poseStack,
                    submitNodeCollector,
                    lightCoords,
                    overlayCoords,
                    outlineColor
            );

            poseStack.popPose();
        }
    }

    @Override
    public Data extractArgument(ItemStack stack) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        boolean isBeingHeld = false;
        if (player != null) {
            ItemStack mainHand = player.getMainHandItem();
            ItemStack offHand = player.getOffhandItem();
            if (isSameDimensionalPocket(stack, mainHand) || isSameDimensionalPocket(stack, offHand)) {
                isBeingHeld = true;
            }
        }

        return new Data(
                DimensionalPocketCache.mainStack,
                stack.getOrDefault(ModDataComponentTypes.SELECTED_COLORED_GROUP, 0),
                isBeingHeld);
    }

    @Override
    public void getExtents(Consumer output) {

    }

    public record Data(ItemStack selectedStack, int color, boolean isBeingHeld) {}



    public static class Unbaked implements SpecialModelRenderer.Unbaked<Data> {

        public static final MapCodec<Unbaked> MAP_CODEC =
                MapCodec.unit(new Unbaked());

        @Override
        public SpecialModelRenderer<Data> bake(
                SpecialModelRenderer.BakingContext context
        ) {
            ModelPart rootOpen = context.entityModelSet()
                    .bakeLayer(ModModelLayers.DIMENSIONAL_POCKET_OPEN);

            ModelPart rootClosed = context.entityModelSet()
                    .bakeLayer(ModModelLayers.DIMENSIONAL_POCKET_CLOSED);

            return new DimensionalPocketRenderer(
                    new DimensionalPocketOpenModel(rootOpen), new DimensionalPocketClosedModel(rootClosed));
        }

        @Override
        public MapCodec<? extends SpecialModelRenderer.Unbaked<Data>> type() {
            return MAP_CODEC;
        }
    }

    private final ItemModelResolver itemModelResolver =
            Minecraft.getInstance().getItemModelResolver();

    private final ItemStackRenderState selectedItemState =
            new ItemStackRenderState();

    private boolean isSameDimensionalPocket(ItemStack stackA, ItemStack stackB) {
        if (stackA.isEmpty() || stackB.isEmpty()) return false;
        if (!stackA.is(stackB.getItem())) return false;

        return ItemStack.matches(stackA, stackB);
    }
}
