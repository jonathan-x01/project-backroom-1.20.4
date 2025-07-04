package projectbackroom.jonathanx.blocks.pipes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.blocks.BackroomBlocks;
import projectbackroom.jonathanx.blocks.state.pipeTypes.CeilingPipeTypes;
import projectbackroom.jonathanx.blocks.state.pipeTypes.TypedPipeSupport;
import projectbackroom.jonathanx.blocks.state.pipeTypes.VerticalPipeTypes;

public class CeilingPipeBlock extends PipeBlock implements TypedPipeSupport {
    public static final EnumProperty<CeilingPipeTypes> TYPE = TypedPipeSupport.build(CeilingPipeTypes.class);
    public CeilingPipeBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(TYPE);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);

        BlockState newState = world.getBlockState(sourcePos);
        Block newBlock = newState.getBlock();
        if (newBlock == BackroomBlocks.VERTICAL_PIPE){
            if (newState.get(VerticalPipeBlock.FACING) instanceof Direction){
                Direction direction = newState.get(VerticalPipeBlock.FACING);
                world.setBlockState(pos, state.with(CeilingPipeBlock.TYPE, CeilingPipeTypes.ONE_LAYER_VERTICAL_PIPE_CONNECTION).with(CeilingPipeBlock.FACING, direction.getOpposite()));
            }
        }
    }
}
