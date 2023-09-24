package rbasamoyai.suitableforcombat.fabric;

import rbasamoyai.suitableforcombat.SuitableForCombatMod;
import net.fabricmc.api.ModInitializer;
import rbasamoyai.suitableforcombat.network.fabric.SFCNetworkImpl;

public class SuitableForCombatModFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        SuitableForCombatMod.init();
		SFCNetworkImpl.serverInit();
    }

}
