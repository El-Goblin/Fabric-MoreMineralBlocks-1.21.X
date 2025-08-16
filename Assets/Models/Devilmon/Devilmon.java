// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class Devilmon extends EntityModel<Entity> {
	private final ModelPart Root;
	private final ModelPart Body;
	private final ModelPart Horns;
	private final ModelPart Wings;
	private final ModelPart WingL;
	private final ModelPart WingR;
	public Devilmon(ModelPart root) {
		this.Root = root.getChild("Root");
		this.Body = this.Root.getChild("Body");
		this.Horns = this.Body.getChild("Horns");
		this.Wings = this.Root.getChild("Wings");
		this.WingL = this.Wings.getChild("WingL");
		this.WingR = this.Wings.getChild("WingR");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Root = modelPartData.addChild("Root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData Body = Root.addChild("Body", ModelPartBuilder.create().uv(32, 32).cuboid(-1.0F, -3.8F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-5.0F, -0.3F, -5.0F, 10.0F, 4.0F, 10.0F, new Dilation(0.0F))
		.uv(0, 25).cuboid(-4.0F, 3.7F, -4.0F, 8.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 14).cuboid(-4.0F, -2.3F, -4.0F, 8.0F, 3.0F, 8.0F, new Dilation(0.0F))
		.uv(32, 14).cuboid(-3.0F, -3.3F, -3.0F, 6.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.7F, 0.0F));

		ModelPartData Horns = Body.addChild("Horns", ModelPartBuilder.create(), ModelTransform.pivot(-3.0F, -2.8F, 2.75F));

		ModelPartData cube_r1 = Horns.addChild("cube_r1", ModelPartBuilder.create().uv(0, 34).cuboid(0.0F, -2.5F, 0.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, 0.0F, 0.0F, -0.223F, -0.6888F, 0.3298F));

		ModelPartData cube_r2 = Horns.addChild("cube_r2", ModelPartBuilder.create().uv(4, 34).cuboid(0.0F, -2.5F, 0.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.2624F, -0.7184F, -0.0121F));

		ModelPartData Wings = Root.addChild("Wings", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData WingL = Wings.addChild("WingL", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, -3.0F, -1.0F));

		ModelPartData cube_r3 = WingL.addChild("cube_r3", ModelPartBuilder.create().uv(32, 22).cuboid(-10.0F, -5.0F, 0.0F, 10.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		ModelPartData WingR = Wings.addChild("WingR", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, -3.0F, -1.0F));

		ModelPartData cube_r4 = WingR.addChild("cube_r4", ModelPartBuilder.create().uv(32, 27).cuboid(0.0F, -5.0F, 0.0F, 10.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		Root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}