// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class Dimensional Pocket extends EntityModel<Entity> {
	private final ModelPart Hips;
	private final ModelPart Bottom;
	private final ModelPart Top;
	public Dimensional Pocket(ModelPart root) {
		this.Hips = root.getChild("Hips");
		this.Bottom = this.Hips.getChild("Bottom");
		this.Top = this.Hips.getChild("Top");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Hips = modelPartData.addChild("Hips", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData Bottom = Hips.addChild("Bottom", ModelPartBuilder.create().uv(40, 46).cuboid(-5.0F, -3.0F, 6.0F, 10.0F, 9.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 28).cuboid(-7.0F, -3.0F, -7.0F, 14.0F, 3.0F, 14.0F, new Dilation(0.0F))
		.uv(0, 45).cuboid(-5.0F, -3.0F, -5.0F, 10.0F, 2.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Top = Hips.addChild("Top", ModelPartBuilder.create().uv(0, 0).cuboid(-7.0F, -3.0F, -14.0F, 14.0F, 3.0F, 14.0F, new Dilation(0.0F))
		.uv(0, 17).cuboid(-5.0F, -1.0F, -12.0F, 10.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(0, 28).cuboid(-1.0F, -2.0F, -15.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 7.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		Hips.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}