package projectbackroom.jonathanx.render.entities;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ModModelLayers;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.entity.hostile.HoundEntity;
import projectbackroom.jonathanx.render.entities.models.HoundModel;
import projectbackroom.jonathanx.render.entities.state.BackroomEntityRenderState;

public class HoundRenderer extends MobEntityRenderer<HoundEntity, BackroomEntityRenderState, HoundModel> {

    public static final Identifier TEXTURE = ProjectBackroom.id("textures/entity/hound.png");

    public HoundRenderer(EntityRendererFactory.Context context) {
        super(context, new HoundModel(context.getPart(ModModelLayers.HOUND)), 0.5f);
    }

    @Override
    public BackroomEntityRenderState createRenderState() {
        return new BackroomEntityRenderState();
    }

    @Override
    public Identifier getTexture(BackroomEntityRenderState entity) {
        return TEXTURE;
    }
}