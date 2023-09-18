package rbasamoyai.suitableforcombat.content.items.hats;

import java.util.EnumSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import rbasamoyai.suitableforcombat.content.items.ornaments.OrnamentType;
import rbasamoyai.suitableforcombat.content.items.ornaments.SupportsOrnaments;
import rbasamoyai.suitableforcombat.index.SFCItems;

public class ShakoItem extends ArmorItem implements SupportsOrnaments {

	public ShakoItem(ArmorMaterial material, Properties properties) {
		super(material, EquipmentSlot.HEAD, properties);
	}

	@Override public Set<? extends OrnamentType> ornamentTypes() { return Ornament.SET; }

	@Override
	public boolean supportsOrnament(ItemStack armor, OrnamentType type, ItemStack ornament) {
		if (type == Ornament.UPPER_BAND || type == Ornament.LOWER_BAND || type == Ornament.MIDDLE_BANDS || type == Ornament.VISOR) {
			return ornament.is(SFCItems.HAT_BAND.get()) || ornament.is(SFCItems.GILDING.get());
		}
		if (type == Ornament.CORD) {
			return ornament.is(SFCItems.CORD.get());
		}
		return false;
	}

	public enum Ornament implements OrnamentType {
		UPPER_BAND,
		LOWER_BAND,
		MIDDLE_BANDS,
		VISOR,
		PLATE,
		STRAP,
		ROUNDEL,
		PLUME,
		CORD;

		public static final Set<Ornament> SET = ImmutableSet.copyOf(EnumSet.allOf(Ornament.class));

		private final String id = this.name().toLowerCase();

		@Override public String getSerializedName() { return this.id; }
	}

}
