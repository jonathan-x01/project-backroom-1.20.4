package projectbackroom.jonathanx.block.level2;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class WallLightBlock extends Block {
    public static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty WATER_LOGGED = Properties.WATERLOGGED;
    public static final VoxelShape SHAPE_WEST = Block.createCuboidShape(14.0, 6.0, 0.0, 16.0, 11.0, 16.0);
    public static final VoxelShape SHAPE_NORTH = Block.createCuboidShape(0.0, 6.0, 14.0, 16.0, 11.0, 16.0);
    public static final VoxelShape SHAPE_EAST = Block.createCuboidShape(0.0, 6.0, 0.0, 2.0, 11.0, 16.0);
    public static final VoxelShape SHAPE_SOUTH = Block.createCuboidShape(0.0, 6.0, 0.0, 16.0, 11.0, 2.0);
    public WallLightBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if (ctx.getSide() == Direction.UP || ctx.getSide() == Direction.DOWN){
            return this.getDefaultState().with(FACING,ctx.getHorizontalPlayerFacing().getOpposite());
        }
        return this.getDefaultState().with(FACING,ctx.getSide());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(WATER_LOGGED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            default -> SHAPE_NORTH;
        };
    }
}
