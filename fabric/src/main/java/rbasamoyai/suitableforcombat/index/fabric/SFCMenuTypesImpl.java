package rbasamoyai.suitableforcombat.index.fabric;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.inventory.MenuType;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;
import rbasamoyai.suitableforcombat.index.SFCMenuTypes.ExtendedMenuFactory;

public class SFCMenuTypesImpl {

	public static <T extends AbstractContainerMenu> Supplier<MenuType<T>> register(String id, ExtendedMenuFactory<T> sup) {
		MenuType<T> type = new ExtendedScreenHandlerType<>(sup::create);
		Registry.register(Registry.MENU, SuitableForCombatMod.resource(id), type);
		return () -> type;
	}

	public static void openMenu(ServerPlayer player, Component title, MenuConstructor prov, Consumer<FriendlyByteBuf> cons) {
		player.openMenu(new ExtendedScreenHandlerFactory() {
			@Override
			public void writeScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf) {
				cons.accept(buf);
			}

			@Override public Component getDisplayName() { return title; }

			@Nullable
			@Override
			public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
				return prov.createMenu(i, inventory, player);
			}
		});
	}

}
