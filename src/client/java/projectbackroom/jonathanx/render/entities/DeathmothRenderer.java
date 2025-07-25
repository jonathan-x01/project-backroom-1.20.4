package projectbackroom.jonathanx.render.entities;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ModModelLayers;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.entity.hostile.DeathmothEntity;
import projectbackroom.jonathanx.render.entities.models.DeathmothModel;
import projectbackroom.jonathanx.render.entities.state.DeathmothEntityRenderState;

public class DeathmothRenderer extends MobEntityRenderer<DeathmothEntity, DeathmothEntityRenderState, DeathmothModel> {
    // 0 = male
    // 1 = female
    public static final int gender = 1;
    public static final Identifier TEXTURE = ProjectBackroom.id("textures/entity/deathmoth.png");
    public DeathmothRenderer(EntityRendererFactory.Context context) {
        super(context, new DeathmothModel(context.getPart(ModModelLayers.DEATHMOTH)), 0.5f);
    }

    @Override
    public DeathmothEntityRenderState createRenderState() {
        return new DeathmothEntityRenderState();
    }

    @Override
    protected void scale(DeathmothEntityRenderState state, MatrixStack matrices) {
        float scale;
        if (gender == 0){
            scale = 0.3f;
        } else {
            scale = 3.0f;
        }
        matrices.scale(scale, scale, scale);
        super.scale(state, matrices);
    }

    @Override
    public Identifier getTexture(DeathmothEntityRenderState state) {
        return TEXTURE;
    }
}
