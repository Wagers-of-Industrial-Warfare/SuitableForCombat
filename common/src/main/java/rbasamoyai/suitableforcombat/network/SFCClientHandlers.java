package rbasamoyai.suitableforcombat.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;

public class SFCClientHandlers {

	public static void checkVersion(ClientboundCheckChannelVersionPacket pkt) {
		if (SFCNetwork.VERSION.equals(pkt.serverVersion())) return;
		Minecraft mc = Minecraft.getInstance();
		if (mc.getConnection() != null)
			mc.getConnection().onDisconnect(new TextComponent("Suitable For Combat on the client uses a different network format than the server.")
					.append(" Please use a matching format."));
	}

}
