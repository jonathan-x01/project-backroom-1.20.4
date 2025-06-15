package projectbackroom.jonathanx.world;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.*;

public class Tile {
    public final String id;
    public final Map<Direction, Set<String>> allowedNeighbors;
    public final Identifier nbtStructureId;

    public Tile(String id, Identifier structureId) {
        this.id = id;
        this.nbtStructureId = structureId;
        this.allowedNeighbors = new EnumMap<>(Direction.class);
    }

    public void allow(Direction dir, String... neighborIds) {
        allowedNeighbors.put(dir, new HashSet<>(Arrays.asList(neighborIds)));
    }
}
