package projectbackroom.jonathanx.render.entities;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.apache.http.annotation.Obsolete;
import projectbackroom.jonathanx.ModModelLayers;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.entity.hostile.SmilerEntity;
import projectbackroom.jonathanx.render.entities.models.SmilerModel;
import projectbackroom.jonathanx.render.entities.state.BackroomEntityRenderState;

public class SmilerRenderer extends MobEntityRenderer<SmilerEntity, BackroomEntityRenderState, SmilerModel> {
    public static final Identifier TEXTURE = ProjectBackroom.id("textures/entity/smiler_face.png");

    public SmilerRenderer(EntityRendererFactory.Context context) {
        super(context, new SmilerModel(context.getPart(ModModelLayers.SMILER)), 0.5f);
    }

    @Override
    public BackroomEntityRenderState createRenderState() {
        return new BackroomEntityRenderState();
    }

    @Obsolete
    protected int getBlockLight(SmilerEntity entity, BlockPos pos) {
        return 15;
    }

    @Override
    public Identifier getTexture(BackroomEntityRenderState state) {
        return TEXTURE;
    }
}
