package projectbackroom.jonathanx.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.blocks.fluids.BackroomFluidBlock;
import projectbackroom.jonathanx.blocks.fluids.BlackSludgeFluidBlock;
import projectbackroom.jonathanx.blocks.fluids.ContaminatedWaterBlock;
import projectbackroom.jonathanx.blocks.fluids.FluidBlockFactory;
import projectbackroom.jonathanx.blocks.level0.FluorescentDropceiling;
import projectbackroom.jonathanx.blocks.level0.Wallpaper;
import projectbackroom.jonathanx.blocks.level0.YellowCarpet;
import projectbackroom.jonathanx.blocks.level2.*;
import projectbackroom.jonathanx.blocks.pipes.CeilingPipeBlock;
import projectbackroom.jonathanx.blocks.pipes.LargeHorizontalPipeBlock;
import projectbackroom.jonathanx.blocks.pipes.PipeBlock;
import projectbackroom.jonathanx.blocks.pipes.VerticalPipeBlock;
import projectbackroom.jonathanx.fluid.BackroomFluids;
import projectbackroom.jonathanx.registry.ModItemGroups;
import projectbackroom.jonathanx.registry.ModSounds;
import projectbackroom.jonathanx.world.gen.tree.ModSaplingGenerator;

import java.util.ArrayList;
import java.util.List;

public class BackroomBlocks {
    public static List<Block> pipeBlocks = new ArrayList<>();
    public static final Block LEVEL_0_WALLPAPER;
    public static final Block LEVEL_0_YELLOW_CARPET;
    public static final Block LEVEL_0_DROPCEILING;
    public static final Block LEVEL_0_LIGHT;

    // Level 2 Blocks
    public static final Block LARGE_HORIZONTAL_PIPE;
    public static final Block VERTICAL_PIPE;
    public static final Block CEILING_PIPE;
    public static final Block WALL_LIGHT;
    // Universal Blocks
    public static final Block BROKEN_BRICKS;
    public static final Block WHITE_BRICKS;
    public static final Block BIOLOGICAL_PIPE;
    public static final Block CORRUPTED_BLOCK;

    // Plants
    public static final Block ALMOND_TREE_SAPLING;

    // Tree Groups
    public static final Block ALMOND_TREE_LOG;
    public static final Block ALMOND_TREE_WOOD;
    public static final Block ALMOND_TREE_LEAVES;
    public static final Block ALMOND_TREE_PLANKS;
    public static final Block ALMOND_TREE_SLAB;
    public static final Block ALMOND_TREE_STAIRS;
    public static final Block ALMOND_TREE_DOOR;
    public static final Block ALMOND_TREE_TRAPDOOR;
    public static final Block ALMOND_TREE_FENCE;
    public static final Block ALMOND_TREE_FENCE_GATE;
    public static final Block ALMOND_TREE_BUTTON;
    public static final Block ALMOND_TREE_PRESSURE_PLATE;

    // Fluids
    public static Block ALMOND_WATER_BLOCK;
    public static Block CONTAMINATED_WATER_BLOCK;
    public static Block BLACK_SLUDGE_BLOCK;

    /**
     * Registers a block with the Project Backrooms mod.
     * @param name The name of the block.
     * @param block The properties of the block.
     * @return The block registration.
     */
    public static Block registerBlock(String name, Block block){
        if (block instanceof PipeBlock){
            pipeBlocks.add(block);
        }
        registerBlockItem(name,block);
        return registerBlockWithoutItem(name, block);
    }

    public static Block registerBlockWithoutItem(String name, Block block){
        return Registry.register(Registries.BLOCK, ProjectBackroom.id(name),block);
    }

