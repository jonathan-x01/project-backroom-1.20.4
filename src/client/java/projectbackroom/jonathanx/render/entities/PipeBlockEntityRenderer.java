package projectbackroom.jonathanx.render.entities;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.blocks.entity.PipeBlockEntity;

public class PipeBlockEntityRenderer implements BlockEntityRenderer<PipeBlockEntity> {

    private final BakedModel largePipeModel;

    public PipeBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        // Load the model from your mod resources
        MinecraftClient mc = MinecraftClient.getInstance();
        this.largePipeModel = mc.getBakedModelManager().getModel(ProjectBackroom.id("blocks/large_pipe_1"));
        ProjectBackroom.debug(
                ProjectBackroom.id("blocks/large_pipe_1"),
                this.largePipeModel
        );
    }

    @Override
    public void render(PipeBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        //PipeType variety = entity.getPipeVariety();
        ProjectBackroom.debug(this.largePipeModel);
        renderModel(this.largePipeModel, matrices, vertexConsumers, light, overlay);
    }

    private void renderModel(BakedModel model, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ProjectBackroom.debug(model);
        MinecraftClient mc = MinecraftClient.getInstance();
        // Get the vertex consumer for solid render layer (or whichever layer your model uses)
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getSolid());

        // Render all quads of the model facing all directions
        for (Direction direction : Direction.values()) {
            for (BakedQuad quad : model.getQuads(null, direction, Random.create())) {
                vertexConsumer.quad(matrices.peek(), quad, 1f, 1f, 1f, light, overlay);
            }
        }
        // Also render quads without specific direction
        for (BakedQuad quad : model.getQuads(null, null, Random.create())) {
            vertexConsumer.quad(matrices.peek(), quad, 1f, 1f, 1f, light, overlay);
        }
    }
}
