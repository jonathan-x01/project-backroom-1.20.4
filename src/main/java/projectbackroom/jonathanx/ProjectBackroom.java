package projectbackroom.jonathanx;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.ComposterBlock;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.apache.http.annotation.Obsolete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectbackroom.jonathanx.init.BackroomBlocks;
import projectbackroom.jonathanx.blocks.entity.BackroomBlockEntities;
import projectbackroom.jonathanx.fluid.BackroomFluids;
import projectbackroom.jonathanx.items.BackroomItems;
import projectbackroom.jonathanx.particle.BackroomParticleTypes;
import projectbackroom.jonathanx.init.*;
import projectbackroom.jonathanx.init.BackroomStatusEffects;
import projectbackroom.jonathanx.util.Initer;
import projectbackroom.jonathanx.villager.BackroomVillager;
import projectbackroom.jonathanx.villager.VillagerTrades;
import projectbackroom.jonathanx.world.gen.chunk.Level1Generation;
import projectbackroom.jonathanx.world.gen.chunk.WFCChunkGenerator;
import projectbackroom.jonathanx.world.gen.chunk.PointMazeGenerator;

import java.util.Objects;

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
		//Initer.initialise();
		BackroomItemGroups.registerModdedItemGroups();
		BackroomFluidTags.registerFluidTags();

		BackroomFluids.registerFluid();
		BackroomBlockEntities.initialize();
		BackroomBlocks.registerBackroomBlocks();
		BackroomSounds.registerModdedSounds();
		BackroomItems.registerModdedItems();
		BackroomStatusEffects.registerModdedStatusEffects();
		BackroomEntities.registerModdedEntities();
		BackroomParticleTypes.registerParticles();
		BackroomPotions.registerPotions();

		BackroomVillager.registerVillagers();
		VillagerTrades.registerVillagerTrades();

		initChunkGeneration();

		BackroomItemGroups.buildAll();

		FlammableBlockRegistry.getDefaultInstance().add(BackroomBlocks.ALMOND_TREE_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BackroomBlocks.ALMOND_TREE_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(BackroomBlocks.ALMOND_TREE_LEAVES, 30, 60);

		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BackroomItems.ALMOND, 0.6f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BackroomItems.ALMOND_SEED, 0.5f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BackroomItems.ALMOND_HUSK, 0.4f);

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(Potions.WATER, BackroomItems.ALMOND_SEED, BackroomPotions.HOMEMADE_ALMOND_WATER);
		});
	}

	public static Identifier id(String path){
		return Identifier.of(MOD_ID, path);
	}
}