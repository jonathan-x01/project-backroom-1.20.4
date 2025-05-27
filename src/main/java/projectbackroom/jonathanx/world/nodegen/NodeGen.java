package projectbackroom.jonathanx.world.nodegen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.noise.NoiseConfig;

public class NodeGen {
    public static int SEED;
    private ChunkRegion region;
    private StructureAccessor structures;
    private NoiseConfig noiseConfig;
    private Chunk chunk;
    private BlockPos.Mutable mutable;

    public NodeGen(ChunkRegion region, StructureAccessor structures, NoiseConfig noiseConfig, Chunk chunk){
        this.region = region;
        this.structures = structures;
        this.noiseConfig = noiseConfig;
        this.chunk = chunk;

        this.mutable = new BlockPos.Mutable();
    }

    public ChunkRegion getRegion() {
        return region;
    }

    public void setRegion(ChunkRegion region) {
        this.region = region;
    }

    public StructureAccessor getStructures() {
        return structures;
    }

    public void setStructures(StructureAccessor structures) {
        this.structures = structures;
    }

    public NoiseConfig getNoiseConfig() {
        return noiseConfig;
    }

    public void setNoiseConfig(NoiseConfig noiseConfig) {
        this.noiseConfig = noiseConfig;
    }

    public Chunk getChunk() {
        return chunk;
    }

    public void setChunk(Chunk chunk) {
        this.chunk = chunk;
    }

    public BlockPos.Mutable getMutable() {
        return mutable;
    }

    public void setMutable(BlockPos.Mutable mutable) {
        this.mutable = mutable;
    }
}
