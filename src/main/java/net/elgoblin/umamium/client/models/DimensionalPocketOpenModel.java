package net.elgoblin.umamium.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class DimensionalPocketOpenModel {
	private final ModelPart hips;
	private final ModelPart bottom;
	private final ModelPart top;

	public DimensionalPocketOpenModel(ModelPart root) {
		this.hips = root.getChild("hips");
		this.bottom = this.hips.getChild("bottom");
		this.top = this.hips.getChild("top");
	}

	public static LayerDefinition createBodyLayer() {

		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition root = meshDefinition.getRoot();

		PartDefinition hips = root.addOrReplaceChild(
				"hips",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F)
		);

		PartDefinition bottom = hips.addOrReplaceChild(
				"bottom",
				CubeListBuilder.create()
						.texOffs(40, 46)
						.addBox(
								-5.0F, -3.0F, 6.0F,
								10.0F, 9.0F, 2.0F,
								new CubeDeformation(0.0F)
						)
						.texOffs(0, 28)
						.addBox(
								-7.0F, -3.0F, -7.0F,
								14.0F, 3.0F, 14.0F,
								new CubeDeformation(0.0F)
						)
						.texOffs(0, 45)
						.addBox(
								-5.0F, -3.0F, -5.0F,
								10.0F, 2.0F, 10.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offset(0.0F, 0.0F, 0.0F)
		);

		PartDefinition top = hips.addOrReplaceChild(
				"top",
				CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(
								-7.0F, -3.0F, -14.0F,
								14.0F, 3.0F, 14.0F,
								new CubeDeformation(0.0F)
						)
						.texOffs(0, 17)
						.addBox(
								-5.0F, -1.0F, -12.0F,
								10.0F, 1.0F, 10.0F,
								new CubeDeformation(0.0F)
						)
						.texOffs(0, 28)
						.addBox(
								-1.0F, -2.0F, -15.0F,
								2.0F, 3.0F, 1.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F,
						-2.0F,
						7.0F,
						-1.6581F,
						0.0F,
						0.0F
				)
		);
		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	public void render(PoseStack poseStack, VertexConsumer consumer, int light, int overlay) {
		hips.render(poseStack, consumer, light, overlay);
	}

	public ModelPart getHips() {
		return this.hips;
	}
	public ModelPart getTop() {
		return this.top;
	}
	public ModelPart getBottom() {
		return this.bottom;
	}
}