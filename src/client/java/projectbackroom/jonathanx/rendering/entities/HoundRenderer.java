package projectbackroom.jonathanx.rendering.entities;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ModModelLayers;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.entity.custom.HoundEntity;
import projectbackroom.jonathanx.rendering.entities.models.HoundModel;

public class HoundRenderer extends MobEntityRenderer<HoundEntity, HoundModel<HoundEntity>> {

    public static final Identifier TEXTURE = new Identifier(ProjectBackroom.MOD_ID,"textures/entity/hound.png");

    public HoundRenderer(EntityRendererFactory.Context context) {
        super(context, new HoundModel<>(context.getPart(ModModelLayers.HOUND)), 0.5f);
    }

    @Override
    public Identifier getTexture(HoundEntity entity) {
        return TEXTURE;
    }
}