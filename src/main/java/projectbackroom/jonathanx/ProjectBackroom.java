package projectbackroom.jonathanx;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectbackroom.jonathanx.registry.*;
import projectbackroom.jonathanx.registry.ModdedStatusEffects;
import projectbackroom.jonathanx.world.chunk.MazeGenerator;

public class ProjectBackroom implements ModInitializer {
	public static String MOD_ID = "project_backroom";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static void initChunkGeneration(){
		Registry.register(Registries.CHUNK_GENERATOR, id("maze_generator"), MazeGenerator.CODEC);
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

	public static Identifier id(String path){
		return new Identifier(MOD_ID, path);
	}

	public static void debug(String msg){
		LOGGER.warn("{DEBUG | " + MOD_ID + "}");
		LOGGER.info("Message : " + msg);
		LOGGER.info("-- DEBUG END --");
	}

	public static void displayRegisteredSectors(Class c){
		LOGGER.info("{ LOADED | " + MOD_ID + " } " + c.getSimpleName());
	}
}