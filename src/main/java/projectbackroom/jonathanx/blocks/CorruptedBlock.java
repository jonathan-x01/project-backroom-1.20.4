package projectbackroom.jonathanx.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import projectbackroom.jonathanx.state.property.DestinationProperty;

public class CorruptedBlock extends Block {
    public static final DestinationProperty DESTINATION = DestinationProperty.of("destination");

    public CorruptedBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DESTINATION);
    }
}
