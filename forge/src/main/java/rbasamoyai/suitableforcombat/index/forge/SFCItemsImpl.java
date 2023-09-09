package rbasamoyai.suitableforcombat.index.forge;

import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;

public class SFCItemsImpl {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registry.ITEM_REGISTRY, SuitableForCombatMod.MOD_ID);

	public static <T extends Item> Supplier<T> register(String id, Supplier<T> sup) {
		return ITEMS.register(id, sup);
	}

}
