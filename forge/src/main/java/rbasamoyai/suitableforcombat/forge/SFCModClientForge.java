package rbasamoyai.suitableforcombat.forge;

import rbasamoyai.suitableforcombat.SFCModClient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class SFCModClientForge {

	public static void clientInit(IEventBus modBus, IEventBus forgeBus) {
		modBus.addListener(SFCModClientForge::onClientSetup);
	}

	public static void onClientSetup(final FMLClientSetupEvent evt) {
		evt.enqueueWork(() -> {
			SFCModClient.clientInit();
		});
	}

}
