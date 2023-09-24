package rbasamoyai.suitableforcombat.index.fabric;

import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;

public class SFCBlocksImpl {

	public static <T extends Block> Supplier<T> register(String id, Supplier<T> sup) {
		T block = sup.get();
		Registry.register(Registry.BLOCK, SuitableForCombatMod.resource(id), block);
		return () -> block;
	}

}
