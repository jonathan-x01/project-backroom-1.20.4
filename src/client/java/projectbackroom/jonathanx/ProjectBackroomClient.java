package projectbackroom.jonathanx;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import projectbackroom.jonathanx.blocks.entity.BackroomBlockEntities;
import projectbackroom.jonathanx.color.block.ModBlockColors;
import projectbackroom.jonathanx.color.item.BackroomItemColors;
import projectbackroom.jonathanx.particle.BackroomParticleManager;
import projectbackroom.jonathanx.registry.BackroomEntities;
import projectbackroom.jonathanx.rendering.entities.PipeBlockEntityRenderer;
import projectbackroom.jonathanx.rendering.entities.RustyTippedArrowRenderer;

public class ProjectBackroomClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BackroomParticleManager.registerDefaultFactories();
		ModBlockColors.create();
		BackroomItemColors.create();

		EntityRendererRegistry.register(BackroomEntities.RUSTY_TIPPED_ARROW, RustyTippedArrowRenderer::new);
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}