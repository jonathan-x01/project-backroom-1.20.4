package projectbackroom.jonathanx.mixin.client;

import net.minecraft.client.gui.RotatingCubeMapRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RotatingCubeMapRenderer.class)
public interface RotatingCubeMapRendererAccessor {
    @Accessor("pitch")
    float getPitch();

    @Accessor("pitch")
    void setPitch(float pitch);
}