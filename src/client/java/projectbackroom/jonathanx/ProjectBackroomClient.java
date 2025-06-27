package projectbackroom.jonathanx;

import net.fabricmc.api.ClientModInitializer;
import projectbackroom.jonathanx.color.block.ModBlockColors;
import projectbackroom.jonathanx.color.item.ModItemColors;
import projectbackroom.jonathanx.particle.BackroomParticleManager;

public class ProjectBackroomClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BackroomParticleManager.registerDefaultFactories();
		ModBlockColors.create();
		ModItemColors.create();
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}