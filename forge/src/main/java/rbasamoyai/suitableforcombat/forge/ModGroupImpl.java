package rbasamoyai.suitableforcombat.forge;

import net.minecraft.world.item.CreativeModeTab;
import rbasamoyai.suitableforcombat.ModGroup;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;

public class ModGroupImpl {

	public static CreativeModeTab create() {
		return new ModGroup(-1, SuitableForCombatMod.MOD_ID);
	}

}
