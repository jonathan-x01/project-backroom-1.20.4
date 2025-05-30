package projectbackroom.jonathanx.world.chunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.JigsawBlockEntity;
import net.minecraft.block.entity.StructureBlockBlockEntity;
import net.minecraft.block.enums.Orientation;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.structure.pool.EmptyPoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.noise.NoiseConfig;
import projectbackroom.jonathanx.ProjectBackroom;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class InfiniteJigsaw extends ChunkGenerator {
    public static final Codec<InfiniteJigsaw> CODEC = RecordCodecBuilder.create(instance ->
       instance.group(
               BiomeSource.CODEC.fieldOf("biome_source").forGetter(InfiniteJigsaw::getBiomeSource),
               Codec.STRING.fieldOf("jigsaw_name").forGetter(InfiniteJigsaw::getName),
               Codec.STRING.fieldOf("target_pool").forGetter(InfiniteJigsaw::getTargetPool)
       ).apply(instance, InfiniteJigsaw::new)
    );
    public final String name;
    public final String targetPool;
    public StructureTemplateManager structureManager;

    public InfiniteJigsaw(BiomeSource biomeSource, String name, String targetPool) {
        super(biomeSource);
        this.name = name;
        this.targetPool = targetPool;
    }

    public void setStructureManager(StructureTemplateManager structureManager) {
        this.structureManager = structureManager;
    }

    public String getName(){
        return this.name;
    }

    public String getTargetPool(){
        return this.targetPool;
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public void carve(ChunkRegion chunkRegion, long seed, NoiseConfig noiseConfig, BiomeAccess biomeAccess, StructureAccessor structureAccessor, Chunk chunk, GenerationStep.Carver carverStep) {

    }

    public void placeInfiniteJigsaw(World world, BlockPos pos){
        // Logic to place a jigsaw block and attach structures
        JigsawBlock jigsawBlock = (JigsawBlock) Blocks.JIGSAW;
        world.setBlockState(pos, jigsawBlock.getDefaultState());

        BlockState blockState = Blocks.STRUCTURE_BLOCK.getDefaultState();
        ProjectBackroom.debug(pos.getX());
        ProjectBackroom.debug(pos.getY());
        ProjectBackroom.debug(pos.getZ());

        if (world.setBlockState(pos, blockState)){
            Block block = world.getBlockState(pos).getBlock();
            if (block instanceof StructureBlock structureBlock){
                BlockEntity entity = structureBlock.createBlockEntity(pos, blockState);
                if (entity instanceof StructureBlockBlockEntity blockEntity){
                    ProjectBackroom.debug(blockEntity.getTemplateName());
                }
            }
        }
    }

    private void placeStructure(StructureWorldAccess world, BlockPos pos, Identifier structureId){
        StructureTemplate structure = structureManager.getTemplate(structureId).orElse(null);
        if (structure != null){
            StructurePlacementData placementData = new StructurePlacementData();
            structure.place(world, pos, pos, placementData, world.getRandom(), 2);
        }
    }

    private final Map<ChunkPos, List<BlockPos>> pendingJigsaws = new HashMap<>();

    private void queueDeferredJigsaw(ChunkPos chunk, BlockPos jigsawPos) {
        pendingJigsaws.computeIfAbsent(chunk, k -> new ArrayList<>()).add(jigsawPos);
    }

    private List<BlockPos> findJigsawBlocksInArea(StructureWorldAccess world, BlockPos origin, StructureTemplate template) {
        List<BlockPos> jigsawBlocks = new ArrayList<>();
        Vec3i size = template.getSize();
        BlockPos end = origin.add(size.getX(), size.getY(), size.getZ());

        Box box = new Box(
                new Vec3d(origin.getX(), origin.getY(), origin.getZ()),
                new Vec3d(end.getX(), end.getY(), end.getZ())
        );

        BlockPos.stream(box).forEach(pos -> {
            if (world.getBlockState(pos).getBlock() instanceof JigsawBlock) {
                jigsawBlocks.add(pos.toImmutable());
            }
        });

        return jigsawBlocks;
    }

    private void generateConnectedStructure(StructureWorldAccess world, StructureTemplateManager templates, BlockPos jigsawPos) {
        /*String msg = "Generate connected structure at: " + jigsawPos;
        System.out.println(msg);
        ProjectBackroom.debug(msg);
        // TODO: Implement based on jigsaw block NBT
        BlockState state = world.getBlockState(jigsawPos);
        if (!(state.getBlock() instanceof JigsawBlock)) return;

        BlockEntity blockEntity = world.getBlockEntity(jigsawPos);
        if (!(blockEntity instanceof JigsawBlockEntity jigsaw)) return;

        Identifier poolId = ((JigsawBlockEntity) blockEntity).getPool().getRegistry();
        Identifier targetName = ((JigsawBlockEntity) blockEntity).getTarget();

        Registry<StructurePool> poolRegistry = world.getRegistryManager().get(RegistryKeys.TEMPLATE_POOL);
        StructurePool pool = poolRegistry.get(poolId);
        if (pool == null) return;

        // Pick a random element from the pool
        StructurePoolElement element = pool.getRandomElement(world.getRandom());
        if (element == null || element == EmptyPoolElement.INSTANCE) return;

        Orientation orientation = state.get(JigsawBlock.ORIENTATION);
        BlockRotation rotation = switch (orientation) {
            case NORTH_UP -> BlockRotation.NONE;
            case EAST_UP -> BlockRotation.COUNTERCLOCKWISE_90;
            case SOUTH_UP -> BlockRotation.CLOCKWISE_180;
            case WEST_UP -> BlockRotation.CLOCKWISE_90;
            default -> BlockRotation.NONE;
        };

        // Calculate rotation and placement
        BlockRotation rotation = BlockRotation.NONE;
        BlockPos offset = jigsawPos.subtract(BlockPos.ORIGIN.offset(orientation));

        // Align with jigsaw connection
        Direction facing = JigsawBlock.getFacing(state);
        BlockPos connectionOffset = BlockPos.ORIGIN.offset(facing);

        // Calculate final position to place the structure
        BlockPos placementPos = jigsawPos.subtract(connectionOffset);

        // Set bounding box for safety (entire chunk or just structure size)
        BlockBox box = BlockBox.create(placementPos, placementPos.add(offset));

        // Place the element
        element.generate(
                templates,
                world,
                null, // structureAccessor, can be null
                this,
                placementPos,
                placementPos,
                rotation,
                box,
                world.getRandom(),
                false
        );*/
    }

    @Override
    public void generateFeatures(StructureWorldAccess world, Chunk chunk, StructureAccessor structureAccessor) {
        ChunkPos chunkPos = chunk.getPos();
        BlockPos origin = chunkPos.getStartPos().add(8, 50, 8); // center of chunk, y = 50

        Identifier startStructure = new Identifier(this.targetPool);

        StructureTemplateManager templateManager = Objects.requireNonNull(world.getServer()).getStructureTemplateManager();

        /*templateManager.streamTemplates()
                .filter(template -> "project_backroom".equals(template.getNamespace()))
                .forEach(template -> {
            ProjectBackroom.debug("Loaded template: " + template.getPath());
        });*/

        Optional<StructureTemplate> optional = templateManager.getTemplate(startStructure);
        if (optional.isEmpty()) {
            ProjectBackroom.debug("Structure not found: " + startStructure);
            return;
        }
        StructureTemplate structureTemplate = optional.get();

        StructurePlacementData placementData = new StructurePlacementData()
                .setIgnoreEntities(true)
                .setRotation(BlockRotation.NONE)
                .setMirror(BlockMirror.NONE);

        structureTemplate.place(world, origin, origin, placementData, world.getRandom(), 2);

        List<BlockPos> jigsaws = findJigsawBlocksInArea(world, origin, structureTemplate);

        for (BlockPos jigsawPos : jigsaws){
            ChunkPos jigsawChunk = new ChunkPos(jigsawPos);
            if (jigsawChunk.equals(chunkPos)){
                generateConnectedStructure(world, templateManager, jigsawPos);
            } else {
                queueDeferredJigsaw(jigsawChunk, jigsawPos);
            }
        }

    }

    /*public void backup(){
        ProjectBackroom.debug("Hello");
        MinecraftServer mcWorld = world.getServer();
        Identifier poolId = new Identifier(this.targetPool);
        if (mcWorld == null) return;
        ProjectBackroom.debug("World!");

        this.setStructureManager(mcWorld.getStructureTemplateManager());
        if (structureManager == null) return;
        ProjectBackroom.debug("Minecraft");

        Registry<StructurePool> poolRegistry = world.getRegistryManager().get(RegistryKeys.TEMPLATE_POOL);
        StructurePool startPool = poolRegistry.get(poolId);
        if (startPool == null) return;
        ProjectBackroom.debug("Rocks");

        RegistryKey<StructurePool> poolKey = RegistryKey.of(RegistryKeys.TEMPLATE_POOL, poolId);
        RegistryEntry<StructurePool> entry = poolRegistry.getEntry(poolKey).orElse(null);

        StructurePoolElement element = startPool.getRandomElement(world.getRandom());
        if (element == null || element == EmptyPoolElement.INSTANCE) return;

        StructureTemplate.StructureBlockInfo anchor = element.getStructureBlockInfos(
                structureManager, BlockPos.ORIGIN, BlockRotation.NONE, world.getRandom()
        ).stream().findFirst().orElse(null);

        BlockPos startPos = chunk.getPos().getStartPos();

        Vec3i first = new Vec3i(startPos.getX(), startPos.getY(), startPos.getZ());
        Vec3i second = new Vec3i(startPos.getX() + 15, 255, startPos.getZ() + 15);
        BlockBox box = BlockBox.create(first, second);

        element.generate(
                structureManager,
                world,
                structureAccessor,
                this,
                startPos,
                startPos,
                BlockRotation.NONE,
                box,
                world.getRandom(),
                false
        );
    }*/

    @Override
    public void buildSurface(ChunkRegion region, StructureAccessor structures, NoiseConfig noiseConfig, Chunk chunk) {
        //BlockPos pos = chunk.getPos().getStartPos();
        ProjectBackroom.debug("Building surface");

    }

    @Override
    public void populateEntities(ChunkRegion region) {

    }

    @Override
    public int getWorldHeight() {
        return 384;
    }

    @Override
    public CompletableFuture<Chunk> populateNoise(Executor executor, Blender blender, NoiseConfig noiseConfig, StructureAccessor structureAccessor, Chunk chunk) {
        BlockPos pos = chunk.getPos().getStartPos();

        return CompletableFuture.completedFuture(chunk);
    }

    @Override
    public int getSeaLevel() {
        return 0;
    }

    @Override
    public int getMinimumY() {
        return 0;
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmap, HeightLimitView world, NoiseConfig noiseConfig) {
        return 0;
    }

    @Override
    public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world, NoiseConfig noiseConfig) {
        return new VerticalBlockSample(world.getBottomY(),new BlockState[0]);
    }

    @Override
    public void getDebugHudText(List<String> text, NoiseConfig noiseConfig, BlockPos pos) {

    }
}
