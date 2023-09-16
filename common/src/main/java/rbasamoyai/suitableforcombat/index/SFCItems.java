package rbasamoyai.suitableforcombat.index;

import java.util.function.Supplier;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;
import rbasamoyai.suitableforcombat.content.SFCArmorMaterials;
import rbasamoyai.suitableforcombat.content.items.SFCDyeableArmorItem;

public class SFCItems {

	public static final Supplier<ArmorItem>
		CAVALRY_POT_HELMET = register("cavalry_pot_helmet", () -> new ArmorItem(ArmorMaterials.IRON, EquipmentSlot.HEAD, new Item.Properties()
			.tab(SuitableForCombatMod.GROUP))),
		PITH_HELMET = register("pith_helmet", () -> new ArmorItem(SFCArmorMaterials.WOOD, EquipmentSlot.HEAD, new Item.Properties()
			.tab(SuitableForCombatMod.GROUP))),
		SHAKO = register("shako", () -> new ArmorItem(SFCArmorMaterials.WOOL, EquipmentSlot.HEAD, new Item.Properties()
			.tab(SuitableForCombatMod.GROUP)));

	public static final Supplier<SFCDyeableArmorItem>
		DRAGOON_HELMET = register("dragoon_helmet", () -> new SFCDyeableArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Item.Properties()
			.tab(SuitableForCombatMod.GROUP), 1710619)),
		KEPI = register("kepi", () -> new SFCDyeableArmorItem(SFCArmorMaterials.WOOL, EquipmentSlot.HEAD, new Item.Properties()
			.tab(SuitableForCombatMod.GROUP), 16777215)),
		PICKELHAUBE = register("pickelhaube", () -> new SFCDyeableArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Item.Properties()
			.tab(SuitableForCombatMod.GROUP), 1710619));

	public static void register() {}

	@ExpectPlatform public static <T extends Item> Supplier<T> register(String id, Supplier<T> sup) { throw new AssertionError(); }

}
