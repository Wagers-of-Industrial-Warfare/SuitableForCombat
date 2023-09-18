package rbasamoyai.suitableforcombat.network.forge;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import rbasamoyai.suitableforcombat.network.RootPacket;
import rbasamoyai.suitableforcombat.network.SFCNetwork;

public class ForgeClientboundPacket {

	private final RootPacket pkt;

	public ForgeClientboundPacket(RootPacket pkt) { this.pkt = pkt; }

	public ForgeClientboundPacket(FriendlyByteBuf buf) {
		this.pkt = SFCNetwork.constructPacket(buf, buf.readVarInt());
	}

	public void encode(FriendlyByteBuf buf) {
		SFCNetwork.writeToBuf(this.pkt, buf);
	}

	public void handle(Supplier<NetworkEvent.Context> sup) {
		NetworkEvent.Context ctx = sup.get();
		ctx.enqueueWork(() -> {
			this.pkt.handle(null, ctx.getNetworkManager().getPacketListener(), null);
		});
		ctx.setPacketHandled(true);
	}


}
