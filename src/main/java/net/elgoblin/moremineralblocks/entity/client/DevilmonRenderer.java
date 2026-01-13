//package net.elgoblin.moremineralblocks.entity.client;
//
//import net.elgoblin.moremineralblocks.MoreMineralBlocks;
//import net.elgoblin.moremineralblocks.entity.custom.DevilmonEntity;
//import net.elgoblin.moremineralblocks.entity.custom.MantisEntity;
//import net.minecraft.client.render.VertexConsumerProvider;
//import net.minecraft.client.render.entity.EntityRendererFactory;
//import net.minecraft.client.render.entity.MobEntityRenderer;
//import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.util.Identifier;
//
//public class DevilmonRenderer extends MobEntityRenderer<DevilmonEntity, DevilmonModel<DevilmonEntity>> {
//    public DevilmonRenderer(EntityRendererFactory.Context context) {
//        super(context, new DevilmonModel<>(context.getPart(DevilmonModel.DEVILMON)), 0.75f);
//    }
//
//    @Override
//    public Identifier getTexture(DevilmonEntity entity) {
//        return Identifier.of(MoreMineralBlocks.MOD_ID, "textures/entity/devilmon/devilmon.png");
//    }
//
//    @Override
//    public void render(DevilmonEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
//        matrixStack.scale(1f, 1f, 1f);
//        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
//    }
//}
