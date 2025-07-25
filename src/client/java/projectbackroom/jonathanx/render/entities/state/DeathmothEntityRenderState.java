package projectbackroom.jonathanx.render.entities.state;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public class DeathmothEntityRenderState extends BackroomEntityRenderState {
    public boolean attacking;
}
