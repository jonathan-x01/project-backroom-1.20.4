package projectbackroom.jonathanx.world.chunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.StructureBlockBlockEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
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

import java.util.List;
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

    @Override
    public void generateFeatures(StructureWorldAccess world, Chunk chunk, StructureAccessor structureAccessor) {
        MinecraftServer mcWorld = world.getServer();
        if (mcWorld != null){
            this.setStructureManager(mcWorld.getStructureTemplateManager());
        } else {
            throw new IllegalStateException("mcWorld not set!");
        }

        if (structureManager == null){
            throw new IllegalStateException("StructureTemplateManager not set!");
        }

        BlockPos startPos = chunk.getPos().getStartPos();

        placeStructure(world, startPos, new Identifier(this.name));
        //placeStructure(world, startPos.add(16, 0, 0), new Identifier("yournamespace", "2genericpillar2"));
        //placeStructure(world, startPos.add(32, 0, 0), new Identifier("yournamespace", "2genericpillar3"));
    }

    @Override
    public void buildSurface(ChunkRegion region, StructureAccessor structures, NoiseConfig noiseConfig, Chunk chunk) {
        //BlockPos pos = chunk.getPos().getStartPos();


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
