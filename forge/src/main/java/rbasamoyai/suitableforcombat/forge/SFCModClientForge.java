package rbasamoyai.suitableforcombat.forge;

import net.minecraft.client.color.item.ItemColors;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ForgeModelBakery;
import rbasamoyai.suitableforcombat.SFCModClient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import rbasamoyai.suitableforcombat.content.PartialModel;

public class SFCModClientForge {

	public static void clientInit(IEventBus modBus, IEventBus forgeBus) {
		modBus.addListener(SFCModClientForge::onClientSetup);
		modBus.addListener(SFCModClientForge::onRegisterLayers);
		modBus.addListener(SFCModClientForge::onRegisterItemColors);
		modBus.addListener(SFCModClientForge::onRegisterModels);
		modBus.addListener(SFCModClientForge::onBakeModels);
	}

	public static void onClientSetup(final FMLClientSetupEvent evt) {
		evt.enqueueWork(() -> {
			SFCModClient.clientInit();
		});
	}

	public static void onRegisterItemColors(final ColorHandlerEvent.Item evt) {
		ItemColors colors = evt.getItemColors();
		SFCModClient.registerItemColor(colors::register);
	}

	public static void onRegisterLayers(final EntityRenderersEvent.RegisterLayerDefinitions evt) {
		SFCModClient.registerLayers(evt::registerLayerDefinition);
	}

	public static void onRegisterModels(final ModelRegistryEvent evt) {
		PartialModel.onModelRegistry(ForgeModelBakery::addSpecialModel);
	}

	public static void onBakeModels(final ModelBakeEvent evt) {
		PartialModel.onModelBake(evt.getModelRegistry());
	}

}
