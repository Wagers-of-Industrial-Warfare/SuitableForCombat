package rbasamoyai.suitableforcombat;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import rbasamoyai.suitableforcombat.content.BasicHumanoidArmorRenderer;
import rbasamoyai.suitableforcombat.content.CustomHumanoidArmorRenderer;
import rbasamoyai.suitableforcombat.content.items.hats.CavalryPotHelmetModel;
import rbasamoyai.suitableforcombat.content.items.hats.DragoonHelmetModel;
import rbasamoyai.suitableforcombat.content.items.hats.KepiModel;
import rbasamoyai.suitableforcombat.content.items.hats.PickelhaubeModel;
import rbasamoyai.suitableforcombat.content.items.hats.PithHelmetModel;
import rbasamoyai.suitableforcombat.content.items.hats.ShakoModel;
import rbasamoyai.suitableforcombat.index.SFCItems;
import rbasamoyai.suitableforcombat.index.SFCModelLayers;

import javax.annotation.Nullable;

public class SFCModClient {

	private static final Map<Item, CustomHumanoidArmorRenderer> ARMOR_MODEL_PROVIDERS = new HashMap<>();

	public static void clientInit() {
		registerArmorRenderer(SFCItems.CAVALRY_POT_HELMET.get(), new BasicHumanoidArmorRenderer() {
			@Override
			public HumanoidModel<?> getModel(ItemStack itemStack, LivingEntity entity, EquipmentSlot slot) {
				return new CavalryPotHelmetModel(bakeRoot(SFCModelLayers.CAVALRY_POT_HELMET));
			}
			@Override
			public ResourceLocation getArmorResource(LivingEntity entity, ItemStack stack, EquipmentSlot slot, @Nullable String overlay) {
				return SuitableForCombatMod.resource("textures/armor/cavalry_pot_helmet.png");
			}
		});
		registerArmorRenderer(SFCItems.DRAGOON_HELMET.get(), new BasicHumanoidArmorRenderer() {
			@Override
			public HumanoidModel<?> getModel(ItemStack itemStack, LivingEntity entity, EquipmentSlot slot) {
				return new DragoonHelmetModel(bakeRoot(SFCModelLayers.DRAGOON_HELMET));
			}
			@Override
			public ResourceLocation getArmorResource(LivingEntity entity, ItemStack stack, EquipmentSlot slot, @Nullable String overlay) {
				String suf = "";
				if (overlay != null) {
					int modelData = stack.getOrCreateTag().getInt("CustomModelData");
					suf = String.format("_%s%s", overlay, modelData == 0 ? "" : Integer.toString(modelData));
				}
				return SuitableForCombatMod.resource("textures/armor/dragoon_helmet%s.png".formatted(overlay == null ? "" : suf));
			}
		});
		registerArmorRenderer(SFCItems.KEPI.get(), new BasicHumanoidArmorRenderer() {
			@Override
			public HumanoidModel<?> getModel(ItemStack itemStack, LivingEntity entity, EquipmentSlot slot) {
				return new PithHelmetModel(bakeRoot(SFCModelLayers.KEPI));
			}
			@Override
			public ResourceLocation getArmorResource(LivingEntity entity, ItemStack stack, EquipmentSlot slot, @Nullable String overlay) {
				return SuitableForCombatMod.resource("textures/armor/kepi%s.png".formatted(overlay == null ? "" : "_" + overlay));
			}
		});
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
		registerArmorRenderer(SFCItems.PICKELHAUBE.get(), new BasicHumanoidArmorRenderer() {
			@Override
			public HumanoidModel<?> getModel(ItemStack itemStack, LivingEntity entity, EquipmentSlot slot) {
				return new PickelhaubeModel(bakeRoot(SFCModelLayers.PICKELHAUBE));
			}
			@Override
			public ResourceLocation getArmorResource(LivingEntity entity, ItemStack stack, EquipmentSlot slot, @Nullable String overlay) {
				String suf = "";
				if (overlay != null) {
					int modelData = stack.getOrCreateTag().getInt("CustomModelData");
					suf = String.format("_%s%s", overlay, modelData == 0 ? "" : Integer.toString(modelData));
				}
				return SuitableForCombatMod.resource("textures/armor/pickelhaube%s.png".formatted(overlay == null ? "" : suf));
			}
		});
		registerArmorRenderer(SFCItems.SHAKO.get(), new BasicHumanoidArmorRenderer() {
			@Override
			public HumanoidModel<?> getModel(ItemStack itemStack, LivingEntity entity, EquipmentSlot slot) {
				return new ShakoModel(bakeRoot(SFCModelLayers.SHAKO));
			}

			@Override
			public ResourceLocation getArmorResource(LivingEntity entity, ItemStack stack, EquipmentSlot slot, @org.jetbrains.annotations.Nullable String overlay) {
				return SuitableForCombatMod.resource("textures/armor/shako.png");
			}
		});
	}

	public static void registerItemColor(BiConsumer<ItemColor, Item> cons) {
		cons.accept(SFCModClient::simpleColor, SFCItems.DRAGOON_HELMET.get());
		cons.accept(SFCModClient::simpleColor, SFCItems.KEPI.get());
		cons.accept(SFCModClient::simpleColor, SFCItems.PICKELHAUBE.get());
	}

	private static int simpleColor(ItemStack stack, int layer) {
		return layer > 0 ? -1 : ((DyeableArmorItem) stack.getItem()).getColor(stack);
	}

	public static void registerLayers(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> cons) {
		cons.accept(SFCModelLayers.CAVALRY_POT_HELMET, CavalryPotHelmetModel::createLayer);
		cons.accept(SFCModelLayers.DRAGOON_HELMET, DragoonHelmetModel::createLayer);
		cons.accept(SFCModelLayers.KEPI, KepiModel::createLayer);
		cons.accept(SFCModelLayers.PICKELHAUBE, PickelhaubeModel::createLayer);
		cons.accept(SFCModelLayers.PITH_HELMET, PithHelmetModel::createLayer);
		cons.accept(SFCModelLayers.SHAKO, ShakoModel::createLayer);
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
