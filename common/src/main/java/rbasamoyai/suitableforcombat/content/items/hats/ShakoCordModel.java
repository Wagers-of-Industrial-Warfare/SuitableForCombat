package rbasamoyai.suitableforcombat.content.items.hats;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class ShakoCordModel extends BasicHatModel {

	private final ModelPart tassel;
	private final ModelPart smallTassel;

	@Nullable private final LivingEntity entity;

	public ShakoCordModel(ModelPart root, @Nullable LivingEntity entity) {
		super(root);
		ModelPart helmet = this.head.getChild("helmet");
		this.tassel = helmet.getChild("tassel");
		this.smallTassel = helmet.getChild("small_tassel");
		this.entity = entity;
	}

	public static LayerDefinition createLayer() {
		MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0f);
		PartDefinition part = mesh.getRoot();
		PartDefinition head = part.getChild("head");
		PartDefinition helmet = head.addOrReplaceChild("helmet", CubeListBuilder.create()
			.texOffs(0, 32).addBox(-4.0F, -12.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25f, 0, 0.25f)), PartPose.offset(0.0f, 0.5f, 0.0f));

		helmet.addOrReplaceChild("tassel", CubeListBuilder.create()
			.texOffs(32, 32).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(36, 32).addBox(-1.0F, 2.5F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(44, 32).addBox(-1.0F, 7.5F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.25F, -10.0F, 0.0F));
		helmet.addOrReplaceChild("small_tassel", CubeListBuilder.create()
			.texOffs(52, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.25F, -10.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		return LayerDefinition.create(mesh, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
							   float red, float green, float blue, float alpha) {
		float f = this.head.xRot;
		float yaw = this.entity == null ? 0 :
			Mth.lerp(Minecraft.getInstance().getFrameTime(), Mth.wrapDegrees(this.entity.yHeadRotO), Mth.wrapDegrees(this.entity.yHeadRot));
		yaw *= Mth.DEG_TO_RAD;
		Vec3 forward = new Vec3(-Mth.sin(yaw), 0, Mth.cos(yaw));
		Vec3 vel = this.entity == null ? Vec3.ZERO : this.entity.getDeltaMovement();
		float dv = (float) forward.dot(vel.normalize()) * (float) vel.horizontalDistance();
		f -= Mth.clamp(dv * 5, -45 * Mth.DEG_TO_RAD, 45 * Mth.DEG_TO_RAD);
		this.tassel.xRot = -f;
		this.smallTassel.xRot = -f;
		super.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
