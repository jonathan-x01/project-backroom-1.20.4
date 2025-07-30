package projectbackroom.jonathanx;

import net.fabricmc.api.ClientModInitializer;
import projectbackroom.jonathanx.util.IniterClient;

public class ProjectBackroomClientLeftover implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        IniterClient.initialise();
    }
}
