package projectbackroom.jonathanx.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.entity.custom.FacelingEntity;

public class FacelingRenderer extends MobEntityRenderer<FacelingEntity, FacelingModel<FacelingEntity>> {
    public static final Identifier TEXTURE = new Identifier(ProjectBackroom.MOD_ID,"textures/entity/faceling.png");

    public FacelingRenderer(EntityRendererFactory.Context context) {
        super(context, new FacelingModel<>(context.getPart(ModModelLayers.FACELING)), 1f);
    }

    @Override
    public Identifier getTexture(FacelingEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(FacelingEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if (mobEntity.isBaby()){
            matrixStack.scale(0.5f,0.5f,0.5f);
        } else {
            matrixStack.scale(1f,1f,1f);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
