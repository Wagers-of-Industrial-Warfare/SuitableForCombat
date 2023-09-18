package rbasamoyai.suitableforcombat.network.fabric;

import me.pepperbell.simplenetworking.S2CPacket;
import me.pepperbell.simplenetworking.SimpleChannel;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import rbasamoyai.suitableforcombat.network.RootPacket;
import rbasamoyai.suitableforcombat.network.SFCNetwork;

public class FabricClientboundPacket implements S2CPacket {

	private final RootPacket pkt;

	public FabricClientboundPacket(RootPacket pkt) { this.pkt = pkt; }

	public FabricClientboundPacket(FriendlyByteBuf buf) {
		this.pkt = SFCNetwork.constructPacket(buf, buf.readVarInt());
	}

	public void encode(FriendlyByteBuf buf) {
		SFCNetwork.writeToBuf(this.pkt, buf);
	}

	@Override
	public void handle(Minecraft client, ClientPacketListener listener, PacketSender responseSender, SimpleChannel channel) {
		client.execute(() -> this.pkt.handle(client, listener, null));
	}

}
