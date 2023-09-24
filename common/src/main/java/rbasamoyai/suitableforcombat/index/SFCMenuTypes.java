package rbasamoyai.suitableforcombat.index;

import java.util.function.Consumer;
import java.util.function.Supplier;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.inventory.MenuType;
import rbasamoyai.suitableforcombat.content.attaching.UniformDecoratingMenu;

public class SFCMenuTypes {

	public static final Supplier<MenuType<UniformDecoratingMenu>> UNIFORM_DECORATION = register("uniform_decoration", UniformDecoratingMenu::new);

	public static void register() {}

	@ExpectPlatform
	public static <T extends AbstractContainerMenu> Supplier<MenuType<T>> register(String id, ExtendedMenuFactory<T> sup) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static void openMenu(ServerPlayer player, Component title, MenuConstructor prov, Consumer<FriendlyByteBuf> cons) {
		throw new AssertionError();
	}

	public interface ExtendedMenuFactory<T extends AbstractContainerMenu> {
		T create(int id, Inventory inv, FriendlyByteBuf buf);
	}

}
