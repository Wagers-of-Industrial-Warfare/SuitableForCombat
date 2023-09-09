package rbasamoyai.suitableforcombat;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import rbasamoyai.suitableforcombat.content.CustomHumanoidArmorRenderer;

import javax.annotation.Nullable;

public class SFCModClient {

	private static final Map<Item, CustomHumanoidArmorRenderer> ARMOR_MODEL_PROVIDERS = new HashMap<>();

	public static void clientInit() {

	}

	public static void registerArmorRenderer(Item item, CustomHumanoidArmorRenderer renderer) {
		if (ARMOR_MODEL_PROVIDERS.containsKey(item)) {
			throw new IllegalStateException("Item %s already has a registered custom armor renderer".formatted(Registry.ITEM.getKey(item)));
		}
		ARMOR_MODEL_PROVIDERS.put(item, renderer);
	}

	@Nullable public static CustomHumanoidArmorRenderer getRenderer(Item item) { return ARMOR_MODEL_PROVIDERS.get(item); }

}
