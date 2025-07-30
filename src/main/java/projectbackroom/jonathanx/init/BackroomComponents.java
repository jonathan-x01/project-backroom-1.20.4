package projectbackroom.jonathanx.init;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.util.Initer;

public class BackroomComponents implements Initer {
    public static final ComponentType<String> MISSING_POSTER_PLAYER_UUID = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            ProjectBackroom.id("missing_poster_player_uuid"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );

    public static final ComponentType<String> MISSING_POSTER_PLAYER_NAME = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            ProjectBackroom.id("missing_poster_player_name"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );
}
