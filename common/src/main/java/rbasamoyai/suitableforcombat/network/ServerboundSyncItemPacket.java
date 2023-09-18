package rbasamoyai.suitableforcombat.network;

import java.util.concurrent.Executor;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketListener;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public record ServerboundSyncItemPacket(ItemStack stack) implements RootPacket {

	public ServerboundSyncItemPacket(FriendlyByteBuf buf) {
		this(buf.readItem());
	}

	@Override
	public void rootEncode(FriendlyByteBuf buf) {
		buf.writeItem(this.stack);
	}

	@Override
	public void handle(Executor exec, PacketListener listener, ServerPlayer sender) {
		exec.execute(() -> {

		});
	}

}
