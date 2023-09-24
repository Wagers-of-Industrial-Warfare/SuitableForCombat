package rbasamoyai.suitableforcombat.index;

import rbasamoyai.suitableforcombat.SuitableForCombatMod;
import rbasamoyai.suitableforcombat.content.PartialModel;

public class SFCPartialModels {

	public static final PartialModel
		SHAKO_UPPER_BAND = ornamentOverlay("shako_upper_band"),
		SHAKO_GILDED_UPPER_BAND = ornamentOverlay("shako_gilded_upper_band"),
		SHAKO_MIDDLE_BANDS = ornamentOverlay("shako_middle_bands"),
		SHAKO_GILDED_MIDDLE_BANDS = ornamentOverlay("shako_gilded_middle_bands"),
		SHAKO_LOWER_BAND = ornamentOverlay("shako_lower_band"),
		SHAKO_GILDED_LOWER_BAND = ornamentOverlay("shako_gilded_lower_band"),
		SHAKO_VISOR = ornamentOverlay("shako_visor"),
		SHAKO_GILDED_VISOR = ornamentOverlay("shako_gilded_visor"),
		SHAKO_GILDED_STRAP = ornamentOverlay("shako_gilded_strap"),
		SHAKO_CORD = ornamentOverlay("shako_cord");

	public static void init() {}

	private static PartialModel ornamentOverlay(String path) {
		return new PartialModel(SuitableForCombatMod.resource("item/ornament_overlays/" + path));
	}

}
