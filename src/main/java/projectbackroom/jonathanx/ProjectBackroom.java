package projectbackroom.jonathanx;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.apache.http.annotation.Obsolete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectbackroom.jonathanx.blocks.BackroomBlocks;
import projectbackroom.jonathanx.blocks.entity.BackroomBlockEntities;
import projectbackroom.jonathanx.fluid.BackroomFluids;
import projectbackroom.jonathanx.items.BackroomItems;
import projectbackroom.jonathanx.particle.BackroomParticleTypes;
import projectbackroom.jonathanx.registry.*;
import projectbackroom.jonathanx.registry.ModStatusEffects;
import projectbackroom.jonathanx.world.gen.chunk.Level1Generation;
import projectbackroom.jonathanx.world.gen.chunk.WFCChunkGenerator;
import projectbackroom.jonathanx.world.gen.chunk.PointMazeGenerator;

public class ProjectBackroom implements ModInitializer {
	public static String MOD_ID = "project_backroom";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static void initChunkGeneration(){
		Registry.register(Registries.CHUNK_GENERATOR, id("point_maze_generator"), PointMazeGenerator.CODEC);
		Registry.register(Registries.CHUNK_GENERATOR, id("infinite_jigsaw"), WFCChunkGenerator.CODEC);
		Registry.register(Registries.CHUNK_GENERATOR, id("level1"), Level1Generation.CODEC);
	}

	@Override
	public void onInitialize() {
		ModItemGroups.registerModdedItemGroups();
		BackroomFluidTags.registerFluidTags();

		BackroomFluids.registerFluid();
		BackroomBlockEntities.initialize();
		BackroomBlocks.registerBackroomBlocks();
		ModSounds.registerModdedSounds();
		BackroomItems.registerModdedItems();
		ModStatusEffects.registerModdedStatusEffects();
		BackroomEntities.registerModdedEntities();
		BackroomParticleTypes.registerParticles();

		initChunkGeneration();

		ModItemGroups.buildAll();

		FlammableBlockRegistry.getDefaultInstance().add(BackroomBlocks.ALMOND_TREE_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BackroomBlocks.ALMOND_TREE_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(BackroomBlocks.ALMOND_TREE_LEAVES, 30, 60);

		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BackroomItems.ALMOND, 0.6f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BackroomItems.ALMOND_SEED, 0.5f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BackroomItems.ALMOND_HUSK, 0.4f);

		BrewingRecipeRegistry.registerPotionRecipe(Potions.WATER, BackroomItems.ALMOND_SEED, BackroomPotions.HOMEMADE_ALMOND_WATER);
		BrewingRecipeRegistry.craft(new ItemStack(BackroomItems.ALMOND_SEED), new ItemStack(BackroomItems.ORIGINAL_ALMOND_WATER));

		this.initServerTicks();
	}

	private void initServerTicks(){
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerWorld world : server.getWorlds()){
				for (Entity entity : world.iterateEntities()){
					BlockPos pos = entity.getBlockPos();
					BlockState state = world.getBlockState(pos);

					FluidState fluidState = state.getFluidState();
					if (fluidState.isOf(BackroomFluids.BLACK_SLUDGE) || fluidState.isOf(BackroomFluids.FLOWING_BLACK_SLUDGE)){
						double height = fluidState.getHeight(world, pos);

						if (height > 0.0){
							/*Vec3d velocity = entity.getVelocity();

							double sinkSpeed = -0.02;
							entity.setVelocity(velocity.x * 0.9, Math.max(velocity.y, sinkSpeed), velocity.z * 0.9);
							entity.velocityModified = true;*/
						}
					}
				}
			}
		});
	}

	public static Identifier id(String path){
		return new Identifier(MOD_ID, path);
	}

	public static final String ANSI_BLUE    = "\u001B[34m";
	public static final String ANSI_YELLOW  = "\u001B[33m";
	public static final String ANSI_CYAN    = "\u001B[36m";
	public static final String ANSI_MAGENTO = "\u001B[35m";
	public static final String ANSI_GREEN   = "\u001B[32m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET   = "\u001B[0m";

	/**
	 * Outputs a debug message to the Minecraft log for debugging purposes.
	 * @param msg The message to include in the debug message.
	 */
	public static void debug(Object msg){
		StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
		String loc = caller.getClassName() + "." + caller.getMethodName() + "()" + ANSI_MAGENTO + " [Line " + caller.getLineNumber() + "]";
		LOGGER.warn(ANSI_YELLOW + "{DEBUG | " + ANSI_CYAN + MOD_ID + ANSI_YELLOW + " | " + ANSI_BLUE + loc + ANSI_YELLOW + "}");
		String message = "%sMessage : %s%s";
		if (msg == null){
			LOGGER.error(String.format(message, ANSI_RED, "", msg));
		} else {
			LOGGER.info(String.format(message, ANSI_GREEN, ANSI_RESET, msg));
		}
		LOGGER.warn(ANSI_YELLOW + "-- DEBUG END --");
	}

	/**
	 * Outputs multiple lines of messages.
	 * @param msg The list of messages to display.
	 */
	public static void debug(Object... msg){
		StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
		String loc = caller.getClassName() + "." + caller.getMethodName() + "() " + ANSI_MAGENTO + " [Line " + caller.getLineNumber() + "]" + ANSI_YELLOW;
		LOGGER.warn(ANSI_YELLOW + "{DEBUG | " + ANSI_CYAN + MOD_ID + ANSI_YELLOW + " | " + ANSI_BLUE + loc + ANSI_YELLOW + "}");
		for (int i = 0; i < msg.length; i++){
			String message = "%sMessage %s(Line %d)%s : %s";
			if (msg[i] == null){
				LOGGER.error(String.format(message, ANSI_RED, ANSI_MAGENTO, i, ANSI_RED, msg[i]));
			} else {
				LOGGER.info(String.format(message, ANSI_GREEN, ANSI_MAGENTO, i, ANSI_RESET, msg[i]));
			}
		}
		LOGGER.warn(ANSI_YELLOW + "-- DEBUG END --");
	}

	/**
	 * Displays a debug message every tick. Useful for methods that would run every tick, that way the IDE can handle it.
	 * @param age = Simply get the age of the entity.
	 * @param increment = How many times per tick the message will display. 20 ticks = 1 second
	 * @param msg = The message to display/
	 */
	public static void debug(int age, int increment, Object msg){
		if (age % increment == 0){
			debug(msg);
		}
	}

	public static void debug(int age, int increment, Object... msg){
		if (age % increment == 0){
			debug(msg);
		}
	}

	@Obsolete
	public static void error(String msg){
		LOGGER.error("{ ERROR | " + MOD_ID + " } " + msg);
	}

	/**
	 * Displays a message to the Minecraft log to list sectors that was loaded from the mod.
	 * @param c The class that has loaded.
	 */
	public static void displayRegisteredSectors(Class c){
		LOGGER.info(ANSI_GREEN + "{ LOADED | " + ANSI_CYAN + MOD_ID + ANSI_GREEN + " } " + ANSI_BLUE + c.getSimpleName());
	}
}