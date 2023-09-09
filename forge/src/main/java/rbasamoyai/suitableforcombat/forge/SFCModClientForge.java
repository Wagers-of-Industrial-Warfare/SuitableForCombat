package rbasamoyai.suitableforcombat.forge;

import net.minecraftforge.client.event.EntityRenderersEvent;
import rbasamoyai.suitableforcombat.SFCModClient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class SFCModClientForge {

	public static void clientInit(IEventBus modBus, IEventBus forgeBus) {
		modBus.addListener(SFCModClientForge::onClientSetup);
		modBus.addListener(SFCModClientForge::onRegisterLayers);
	}

	public static void onClientSetup(final FMLClientSetupEvent evt) {
		evt.enqueueWork(() -> {
			SFCModClient.clientInit();
		});
	}

	public static void onRegisterLayers(final EntityRenderersEvent.RegisterLayerDefinitions evt) {
		SFCModClient.registerLayers(evt::registerLayerDefinition);
	}

}
