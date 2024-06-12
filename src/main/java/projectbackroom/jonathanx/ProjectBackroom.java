package projectbackroom.jonathanx;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectbackroom.jonathanx.blocks.ModdedBlocks;
import projectbackroom.jonathanx.entity.ModdedEntities;
import projectbackroom.jonathanx.entity.effect.ModdedStatusEffects;
import projectbackroom.jonathanx.itemGroups.ModdedItemGroups;
import projectbackroom.jonathanx.items.ModdedItems;
import projectbackroom.jonathanx.particle.ModdedParticleTypes;
import projectbackroom.jonathanx.sound.ModdedSounds;

public class ProjectBackroom implements ModInitializer {
	public static String MOD_ID = "project_backroom";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModdedItemGroups.registerModdedItemGroups();

		ModdedBlocks.registerModdedBlocks();
		ModdedSounds.registerModdedSounds();
		ModdedItems.registerModdedItems();
		ModdedParticleTypes.registerParticles();
		ModdedStatusEffects.registerModdedStatusEffects();
		ModdedEntities.registerModdedEntities();

		ModdedItemGroups.buildAll();
	}
}