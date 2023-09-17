package rbasamoyai.suitableforcombat.content;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;

public abstract class BasicHumanoidArmorRenderer implements CustomHumanoidArmorRenderer {

	private static final float RECIP_OF_255 = 1 / 255f;

	@Override
	public void render(ItemStack itemStack, PoseStack poseStack, MultiBufferSource buffers, LivingEntity entity,
					   EquipmentSlot slot, int light, HumanoidModel<?> parentModel) {
		if (!(itemStack.getItem() instanceof ArmorItem armorItem) || armorItem.getSlot() != slot) return;
		List<SubArmorLayer> sublayers = new ArrayList<>();

		boolean colorFlag = armorItem instanceof DyeableArmorItem;
		int color = colorFlag ? ((DyeableArmorItem) armorItem).getColor(itemStack) : 0;
		sublayers.add(new SubArmorLayer(
			this.getModel(itemStack, entity, slot),
			this.getArmorResource(entity, itemStack, slot, null),
			colorFlag,
			color,
			colorFlag ? this.getArmorResource(entity, itemStack, slot, "overlay") : null));

		this.addOrnaments(itemStack, entity, slot, sublayers);

		boolean hasFoil = itemStack.hasFoil();

		for (SubArmorLayer sl : sublayers) {
			HumanoidModel<?> layerModel = sl.model();
			copyProperties(parentModel, layerModel);
			this.setPartVisibility(layerModel, slot);
			if (sl.hasColor()) {
				int i = sl.color();
				float r = (float)(i >> 16 & 0xFF) * RECIP_OF_255;
				float g = (float)(i >> 8 & 0xFF) * RECIP_OF_255;
				float b = (float)(i & 0xFF) * RECIP_OF_255;
				this.renderModel(poseStack, buffers, light, hasFoil, layerModel, r, g, b, sl.texture());
				this.renderModel(poseStack, buffers, light, hasFoil, layerModel, 1.0F, 1.0F, 1.0F, sl.overlay());
			} else {
				this.renderModel(poseStack, buffers, light, hasFoil, layerModel, 1.0F, 1.0F, 1.0F, sl.texture());
			}
		}
	}

	private void renderModel(PoseStack arg, MultiBufferSource arg2, int i, boolean hasFoil, Model arg3, float r, float g, float b, ResourceLocation armorResource) {
		VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(arg2, RenderType.armorCutoutNoCull(armorResource), false, hasFoil);
		arg3.renderToBuffer(arg, vertexconsumer, i, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
	}

	private static <T extends LivingEntity> void copyProperties(HumanoidModel<T> src, HumanoidModel<?> dest) {
		src.copyPropertiesTo((HumanoidModel<T>) dest);
	}

	protected void setPartVisibility(HumanoidModel<?> model, EquipmentSlot slot) {
		model.setAllVisible(false);
		switch (slot) {
			case HEAD -> {
				model.head.visible = true;
				model.hat.visible = true;
			}
			case CHEST -> {
				model.body.visible = true;
				model.rightArm.visible = true;
				model.leftArm.visible = true;
			}
			case LEGS -> {
				model.body.visible = true;
				model.rightLeg.visible = true;
				model.leftLeg.visible = true;
			}
			case FEET -> {
				model.rightLeg.visible = true;
				model.leftLeg.visible = true;
			}
		}
	}

	public abstract HumanoidModel<?> getModel(ItemStack itemStack, LivingEntity entity, EquipmentSlot slot);
	public abstract ResourceLocation getArmorResource(LivingEntity entity, ItemStack stack, EquipmentSlot slot, @Nullable String overlay);

	protected void addOrnaments(ItemStack itemStack, LivingEntity entity, EquipmentSlot slot, List<SubArmorLayer> list) {}

	public record SubArmorLayer(HumanoidModel<?> model, ResourceLocation texture, boolean hasColor, int color, @Nullable ResourceLocation overlay) {
	}

}
