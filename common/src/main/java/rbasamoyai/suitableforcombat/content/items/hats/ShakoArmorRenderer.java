package rbasamoyai.suitableforcombat.content.items.hats;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import rbasamoyai.suitableforcombat.SFCModClient;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;
import rbasamoyai.suitableforcombat.content.BasicHumanoidArmorRenderer;
import rbasamoyai.suitableforcombat.content.items.hats.ShakoItem.Ornament;
import rbasamoyai.suitableforcombat.content.items.ornaments.DyeableOrnamentItem;
import rbasamoyai.suitableforcombat.index.SFCItems;
import rbasamoyai.suitableforcombat.index.SFCModelLayers;

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
		if (!(itemStack.getItem() instanceof ShakoItem shako)) return;

		DyeableOrnamentItem HAT_BAND = SFCItems.HAT_BAND.get();
		Item GILDING = SFCItems.GILDING.get();

		ItemStack upperBand = shako.getOrnament(itemStack, Ornament.UPPER_BAND);
		if (upperBand.is(HAT_BAND)) {
			int i = HAT_BAND.getColor(upperBand);
			list.add(new SubArmorLayer(
				new ShakoModel(SFCModClient.bakeRoot(SFCModelLayers.SHAKO)),
				SuitableForCombatMod.resource("textures/armor/ornament_overlays/shako_upper_band.png"),
				true,
				i,
				SuitableForCombatMod.resource("textures/armor/ornament_overlays/no_overlay.png")));
		} else if (upperBand.is(GILDING)) {
			list.add(new SubArmorLayer(
				new ShakoModel(SFCModClient.bakeRoot(SFCModelLayers.SHAKO)),
				SuitableForCombatMod.resource("textures/armor/ornament_overlays/shako_gilded_upper_band.png"),
				false, 0, null));
		}
		ItemStack middleBand = shako.getOrnament(itemStack, Ornament.MIDDLE_BANDS);
		if (middleBand.is(HAT_BAND)) {
			int i = HAT_BAND.getColor(middleBand);
			list.add(new SubArmorLayer(
				new ShakoModel(SFCModClient.bakeRoot(SFCModelLayers.SHAKO)),
				SuitableForCombatMod.resource("textures/armor/ornament_overlays/shako_middle_bands.png"),
				true,
				i,
				SuitableForCombatMod.resource("textures/armor/ornament_overlays/no_overlay.png")));
		} else if (middleBand.is(GILDING)) {
			list.add(new SubArmorLayer(
				new ShakoModel(SFCModClient.bakeRoot(SFCModelLayers.SHAKO)),
				SuitableForCombatMod.resource("textures/armor/ornament_overlays/shako_gilded_middle_bands.png"),
				false, 0, null));
		}
		ItemStack lowerBand = shako.getOrnament(itemStack, Ornament.LOWER_BAND);
		if (lowerBand.is(HAT_BAND)) {
			int i = HAT_BAND.getColor(lowerBand);
			list.add(new SubArmorLayer(
				new ShakoModel(SFCModClient.bakeRoot(SFCModelLayers.SHAKO)),
				SuitableForCombatMod.resource("textures/armor/ornament_overlays/shako_lower_band.png"),
				true,
				i,
				SuitableForCombatMod.resource("textures/armor/ornament_overlays/no_overlay.png")));
		} else if (lowerBand.is(GILDING)) {
			list.add(new SubArmorLayer(
				new ShakoModel(SFCModClient.bakeRoot(SFCModelLayers.SHAKO)),
				SuitableForCombatMod.resource("textures/armor/ornament_overlays/shako_gilded_lower_band.png"),
				false, 0, null));
		}
		ItemStack visor = shako.getOrnament(itemStack, Ornament.VISOR);
		if (visor.is(HAT_BAND)) {
			int i = HAT_BAND.getColor(visor);
			list.add(new SubArmorLayer(
				new ShakoModel(SFCModClient.bakeRoot(SFCModelLayers.SHAKO)),
				SuitableForCombatMod.resource("textures/armor/ornament_overlays/shako_visor.png"),
				true,
				i,
				SuitableForCombatMod.resource("textures/armor/ornament_overlays/no_overlay.png")));
		} else if (visor.is(GILDING)) {
			list.add(new SubArmorLayer(
				new ShakoModel(SFCModClient.bakeRoot(SFCModelLayers.SHAKO)),
				SuitableForCombatMod.resource("textures/armor/ornament_overlays/shako_gilded_visor.png"),
				false, 0, null));
		}
		ItemStack cord = shako.getOrnament(itemStack, Ornament.CORD);
		if (cord.is(SFCItems.CORD.get())) {
			int i = SFCItems.CORD.get().getColor(cord);
			ShakoCordModel cordModel = new ShakoCordModel(SFCModClient.bakeRoot(SFCModelLayers.SHAKO_CORD), entity);
			list.add(new SubArmorLayer(
				cordModel,
				SuitableForCombatMod.resource("textures/armor/ornament_overlays/shako_cord.png"),
				true,
				i,
				SuitableForCombatMod.resource("textures/armor/ornament_overlays/no_overlay.png")));
		}
	}

}
