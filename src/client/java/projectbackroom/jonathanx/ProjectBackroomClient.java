package projectbackroom.jonathanx;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import projectbackroom.jonathanx.blocks.fluids.BackroomFluidBlock;
import projectbackroom.jonathanx.color.block.BackroomBlockColors;
import projectbackroom.jonathanx.color.item.BackroomItemColors;
import projectbackroom.jonathanx.particle.BackroomParticleManager;
import projectbackroom.jonathanx.registry.BackroomEntities;
import projectbackroom.jonathanx.render.entities.RustyTippedArrowRenderer;

public class ProjectBackroomClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BackroomParticleManager.registerDefaultFactories();
		BackroomBlockColors.create();
		BackroomItemColors.create();

		EntityRendererRegistry.register(BackroomEntities.RUSTY_TIPPED_ARROW, RustyTippedArrowRenderer::new);

        for (BackroomFluidBlock backroomFluidBlock : BackroomFluidBlock.getAll()) {
            FluidRenderHandlerRegistry.INSTANCE.register(
                    backroomFluidBlock.getFluid().getStill(),
                    backroomFluidBlock.getFluid().getFlowing(),
                    new SimpleFluidRenderHandler(
                            SimpleFluidRenderHandler.WATER_STILL,
                            SimpleFluidRenderHandler.WATER_FLOWING,
							SimpleFluidRenderHandler.WATER_OVERLAY,
                            backroomFluidBlock.getColor(0)
                    )
            );
        }

		RenderSystem.setShaderFogStart(0.0f);
		RenderSystem.setShaderFogColor(0.0f,1.0f, 0.2f);
		RenderSystem.setShaderFogEnd(30.0f);
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}