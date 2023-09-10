package rbasamoyai.suitableforcombat.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import rbasamoyai.suitableforcombat.SFCModClient;

public class SFCModClientFabric implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		SFCModClient.clientInit();
		SFCModClient.registerLayers((loc, sup) -> EntityModelLayerRegistry.registerModelLayer(loc, sup::get));
		SFCModClient.registerItemColor(ColorProviderRegistry.ITEM::register);
	}

}
