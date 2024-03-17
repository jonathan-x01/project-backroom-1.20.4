package projectbackroom.jonathanx.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.sound.moddedSounds;

public class moddedBlocks {
    public static final Block LEVEL_0_WALLPAPER = registerBlock("level_0_wallpaper",new Block(FabricBlockSettings.create().strength(100)));
    public static final Block LEVEL_0_YELLOW_CARPET = registerBlock("level_0_yellow_carpet",new Block(FabricBlockSettings.create().strength(100).sounds(
            new BlockSoundGroup(
                    1f,
                    1f,
                    SoundEvent.of(Identifier.tryParse("minecraft:block.wool.break")),
                    moddedSounds.SOGGY_STEPS,
                    SoundEvent.of(Identifier.tryParse("minecraft:block.wool.place")),
                    SoundEvent.of(Identifier.tryParse("minecraft:block.wool.hit")),
                    SoundEvent.of(Identifier.tryParse("minecraft:block.wool.fall"))
            )
            // break
            // step
            // place
            // hit
            // fall
    )));

    public static Block registerBlock(String name, Block block){
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK, new Identifier(ProjectBackroom.MOD_ID,name),block);
    }

    public static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(ProjectBackroom.MOD_ID,name), new BlockItem(block,new FabricItemSettings()));
    }

    public static void registerModdedBlocks(){
        ProjectBackroom.LOGGER.info("Registering blocks for " + ProjectBackroom.MOD_ID);
    }
}
