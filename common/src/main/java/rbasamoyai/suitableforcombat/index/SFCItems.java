package rbasamoyai.suitableforcombat.index;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class SFCItems {

	public static void register() {}

	@ExpectPlatform public static <T extends Item> Supplier<T> register(String id, Supplier<T> sup) { throw new AssertionError(); }

}
