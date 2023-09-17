package rbasamoyai.suitableforcombat.index;

import rbasamoyai.suitableforcombat.SuitableForCombatMod;
import rbasamoyai.suitableforcombat.content.PartialModel;

public class SFCPartialModels {

	public static final PartialModel
		SHAKO_UPPER_BAND = ornamentOverlay("shako_upper_band");

	public static void init() {}

	private static PartialModel ornamentOverlay(String path) {
		return new PartialModel(SuitableForCombatMod.resource("item/ornament_overlays/" + path));
	}

}
