package projectbackroom.jonathanx.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.atomic.AtomicReference;

@Environment(EnvType.CLIENT)
public class MissingPosterRenderState {
    public Identifier backgroundTexture;
    public Identifier skinTexture;
    public String playerUUID;
}
