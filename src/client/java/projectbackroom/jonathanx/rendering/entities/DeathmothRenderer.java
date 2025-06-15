package projectbackroom.jonathanx.rendering.entities;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ModModelLayers;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.entity.hostile.DeathmothEntity;
import projectbackroom.jonathanx.rendering.entities.models.DeathmothModel;

public class DeathmothRenderer extends MobEntityRenderer<DeathmothEntity, DeathmothModel<DeathmothEntity>> {
    // 0 = male
    // 1 = female
    public static final int gender = 1;
    public static final Identifier TEXTURE = new Identifier(ProjectBackroom.MOD_ID,"textures/entity/deathmoth.png");
    public DeathmothRenderer(EntityRendererFactory.Context context) {
        super(context, new DeathmothModel<>(context.getPart(ModModelLayers.DEATHMOTH)), 0.5f);
    }

    @Override
    public Identifier getTexture(DeathmothEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(DeathmothEntity entity, MatrixStack matrices, float amount) {
        float scale;
        if (gender == 0){
            scale = 0.3f;
        } else {
            scale = 3.0f;
        }
        matrices.scale(scale, scale, scale);
        super.scale(entity, matrices, amount);
    }
}
