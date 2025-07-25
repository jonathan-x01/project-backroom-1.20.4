package projectbackroom.jonathanx.blocks.fluids;

import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.fluid.FlowableFluid;
import projectbackroom.jonathanx.init.BackroomStatusEffects;

public class BlackSludgeFluidBlock extends BackroomFluidBlock {
    public BlackSludgeFluidBlock(FlowableFluid fluid, AbstractBlock.Settings settings, BackroomFluidBlock.Settings fluidSettings) {
        super(fluid, settings, fluidSettings);
    }

    @Override
    protected StatusEffectInstance[] applyEffectsToEntities() {
        super.applyEffectsToEntities();
        return new StatusEffectInstance[]{
                new StatusEffectInstance(BackroomStatusEffects.INSANITY, 1800)
        };
    }
}
