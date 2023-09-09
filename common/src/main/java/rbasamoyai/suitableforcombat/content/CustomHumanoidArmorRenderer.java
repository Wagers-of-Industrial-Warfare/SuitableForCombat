package rbasamoyai.suitableforcombat.content;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface CustomHumanoidArmorRenderer {

	void render(ItemStack itemStack, PoseStack poseStack, MultiBufferSource buffers, LivingEntity entity, EquipmentSlot slot,
				int light, HumanoidModel<?> parentModel);

}
