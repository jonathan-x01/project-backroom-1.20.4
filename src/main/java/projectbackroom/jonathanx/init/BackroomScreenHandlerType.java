package projectbackroom.jonathanx.init;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import projectbackroom.jonathanx.screen.ConspiracyTableScreenHandler;

public class BackroomScreenHandlerType {
    public static final ScreenHandlerType<ConspiracyTableScreenHandler> CONSPIRACY_TABLE = register("conspiracy_table", ConspiracyTableScreenHandler::new);

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, id, new ScreenHandlerType<>(factory, FeatureFlags.VANILLA_FEATURES));
    }
}
