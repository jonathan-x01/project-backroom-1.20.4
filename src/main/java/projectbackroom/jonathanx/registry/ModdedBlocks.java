package projectbackroom.jonathanx.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.blocks.*;
import projectbackroom.jonathanx.blocks.level0Blocks.FluorescentDropceiling;
import projectbackroom.jonathanx.blocks.level0Blocks.Wallpaper;
import projectbackroom.jonathanx.blocks.level0Blocks.YellowCarpet;
import projectbackroom.jonathanx.blocks.level2Blocks.LargePipeBlock;
import projectbackroom.jonathanx.blocks.level2Blocks.PipeBlock;
import projectbackroom.jonathanx.blocks.level2Blocks.VerticalPipe;
import projectbackroom.jonathanx.blocks.level2Blocks.WallLightBlock;

public class ModdedBlocks {
    public static final Block LEVEL_0_WALLPAPER = registerBlock("level_0_wallpaper",new Wallpaper(AbstractBlock.Settings.create().strength(3)));
    public static final Block LEVEL_0_YELLOW_CARPET = registerBlock("level_0_yellow_carpet",new YellowCarpet(AbstractBlock.Settings.create().strength(0.7f).sounds(
            new BlockSoundGroup(
                    1f,
                    1f,
                    SoundEvent.of(Identifier.tryParse("minecraft:block.wool.break")),
                    ModdedSounds.SOGGY_STEPS,
                    SoundEvent.of(Identifier.tryParse("minecraft:block.wool.place")),
                    SoundEvent.of(Identifier.tryParse("minecraft:block.wool.hit")),
                    SoundEvent.of(Identifier.tryParse("minecraft:block.wool.fall"))
            )
    )));
    public static final Block LEVEL_0_DROPCEILING = registerBlock("level_0_dropceiling", new Block(FabricBlockSettings.create()));
    public static final Block LEVEL_0_LIGHT = registerBlock("level_0_fluorescent_dropceiling", new FluorescentDropceiling(AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.GLASS)
            .luminance((value) -> value.get(FluorescentDropceiling.LIGHTING)))
    );

    // Level 2 Blocks
    public static final Block PIPES_1 = registerBlock("pipes_1", new PipeBlock(AbstractBlock.Settings.create().nonOpaque()));
    public static final Block PIPES_1_W_VERTICAL = registerBlock("pipes_1_w_vertical", new PipeBlock(AbstractBlock.Settings.create().nonOpaque()));
    public static final Block PIPES_2 = registerBlock("pipes_2", new PipeBlock(AbstractBlock.Settings.create().nonOpaque()));
    public static final Block PIPES_3 = registerBlock("pipes_3", new PipeBlock(AbstractBlock.Settings.create().nonOpaque()));
    public static final Block PIPES_4 = registerBlock("pipes_4", new PipeBlock(AbstractBlock.Settings.create().nonOpaque()));
    public static final Block VERTICAL_PIPE = registerBlock("vertical_pipe", new VerticalPipe(AbstractBlock.Settings.create().nonOpaque()));
    public static final Block VERTICAL_PIPE_CONNECTED = registerBlock("vertical_pipe_connected", new VerticalPipe(AbstractBlock.Settings.create().nonOpaque()));
    public static final Block LARGE_PIPE_1 = registerBlock("large_pipe_1", new LargePipeBlock(AbstractBlock.Settings.create().nonOpaque()));
    public static final Block LARGE_PIPE_2 = registerBlock("large_pipe_2", new LargePipeBlock(AbstractBlock.Settings.create().nonOpaque()));
    public static final Block LARGE_PIPE_3 = registerBlock("large_pipe_3", new LargePipeBlock(AbstractBlock.Settings.create().nonOpaque()));
    public static final Block WALL_LIGHT = registerBlock("wall_light", new WallLightBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS).nonOpaque().luminance(state -> 14).noCollision()));
    // Universal Blocks
    public static final Block BROKEN_BRICKS = registerBlock("broken_bricks", new BrokenBricksBlock(AbstractBlock.Settings.create().nonOpaque()));
    public static final Block WHITE_BRICKS = registerBlock("white_bricks", new Block(FabricBlockSettings.create()));
    //public static final Block CORRUPTED_BLOCK = registerBlock("corrupted_block",new Block(FabricBlockSettings.create().collidable(false)));
    public static final Block CORRUPTED_BLOCK = registerBlock("corrupted_block",new CorruptedBlock(AbstractBlock.Settings.create().noCollision().strength(-1.0f, 3600000.8F).dropsNothing()));

    public static Block registerBlock(String name, Block block){
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK, ProjectBackroom.id(name),block);
    }

    public static Item registerBlockItem(String name, Block block){
        Item ITEM = Registry.register(Registries.ITEM, ProjectBackroom.id(name), new BlockItem(block,new FabricItemSettings()));
        return ITEM;
    }

    public static void registerModdedBlocks(){
        ProjectBackroom.displayRegisteredSectors(ModdedBlocks.class);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.addBefore(Items.BRICKS,WHITE_BRICKS);
        });
        ModdedItemGroups.getItemGroup("backroom_blocks")
                .entries(((displayContext, entries) -> {
                    entries.add(WHITE_BRICKS);
                    entries.add(LEVEL_0_WALLPAPER);
                    entries.add(LEVEL_0_DROPCEILING);
                    entries.add(LEVEL_0_LIGHT);
                    entries.add(LEVEL_0_YELLOW_CARPET);
                    entries.add(PIPES_1);
                    entries.add(PIPES_1_W_VERTICAL);
                    entries.add(PIPES_2);
                    entries.add(PIPES_3);
                    entries.add(PIPES_4);
                    entries.add(LARGE_PIPE_1);
                    entries.add(LARGE_PIPE_2);
                    entries.add(LARGE_PIPE_3);
                    entries.add(VERTICAL_PIPE);
                    entries.add(VERTICAL_PIPE_CONNECTED);
                    entries.add(BROKEN_BRICKS);
                    entries.add(WALL_LIGHT);
                }));
    }
}
