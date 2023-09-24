package rbasamoyai.suitableforcombat.forge;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import rbasamoyai.suitableforcombat.SFCModClient.ScreenConstructor;

public class SFCModClientImpl {

	public static <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void registerMenuScreen(MenuType<? extends M> type, ScreenConstructor<M, U> factory) {
		MenuScreens.register(type, factory::create);
	}

}
