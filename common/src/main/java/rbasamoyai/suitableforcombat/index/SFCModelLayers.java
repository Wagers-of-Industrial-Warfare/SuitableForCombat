package rbasamoyai.suitableforcombat.index;

import net.minecraft.client.model.geom.ModelLayerLocation;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;

public class SFCModelLayers {

	public static final ModelLayerLocation CAVALRY_POT_HELMET = create("cavalry_pot_helmet");
	public static final ModelLayerLocation DRAGOON_HELMET = create("dragoon_helmet");
	public static final ModelLayerLocation KEPI = create("kepi");
	public static final ModelLayerLocation PICKELHAUBE = create("pickelhaube");
	public static final ModelLayerLocation PITH_HELMET = create("pith_helmet");
	public static final ModelLayerLocation SHAKO = create("shako");

	private static ModelLayerLocation create(String name) {
		return create(name, "main");
	}

	private static ModelLayerLocation create(String name, String layer) {
		return new ModelLayerLocation(SuitableForCombatMod.resource(name), layer);
	}

}
