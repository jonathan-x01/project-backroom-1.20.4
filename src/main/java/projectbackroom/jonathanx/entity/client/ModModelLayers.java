package projectbackroom.jonathanx.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;

public class ModModelLayers {
    public static final EntityModelLayer FACELING =
            new EntityModelLayer(new Identifier(ProjectBackroom.MOD_ID, "faceling"),"main");
}
