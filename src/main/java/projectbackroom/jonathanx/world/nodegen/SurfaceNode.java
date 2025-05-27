package projectbackroom.jonathanx.world.nodegen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;

public class SurfaceNode implements Node {

    public final int width;
    public final int height;
    public final int length;
    public final BlockState block;

    public SurfaceNode(int width, int height, int length, BlockState block){
        this.width = width;
        this.height = height;
        this.length = length;
        this.block = block;
    }

    @Override
    public void apply(WorldGenContext context) {
        BlockPos origin = context.origin;

        for (int x = 0; x < this.width; x++){
            for (int z = 0; z < this.length; z++){
                BlockPos pos = origin.add(x, this.height, z);

                context.world.setBlockState(pos, this.block, 3);
                /*int worldX = chunkPos.getStartX() + x;
                int worldZ = chunkPos.getStartZ() + z;

                nodeGen.getMutable().set(worldX, height, worldZ);
                nodeGen.getChunk().setBlockState(nodeGen.getMutable(), blockState, false);*/
            }
        }
    }
}
