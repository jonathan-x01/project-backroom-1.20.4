package projectbackroom.jonathanx;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import projectbackroom.jonathanx.entity.ModdedEntities;
import projectbackroom.jonathanx.entity.client.FacelingModel;
import projectbackroom.jonathanx.entity.client.FacelingRenderer;
import projectbackroom.jonathanx.entity.client.ModModelLayers;

public class ProjectBackroomClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		EntityRendererRegistry.register(ModdedEntities.FACELINGS, FacelingRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.FACELING, FacelingModel::getTexturedModelData);
	}
}