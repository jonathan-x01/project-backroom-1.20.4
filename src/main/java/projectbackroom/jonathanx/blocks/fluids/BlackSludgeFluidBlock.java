package projectbackroom.jonathanx.blocks.fluids;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import projectbackroom.jonathanx.registry.ModStatusEffects;
import projectbackroom.jonathanx.util.ColorManager;

public class BlackSludgeFluidBlock extends BackroomFluidBlock {
    public BlackSludgeFluidBlock(FlowableFluid fluid, AbstractBlock.Settings settings, BackroomFluidBlock.Settings fluidSettings) {
        super(fluid, settings, fluidSettings);
    }

    @Override
    protected StatusEffectInstance[] applyEffectsToEntities() {
        super.applyEffectsToEntities();
        return new StatusEffectInstance[]{
                new StatusEffectInstance(ModStatusEffects.INSANITY, 1800)
        };
    }
}
