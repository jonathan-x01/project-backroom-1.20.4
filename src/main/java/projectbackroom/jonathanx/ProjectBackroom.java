package projectbackroom.jonathanx;

import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectbackroom.jonathanx.registry.*;
import projectbackroom.jonathanx.registry.ModdedStatusEffects;
import projectbackroom.jonathanx.world.chunk.Level1Generation;
import projectbackroom.jonathanx.world.chunk.WFCChunkGenerator;
import projectbackroom.jonathanx.world.chunk.PointMazeGenerator;

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
		ModdedItemGroups.registerModdedItemGroups();

		ModdedBlockEntities.initalize();
		ModdedBlocks.registerModdedBlocks();
		ModdedSounds.registerModdedSounds();
		ModdedItems.registerModdedItems();
		ModdedParticleTypes.registerParticles();
		ModdedStatusEffects.registerModdedStatusEffects();
		ModdedEntities.registerModdedEntities();

		initChunkGeneration();

		ModdedItemGroups.buildAll();
	}

	public static Identifier id(String path){
		return new Identifier(MOD_ID, path);
	}

	/**
	 * Outputs a debug message to the Minecraft log for debugging purposes.
	 * @param msg The message to include in the debug message.
	 */
	public static void debug(Object msg){
		LOGGER.warn("{DEBUG | " + MOD_ID + "}");
		LOGGER.info("Message : " + msg);
		LOGGER.info("-- DEBUG END --");
	}

	public static void error(String msg){
		LOGGER.error("{ ERROR | " + MOD_ID + " } " + msg);
	}

	/**
	 * Displays a message to the Minecraft log to list sectors that was loaded from the mod.
	 * @param c The class that has loaded.
	 */
	public static void displayRegisteredSectors(Class c){
		LOGGER.info("{ LOADED | " + MOD_ID + " } " + c.getSimpleName());
	}
}