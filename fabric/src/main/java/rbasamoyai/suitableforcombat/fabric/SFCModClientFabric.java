package rbasamoyai.suitableforcombat.fabric;

import rbasamoyai.suitableforcombat.SFCModClient;
import net.fabricmc.api.ClientModInitializer;

public class SFCModClientFabric implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		SFCModClient.clientInit();
	}

}
