package projectbackroom.jonathanx.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import org.jetbrains.annotations.Nullable;
import projectbackroom.jonathanx.blocks.BackroomBlocks;
import projectbackroom.jonathanx.blocks.fluids.BackroomFluidBlock;
import projectbackroom.jonathanx.particle.BackroomParticleTypes;

public class AlmondWaterFluid extends BackroomsFlowableFluid {
    public AlmondWaterFluid(){
        super(SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.BLOCKS);
    }

    @Override
    public Fluid getFlowing() {
        return BackroomFluids.FLOWING_ALMOND_WATER;
    }

    @Override
    public Fluid getStill() {
        return BackroomFluids.ALMOND_WATER;
    }

    @Override
    public Item getBucketItem() {
        return null;
    }

    @Override
    public ItemStack getBottleItem() {
        return null;
    }

    @Override
    public BackroomFluidBlock getFluidBlock() {
        return (BackroomFluidBlock) BackroomBlocks.ALMOND_WATER_BLOCK;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return BackroomBlocks.ALMOND_WATER_BLOCK.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    @Nullable
    @Override
    public ParticleEffect getParticle() {
        return BackroomParticleTypes.DRIPPING_ALMOND_WATER;
    }

    public static class Flowing extends AlmondWaterFluid {
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

    public static class Still extends AlmondWaterFluid {
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
