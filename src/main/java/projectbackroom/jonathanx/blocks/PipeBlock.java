package projectbackroom.jonathanx.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.util.Random;

public class PipeBlock extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LEAKING = BooleanProperty.of("leaking");

    public PipeBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        double chance = new Random().nextDouble();;
        boolean leakingDefault = chance < 0.2f;
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(LEAKING,leakingDefault).with(WATERLOGGED,false);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(LEAKING).add(WATERLOGGED);
    }

    public double getRandomPosition(double min, double max){
        Random random = new Random();
        double randomDouble = random.nextDouble() * (max - min) + min;
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(randomDouble));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED).booleanValue()) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        super.randomDisplayTick(state, world, pos, random);
        double chance = random.nextDouble();

        VoxelShape shape = state.getOutlineShape(world,pos);
        double minX = shape.getMin(Direction.Axis.X);
        double maxX = shape.getMax(Direction.Axis.X);
        double minZ = shape.getMin(Direction.Axis.Z);
        double maxZ = shape.getMax(Direction.Axis.Z);
        if (chance < 0.2f){
            boolean leaking = state.get(LEAKING);
            double x = pos.getX() + getRandomPosition(minX, maxX);
            double y = pos.getY();
            double z = pos.getZ() + getRandomPosition(minZ, maxZ);
            if (leaking){
                world.addParticle(ParticleTypes.DRIPPING_WATER, x, y, z, 0.0f, 0.0f, 0.0f);
            }
        }
    }
}
