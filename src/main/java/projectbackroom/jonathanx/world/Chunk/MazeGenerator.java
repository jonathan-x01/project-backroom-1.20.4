package projectbackroom.jonathanx.world.Chunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
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

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class MazeGenerator extends ChunkGenerator {
    public static final Codec<MazeGenerator> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BiomeSource.CODEC.fieldOf("biome_source").forGetter(MazeGenerator::getBiomeSource),
                    Codec.INT.fieldOf("sea_level").forGetter(MazeGenerator::getSeaLevel),
                    Codec.INT.fieldOf("world_height").forGetter(MazeGenerator::getWorldHeight)
            ).apply(instance, MazeGenerator::new)
    );

    private final Block floorBlock = ModdedBlocks.LEVEL_0_YELLOW_CARPET;
    private final Block wallBlock = ModdedBlocks.LEVEL_0_WALLPAPER;
    private final int seaLevel;
    private final int worldHeight;

    public MazeGenerator(BiomeSource biomeSource, int seaLevel, int worldHeight) {
        super(biomeSource);
        this.seaLevel = seaLevel;
        this.worldHeight = worldHeight;
    }

    public Block getFloorBlock(){
        return floorBlock;
    }

    public Block getWallBlock(){
        return wallBlock;
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public void carve(ChunkRegion chunkRegion, long seed, NoiseConfig noiseConfig, BiomeAccess biomeAccess, StructureAccessor structureAccessor, Chunk chunk, GenerationStep.Carver carverStep) {

    }

    @Override
    public void buildSurface(ChunkRegion region, StructureAccessor structures, NoiseConfig noiseConfig, Chunk chunk) {
        ChunkPos chunkPos = chunk.getPos();
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunkPos.getStartX() + x;
                int worldZ = chunkPos.getStartZ() + z;

                // Set floor block
                for (int y = 0; y < 64; y++) {  // Example: floor up to height 64
                    mutable.set(worldX, y, worldZ);
                    chunk.setBlockState(mutable, this.floorBlock.getDefaultState(), false);
                }

                // Set wall block above the floor
                for (int y = 64; y < 128; y++) {  // Example: wall from height 64 to 128
                    mutable.set(worldX, y, worldZ);
                    chunk.setBlockState(mutable, this.wallBlock.getDefaultState(), false);
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
        return null;
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
        return null;
    }

    @Override
    public void getDebugHudText(List<String> text, NoiseConfig noiseConfig, BlockPos pos) {

    }
}
