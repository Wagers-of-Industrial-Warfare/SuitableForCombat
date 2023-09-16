package rbasamoyai.suitableforcombat.content.items.hats;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class KepiModel extends BasicHatModel {

	public KepiModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createLayer() {
		MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0f);
		PartDefinition part = mesh.getRoot();
		PartDefinition head = part.getChild("head");

		PartDefinition kepi = head.addOrReplaceChild("kepi", CubeListBuilder.create()
				.texOffs(0, 32).addBox(-4.0F, -6.0F, -6.0F, 8.0F, 1.0F, 10.0F, CubeDeformation.NONE)
				.texOffs(0, 43).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 2.0F, 8.0F, CubeDeformation.NONE), PartPose.ZERO);

		kepi.addOrReplaceChild("peak", CubeListBuilder.create()
				.texOffs(0, 53).addBox(-3.5F, -9.75F, -2.5F, 7.0F, 3.0F, 7.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		return LayerDefinition.create(mesh, 64, 64);
	}

}
