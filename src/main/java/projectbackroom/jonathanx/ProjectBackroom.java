package projectbackroom.jonathanx;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectbackroom.jonathanx.blocks.moddedBlocks;
import projectbackroom.jonathanx.items.moddedItems;
import projectbackroom.jonathanx.potions.moddedPotions;
import projectbackroom.jonathanx.sound.moddedSounds;

public class ProjectBackroom implements ModInitializer {
	public static String MOD_ID = "project_backroom";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	//public static final Block LEVEL_0_WALLPAPER = new Block(FabricBlockSettings.create().strength(100));
	//public static String LEVEL_0_WALLPAPER_PATH = "level_0_wallpaper";

	@Override
	public void onInitialize() {
		//Registry.register(Registries.BLOCK, new Identifier(MOD_ID,LEVEL_0_WALLPAPER_PATH),LEVEL_0_WALLPAPER);
		//Registry.register(Registries.ITEM, new Identifier(MOD_ID,LEVEL_0_WALLPAPER_PATH), new BlockItem(LEVEL_0_WALLPAPER, new FabricItemSettings()));
		LOGGER.info("Hello Fabric world!");

		moddedBlocks.registerModdedBlocks();
		moddedSounds.registerModdedSounds();
		moddedItems.registerModdedItems();
	}
}