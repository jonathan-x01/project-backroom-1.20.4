package projectbackroom.jonathanx.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.util.Random;

public class PipeBlock extends Block {
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
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(LEAKING,leakingDefault);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(LEAKING);
    }

    public double getRandomPosition(){
        double random = new Random().nextDouble();
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(random));
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        super.randomDisplayTick(state, world, pos, random);
        double chance = random.nextDouble();
        if (chance < 0.2f){
            boolean leaking = state.get(LEAKING);
            double x = pos.getX() + getRandomPosition();
            double y = pos.getY();
            double z = pos.getZ() + getRandomPosition();
            if (leaking){
                world.addParticle(ParticleTypes.DRIPPING_WATER, x, y, z, 0.0f, 0.0f, 0.0f);
            }
        }
    }
}
