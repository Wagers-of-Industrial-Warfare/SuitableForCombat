package rbasamoyai.suitableforcombat.content.items.ornaments;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.phys.Vec3;

public interface OrnamentType extends StringRepresentable {
	Vec3 getGuiPosition();
	int slotX();
	int slotY();
	String getDescriptionId();
}
