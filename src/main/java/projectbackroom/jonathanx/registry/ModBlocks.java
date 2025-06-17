package projectbackroom.jonathanx.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.blocks.*;
import projectbackroom.jonathanx.blocks.level0.FluorescentDropceiling;
import projectbackroom.jonathanx.blocks.level0.Wallpaper;
import projectbackroom.jonathanx.blocks.level0.YellowCarpet;
import projectbackroom.jonathanx.blocks.level2.*;
import projectbackroom.jonathanx.world.gen.tree.ModSaplingGenerator;

public class ModBlocks {
    public static final Block LEVEL_0_WALLPAPER = registerBlock("level_0_wallpaper",new Wallpaper(AbstractBlock.Settings.create().strength(3)));
    public static final Block LEVEL_0_YELLOW_CARPET = registerBlock("level_0_yellow_carpet",new YellowCarpet(AbstractBlock.Settings.create().strength(0.7f).sounds(
            new BlockSoundGroup(
                    1f,
                    1f,
                    SoundEvent.of(Identifier.tryParse("minecraft:block.wool.break")),
                    ModSounds.SOGGY_STEPS,
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
    public static final Block BIOLOGICAL_PIPE = registerBlock("biological_pipe", new BiologicalPipeBlock(AbstractBlock.Settings.create().slipperiness(0.98f)));
    public static final Block CORRUPTED_BLOCK = registerBlock("corrupted_block",new CorruptedBlock(AbstractBlock.Settings.create().noCollision().strength(-1.0f, 3600000.8F).dropsNothing()));

    public static final Block ALMOND_TREE_SAPLING = registerBlock("almond_tree_sapling", new SaplingBlock(ModSaplingGenerator.ALMOND_TREE, FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));

    public static final Block ALMOND_TREE_LOG = registerBlock("almond_tree_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block ALMOND_TREE_PLANKS = registerBlock("almond_tree_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(4f)));
    public static final Block ALMOND_TREE_LEAVES = registerBlock("almond_tree_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).strength(4f).nonOpaque()));

    /**
     * Registers a block with the Project Backrooms mod.
     * @param name The name of the block.
     * @param block The properties of the block.
     * @return The block registration.
     */
    public static Block registerBlock(String name, Block block){
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK, ProjectBackroom.id(name),block);
    }

    public static Item registerBlockItem(String name, Block block){
        Item ITEM = Registry.register(Registries.ITEM, ProjectBackroom.id(name), new BlockItem(block,new FabricItemSettings()));
        return ITEM;
    }

    public static void registerModdedBlocks(){
        ProjectBackroom.displayRegisteredSectors(ModBlocks.class);
        /*ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.addBefore(Items.BRICKS,WHITE_BRICKS);
        });*/
        ModItemGroups.getItemGroup("backroom_flora")
                .entries((displayContext, entries) -> {
                    entries.add(ALMOND_TREE_SAPLING);
                    entries.add(ALMOND_TREE_LOG);
                    entries.add(ALMOND_TREE_LEAVES);
                    entries.add(ALMOND_TREE_PLANKS);
                });

        ModItemGroups.getItemGroup("backroom_blocks")
                .entries((displayContext, entries) -> {
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
                    entries.add(BIOLOGICAL_PIPE);
                });
    }
}
