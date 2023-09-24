package rbasamoyai.suitableforcombat.index.forge;

import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;

public class SFCBlocksImpl {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registry.BLOCK_REGISTRY, SuitableForCombatMod.MOD_ID);

	public static <T extends Block> Supplier<T> register(String id, Supplier<T> sup) {
		return BLOCKS.register(id, sup);
	}

}
