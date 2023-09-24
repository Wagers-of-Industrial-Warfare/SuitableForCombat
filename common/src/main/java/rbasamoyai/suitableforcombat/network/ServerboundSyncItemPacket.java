package rbasamoyai.suitableforcombat.network;

import java.util.concurrent.Executor;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketListener;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import rbasamoyai.suitableforcombat.content.attaching.UniformDecoratingMenu;

public record ServerboundSyncItemPacket(ItemStack mainItem, ItemStack carried) implements RootPacket {

	public ServerboundSyncItemPacket(FriendlyByteBuf buf) {
		this(buf.readItem(), buf.readItem());
	}

	@Override
	public void rootEncode(FriendlyByteBuf buf) {
		buf.writeItem(this.mainItem).writeItem(this.carried);
	}

	@Override
	public void handle(Executor exec, PacketListener listener, ServerPlayer sender) {
		exec.execute(() -> {
			if (sender != null && sender.containerMenu instanceof UniformDecoratingMenu menu) {
				menu.setMainItem(this.mainItem);
				menu.setCarried(this.carried);
			}
		});
	}

}
