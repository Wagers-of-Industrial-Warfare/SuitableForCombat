package rbasamoyai.suitableforcombat.network;

import java.util.concurrent.Executor;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketListener;
import net.minecraft.server.level.ServerPlayer;

public interface RootPacket {

	void rootEncode(FriendlyByteBuf buf);
	void handle(Executor exec, PacketListener listener, ServerPlayer sender);

}
