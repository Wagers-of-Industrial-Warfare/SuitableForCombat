package rbasamoyai.suitableforcombat.content.items.hats;

import java.util.EnumSet;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;
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
		UPPER_BAND(0, 0, 0, 8, 61),
		LOWER_BAND(0, 0, 0, 8, 101),
		MIDDLE_BANDS(0, 0, 0, 8, 81),
		VISOR(0, 0, 0, 69, 154),
		PLATE(0, 0, 0, 150, 71),
		STRAP(0, 0, 0, 89, 154),
		ROUNDEL(0, 0, 0, 69, 20),
		PLUME(0, 0, 0, 89, 20),
		CORD(0, 0, 0, 150, 91);

		public static final Set<Ornament> SET = Sets.immutableEnumSet(EnumSet.allOf(Ornament.class));

		private final String id = this.name().toLowerCase();
		private final Vec3 guiPosition;
		private final int slotX;
		private final int slotY;
		private final String descriptionId = "ornament." + SuitableForCombatMod.MOD_ID + ".shako." + this.id;

		Ornament(double gx, double gy, double gz, int slotX, int slotY) {
			this.guiPosition = new Vec3(gx, gy, gz);
			this.slotX = slotX;
			this.slotY = slotY;
		}

		@Override public String getSerializedName() { return this.id; }
		@Override public Vec3 getGuiPosition() { return this.guiPosition; }
		@Override public int slotX() { return this.slotX; }
		@Override public int slotY() { return this.slotY; }
		@Override public String getDescriptionId() { return this.descriptionId; }
	}

}
