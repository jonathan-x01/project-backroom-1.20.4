package projectbackroom.jonathanx.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import projectbackroom.jonathanx.blocks.BackroomBlocks;

public class BlackSludgeFluid extends BackroomsFlowableFluid {
    public BlackSludgeFluid() {
        super(SoundEvents.BLOCK_HONEY_BLOCK_SLIDE, SoundCategory.BLOCKS);
    }

    @Override
    public Item getBottleItem() {
        return null;
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
