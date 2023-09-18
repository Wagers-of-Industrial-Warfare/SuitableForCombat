package rbasamoyai.suitableforcombat.network.forge;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import rbasamoyai.suitableforcombat.network.RootPacket;
import rbasamoyai.suitableforcombat.network.SFCNetwork;

public class ForgeServerboundPacket {

	private final RootPacket pkt;

	public ForgeServerboundPacket(RootPacket pkt) { this.pkt = pkt; }

	public ForgeServerboundPacket(FriendlyByteBuf buf) {
		this.pkt = SFCNetwork.constructPacket(buf, buf.readVarInt());
	}

	public void encode(FriendlyByteBuf buf) {
		SFCNetwork.writeToBuf(this.pkt, buf);
	}

	public void handle(Supplier<NetworkEvent.Context> sup) {
		NetworkEvent.Context ctx = sup.get();
		ctx.enqueueWork(() -> {
			ServerPlayer sender = ctx.getSender();
			this.pkt.handle(sender.getServer(), ctx.getNetworkManager().getPacketListener(), sender);
		});
		ctx.setPacketHandled(true);
	}

}
