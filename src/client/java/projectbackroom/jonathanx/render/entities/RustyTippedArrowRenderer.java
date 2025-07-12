package projectbackroom.jonathanx.render.entities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.entity.projectile.RustyTippedArrowEntity;

@Environment(EnvType.CLIENT)
public class RustyTippedArrowRenderer extends ProjectileEntityRenderer<RustyTippedArrowEntity> {
    public static final Identifier TEXTURE = new Identifier("textures/entity/projectiles/arrow.png");

    public RustyTippedArrowRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(RustyTippedArrowEntity entity) {
        return TEXTURE;
    }
}
