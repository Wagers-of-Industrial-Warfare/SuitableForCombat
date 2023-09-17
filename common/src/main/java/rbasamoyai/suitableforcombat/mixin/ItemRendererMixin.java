package rbasamoyai.suitableforcombat.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import rbasamoyai.suitableforcombat.SFCModClient;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

	@Inject(method = "render",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;renderModelLists(Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/world/item/ItemStack;IILcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;)V", shift = At.Shift.AFTER)
	)
	private void suitableforcombat$renderByItem(ItemStack itemStack, TransformType transformType, boolean leftHand,
												PoseStack poseStack, MultiBufferSource buffer, int combinedLight,
												int combinedOverlay, BakedModel model, CallbackInfo ci) {
		BlockEntityWithoutLevelRenderer other = SFCModClient.getCustomItemOverlayRenderer(itemStack.getItem());
		if (other != null) other.renderByItem(itemStack, transformType, poseStack, buffer, combinedLight, combinedOverlay);
	}

}
