package rbasamoyai.suitableforcombat;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import rbasamoyai.suitableforcombat.content.BasicHumanoidArmorRenderer;
import rbasamoyai.suitableforcombat.content.CustomHumanoidArmorRenderer;
import rbasamoyai.suitableforcombat.content.items.hats.PithHelmetModel;
import rbasamoyai.suitableforcombat.index.SFCItems;
import rbasamoyai.suitableforcombat.index.SFCModelLayers;

import javax.annotation.Nullable;

public class SFCModClient {

	private static final Map<Item, CustomHumanoidArmorRenderer> ARMOR_MODEL_PROVIDERS = new HashMap<>();

	public static void clientInit() {
		registerArmorRenderer(SFCItems.PITH_HELMET.get(), new BasicHumanoidArmorRenderer() {
			@Override
			public HumanoidModel<?> getModel(ItemStack itemStack, LivingEntity entity, EquipmentSlot slot) {
				return new PithHelmetModel(bakeRoot(SFCModelLayers.PITH_HELMET));
			}
			@Override
			public ResourceLocation getArmorResource(LivingEntity entity, ItemStack stack, EquipmentSlot slot, @Nullable String overlay) {
				return SuitableForCombatMod.resource("textures/armor/pith_helmet.png");
			}
		});
	}

	public static void registerLayers(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> cons) {
		cons.accept(SFCModelLayers.PITH_HELMET, PithHelmetModel::createLayer);
	}

	public static void registerArmorRenderer(Item item, CustomHumanoidArmorRenderer renderer) {
		if (ARMOR_MODEL_PROVIDERS.containsKey(item)) {
			throw new IllegalStateException("Item %s already has a registered custom armor renderer".formatted(Registry.ITEM.getKey(item)));
		}
		ARMOR_MODEL_PROVIDERS.put(item, renderer);
	}

	public static ModelPart bakeRoot(ModelLayerLocation loc) {
		return Minecraft.getInstance().getEntityModels().bakeLayer(loc);
	}

	@Nullable public static CustomHumanoidArmorRenderer getRenderer(Item item) { return ARMOR_MODEL_PROVIDERS.get(item); }

}
