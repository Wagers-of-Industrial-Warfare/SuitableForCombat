package rbasamoyai.suitableforcombat.content.attaching;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import rbasamoyai.suitableforcombat.index.SFCBlocks;
import rbasamoyai.suitableforcombat.index.SFCMenuTypes;

public class UniformDecoratingMenu extends AbstractContainerMenu {

	private final ContainerLevelAccess access;
	private final Slot mainItemSlot;
	private final List<Consumer<ItemStack>> mainStackCallbacks = new ArrayList<>();

	public UniformDecoratingMenu(int id, Inventory inv, FriendlyByteBuf buf) {
		this(id, inv, ContainerLevelAccess.NULL);
	}

	public UniformDecoratingMenu(int containerId, Inventory inv, ContainerLevelAccess access) {
		super(SFCMenuTypes.UNIFORM_DECORATION.get(), containerId);
		this.access = access;

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(inv, i * 9 + j + 9, j * 18 + 8, i * 18 + 198));
			}
		}
		for (int i = 0; i < 9; ++i) {
			this.addSlot(new Slot(inv, i, i * 18 + 8, 256));
		}

		this.mainItemSlot = this.addSlot(new Slot(new SimpleContainer(1), 0, 8, 160) {
			@Override
			public void setChanged() {
				super.setChanged();
				ItemStack stack = this.getItem();
				for (Consumer<ItemStack> cb : UniformDecoratingMenu.this.mainStackCallbacks) {
					cb.accept(stack);
				}
			}
		});
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(this.access, player, SFCBlocks.UNIFORM_DECORATING_TABLE.get());
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		return ItemStack.EMPTY;
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
		if (!(player instanceof ServerPlayer splayer)) return;
		ItemStack mainItem = this.getMainItem();
		if (mainItem.isEmpty()) return;
		if (player.isAlive() && !splayer.hasDisconnected()) {
			player.getInventory().placeItemBackInInventory(mainItem);
		} else {
			player.drop(mainItem, false);
		}
		this.mainItemSlot.set(ItemStack.EMPTY);
	}

	public ItemStack getMainItem() {
		return this.mainItemSlot.getItem();
	}

	public void setMainItem(ItemStack stack) {
		this.mainItemSlot.set(stack);
	}

	public void addCallback(Consumer<ItemStack> cb) {
		this.mainStackCallbacks.add(cb);
	}

}
