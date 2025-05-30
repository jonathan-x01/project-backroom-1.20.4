package projectbackroom.jonathanx.world.component.components;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.storage.ChunkDataAccess;
import projectbackroom.jonathanx.world.component.Component;
import projectbackroom.jonathanx.world.component.ConnectionPoint;
import projectbackroom.jonathanx.world.component.WorldGenContext;

public class BoxComponent extends Component {

    private final int width;
    private final int height;
    private final int length;
    private final BlockState block;

    public BoxComponent(int width, int height, int length, BlockState block){
        this.width = width;
        this.height = height;
        this.length = length;
        this.block = block;
    }

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

    @Override
    public void generate(ChunkGenerator chunkGenerator, ChunkDataAccess chunkAccess) {

    }

    @Override
    public boolean canConnectTo(Component other, ConnectionPoint thisPoint, ConnectionPoint otherPoint) {
        return false;
    }
}
