package rbasamoyai.suitableforcombat.index;

import net.minecraft.client.model.geom.ModelLayerLocation;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;

public class SFCModelLayers {

	public static final ModelLayerLocation PITH_HELMET = create("pith_helmet");

	private static ModelLayerLocation create(String name) {
		return create(name, "main");
	}

	private static ModelLayerLocation create(String name, String layer) {
		return new ModelLayerLocation(SuitableForCombatMod.resource(name), layer);
	}

}
