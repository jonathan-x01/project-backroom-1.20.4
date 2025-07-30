package projectbackroom.jonathanx.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import projectbackroom.jonathanx.init.BackroomBlocks;
import projectbackroom.jonathanx.blocks.fluids.BackroomFluidBlock;
import projectbackroom.jonathanx.init.BackroomFluids;

public class BlackSludgeFluid extends BackroomsFlowableFluid {
    public BlackSludgeFluid() {
        super(SoundEvents.BLOCK_HONEY_BLOCK_SLIDE, SoundCategory.BLOCKS);
    }

    @Override
    public Vec3d getVelocity(BlockView world, BlockPos pos, FluidState state) {
        return new Vec3d(0.0, -0.2, 0);
    }

    @Override
    public ItemStack getBottleItem() {
        return null;
    }

    @Override
    public BackroomFluidBlock getFluidBlock() {
        return (BackroomFluidBlock) BackroomBlocks.BLACK_SLUDGE_BLOCK;
    }

    @Override
    public Fluid getFlowing() {
        return BackroomFluids.FLOWING_BLACK_SLUDGE;
    }

    @Override
    public Fluid getStill() {
        return BackroomFluids.BLACK_SLUDGE;
    }

    @Override
    protected boolean isInfinite(ServerWorld world) {
        return false;
    }

    @Override
    protected int getMaxFlowDistance(WorldView world) {
        return 4;
    }

    @Override
    public Item getBucketItem() {
        return null;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return BackroomBlocks.BLACK_SLUDGE_BLOCK.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    public static class Flowing extends BlackSludgeFluid {
        public Flowing() {
        }

        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }

        public boolean isStill(FluidState state) {
            return false;
        }
    }

    public static class Still extends BlackSludgeFluid {
        public Still() {
        }

        public int getLevel(FluidState state) {
            return 8;
        }

        public boolean isStill(FluidState state) {
            return true;
        }
    }
}
