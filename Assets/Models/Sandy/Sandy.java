// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class Sandy extends EntityModel<Entity> {
	private final ModelPart Root;
	private final ModelPart Body;
	private final ModelPart Legs;
	private final ModelPart LegFL;
	private final ModelPart LegFR;
	private final ModelPart LegBR;
	private final ModelPart LegBL;
	private final ModelPart Tail;
	private final ModelPart Head;
	private final ModelPart EarL;
	private final ModelPart EarR;
	public Sandy(ModelPart root) {
		this.Root = root.getChild("Root");
		this.Body = this.Root.getChild("Body");
		this.Legs = this.Root.getChild("Legs");
		this.LegFL = this.Legs.getChild("LegFL");
		this.LegFR = this.Legs.getChild("LegFR");
		this.LegBR = this.Legs.getChild("LegBR");
		this.LegBL = this.Legs.getChild("LegBL");
		this.Tail = this.Root.getChild("Tail");
		this.Head = this.Root.getChild("Head");
		this.EarL = this.Head.getChild("EarL");
		this.EarR = this.Head.getChild("EarR");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Root = modelPartData.addChild("Root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData Body = Root.addChild("Body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -6.0F, 0.375F));

		ModelPartData Body_r1 = Body.addChild("Body_r1", ModelPartBuilder.create().uv(26, 13).cuboid(-1.0F, -9.0F, -0.5F, 6.0F, 6.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 6.0F, -5.375F, 0.0F, -1.5708F, 0.0F));

		ModelPartData Body_r2 = Body.addChild("Body_r2", ModelPartBuilder.create().uv(26, 26).cuboid(-2.0F, -8.0F, -0.5F, 7.0F, 5.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, 6.0F, 1.625F, 0.0F, -1.5708F, 0.0F));

		ModelPartData Body_r3 = Body.addChild("Body_r3", ModelPartBuilder.create().uv(0, 13).cuboid(0.0F, -10.0F, -1.5F, 5.0F, 7.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, 6.0F, -5.375F, 0.0F, -1.5708F, 0.0F));

		ModelPartData Body_r4 = Body.addChild("Body_r4", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -9.0F, -1.5F, 8.0F, 6.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 6.0F, 1.125F, 0.0F, -1.5708F, 0.0F));

		ModelPartData Legs = Root.addChild("Legs", ModelPartBuilder.create(), ModelTransform.pivot(2.0F, -3.5F, -4.0F));

		ModelPartData LegFL = Legs.addChild("LegFL", ModelPartBuilder.create(), ModelTransform.pivot(0.5F, 0.5F, 0.0F));

		ModelPartData cube_r1 = LegFL.addChild("cube_r1", ModelPartBuilder.create().uv(24, 37).cuboid(-1.0F, -1.0F, -0.5F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData LegFR = Legs.addChild("LegFR", ModelPartBuilder.create(), ModelTransform.pivot(-3.5F, 0.5F, 0.0F));

		ModelPartData cube_r2 = LegFR.addChild("cube_r2", ModelPartBuilder.create().uv(32, 37).cuboid(-1.0F, -1.0F, -0.5F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData LegBR = Legs.addChild("LegBR", ModelPartBuilder.create(), ModelTransform.pivot(-3.25F, 0.5F, 9.0F));

		ModelPartData cube_r3 = LegBR.addChild("cube_r3", ModelPartBuilder.create().uv(16, 33).cuboid(-1.0F, -1.0F, -0.5F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData LegBL = Legs.addChild("LegBL", ModelPartBuilder.create(), ModelTransform.pivot(-0.25F, 0.5F, 9.0F));

		ModelPartData cube_r4 = LegBL.addChild("cube_r4", ModelPartBuilder.create().uv(30, 4).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData Tail = Root.addChild("Tail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -8.125F, 7.625F));

		ModelPartData cube_r5 = Tail.addChild("cube_r5", ModelPartBuilder.create().uv(4, 40).cuboid(-1.0F, -1.0F, -1.0F, 7.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 0.375F, -0.375F, 0.0F, -1.5708F, 0.0F));

		ModelPartData cube_r6 = Tail.addChild("cube_r6", ModelPartBuilder.create().uv(30, 0).cuboid(-1.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.625F, -0.625F, 0.0F, -1.5708F, 0.0F));

		ModelPartData Head = Root.addChild("Head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -6.25F, -8.5F));

		ModelPartData Snout_r1 = Head.addChild("Snout_r1", ModelPartBuilder.create().uv(16, 28).cuboid(-1.0F, -1.5F, 0.5F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 1.25F, -1.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData Fringe_r1 = Head.addChild("Fringe_r1", ModelPartBuilder.create().uv(18, 43).cuboid(-2.0F, -4.5F, -2.5F, 3.0F, 5.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 1.25F, 1.0F, -1.5708F, -1.4835F, 1.5708F));

		ModelPartData Head_r1 = Head.addChild("Head_r1", ModelPartBuilder.create().uv(0, 28).cuboid(-2.0F, -4.5F, -1.5F, 3.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.25F, 1.5F, 0.0F, -1.5708F, 0.0F));

		ModelPartData EarL = Head.addChild("EarL", ModelPartBuilder.create(), ModelTransform.pivot(1.75F, -3.5F, 2.0F));

		ModelPartData EarL_r1 = EarL.addChild("EarL_r1", ModelPartBuilder.create().uv(38, 4).cuboid(0.0F, -1.5F, 1.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.25F, -0.25F, -0.5F, 0.0F, -1.5708F, 0.0F));

		ModelPartData EarLTip_r1 = EarL.addChild("EarLTip_r1", ModelPartBuilder.create().uv(34, 10).cuboid(0.0F, -0.5F, 1.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.25F, -2.25F, -1.5F, 0.0F, -1.5708F, 0.0F));

		ModelPartData EarR = Head.addChild("EarR", ModelPartBuilder.create(), ModelTransform.pivot(-1.75F, -3.5F, 2.0F));

		ModelPartData EarR_r1 = EarR.addChild("EarR_r1", ModelPartBuilder.create().uv(0, 38).cuboid(0.0F, -1.5F, 1.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.75F, -0.25F, -0.5F, 0.0F, -1.5708F, 0.0F));

		ModelPartData EarRTip_r1 = EarR.addChild("EarRTip_r1", ModelPartBuilder.create().uv(30, 10).cuboid(0.0F, -0.5F, 1.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.75F, -2.25F, -1.5F, 0.0F, -1.5708F, 0.0F));
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