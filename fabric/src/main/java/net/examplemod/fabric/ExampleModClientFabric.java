package net.examplemod.fabric;

import net.examplemod.ExampleModClient;
import net.fabricmc.api.ClientModInitializer;

public class ExampleModClientFabric implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ExampleModClient.clientInit();
	}

}
