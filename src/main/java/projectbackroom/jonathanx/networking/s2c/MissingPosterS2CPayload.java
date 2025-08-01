package projectbackroom.jonathanx.networking.s2c;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.init.BackroomComponents;
import projectbackroom.jonathanx.init.BackroomItems;

import java.util.UUID;

public record MissingPosterS2CPayload(UUID uuid, String name) implements CustomPayload {
    public static final Identifier SYNC_POSTER_NAME_PACKET = ProjectBackroom.id("sync_poster_name");
    public static final CustomPayload.Id<MissingPosterS2CPayload> ID = new CustomPayload.Id<>(SYNC_POSTER_NAME_PACKET);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static final PacketCodec<PacketByteBuf, MissingPosterS2CPayload> CODEC =
            PacketCodec.of(
                    (payload, buf) -> { // write
                        buf.writeUuid(payload.uuid());
                        buf.writeString(payload.name());
                    },
                    buf -> new MissingPosterS2CPayload(buf.readUuid(), buf.readString()) // read
            );

    public static void receive(MissingPosterS2CPayload payload, ServerPlayNetworking.Context context) {
        ServerPlayerEntity player = context.player();
        context.server().execute(() -> {
            ItemStack stack = player.getMainHandStack();
            if (stack.isOf(BackroomItems.MISSING_POSTER)) {
                String storedUuid = stack.get(BackroomComponents.MISSING_POSTER_PLAYER_UUID);
                if (storedUuid != null && storedUuid.equals(payload.uuid().toString())) {
                    stack.set(BackroomComponents.MISSING_POSTER_PLAYER_NAME, payload.name());
                }
            }
        });
    }
}
