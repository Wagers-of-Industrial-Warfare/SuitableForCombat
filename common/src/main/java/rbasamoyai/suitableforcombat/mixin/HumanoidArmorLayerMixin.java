package rbasamoyai.suitableforcombat.mixin;

import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import rbasamoyai.suitableforcombat.SFCModClient;
import rbasamoyai.suitableforcombat.content.CustomHumanoidArmorRenderer;

@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin extends RenderLayer {

	HumanoidArmorLayerMixin(RenderLayerParent renderer) { super(renderer); }

	@Inject(method = "renderArmorPiece", at = @At("HEAD"), cancellable = true)
	private void suitableforcombat$render(PoseStack poseStack, MultiBufferSource buffer, LivingEntity livingEntity,
										  EquipmentSlot slot, int light, HumanoidModel model, CallbackInfo ci) {
		ItemStack armorItem = livingEntity.getItemBySlot(slot);
		CustomHumanoidArmorRenderer custom = SFCModClient.getRenderer(armorItem.getItem());
		if (custom != null) {
			custom.render(armorItem, poseStack, buffer, livingEntity, slot, light, (HumanoidModel<?>) this.getParentModel());
			ci.cancel();
		}
	}

}
