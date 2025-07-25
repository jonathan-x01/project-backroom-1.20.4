package projectbackroom.jonathanx.world.gen.chunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import eu.irzinfante.wfc4j.enums.Side2D;
import eu.irzinfante.wfc4j.exceptions.TileException;
import eu.irzinfante.wfc4j.model.Tile;
import eu.irzinfante.wfc4j.model.TileMap2D;
import net.minecraft.block.*;
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

import java.util.*;
import java.util.HashSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class WFCChunkGenerator extends ChunkGenerator {
    public static final MapCodec<WFCChunkGenerator> CODEC = RecordCodecBuilder.mapCodec(instance ->
       instance.group(
               BiomeSource.CODEC.fieldOf("biome_source").forGetter(WFCChunkGenerator::getBiomeSource),
               Codec.STRING.fieldOf("jigsaw_name").forGetter(WFCChunkGenerator::getName),
               Codec.STRING.fieldOf("target_pool").forGetter(WFCChunkGenerator::getTargetPool)
       ).apply(instance, WFCChunkGenerator::new)
    );
    public final String name;
    public final String targetPool;
    //public StructureTemplateManager structureManager;
    private TileMap2D<String> tileMap;

    public WFCChunkGenerator(BiomeSource biomeSource, String name, String targetPool) {
        super(biomeSource);
        this.name = name;
        this.targetPool = targetPool;
    }

    public String getName(){
        return this.name;
    }

    public String getTargetPool(){
        return this.targetPool;
    }

    @Override
    protected MapCodec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public void carve(ChunkRegion chunkRegion, long seed, NoiseConfig noiseConfig, BiomeAccess biomeAccess, StructureAccessor structureAccessor, Chunk chunk) {

    }

    @SafeVarargs
    public static HashSet<Tile<String>> createAdjacent(Tile<String>... tile){
        return new HashSet<Tile<String>>(Arrays.asList(tile));
    }

    public void setAdjacents(Tile<String> tile, HashSet<Tile<String>> top, HashSet<Tile<String>> bottom, HashSet<Tile<String>> left, HashSet<Tile<String>> right) throws TileException {
        tileMap.setAdjacents(tile, Side2D.Top, top);
        tileMap.setAdjacents(tile, Side2D.Bottom, bottom);
        tileMap.setAdjacents(tile, Side2D.Left, left);
        tileMap.setAdjacents(tile, Side2D.Right, right);
    }

    public void setAdjacents(Tile<String> tile, HashSet<Tile<String>> adjacent) throws TileException {
        for (Side2D side : Side2D.values()) {
            tileMap.setAdjacents(tile, side, adjacent);
        }
    }

    @Override
    public void generateFeatures(StructureWorldAccess world, Chunk chunk, StructureAccessor structureAccessor) {

    }

    /*public void backup(){
        Tile<String>
                HALLWAY_NS = new Tile<>("HALLWAY_NS"),
                HALLWAY_EW = new Tile<>("HALLWAY_EW"),
                INTERSECTION_4WAY = new Tile<>("INTERSECTION_4WAY"),
                INTERSECTION_3WAY = new Tile<>("INTERSECTION_3WAY"),
                ROOM_SMALL = new Tile<>("ROOM_SMALL"),
                ROOM_LARGE = new Tile<>("ROOM_LARGE"),
                DEAD_END = new Tile<>("DEAD_END");

        var tileSet = createAdjacent(
                HALLWAY_EW, HALLWAY_NS,
                INTERSECTION_4WAY, INTERSECTION_3WAY,
                ROOM_SMALL, ROOM_LARGE,
                DEAD_END
        );

        var tileSet = createAdjacent(
                HALLWAY_EW, HALLWAY_NS,
                ROOM_SMALL,
                INTERSECTION_4WAY
        );

        try {
            tileMap = new TileMap2D<>(tileSet);

            this.setAdjacents(HALLWAY_NS,
                    createAdjacent(HALLWAY_NS, ROOM_SMALL, INTERSECTION_4WAY),
                    createAdjacent(HALLWAY_NS, ROOM_SMALL, INTERSECTION_4WAY),
                    createAdjacent(ROOM_SMALL, INTERSECTION_4WAY),
                    createAdjacent(ROOM_SMALL, INTERSECTION_4WAY)
            );

            this.setAdjacents(HALLWAY_EW,
                    createAdjacent(ROOM_SMALL, INTERSECTION_4WAY),
                    createAdjacent(ROOM_SMALL, INTERSECTION_4WAY),
                    createAdjacent(HALLWAY_EW, ROOM_SMALL, INTERSECTION_4WAY),
                    createAdjacent(HALLWAY_EW, ROOM_SMALL, INTERSECTION_4WAY)
            );

            this.setAdjacents(ROOM_SMALL,
                    createAdjacent(ROOM_SMALL, HALLWAY_NS, HALLWAY_NS, HALLWAY_NS),
                    createAdjacent(ROOM_SMALL, HALLWAY_NS, HALLWAY_NS, HALLWAY_NS),
                    createAdjacent(ROOM_SMALL, HALLWAY_EW, HALLWAY_EW, HALLWAY_EW),
                    createAdjacent(ROOM_SMALL, HALLWAY_EW, HALLWAY_EW, HALLWAY_EW)
            );

            this.setAdjacents(INTERSECTION_4WAY,
                    createAdjacent(HALLWAY_NS, HALLWAY_EW)
            );

            // === HALLWAY_NS ===
            this.setAdjacents(HALLWAY_NS,
                    createAdjacent(HALLWAY_NS, INTERSECTION_4WAY, INTERSECTION_3WAY, DEAD_END, ROOM_SMALL, ROOM_LARGE),
                    createAdjacent(HALLWAY_NS, INTERSECTION_4WAY, INTERSECTION_3WAY, DEAD_END, ROOM_SMALL, ROOM_LARGE),
                    createAdjacent(DEAD_END, ROOM_SMALL, ROOM_LARGE),
                    createAdjacent(DEAD_END, ROOM_SMALL, ROOM_LARGE)
            );

            // === HALLWAY_EW ===
            this.setAdjacents(HALLWAY_EW,
                    createAdjacent(HALLWAY_EW, INTERSECTION_4WAY, INTERSECTION_3WAY, DEAD_END),
                    createAdjacent(HALLWAY_EW, INTERSECTION_4WAY, INTERSECTION_3WAY, DEAD_END),
                    createAdjacent(DEAD_END, ROOM_SMALL, ROOM_LARGE),
                    createAdjacent(DEAD_END, ROOM_SMALL, ROOM_LARGE)
            );

            // === INTERSECTION_4WAY ===
            this.setAdjacents(INTERSECTION_4WAY, createAdjacent(HALLWAY_NS, HALLWAY_EW, DEAD_END, ROOM_SMALL, ROOM_LARGE));

            // === INTERSECTION_3WAY ===
            this.setAdjacents(INTERSECTION_3WAY,createAdjacent(HALLWAY_NS, HALLWAY_EW, DEAD_END, ROOM_SMALL));

            // === DEAD_END ===
            this.setAdjacents(DEAD_END,createAdjacent(HALLWAY_NS, HALLWAY_EW));

            // === ROOM_SMALL ===
            this.setAdjacents(ROOM_SMALL,
                    createAdjacent(HALLWAY_NS, HALLWAY_EW, INTERSECTION_4WAY),
                    createAdjacent(HALLWAY_NS, HALLWAY_EW, INTERSECTION_4WAY),
                    createAdjacent(HALLWAY_EW, HALLWAY_NS, INTERSECTION_4WAY, INTERSECTION_3WAY),
                    createAdjacent(HALLWAY_EW, HALLWAY_NS, INTERSECTION_4WAY, INTERSECTION_3WAY)
            );


            // === ROOM_LARGE ===
            this.setAdjacents(ROOM_LARGE,
                    createAdjacent(HALLWAY_NS, HALLWAY_EW, INTERSECTION_4WAY),
                    createAdjacent(HALLWAY_NS, HALLWAY_EW, INTERSECTION_4WAY),
                    createAdjacent(HALLWAY_EW, HALLWAY_NS, INTERSECTION_4WAY),
                    createAdjacent(HALLWAY_EW, HALLWAY_NS, INTERSECTION_4WAY)
            );

            int gridSizeX = 16, gridSizeY = 16;
            var WFC = new WaveFunctionCollapseEuclidean2D<String>(tileMap, gridSizeX, gridSizeY, world.getSeed());

            WFC.clear();
            Cell2D<String>[][] result = WFC.generate();
            //ProjectBackroom.LOGGER.info(WFCUtils.WFC2DToString(result));
            for (int z = 0; z < result.length; z++) {
                for (int x = 0; x < result[z].length; x++) {
                    int dungeonX = origin.getStartX() + x;
                    int dungeonZ = origin.getStartZ() + z;

                    Cell2D<String> cell2D = result[z][x];
                    String value = cell2D.getTile().getValue();
                    int height = 3;
                    switch (value) {
                        case "HALLWAY_NS", "HALLWAY_EW", "INTERSECTION_4WAY", "INTERSECTION_3WAY" -> {
                            mutable.set(dungeonX, height, dungeonZ);
                            chunk.setBlockState(mutable, Blocks.ORANGE_WOOL.getDefaultState(), false);
                        }
                        case "ROOM_SMALL" -> {
                            mutable.set(dungeonX, height, dungeonZ);
                            chunk.setBlockState(mutable, Blocks.GREEN_WOOL.getDefaultState(), false);
                        }
                        case "ROOM_LARGE" -> {
                            mutable.set(dungeonX, height, dungeonZ);
                            chunk.setBlockState(mutable, Blocks.BROWN_WOOL.getDefaultState(), false);
                        }
                        case "DEAD_END" -> {
                            mutable.set(dungeonX, 4, dungeonZ);
                            chunk.setBlockState(mutable, ModdedBlocks.BIOLOGICAL_PIPE.getDefaultState(), false);
                        }
                        default -> {
                            mutable.set(dungeonX, height, dungeonZ);
                            chunk.setBlockState(mutable, Blocks.STONE.getDefaultState(), false);
                        }
                    }
                }
            }
        } catch (TileException | DimensionException e) {
            throw new RuntimeException(e);
        }
    }*/

    @Override
    public void buildSurface(ChunkRegion region, StructureAccessor structures, NoiseConfig noiseConfig, Chunk chunk) {

    }

    @Override
    public void populateEntities(ChunkRegion region) {

    }

    @Override
    public int getWorldHeight() {
        return 384;
    }

    @Override
    public CompletableFuture<Chunk> populateNoise(Blender blender, NoiseConfig noiseConfig, StructureAccessor structureAccessor, Chunk chunk) {
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
    public void appendDebugHudText(List<String> text, NoiseConfig noiseConfig, BlockPos pos) {

    }
}
