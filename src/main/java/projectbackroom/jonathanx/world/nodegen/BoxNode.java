package projectbackroom.jonathanx.world.nodegen;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;

public class BoxNode implements Node {

    private final int width;
    private final int height;
    private final int length;
    private final BlockState block;

    public BoxNode(int width, int height, int length, BlockState block){
        this.width = width;
        this.height = height;
        this.length = length;
        this.block = block;
    }

    @Override
    public void apply(WorldGenContext context) {
        BlockPos origin = context.origin;

        for (int x = 0; x < this.width; x++){
            for (int y = 0; y < this.height; y++){
                for (int z = 0; z < this.length; z++){
                    BlockPos pos = origin.add(x, y, z);
                    context.world.setBlockState(pos, this.block, 3);
                }
            }
        }
    }
}
