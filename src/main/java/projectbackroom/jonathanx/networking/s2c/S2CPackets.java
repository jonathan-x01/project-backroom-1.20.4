package projectbackroom.jonathanx.networking.s2c;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.packet.CustomPayload;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.util.Initer;

public class S2CPackets implements Initer {

    @Override
    public void init() {
        Initer.super.init();
        PayloadTypeRegistry.playC2S().register(MissingPosterS2CPayload.ID, MissingPosterS2CPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(MissingPosterS2CPayload.ID, MissingPosterS2CPayload::receive);
    }
}
