package projectbackroom.jonathanx.networking.s2c;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.screen.ConspiracyTableScreenHandler;
import projectbackroom.jonathanx.util.DebugLogger;

public record ConspiracyTableS2CPayload(String name) implements CustomPayload {
    public static final Identifier TEXT_UPDATE_PACKET_ID = ProjectBackroom.id("text_update_packet");
    public static final CustomPayload.Id<ConspiracyTableS2CPayload> ID = new CustomPayload.Id<>(TEXT_UPDATE_PACKET_ID);

    public static final PacketCodec<PacketByteBuf, ConspiracyTableS2CPayload> CODEC =
            PacketCodec.of(
                    (payload, buf) -> buf.writeString(payload.name()),
                    buf -> new ConspiracyTableS2CPayload(buf.readString())
            );

    public static void receive(ConspiracyTableS2CPayload payload, ServerPlayNetworking.Context context){
        context.server().execute(() -> {
            if (context.player().currentScreenHandler instanceof ConspiracyTableScreenHandler handler){
                handler.setClientText(payload.name());
            }
        });
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
