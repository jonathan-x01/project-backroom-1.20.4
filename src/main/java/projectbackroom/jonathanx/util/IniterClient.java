package projectbackroom.jonathanx.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

import java.util.Comparator;

public interface IniterClient {
    default void init() {
        DebugLogger.displayRegisteredSectors(EnvType.CLIENT, this.getClass());
    }

    default int order(){return 0;}

    static void initialise(){
        FabricLoader.getInstance().getEntrypoints("IniterClient", IniterClient.class)
                .stream()
                .sorted(Comparator.comparingInt(IniterClient::order))
                .forEach(IniterClient::init);
    }
}
