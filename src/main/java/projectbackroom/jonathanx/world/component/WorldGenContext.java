package projectbackroom.jonathanx.world.component;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public record WorldGenContext(World world, BlockPos origin, Random random) { }
