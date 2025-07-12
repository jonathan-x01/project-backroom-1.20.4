package projectbackroom.jonathanx.render.entities;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import projectbackroom.jonathanx.ModModelLayers;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.entity.hostile.SmilerEntity;
import projectbackroom.jonathanx.render.entities.models.SmilerModel;

public class SmilerRenderer extends MobEntityRenderer<SmilerEntity, SmilerModel<SmilerEntity>> {
    public static final Identifier TEXTURE = new Identifier(ProjectBackroom.MOD_ID,"textures/entity/smiler_face.png");

    public SmilerRenderer(EntityRendererFactory.Context context) {
        super(context, new SmilerModel<>(context.getPart(ModModelLayers.SMILER)), 0.5f);
    }

    @Override
    public Identifier getTexture(SmilerEntity entity) {
        return TEXTURE;
    }

    @Override
    protected int getBlockLight(SmilerEntity entity, BlockPos pos) {
        return 15;
    }
}
