package projectbackroom.jonathanx.render.entities;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.render.entities.models.FacelingModel;
import projectbackroom.jonathanx.ModModelLayers;
import projectbackroom.jonathanx.entity.neutral.FacelingEntity;
import projectbackroom.jonathanx.render.entities.state.BackroomEntityRenderState;

public class FacelingRenderer extends MobEntityRenderer<FacelingEntity, BackroomEntityRenderState, FacelingModel> {
    public static final Identifier TEXTURE = ProjectBackroom.id("textures/entity/faceling.png");

    public FacelingRenderer(EntityRendererFactory.Context context) {
        super(context, new FacelingModel(context.getPart(ModModelLayers.FACELING)), 0.5f);
    }

    @Override
    public void render(BackroomEntityRenderState livingEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (livingEntityRenderState.baby){
            matrixStack.scale(0.5f,0.5f,0.5f);
        } else {
            matrixStack.scale(1f,1f,1f);
        }
        super.render(livingEntityRenderState, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public BackroomEntityRenderState createRenderState() {
        return new BackroomEntityRenderState();
    }

    @Override
    public Identifier getTexture(BackroomEntityRenderState state) {
        return TEXTURE;
    }
}
