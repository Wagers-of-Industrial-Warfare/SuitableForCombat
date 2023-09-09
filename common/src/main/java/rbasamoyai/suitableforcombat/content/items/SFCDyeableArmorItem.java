package rbasamoyai.suitableforcombat.content.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;

public class SFCDyeableArmorItem extends DyeableArmorItem {

	private final int defaultColor;

	public SFCDyeableArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties properties, int defaultColor) {
		super(material, slot, properties);
		this.defaultColor = defaultColor;
	}

	@Override
	public int getColor(ItemStack stack) {
		CompoundTag display = stack.getTagElement("display");
		return display != null && display.contains("color", 99) ? display.getInt("color") : this.defaultColor;
	}

}
