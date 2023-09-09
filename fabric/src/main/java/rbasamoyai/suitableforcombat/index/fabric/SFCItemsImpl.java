package rbasamoyai.suitableforcombat.index.fabric;

import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;

import java.util.function.Supplier;

public class SFCItemsImpl {

	public static <T extends Item> Supplier<T> register(String id, Supplier<T> sup) {
		Registry.register(Registry.ITEM, SuitableForCombatMod.resource(id), sup.get());
		return sup;
	}

}
