package projectbackroom.jonathanx;

import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectbackroom.jonathanx.blocks.ModdedBlocks;
import projectbackroom.jonathanx.entity.ModdedEntities;
import projectbackroom.jonathanx.entity.effect.ModdedStatusEffects;
import projectbackroom.jonathanx.itemGroups.ModdedItemGroups;
import projectbackroom.jonathanx.items.ModdedItems;
import projectbackroom.jonathanx.particle.ModdedParticleTypes;
import projectbackroom.jonathanx.sound.ModdedSounds;
import projectbackroom.jonathanx.world.Chunk.MazeGenerator;

public class ProjectBackroom implements ModInitializer {
	public static String MOD_ID = "project_backroom";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static void initChunkGeneration(){
		Registry.register(Registries.CHUNK_GENERATOR, new Identifier(MOD_ID,"maze_generator"), MazeGenerator.CODEC);
	}

	@Override
	public void onInitialize() {
		ModdedItemGroups.registerModdedItemGroups();

		ModdedBlocks.registerModdedBlocks();
		ModdedSounds.registerModdedSounds();
		ModdedItems.registerModdedItems();
		ModdedParticleTypes.registerParticles();
		ModdedStatusEffects.registerModdedStatusEffects();
		ModdedEntities.registerModdedEntities();

		initChunkGeneration();

		ModdedItemGroups.buildAll();
	}
}