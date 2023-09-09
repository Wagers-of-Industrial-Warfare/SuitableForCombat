package net.examplemod.forge;

import net.examplemod.ExampleModClient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ExampleModClientForge {

	public static void clientInit(IEventBus modBus, IEventBus forgeBus) {
		modBus.addListener(ExampleModClientForge::onClientSetup);
	}

	public static void onClientSetup(final FMLClientSetupEvent evt) {
		ExampleModClient.clientInit();
	}

}
