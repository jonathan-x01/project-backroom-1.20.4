package projectbackroom.jonathanx.block.pipes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import projectbackroom.jonathanx.block.state.pipeTypes.LargeHorizontalPipeTypes;
import projectbackroom.jonathanx.block.state.pipeTypes.TypedPipeSupport;
import projectbackroom.jonathanx.util.DebugLogger;

import static projectbackroom.jonathanx.block.pipes.ConnectorPipeBlock.CONNECTOR;

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
    public BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        BlockState north = world.getBlockState(pos.north());
        BlockState east = world.getBlockState(pos.east());
        BlockState south = world.getBlockState(pos.south());
        BlockState west = world.getBlockState(pos.west());
        DebugLogger.debug(
                north,
                east,
                south,
                west
        );
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }
}
