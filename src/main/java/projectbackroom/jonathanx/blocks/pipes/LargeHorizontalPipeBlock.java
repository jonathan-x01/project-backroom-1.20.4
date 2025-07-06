package projectbackroom.jonathanx.blocks.pipes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import projectbackroom.jonathanx.blocks.state.pipeTypes.LargeHorizontalPipeTypes;
import projectbackroom.jonathanx.blocks.state.pipeTypes.TypedPipeSupport;

import static projectbackroom.jonathanx.blocks.pipes.ConnectorPipeBlock.CONNECTOR;

public class LargeHorizontalPipeBlock extends PipeBlock implements TypedPipeSupport {
    public static final EnumProperty<LargeHorizontalPipeTypes> TYPE = TypedPipeSupport.build(LargeHorizontalPipeTypes.class);
    public LargeHorizontalPipeBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder
                .add(CONNECTOR)
                .add(TYPE);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
}
