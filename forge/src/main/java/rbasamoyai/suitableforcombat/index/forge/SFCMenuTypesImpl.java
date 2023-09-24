package rbasamoyai.suitableforcombat.index.forge;

import java.util.function.Consumer;
import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.DeferredRegister;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;
import rbasamoyai.suitableforcombat.index.SFCMenuTypes.ExtendedMenuFactory;

public class SFCMenuTypesImpl {

	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registry.MENU_REGISTRY, SuitableForCombatMod.MOD_ID);

	public static <T extends AbstractContainerMenu> Supplier<MenuType<T>> register(String id, ExtendedMenuFactory<T> sup) {
		return MENU_TYPES.register(id, () -> IForgeMenuType.create(sup::create));
	}

	public static void openMenu(ServerPlayer player, Component title, MenuConstructor prov, Consumer<FriendlyByteBuf> cons) {
		NetworkHooks.openGui(player, new SimpleMenuProvider(prov, title), cons);
	}

}
