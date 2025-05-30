package projectbackroom.jonathanx.world.component;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenContext {
    public final World world;
    public final BlockPos origin;
    public final Random random;

    public WorldGenContext(World world, BlockPos origin, Random random){
        this.world = world;
        this.origin = origin;
        this.random = random;
    }
}
