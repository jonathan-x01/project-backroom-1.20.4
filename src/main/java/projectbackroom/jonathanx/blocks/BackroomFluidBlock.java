package projectbackroom.jonathanx.blocks;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;

import java.util.Map;

public class BackroomFluidBlock extends FluidBlock {
    private static final Map<FlowableFluid, BackroomFluidBlock> FLUIDS = Maps.newIdentityHashMap();
    private final int primaryColor;
    private final FlowableFluid fluid;

    public BackroomFluidBlock(FlowableFluid fluid, Settings settings, int primaryColor) {
        super(fluid, settings);
        this.primaryColor = primaryColor;
        this.fluid = fluid;
        FLUIDS.put(fluid, this);
    }

    public static Iterable<BackroomFluidBlock> getAll(){
        return Iterables.unmodifiableIterable(FLUIDS.values());
    }

    public int getColor(int tintIndex) {
        return tintIndex == 0 ? this.primaryColor : 0;
    }

    public FlowableFluid getFluid(){
        return this.fluid;
    }
}
