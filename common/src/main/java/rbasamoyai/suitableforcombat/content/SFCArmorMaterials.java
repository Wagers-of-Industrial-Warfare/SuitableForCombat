package rbasamoyai.suitableforcombat.content;

import java.util.function.Supplier;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public enum SFCArmorMaterials implements ArmorMaterial {

	WOOD("wood", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, () -> Ingredient.of(ItemTags.PLANKS));

	private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
	private final String name;
	private final int durabilityMultiplier;
	private final int[] slotProtections;
	private final int enchantmentValue;
	private final SoundEvent sound;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyLoadedValue<Ingredient> repairIngredient;

	SFCArmorMaterials(
		String name,
		int durabilityMultiplier,
		int[] slotProtections,
		int enchantmentValue,
		SoundEvent sound,
		float toughness,
		float knockbackResistance,
		Supplier<Ingredient> repairIngredient
	) {
		this.name = name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.slotProtections = slotProtections;
		this.enchantmentValue = enchantmentValue;
		this.sound = sound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
	}

	@Override public int getDurabilityForSlot(EquipmentSlot slot) { return HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier; }
	@Override public int getDefenseForSlot(EquipmentSlot slot) { return this.slotProtections[slot.getIndex()]; }
	@Override public int getEnchantmentValue() { return this.enchantmentValue; }
	@Override public SoundEvent getEquipSound() { return this.sound; }
	@Override public Ingredient getRepairIngredient() { return this.repairIngredient.get(); }
	@Override public String getName() { return this.name; }
	@Override public float getToughness() { return this.toughness; }
	@Override public float getKnockbackResistance() { return this.knockbackResistance; }

}
