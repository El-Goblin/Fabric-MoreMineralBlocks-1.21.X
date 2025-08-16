// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class Snail extends EntityModel<Entity> {
	private final ModelPart Root;
	private final ModelPart Body;
	private final ModelPart Shell;
	private final ModelPart Antenae;
	private final ModelPart AntenaeL;
	private final ModelPart AntenaeR;
	public Snail(ModelPart root) {
		this.Root = root.getChild("Root");
		this.Body = this.Root.getChild("Body");
		this.Shell = this.Root.getChild("Shell");
		this.Antenae = this.Root.getChild("Antenae");
		this.AntenaeL = this.Antenae.getChild("AntenaeL");
		this.AntenaeR = this.Antenae.getChild("AntenaeR");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Root = modelPartData.addChild("Root", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 24.0F, 0.0F));

		ModelPartData Body = Root.addChild("Body", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -2.0F, -2.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = Body.addChild("cube_r1", ModelPartBuilder.create().uv(14, 8).cuboid(-1.0F, -0.5F, -3.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -0.5F, -1.75F, 0.2182F, 0.0F, 0.0F));

		ModelPartData Shell = Root.addChild("Shell", ModelPartBuilder.create().uv(0, 8).cuboid(-1.0F, -4.1941F, -1.2756F, 3.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -0.75F, -1.25F, -0.2618F, 0.0F, 0.0F));

		ModelPartData Antenae = Root.addChild("Antenae", ModelPartBuilder.create(), ModelTransform.pivot(0.5F, -2.5F, 3.0F));

		ModelPartData AntenaeL = Antenae.addChild("AntenaeL", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -1.0F));

		ModelPartData cube_r2 = AntenaeL.addChild("cube_r2", ModelPartBuilder.create().uv(14, 12).cuboid(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 1.0F, -0.7681F, -0.1841F, -0.1872F));

		ModelPartData AntenaeR = Antenae.addChild("AntenaeR", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 0.0F, -1.0F));

		ModelPartData cube_r3 = AntenaeR.addChild("cube_r3", ModelPartBuilder.create().uv(14, 15).cuboid(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 1.0F, -0.7681F, 0.1841F, 0.1872F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		Root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}