package rbasamoyai.suitableforcombat.content.items.ornaments;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class DyeableOrnamentItem extends Item implements DyeableLeatherItem {

	public DyeableOrnamentItem(Properties properties) {
		super(properties);
	}

	@Override
	public int getColor(ItemStack stack) {
		CompoundTag compoundTag = stack.getTagElement("display");
		return compoundTag != null && compoundTag.contains("color", 99) ? compoundTag.getInt("color") : 16777215;
	}

}
