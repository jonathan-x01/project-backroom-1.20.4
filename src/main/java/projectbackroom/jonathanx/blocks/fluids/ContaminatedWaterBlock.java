package projectbackroom.jonathanx.blocks.fluids;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.util.ColorManager;

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
