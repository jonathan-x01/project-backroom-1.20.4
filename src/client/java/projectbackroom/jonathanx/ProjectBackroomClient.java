package projectbackroom.jonathanx;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ModelTransformationMode;
import net.minecraft.server.MinecraftServer;
import projectbackroom.jonathanx.block.fluids.BackroomFluidBlock;
import projectbackroom.jonathanx.color.block.BackroomBlockColors;
import projectbackroom.jonathanx.color.item.BackroomItemColors;
import projectbackroom.jonathanx.gui.screen.ingame.ConspiracyTableScreen;
import projectbackroom.jonathanx.init.BackroomComponents;
import projectbackroom.jonathanx.init.BackroomItems;
import projectbackroom.jonathanx.init.BackroomScreenHandlerType;
import projectbackroom.jonathanx.particle.BackroomParticleManager;
import projectbackroom.jonathanx.init.BackroomEntities;
import projectbackroom.jonathanx.render.MissingPosterRenderState;
import projectbackroom.jonathanx.render.MissingPosterRenderer;
import projectbackroom.jonathanx.render.entities.RustyTippedArrowRenderer;
import projectbackroom.jonathanx.util.DebugLogger;

public class ProjectBackroomClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BackroomParticleManager.registerDefaultFactories();
		BackroomBlockColors.create();
		BackroomItemColors.create();

		HandledScreens.register(BackroomScreenHandlerType.CONSPIRACY_TABLE, ConspiracyTableScreen::new);

		EntityRendererRegistry.register(BackroomEntities.RUSTY_TIPPED_ARROW, RustyTippedArrowRenderer::new);

		BuiltinItemRendererRegistry.INSTANCE.register(BackroomItems.MISSING_POSTER, (itemStack, modelTransformationMode, matrixStack, vertexConsumerProvider, i, i1) -> {
			if (modelTransformationMode == ModelTransformationMode.GUI){
				matrixStack.push();

				matrixStack.translate(0.5f, 0.5f, 0.0f); // center the item
				matrixStack.scale(1.0f, -1.0f, 1.0f);

				MinecraftClient mc = MinecraftClient.getInstance();
				ItemRenderer itemRenderer = mc.getItemRenderer();

				DiffuseLighting.enableGuiDepthLighting();

				ItemStack paperStack = new ItemStack(Items.PAPER);
				itemRenderer.renderItem(
						paperStack,
						modelTransformationMode,
						0xF000F0,
						i1,
						matrixStack,
						vertexConsumerProvider,
						mc.world,
						0);

				matrixStack.pop();
			} else {
				matrixStack.push();

				if (modelTransformationMode == ModelTransformationMode.FIXED){
					matrixStack.translate(0.5, 0.5, 0.5);
					matrixStack.scale(0.0175f, 0.0175f, 0.0175f);
					matrixStack.translate(-MissingPosterRenderer.WIDTH / 2F, -MissingPosterRenderer.HEIGHT / 2F, 0F);
				} else {
					matrixStack.translate(0.0f, 0.0f, 0.0f);
				}

				MissingPosterRenderState state = new MissingPosterRenderState();
				state.backgroundTexture = ProjectBackroom.id("textures/gui/missing_poster.png");
				state.playerUUID = itemStack.get(BackroomComponents.MISSING_POSTER_PLAYER_UUID);
				MissingPosterRenderer missingPosterRenderer = new MissingPosterRenderer(state, matrixStack, vertexConsumerProvider, i);
				missingPosterRenderer.draw(itemStack, state, matrixStack, vertexConsumerProvider, i);

				matrixStack.pop();
			}
		});

        setupFluids();
	}

	private void setupFluids(){
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
	}
}