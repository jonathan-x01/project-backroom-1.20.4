package projectbackroom.jonathanx.blocks.fluids;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import org.apache.http.annotation.Obsolete;
import projectbackroom.jonathanx.util.ColorManager;

@FunctionalInterface
public interface FluidBlockFactory {
    Block create(FlowableFluid fluid, AbstractBlock.Settings settings, BackroomFluidBlock.Settings fluidSettings);
}
