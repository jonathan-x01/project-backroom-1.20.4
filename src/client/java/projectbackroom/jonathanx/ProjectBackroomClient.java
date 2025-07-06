package projectbackroom.jonathanx;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.blocks.BackroomFluidBlock;
import projectbackroom.jonathanx.color.block.BackroomBlockColors;
import projectbackroom.jonathanx.color.item.BackroomItemColors;
import projectbackroom.jonathanx.fluid.BackroomFluids;
import projectbackroom.jonathanx.particle.BackroomParticleManager;
import projectbackroom.jonathanx.registry.BackroomEntities;
import projectbackroom.jonathanx.rendering.entities.RustyTippedArrowRenderer;

import java.util.Iterator;

public class ProjectBackroomClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BackroomParticleManager.registerDefaultFactories();
		BackroomBlockColors.create();
		BackroomItemColors.create();

		EntityRendererRegistry.register(BackroomEntities.RUSTY_TIPPED_ARROW, RustyTippedArrowRenderer::new);

        for (BackroomFluidBlock backroomFluidBlock : BackroomFluidBlock.getAll()) {
			ProjectBackroom.debug(backroomFluidBlock);
            FluidRenderHandlerRegistry.INSTANCE.register(
                    backroomFluidBlock.getFluid().getStill(),
                    backroomFluidBlock.getFluid().getFlowing(),
                    new SimpleFluidRenderHandler(
                            ProjectBackroom.minecraftId("block/water_still"),
                            ProjectBackroom.minecraftId("block/water_flow"),
                            backroomFluidBlock.getColor(0)
                    )
            );
        }

		/*FluidRenderHandlerRegistry.INSTANCE.register(
				BackroomFluids.ALMOND_WATER,
				BackroomFluids.FLOWING_ALMOND_WATER,
				new SimpleFluidRenderHandler(
						new Identifier("minecraft","block/water_still"),
						new Identifier("minecraft","block/water_flow"),
						0xFFECB3
				)
		);*/
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}