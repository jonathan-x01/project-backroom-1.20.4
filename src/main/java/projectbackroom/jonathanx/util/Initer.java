package projectbackroom.jonathanx.util;

import net.fabricmc.loader.api.FabricLoader;
import projectbackroom.jonathanx.ProjectBackroom;

import java.util.Comparator;
import java.util.List;

public interface Initer {
    default void init() {
        DebugLogger.displayRegisteredSectors(this.getClass());
    }

    default int order(){return 0;}

    static void initialise(){
        FabricLoader.getInstance().getEntrypoints("Initer", Initer.class)
                .stream()
                .sorted(Comparator.comparingInt(Initer::order))
                .forEach(Initer::init);
    }
}
