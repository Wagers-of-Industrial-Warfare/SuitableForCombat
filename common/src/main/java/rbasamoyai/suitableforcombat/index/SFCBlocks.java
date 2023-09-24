package rbasamoyai.suitableforcombat.index;

import java.util.function.Supplier;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import rbasamoyai.suitableforcombat.content.attaching.UniformDecoratingTableBlock;

public class SFCBlocks {

	public static final Supplier<UniformDecoratingTableBlock> UNIFORM_DECORATING_TABLE = register("uniform_decorating_table",
		() -> new UniformDecoratingTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));

	public static void register() {}

	@ExpectPlatform public static <T extends Block> Supplier<T> register(String id, Supplier<T> sup) { throw new AssertionError(); }

}
