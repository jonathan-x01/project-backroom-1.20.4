package projectbackroom.jonathanx.init;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.apache.http.annotation.Obsolete;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.blocks.BackroomBlockSoundGroups;
import projectbackroom.jonathanx.blocks.BrokenBricksBlock;
import projectbackroom.jonathanx.blocks.ConspiracyTable;
import projectbackroom.jonathanx.blocks.CorruptedBlock;
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
import projectbackroom.jonathanx.util.DebugLogger;
import projectbackroom.jonathanx.util.Initer;
import projectbackroom.jonathanx.world.gen.tree.ModSaplingGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BackroomBlocks implements Initer {
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

    public static final Block CONSPIRACY_TABLE;

    // Fluids
    public static Block ALMOND_WATER_BLOCK;
    public static Block CONTAMINATED_WATER_BLOCK;
    public static Block BLACK_SLUDGE_BLOCK;

    @Override
    public int order() {
        return 1;
    }

    @Override
    public void init() {
        Initer.super.init();
        registerItemGroups();
        registerBackroomFluids();
    }

    private static void registerBackroomFluids(){
        ALMOND_WATER_BLOCK = registerFluidBlock("almond_water", BackroomFluids.ALMOND_WATER, BackroomFluidBlock::new,
                BackroomFluidBlock.Settings.create()
                        .setColor(0xFFECB3)
                        .setFogEnd(10.0f)
                        .setFogAlpha(0.8f)
                        .setSplashParticles(
                                BackroomFluidBlock.FluidSplashParticleManager.create()
                                        .setParticles(BackroomParticleTypes.CONTAMINATED_WATER_SPLASH, BackroomParticleTypes.CONTAMINATED_WATER_BUBBLE)
                        )
        );

        CONTAMINATED_WATER_BLOCK = registerFluidBlock("contaminated_water", BackroomFluids.CONTAMINATED_WATER, ContaminatedWaterBlock::new,
                BackroomFluidBlock.Settings.create()
                        .setColor(0x556B2F)
                        .setFogEnd(10.0f)
                        .setFogAlpha(0.8f)
                        .setSplashParticles(
                                BackroomFluidBlock.FluidSplashParticleManager.create()
                                        .setParticles(BackroomParticleTypes.CONTAMINATED_WATER_SPLASH, BackroomParticleTypes.CONTAMINATED_WATER_BUBBLE)
                        )
        );

        BLACK_SLUDGE_BLOCK = registerFluidBlock("black_sludge", BackroomFluids.BLACK_SLUDGE, BlackSludgeFluidBlock::new,
                BackroomFluidBlock.Settings.create()
                        .setColor(0x000000)
                        .setSpeed(0.005f)
                        .setDrag(new Vec3d(0.5, 0.4, 0.5))
                        .setFogStart(0.25f)
                        .setFogEnd(1.0f)
                        .setFogAlpha(1.0F)
        );
    }

    private static void registerItemGroups(){
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

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {
            content.addAfter(Items.LOOM, CONSPIRACY_TABLE);
        });

        BackroomItemGroups.getItemGroup("backroom_flora")
                .entries((displayContext, entries) -> {
                    entries.add(ALMOND_TREE_SAPLING);
                    entries.add(ALMOND_TREE_LEAVES);
                });

        BackroomItemGroups.getItemGroup("backroom_blocks")
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

    public static void checkAndSetPipe(Block block){
        if (block instanceof PipeBlock){
            pipeBlocks.add(block);
        }
    }

    /**
     * Registers a block with the Project Backrooms mod.
     * @param id The name of the block.
     * @param factory The block class.
     * @return The block registration.
     */
    public static Block register(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, BiFunction<Block, Item.Settings, BlockItem> itemFactory, Item.Settings itemSettings){
        Identifier identifier = ProjectBackroom.id(id);
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, identifier);

        Block block = factory.apply(settings.registryKey(blockKey));
        checkAndSetPipe(block);
        Item item = itemFactory.apply(block, itemSettings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    public static Block register(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, boolean createItem){
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, ProjectBackroom.id(id));
        Block block = factory.apply(settings.registryKey(blockKey));
        checkAndSetPipe(block);

        Block registeredBlock = Registry.register(Registries.BLOCK, blockKey, block);
        if (createItem){
            RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, ProjectBackroom.id(id));
            BlockItem item = new BlockItem(registeredBlock, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, item);
        }
        return registeredBlock;
    }

    public static Block register(String id, Function<AbstractBlock.Settings, Block> factory, boolean createItem){
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, ProjectBackroom.id(id));
        Block block = factory.apply(AbstractBlock.Settings.create().registryKey(blockKey));
        checkAndSetPipe(block);

        Block registeredBlock = Registry.register(Registries.BLOCK, blockKey, block);
        if (createItem){
            RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, ProjectBackroom.id(id));
            BlockItem item = new BlockItem(registeredBlock, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, item);
        }
        return registeredBlock;
    }

    public static Block registerFluidBlock(String name, FlowableFluid flowableFluid, FluidBlockFactory factory, BackroomFluidBlock.Settings settings){
        return register(
                name + "_fluid",
                blockSettings -> factory.create(flowableFluid, blockSettings, settings),
                AbstractBlock.Settings.copy(Blocks.WATER)
                        .replaceable()
                        .noCollision()
                        .strength(100.0f)
                        .pistonBehavior(PistonBehavior.DESTROY)
                        .dropsNothing()
                        .liquid(),
                false
        );
    }

    @Obsolete
    private static Block createStairsBlock(Block base, AbstractBlock.Settings settings) {
        return new StairsBlock(base.getDefaultState(), settings);
    }

    static {
        LEVEL_0_WALLPAPER = register("level_0_wallpaper", Wallpaper::new, AbstractBlock.Settings.create().strength(3), true);
        LEVEL_0_YELLOW_CARPET = register("level_0_yellow_carpet", YellowCarpet::new, AbstractBlock.Settings.create().strength(0.7f).sounds(BackroomBlockSoundGroups.SOGGY_CARPET), true);
        LEVEL_0_DROPCEILING = register("level_0_dropceiling", Block::new, true);
        LEVEL_0_LIGHT = register("level_0_fluorescent_dropceiling", FluorescentDropceiling::new, AbstractBlock.Settings.create()
                .sounds(BlockSoundGroup.GLASS)
                .luminance((value) -> value.get(FluorescentDropceiling.LIGHTING)),
                true
        );
        CONSPIRACY_TABLE = register("conspiracy_table", ConspiracyTable::new, true);

        // Level 2 Blocks
        LARGE_HORIZONTAL_PIPE = register("large_horizontal_pipe", LargeHorizontalPipeBlock::new, AbstractBlock.Settings.create().nonOpaque(), true);
        VERTICAL_PIPE = register("vertical_pipe", VerticalPipeBlock::new, AbstractBlock.Settings.create().nonOpaque(), true);
        CEILING_PIPE = register("ceiling_pipe", CeilingPipeBlock::new, AbstractBlock.Settings.create().nonOpaque(), true);

        WALL_LIGHT = register("wall_light", WallLightBlock::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS).nonOpaque().luminance(state -> 14).noCollision(), true);
        // Universal Blocks
        BROKEN_BRICKS = register("broken_bricks", BrokenBricksBlock::new, AbstractBlock.Settings.create().nonOpaque(), true);
        WHITE_BRICKS = register("white_bricks", Block::new, AbstractBlock.Settings.create(), true);
        //public static final Block CORRUPTED_BLOCK = registerBlock("corrupted_block",new Block(FabricBlockSettings.create().collidable(false)));
        BIOLOGICAL_PIPE = register("biological_pipe", BiologicalPipeBlock::new, AbstractBlock.Settings.create().slipperiness(0.98f), true);
        CORRUPTED_BLOCK = register("corrupted_block", CorruptedBlock::new, AbstractBlock.Settings.create().noCollision().strength(-1.0f, 3600000.8F).dropsNothing(), true);

        // Plants
        ALMOND_TREE_SAPLING = register("almond_tree_sapling", settings -> new SaplingBlock(ModSaplingGenerator.ALMOND_TREE, settings), true);

        // Tree Groups
        ALMOND_TREE_LOG = register("almond_tree_log", PillarBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_LOG), true);
        ALMOND_TREE_WOOD = register("almond_tree_wood", PillarBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_WOOD), true);
        ALMOND_TREE_LEAVES = register("almond_tree_leaves", LeavesBlock::new, Blocks.createLeavesSettings(BlockSoundGroup.GRASS), true);
        ALMOND_TREE_PLANKS = register("almond_tree_planks", Block::new, AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(4f), true);
        ALMOND_TREE_SLAB = register("almond_tree_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(4f), true);
        ALMOND_TREE_STAIRS = register("almond_tree_stairs", settings -> createStairsBlock(ALMOND_TREE_PLANKS, settings), true);
        ALMOND_TREE_DOOR = register("almond_tree_door", settings -> new DoorBlock(BlockSetType.OAK, settings.nonOpaque()), true);
        ALMOND_TREE_TRAPDOOR = register("almond_tree_trapdoor", settings -> new TrapdoorBlock(BlockSetType.OAK, settings.nonOpaque()), true);
        ALMOND_TREE_FENCE = register("almond_tree_fence", FenceBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_FENCE), true);
        ALMOND_TREE_FENCE_GATE = register("almond_tree_fence_gate", (settings -> new FenceGateBlock(WoodType.OAK, settings)), true);
        ALMOND_TREE_BUTTON = register("almond_tree_button", settings -> new ButtonBlock(BlockSetType.OAK, 35, settings), true);
        ALMOND_TREE_PRESSURE_PLATE = register("almond_tree_pressure_plate", (settings -> new PressurePlateBlock(BlockSetType.OAK, settings)), true);
    }
}
