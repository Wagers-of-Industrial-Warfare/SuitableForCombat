package rbasamoyai.suitableforcombat.index;

import net.minecraft.client.model.geom.ModelLayerLocation;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;

public class SFCModelLayers {

	public static final ModelLayerLocation
		CAVALRY_POT_HELMET = create("cavalry_pot_helmet"),
		DRAGOON_HELMET = create("dragoon_helmet"),
		KEPI = create("kepi"),
		PICKELHAUBE = create("pickelhaube"),
		PITH_HELMET = create("pith_helmet"),
		SHAKO = create("shako"),
		SHAKO_CORD = create("shako_cord");

	private static ModelLayerLocation create(String name) {
		return create(name, "main");
	}

	private static ModelLayerLocation create(String name, String layer) {
		return new ModelLayerLocation(SuitableForCombatMod.resource(name), layer);
	}

}
