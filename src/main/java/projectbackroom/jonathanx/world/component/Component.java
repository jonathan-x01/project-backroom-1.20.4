package projectbackroom.jonathanx.world.component;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.storage.ChunkDataAccess;

import java.util.List;

public abstract class Component {
    protected BlockPos startPos;  // The starting position in the world
    protected int width, height, length;
    protected List<ConnectionPoint> connections;  // Points where other components can attach

    public abstract void generate(ChunkGenerator chunkGenerator, ChunkDataAccess chunkAccess);

    public List<ConnectionPoint> getConnections() {
        return connections;
    }

    // Utility to check if components can connect at certain points
    public abstract boolean canConnectTo(Component other, ConnectionPoint thisPoint, ConnectionPoint otherPoint);
}
