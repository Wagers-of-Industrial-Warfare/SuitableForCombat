package rbasamoyai.suitableforcombat.content.items.ornaments;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;

import java.util.Set;

public interface SupportsOrnaments {

	Set<? extends OrnamentType> ornamentTypes();

	boolean supportsOrnament(ItemStack armor, OrnamentType type, ItemStack ornament);

	default void setOrnament(ItemStack armor, OrnamentType type, ItemStack ornament) {
		CompoundTag tag = armor.getOrCreateTag();
		if (!tag.contains("ArmorOrnaments", Tag.TAG_COMPOUND)) {
			tag.put("ArmorOrnaments", new CompoundTag());
		}
		CompoundTag armorOrnaments = tag.getCompound("ArmorOrnaments");
		armorOrnaments.put(type.getSerializedName(), ornament.save(new CompoundTag()));
		tag.put("ArmorOrnaments", armorOrnaments);
	}

	default ItemStack getOrnament(ItemStack armor, OrnamentType type) {
		CompoundTag tag = armor.getOrCreateTag();
		if (!tag.contains("ArmorOrnaments", Tag.TAG_COMPOUND)) {
			tag.put("ArmorOrnaments", new CompoundTag());
		}
		CompoundTag armorOrnaments = tag.getCompound("ArmorOrnaments");
		return armorOrnaments.contains(type.getSerializedName(), Tag.TAG_COMPOUND)
			? ItemStack.of(armorOrnaments.getCompound(type.getSerializedName())) : ItemStack.EMPTY;
	}

	default boolean isTemplateArmor(ItemStack armor) {
		CompoundTag tag = armor.getOrCreateTag();
		if (!tag.contains("Templated")) {
			tag.putBoolean("Templated", false);
		}
		return tag.getBoolean("Templated");
	}

}
