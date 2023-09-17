package rbasamoyai.suitableforcombat.fabric;

import java.util.Map;

import io.github.fabricators_of_create.porting_lib.event.client.ModelsBakedCallback;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import rbasamoyai.suitableforcombat.SFCModClient;
import rbasamoyai.suitableforcombat.content.PartialModel;

public class SFCModClientFabric implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		SFCModClient.clientInit();
		SFCModClient.registerLayers((loc, sup) -> EntityModelLayerRegistry.registerModelLayer(loc, sup::get));

		ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, cons) -> PartialModel.onModelRegistry(cons));
		ModelsBakedCallback.EVENT.register(SFCModClientFabric::onModelsBaked);

		SFCModClient.registerItemColor(ColorProviderRegistry.ITEM::register);
	}
	public static void onModelsBaked(ModelManager manager, Map<ResourceLocation, BakedModel> models, ModelBakery loader) {
		PartialModel.onModelBake(models);
	}

}
