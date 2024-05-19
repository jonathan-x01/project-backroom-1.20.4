package projectbackroom.jonathanx.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.sound.moddedSounds;

public class moddedBlocks {
    public static final Block LEVEL_0_WALLPAPER = registerBlock("level_0_wallpaper",new Block(FabricBlockSettings.create().strength(3)));
    public static final Block LEVEL_0_YELLOW_CARPET = registerBlock("level_0_yellow_carpet",new level0YellowCarpet(AbstractBlock.Settings.create().strength(0.7f).sounds(
            new BlockSoundGroup(
                    1f,
                    1f,
                    SoundEvent.of(Identifier.tryParse("minecraft:block.wool.break")),
                    moddedSounds.SOGGY_STEPS,
                    SoundEvent.of(Identifier.tryParse("minecraft:block.wool.place")),
                    SoundEvent.of(Identifier.tryParse("minecraft:block.wool.hit")),
                    SoundEvent.of(Identifier.tryParse("minecraft:block.wool.fall"))
            )
    )));
    public static final Block LEVEL_0_DROPCEILING = registerBlock("level_0_dropceiling", new Block(FabricBlockSettings.create()));
    public static final Block LEVEL_0_LIGHT = registerBlock("level_0_fluorescent_dropceiling", new level0FluorescentDropceiling(AbstractBlock.Settings.create()
            .luminance((value) -> value.get(level0FluorescentDropceiling.LIGHTING)))
    );

    // Level 2 Blocks
    public static final Block PIPES_1 = registerBlock("pipes_1", new pipes(AbstractBlock.Settings.create()));
    public static final Block PIPES_2 = registerBlock("pipes_2", new pipes(AbstractBlock.Settings.create()));
    public static final Block PIPES_3 = registerBlock("pipes_3", new pipes(AbstractBlock.Settings.create()));
    public static final Block PIPES_4 = registerBlock("pipes_4", new pipes(AbstractBlock.Settings.create()));
    public static final Block LARGE_PIPE_1 = registerBlock("large_pipe_1", new pipes(AbstractBlock.Settings.create()));
    public static final Block LARGE_PIPE_2 = registerBlock("large_pipe_2", new pipes(AbstractBlock.Settings.create()));
    public static final Block BROKEN_BRICKS_1 = registerBlock("broken_bricks_1", new Block(FabricBlockSettings.create()));

    // Universal Blocks
    public static final Block WHITE_BRICKS = registerBlock("white_bricks", new Block(FabricBlockSettings.create()));
    public static final Block CORRUPTED_BLOCK = registerBlock("corrupted_block",new Block(FabricBlockSettings.create().collidable(false)));

    public static final ItemGroup BACKROOM_BLOCKS = FabricItemGroup.builder()
            .displayName(Text.translatable("itemgroup.project_backroom.backroom_blocks"))
            .icon(() -> new ItemStack(LEVEL_0_WALLPAPER.asItem()))
            .entries(((displayContext, entries) -> {
                entries.add(WHITE_BRICKS);
                entries.add(LEVEL_0_WALLPAPER);
                entries.add(LEVEL_0_DROPCEILING);
                entries.add(LEVEL_0_LIGHT);
                entries.add(LEVEL_0_YELLOW_CARPET);
                entries.add(PIPES_1);
                entries.add(PIPES_2);
                entries.add(PIPES_3);
                entries.add(PIPES_4);
                entries.add(LARGE_PIPE_1);
                entries.add(LARGE_PIPE_2);
                entries.add(BROKEN_BRICKS_1);
            }))
            .build();

    public static ItemGroup itemGroupRegistry = Registry.register(Registries.ITEM_GROUP, new Identifier(ProjectBackroom.MOD_ID,"backroom_blocks"), BACKROOM_BLOCKS);

    public static Block registerBlock(String name, Block block){
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK, new Identifier(ProjectBackroom.MOD_ID,name),block);
    }

    public static Item registerBlockItem(String name, Block block){
        Item ITEM = Registry.register(Registries.ITEM, new Identifier(ProjectBackroom.MOD_ID,name), new BlockItem(block,new FabricItemSettings()));
        return ITEM;
    }

    public static void registerModdedBlocks(){
        ProjectBackroom.LOGGER.info("Registering blocks for " + ProjectBackroom.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.addBefore(Items.BRICKS,WHITE_BRICKS);
        });
    }
}
