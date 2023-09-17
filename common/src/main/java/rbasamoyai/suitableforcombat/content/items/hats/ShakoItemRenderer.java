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
import net.minecraft.world.item.ItemStack;
import rbasamoyai.suitableforcombat.index.SFCPartialModels;

public class ShakoItemRenderer extends BlockEntityWithoutLevelRenderer {

	public ShakoItemRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet models) {
		super(dispatcher, models);
	}

	@Override
	public void renderByItem(ItemStack stack, TransformType transformType, PoseStack poseStack, MultiBufferSource buffer,
							 int packedLight, int packedOverlay) {
		RenderType renderType = ItemBlockRenderTypes.getRenderType(stack, true);
		VertexConsumer vcons = ItemRenderer.getFoilBufferDirect(buffer, renderType, true, stack.hasFoil());

		this.renderModelLists(SFCPartialModels.SHAKO_UPPER_BAND.get(), packedLight, packedOverlay, poseStack, vcons, 1, 0, 0);
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
