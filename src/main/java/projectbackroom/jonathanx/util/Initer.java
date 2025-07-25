package projectbackroom.jonathanx.util;

import net.fabricmc.loader.api.FabricLoader;
import projectbackroom.jonathanx.ProjectBackroom;

import java.util.List;

public interface Initer {
    default void init() {}
    static void initialise(){
        FabricLoader.getInstance().getEntrypoints("Initer", Initer.class).forEach(Initer -> {
            Runnable init = Initer::init;
            init.run();
            DebugLogger.displayRegisteredSectors(Initer.getClass());
        });
    }
}
