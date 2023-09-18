package rbasamoyai.suitableforcombat.network.fabric;

import me.pepperbell.simplenetworking.C2SPacket;
import me.pepperbell.simplenetworking.SimpleChannel;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import rbasamoyai.suitableforcombat.network.RootPacket;
import rbasamoyai.suitableforcombat.network.SFCNetwork;

public class FabricServerboundPacket implements C2SPacket {

	private final RootPacket pkt;

	public FabricServerboundPacket(RootPacket pkt) {
		this.pkt = pkt;
	}

	public FabricServerboundPacket(FriendlyByteBuf buf) {
		this.pkt = SFCNetwork.constructPacket(buf, buf.readVarInt());
	}

	@Override
	public void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl listener, PacketSender responseSender, SimpleChannel channel) {
		server.execute(() -> this.pkt.handle(server, listener, player));
	}

	@Override public void encode(FriendlyByteBuf buf) { SFCNetwork.writeToBuf(this.pkt, buf); }

}
