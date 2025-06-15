package projectbackroom.jonathanx.world.component;

import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.Vec3i;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level1LayoutGenerator {
    private final Random random;
    private final List<Room> rooms = new ArrayList<>();
    private final List<BlockBox> hallways = new ArrayList<>();

    public Level1LayoutGenerator(long seed) {
        this.random = new Random(seed);
    }

    public void generate(int areaX, int areaZ, int sizeX, int sizeZ) {
        rooms.clear();
        hallways.clear();

        // Step 1: Add rooms
        for (int i = 0; i < 20; i++) {
            int roomWidth = 6 + random.nextInt(6);  // 6-11
            int roomHeight = 6 + random.nextInt(6);
            int x = areaX + random.nextInt(sizeX - roomWidth);
            int z = areaZ + random.nextInt(sizeZ - roomHeight);

            BlockBox box = new BlockBox(x, 64, z, x + roomWidth, 64 + 4, z + roomHeight);

            boolean overlaps = false;
            for (Room other : rooms) {
                if (other.bounds.intersects(box)) {
                    overlaps = true;
                    break;
                }
            }

            if (!overlaps) {
                rooms.add(new Room(box));
            }
        }

        // Step 2: Connect rooms with hallways
        for (int i = 1; i < rooms.size(); i++) {
            Room a = rooms.get(i - 1);
            Room b = rooms.get(i);

            int ax = a.centerX();
            int az = a.centerZ();
            int bx = b.centerX();
            int bz = b.centerZ();

            if (random.nextBoolean()) {
                hallways.add(BlockBox.create(new Vec3i(ax, 64, az), new Vec3i(bx, 68, az)));
                hallways.add(BlockBox.create(new Vec3i(bx, 64, az), new Vec3i(bx, 68, bz)));
            } else {
                hallways.add(BlockBox.create(new Vec3i(ax, 64, az), new Vec3i(ax, 68, bz)));
                hallways.add(BlockBox.create(new Vec3i(ax, 64, bz), new Vec3i(bx, 68, bz)));
            }
        }
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<BlockBox> getHallways() {
        return hallways;
    }
}
