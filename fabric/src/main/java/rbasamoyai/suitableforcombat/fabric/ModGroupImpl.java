package rbasamoyai.suitableforcombat.fabric;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import rbasamoyai.suitableforcombat.ModGroup;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;

public class ModGroupImpl {

	public static CreativeModeTab create() {
		int id = FabricItemGroupBuilder.build(new ResourceLocation("error_should_not_see", "error"), Items.AIR::getDefaultInstance).getId();
		return new ModGroup(CreativeModeTab.TABS.length - 1, SuitableForCombatMod.MOD_ID);
	}

}
