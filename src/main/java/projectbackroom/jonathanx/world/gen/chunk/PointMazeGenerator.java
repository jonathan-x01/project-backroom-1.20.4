package projectbackroom.jonathanx.world.gen.chunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.noise.NoiseConfig;
import projectbackroom.jonathanx.blocks.level0.FluorescentDropceiling;
import projectbackroom.jonathanx.init.BackroomBlocks;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class PointMazeGenerator extends ChunkGenerator {
    public static final MapCodec<PointMazeGenerator> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    BiomeSource.CODEC.fieldOf("biome_source").forGetter(PointMazeGenerator::getBiomeSource),
                    Codec.INT.fieldOf("sea_level").forGetter(PointMazeGenerator::getSeaLevel),
                    Codec.INT.fieldOf("world_height").forGetter(PointMazeGenerator::getWorldHeight),
                    Codec.INT.fieldOf("light_dist").forGetter(PointMazeGenerator::getLightDist)
            ).apply(instance, PointMazeGenerator::new)
    );

    private final Block floorBlock = BackroomBlocks.LEVEL_0_YELLOW_CARPET;
    private final Block wallBlock = BackroomBlocks.LEVEL_0_WALLPAPER;
    private  final Block ceilingBlock = BackroomBlocks.LEVEL_0_DROPCEILING;
    private final Block ceilingLight = BackroomBlocks.LEVEL_0_LIGHT;
    private final int seaLevel;
    private final int worldHeight;
    private final int lightDist;
    private final int wallHeight = 5;

    public PointMazeGenerator(BiomeSource biomeSource, int seaLevel, int worldHeight, int lightDist) {
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
    protected MapCodec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public void carve(ChunkRegion chunkRegion, long seed, NoiseConfig noiseConfig, BiomeAccess biomeAccess, StructureAccessor structureAccessor, Chunk chunk) {

    }

    public double getRandomPercentage(){
        Random random = new Random();
        return random.nextDouble();
    }

    public int buildColumn(int xz, int dir, Chunk chunk, BlockPos.Mutable mutable, Direction direction){
        for (int y = 1; y < wallHeight; y++){
            if (direction.equals(Direction.SOUTH) || direction.equals(Direction.NORTH)){
                mutable.set(xz, y, dir);
            } else {
                mutable.set(dir, y, xz);
            }
            chunk.setBlockState(mutable, getWallBlock().getDefaultState(), false);
        }
        return dir;
    }

    // Positive Z (+Z) = South
    // Negative Z (-Z) = North
    // Positive X (+X) = East
    // Negative X (-X) = West
    public void buildPath(int x, int z, Chunk chunk, BlockPos.Mutable mutable){
        double dirChance = getRandomPercentage();
        int randomPathSize = new Random().nextInt(15);

        int x1 = x;
        int z1 = z;

        for (int i = 0; i < randomPathSize; i++){
            int south = z1 + 1;
            int north = z1 - 1;
            int east = x1 + 1;
            int west = x1 - 1;

            if (dirChance < 0.1){
                // If less than 10%, set path to south.
                z1 = buildColumn(x1, south, chunk, mutable, Direction.SOUTH);
            } else if (dirChance < 0.2){
                // If less than 20%, set path to north
                z1 = buildColumn(x1, north, chunk, mutable, Direction.NORTH);
            } else if (dirChance < 0.3){
                // If less than 30%, set path to east.
                x1 = buildColumn(z1, east, chunk, mutable, Direction.EAST);
            } else if (dirChance < 0.4){
                // If less than 40%, set path to west.
                x1 = buildColumn(z1, west, chunk, mutable, Direction.WEST);
            } else {
                // If less than 100%, it will stop the path builder.
                break;
            }
        }
    }

    @Override
    public void populateEntities(ChunkRegion region) {

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

                boolean pathSet = false;
                // Create the randomized wall
                if (getRandomPercentage() < 0.05){
                    for (int y = 1; y < wallHeight; y++) {
                        mutable.set(worldX, y, worldZ);
                        chunk.setBlockState(mutable, getWallBlock().getDefaultState(), false);
                        if (!pathSet){
                            buildPath(worldX, worldZ, chunk, mutable);
                            pathSet = true;
                        }
                    }
                }

                // Sets the ceiling
                for (int y = wallHeight; y < 6; y++){
                    mutable.set(worldX, y, worldZ);
                    if (x % getLightDist() == 0 && z % getLightDist() == 0){
                        BlockState state = getCeilingLight().getDefaultState().with(FluorescentDropceiling.LIGHTING, FluorescentDropceiling.getRandomLighting());
                        chunk.setBlockState(mutable, state, false);
                    } else {
                        chunk.setBlockState(mutable, getCeilingBlock().getDefaultState(), false);
                    }
                }
            }
        }
    }

    @Override
    public int getWorldHeight() {
        return this.worldHeight;
    }

    @Override
    public CompletableFuture<Chunk> populateNoise(Blender blender, NoiseConfig noiseConfig, StructureAccessor structureAccessor, Chunk chunk) {
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
    public void appendDebugHudText(List<String> text, NoiseConfig noiseConfig, BlockPos pos) {

    }
}
