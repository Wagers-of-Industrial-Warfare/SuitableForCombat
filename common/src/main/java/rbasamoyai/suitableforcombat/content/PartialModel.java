package rbasamoyai.suitableforcombat.content;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Copied from Flywheel's PartialModel with some multiloader modifications, credit to Jozufozu
 */
public class PartialModel {

	private static final List<PartialModel> ALL = new ArrayList<>();
	private static boolean tooLate = false;

	protected final ResourceLocation modelLocation;
	protected BakedModel bakedModel;

	public PartialModel(ResourceLocation modelLocation) {
		if (tooLate) throw new RuntimeException("PartialModel '" + modelLocation + "' loaded after ModelRegistryEvent");

		this.modelLocation = modelLocation;
		ALL.add(this);
	}

	public static void onModelRegistry(Consumer<ResourceLocation> cons) {
		for (PartialModel partial : ALL)
			cons.accept(partial.getLocation());

		tooLate = true;
	}

	public static void onModelBake(Map<ResourceLocation, BakedModel> registry) {
		for (PartialModel partial : ALL)
			partial.set(registry.get(partial.getLocation()));
	}

	protected void set(BakedModel bakedModel) {
		this.bakedModel = bakedModel;
	}

	public ResourceLocation getLocation() {
		return this.modelLocation;
	}

	public BakedModel get() {
		return this.bakedModel;
	}

}

