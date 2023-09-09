package rbasamoyai.suitableforcombat;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import rbasamoyai.suitableforcombat.index.SFCItems;

public class ModGroup extends CreativeModeTab {

	public ModGroup(int id, String langId) {
		super(id, langId);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(SFCItems.PITH_HELMET.get());
	}

	@ExpectPlatform public static CreativeModeTab create() { throw new AssertionError(); }

}
