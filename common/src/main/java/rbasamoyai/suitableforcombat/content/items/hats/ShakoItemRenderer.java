package rbasamoyai.suitableforcombat.content.items.hats;

import java.util.List;
import java.util.Random;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import rbasamoyai.suitableforcombat.content.items.hats.ShakoItem.Ornament;
import rbasamoyai.suitableforcombat.content.items.ornaments.DyeableOrnamentItem;
import rbasamoyai.suitableforcombat.index.SFCItems;
import rbasamoyai.suitableforcombat.index.SFCPartialModels;

public class ShakoItemRenderer extends BlockEntityWithoutLevelRenderer {

	public ShakoItemRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet models) {
		super(dispatcher, models);
	}

	@Override
	public void renderByItem(ItemStack stack, TransformType transformType, PoseStack poseStack, MultiBufferSource buffer,
							 int packedLight, int packedOverlay) {
		if (!(stack.getItem() instanceof ShakoItem shako)) return;

		RenderType renderType = ItemBlockRenderTypes.getRenderType(stack, true);
		VertexConsumer vcons = ItemRenderer.getFoilBufferDirect(buffer, renderType, true, stack.hasFoil());

		DyeableOrnamentItem HAT_BAND = SFCItems.HAT_BAND.get();
		Item GILDING = SFCItems.GILDING.get();

		ItemStack upperBand = shako.getOrnament(stack, Ornament.UPPER_BAND);
		if (upperBand.is(HAT_BAND)) {
			int i = HAT_BAND.getColor(upperBand);
			float r = (float)(i >> 16 & 0xFF) / 255f;
			float g = (float)(i >> 8 & 0xFF) / 255f;
			float b = (float)(i & 0xFF) / 255f;
			this.renderModelLists(SFCPartialModels.SHAKO_UPPER_BAND.get(), packedLight, packedOverlay, poseStack, vcons, r, g, b);
		} else if (upperBand.is(GILDING)) {
			this.renderModelLists(SFCPartialModels.SHAKO_GILDED_UPPER_BAND.get(), packedLight, packedOverlay, poseStack, vcons, 1, 1, 1);
		}
		ItemStack middleBand = shako.getOrnament(stack, Ornament.MIDDLE_BANDS);
		if (middleBand.is(HAT_BAND)) {
			int i = HAT_BAND.getColor(middleBand);
			float r = (float)(i >> 16 & 0xFF) / 255f;
			float g = (float)(i >> 8 & 0xFF) / 255f;
			float b = (float)(i & 0xFF) / 255f;
			this.renderModelLists(SFCPartialModels.SHAKO_MIDDLE_BANDS.get(), packedLight, packedOverlay, poseStack, vcons, r, g, b);
		} else if (middleBand.is(GILDING)) {
			this.renderModelLists(SFCPartialModels.SHAKO_GILDED_MIDDLE_BANDS.get(), packedLight, packedOverlay, poseStack, vcons, 1, 1, 1);
		}
		ItemStack lowerBand = shako.getOrnament(stack, Ornament.LOWER_BAND);
		if (lowerBand.is(HAT_BAND)) {
			int i = HAT_BAND.getColor(lowerBand);
			float r = (float)(i >> 16 & 0xFF) / 255f;
			float g = (float)(i >> 8 & 0xFF) / 255f;
			float b = (float)(i & 0xFF) / 255f;
			this.renderModelLists(SFCPartialModels.SHAKO_LOWER_BAND.get(), packedLight, packedOverlay, poseStack, vcons, r, g, b);
		} else if (lowerBand.is(GILDING)) {
			this.renderModelLists(SFCPartialModels.SHAKO_GILDED_LOWER_BAND.get(), packedLight, packedOverlay, poseStack, vcons, 1, 1, 1);
		}
		ItemStack visor = shako.getOrnament(stack, Ornament.VISOR);
		if (visor.is(HAT_BAND)) {
			int i = HAT_BAND.getColor(visor);
			float r = (float)(i >> 16 & 0xFF) / 255f;
			float g = (float)(i >> 8 & 0xFF) / 255f;
			float b = (float)(i & 0xFF) / 255f;
			this.renderModelLists(SFCPartialModels.SHAKO_VISOR.get(), packedLight, packedOverlay, poseStack, vcons, r, g, b);
		} else if (visor.is(GILDING)) {
			this.renderModelLists(SFCPartialModels.SHAKO_GILDED_VISOR.get(), packedLight, packedOverlay, poseStack, vcons, 1, 1, 1);
		}
		ItemStack cord = shako.getOrnament(stack, Ornament.CORD);
		if (cord.is(SFCItems.CORD.get())) {
			int i = SFCItems.CORD.get().getColor(cord);
			float r = (float)(i >> 16 & 0xFF) / 255f;
			float g = (float)(i >> 8 & 0xFF) / 255f;
			float b = (float)(i & 0xFF) / 255f;
			this.renderModelLists(SFCPartialModels.SHAKO_CORD.get(), packedLight, packedOverlay, poseStack, vcons, r, g, b);
		}
		ItemStack strap = shako.getOrnament(stack, Ornament.STRAP);
		if (strap.is(GILDING)) {
			this.renderModelLists(SFCPartialModels.SHAKO_GILDED_STRAP.get(), packedLight, packedOverlay, poseStack, vcons, 1, 1, 1);
		}
	}

	public void renderModelLists(BakedModel pModel, int pCombinedLight, int pCombinedOverlay, PoseStack pMatrixStack,
								 VertexConsumer pBuffer, float r, float g, float b) {
		Random random = new Random();
		for(Direction direction : Direction.values()) {
			random.setSeed(42L);
			this.renderQuadList(pMatrixStack, pBuffer, pModel.getQuads(null, direction, random), pCombinedLight, pCombinedOverlay, r, g, b);
		}
		random.setSeed(42L);
		this.renderQuadList(pMatrixStack, pBuffer, pModel.getQuads(null, null, random), pCombinedLight, pCombinedOverlay, r, g, b);
	}

	public void renderQuadList(PoseStack pPoseStack, VertexConsumer pBuffer, List<BakedQuad> pQuads, int pCombinedLight,
							   int pCombinedOverlay, float r, float g, float b) {
		PoseStack.Pose posestack$pose = pPoseStack.last();
		for (BakedQuad bakedquad : pQuads) {
			pBuffer.putBulkData(posestack$pose, bakedquad, r, g, b, pCombinedLight, pCombinedOverlay);
		}
	}

}
