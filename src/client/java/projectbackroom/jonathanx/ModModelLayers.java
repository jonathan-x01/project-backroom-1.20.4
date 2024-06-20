package projectbackroom.jonathanx;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.registry.ModdedEntities;

@Environment(EnvType.CLIENT)
public class ModModelLayers implements ClientModInitializer {
    public static final EntityModelLayer FACELING =
            new EntityModelLayer(new Identifier(ProjectBackroom.MOD_ID, "faceling"),"main");

    public static final EntityModelLayer SMILER =
            new EntityModelLayer(new Identifier(ProjectBackroom.MOD_ID, "smiler"),"main");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModdedEntities.FACELINGS, projectbackroom.jonathanx.rendering.entities.FacelingRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.FACELING, projectbackroom.jonathanx.rendering.entities.models.FacelingModel::getTexturedModelData);

        EntityRendererRegistry.register(ModdedEntities.SMILERS, projectbackroom.jonathanx.rendering.entities.SmilerRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SMILER, projectbackroom.jonathanx.rendering.entities.models.SmilerModel::getTexturedModelData);
    }
}
