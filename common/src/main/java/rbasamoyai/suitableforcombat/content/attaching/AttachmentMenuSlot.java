package rbasamoyai.suitableforcombat.content.attaching;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class AttachmentMenuSlot extends Slot {

	public AttachmentMenuSlot(Container container, int slot, int x, int y) {
		super(container, -1, x, y);
	}

	@Override
	public ItemStack getItem() {
		return super.getItem();
	}

	@Override
	public void set(ItemStack stack) {
		super.set(stack);
	}

	@Override
	public ItemStack remove(int amount) {
		return super.remove(amount);
	}

	@Override
	public int getContainerSlot() {
		return super.getContainerSlot();
	}
}
