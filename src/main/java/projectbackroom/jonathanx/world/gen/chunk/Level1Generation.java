package projectbackroom.jonathanx.world.gen.chunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
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
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.noise.NoiseConfig;
import projectbackroom.jonathanx.world.gen.components.Level1LayoutGenerator;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class Level1Generation extends ChunkGenerator {
    public static final MapCodec<Level1Generation> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    BiomeSource.CODEC.fieldOf("biome_source").forGetter(Level1Generation::getBiomeSource),
                    Codec.STRING.fieldOf("jigsaw_name").forGetter(Level1Generation::getName),
                    Codec.STRING.fieldOf("target_pool").forGetter(Level1Generation::getTargetPool)
            ).apply(instance, Level1Generation::new)
    );
    private final Map<Long, Level1LayoutGenerator> cachedLayouts = new HashMap<>();

    public String getName() {
        return name;
    }

    public final String name;

    public String getTargetPool() {
        return targetPool;
    }

    public final String targetPool;

    public Level1Generation(BiomeSource biomeSource, String name, String targetPool) {
        super(biomeSource);
        this.name = name;
        this.targetPool = targetPool;
    }

    @Override
    protected MapCodec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public void carve(ChunkRegion chunkRegion, long seed, NoiseConfig noiseConfig, BiomeAccess biomeAccess, StructureAccessor structureAccessor, Chunk chunk) {

    }

    @Override
    public void buildSurface(ChunkRegion region, StructureAccessor structures, NoiseConfig noiseConfig, Chunk chunk) {
        long worldSeed = region.getSeed();
        int chunkX = chunk.getPos().x;
        int chunkZ = chunk.getPos().z;

        final int CHUNK_SIZE = 16;
        final int BASE_Y = 64;

        GlobalMaze maze = new GlobalMaze(worldSeed, 3);
        RoomGenerator roomGen = new RoomGenerator(worldSeed);

        BlockState floorBlock = Blocks.SMOOTH_STONE.getDefaultState();
        BlockState hallwayWall = Blocks.STONE_BRICKS.getDefaultState();
        BlockState roomWall = Blocks.SMOOTH_QUARTZ.getDefaultState();
        BlockState doorBlock = Blocks.OAK_DOOR.getDefaultState();
        BlockPos.Mutable pos = new BlockPos.Mutable();

        for (int dz = 0; dz < CHUNK_SIZE; dz++) {
            for (int dx = 0; dx < CHUNK_SIZE; dx++) {
                int worldX = chunkX * 16 + dx;
                int worldZ = chunkZ * 16 + dz;

                boolean isPath = maze.isPath(worldX, worldZ);
                boolean isRoom = roomGen.isInRoom(worldX, worldZ);
                boolean isOpen = isPath || isRoom;

                for (int y = 0; y <= BASE_Y; y++) {
                    pos.set(dx, y, dz);
                    chunk.setBlockState(pos, y == BASE_Y ? floorBlock : Blocks.AIR.getDefaultState(), false);
                }

                if (!isOpen) {
                    boolean isWallInRoom = roomGen.isInRoom(worldX, worldZ);

                    if (isWallInRoom) {
                        // Check 4 directions for maze path
                        int[][] offsets = {{1,0},{-1,0},{0,1},{0,-1}};
                        for (int[] off : offsets) {
                            int nx = worldX + off[0];
                            int nz = worldZ + off[1];
                            boolean adjacentIsMaze = maze.isPath(nx, nz) && !roomGen.isInRoom(nx, nz);

                            if (adjacentIsMaze) {
                                // Replace wall with a door
                                pos.set(dx, BASE_Y + 1, dz);
                                chunk.setBlockState(pos, Blocks.OAK_DOOR.getDefaultState()
                                        .with(DoorBlock.HALF, DoubleBlockHalf.LOWER)
                                        .with(DoorBlock.FACING, getDoorFacing(off[0], off[1]))
                                        .with(DoorBlock.OPEN, false), false);
                                pos.set(dx, BASE_Y + 2, dz);
                                chunk.setBlockState(pos, Blocks.OAK_DOOR.getDefaultState()
                                        .with(DoorBlock.HALF, DoubleBlockHalf.UPPER)
                                        .with(DoorBlock.FACING, getDoorFacing(off[0], off[1]))
                                        .with(DoorBlock.OPEN, false), false);
                                break;
                            }
                        }
                    }

                    BlockState wall = isWallInRoom ? roomWall : hallwayWall;

                    for (int y = BASE_Y + 1; y <= BASE_Y + 3; y++) {
                        pos.set(dx, y, dz);
                        chunk.setBlockState(pos, wall, false);
                    }
                }
            }
        }
    }

    private static Direction getDoorFacing(int dx, int dz) {
        if (dx == 1) return Direction.EAST;
        if (dx == -1) return Direction.WEST;
        if (dz == 1) return Direction.SOUTH;
        return Direction.NORTH;
    }

    @Override
    public void populateEntities(ChunkRegion region) {

    }

    @Override
    public int getWorldHeight() {
        return 0;
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

    public static class GlobalMaze {
        private final long seed;
        private final int width;

        public GlobalMaze(long seed, int width) {
            this.seed = seed;
            this.width = width;
        }

        public boolean isPath(int x, int z) {
            // Virtual cell grid, treat (x, z) as maze cell coordinates.
            int cellX = x / this.width;
            int cellZ = z / this.width;

            long hash = (((long) cellX) * 341873128712L + ((long) cellZ) * 132897987541L) ^ seed;
            Random rand = new Random(hash);

            // Maze cell is a path or not, based on pseudorandomness.
            return rand.nextFloat() < 0.5f;
        }
    }

    public static class RoomGenerator {
        private final long seed;

        public RoomGenerator(long seed) {
            this.seed = seed;
        }

        public boolean isInRoom(int x, int z) {
            // Room grid size (spacing between room origins)
            final int ROOM_GRID = 32;

            int roomX = Math.floorDiv(x, ROOM_GRID);
            int roomZ = Math.floorDiv(z, ROOM_GRID);

            long hash = ((long) roomX * 341873128712L + (long) roomZ * 132897987541L) ^ seed;
            Random rand = new Random(hash);

            // Room chance (e.g., 20% chance to spawn a room here)
            if (rand.nextFloat() < 0.8f) return false;

            // Determine room size and offset (within ROOM_GRID)
            int size = 20 + rand.nextInt(5); // room size 6-10
            int half = size / 2;
            int centerX = roomX * ROOM_GRID + rand.nextInt(ROOM_GRID);
            int centerZ = roomZ * ROOM_GRID + rand.nextInt(ROOM_GRID);

            // Check if world (x, z) is within this room
            return Math.abs(x - centerX) <= half && Math.abs(z - centerZ) <= half;
        }
    }
}