    public static Block registerFluidBlock(String name, FlowableFluid flowableFluid, FluidBlockFactory factory, BackroomFluidBlock.Settings settings){
        return registerBlockWithoutItem(
                name + "_fluid",
                factory.create(
                        flowableFluid,
                        FabricBlockSettings.create()
                                .replaceable()
                                .noCollision()
                                .strength(100.0f)
                                .pistonBehavior(PistonBehavior.DESTROY)
                                .dropsNothing()
                                .liquid()
                                .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY),
                        settings
                )
        );
    }

    public static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, ProjectBackroom.id(name), new BlockItem(block,new FabricItemSettings()));
    }

    private static Block createStairsBlock(Block base) {
        return new StairsBlock(base.getDefaultState(), AbstractBlock.Settings.copy(base));
    }

    public static void registerBackroomBlocks(){
        ProjectBackroom.displayRegisteredSectors(BackroomBlocks.class);
        registerBackroomFluids();
        registerItemGroups();
    }

    public static void registerItemGroups(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.addBefore(Items.BRICKS,WHITE_BRICKS);
            content.addBefore(Items.OAK_LOG, ALMOND_TREE_BUTTON);
            content.addBefore(ALMOND_TREE_BUTTON, ALMOND_TREE_PRESSURE_PLATE);
            content.addBefore(ALMOND_TREE_PRESSURE_PLATE, ALMOND_TREE_TRAPDOOR);
            content.addBefore(ALMOND_TREE_TRAPDOOR, ALMOND_TREE_DOOR);
            content.addBefore(ALMOND_TREE_DOOR, ALMOND_TREE_FENCE_GATE);
            content.addBefore(ALMOND_TREE_FENCE_GATE, ALMOND_TREE_FENCE);
            content.addBefore(ALMOND_TREE_FENCE, ALMOND_TREE_SLAB);
            content.addBefore(ALMOND_TREE_SLAB, ALMOND_TREE_STAIRS);
            content.addBefore(ALMOND_TREE_STAIRS, ALMOND_TREE_PLANKS);
            content.addBefore(ALMOND_TREE_PLANKS, ALMOND_TREE_WOOD);
            content.addBefore(ALMOND_TREE_WOOD, ALMOND_TREE_LOG);
        });

        ModItemGroups.getItemGroup("backroom_flora")
                .entries((displayContext, entries) -> {
                    entries.add(ALMOND_TREE_SAPLING);
                    entries.add(ALMOND_TREE_LEAVES);
                });

        ModItemGroups.getItemGroup("backroom_blocks")
                .entries((displayContext, entries) -> {
                    entries.add(WHITE_BRICKS);
                    entries.add(LEVEL_0_WALLPAPER);
                    entries.add(LEVEL_0_DROPCEILING);
                    entries.add(LEVEL_0_LIGHT);
                    entries.add(LEVEL_0_YELLOW_CARPET);
                    entries.add(LARGE_HORIZONTAL_PIPE);
                    entries.add(VERTICAL_PIPE);
                    entries.add(CEILING_PIPE);
                    entries.add(BROKEN_BRICKS);
                    entries.add(WALL_LIGHT);
                    entries.add(BIOLOGICAL_PIPE);
                });
    }

    public static void registerBackroomFluids(){
        ALMOND_WATER_BLOCK = registerFluidBlock("almond_water", BackroomFluids.ALMOND_WATER, BackroomFluidBlock::new,
                BackroomFluidBlock.Settings.create()
                        .setColor(0xFFECB3)
        );

        CONTAMINATED_WATER_BLOCK = registerFluidBlock("contaminated_water", BackroomFluids.CONTAMINATED_WATER, ContaminatedWaterBlock::new,
                BackroomFluidBlock.Settings.create()
                        .setColor(0x556B2F)
        );

        BLACK_SLUDGE_BLOCK = registerFluidBlock("black_sludge", BackroomFluids.BLACK_SLUDGE, BlackSludgeFluidBlock::new,
                BackroomFluidBlock.Settings.create()
                        .setColor(0x000000)
                        .setSpeed(0.005f)
                        .setDrag(new Vec3d(0.5, 0.4, 0.5))
                        .setFogStart(0.25f)
                        .setFogEnd(1.0f)
        );
    }

    static {
        LEVEL_0_WALLPAPER = registerBlock("level_0_wallpaper",new Wallpaper(AbstractBlock.Settings.create().strength(3)));
        LEVEL_0_YELLOW_CARPET = registerBlock("level_0_yellow_carpet",new YellowCarpet(AbstractBlock.Settings.create().strength(0.7f).sounds(
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
        LEVEL_0_DROPCEILING = registerBlock("level_0_dropceiling", new Block(FabricBlockSettings.create()));
        LEVEL_0_LIGHT = registerBlock("level_0_fluorescent_dropceiling", new FluorescentDropceiling(AbstractBlock.Settings.create()
                .sounds(BlockSoundGroup.GLASS)
                .luminance((value) -> value.get(FluorescentDropceiling.LIGHTING)))
        );

        // Level 2 Blocks
        LARGE_HORIZONTAL_PIPE = registerBlock("large_horizontal_pipe", new LargeHorizontalPipeBlock(AbstractBlock.Settings.create().nonOpaque()));
        VERTICAL_PIPE = registerBlock("vertical_pipe", new VerticalPipeBlock(AbstractBlock.Settings.create().nonOpaque()));
        CEILING_PIPE = registerBlock("ceiling_pipe", new CeilingPipeBlock(AbstractBlock.Settings.create().nonOpaque()));

        WALL_LIGHT = registerBlock("wall_light", new WallLightBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS).nonOpaque().luminance(state -> 14).noCollision()));
        // Universal Blocks
        BROKEN_BRICKS = registerBlock("broken_bricks", new BrokenBricksBlock(AbstractBlock.Settings.create().nonOpaque()));
        WHITE_BRICKS = registerBlock("white_bricks", new Block(FabricBlockSettings.create()));
        //public static final Block CORRUPTED_BLOCK = registerBlock("corrupted_block",new Block(FabricBlockSettings.create().collidable(false)));
        BIOLOGICAL_PIPE = registerBlock("biological_pipe", new BiologicalPipeBlock(AbstractBlock.Settings.create().slipperiness(0.98f)));
        CORRUPTED_BLOCK = registerBlock("corrupted_block",new CorruptedBlock(AbstractBlock.Settings.create().noCollision().strength(-1.0f, 3600000.8F).dropsNothing()));

        // Plants
        ALMOND_TREE_SAPLING = registerBlock("almond_tree_sapling", new SaplingBlock(ModSaplingGenerator.ALMOND_TREE, FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));

        // Tree Groups
        ALMOND_TREE_LOG = registerBlock("almond_tree_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
        ALMOND_TREE_WOOD = registerBlock("almond_tree_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)));
        ALMOND_TREE_LEAVES = registerBlock("almond_tree_leaves", Blocks.createLeavesBlock(BlockSoundGroup.GRASS));
        ALMOND_TREE_PLANKS = registerBlock("almond_tree_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(4f)));
        ALMOND_TREE_SLAB = registerBlock("almond_tree_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(4f)));
        ALMOND_TREE_STAIRS = registerBlock("almond_tree_stairs", createStairsBlock(ALMOND_TREE_PLANKS));
        ALMOND_TREE_DOOR = registerBlock("almond_tree_door", new DoorBlock(BlockSetType.OAK ,FabricBlockSettings.copyOf(Blocks.OAK_DOOR).nonOpaque()));
        ALMOND_TREE_TRAPDOOR = registerBlock("almond_tree_trapdoor", new TrapdoorBlock(BlockSetType.OAK, FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR).nonOpaque()));
        ALMOND_TREE_FENCE = registerBlock("almond_tree_fence", new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE)));
        ALMOND_TREE_FENCE_GATE = registerBlock("almond_tree_fence_gate", new FenceGateBlock(WoodType.OAK, FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE)));
        ALMOND_TREE_BUTTON = registerBlock("almond_tree_button", new ButtonBlock(BlockSetType.OAK, 35, FabricBlockSettings.copyOf(Blocks.OAK_BUTTON)));
        ALMOND_TREE_PRESSURE_PLATE = registerBlock("almond_tree_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE)));
    }
}
