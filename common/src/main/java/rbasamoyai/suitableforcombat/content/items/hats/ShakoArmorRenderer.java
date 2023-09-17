package rbasamoyai.suitableforcombat.content.items.hats;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import rbasamoyai.suitableforcombat.SFCModClient;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;
import rbasamoyai.suitableforcombat.content.BasicHumanoidArmorRenderer;
import rbasamoyai.suitableforcombat.index.SFCModelLayers;

import javax.annotation.Nullable;

import java.util.List;

public class ShakoArmorRenderer extends BasicHumanoidArmorRenderer {

	@Override
	public HumanoidModel<?> getModel(ItemStack itemStack, LivingEntity entity, EquipmentSlot slot) {
		return new ShakoModel(SFCModClient.bakeRoot(SFCModelLayers.SHAKO));
	}

	@Override
	public ResourceLocation getArmorResource(LivingEntity entity, ItemStack stack, EquipmentSlot slot, @Nullable String overlay) {
		return SuitableForCombatMod.resource("textures/armor/shako.png");
	}

	@Override
	protected void addOrnaments(ItemStack itemStack, LivingEntity entity, EquipmentSlot slot, List<SubArmorLayer> list) {
		super.addOrnaments(itemStack, entity, slot, list);
		list.add(new SubArmorLayer(
			new ShakoModel(SFCModClient.bakeRoot(SFCModelLayers.SHAKO)),
			SuitableForCombatMod.resource("textures/armor/ornament_overlays/shako_upper_band.png"),
			true,
			0xFF0000,
			SuitableForCombatMod.resource("textures/armor/ornament_overlays/no_overlay.png")));
	}

}