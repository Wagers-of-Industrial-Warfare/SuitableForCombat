package rbasamoyai.suitableforcombat.index;

import java.util.function.Supplier;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;
import rbasamoyai.suitableforcombat.content.SFCArmorMaterials;
import rbasamoyai.suitableforcombat.content.items.SFCDyeableArmorItem;
import rbasamoyai.suitableforcombat.content.items.hats.ShakoItem;
import rbasamoyai.suitableforcombat.content.items.ornaments.DyeableOrnamentItem;

public class SFCItems {

	public static final Supplier<ArmorItem>
		CAVALRY_POT_HELMET = register("cavalry_pot_helmet", () -> new ArmorItem(ArmorMaterials.IRON, EquipmentSlot.HEAD, new Item.Properties()
			.tab(SuitableForCombatMod.GROUP))),
		PITH_HELMET = register("pith_helmet", () -> new ArmorItem(SFCArmorMaterials.WOOD, EquipmentSlot.HEAD, new Item.Properties()
			.tab(SuitableForCombatMod.GROUP)));

	public static final Supplier<ShakoItem> SHAKO = register("shako", () -> new ShakoItem(SFCArmorMaterials.WOOL, new Item.Properties().tab(SuitableForCombatMod.GROUP)));

	public static final Supplier<SFCDyeableArmorItem>
		DRAGOON_HELMET = register("dragoon_helmet", () -> new SFCDyeableArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Item.Properties()
			.tab(SuitableForCombatMod.GROUP), 1710619) {
			@Override
			public void fillItemCategory(CreativeModeTab category, NonNullList<ItemStack> items) {
				super.fillItemCategory(category, items);
				if (!this.allowdedIn(category)) return;
				for (int i = 1; i < 2; ++i) {
					ItemStack add = new ItemStack(this);
					add.getOrCreateTag().putInt("CustomModelData", i);
					items.add(add);
				}
			}
		}),
		KEPI = register("kepi", () -> new SFCDyeableArmorItem(SFCArmorMaterials.WOOL, EquipmentSlot.HEAD, new Item.Properties()
			.tab(SuitableForCombatMod.GROUP), 16777215)),
		PICKELHAUBE = register("pickelhaube", () -> new SFCDyeableArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Item.Properties()
			.tab(SuitableForCombatMod.GROUP), 1710619));

	public static final Supplier<DyeableOrnamentItem>
		CORD = register("cord", () -> new DyeableOrnamentItem(new Item.Properties().tab(SuitableForCombatMod.GROUP))),
		HAT_BAND = register("hat_band", () -> new DyeableOrnamentItem(new Item.Properties().tab(SuitableForCombatMod.GROUP)));

	public static final Supplier<Item>
		GILDING = register("gilding", () -> new Item(new Item.Properties().tab(SuitableForCombatMod.GROUP)));

	public static void register() {}

	@ExpectPlatform public static <T extends Item> Supplier<T> register(String id, Supplier<T> sup) { throw new AssertionError(); }

}
