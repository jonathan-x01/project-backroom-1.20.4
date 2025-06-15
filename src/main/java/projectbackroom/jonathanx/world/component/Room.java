package projectbackroom.jonathanx.world.component;

import net.minecraft.util.math.BlockBox;

public class Room {
    public final BlockBox bounds;

    public Room(BlockBox box) {
        this.bounds = box;
    }

    public int centerX() {
        return (bounds.getMinX() + bounds.getMaxX()) / 2;
    }

    public int centerZ() {
        return (bounds.getMinZ() + bounds.getMaxZ()) / 2;
    }
}

