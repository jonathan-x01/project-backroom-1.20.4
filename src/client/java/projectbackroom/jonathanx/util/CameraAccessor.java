package projectbackroom.jonathanx.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public interface CameraAccessor {
    BlockView getArea();
    BlockPos getBlockPos();
}
