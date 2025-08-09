package projectbackroom.jonathanx.block.fluids;

import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FlowableFluid;

public class ContaminatedWaterBlock extends BackroomFluidBlock {
    public ContaminatedWaterBlock(FlowableFluid fluid, AbstractBlock.Settings settings, BackroomFluidBlock.Settings fluidSettings) {
        super(fluid, settings, fluidSettings);
    }

    @Override
    protected StatusEffectInstance[] applyEffectsToEntities() {
        return new StatusEffectInstance[]{
                new StatusEffectInstance(StatusEffects.NAUSEA, 600, 1),
                new StatusEffectInstance(StatusEffects.POISON, 300, 1)
        };
    }
}
