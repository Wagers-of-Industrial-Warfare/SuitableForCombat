package rbasamoyai.suitableforcombat;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rbasamoyai.suitableforcombat.index.SFCItems;

public class SuitableForCombatMod {
    public static final String MOD_ID = "suitableforcombat";
    public static final String NAME = "Example Mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	public static final CreativeModeTab GROUP = ModGroup.create();

    public static void init() {
        SFCItems.register();
    }

    public static ResourceLocation resource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

}
