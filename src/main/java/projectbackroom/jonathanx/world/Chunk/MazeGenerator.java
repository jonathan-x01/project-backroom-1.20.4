package projectbackroom.jonathanx.world.Chunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.noise.NoiseConfig;
import projectbackroom.jonathanx.blocks.ModdedBlocks;
import projectbackroom.jonathanx.blocks.level0FluorescentDropceiling;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class MazeGenerator extends ChunkGenerator {
    public static final Codec<MazeGenerator> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BiomeSource.CODEC.fieldOf("biome_source").forGetter(MazeGenerator::getBiomeSource),
                    Codec.INT.fieldOf("sea_level").forGetter(MazeGenerator::getSeaLevel),
                    Codec.INT.fieldOf("world_height").forGetter(MazeGenerator::getWorldHeight),
                    Codec.INT.fieldOf("light_dist").forGetter(MazeGenerator::getLightDist)
            ).apply(instance, MazeGenerator::new)
    );

    private final Block floorBlock = ModdedBlocks.LEVEL_0_YELLOW_CARPET;
    private final Block wallBlock = ModdedBlocks.LEVEL_0_WALLPAPER;
    private  final Block ceilingBlock = ModdedBlocks.LEVEL_0_DROPCEILING;
    private final Block ceilingLight = ModdedBlocks.LEVEL_0_LIGHT;
    private final int seaLevel;
    private final int worldHeight;
    private final int lightDist;

    public MazeGenerator(BiomeSource biomeSource, int seaLevel, int worldHeight, int lightDist) {
        super(biomeSource);
        this.seaLevel = seaLevel;
        this.worldHeight = worldHeight;
        this.lightDist = lightDist;
    }

    public Block getFloorBlock(){
        return floorBlock;
    }

    public Block getWallBlock(){
        return wallBlock;
    }

    public Block getCeilingBlock(){
        return  ceilingBlock;
    }

    public Block getCeilingLight(){
        return ceilingLight;
    }

    public int getLightDist(){
        return  lightDist;
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public void carve(ChunkRegion chunkRegion, long seed, NoiseConfig noiseConfig, BiomeAccess biomeAccess, StructureAccessor structureAccessor, Chunk chunk, GenerationStep.Carver carverStep) {

    }

    public double getRandomPercentage(){
        Random random = new Random();
        return random.nextDouble();
    }

    @Override
    public void buildSurface(ChunkRegion region, StructureAccessor structures, NoiseConfig noiseConfig, Chunk chunk) {
        ChunkPos chunkPos = chunk.getPos();
        BlockPos.Mutable mutable = new BlockPos.Mutable();


        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunkPos.getStartX() + x;
                int worldZ = chunkPos.getStartZ() + z;

                // Create the floor
                for (int y = 0; y < 1; y++) {
                    mutable.set(worldX, y, worldZ);
                    chunk.setBlockState(mutable, getFloorBlock().getDefaultState(), false);
                }

                // Create the randomized wall
                if (getRandomPercentage() < 0.15){
                    for (int y = 1; y < 5; y++) {
                        mutable.set(worldX, y, worldZ);
                        chunk.setBlockState(mutable, getWallBlock().getDefaultState(), false);
                    }
                }

                // Sets the ceiling
                for (int y = 5; y < 6; y++){
                    mutable.set(worldX, y, worldZ);
                    if (x % getLightDist() == 0 && z % getLightDist() == 0){
                        BlockState state = getCeilingLight().getDefaultState().with(level0FluorescentDropceiling.LIGHTING, level0FluorescentDropceiling.getRandomLighting());
                        chunk.setBlockState(mutable, state, false);
                    } else {
                        chunk.setBlockState(mutable, getCeilingBlock().getDefaultState(), false);
                    }
                }
            }
        }
    }

    @Override
    public void populateEntities(ChunkRegion region) {

    }

    @Override
    public int getWorldHeight() {
        return this.worldHeight;
    }

    @Override
    public CompletableFuture<Chunk> populateNoise(Executor executor, Blender blender, NoiseConfig noiseConfig, StructureAccessor structureAccessor, Chunk chunk) {
        return CompletableFuture.completedFuture(chunk);
    }

    @Override
    public int getSeaLevel() {
        return this.seaLevel;
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
