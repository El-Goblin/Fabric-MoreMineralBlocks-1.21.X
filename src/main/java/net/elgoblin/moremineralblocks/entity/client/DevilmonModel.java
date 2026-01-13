//package net.elgoblin.moremineralblocks.entity.client;
//
//import net.elgoblin.moremineralblocks.MoreMineralBlocks;
//import net.elgoblin.moremineralblocks.entity.custom.DevilmonEntity;
//import net.elgoblin.moremineralblocks.entity.custom.MantisEntity;
//import net.minecraft.client.model.*;
//import net.minecraft.client.render.VertexConsumer;
//import net.minecraft.client.render.entity.model.EntityModelLayer;
//import net.minecraft.client.render.entity.model.SinglePartEntityModel;
//import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.entity.Entity;
//import net.minecraft.util.Identifier;
//import net.minecraft.util.math.MathHelper;
//
//public class DevilmonModel<T extends DevilmonEntity> extends SinglePartEntityModel<T> {
//
//    public static final EntityModelLayer DEVILMON = new EntityModelLayer(Identifier.of(MoreMineralBlocks.MOD_ID, "devilmon"), "main");
//
//    private final ModelPart devilmon;
//    //private final ModelPart body;
//
//    public DevilmonModel(ModelPart root) {
//        this.devilmon = root.getChild("devilmon");
//        //this.body = this.devilmon.getChild("body");
//    }
//    public static TexturedModelData getTexturedModelData() {
//        ModelData modelData = new ModelData();
//        ModelPartData modelPartData = modelData.getRoot();
//        ModelPartData Root = modelPartData.addChild("devilmon", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
//
//        ModelPartData Body = Root.addChild("body", ModelPartBuilder.create().uv(32, 32).cuboid(-1.0F, -3.8F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
//                .uv(0, 0).cuboid(-5.0F, -0.3F, -5.0F, 10.0F, 4.0F, 10.0F, new Dilation(0.0F))
//                .uv(0, 25).cuboid(-4.0F, 3.7F, -4.0F, 8.0F, 1.0F, 8.0F, new Dilation(0.0F))
//                .uv(0, 14).cuboid(-4.0F, -2.3F, -4.0F, 8.0F, 3.0F, 8.0F, new Dilation(0.0F))
//                .uv(32, 14).cuboid(-3.0F, -3.3F, -3.0F, 6.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.7F, 0.0F));
//
//        ModelPartData Horns = Body.addChild("horns", ModelPartBuilder.create(), ModelTransform.pivot(-3.0F, -2.8F, 2.75F));
//
//        ModelPartData cube_r1 = Horns.addChild("cube_r1", ModelPartBuilder.create().uv(0, 34).cuboid(0.0F, -2.5F, 0.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, 0.0F, 0.0F, -0.223F, -0.6888F, 0.3298F));
//
//        ModelPartData cube_r2 = Horns.addChild("cube_r2", ModelPartBuilder.create().uv(4, 34).cuboid(0.0F, -2.5F, 0.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.2624F, -0.7184F, -0.0121F));
//
//        ModelPartData Wings = Root.addChild("wings", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//
//        ModelPartData WingL = Wings.addChild("wingL", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, -3.0F, -1.0F));
//
//        ModelPartData cube_r3 = WingL.addChild("cube_r3", ModelPartBuilder.create().uv(32, 22).cuboid(-10.0F, -5.0F, 0.0F, 10.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));
//
//        ModelPartData WingR = Wings.addChild("wingR", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, -3.0F, -1.0F));
//
//        ModelPartData cube_r4 = WingR.addChild("cube_r4", ModelPartBuilder.create().uv(32, 27).cuboid(0.0F, -5.0F, 0.0F, 10.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));
//        return TexturedModelData.of(modelData, 64, 64);
//    }
//    @Override
//    public void setAngles(DevilmonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        this.getPart().traverse().forEach(ModelPart::resetTransform);
//        this.setHeadAngles(netHeadYaw, headPitch);
//
//        this.animateMovement(DevilmonAnimations.ANIM_DEVILMON_FLY, limbSwing, limbSwingAmount, 2f, 2.5f);
//    }
//
//    private void setHeadAngles(float headYaw, float headPitch) {
//        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
//        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);
//
//        //this.body.yaw = headYaw * 0.017453292F;
//        //this.body.pitch = headPitch * 0.017453292F;
//    }
//
//    @Override
//    public ModelPart getPart() {
//        return devilmon;
//    }
//
//    @Override
//    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
//        devilmon.render(matrices, vertexConsumer, light, overlay, color);
//    }
//}