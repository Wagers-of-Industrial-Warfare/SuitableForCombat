package rbasamoyai.suitableforcombat.index;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;
import rbasamoyai.suitableforcombat.content.SFCArmorMaterials;

import java.util.function.Supplier;

public class SFCItems {

	public static final Supplier<ArmorItem>
		PITH_HELMET = register("pith_helmet", () -> new ArmorItem(SFCArmorMaterials.WOOD, EquipmentSlot.HEAD, new Item.Properties()
			.tab(SuitableForCombatMod.GROUP)));

	public static void register() {}

	@ExpectPlatform public static <T extends Item> Supplier<T> register(String id, Supplier<T> sup) { throw new AssertionError(); }

}
