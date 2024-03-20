package projectbackroom.jonathanx.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
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
    )));
    public static final Block LEVEL_0_DROPCEILING = registerBlock("level_0_dropceiling", new Block(FabricBlockSettings.create()));
    public static final Block LEVEL_0_LIGHT = registerBlock("level_0_led_dropceiling", new level_0_led_dropceiling(AbstractBlock.Settings.create()));
    //new Block(FabricBlockSettings.create().luminance(8).sounds(BlockSoundGroup.GLASS))

    public static final ItemGroup BACKROOM_BLOCKS = FabricItemGroup.builder()
            .displayName(Text.translatable("itemgroup.project_backroom.backroom_blocks"))
            .icon(() -> new ItemStack(LEVEL_0_WALLPAPER.asItem()))
            .entries(((displayContext, entries) -> {
                entries.add(LEVEL_0_WALLPAPER);
                entries.add(LEVEL_0_DROPCEILING);
                entries.add(LEVEL_0_LIGHT);
                entries.add(LEVEL_0_YELLOW_CARPET);
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
    }
}
